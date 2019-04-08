#include <stdio.h>

int main(void)
{
    int i=2;
    int *pi;
    int **ppi;
    

          ppi = &pi;
          pi = &i;

    printf("i=%d\n",**ppi);
    
    {
      int j;

      printf("\n");
      for(j=0;j<sizeof(int **);j++)
	printf("%p %x\n",((char *)&ppi+j),*((char *)&ppi+j));
      printf("\n");
      for(j=0;j<sizeof(int *);j++)
	printf("%p %x\n",((char *)&pi+j),*((char *)&pi+j));
      printf("\n");
      for(j=0;j<sizeof(int);j++)
	printf("%p %x\n",((char *)&i+j),*((char *)&i+j));
      printf("\n");
    }
    
    return 0;
}

