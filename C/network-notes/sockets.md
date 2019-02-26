System Calls
	
	The system calls in this guide will allow you to access the network
	functionality of a Unix box, or any box that supports the sockets API.
	By using these functions, the kernel takes over and does all the work.

getaddrinfo()

	getaddrinfo() does DNS and service name lookups like so:

	#include <sys/types.h>
	#include <sys/socket.h>
	#include <netdb.h>

	int getaddrinfo(const char *node,     // e.g. "www.example.com" or IP
			const char *service,  // e.g. "http" or port number
			const struct addrinfo *hints,
        		struct addrinfo **res); // Should be returning this?
			
	Once given the parameters it returns a pointer to a linked list of
	'res' results.

	*node is host name to connect to (usually IP address)
	*service is typically a port number or name of service ("http")
	*hints is already filled out with relevant information

	* Structures *

	For server who wants to listen on host's IP address with port 3490 (set up only)

	int status;
	
	struct addrinfo hints;
	struct addrinfo *servinfo;  // will point to the results

	memset(&hints, 0, sizeof hints); // make sure the struct is empty
	
	hints.ai_family   = AF_UNSPEC;      // don't care IPv4 or IPv6
	hints.ai_socktype = SOCK_STREAM;    // TCP stream sockets
	hints.ai_flags    = AI_PASSIVE;     // fill in my IP for me

	if ((status = getaddrinfo(NULL, "3490", &hints, &servinfo)) != 0) {
		fprintf(stderr, "getaddrinfo error: %s\n", gai_strerror(status));
		exit(1);
	}

	// servinfo now points to a linked list of 1 or more struct addrinfos

	// ... do everything until you don't need servinfo anymore ....

	freeaddrinfo(servinfo); // free the linked-list

	Should note that *servinfo will point to a linked list of struct addrinfos
	each of which contain a struct sockaddr that can be used later.

	For client who wants to connect to www.example.net with port 3490 (set up)

	int status;
	
	struct addrinfo hints;
	struct addrinfo *servinfo;  // will point to the results

	memset(&hints, 0, sizeof hints); // make sure the struct is empty
	
	hints.ai_family   = AF_UNSPEC;     // don't care IPv4 or IPv6
	hints.ai_socktype = SOCK_STREAM; // TCP stream sockets

	// get ready to connect
	status = getaddrinfo("www.example.net", "3490", &hints, &servinfo);

	// servinfo now points to a linked list of 1 or more struct addrinfos

	freeaddrinfo(servinfo); // free the linked-list

socket()

	#include <sys/types.h>
	#include <sys/socket.h>

	int socket(int domain, int type, int protocol);

	The parameters in socket() allow you to say what kind of socket you
	want (IPv4, IPv6, stream or datagram, TCP or UDP)

	The values can hardcoded if needed:

	domain = PF_INET or PF_INET6

	type = SOCK_STREAM or SOCK_DGRAM
	protocol = "tcp" or "udp"

	AF also stands for Address Family (for clarification)

	Instead of hardcoding we use getaddrinfo() as shown:

	/* Will tell socket to return a socket descriptor that you can use for 
	later system calls, or -1 on error. The global variable errno is set 
	to the error's value. */

	int s;
	struct addrinfo hints, *res;

	// do the lookup
	getaddrinfo("www.example.net", "http", &hints, &res);

	// Should also do error-checking on getaddrinfo()

	s = socket(res->ai_family, res->ai_socktype, res->ai_protocol);

	By itself, this socket is no good by itself, so we call other system calls.

bind()

	Once we have the socket, we have to associate that socket with a port on 
	your local machine. This is done like so:

	#include <sys/types.h>
	#include <sys/socket.h>

	int bind(int sockfd, struct sockaddr *my_addr, int addrlen);

	sockfd is the socket file descriptor returned by socket()
	myaddr is a pointer to a struct sockaddr that contains information about your
	address like port and IP address
	addrlen is the length in bytes of that address

	Using the socket() code sample above, we add the following line:

	// binding it to the port we passed in to getaddrinfo()
	bind(sockfd, res->ai_addr, res->ai_addrlen);

	bind() returns -1 on error, 0 otherwise so remember to error check in source code

	Sometimes we rerun the server and bind() fails and returns "Address already in use"
	In order to reuse the port, we implement the following:

	if (setsockopt(listener, SOL_SOCKET, SO_REUSEADDR, &yes, sizeof yes) == -1) {
		perror("setsockopt");
		exit(1);
	}

connect()

	Connecting to a remote host using connect()

	#include <sys/types.h>
	#include <sys/socket.h>

	int connect(int sockfd, struct sockaddr *serv_addr, int addrlen);

	sockfd is the socket file descriptor returned by the socket() call
	serv_addr is a struct sockaddr containing the port and IP address
	addrlen is the byte length of the server address structure

	Making a socket connection to example.com on port 3490:

	struct addrinfo hints, *res;
	int sockfd;

	// first, load up address structs with getaddrinfo():

	memset(&hints, 0, sizeof hints);
	hints.ai_family = AF_UNSPEC;
	hints.ai_socktype = SOCK_STREAM;

	getaddrinfo("www.example.com", "3490", &hints, &res);

	// make a socket:

	sockfd = socket(res->ai_family, res->ai_socktype, res->ai_protocol);

	// connect!

	connect(sockfd, res->ai_addr, res->ai_addrlen);

	Notice how we didnt use bind() since we didnt care about our own local
	port number.

listen()

	Waiting for connections and handling them in two steps, listen() and accept()

	int listen(int sockfd, int backlog);

	Before we call listen, however, we call bind so that the server is running
	on a specific port. So for listening to incoming connections, the following
	sequence of system calls are required:

	getaddrinfo();
	socket();
	bind();
	listen();
	accept();

accept()

	When a person is trying to connect() to your machine on a port that you
	are listen()ing on, their connection will be queued up waiting to be 
	accept()ed. Once you call accept on the pending connection, it will return
	a new socket file descriptor for use on the connection.

	While the original socket file descriptor is still listening for new
	connections, the new one is used for send() and recv() calls.

	As follows:

	#include <sys/types.h>
	#include <sys/socket.h>

	int accept(int sockfd, struct sockaddr *addr, socklen_t *addrlen);

	sockfd is the listen()ing socket descriptor
	addr is the pointer to a local sockaddr_storage
	addrlen is the int variable that should be set to 
	sizeof(struct sockaddr_storage) before  being passed to accept()

	accept() returns -1 and sets errno if an error occurs (just like all other calls)

Sample code fragment:

	#include <sys/socket.h>
	#include <netinet/in.h>

	#define MYPORT "3490" // Port we are connecting to
	#define BACKLOG 10    // How many connections we will queue at max

	int main(void){

		struct sockaddr_storage their_addr;
		socklen_t addr_size;
		struct addrinfo hints, *res;
		int sockfd, new_fd;

		// first, load up address structs with getaddrinfo():

		memset(&hints, 0, sizeof hints);
		hints.ai_family = AF_UNSPEC;  // use IPv4 or IPv6, whichever
		hints.ai_socktype = SOCK_STREAM;
		hints.ai_flags = AI_PASSIVE;     // fill in my IP for me

		getaddrinfo(NULL, MYPORT, &hints, &res);

	        // make a socket, bind it, and listen on it:
	
		sockfd = socket(res->ai_family, res->ai_socktype, res->ai_protocol);
		bind(sockfd, res->ai_addr, res->ai_addrlen);
		listen(sockfd, BACKLOG);

		// now accept an incoming connection:

		addr_size = sizeof their_addr;
		new_fd = accept(sockfd, (struct sockaddr *)&their_addr, &addr_size);

		// ready to communicate on socket descriptor new_fd!

		-
		-
	}

send() and recv()

	send() call over stream sockets or connected datagram sockets:

	int send(int sockfd, const void *msg, int len, int flags);

	sockfd is the socket descriptor you want to send data to (from either
	socket() or accept() 
	msg is the pointer to the data you want to send
	len is the length of that data in bytes
	flags is usually set to 0, see send() man for more details

	Using send():

	char *msg = "Bee was here!";
	int len, bytes_sent;

	(insert all set up code here)

	len = strlen(msg);
	bytes_sent = sned(sockfd, msg, len, 0);

	Not every send() call will return the full number of bytes it sent out,
	sometimes it can't handle sending a massive chunk of data so it may
	return less than what it was told to send. It is up to you to send
	the rest of the data that did not go through. 

	send() returns -1 and sets errno on error.

	recv() call is similar:

	int recv(int sockfd, void *buf, int len, int flags);

	recv() returns the number of bytes actually read into the buffer or -1
	on error. recv() can also return 0 which means that the remote side closed
	its connection to you.

close() and shutdown()

	After send()ing and recv()ing data, you close the connection like so:

	close(sockfd);

	The shutdown() function will cut off communication in a certain direction:
	
	int shutdown(int sockfd, int how);

	how can be the following:

	0 for further receives are disallowed
	1 for further sends are disallowed
	2 all sends and receives are disallowed (like close())


Taken from Advanced Programming

What is a socket?

It is an endpoint for interprocess communication over TCP/IP networks

Listening sockets vs Connected sockets

HTTP 1.0

The client receives a HTTP request for  a resource on the server


CODE:

For sockets, we must create a reliable, stream socket using TCP

if ( sock = socket(PF_INET, SOCK_STREAM, IPROTO_TCP < 0 )
	die("socket() failed")

In addition:

We construct the server address structure

memset( &echoServAddr, 0, sizeof(echServeAddr)); /*Zero out structure */
echoServAddre.sin_family = AF_INET; 		 /*Internet address family */

echoServAddr.sin_addr.s_addr = inet_addriservIP; /*Server IP address */
echoServAddr.sin_port        = htons(echoServPort); /*Server port */

Establish the connection to the echo server
if ( connect(sock, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr) ) < 0 )
	die("conenct failed")

Determine input length
echoStringLen = strlen(echoString);

Send the string back to the server
if ( send(sock, echoString, echoStringLen, 0) != echoStringLen )
	die("send() sent wrong number of btes than expected")

Receive the same string back from the server
totalBytesRcvd = 0;
printf("Received: ");
while ( totalBytesRcvd < echoStringLen ){
	if (bytesRcvd = recv(sock, echoBuffer, REVBUFSIZE = 1, 0 ) <= 0)
		die(recv() failed or connection closed premateurel);
	
	totalBytesRcvd += bytesRcvd;
	echoBuffer[bytesRcvd] = '\0'
	printf("%s", echoBuffer);
}
printf("\n"); Receive up to the buffer size (minus 1 to leave space for a null terminator)

close(sock);

If we were to build a local address structure to connect to a client, we need bind(), listen() abd clntSock()

Bind to local address
if ( bind(servSock, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr)) < 0)
	die("bind failed")

Mark the socket so it will listen for incoming connections
if ( listen(ServSock, MAXPENDING) <0)
	die("listen() failed);

for (; ;){

	Set the size of the in-out parameter
	clntLen = sizeof(echoClntAddr);

	Wait for a client to connect
	if ( clntSock = accept(servSock, (struct sockaddr *) &echoClntAddr, &clntLen)) < 0)
		die("accept failed");
	
	clntSock is connected to a client!

	printf("Handling client %s \n", 

Quick Notes:

Sockets API Summary

  CLIENT				SERVER			int socket(domain, type, protocol)
  									
  socket				socket			bind(server_sock, &server_addr, server_len)
	|					  |	
	|					 bind			listen(server_sock, backlog)
    |					  |
  	|					listen			accept(server_sock, &client_addr, &client_len)
	|					  |
 connect--------------->accept<--.		connect(client_sock, &server_addr, server_len)
   	|					  | 	 |
.>send------------------>recv<.	 |:
|	|					  |	  |	 |
'-recv<------------------send-'	 |
	|					  |		 |
  close----EOF---------=>read	 |
						  |		 |
						 close---'

socket(domain, type, protocol)
- Called by both client and server
- The server uses socket() to create a listening socket.
  This listening socket it later used to create a connected socket
  using accept().

bind(server_sock, &server_addr, server_len)
- Called by server only
- Binds a listening socket to a specific port number that the client
  uses to connect.

listen(server_sock, backlog)
- Called by server only
- Sets up with listening socket to accept connections

accept(server_sock, &client_addr, &client_len)
- Called by server only
- Creates and returns a brand new connected socket for each successive client

connect(client_sock, &server_addr, server_len)
- Called by client only
- Creates a connection between client and server, data may be sent back and forth

send() recv()
- Called by client and server
- Reads and writes to the other side
- Message boundaries may not always be preserved


What is a socket?

	A socket is a way to speak to other programs using standard Unix
	file descriptors. 

	Whenever Unix programs do any sort of I/O, they do it by reading 
	to a file descriptor.

	A file descriptor is an integer associated with an open file.
	This open file can also be a network connection, a FIFO, a pipe,
	a terminal, a real on-the-disk file or anything else.

	Don't forget that everything in Unix is a file! So if you want a 
	program to communicate over the internet, you do it with a file desc.

	For obtaining a file descriptor for network communication we use:

	socket()

	This returns the socket decriptor which you can communicate through it
	using

	send() and recv()

	read() and write() calls can also communicate through the socket,
	but send() and recv() offer you greater control over data transmission.

DARPA Internet Sockets

	There are two important types of internet sockets:

	Stream sockets  |  Datagram sockets

	  SOCK_STREAM   |   SOCK_DGRAM

	Raw sockets are also important but not discussed in these notes.

	Datagram sockets are sometimes called "connectionless sockets" but
	are technically capable of using connect() function call

	Stream sockets are reliable two-way connected communication streams.
	If two items were inserted into the socket as:

	1, 2

	These would arrive in the order:

	1, 2

	Telnet applications typically use stream sockets. This can be seen
	by the letters you type on a screen which arrive in the same order
	you type them. Web browsers also use the HTTP protocol which uses
	stream sockets to get pages (IMPORTANT).

	If you telnet to a website on port 80 and type

	GET HTTP/1.0 and hit RETURN twice

	It will dump the HTML back at you ~

	Stream sockets achieve a high level of data transmission quality
	by using the Transmission Control Protocol (TCP).

	TCP ensures your data arrives sequentially and error-free.

	Internet Protocol (IP) mainly deals with internet routing and is
	not responsible for data integrity.

	Datagram sockets are rather unreliable, sending them could result
	in data arriving out of order. Datagram sockets are connectionless
	because they don't need to maintain an open connection as you do
	with stream sockets. 

	Datagram sockets are configured as so:

	Build a packet, slap an IP header on it with destination information,
	and send it out.

	tftp (trivial file transfer protocol), multplayer games, streaming
	audio, etc utilize datagram sockets since small losses of data
	are not very important. Since these applications can be unreliable,
	we can ifnore the dropped packets and re-transmit until the
	receiver finally send backs a confirmation packet ("ACK" packet)

	Why would datagram sockets be used if they're unreliable?

	Speed. 

Data Encapsulation

	When a packet is born, the packet is wrapped (encapsulated) in a
	header by the first protocol (TFTP) and then the whole thing
	is wrapped again by the next protocol (UDP) and again by the
	next (IP) and again by the final protocol (Ethernet)

	When a computer receives the packet, the following occurs:

	The hardware strips the Ethernet header
	The kernel strips the IP and UDP headers
	The TFTP program strips the TFTP header
	The data can now be accessed by the computer

Network Model

	(from top to bottom)

	Application (telnet, ftp, etc)
	Transport (TCP, UDP)
	Network (Ethernet, wi-fi)
	Data Link 
	Physical (Cables)


