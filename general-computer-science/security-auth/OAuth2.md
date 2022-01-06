# OAuth 2.0 (Pluralsight)

## Introduction

| Past                             | Present                                                 |
| -------------------------------- | ------------------------------------------------------- |
| XML<br />SOAP<br />SAML/WS<br /> | JSON<br />HTTP APIs<br />OAuth and OpenID Connect<br /> |

- Previous API Authentication methods relied primarily on Enterprise applications
- IoT and Smart devices changed authorization methods
- **Authentication**
  - process of verifying and identity
  - confirming who they are
  - user provides something they know
- **Authorization**
  - permissions and access control
  - verifying if person is allowed to do the thing they want to do

- Problem of credential sharing (login username/password)
  - No way for API to tell apart user or application
  - Application could impersonate user
  - If API knows username and password, they could change the password themselves
  - Revocation of leaked credentials
    - If potential leak occurs, it would penalize user for sharing their credentials in a trusted app
  - Incompatible types between front end and back end applications, front end cannot use username/password by itself
- Problem of Cookies
  - Cookies were once used to protect APIs
  - Login to the API and get a cookie
    - Cookies are unsuitable due to Cross Site Request Forgery
      - If user opens another tab to access the API, they will still be authorized with that cookie
        - Doing so, we have authorized the browser to use the API with that cookie credentials
        - Big vulnerability
- Problem of API Keys
  - Solves most problems of Cookie authorization
  - Does not apply well to applications who cannot keep a secret such as
    - Single page app with no server backend
      - Cannot keep key secure
  - There are no standard design method for API Keys
  - API keys are Long Lived
    - For user this is good
    - If key gets stolen, it can be used forever until its noticed as stolen

## OAuth 2.0

- **OAuth 2.0**
  - This authorization framework was build for HTTP APIs
  - Allows a user to security delegate scoped API access to an application 
    - scoped access - user defines which parts of the API the application can use
  - Can be described as a delegation protocol

### OAuth 2.0 Players

Protected Resource (HTTP API)

- Our API

Client (Requesting Application)

- App

Resource Owner

- User
  - Consents client to access certain data

Authorization Server

- Trusted by all parties involved

![oauth2-01](C:\Users\Jason\Documents\Git Projects\notes\security-auth\photos\oauth2-01.PNG)

### The OAuth Dance

- Resource Owner, Client App, Authorization Server, Protected Resource

1. Client App makes an Authorization request to Resource Owner (user)
2. The authorization request then direct the User to the Authorization Server to gain authentication and consent
   1. Note that user credentials are completely removed from the client app
   2. User authentication can be done using any method
   3. Once user is authenticated (username, password) the user must consent to client app permission
3. After authorization server is complete, it will redirect to the client app as well as provide an Authorization Grant 
   1. This Grant is a physical representation of the user's authorization of the client application to act on their behalf in the form of a random value that only the Authorization Server understands
4. The client app makes a backchannel request to the Authorization Server away from the browser 
   1. In this request it includes the Authorization Grant it received and also some way of authenticating itself as a valid client
   2. If this request is valid, the Authorization Server will return an Access Token 
      1. Only the Client app that was given the Authorization Grant can swap it with an Access Token
5. Once the client app receives the Access Token it can then make requests to the Protected Resource API
   1. The Access Token is sent through the header as "Authorization" using Bearer scheme
      1. Authorization: Bearer f4haq3aqqw90bfe338and9aa938n3oenc2nlwpqo
      2. Whoever has this token can use it (bearer scheme)
6. When Protected Resource API receives an Access Token, it needs to verify it in some way
   1. Can get hazy as the implementation of verification is outside the OAuth Spec
   2. It could be implemented as structured data such as a JSON web token (JWT)
   3. Could be unstructured and be verified within the protected resource API or simply sent to the Authorization server to validate
   4. Once Protected Resource API validates the token
      1. Will see which roles the client app has + permissions

![oauth2-02](C:\Users\Jason\Documents\Git Projects\notes\security-auth\photos\oauth2-02.PNG)

### OAuth 1.0 Mistakes

- OAuth 1.0 is not authentication
  - Was misused as a way to authenticate a request
  - Access tokens do not represent the user
  - Client apps are not the token's intended audience
  - OpenID OAuth is another topic of discussion
- Too abstract
  - Access token format
  - Access token validation
  - User validation
    - Many different implementations
- What OAuth 1.0 got right
  - Delegated access 
  - API Access control
  - Simple solution for user and client credentials
  - User consent

**OAuth 2.0 is a framework**

- should be built upon
- OAuth 2.0 is best used to protect an API



### Protocol Endpoints

- In OAuth, we use two endpoints
  - Authorization endpoint
    - All user interaction from a browser or device
  - Token endpoint
    - Meant for machines only
- Must use transport layer security

![oauth2-03](C:\Users\Jason\Documents\Git Projects\notes\security-auth\photos\oauth2-03.PNG)

### OAuth Scope

- A permission to do something within a protected resource on behalf of the resource owner















































