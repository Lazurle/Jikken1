#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include<sys/time.h>

#define DIST_MAX 1.0e100

//構造体定義
struct node{
  int number;
  struct node *parent;
  struct node *child;
}typedef Node;

Node *listhead = NULL;
Node *listtail = NULL;

// 時刻を計測して double値として返す関数
double gettimeofday_sec() {
  struct timeval tv;
  gettimeofday( &tv, NULL );
  return tv.tv_sec + tv.tv_usec * 1e-6;
}

//閉路を巡回して、経路とその長さを表示する関数
void tour(Node *p,double **dist){
  double distance = 0.0;
  
  do{
  printf("->%d",p->number);
  distance += dist[p->number][p->child->number];
  p = p->child;
  }while(p != listhead);

  printf("->%d\n",listhead->number);
  printf("最短閉路長:%lf(NearestAddition)\n",distance);
}

//スタートする点を設定する関数
Node * setStart(int s){
  Node *p;

  p = (Node *)malloc(sizeof(Node));
  p->number = s;
  p->parent = NULL;
  p->child = NULL;
  
  listhead = listtail = p;

  return p;
}

void nearestSearch1(int N,int s,int *T,Node *p,double **dist){
  int i,j;
  int v;
  double distMin = DIST_MAX;
  Node *q;
  
  q = (Node *)malloc(sizeof(Node));
  q->number = 0;
  q->parent = NULL;
  q->child = NULL;

  for(i=0;i<N;i++)
    if(dist[s][i] < distMin && s != i){
      distMin =  dist[s][i];
      v = i;
    }
  T[v] = 1;
  q->number = v;
  //双方向に接続して閉路とする
  q->parent = q->child = p;
  p->parent = p->child = q;
  
}

void nearestSearch(int N,int *T,int nodeCount,Node *p,double **dist){
  int i,j;
  double distMin = DIST_MAX;
  Node *q;
  Node *tmp;
  
  q = (Node *)malloc(sizeof(Node));
  q->parent = NULL;
  q->child = NULL;
  
  for(j=0;j<nodeCount;j++){                 //閉路に含まれる点を巡回していく
    for(i=0;i<N;i++){                       //全ての点の中で
      if(T[i] == 0){                        //まだ訪問していない点
	if(distMin > dist[p->number][i]){
	  distMin = dist[p->number][i];
	  q->number = i;
	  q->parent = p->parent;
	  tmp = p;
	}
      }
    }
    
    p = p->child;
  }
  
  p = tmp;
  T[q->number] = 1;
  p->parent->child = q;
  p->parent = q;
  q->child = p;
  
}


void nearestAddition(int N,int s,double **dist){
  int i;
  int *T;            //部分閉路
  int nodeCount = 2; //訪問した都市のカウンタ
  Node *p;
  Node *tmp;

  //配列を動的に確保
  T = (int *)malloc(sizeof(int) * N);
  for(i=0;i<N;i++)
    T[i] = 0;

  //スタートを設定
  p = setStart(s);
  T[s] = 1;

  //次の都市を決定
  nearestSearch1(N,s,T,p,dist);

  //3都市目以降の都市を決定
  while(nodeCount < N){
    nearestSearch(N,T,nodeCount,listhead,dist);
    nodeCount++;
  }

  tour(listhead,dist);

  //領域を解放
  p = listhead;
  for(i=0;i<N;i++){
    tmp = p->child;
    free(p);
    p = tmp;
  }
  
}
  
      
int main(int argc,char *argv[]){
  int i,j,a,b,s,repeat;
  double d,t1,t2;
  int N;          //点の次数
  double **dist;  //距離行列
  FILE *fp;

  //実行時引数のチェック
  if(argc != 3){
    fprintf(stderr,"Useage:%s data repeat_number\n",argv[0]);
    exit(1);
  }

  //ファイルオープン
  fp = fopen(argv[1],"r");
  if(fp == NULL){
    fprintf(stderr,"File open error!\n");
    exit(1);
  }

  //繰り返し回数
  repeat = atoi(argv[2]);

  //点の次数を読み取る
  fscanf(fp,"%d",&N);

  //配列を動的に確保
  dist = (double **)malloc(sizeof(double *) * N);
  for(i=0;i<N;i++)
    dist[i] = (double *)malloc(sizeof(double) * N);

  //配列を初期化
  for(i=0;i<N;i++)
    for(j=0;j<N;j++)
      dist[i][j] = 0;

  //距離行列を作成
  while(fscanf(fp,"%d %d %lf",&a,&b,&d) != EOF){
    dist[a][b] = dist[b][a] = d;
  }  

  //ファイルクローズ
  fclose(fp);

  // 時刻計測(t1)
  t1 = gettimeofday_sec();
  
  for(i=0;i<repeat;i++){
    s = i % N;
    //Nearest Addtion法を実行
    nearestAddition(N,s,dist);
  }
  // 時刻計測(t2)
  t2 = gettimeofday_sec();
  
  fprintf( stdout, "かかった時間(平均): %.10f\n", (t2-t1)/repeat );
  
  
  //領域を解放
  for(i=0;i<N;i++)
    free(dist[i]);
  free(dist);
  
  return 0;
}
