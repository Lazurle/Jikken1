//BP14003 秋山和哉
//情報実験I 課題２

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/un.h>
#include<arpa/inet.h>

#define SOCK_NAME "./socket"

int main()
{
  struct sockaddr_in saddr;
  int soc;
  char buf[1024];

  if ( ( soc = socket( AF_INET, SOCK_STREAM, 0 ) ) < 0 ) {
    perror( "socket" );
    exit( 1 );
  }

  memset( (char *)&saddr, 0, sizeof( saddr ) );
  saddr.sin_family = AF_INET;
  //strcpy( saddr.sun_path, SOCK_NAME );
  saddr.sin_addr.s_addr = inet_addr("172.29.144.28");
  saddr.sin_port = htons(11188);//1024<= x <=65535

  if ( connect( soc, ( struct sockaddr * )&saddr, ( socklen_t )sizeof( saddr ) ) < 0 ) {
    perror( "connect" );
    exit( 1 );
  }
  fprintf( stderr, "Connection established: socket %d used.\n", soc );

  while( fgets( buf, 1024, stdin ) ) {
    if ( buf[strlen(buf)-1] == '\n' ) buf[strlen(buf)-1] = '\0';
    write( soc, buf, strlen( buf )+1 );
    fsync( soc );
    read( soc, buf, 1024 );
    fprintf( stdout, "%s\n", buf );
  }

  close( soc );

  return 0;
}
