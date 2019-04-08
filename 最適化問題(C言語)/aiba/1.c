// テストケース
5
0 3 4 2 3
1 0 2 5 8
7 5 0 6 1
3 5 8 0 4
6 4 3 2 0

// プログラム
#include    <stdio.h>

#define MAXN (100)
#define YES (1)
#define NO (0)

int n;            // 巡回すべき頂点数
int a[MAXN+1];            // 巡回経路(お試し経路)
int used[MAXN+1];            // 巡回済か否か
int dist[MAXN+1][MAXN+1];    // 隣接行列 dist[a][b] 頂点aから頂点bまでの距離(コスト)
int mina[MAXN+1];         // 最小コストが得られる巡回経路
int min = 10000;

void perm(int d);

int main(int argc, char **argv)
{
  int  i, j;
  FILE*   fp;
  if (argc != 2) {
    // 実行ファイル(a.out)とファイル名の2語以外の入力なら強制終了
    printf("Usage: %s <filename>\n", argv[0]);
    exit(1);
  }
  fp = fopen(argv[1], "r");
  if (fp == NULL) {
    printf("File not found.\n");
    exit(1);
  }
  //  最初の値はグラフの頂点数
  fscanf(fp, "%d", &n);
  //  範囲外の頂点数の場合は強制終了
  if ((n > MAXN) || (n <= 0)) {
    printf("Out of range: n.\n");
    exit(1);
  }
  //  隣接行列のデータの読みこみ
  for (i = 0; i < n; i++) {
    for (j = 0; j < n; j++) {
      fscanf(fp, "%d", &dist[i][j]);
    }
  }
  //  隣接行列のデータの出力
  for (i = 0; i < n; i++) {
    for (j = 0; j < n; j++) {
      printf("%d ", dist[i][j]);
    }
    printf("\n");
  }
  //  始めはどの値も使っていない
  for (i = 0; i < n; i++) {
    used[i] = NO;
  }
  perm(0);    //  探索
  // 最短経路(距離合計)の表示
  printf("%d\n", min);
  // 最短経路(訪問順序)の表示
  for (i = 0; i < n; i++) {
    printf("%d", mina[i]); 
  }
  printf("\n");
}

/* 深さdの節点を根とする木を作成する関数 */
void perm(int d)
{
  int i;
  int length = 0;
  if (d == n) {
    ///// 深さ == 頂点数
    //     -> 高いか安いかは不明だが、何か一つ(お試し)経路ができた
    //  現在のお試し経路を表示
    for (i = 0; i < n; i++) {
      printf("%d", a[i]);
    }
    printf("\n");
    //  経路にしたがって距離(コスト)を総和
    for (i = 0; i < n-1; i++) {
      length += dist[ a[i] ][ a[i+1] ];
    }
    // 最後に最終点から開始点へのコストを加える
    length += dist[ a[n-1] ][ a[0] ];
    // よりコストが少ない訪問順が得られたら記録する
    if(length < min) {
      min = length;
      for (i = 0; i < n; i++) {
	mina[i] = a[i];
      }
    } 
  } else {
    // 深さ < 頂点数なので経路を列挙する
    for (i = 0; i < n; i++) {
      if (used[i] == NO) {
	a[d] = i; // 配列のd番目にiを代入する(お試し用経路を作る)
	used[i] = YES; // iを使ったことを記憶する
	perm(d + 1); // 再帰呼び出し
	used[i] = NO; // iを使ったことを忘れる
      }
    }
  }
}
