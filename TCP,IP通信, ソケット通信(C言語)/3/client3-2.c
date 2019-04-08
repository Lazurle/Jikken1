#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/un.h>
#include <arpa/inet.h>
#include <netinet/in.h>

#define SOCK_NAME "./socket"

int main()
{
  struct sockaddr_in saddr;
  int soc;
  char buf[1024], cmd[1024];			// Buffer, Command
  int portnum=0;				// Port Number

  while(1){
    printf("Select port number? -> ");
    scanf("%d", &portnum);
    if(portnum >= 65536 || portnum <= 9999)
      printf("Input 10000-65535.\n");
    else break;
  }

  if ( ( soc = socket( AF_INET, SOCK_STREAM, 0 ) ) < 0 ) {
    perror( "socket" );
    exit( 1 );
  }

  memset( (char *)&saddr, 0, sizeof( saddr ) );
  saddr.sin_family = AF_INET;
  saddr.sin_addr.s_addr = inet_addr("172.29.144.28");
  saddr.sin_port = htons(portnum);
  // strcpy( saddr.sin_path, SOCK_NAME );

  if ( connect( soc, ( struct sockaddr * )&saddr, ( socklen_t )sizeof( saddr ) ) < 0 ) {
    perror( "connect" );
    exit( 1 );
  }
  fprintf( stderr, "Connection established: socket %d used.\n", soc );
  
  ///////////////////////////////////////////////////
  printf("Prog: Send \"USERID bp14068\"\n");
  ///////////////////////////////////////////////////
  
  close( soc );
  return 0;
}
