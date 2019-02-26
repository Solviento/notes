IP Addresses (Ver. 4 & 6)

	Internet Protocol Version (IPv4)

	IPv4 had addresses made up of four bytes and commonly written as:

	192.0.2.111
	
	Virtually every site on the internet uses IPv4. However, IPv4 is 
	only capable of generating several billion IP addresses. Thus, we
	turn to IPv6.

	IPv6 is capable of generating 2^128 IP addresses while IPv4 is only
	capable of generating 2^32 IP addresses.

	IPv6 is represented in hexadecimal:

	2001:0db8:c9d2:aee5:73e3:934a:a5ae:9551

	IPv6 addresses can also be compressed if it contains lots of zeros
	using two colons '::' like so:

	2001:0db8:c9d2:0012:0000:0000:0000:0051
	2001:db8:c9d2:12::51

	The two addresses above are equivalent.

	The address:

	::1

	is the loopback address which is simply the machine that you're
	currently running on. The IPv4 compatible version of this is:

	::ffff:192.0.2.33

Subnets
	
	IP addresses are organized in the following:
	The first part of the IP address is the network portion of the 
	IP address and the remainder is the host portion.

	Today, we place a slash after the IP address to indicate the number
	of network bits in decimal like so:

	192.0.2.12/30

	2001:db8::/32

Port numbers

	In addition to IP addresses, there is another address that is used
	by TCP (stream sockets) and UDP (datagram sockets). This is the
	port number, a 16 bit number that represents the local address
	for the connection.

	The IP address can be thought of as the street address of a hotel
	and the port number is the room number.

	Different port numbers are used to handle different incoming services
	like mail, web, and gaming. Ports under 1024 require special OS
	privileges.

Byte Order

	Big endian represents a two byte hex number:

	b34f 

	as b3 followed by 4f.

	Little endian represents it as 4f followed by b3.

	In networks, Big Endian is also called Network Byte Order. Since
	some computers use Little endian, we must use conversion to make
	it portable. In any case, there are two types of numbers we can
	convert: short (two bytes) and long (four bytes).

	The function Host to Network Short is used for this purpose:

	htons() - Host to Network Short
	htonl()  - Host to Network Long
	ntohs() - Network to Host Short
	ntohl() - Network to Host Long

	Usually you want to convert the numbers to Network Byte Order before
	they go out on the wire and then convert them to Host Byte Order as
	they come off the wire. 

Structs

	A socket descriptor is the int types, Others are described as so:

	/* This struct is used to prep the socket address structures for
	subsequent use. Also used in host name and service name lookups */

	struct addrinfo {
		int      	ai_flags;     // AI_PASSIVE, AI_CANONNAME, etc.
	        int      	ai_family;    // AF_INET, AF_INET6, AF_UNSPEC
		int      	ai_socktype;  // SOCK_STREAM, SOCK_DGRAM
		int      	ai_protocol;  // use 0 for "any"
		size_t		ai_addrlen;   // size of ai_addr in bytes
		struct sockaddr *ai_addr;      // struct sockaddr_in or _in6
		char            *ai_canonname; // full canonical hostname
	        struct addrinfo *ai_next;      // linked list, next node
	};

	/* This struct holds socket address information for different types
	of sockets */

	struct sockaddr {
		unsigned short  sa_family;    // address family, AF_xxx
	        char            sa_data[14];  // 14 bytes of protocol address
	};

	// (IPv4 only--see struct sockaddr_in6 for IPv6)
	/* This struct makes it easier to reference elements of the socket
	address */
	
	struct sockaddr_in {
		short int          sin_family;  // Address family, AF_INET
		unsigned short int sin_port;    // Port number
		struct in_addr     sin_addr;    // Internet address
		unsigned char      sin_zero[8]; // Same size as struct sockaddr
	};

	// (IPv4 only--see struct in6_addr for IPv6)
	/* Struct that will the 4 byte IP address in Network Byte Order */

	// Internet address (a structure for historical reasons)
	struct in_addr {
	   	uint32_t s_addr; // that's a 32-bit int (4 bytes)
	    };

	
