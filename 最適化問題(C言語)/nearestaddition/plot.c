#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h>
#include<time.h>

#define D 10000000
#define CITY_MAX 10000

struct vector{
  double x;
  double y;
}typedef vector;

int main(int argc,char *argv[]){
  int i,j;
  int N;            //都市数
  vector *position; //都市の座標
  double dist[3];   //都市間の距離
  FILE *fp;
  char filename[32];
  
  //実行時引数
  if(argc != 2){
    printf("Useage:%s number_of_cities\n",argv[0]);
    exit(1);
  }

  N = atoi(argv[1]);

  //最大都市数を制限
  if(N > CITY_MAX){
    printf("Too large! Please input values 1 and %d\n",CITY_MAX);
      exit(1);
  }
  
  printf("Create a data of %d cities\n",N);

  //配列を動的に確保
  for(i=0;i<N;i++)
    position = (vector *)malloc(sizeof(vector) * N);

  srand(time(NULL));

  //都市の位置(座標)を設定
  for(i=0;i<N;i++){
    position[i].x = rand() / D;
    position[i].y = rand() / D;
  }

  //ファイル名を設定
  sprintf(filename,"city_%d.txt",N);
  printf("Output to %s\n",filename);

  //ファイルオープン
  fp = fopen(filename,"w");
  if(fp == NULL){
    printf("File open error!\n");
    exit(1);
  }

  //都市数を書き込み
  fprintf(fp,"%d\n",N);

  //都市間の距離を書き込み
  for(i=0;i<N;i++)
    for(j=i+1;j<N;j++){
      dist[0] = position[i].x - position[j].x;  //x方向の距離
      dist[1] = position[i].y - position[j].y;  //y方向の距離
      dist[2] = sqrt(pow(dist[0],2) + pow(dist[1],2));
      fprintf(fp,"%d %d %lf\n",i,j,dist[2]);
    }

  //ファイルクローズ
  fclose(fp);

  //領域を解放
  free(position);
  
  printf("Success!\n");
  
  return 0;
}
