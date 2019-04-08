/*            img_proc.c                 */
/* Usage:  time a.out file.ppm kaisuu    */
/* Output: file_heikin.pgm, file_laplasian.pgm */
/*         file_res.ppm                  */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef unsigned char UCHAR;

				/* �摜�T�C�Y�͕ύX�s�� */
#define DIM1  960		/* �s�� */
#define DIM2 1280		/* �� */
#define DIM3    3

#define RED   0
#define GREEN 1
#define BLUE  2

#define WHITE 255
#define BLACK 0

#define ON 1
#define OFF 0

#define HALF_VAL 127            /* ���v���V�A���t�B���^�����O�̌��ʂɉ�����o�C�A�X�l */

#define THRESH 7                /* �v�Z���ԑ��莞��臒l�͂V�ŌŒ�i���ʂ̕ω�����������ΕύX����OK�j */
#define N_REPEAT 50	        /* �J�Ԃ��񐔁D����ԑ��莞�͕K��50�Ƃ���D */

void grayscale(UCHAR [][DIM2], UCHAR [][DIM2][DIM3], int , int );
void heikinka(UCHAR [][DIM2], UCHAR [][DIM2], int , int );
void copy(UCHAR [][DIM2], UCHAR [][DIM2], int , int );
void laplasian(UCHAR [][DIM2], UCHAR [][DIM2], int , int );
void zero_kousa(UCHAR [][DIM2][DIM3], UCHAR [][DIM2][DIM3], UCHAR [][DIM2], int, int, int );

void write_ppm_cip(UCHAR [][DIM2][DIM3],char *,int ,int );
void write_pgm_cip(UCHAR [][DIM2],char *,int ,int );
void read_ppm_cip(UCHAR [][DIM2][DIM3], char *);

  UCHAR org[DIM1][DIM2][DIM3];	/* ���͉摜 */
  UCHAR res[DIM1][DIM2][DIM3];	/* �o�͉摜 */
  UCHAR gray[DIM1][DIM2];	/* �O���[�X�P�[���摜�C�������摜*/
  UCHAR gray2[DIM1][DIM2];	/* �����p�O���[�X�P�[���摜 */
  UCHAR lap[DIM1][DIM2];	/* ���v���V�A���̌��ʉ摜 */

int main(int argc, char *argv[])
{
  int i,j,g,r,k;
  int kaisuu;			/* �������̌J��Ԃ��� */
  char in_fname[200];		/* ���͉摜�t�@�C���� */
  char out_fname[200];		/* �o�͉摜�t�@�C���� */
  
  if(argc < 3)			/* ���̓R�}���h�`�F�b�N */
  {
    printf("usage: a.out filename keisuu \n");
    exit(1);
  }
  else
  {
    strcpy(in_fname,argv[argc-2]); /* ���͉摜�̃t�@�C���� */
    kaisuu=atoi(argv[argc-1]);	/* �������̌J��Ԃ��� */
  }

  read_ppm_cip(org, in_fname);	/* �摜���t�@�C�����ǂݍ��� */

  for(g=0;g<DIM1;g++)		/* �����p�摜�C���v���V�A���摜�C�o�͉摜��0�ɏ�����(�O������0�ɂ��邽��) */
    for(r=0;r<DIM2;r++)
    {
      gray2[g][r]=0;
      lap[g][r]=0;
	  for(k=0;k<DIM3;k++)
		res[g][r][k]=0;
    }

				/* �t�B���^�����O(���ԑ���̂��ߕK��50��s���D) */
  for(i=0;i<N_REPEAT;i++)
  {
    grayscale(gray, org, DIM1, DIM2); /* ���͉摜(�J���[)����O���[�X�P�[���摜�쐬 */

#ifdef REMOVE
    for(j=0;j<kaisuu;j++)
    {
      heikinka(gray2, gray, DIM1, DIM2); /* 3�~3�̕��ω��t�B���^�ɂ�镽���� */
      copy(gray, gray2, DIM1, DIM2); /* �J��Ԃ��̂��߂ɃR�s�[ */
    }

    laplasian(lap, gray, DIM1, DIM2); /* ���v���V�A���̌��ʉ摜�쐬 */

    zero_kousa(res, org, lap, DIM1, DIM2, DIM3); /* �o�͉摜�쐬�i�[�������@�j */
#endif
  }  
				
  in_fname[strlen(in_fname)-4]='\0'; /* ���͉摜�t�@�C��������.ppm���� */

  sprintf(out_fname,"%s_heikin.pgm",in_fname); /* �i�O���[�X�P�[���摜�j�������摜�p�o�͉摜�t�@�C�����ݒ� */
  write_pgm_cip(gray,out_fname,DIM2,DIM1); /* �i�O���[�X�P�[���摜�j�������摜���t�@�C���֏o�� */

  sprintf(out_fname,"%s_laplasian.pgm",in_fname); /* ���v���V�A���̌��ʉ摜�p�t�@�C�����ݒ� */
  write_pgm_cip(lap,out_fname,DIM2,DIM1); /* ���v���V�A���̌��ʉ摜���t�@�C���֏o�� */

  sprintf(out_fname,"%s_res.ppm",in_fname); /* �o�͉摜�t�@�C�����ݒ� */
  write_ppm_cip(res,out_fname,DIM2,DIM1); /* �o�͉摜���t�@�C���֏o�� */

  return 0;
}

 /* �J���[�摜����O���[�X�P�[���摜�쐬 */
void grayscale(UCHAR gray[][DIM2], UCHAR data[][DIM2][DIM3], int n_gyou, int n_retu)
{
  int g,r,k;
  int tmp;

  for(g=0;g<n_gyou;g++)
    for(r=0;r<n_retu;r++)
    {
      tmp=0;
      for(k=0;k<DIM3;k++)
      {
	tmp += data[g][r][k];
      }
      gray[g][r]=(UCHAR)(tmp/3.0+0.5); /* ���ϒl���l�̌ܓ� */
    }
}

 /* 3�~3�̕��ω��t�B���^�ɂ�镽���� */
 /* �O�����͏������Ȃ��B�\��0�������Ă���B */
void heikinka(UCHAR gray[][DIM2], UCHAR data[][DIM2], int n_gyou, int n_retu)
{
  int g,r,i,j;
  double tmp;

  for(g=1;g<n_gyou-1;g++)
    for(r=1;r<n_retu-1;r++)
    {
      tmp=0.0;
      for(i=-1;i<=1;i++)
	for(j=-1;j<=1;j++)
	  tmp += (double)data[g+i][r+j]/9.0;
      gray[g][r]=(UCHAR)(tmp+0.5); /* �l�̌ܓ� */
    }
}

 /* �O���[�X�P�[���摜�̃R�s�[ */
void copy(UCHAR gray[][DIM2], UCHAR data[][DIM2], int n_gyou, int n_retu)
{
  int g,r;

  for(g=0;g<n_gyou;g++)
    for(r=0;r<n_retu;r++)
      gray[g][r]=data[g][r];
}


/* ���v���V�A���̌��ʉ摜�쐬 */
/* �O�����͏������Ȃ��B�\��0�������Ă���B */
void laplasian(UCHAR lap[][DIM2], UCHAR data[][DIM2], int n_gyou, int n_retu)
{
  int g,r;
  int tmp;
  
  for(g=1;g<n_gyou-1;g++)
    for(r=1;r<n_retu-1;r++)
    {
      tmp= -data[g-1][r-1]-data[g-1][r]-data[g-1][r+1] 
	   -data[g][r-1]+8*data[g][r]-data[g][r+1]
	   -data[g+1][r-1]-data[g+1][r]-data[g+1][r+1];
      tmp += HALF_VAL; /* ���̒l�ɂ��邽��127�𑫂� */

      if(tmp > 255)
    	lap[g][r]=255;
      else if(tmp < 0)
	lap[g][r]=0;
      else
	lap[g][r]=tmp;
    }
}


/* ���摜�̃[�������̉�f�����F�ɂ����o�͉摜�쐬 */
/* �ŊO�����Q��i�Q�s�j�͏������Ȃ��B�\��0�������Ă���B */
void zero_kousa(UCHAR res[][DIM2][DIM3], UCHAR org[][DIM2][DIM3], UCHAR data[][DIM2], int n_gyou, int n_retu, int col)
{
  int g, r, i, j, k;
  int flag_p; /* �l���iHALF_VAL + THRESH�j���傫�������`�F�b�N����t���O */
  int flag_m; /* �l���iHALF_VAL - THRESH�j��菬���������`�F�b�N����t���O */

  for (g=2; g<n_gyou-2; g++)
    for (r=2; r<n_retu-2; r++)
    {
      flag_p = flag_m = OFF;
      for (i=-2; i<=2; i++)
	for (j=-2; j<=2; j++)
          if (data[g+i][r+j] < HALF_VAL-THRESH)
	    flag_m = ON;                /* �i���v���V�A���̒l < -THRESH�j�̉�f���ߖT�ɂP��f�ł������ON */
	  else if (data[g+i][r+j] > HALF_VAL+THRESH)
	    flag_p = ON;                /* �i���v���V�A���̒l > THRESH�j�̉�f���ߖT�ɂP��f�ł������ON */
      
      if ((flag_p == 1) && (flag_m == 1)) /* ����̃t���O��ON���ƃG�b�W�Ƃ��ĉ��F�ɂ��� */
      {
	res[g][r][0] = WHITE;
	res[g][r][1] = WHITE;
	res[g][r][2] = BLACK;
      }
      else                             /* �����łȂ���Ό��摜�̐F */
	for (k=0; k<col; k++)
	  res[g][r][k] = org[g][r][k];
    }
}



#define W_BYTE DIM1*DIM2*DIM3

/* CIP�`���̉摜�f�[�^���PPM�t�@�C���쐬 */
void write_ppm_cip(UCHAR data_buf[][DIM2][DIM3],char *fname,int width,int height)
{
  FILE *fp;
  
				/* �t�@�C�����J�� */
  if((fp = fopen(fname, "wb")) == NULL) {
    fprintf(stderr, "file(%s) can't open\n", fname) ;
    exit(1) ;
  }

  fprintf(fp, "P6\n") ;		/* �J���[�摜���o�C�i���[�f�[�^�̋L�� */
  fprintf(fp, "%d %d\n", width, height) ; /* �摜�̕�(��)�ƍ���(�s��) */
  fprintf(fp, "255\n") ;	/* �ő�l */

  {				/* �摜�f�[�^��repeat(+1)�ɕ������ď������� */
    int i;
    int repeat=(DIM1*DIM2*DIM3)/(W_BYTE); /* ������ */
    int rest=DIM1*DIM2*DIM3-repeat*(W_BYTE); /* �]��f�[�^�� */
    UCHAR *pt=(UCHAR *)&data_buf[0][0][0]; /* �������ރf�[�^�̈ʒu�����|�C���^ */

    for(i=0;i<repeat;i++)
    {
      fwrite(pt, sizeof(UCHAR), W_BYTE, fp);
      pt += W_BYTE;
    }
    if(rest > 0)
      fwrite(pt, sizeof(UCHAR), rest, fp);
  }
  
  fclose(fp);			/* �t�@�C������� */    
}


/* CIP�`���̉摜�f�[�^���PGM�t�@�C���쐬 */
void write_pgm_cip(UCHAR data_buf[][DIM2],char *fname,int width,int height)
{
  FILE *fp;
  
				/* �t�@�C�����J�� */
  if((fp = fopen(fname, "wb")) == NULL) {
    fprintf(stderr, "file(%s) can't open\n", fname) ;
    exit(1) ;
  }

  fprintf(fp, "P5\n") ;		/* �O���[�X�P�[���摜���o�C�i���[�f�[�^�̋L�� */
  fprintf(fp, "%d %d\n", width, height) ; /* �摜�̕�(��)�ƍ���(�s��) */
  fprintf(fp, "255\n") ;	/* �ő�l */

  fwrite(&data_buf[0][0], sizeof(UCHAR), DIM1*DIM2, fp);
  
  fclose(fp);			/* �t�@�C������� */    
}


#define R_BYTE DIM1*DIM2*DIM3

/* ppm�t�H�[�}�b�g�̉摜�t�@�C����CIP�`���̉摜�������ɓǂݍ��� */
void read_ppm_cip(UCHAR data_buf[][DIM2][DIM3], char *fname)
{
  FILE	*fp ;
  char	str_buf[1024] ;
  char	magic_num[8] ;		/* �}�W�b�N�i���o�[ */
  int	max_val ;		/* ��f�l�̍ő�l */    
  int width,height;
  int count_limit;

				/* �t�@�C�����J�� */    
  if((fp = fopen(fname, "rb")) == NULL) {
    fprintf(stderr, "file(%s) can't open.\n", fname) ;
    exit(1) ;
  }
				/* �}�W�b�N�i���o�[�ǂݍ��� */
  count_limit=0;
  sprintf(str_buf,"#");
  while((str_buf[0]=='#') || (str_buf[0]=='\n') || (str_buf[0]=='\t') || (str_buf[0]==' '))
  {
    fgets(str_buf,1024,fp);
    count_limit++;
    if(count_limit > 1000)
    {
      fprintf(stderr,"ERROR: Irregal file format.\n");
      exit(1);
    }
  }
  strcpy(magic_num, str_buf);
  magic_num[strlen(magic_num)-1]='\0'; /* \n������ */
  if(strcmp(magic_num, "P6") != 0) 
  {
    fprintf(stderr, "ERROR: magic number(%s) not match.\n", magic_num) ;
    exit(1) ;
  }

				/* �摜�̕�(��)�ƍ���(�s��) */
  count_limit=0;
  sprintf(str_buf,"#");
  while((str_buf[0]=='#') || (str_buf[0]=='\n') || (str_buf[0]=='\t') || (str_buf[0]==' '))
  {
    fgets(str_buf,1024,fp);
    count_limit++;
    if(count_limit > 1000)
    {
      fprintf(stderr,"ERROR: Irregal file format.\n");
      exit(1);
    }
  }
  sscanf(str_buf,"%d %d",&width,&height);
  if((height != DIM1) || (width != DIM2)) /* �摜�̃T�C�Y�����肵���l�ƈقȂ�ꍇ�̓G���[�ŏI�� */
  {
    fprintf(stderr, "ERROR: Dimension dosenot match.\n");
    exit(1) ;
  }

				/* �ő�l */
  count_limit=0;
  sprintf(str_buf,"#");
  while((str_buf[0]=='#') || (str_buf[0]=='\n') || (str_buf[0]=='\t') || (str_buf[0]==' '))
  {
    fgets(str_buf,1024,fp);
    count_limit++;
    if(count_limit > 1000)
    {
      fprintf(stderr,"ERROR: Irregal file format.\n");
      exit(1);
    }
  }
  sscanf(str_buf,"%d",&max_val);
  if(max_val > 255)
  {
    fprintf(stderr, "ERROR: Irregal max value.\n");
    exit(1) ;
  }

				/* �摜�f�[�^��repeat(+1)�ɕ������ēǂݍ��� */
  {
    int i;
    int repeat=(DIM1*DIM2*DIM3)/(R_BYTE); /* ������ */
    int rest=DIM1*DIM2*DIM3-repeat*(R_BYTE); /* �]��f�[�^�� */
    UCHAR *pt=(UCHAR *)&data_buf[0][0][0]; /* �ǂݍ��݃f�[�^�̈ʒu�����|�C���^ */

    for(i=0;i<repeat;i++)
    {
      fread(pt, sizeof(UCHAR), R_BYTE, fp);
      pt += R_BYTE;
    }
    if(rest > 0)
      fread(pt, sizeof(UCHAR), rest, fp);
  }

  /* �t�@�C������� */    
  fclose(fp);
}
