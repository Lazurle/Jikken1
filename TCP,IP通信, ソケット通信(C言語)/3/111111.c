
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/socket.h>
#include <arpa/inet.h>//
#include <math.h>

#define SOCK_NAME "./socket"
//1 c q
int main()
{
  struct sockaddr_in saddr;//
  struct timeval tv;
  int soc;
  int port = 10000;//検索
  int port_num1;

  char buf[1024];

  /////////////////////////////////////////////////////////////////////
  //ポート番号探索
  if ( ( soc = socket( AF_INET, SOCK_STREAM, 0 ) ) < 0 ) {
    perror( "socket" );
    exit( 1 );
  }
   memset( (char *)&saddr, 0, sizeof( saddr ) );
  saddr.sin_family = AF_INET;
  //サーバー１
  saddr.sin_addr.s_addr = inet_addr("172.29.144.28");
  tv.tv_sec = 1;
  tv.tv_usec = 0;
  setsockopt(soc, SOL_SOCKET, SO_RCVTIMEO, (char *)&tv, sizeof(tv));
  while(port<65536){
    saddr.sin_port = htons(port);
    if ( connect( soc, ( struct sockaddr * )&saddr, ( socklen_t )sizeof( saddr)) >= 0 ) {
      fprintf(stdout,"connect = %d\n",port);
      read(soc,buf,1024);
      if(strcmp(buf,"INFOEXP1-MIYOSHI-1803_1_Ver11.03") ==0 ){
		 fprintf( stdout, "%s\n", buf );
	port_num1 = port;
	break;
      }
      close(soc);
      //ソケット再接続
      if ( ( soc = socket( AF_INET, SOCK_STREAM, 0 ) ) < 0 ) {
        perror( "socket" );
        exit( 1 );
      }
      memset( (char *)&saddr, 0, sizeof( saddr ) );
      saddr.sin_family = AF_INET;
      setsockopt(soc, SOL_SOCKET, SO_RCVTIMEO, (char *)&tv, sizeof(tv));
      saddr.sin_addr.s_addr = inet_addr("172.29.144.28");
      saddr.sin_port = htons(port);
    }
    port++;
  }
  port = 10000;

  /////////////////////////////////////////////////////////////////////

  if ( ( soc = socket( AF_INET, SOCK_STREAM, 0 ) ) < 0 ) {
    perror( "socket" );
    exit( 1 );
  }

  memset( (char *)&saddr, 0, sizeof( saddr ) );
  saddr.sin_family = AF_INET;//
  saddr.sin_addr.s_addr = inet_addr("172.29.144.28");//

  saddr.sin_port = htons(port_num1);//
  if ( connect( soc, ( struct sockaddr * )&saddr, ( socklen_t )sizeof( saddr ) ) >= 0 ) {
    fprintf(stdout,"connect = %d\n",port_num1);
    read(soc,buf,1024);
    fprintf( stdout, "%s\n", buf );
    write( soc, "USRID bp14003", strlen( "USRID bp14003" )+1 );
    fsync( soc );
    read( soc, buf, 1024 );
    fprintf( stdout, "%s\n", buf );
    //read( soc, buf, 1024 );
    //fprintf( stdout, "%s\n", buf );
}


    close(soc);

    return 0;
}
