#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define NAME_MAX 256

// v: 次に飛ぶ点
// sum: 最短経路の保存
void circuit (int v,int N,int **d,int sum){
  int i,min,m_place;
  printf(" %d ",v);

  min=99;
  for(i=0;i<N;i++){
    if(d[v][i]<min&&d[v][i]!=0){
      min=d[v][i];
      m_place=i;
    }
  }
  if(min==99){
    sum=sum+d[v][0];
    printf("-> 0\n");//最後の部分の距離が反映されていない
    printf("経路の大きさは%d\n",sum);
    return;
  }
  sum=sum+min;
  for(i=0;i<N;i++){
    d[i][v]=0;
  }
  v=m_place;
  printf("->");
  circuit(v,N,d,sum);

}


int main (int argc, char *argv[]){
  int **adjacent;
  // int **load;
  // int *sum;
  int N,/*Nkai,*/i,j/*,min_num,point*/;
  int a,b,c,d,e,f,g,h,li,lj,k,l,rand/*,A,B*/;//一時的読み込み
  FILE *fp;
  char fn[NAME_MAX];
  // int next_num[N];
  if(argc!= 2){
    fprintf(stderr,"usage: %s graph_file\n",argv[0]);
    exit(1);
  }
  //argv[1]にファイル名が入る
  strcpy(fn,argv[1]);
  fp = fopen( fn , "r" );
  if(fp == NULL){
    printf( "error\n" );
    exit(1);
  }
  fscanf(fp,"%d",&N);

  adjacent=(int**)malloc(sizeof(int *)*N);
  for(i=0;i<N;i++){
    adjacent[i]=(int*)malloc(sizeof(int)*N);
   for(j=0;j<N;j++)
     adjacent[i][j]=0;
  }

  i=0;
  switch(N){
  case 3:
    while(fscanf(fp,"%d %d %d",&a,&b,&c)!=EOF){
	adjacent[i][0]=a;
	adjacent[i][1]=b;
	adjacent[i][2]=c;
	i++;
    } 
    break;
  case 4:
    while(fscanf(fp,"%d %d %d %d",&a,&b,&c,&d)!=EOF){
	adjacent[i][0]=a;
	adjacent[i][1]=b;
	adjacent[i][2]=c;
	adjacent[i][3]=d;
	i++;
    } 
    break;
  case 5:
    while(fscanf(fp,"%d %d %d %d %d",&a,&b,&c,&d,&e)!=EOF){
	adjacent[i][0]=a;
	adjacent[i][1]=b;
	adjacent[i][2]=c;
	adjacent[i][3]=d;
	adjacent[i][4]=e;
	i++;
    } 
    break;
  case 6:
    while(fscanf(fp,"%d %d %d %d %d %d",&a,&b,&c,&d,&e,&f)!=EOF){
	adjacent[i][0]=a;
	adjacent[i][1]=b;
	adjacent[i][2]=c;
	adjacent[i][3]=d;
	adjacent[i][4]=e;
	adjacent[i][5]=f;
	i++;
    } 
    break;
  case 7:
    while(fscanf(fp,"%d %d %d %d %d %d %d",&a,&b,&c,&d,&e,&f,&g)!=EOF){
	adjacent[i][0]=a;
	adjacent[i][1]=b;
	adjacent[i][2]=c;
	adjacent[i][3]=d;
	adjacent[i][4]=e;
	adjacent[i][5]=f;
	adjacent[i][6]=g;
	i++;
    } 
    break;
  case 8:
    while(fscanf(fp,"%d %d %d %d %d %d %d %d",&a,&b,&c,&d,&e,&f,&g,&h)!=EOF){
      adjacent[i][0]=a;
      adjacent[i][1]=b;
      adjacent[i][2]=c;
      adjacent[i][3]=d;
      adjacent[i][4]=e;
      adjacent[i][5]=f;
      adjacent[i][6]=g;
      adjacent[i][7]=h;
      i++;
    } 
    break;
  case 9:
    while(fscanf(fp,"%d %d %d %d %d %d %d %d %d",&a,&b,&c,&d,&e,&f,&g,&h,&li)!=EOF){
      adjacent[i][0]=a;
      adjacent[i][1]=b;
      adjacent[i][2]=c;
      adjacent[i][3]=d;
      adjacent[i][4]=e;
      adjacent[i][5]=f;
      adjacent[i][6]=g;
      adjacent[i][7]=h;
      adjacent[i][8]=li;
      i++;
    } 
    break;
case 10:
  while(fscanf(fp,"%d %d %d %d %d %d %d %d %d %d",&a,&b,&c,&d,&e,&f,&g,&h,&li,&lj)!=EOF){
      adjacent[i][0]=a;
      adjacent[i][1]=b;
      adjacent[i][2]=c;
      adjacent[i][3]=d;
      adjacent[i][4]=e;
      adjacent[i][5]=f;
      adjacent[i][6]=g;
      adjacent[i][7]=h;
      adjacent[i][8]=li;
      adjacent[i][9]=lj;
      i++;
    } 
    break;
case 11:
  while(fscanf(fp,"%d %d %d %d %d %d %d %d %d %d %d",&a,&b,&c,&d,&e,&f,&g,&h,&li,&lj,&k)!=EOF){
      adjacent[i][0]=a;
      adjacent[i][1]=b;
      adjacent[i][2]=c;
      adjacent[i][3]=d;
      adjacent[i][4]=e;
      adjacent[i][5]=f;
      adjacent[i][6]=g;
      adjacent[i][7]=h;
      adjacent[i][8]=li;
      adjacent[i][9]=lj;
      adjacent[i][10]=k;
      i++;
    } 
    break;
  case 12:
    while(fscanf(fp,"%d %d %d %d %d %d %d %d %d %d %d %d",&a,&b,&c,&d,&e,&f,&g,&h,&li,&lj,&k,&l)!=EOF){
      adjacent[i][0]=a;
      adjacent[i][1]=b;
      adjacent[i][2]=c;
      adjacent[i][3]=d;
      adjacent[i][4]=e;
      adjacent[i][5]=f;
      adjacent[i][6]=g;
      adjacent[i][7]=h;
      adjacent[i][8]=li;
      adjacent[i][9]=lj;
      adjacent[i][10]=k;
      adjacent[i][11]=l;
      i++;
    } 
    break;
  default:
    puts("this is over ...");
    return 1;
  }
  for(i=0;i<N;i++){
    for(j=0;j<N;j++){
      printf(" %d ",adjacent[i][j]);
    }
    printf("\n");    
  }
  //  Nkai=factorial(N);
  // printf("Nkai=%d\n",Nkai);
  puts("");
  /*  srand(10);
  rand();
  rand();
  rand();
  rand();
  rand();
  rand=rand()%N;*/
  rand = 0;
  circuit(rand,N,adjacent,0);
  return 0;
}
