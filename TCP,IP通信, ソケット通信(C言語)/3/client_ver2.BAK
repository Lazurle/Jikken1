#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/un.h>
#include <arpa/inet.h>

#define SOCK_NAME "./socket"
//#define PORT_NUM 55629

int main(int argc,char* argv[])
{
  struct sockaddr_in saddr;
  int soc;
  char buf[1024];
  int state=0;
  int n;
  char proto[5];
  int i;
  if ( ( soc = socket( AF_INET, SOCK_STREAM, 0 ) ) < 0 ) {
    perror( "socket" );
    exit( 1 );
  }

  memset( (char *)&saddr, 0, sizeof( saddr ) );
  saddr.sin_family = AF_INET;
  saddr.sin_addr.s_addr=inet_addr("172.29.144.29");
  n=atoi(argv[1]);
  saddr.sin_port=htons(n);
  //printf("%d\n",n);
  if ( connect( soc, ( struct sockaddr * )&saddr, ( socklen_t )sizeof( saddr ) ) < 0 ) {
    perror( "connect" );
    exit( 1 );
  }
  fprintf( stderr, "Connection established: socket %d used.\n", soc );
  fsync( soc );
  read( soc, buf, 1024 );
  fprintf( stdout, "%s\n", buf );
  while( 1 ) {
    if(state==0){
      strcpy(buf,"CLEDEPASSE zaKTOvyw");
    }
    else if(state>=1)fgets( buf, 1024, stdin );
    
    //else if(state==1)strcpy(buf,"OK");
    //fgets( buf, 1024, stdin );
    if ( buf[strlen(buf)-1] == '\n' ) buf[strlen(buf)-1] = '\0';
    write( soc, buf, strlen( buf )+1 );
    fsync( soc );
    read( soc, buf, 1024 );
    fprintf( stdout, "%s\n", buf );
    if(state==0){
    fsync( soc );
    read( soc, buf, 1024 );
    fprintf( stdout, "%s\n", buf );

    strcpy(proto,&buf[18]);
    //printf("%s\n",proto);
    //fsync( soc );
    //read( soc, buf, 1024 );
    //fprintf( stdout, "%s\n", buf );
    }
    strcpy(buf,"OK");
    write( soc, buf, strlen( buf )+1 );
    for(i=0;i<6;i++){
      if(proto[i]=='0'){
	printf("P0 start!!\n");
	read( soc, buf, 1024 );
	fprintf( stdout, "%s\n", buf );

	sprintf(buf,"REPONSE %d",strtol(buf,NULL,8));
	
	write( soc, buf, strlen( buf )+1 );
	read( soc, buf, 1024 );
	fprintf( stdout, "%s\n", buf );
	write(soc,"OK",3);
	usleep(1000);
      }
      if(proto[i]=='1'){
	printf("P1 start!!\n");
	read( soc, buf, 1024 );
	fprintf( stdout, "%s\n", buf );
	int num=atoi(buf);
	num=2*num*num*num-num*num+7*num-2;
	sprintf(buf,"REPONSE %d",num);
	write( soc, buf, strlen( buf )+1 );
	read( soc, buf, 1024 );
	fprintf( stdout, "%s\n", buf );
	write(soc,"OK",3);
	write( soc, buf, strlen( buf )+1 );
	write(soc,"OK",3);
	usleep(1000);
      }
      if(proto[i]=='2'){
	printf("P2 start!!\n");
	strcpy(buf,"COMMENCER PROTO_2");
	write( soc, buf, strlen( buf )+1 );
	read( soc, buf, 1024 );
	int x=atoi(buf);
	fprintf( stdout, "%s\n", buf );
	read( soc, buf, 1024 );
	int y=atoi(buf);
	fprintf( stdout, "%s\n", buf );
	sprintf(buf,"REPONSE %d",2*x*x-y*y);
	write( soc, buf, strlen( buf )+1 );
	read( soc, buf, 1024 );
	fprintf( stdout, "%s\n", buf );
	write(soc,"OK",3);
      }
      if(proto[i]=='3'){
	printf("P3 start!!\n");
	strcpy(buf,"BONSOIR, CA VA BIEN?");
	write( soc, buf, strlen( buf )+1 );
	usleep(1000);
	strcpy(buf,"COMMENCER PROTO_3");
	write( soc, buf, strlen( buf )+1 );
	usleep(1000);
	fsync( soc );
	read( soc, buf, 1024 );
	fprintf( stdout, "%s\n", buf );
	int x=atoi(buf);
	read( soc, buf, 1024 );
	fprintf( stdout, "%s\n", buf );
	int y=atoi(buf);
	if(4*x<7*y)sprintf(buf,"REPONSE %d",7*y);
	else sprintf(buf,"REPONSE %d",4*x);
	write( soc, buf, strlen( buf )+1 );
      }
    }
    //if(state==8)break;
    state++;
  }

  close( soc );

  return 0;
}
