**Security**

- SE makes it difficult to decrypt sensitive information without physical access to the device
- SE is a separate system, makes it very difficult to decrypt data without proper authorization
- The Enclave will store encryption keys used to lock down biometric data
- Third party programs can also create and store keys in the enclave to lock down data but the apps **never have access to the keys themselves** 
  - The apps will make requests for the Secure Enclave to encrypt and decrypt daata
- For developers
  - When you store a private key in the Secure Enclave, you never actually handle the key, making it difficult for the key to become compromised. Instead, you instruct the Secure Enclave to create the key, securely store it, and perform operations with it. You receive only the output of these operations, such as encrypted data or a cryptographic signature verification outcome.
- The Secure Enclave cannot import keys from other devices, it is exclusively designed to create nad use keys locally

**The Game of Life or Death**

An ongoing theory of how the world functions.

Consider this scenario.

Take any living being on Earth. Let's say for example Ariel, a barista from Starbucks located somewhere along the coast. Ariel is a young, hardworking, and optimistic college student who enjoys a vast range of recreational activities. Since childhood, Ariel was taught by her parents to take caution against risky ventures as most attentive parents ought to do.

But risk is its own reward.

See now here's the thing. Why would Ariel want to strive to become a young, hardworking, and optimistic college student who works at Starbucks?

As the day ends Ariel leaves her place of work, takes the train back home, and arrives at the corner of her apartment complex. In the corner of her eye, a small group of young men are entertaining themselves in front of 



**Key Factors**

- Money
  - Have
- Personality
  - Introvert
  - Extravert
  - Ambivert
- Trauma
  - Pain
  - Euphoria
- Love
  - Love for self
  - Love for family
  - Love for country
- Luck
  - Right time, right place
  - Right time, wrong place
  - Wrong time, right place

**Authentication**

- The Enclave will store encryption keys used to lock down biometric data
- Third party programs can also create and store keys in the enclave to lock down data but the apps **never have access to the keys themselves** 
  - The apps will make requests for the Secure Enclave to encrypt and decrypt daata
- For developers
  - When you store a private key in the Secure Enclave, you never actually handle the key, making it difficult for the key to become compromised. Instead, you instruct the Secure Enclave to create the key, securely store it, and perform operations with it. You receive only the output of these operations, such as encrypted data or a cryptographic signature verification outcome.
- The Secure Enclave cannot import keys from other devices, it is exclusively designed to create nad use keys locally

































