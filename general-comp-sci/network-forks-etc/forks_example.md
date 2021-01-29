# Forks Example

**Below is a sample program of how a fork process works in C**

``` c
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

/* Small test program for learning fork, parent-child processes */

int main(){
	/*pid is similar to an int, except that it used mainly
	for capturing and returning values */
	pid_t childPID = fork();

	if (childPID < 0){
		perror("fork() error");
		exit(-1);
		//exit(-1) tells user that something catastrophic has happened
		//exit(0) tells user that everything ran normally
	}

	if (childPID != 0){
		/* If child's PID is not 0, then that we are actually inside
		the parent process */
		printf("We are in the parent process: %d, this is the child process: %d", getpid(), childPID);
		wait(NULL);
		/* In order to save resources, we tell the parent process to wait for the child process to catch up and join */
	}
	else{
		printf("We are in the child process: %d, this is the parent process: %d \n", getpid(), getppid());
		execl("/bin/echo", "echo", "Hello", "World", NULL);
	}

	return 0;

}
```

**Explanation**

- The fork command is generally a way of taking your currently running process and
  spawning a new process. 
  - Parent process
    - Child Process
- Linux has a unique way of doing this: by duplicating the
  current process. 

- For fork commands, we include

``` c
#include <sys/types.h>
#include <unistd.h>
```

- For obtaining process id, we use:
  - getpid(), usually we cast as (int) when called

- For creating a new process, we use
  - fork(), usually casted as pid_t

- Child processes occur when fork() returns 0. 
  - Positive return values from fork means that current process is the parent. 
  - Negative return values mean an error in creating the fork processes. This usually occurs when we are making numerous
    forks and go past the limit.

- When running child processes, we usually make the parent process wait until
  all child processes have ended. This can be done by adding a wait(NULL) to the
  parent branch. wait(NULL) tells the parent process to wait until its child
  processes have finished.

- For wait(NULL), we include

``` c
#include <sys/wait.h>
```