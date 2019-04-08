#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define NAME_MAX 256

//Quoted( http://goo.gl/yUOhFX )
#define pmttsize 10
int buffer[pmttsize];
bool used[pmttsize+1];
int p=0;

// Factorial -> n!
int factorial(int n) {
  int result = 1, k;

  for(k=1 ; k<=n ; k++) result *= k;
  return result;
}

// pmtt, Quoted( http://goo.gl/yUOhFX )
void prtpmtt(int n, int **cmp){
  int i;
  for(i=0; i<n; i++) cmp[p][i] = buffer[i];
  p++;
}

void pmtt(int n, int z, int **tst){
  int i;
  if (n == z) prtpmtt(n, tst);
  else{
    for(i=1; i<=n; i++){
      if(used[i]) continue;
      buffer[z] = i;
      used[i] = true;
      pmtt(n, z+1, tst);
      used[i] = false;
    }
  }
}

void pre_pmtt(int n, int **tst){
  pmtt(n, 0, tst);
}


// main
int main(int argc, char *argv[]){
  FILE *fp;
  char fn[NAME_MAX];
  
  int **mtrx, **cmp, N=0;
  int i, j;
  int n0, n1, n2, n3, n4, n5, n6, n7, n8, n9;
  int fctral, *fctralsum;
  int root1=0, root2=0, dstnc=0, checker=-1;
  
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

  // Factorial
  fctral = factorial(N);

  // Display
  printf("[Matrix of Distance]\n");
  for(i=0; i<N ; i++){
    for(j=0 ; j<N ; j++){
      printf("%d ", mtrx[i][j]);
    }
    printf("\n");
  }
  printf("Vertex = %d, Factreal(N) = %d.", N, fctral);
  printf("\n");
 
  // Generate(cmp)
  cmp = (int**)malloc(sizeof(int*) * fctral);  
  for(i=0 ; i<fctral ; i++){
    cmp[i] = (int*)malloc(sizeof(int) * N);
    for(j=0 ; j<N ; j++) cmp[i][j] = 0;
  }    

  // Pmtt
  pre_pmtt(N, cmp);

  // Generate(fctralsum)
  fctralsum = (int*)malloc(sizeof(int*) * fctral);
  for(i=0 ; i<fctral ; i++) fctralsum[i] = 0;

  // Rooting
  for(i=0 ; i<fctral ; i++){
    
    for(j=0 ; j<N-1 ; j++){ /* Consideration: Number of array. */
      root1 = cmp[i][j];   /* Rooting Start */
      root2 = cmp[i][j+1]; /* Rooting Next Phase */
      fctralsum[i] = fctralsum[i] + mtrx[root1-1][root2-1];
    } 
    fctralsum[i] = fctralsum[i] + mtrx[root2-1][cmp[i][0]-1];
  }

  dstnc = fctralsum[0];
  
  for(i=0 ; i<fctral ; i++){
    if(dstnc >= fctralsum[i]){ /* Change */
      checker = i;
      dstnc = fctralsum[i];
    }
  }
  
  // Display
  printf("\n[Result]\n");
  printf("Minimum Distance = %d,\n", dstnc);
  printf("Rooting: ");
  for(i=0 ; i<N ; i++) printf("%d ", cmp[checker][i]);
  printf("%d \n\n", cmp[checker][0]); 

  free(fctralsum);
  free(mtrx);
  free(cmp);
  return 0;
}
