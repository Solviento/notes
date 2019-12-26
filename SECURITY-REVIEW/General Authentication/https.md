**History**

- Before, the unecrypted HTTP protocol did not protect data from interception or alteration which can lead to eavesdropping, tracking, and modding received data
  - HTTP data can include browser identity, website content, search terms, etc.
- HTTPS have now adopted HTTTPS to protect visitors to their website and services

**HTTPS (Hypertext Transfer Protocol Secure)**

- HTTPS verifies the identity of a website or web service for a connecting client, encrypts nearly all information sent between the website or service and the user
  - Includes cookies, user agent details, URL paths, form submission, query string paramters
- HTTPS is a combination of HTTP and Transport Layer Security (TLS)
  - TLS is a network protocol that established an encrypted connection to an authenticated peer over an untrusted network
- Browsers and HTTPS clients are configured to trust a set of certificate authorities that can issue cryptographically signed certificates on behalf o web service owners
  - These certificates communicate to the client that the web service host demonstrated ownership of the domain to the certificate authority at the time of certificate issuance

**Limitations**

- IP addresses and destination domain names are not encrypted during communication
- Encrypted information can reveal information indirectly such as time spent on site, size of requested resources/submitted information
- HTTPS guarantees the integrity of the connection between the two systems, not the systems themselves
  - A user's system can be compromised by an attacker
  - Web server can be hacked or compromised through other means

**Challenges and Considerations**

- Site performance
  - Encryption adds some computational overhead
    - Modern browsers can handle such overhead
    - Some websites performance can be improved by switching to HTTPS
- Server name indication
  - Allows for more efficient use of IP addresses when serving multiple domains
- Mixed content
  - Websites served over HTTPS need to ensure that all external resources (images, scripts, fonts, etc.) are also loaded over a secure connection
    - Can be burdensome when converting an old website
- APIs and Services
  - Web APIs require a more gradual and hands on migration strategy
    - Not all clients can be expected to be configured for HTTPS connections or to follow redirects
- Planning for change
  - Protocols and web standards improve regularly
    - Websites should deploy HTTPS in a manner that allows for rapid updates to certificates, cipher choices, and configuration elements
- Strict Transport Security
  - HTTPS websites must enable HTTP String Transport Security (HSTS) to instruct browsers to assume HTTPS going forward
- Cost effective implementation
  - HTTP websites can be mocked by malicious attackers
    - HTTPS websites make it harder to do so
  - Save money through avoiding loss of data and privacy

**HTTPS for Everything**

- Unencrypted HTTP request reveals information about a user’s behavior, and the interception and tracking of unencrypted browsing has become commonplace

- Today, **there is no such thing as non-sensitive web traffic**, and public services should not depend on the benevolence of network operators.
- When properly configured, HTTPS can provide a fast, secure connection that offers the level of privacy and reliability that users should expect from government web services.

### What does HTTPS do?

When properly configured, an HTTPS connection guarantees three things:

- **Confidentiality.** The visitor’s connection is encrypted, obscuring URLs, cookies, and other sensitive metadata.
- **Authenticity.** The visitor is talking to the “real” website, and not to an impersonator or through a “man-in-the-middle”.
- **Integrity.** The data sent between the visitor and the website has not been tampered with or modified.

- A plain HTTP connection can be easily monitored, modified, and impersonated.

### What information does HTTPS protect?

- HTTPS encrypts nearly all information sent between a client and a web service.

- For example, an **unencrypted HTTP** request reveals not just the body of the request, but the full URL, query string, and various HTTP headers about the client and request:

![What you see with HTTP](https://https.cio.gov/assets/images/with-http-headers.png)

- An **encrypted HTTPS** request protects most things:

![What you see with HTTPS](https://https.cio.gov/assets/images/with-https-headers.png)

- This is the same for all HTTP methods (GET, POST, PUT, etc.). The URL path and query string parameters are encrypted, as are POST bodies.

### What information does HTTPS *not* protect?

- While HTTPS encrypts the entire HTTP request and response, the DNS resolution and connection setup can reveal other information, such as the full domain or subdomain and the originating IP address, as shown above.

- Additionally, attackers can still analyze encrypted HTTPS traffic for “side channel” information. This can include the time spent on site, or the *relative size* of user input.

### How does HTTPS relate to HTTP/2?

- HTTP/2 (2015) is a backwards-compatible update to HTTP/1.1 (finalized in [1999](https://tools.ietf.org/html/rfc2616)) that is optimized for the modern web.

- HTTP/2 includes many features that can drastically speed up website performance

- While HTTP/2 does not require the use of encryption in its formal spec, every major browser that has implemented HTTP/2 has only implemented support for encrypted connections

- This means that in practice, **the major performance benefits of HTTP/2 first require the use of HTTPS.**

### How does migrating to HTTPS affect search engine optimization (SEO)?

In general, migrating to HTTPS improves a website’s own SEO and analytics.

- As of August 2014, Google [uses HTTPS as a ranking signal](https://security.googleblog.com/2014/08/https-as-ranking-signal_6.html), which can improve search rankings.
- Migrating to HTTPS will improve analytics about web traffic referred from HTTPS websites, as referrer information [is not passed from HTTPS websites to HTTP websites](https://stackoverflow.com/questions/1361705/is-http-header-referer-sent-when-going-to-a-http-page-from-a-https-page/1361720#1361720).

To make the migration as smooth as possible, and avoid taking a SEO hit:

- Use a **proper 301 redirect** to redirect users from `http://` to `https://`. **Do not use a 302 redirect**, as this may negatively impact search rankings.
- Use the [canonical link element](https://en.wikipedia.org/wiki/Canonical_link_element) (`<link rel="canonical">`) to inform search engines that the “canonical” URL for a website uses `https://`.

### How can an HTTPS site keep sending referrer information to linked HTTP sites?

- By default, when a user is on an HTTPS website and clicks a link to an HTTP website, browsers will not send a `Referer` header to the HTTP website

- However, this means that if a website migrates to HTTPS, any HTTP sites it links to will stop seeing referrer data from the HTTPS website. This can be a disincentive to migrate to HTTPS, as it deprives linked HTTP sites of analytics data, and means the HTTPS website won’t get “credit” for referring traffic to linked websites.

- Website owners who wish to continue sending outbound referrer information to linked HTTP sites can use **Referrer Policy** to override browser default behavior, while retaining the privacy of HTTPS URLs.

- To do this, websites **should use** the [`origin-when-cross-origin`](https://www.w3.org/TR/referrer-policy/#referrer-policy-origin-when-cross-origin) policy. This will allow supporting browsers to send **only the origin** as the `Referer` header. This limited referral information applies even if both sites use HTTPS.

- The simplest way to set this policy is by including a `<meta>` tag in the body of the HTTPS website:

```html
<meta name="referrer" value="origin-when-cross-origin" />
```

### How difficult is it to attack an HTTPS connection?

- Attacks on HTTPS connections generally fall into 3 categories:
  - Compromising the quality of the HTTPS connection, through cryptanalysis or other protocol weaknesses.
  - Compromising the client computer, such as by installing a malicious root certificate into the system or browser trust store.
  - Obtaining a “rogue” certificate trusted by major browsers, generally by manipulating or compromising a certificate authority.

- These are all possible, but for most attackers they are very difficult and require significant expense. Importantly, they are all *targeted* attacks, and are not feasible to execute against any user connecting to any website.

- By contrast, plain HTTP connections can be easily intercepted and modified by anyone involved in the network connection, and so attacks can be carried out at large scale and at low cost.

## Why are domain names unencrypted over HTTPS today?

- This is primarily to support **Server Name Indication** (SNI), a TLS extension that allows multiple hostnames to be served over HTTPS from one IP address.
- The SNI extension was introduced in 2003 to allow HTTPS deployment to scale more easily and cheaply, but it does mean that the hostname is sent by browsers to servers “in the clear” so that the receiving IP address knows which certificate to present to the client.
- When a domain or a subdomain itself reveals sensitive information (e.g. ‘contraception.foo.gov’ or ‘suicide-help.foo.gov’), this can reveal that information to passive eavesdroppers.
- From a network privacy perspective, DNS also “leaks” hostnames in the clear across the network today (even when DNSSEC is used). There are ongoing efforts in the network standards community to encrypt both the SNI hostname and DNS lookups, but as of late 2015, nothing has been deployed to support these goals

### How does HTTPS protect against DNS spoofing?

- A valid HTTPS certificate shows that the server has demonstrated ownership over the domain to a trusted certificate authority at the time of certificate issuance.
- To ensure that an attacker cannot use DNS spoofing to direct the user to a plain `http://` connection where traffic can be intercepted, websites can use [HTTP Strict Transport Security](https://https.cio.gov/hsts/) (HSTS) to instruct browsers to require an HTTPS connection for their domain at all times.
- This means that an attacker that successfully spoofs DNS resolution must also create a valid HTTPS connection. This makes DNS spoofing as challenging and expensive as [attacking HTTPS generally](https://https.cio.gov/faq/#how-difficult-is-it-to-attack-an-https-connection?).
- If the attacker spoofs DNS but doesn’t compromise HTTPS, users will receive a notable warning message from their browser that will prevent them from visiting the possibly malicious site. If the site uses HSTS, there will be no option for the visitor to disregard and click through the warning.
- HTTPS and HSTS work together to protect a domain against DNS spoofing.