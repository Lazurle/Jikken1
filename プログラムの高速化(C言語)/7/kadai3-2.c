/*** kadai3-2.c ***/

#include <stdio.h>
#include <stdlib.h>

#define MAX_REPEAT 1000		//�R�s�[�J��Ԃ���
#define SIZE 500               //�摜�T�C�Y�i�s����j

#ifdef CSQ
#define DIM1 3
#define DIM2 SIZE
#define DIM3 SIZE
#endif
#ifdef CIP
#define DIM1 SIZE
#define DIM2 SIZE
#define DIM3 3
#endif

typedef unsigned char UCHAR;

void write_ppm_csq_mem(UCHAR ***,char *,int ,int);
void write_ppm_cip_mem(UCHAR ***,char *,int ,int);
UCHAR ***malloc3Duchar(int ,int ,int);
void free3Duchar(UCHAR ***);
void error1(char *);


/* �摜�̃R�s�[ */
void copy3dimg_mem(UCHAR ***res,UCHAR ***org,int dim1,int dim2,int dim3)
{
  int i,j,k;

  for(i=0;i<dim1;i++)
    for(j=0;j<dim2;j++)
      for(k=0;k<dim3;k++)
	res[i][j][k]=org[i][j][k]/2;
}


int main(void)
{
  int repeat;
  UCHAR ***org,***res;

  org=malloc3Duchar(DIM1,DIM2,DIM3);	/* �쐬�摜 */
  res=malloc3Duchar(DIM1,DIM2,DIM3);	/* �R�s�[��摜 */

				/* �摜�̍쐬 */
  {
    int i,j,k;

    for(i=0;i<DIM1;i++)
      for(j=0;j<DIM2;j++)
	for(k=0;k<DIM3;k++)
	  org[i][j][k]=(UCHAR)((i*j+10*j+k*k)%256);
  }
     
				/* �摜�̃R�s�[ */
  for(repeat=0;repeat<MAX_REPEAT;repeat++)
    copy3dimg_mem(res,org,DIM1,DIM2,DIM3);

				/* �t�@�C���ւ̏o�� */
#ifdef CSQ
  write_ppm_csq_mem(res,"./tmp.ppm",DIM3,DIM2);
#endif
#ifdef CIP
  write_ppm_cip_mem(res,"./tmp.ppm",DIM2,DIM1);
#endif

  free3Duchar(res);
  free3Duchar(org);

  return 0;
}

void write_ppm_csq_mem(UCHAR ***data_buf,char *fname,int width,int height)
{
  FILE *fp;
  int m, n;
    
  if((fp = fopen(fname, "wb")) == NULL) {
    fprintf(stderr, "file(%s) can't open\n", fname) ;
    exit(1) ;
  }

  fprintf(fp, "P6\n") ;			/* �J���[�摜���o�C�i���[�f�[�^�̋L�� */
  fprintf(fp, "%d %d\n", width, height) ;	/* �摜�̕�(��)�ƍ���(�s��) */
  fprintf(fp, "255\n") ;		/* �ő�l */

  for(m=0;m<height;m++)
    for(n=0;n<width;n++)
    {
      fwrite(&data_buf[0][m][n], sizeof(UCHAR), 1, fp);
      fwrite(&data_buf[1][m][n], sizeof(UCHAR), 1, fp);
      fwrite(&data_buf[2][m][n], sizeof(UCHAR), 1, fp);
    }

  fclose(fp) ;
}

void write_ppm_cip_mem(UCHAR ***data_buf,char *fname,int width,int height)
{
  FILE *fp;
    
  if((fp = fopen(fname, "wb")) == NULL) {
    fprintf(stderr, "file(%s) can't open\n", fname) ;
    exit(1) ;
  }

  fprintf(fp, "P6\n") ;			/* �J���[�摜���o�C�i���[�f�[�^�̋L�� */
  fprintf(fp, "%d %d\n", width, height) ;	/* �摜�̕�(��)�ƍ���(�s��) */
  fprintf(fp, "255\n") ;		/* �ő�l */

  fwrite(&data_buf[0][0][0], sizeof(UCHAR), width*height*3, fp);

  fclose(fp) ;
}

UCHAR ***malloc3Duchar(int dim1,int dim2,int dim3)
{
  int i,j;
  UCHAR ***c1,**c2,*c3;

  if((c1 = (UCHAR ***)malloc(dim1*sizeof(UCHAR **))) == NULL)
    error1("can't allocate in malloc3Duchar");

  if((c2 = (UCHAR **)malloc(dim1*dim2*sizeof(UCHAR *))) == NULL)
    error1("can't allocate in malloc3Duchar");

  for(i=0;i<dim1;i++)
    c1[i]=c2+dim2*i;

  if((c3 = (UCHAR *)malloc(dim1*dim2*dim3*sizeof(UCHAR))) == NULL)
    error1("can't allocate in malloc3Duchar");

  for(i=0;i<dim1;i++)
    for(j=0;j<dim2;j++)
      c1[i][j]=c3+(dim2*i+j)*dim3;

  return c1;
}

void free3Duchar(UCHAR ***point)
{
  free(point[0][0]);
  free(point[0]);
  free(point);
}

/* �G���[���� */
void error1(char *message)
{
  printf("%s\n",message);
  exit(1);
}
