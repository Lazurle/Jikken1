//BP14003 秋山和哉
//課題２

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

  if ( ( soc = socket( AF_INET, SOCK_STREAM, 0 ) ) < 0 ) {
    perror( "socket" );
    exit( 1 );
  }

  memset( (char *)&saddr, 0, sizeof( saddr ) );
  saddr.sin_family = AF_INET;
  saddr.sin_addr.s_addr = inet_addr("172.29.144.26");
  saddr.sin_port = htons(8888);
  // strcpy( saddr.sin_path, SOCK_NAME );

  if ( connect( soc, ( struct sockaddr * )&saddr, ( socklen_t )sizeof( saddr ) ) < 0 ) {
    perror( "connect" );
    exit( 1 );
  }
  fprintf( stderr, "Connection established: socket %d used.\n", soc );

  fprintf( stderr, "\n◆◆ Program started ◆◆\n\n QUIT: Quit, STAT: Status.\n(Enter into ANSR,after logging in.)\n\n" );

  ////////// Phase0, 1: LogIn
  fprintf( stderr, "[LogIn]\n" );
  while(1){
    // Phase.1: User
    fprintf( stderr, "USER " );
    while( fgets( buf, 1024, stdin ) ){
      if ( buf[strlen(buf)-1] == '\n' ) buf[strlen(buf)-1] = '\0';

      if( strcmp( buf, "QUIT" ) == 0  || strcmp( buf, "STAT" ) == 0 )
	// Quit, Status
	write( soc, buf, strlen( buf )+1 );
      else{
	strcpy(cmd, buf);			       	// Buf -> Cmd
	sprintf(buf, "USER %s", cmd);			// Cmd+ -> Buf
	write( soc, buf, strlen( buf )+1 );
      }

      fsync( soc );
      read( soc, cmd, 1024 );
      fprintf( stdout, "Response: %s\n", cmd );

      if( strcmp(cmd, "OK")==0 ){			// Go on to the next phase -> PASS
	printf("Permittion.\n");
	break;
      }
      else if( strcmp(cmd, "NG")==0 || strcmp(cmd, "ERROR")==0 )
	printf("Try again. User?-> ");
      else if( strcmp(cmd, "GOOD BYE")==0 ){
	printf("Good Bye.\n");
	exit(1);
      }
      else
	// STAT
	printf("User? -> ");
    }

    // Phase.1: Password
    fprintf( stderr, "PASS " );
    fgets( buf, 1024, stdin );
    if ( buf[strlen(buf)-1] == '\n' ) buf[strlen(buf)-1] = '\0';

    if( strcmp( buf, "QUIT" ) == 0  || strcmp( buf, "STAT" ) == 0 )
      // Quit, Status
      write( soc, buf, strlen( buf )+1 );
    else{
      strcpy(cmd, buf);					// Buf -> Cmd
      sprintf(buf, "PASS %s", cmd);			// Cmd+ -> Buf
      write( soc, buf, strlen( buf )+1 );
    }

    fsync( soc );
    read( soc, cmd, 1024 );
    fprintf( stdout, "Response: %s\n", cmd );

    if( strcmp(cmd, "OK")==0 ){				// Go on to the next phase -> QUIZ
      printf("Permittion. Starting Quiz!...\n\n");
      break;
    }
    else if( strcmp(cmd, "NG")==0 || strcmp(cmd, "ERROR")==0 );
    else if( strcmp(cmd, "GOOD BYE")==0 ){
      printf("Good Bye.\n");
      exit(1);
    }
    else
      // STAT
      printf("USER ");
  }

  ////////// Phase.2, 3: QUIZ
  int crct=0;				       		// Correct
  fprintf( stderr, "[Quiz]\n" );

  while(1){
    if(crct == 5){
      fprintf( stdout, "Completed.\n\n" );
      break;
    }

    // Phase.2: QUIZ
    sprintf(buf, "QUIZ %d", crct);
    write( soc, buf, strlen( buf )+1 );

    fsync( soc );
    read( soc, cmd, 1024 );
    fprintf( stdout, "Correct: %d.\n", crct );
    fprintf( stdout, "QUIZ %s\nANSR ", cmd );

    // Phase.3: ANSR
    fgets( buf, 1024, stdin );
    if ( buf[strlen(buf)-1] == '\n' ) buf[strlen(buf)-1] = '\0';

    if( strcmp( buf, "QUIT" ) == 0  || strcmp( buf, "STAT" ) == 0 )
      // Quit, Status
      write( soc, buf, strlen( buf )+1 );
    else{
      strcpy(cmd, buf);					// Buf -> Cmd
      sprintf(buf, "ANSR %s", cmd);			// Cmd+ -> Buf
      write( soc, buf, strlen( buf )+1 );
    }

    fsync( soc );
    read( soc, cmd, 1024 );
    fprintf( stdout, "Response: %s\n", cmd );

    if( strcmp(cmd, "OK")==0 ) crct++;
    else if( strcmp(cmd, "NG")==0 || strcmp(cmd, "ERROR")==0 );
    else if( strcmp(cmd, "GOOD BYE")==0 ){
      printf("Good Bye.\n");
      exit(1);
    }
    else printf("Go on next quiz.\n");			// STAT
  }

  ////////// Phase.03: MESSAGE
  fprintf( stderr, "[Message]\n" );
  fprintf( stderr, "If you want hidden message, input \"GET MESSAGE\".\n" );

  while(1){
    printf("Command? -> ");

    fgets( buf, 1024, stdin );
    if ( buf[strlen(buf)-1] == '\n' ) buf[strlen(buf)-1] = '\0';
    write( soc, buf, strlen( buf )+1 );

    fsync( soc );
    read( soc, cmd, 1024 );

    if( strcmp(cmd, "GOOD BYE")==0 ){
      printf("Good Bye.\n");
      exit(1);
    }
    else if( strcmp(cmd, "NG")==0 || strcmp(cmd, "ERROR")==0 );
    else fprintf( stdout, "Response: %s\n", cmd );
  }

  close( soc );
  return 0;
}
