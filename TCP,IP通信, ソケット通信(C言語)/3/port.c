
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/un.h>
#include <arpa/inet.h>
#include <netinet/in.h>

#define SOCK_NAME "./socket"

	      /************************************************/

int main()
{
  struct sockaddr_in saddr;
  int soc;
  char buf[1024], cmd[1024];		// Buffer, Command

  int u=10000;				// Port Number
  int u2=10000;
  int i=0, j=15;			// PWD1 Counter
  char user[99] = "USERID bp14003";	// USERID

  char pwd2[99];			// Password No.2
  char pwd2_send[99];			// Sending Password No.2

  // char code[99];		       	// CODE
  char code_send[99];			// Sending CODE
  char phase[99];			// Protocol[0-3]

  int x=0, y=0;				// x, y
  int send=0;				// Sending x

  struct timeval tv;			// Time Out
  tv.tv_sec = 3;
  tv.tv_usec = 0;

  memset( ( char* )&saddr, 0, sizeof( saddr ) );
  saddr.sin_family = AF_INET;
  saddr.sin_addr.s_addr = inet_addr( "172.29.144.28" );

  /////////////////////// Phase.01 ///////////////////////
  printf( "[Phase.01 ---------> Searching Available Port]\n" );

  u=10000;
  while(1){
    setsockopt( soc, SOL_SOCKET, SO_RCVTIMEO, (char*)&tv, sizeof(tv) );
    if( u==65535 ) break;

    if ( ( soc = socket( AF_INET, SOCK_STREAM, 0 ) ) < 0 ) {
      perror( "socket" );
      exit(1);
    }

    saddr.sin_port = htons(u);
    if ( connect( soc, ( struct sockaddr * )&saddr, ( socklen_t )sizeof( saddr ) ) < 0 ) {
      close( soc );
      u++;
    }
    else{
      printf( "Available port Found: No.%d -----\n", u );
      fprintf( stderr, "Connection Established: socket %d used.\n", soc );

      /////////////////////// Phase.02 ///////////////////////
      write( soc, user, 1024 );
      fsync( soc );
      if( read( soc, buf, 1024 ) < 0 ||
	  strcmp( buf, "INFOEXP1-MIYOSHI-1803_1_Ver11.03") != 0 ){
	close( soc );
	u++;
      }

      else if( strcmp(buf, "INFOEXP1-MIYOSHI-1803_1_Ver11.03") == 0 ){
	read( soc, cmd, 1024 );
	printf( "\n" );
}
}
}
  return 0;
}
