#include<stdio.h>
#include<string.h>
#include<math.h>
#include<stdlib.h>

struct hoge{
  int a;
  unsigned char b;
  char c[2];
};

int main(void){
    int j = 0;
  struct hoge d = {-1,255,{-128,127}};
  unsigned char *pb = &d.b;
  char name[]="yuasa";
  char *pname=name;
  short e[2] = {-3,3};

//アドレスと値の表示
for(j=0;j<sizeof(d.a);j++)printf("%p %x \n ",((char *)&d.a+j),*((char *)&d.a+j));

for(j=0;j<sizeof(d.b);j++)printf("%p %x \n ",((char *)&d.b+j),*((char *)&d.b+j));

for(j=0;j<sizeof(d.c);j++)printf("%p %x \n ",((char *)&d.c+j),*((char *)&d.c+j));
printf("struct hoge d\n");

for(j=0;j<sizeof(pb);j++)printf("%p %x \n ",((char *)&pb+j),*((char *)&pb+j));
printf("pointa hensu pb \n");

for(j=0;j<sizeof(name);j++)printf("%p %x \n ",((char *)&name+j),*((char *)&name+j));
printf("hairetsu name \n");

for(j=0;j<sizeof(pname);j++)printf("%p %x \n ",((char *)&pname+j),*((char *)&pname+j));
printf("*pname = name no sento addres\n");

for(j=0;j<sizeof(e);j++)printf("%p %x \n ",((char *)&e+j),*((char *)&e+j));
printf("e hairetu \n");

  return 0;
}
