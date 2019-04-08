#include<stdio.h>
#include<string.h>
#include<math.h>
#include<stdlib.h>

int main(void){
short p[4]={3,-16,0,-257};
short *p2 = (short *)((char *)((int *)p-1)-1)+sizeof(p)/sizeof(int)+1;//計算すると結局p+1
int n;

for(n=0;n<3;n++)printf("%d,%d \n ",n,p2[n]);//p[]の配列３

printf("%d \n",sizeof(p));

int i,j;
for(j=0;j<sizeof(p);j++)
printf("%p %02x \n",((char *)&p+j),*((char *)&p+j));
//char型にする理由は一つずつ見ていくため、int型だと４つ飛ばしで見て言ってしまう

printf("\n");

return 0;
}
