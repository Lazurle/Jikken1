#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/un.h>
#include<arpa/inet.h>

#define SOCK_NAME "./socket"

int main()
{
    int i;
    int fd1, fd2;
    struct sockaddr_in saddr;
    struct sockaddr_in caddr;

    int len;
    int ret;
    char buf[1024];

    if ( ( fd1 = socket( AF_INET, SOCK_STREAM, 0 ) ) < 0 ) {
        perror( "socket" );
        exit( 1 );
    }

    memset( (char *)&saddr, 0, sizeof( saddr ) );
    saddr.sin_family = AF_UNIX;
    //  strcpy( saddr.sun_path, SOCK_NAME );
    saddr.sin_addr.s_addr = INADDR_ANY;
    saddr.sin_port = htons(44003);//1024<= x <=65535

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
            fprintf( stderr, "read: %s\n", buf );
            for ( i=0; i<ret; i++ )
            if(isalpha (buf[i] )){
                if(97 <= buf[i] && buf[i] <= 122)
                buf[i] = toupper( buf[i] );
                else if(65 <= buf[i] && buf[i] <= 90)
                buf[i] = tolower(buf[i]);
            }

            fprintf( stderr, "write: %s\n", buf );
            write( fd2, buf, strlen( buf )+1 );
            fsync( fd2 );
        }

        close( fd2 );
    }

    close( fd1 );

    return 0;
}
