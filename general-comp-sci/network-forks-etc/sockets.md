# System Calls

**Sockets API Summary**

``` 
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
```

- Called by both client and server
- The server uses socket() to create a listening socket.
This listening socket it later used to create a connected socket
using accept().

**bind(server_sock, &server_addr, server_len)**

- Called by server only
- Binds a listening socket to a specific port number that the client
uses to connect.

**listen(server_sock, backlog)**

- Called by server only
- Sets up with listening socket to accept connections

**accept(server_sock, &client_addr, &client_len)**

- Called by server only
- Creates and returns a brand new connected socket for each successive client

**connect(client_sock, &server_addr, server_len)**

- Called by client only
- Creates a connection between client and server, data may be sent back and forth

**send() recv()**

- Called by client and server
- Reads and writes to the other side
- Message boundaries may not always be preserved

## What is a Socket?

A socket is a way to speak to other programs using standard Unix file descriptors. 

Whenever Unix programs do any sort of I/O, they do it by reading to a file descriptor.

A file descriptor is an integer associated with an open file. This open file can also be a network connection, a FIFO, a pipe,
a terminal, a real on-the-disk file or anything else.

Don't forget that everything in Unix is a file! So if you want a program to communicate over the internet, you do it with a file desc.

For obtaining a file descriptor for network communication we use:

- socket()

This returns the socket decriptor which you can communicate through it
using

- send() and recv()

- read() and write() calls can also communicate through the socket,
  but send() and recv() offer you greater control over data transmission.

**DARPA Internet Sockets**

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

- Why would datagram sockets be used if they're unreliable?
  - Speed. 

**Data Encapsulation**

When a packet is born, the packet is wrapped (encapsulated) in a
header by the first protocol (TFTP) and then the whole thing
is wrapped again by the next protocol (UDP) and again by the
next (IP) and again by the final protocol (Ethernet)

When a computer receives the packet, the following occurs:

The hardware strips the Ethernet header
The kernel strips the IP and UDP headers
The TFTP program strips the TFTP header
The data can now be accessed by the computer

**Network Model (from top to bottom)**

``` 
Application (telnet, ftp, etc)
Transport (TCP, UDP)
Network (Ethernet, wi-fi)
Data Link 
Physical (Cables)
```




