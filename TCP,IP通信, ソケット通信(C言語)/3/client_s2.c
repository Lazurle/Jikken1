#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/un.h>
#include <arpa/inet.h>
#include "rmd160.h"

#define SOCK_NAME "./socket"
#define PORT_NUM 39663
#define RMDsize 160

extern byte *RMD(byte *);

int main()
{
  struct sockaddr_in saddr;
  int soc;
  char buf[1024];
  int state=0;
  char key[200];
  byte *hashcode;
  char output[RMDsize/4+1];
  int i;
  int check =0;
  if ( ( soc = socket( AF_INET, SOCK_STREAM, 0 ) ) < 0 ) {
    perror( "socket" );
    exit( 1 );
  }

  memset( (char *)&saddr, 0, sizeof( saddr ) );
  saddr.sin_family = AF_INET;
  saddr.sin_addr.s_addr=inet_addr("172.29.144.28");
  saddr.sin_port=htons(PORT_NUM);

  if ( connect( soc, ( struct sockaddr * )&saddr, ( socklen_t )sizeof( saddr ) ) < 0 ) {
    perror( "connect" );
    exit( 1 );
  }
  fprintf( stderr, "Connection established: socket %d used.\n", soc );

  while( 1 ) {
    if(state==0)strcpy(buf,"USRID bp16052");
    else if(state>=1){
      usleep(1000);
      fgets(buf,1024,stdin);
      //strcpy(buf,key);
    }
    if ( buf[strlen(buf)-1] == '\n' ) buf[strlen(buf)-1] = '\0';
    write( soc, buf, strlen( buf )+1 );
    usleep(1000);
    fsync( soc );
    read( soc, buf, 1024 );
    usleep(1000);
    fprintf( stdout, "%s\n", buf );

    if(state==0){
      fsync( soc );
      read(soc , buf ,1024);            //key
      fprintf( stdout, "%s\n", buf );

      strcpy(key,&buf[10]);

      //printf("%s\n",key);
      fsync( soc );
      usleep(1000);
      read( soc, buf, 1024 );          //パスワードのヒント
      usleep(1000);
      fprintf( stdout, "%s\n", buf );
      usleep(1000);
      //read(soc , buf ,1024);
      //fprintf( stdout, "%s\n", buf );
      //usleep(1000);

      //write( soc, key, strlen( key )+1 );
      //usleep(1000);
      //fsync( soc );
      //read(soc , buf ,1024);
      //usleep(1000);
      //fprintf( stdout, "%s\n", buf );

      hashcode=RMD((byte *)key);

      for (i=0; i<RMDsize/8; i++)
	sprintf(output+2*i, "%02x", hashcode[i]);

      printf("hashcode: %s\n", output);
      strcpy(key,"RIPEMD160 ");
      strcat(key,output);
      //strcat(key,"miss");
      printf("%s\n", key);
      usleep(1000);
      write(soc,key,strlen(key)+1);

      usleep(1000);
      fsync( soc );
      printf("OK? %s\n",buf);
      //check=read(soc , buf ,1024);
      printf("%d \n",check);
      //if(check==-1)printf("残念!!\n");
      printf("OK!!\n");
      usleep(1000);
    buf[0]='\0';
      fprintf( stdout, "%s\n", buf );
    }
    buf[0]='\0';
    if(state==5)break;
    state++;
  }

  close( soc );

  return 0;
}
