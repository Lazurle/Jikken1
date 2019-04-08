#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define NAME_MAX 256

// Nearest Neighbor
void nn(int tmp, int **mtrx, int **mtrxcpy, int N, int dstnc){
  /* nn(tmp, mtrx, N, 0); */
  int i, focus, tmp_dstnc=1000;
  int itl=0;
  
  printf("%d", tmp);
  
  for(i=0 ; i<N ; i++){
    if(mtrx[tmp][i] !=0 && 
       mtrx[tmp][i] < tmp_dstnc){
      tmp_dstnc = mtrx[tmp][i];
      focus = i;
    }
  }
  
  if(tmp_dstnc == 1000){
    dstnc += mtrxcpy[tmp][0];
    printf(" %d, \n", itl);
    printf("Minimum Distance = %d\n", dstnc);
    return;
  }

  dstnc += tmp_dstnc;
  for(i=0 ; i<N ; i++) mtrx[i][tmp]=0;
  tmp = focus;
  printf(" ");
  nn(tmp, mtrx, mtrxcpy, N, dstnc);
}


// main
int main(int argc, char *argv[]){
  FILE *fp;
  char fn[NAME_MAX];
  
  int **mtrx, **mtrxcpy, N=0;
  int i, j;
  int n0, n1, n2, n3, n4, n5, n6, n7, n8, n9;
  // int fctral, *fctralsum;
  // int root1=0, root2=0;
  int tmp=0/*, checker=-1*/;
  
  if(argc != 2){
    fprintf(stderr, "Usage: %s graph_file\n", argv[0]); 
    exit(1);
  }
  strcpy(fn, argv[1]);
  if((fp = fopen(fn, "r")) == NULL){
    fprintf(stderr, "File Open Error: %s\n", fn);
    exit(1);
  }

  fscanf(fp, "%d", &N);
  
  // Generate(mtrx)
  mtrx = (int**)malloc(sizeof(int*) * N);  
  for(i=0 ; i<N ; i++){
    mtrx[i] = (int*)malloc(sizeof(int) * N);
    for(j=0 ; j<N ; j++) mtrx[i][j] = 0;
  }    

  // Generate(mtrxcpy)
  mtrxcpy = (int**)malloc(sizeof(int*) * N);  
  for(i=0 ; i<N ; i++){
    mtrxcpy[i] = (int*)malloc(sizeof(int) * N);
    for(j=0 ; j<N ; j++) mtrxcpy[i][j] = 0;
  }    
  
  // Include
  i=0;

  switch (N) {;
  case 3:
    while(fscanf(fp, "%d %d %d", &n0, &n1, &n2) != EOF){
      mtrx[i][0] = n0;
      mtrx[i][1] = n1;
      mtrx[i][2] = n2;
      i++;
    }
    break;
  case 4:
    while(fscanf(fp, "%d %d %d %d", &n0, &n1, &n2, &n3) != EOF){
      mtrx[i][0] = n0;
      mtrx[i][1] = n1;
      mtrx[i][2] = n2;
      mtrx[i][3] = n3;
      i++;
    }
    break;
  case 5:
    while(fscanf(fp, "%d %d %d %d %d", &n0, &n1, &n2, &n3, &n4) != EOF){
      mtrx[i][0] = n0;
      mtrx[i][1] = n1;
      mtrx[i][2] = n2;
      mtrx[i][3] = n3;
      mtrx[i][4] = n4;
      i++;
    }
    break;

  case 6:
    while(fscanf(fp, "%d %d %d %d %d %d", 
		 &n0, &n1, &n2, &n3, &n4, &n5) != EOF){
      mtrx[i][0] = n0;
      mtrx[i][1] = n1;
      mtrx[i][2] = n2;
      mtrx[i][3] = n3;
      mtrx[i][4] = n4;
      mtrx[i][5] = n5;
      i++;
    }
    break;

  case 7:
    while(fscanf(fp, "%d %d %d %d %d %d %d", 
		 &n0, &n1, &n2, &n3, &n4, &n5, &n6) != EOF){
      mtrx[i][0] = n0;
      mtrx[i][1] = n1;
      mtrx[i][2] = n2;
      mtrx[i][3] = n3;
      mtrx[i][4] = n4;
      mtrx[i][5] = n5;
      mtrx[i][6] = n6;
      i++;
    }
    break;

  case 8:
    while(fscanf(fp, "%d %d %d %d %d %d %d %d", 
		 &n0, &n1, &n2, &n3, &n4, &n5, &n6, &n7) != EOF){
      mtrx[i][0] = n0;
      mtrx[i][1] = n1;
      mtrx[i][2] = n2;
      mtrx[i][3] = n3;
      mtrx[i][4] = n4;
      mtrx[i][5] = n5;
      mtrx[i][6] = n6;
      mtrx[i][7] = n7;
      i++;
    }
    break;

  case 9:
    while(fscanf(fp, "%d %d %d %d %d %d %d %d %d", 
		 &n0, &n1, &n2, &n3, &n4, &n5, &n6, &n7, &n8) != EOF){
      mtrx[i][0] = n0;
      mtrx[i][1] = n1;
      mtrx[i][2] = n2;
      mtrx[i][3] = n3;
      mtrx[i][4] = n4;
      mtrx[i][5] = n5;
      mtrx[i][6] = n6;
      mtrx[i][7] = n7;
      mtrx[i][8] = n8;
      i++;
    }    
    break;

  case 10:
    while(fscanf(fp, "%d %d %d %d %d %d %d %d %d %d", 
		 &n0, &n1, &n2, &n3, &n4, &n5, &n6, &n7, &n8, &n9) != EOF){
      mtrx[i][0] = n0;
      mtrx[i][1] = n1;
      mtrx[i][2] = n2;
      mtrx[i][3] = n3;
      mtrx[i][4] = n4;
      mtrx[i][5] = n5;
      mtrx[i][6] = n6;
      mtrx[i][7] = n7;
      mtrx[i][8] = n8;
      mtrx[i][9] = n9;
      i++;
    }
    break;

  default:
    fprintf(stderr, "File Open Error: Include Error!!\n");
    break;
  }

  // Include(mtrxcpy)
  for(i=0 ; i<N ; i++){
    for(j=0 ; j<N ; j++)
      mtrxcpy[i][j] = mtrx[i][j];
  }

  // Display
  printf("[Matrix of Distance]\n");
  for(i=0; i<N ; i++){
    for(j=0 ; j<N ; j++){
      printf("%d ", mtrx[i][j]);
    }
    printf("\n");
  }
  printf("Vertex = %d.", N);
  printf("\n");
 
  // Display
  printf("\n[Result]\n");

  // Nearest Neighbor
  printf("Rooting: ");
  nn(tmp, mtrx, mtrxcpy, N, 0);

  //  printf("Minimum Distance = %d,\n", dstnc);
  printf("\n"); 

  //  free(fctralsum);
  free(mtrx);
  free(mtrxcpy);
  //  free(cmp);
  return 0;
}
