**Introduction**

- OAuth 2 is an authorization framework that enables applications to obtain limited access to user accounts on an HTTP service, such as Facebook, GitHub, and DigitalOcean. 
- It works by delegating user authentication to the service that hosts the user account, and authorizing third-party applications to access the user account. 
- OAuth 2 provides authorization flows for web and desktop applications, and mobile devices.

**Basic Overview**

John is a developer who has been told to use OAuth 2.0 to authorize users with an external server.

- Removes need to manage user private info on their own

*OAuth 2.0 Main Roles*

- **User** (Resource Owner)
- Resource Owner is the user who authorizes an application to access their account
  - The application's access to the user's account is limited to the "scope" of the authorization granted (e.g. read or write access)
  
- **Application**
  - The client is the *application* that wants to access the *user*'s account. Before it may do so, it must be authorized by the user, and the authorization must be validated by the API.
  - Ex: Spotify
  
- **API**
  - **Authorization Server**
    - The authorization server verifies the identity of the *user* then issues access tokens to the *application*
  - **Resource Server**
    - The resource server hosts the protected user accounts
  
  - Ex: Facebook API login

<img style="height=400px" src="../PHOTOS/oauth-09.png">

*Here is a more detailed explanation of the steps in the diagram:*

1. The *application* requests authorization to access service resources from the *user*
2. If the *user* authorized the request, the *application* receives an authorization grant
3. The *application* requests an access token from the *authorization server* (API) by presenting authentication of its own identity, and the authorization grant
4. If the application identity is authenticated and the authorization grant is valid, the *authorization server* (API) issues an access token to the application. Authorization is complete.
5. The *application* requests the resource from the *resource server* (API) and presents the access token for authentication
6. If the access token is valid, the *resource server* (API) serves the resource to the *application*

The actual flow of this process will differ depending on the authorization grant type in use, but this is the general idea.

**Application Registration**

- Before using OAuth with your application, you must register your application with the service. 
- This is done through a registration form in the "developer" or "API" portion of the service's website, where you will provide the following information (and probably details about your application):
  - Application Name
  - Application Website
  - Redirect URI or Callback URL
    - The redirect URI is where the service will redirect the user after they authorize (or deny) your application, and therefore the part of your application that will handle authorization codes or access tokens.

**Client ID and Client Secret**

- Once your application is registered, the service will issue "client credentials" in the form of a ***client identifier*** and a ***client secret***. 
  - The Client ID is a publicly exposed string that is used by the service API to identify the application, and is also used to build authorization URLs that are presented to users. 
  - The Client Secret is used to authenticate the identity of the application to the service API when the application requests to access a user's account, and must be kept private between the application and the API.
    

**Note**

- When you log into Spotify with a FB account, Spotify DOES NOT grab your username and password from FB
- Passwords are never passed from to server in an OAuth 2.0 framework

**Step by Step Workflow of OAuth 2.0 using Web Service** 

<img style="height=400px;" src="../PHOTOS/oauth-01.png">



- Sarah is a user who wants to manage their bank finances through MyBucks application
- When Sarah clicks to connect her bank account, MyBucks will make an Authorization Request to the Authorization Server and an authorization prompt will appear

<img style="height=400px" src="../PHOTOS/oauth-02.png">

- Authorization prompt should usually tell User which permissions they are requesting

- Spotify would typically ask to use FB profile info and profile picture

<img style="height=400px" src="../PHOTOS/oauth-03.png">

- Sarah grants permission to MyBucks to see account balances and transaction history data
- That authorization is sent back to MyBucks through an Authorization Grant along with an Authorization Code that MyBucks will use when requesting an access token to see Sarah's account

<img style="height=400px" src="../PHOTOS/oauth-04.png">

- MyBucks will now request an access token from Authorization Server using the Authorization code given from before
- Note that none of Sarah's private information is located on the Authorization Server
  - It is only responsible for sending/receiving Authorization grants

<img style="height=400px" src="../PHOTOS/oauth-05.png">

- After accepting the Authorization Grant from MyBucks, the Authorization Server will generate an Access Token specifically for Sarah
  - Provides MyBucks permission to only see balances and transaction history

<img style="height=400px" src="../PHOTOS/oauth-06.png">

- Now whenever MyBucks requests access using the given Access Token, Memorial Bank will accept it and return the requested resources
- Note that Oauth 2.0 serves as the Authorization Framework!!!
  - The actual authentication occurs with OpenID connect

<img style="height=400px" src="../PHOTOS/oauth-07.png">

- For any of this to work, MyBucks application will need to register with the Bank's API service
- MyBucks will need to provide a name, website, and callback URL

<img style="height=400px" src="../PHOTOS/oauth-08.png">

- Once this information is sent to Memorial Bank's API, the service will send back a set of credentials
  - Client ID to identify the application
  - Client Secret is used to authenticate MyBucks

<h3>More Detailed Steps on OAuth 2.0 Grants </h3>

**Authorization Grant**

- In the *Protocol Flow* above, the first four steps cover obtaining an authorization grant and access token. 

- The authorization grant type depends on the method used by the application to request authorization, and the grant types supported by the API. 

- OAuth 2 defines four grant types, each of which is useful in different cases:
  - **Authorization Code**: used with server-side Applications
  - **Implicit**: used with Mobile Apps or Web Applications (applications that run on the user's device)
  - **Resource Owner Password Credentials**: used with trusted Applications, such as those owned by the service itself
  - **Client Credentials**: used with Applications API access

**Grant Type: Authorization Code**

- The **authorization code** grant type is the most commonly used because it is optimized for *server-side applications*, where source code is not publicly exposed, and *Client Secret* confidentiality can be maintained. 
- This is a redirection-based flow, which means that the application must be capable of interacting with the *user-agent* (i.e. the user's web browser) and receiving API authorization codes that are routed through the user-agent.

<img style="height=400px" src="../PHOTOS/oauth-10.png">

**Step 1: Authorization Code Link**

- First, the user is given an authorization code link to click on that looks like the following:

```
https://cloud.digitalocean.com/v1/oauth/authorize?response_type=code&client_id=CLIENT_ID&redirect_uri=CALLBACK_URL&scope=read
```

- Here is an explanation of the link components:
  - **https://cloud.digitalocean.com/v1/oauth/authorize**: the API authorization endpoint
  - **client_id=client_id**: the application's *client ID* (how the API identifies the application)
  - **redirect_uri=CALLBACK_URL**: where the service redirects the user-agent after an authorization code is granted
  - **response_type=code**: specifies that your application is requesting an authorization code grant
  - **scope=read**: specifies the level of access that the application is requesting

**Step 2: User Authorizes Application**

- When the user clicks the link, they must first log in to the service, to authenticate their identity (unless they are already logged in). 
- Then they will be prompted by the service to *authorize* or *deny* the application access to their account. Here is an example authorize application prompt:

<img style="width=500px" src="../PHOTOS/oauth-11.png">

**Step 3: Application Receives Authorization Code**

- If the user clicks "Authorize Application", the service redirects the user-agent to the application redirect URI, which was specified during the client registration, along with an ***authorization code***. 
- The redirect would look something like this (assuming the application is "dropletbook.com"):

```
https://dropletbook.com/callback?code=AUTHORIZATION_CODE
```

**Step 4: Application Requests Access Token**

- The application requests an access token from the API, by passing the authorization code along with authentication details, including the *client secret*, to the API token endpoint. 
- Here is an example POST request to DigitalOcean's token endpoint:

```
https://cloud.digitalocean.com/v1/oauth/token?client_id=CLIENT_ID&client_secret=CLIENT_SECRET&grant_type=authorization_code&code=AUTHORIZATION_CODE&redirect_uri=CALLBACK_URL
```

**Step 5: Application Receives Access Token**

- If the authorization is valid, the API will send a response containing the access token (and optionally, a refresh token) to the application. 
- The entire response will look something like this:

```
{"access_token":"ACCESS_TOKEN","token_type":"bearer","expires_in":2592000,"refresh_token":"REFRESH_TOKEN","scope":"read","uid":100101,"info":{"name":"Mark E. Mark","email":"mark@thefunkybunch.com"}}
```

- Now the application is authorized! It may use the token to access the user's account via the service API, limited to the scope of access, until the token expires or is revoked. If a refresh token was issued, it may be used to request new access tokens if the original token has expired.



**Grant Type: Implicit**

- The **implicit** grant type is used for mobile apps and web applications (i.e. applications that run in a web browser), where the *client secret* confidentiality is not guaranteed. 
- The implicit grant type is also a redirection-based flow but the access token is given to the **user-agent** to forward to the application, so it may be exposed to the user and other applications on the user's device. 
- Also, this flow does not authenticate the identity of the application, and relies on the **redirect UR**I (that was registered with the service) to serve this purpose.
  - The implicit grant type does not support refresh tokens.

The implicit grant flow basically works as follows: 

- the user is asked to authorize the application
- then the authorization server passes the access token back to the user-agent which passes it to the application

<img style="width=500px" src="../PHOTOS/oauth-12.png">

**Step 1: Implicit Authorization Link**

- With the implicit grant type, the user is presented with an authorization link, that requests a token from the API. 
- This link looks just like the authorization code link, except it is requesting a *token* instead of a code (note the *response type* "token"):

```
https://cloud.digitalocean.com/v1/oauth/authorize?response_type=token&client_id=CLIENT_ID&redirect_uri=CALLBACK_URL&scope=read
```

**Step 2: User Authorizes Application**

- When the user clicks the link, they must first log in to the service, to authenticate their identity (unless they are already logged in). 
- Then they will be prompted by the service to *authorize* or *deny* the application access to their account. Here is an example authorize application prompt:

<img style="width=300px;" src="../PHOTOS/oauth-11.png">

**Step 3: User-agent Receives Access Token with Redirect URI**

- If the user clicks "Authorize Application", the service redirects the user-agent to the application redirect URI, and includes a URI fragment containing the access token. It would look something like this:

```
https://dropletbook.com/callback#token=ACCESS_TOKEN
```

**Step 4: User-agent Follows the Redirect URI**

- The user-agent follows the redirect URI but **retains** the access token.

**Step 5: Application Sends Access Token Extraction Script**

- The application returns a webpage that contains a script that can extract the access token from the full redirect URI that the user-agent has retained.

**Step 6: Access Token Passed to Application**

- The user-agent (browser) executes the provided script and passes the extracted access token to the application.

- Now the application is authorized! It may use the token to access the user's account via the service API, limited to the scope of access, until the token expires or is revoked.



**Grant Type: Resource Owner Password Credentials**

- With the **resource owner password credentials** grant type, the user provides their service credentials (username and password) directly to the application, which uses the credentials to obtain an access token from the service. 
- This grant type should only be enabled on the authorization server if **other flows are not viable**. 
- Also, it should only be used if the application is trusted by the user (e.g. it is owned by the service, or the user's desktop OS).

**Password Credentials Flow**

- After the user gives their credentials to the application, the application will then request an access token from the authorization server. The POST request might look something like this:

```
https://oauth.example.com/token?grant_type=password&username=USERNAME&password=PASSWORD&client_id=CLIENT_ID
```

- If the user credentials check out, the authorization server returns an access token to the application. Now the application is authorized!



**Grant Type: Client Credentials**

- The **client credentials** grant type provides an application a way to access its own service account. 
- Examples of when this might be useful include if an application wants to update its registered description or redirect URI, or access other data stored in its service account via the API.

**Client Credentials Flow**

- The application requests an access token by sending its credentials, its client ID and client secret, to the authorization server. 
- An example POST request might look like the following:

```
https://oauth.example.com/token?grant_type=client_credentials&client_id=CLIENT_ID&client_secret=CLIENT_SECRET
```

- If the application credentials check out, the authorization server returns an access token to the application. Now the application is authorized to use its own account!



**Example Access Token Usage**

- Once the application has an access token, it may use the token to access the user's account via the API, limited to the scope of access, until the token expires or is revoked.

- Here is an example of an API request, using `curl`. Note that it includes the access token:

```
curl -X POST -H "Authorization: Bearer ACCESS_TOKEN""https://api.digitalocean.com/v2/$OBJECT" 
```

- Assuming the access token is valid, the API will process the request according to its API specifications. If the access token is expired or otherwise invalid, the API will return an "invalid_request" error.



**Refresh Token Flow**

- After an access token expires, using it to make a request from the API will result in an "Invalid Token Error". 
- At this point, if a refresh token was included when the original access token was issued, it can be used to request a fresh access token from the authorization server.

- Here is an example POST request, using a refresh token to obtain a new access token:

```
https://cloud.digitalocean.com/v1/oauth/token?grant_type=refresh_token&client_id=CLIENT_ID&client_secret=CLIENT_SECRET&refresh_token=REFRESH_TOKEN
```











