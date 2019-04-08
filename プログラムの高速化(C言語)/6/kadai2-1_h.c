#include <stdio.h>
#include <stdlib.h>

char i1;
void func1(short);
void func2(short *);
void func3(int **);

int main(void)
{
  int *i2;
  int *i3;
  static short i4 = -2;

  printf("&i1=%p\n",&i1);
  printf("&i2=%p\n",&i2);
  printf("&i3=%p\n",&i3);
  printf("&i4=%p\n",&i4);

  func1(i4);
  func2(&i4);

  i2=(int *)malloc(sizeof(int));
  *i2=3;
  printf("*i2=%d\n",*i2);
  printf("i2=%p\n\n",i2);

  func3(&i3);
  printf("*i3=%d\n\n",*i3);
  printf("i3=%p\n\n",i3);
  free(i3);
    free(i2);

  return 0;
}

void func1(short i5)
{
  printf("i5=%d\n",i5);
  printf("&i5=%p\n\n",&i5);
}

void func2(short *i6)
{
  *i6=(*i6)*(*i6);
  printf("*i6=%d\n",*i6);
  printf("i6=%p\n",i6);
  printf("&i6=%p\n\n",&i6);
}

void func3(int **i7)
{
  *i7=(int *)malloc(sizeof(int));
  **i7=7;
  printf("**i7=%d\n",**i7);
  printf("*i7=%p\n",*i7);
  printf("i7=%p\n",i7);
  printf("&i7=%p\n\n",&i7);
}
