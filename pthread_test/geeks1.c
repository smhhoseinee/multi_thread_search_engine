#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>  //Header file for sleep(). man 3 sleep for details.
#include <pthread.h>
  
// A normal C function that is executed as a thread 
// when its name is specified in pthread_create()
void *myThreadFun(void *vargp)
{
    sleep(1);
    printf("Printing GeeksQuiz from Thread \n");
    return NULL;
}

void *scan_port(char *server_addr_str,int server_port,int sock){



	return 0;
}

void *thread_send_ping(void *_args){
//(int ping_sockfd, struct sockaddr_in *ping_addr,char *ping_dom, char *ping_ip, char *rev_host)


	struct  thread_send_ping_args *args = (struct thread_send_ping_args *) _args;

	int ping_sockfd = args->ping_sockfd;
	struct sockaddr_in *ping_addr = args->ping_addr;
	char *ping_dom = args->ping_dom;
       	char *ping_ip = args->ping_ip;
	char *rev_host = args->rev_host;

	send_ping(ping_sockfd, &*ping_addr, ping_dom, ping_ip, rev_host);

	return NULL;
}

   
int main()
{
    pthread_t thread_id;
    printf("Before Thread\n");
    pthread_create(&thread_id, NULL, myThreadFun, NULL);
//    pthread_join(thread_id, NULL);
			pthread_create(&thread_id[threads_counter], NULL, thread_send_ping, args);

    printf("After Thread\n");
    exit(0);
}
