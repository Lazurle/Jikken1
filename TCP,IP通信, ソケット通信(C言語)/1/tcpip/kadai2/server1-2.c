//BP14003 秋山和哉
//情報実験I 課題２

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/un.h>
#include <arpa/inet.h>
#include <netinet/in.h>

#define SOCK_NAME "./socket"


struct Dictionary{
  char english[15];
  char japanese[15];
};
typedef struct Dictionary dictionary;


int main()
{
  int i;
  int fd1, fd2;
  struct sockaddr_in saddr;
  struct sockaddr_in caddr;

  int len;
  int ret;
  char buf[1024];

  FILE *fp;			// File Pointer
  dictionary dic[45];		// Data Construction
  int ext=0;			// Flagment

  // File Open
  if( (fp = fopen("dic-w.txt", "r")) == NULL ){
    perror("File Open Error!\n");
    exit(1);
  }

  // Include Dictionary
  for(i=0 ; i<45 ; i++){
    if(fscanf(fp, "%s %s",
	      dic[i].english, dic[i].japanese) == EOF){
      break;
    }
  }

  if ( ( fd1 = socket( AF_INET, SOCK_STREAM, 0 ) ) < 0 ) {
    perror( "socket" );
    exit( 1 );
  }

  memset( (char *)&saddr, 0, sizeof( saddr ) );
  saddr.sin_family = AF_INET;
  saddr.sin_addr.s_addr = INADDR_ANY;
  saddr.sin_port = htons(44003);
  // strcpy( saddr.sun_path, SOCK_NAME );

  unlink( SOCK_NAME );
  if ( bind( fd1, ( struct sockaddr * )&saddr, ( socklen_t )sizeof( saddr ) ) < 0 ) {
    perror( "bind" );
    exit( 1 );
  }

  if ( listen( fd1, 5 ) < 0 ) {
    perror( "listen" );
    exit( 1 );
  }

  while( 1 ) {
    len = sizeof( caddr );
    if ( ( fd2 = accept( fd1, ( struct sockaddr * )&caddr, ( socklen_t * ) &len ) ) < 0 ) {
      perror( "accept" );
      exit( 1 );
    }
    fprintf( stderr, "Connection established: socket %d used.\n", fd2 );

    while( ( ret = read( fd2, buf, 1024 ) ) > 0 ) {
      ext = 0;
      fprintf( stderr, "read: %s\n", buf );
      for ( i=0; i<45; i++ ){
	if(strcmp(buf, dic[i].english ) == 0){
	  strcpy(buf, dic[i].japanese);
	  ext = 1;			// -> "未登録"
	  break;
	}
      }

      if(ext == 0) strcpy(buf, "未登録");

      fprintf( stderr, "write: %s\n", buf );
      write( fd2, buf, strlen( buf )+1 );
      fsync( fd2 );
    }

    close( fd2 );
  }

  close( fd1 );

  return 0;
}
