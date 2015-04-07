#include <sys/wait.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char **argv)
{
	int status;
	int count = atoi(argv[1]);
	char *command = argv[2];
//	printf("%s %d",command, count);
	char** childArgs = (char *[]){command, argv[3],NULL};
	for (int i=0;i<count;i++){
		int x = vfork();
		if (x==-1){
//			printf("Error\n");
		}
		else if (x==0){
			execv(command, childArgs);
			_exit(1);
		}
		else {
			waitpid(x, &status,0);
		}
	}
}
