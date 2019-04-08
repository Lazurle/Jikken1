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
  // struct timeval tv;
  int soc;					// Socket
  int u=10000;					// Port Number 

  struct timeval tv;
  tv.tv_sec = 3;
  tv.tv_usec = 0;

  printf("Searching...\n");
  while(1){
  memset( (char *)&saddr, 0, sizeof( saddr ) );
  saddr.sin_family = AF_INET;
  saddr.sin_addr.s_addr = inet_addr("172.29.144.28");
  
  for(u=10000 ; u<65536 ; u++){
    if ( ( soc = socket( AF_INET, SOCK_STREAM, 0 ) ) < 0 ) {
      perror( "socket" );
      exit(1);
    }
    
    saddr.sin_port = htons(u);
    if ( connect( soc, ( struct sockaddr * )&saddr, 
		  ( socklen_t )sizeof( saddr ) ) < 0 ){
      u++;
      close(soc);
    }
    else{
      fprintf( stderr, "Connection established: socket %d used.\n", soc );
      printf("Available port found: No.%d\n", u);
      break;
    }
  }
  
  if(u == 65535) break;
  }

  close(soc);
  return 0;
}
