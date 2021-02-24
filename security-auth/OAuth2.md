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