**SAML**

- Security Assertion Markup Language (SAML)
  - Is a **SSO standard** for logging users into applications based on their sessions in another context
  - This Single Sign On (SSO) login standard has significant advantages over logging in using a username/password
    - No need to type in credentials
    - No need to remember and renew passwords
    - No weak passwords
- Most organizations already know the identity of users because they are logged in to their Active Directory domain or intranet. 
- It makes sense to use this information to log users in to other applications, such as web-based applications, and one of the more elegant ways of doing this is by using SAML.
- SAML is very *powerful* and *flexible*, but the specification can be quite a handful

**How SAML Works** 

- SAML SSO works by transferring the user’s identity from one place (the identity provider) to another (the service provider). This is done through an exchange of **digitally signed XML documents**.
- Consider the following scenario: 
  - A user is logged into a system that acts as an identity provider. The user wants to log in to a remote application, such as a support or accounting application (the service provider). The following happens:

1. The user accesses the remote application using a link on an intranet, a bookmark, or similar and the application loads.
2. The application identifies the user’s origin (by application subdomain, user IP address, or similar) and redirects the user back to the identity provider, asking for authentication. This is the authentication request.
3. The user either has an existing active browser session with the identity provider or establishes one by logging into the identity provider.
4. The identity provider builds the authentication response in the form of an XML-document containing the user’s username or email address, signs it using an X.509 certificate, and posts this information to the service provider.
5. The service provider, which already knows the identity provider and has a certificate fingerprint, retrieves the authentication response and validates it using the certificate fingerprint.
6. The identity of the user is established and the user is provided with app access.

**SAML SSO FLOW**

<img height=300px src="../PHOTOS/saml-01.png">

**Benefits of SAML Authentication**

- **Standardization:** 
  - SAML is a standard format that allows seamless interoperability between systems, independent of implementation. 
  - It takes away the common problems associated with vendor and platform-specific architecture and implementation.
- **Improved User Experience:** 
  - Users can access multiple service providers by signing in just once, without additional authentication, allowing for a faster and better experience at each service provider. 
  - This eliminates password issues such as reset and recovery.
- **Increased Security:** 
  - Security is a key aspect of software development, and when it comes to enterprise applications, it is extremely important. 
  - SAML provides a single point of authentication, which happens at a secure identity provider. Then, SAML transfers the identity to service providers. This form of authentication ensures that credentials don't leave the firewall boundary.
- **Loose Coupling of Directories:** 
  - SAML doesn't require user information to be maintained and synchronized between directories.
- **Reduced Costs for Service Providers:** 
  - With SAML, you don't have to maintain account information across multiple services. The identity provider bears this burden.





























