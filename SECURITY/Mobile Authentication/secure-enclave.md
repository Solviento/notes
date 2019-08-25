**Introduction**

- Secure Enclave is a separate processor that handles biometric information on iPhone and Macs
- SE boots separately from the rest of the device, runs on its own microkernel
  - Not accessible by the OS or any programs
  - 4MB of flash storage
  - Stores only 256 bit elliptic curve private keys
    - Never synced to the cloud or seen by device OS
    - System will only ask SE to decrypt information using the keys

**Security**

- SE makes it difficult to decrypt sensitive information without physical access to the device
- SE is a separate system, makes it very difficult to decrypt data without proper authorization
- The Enclave will store encryption keys used to lock down biometric data
- Third party programs can also create and store keys in the enclave to lock down data but the apps **never have access to the keys themselves** 
  - The apps will make requests for the Secure Enclave to encrypt and decrypt daata
- For developers
  - When you store a private key in the Secure Enclave, you never actually handle the key, making it difficult for the key to become compromised. Instead, you instruct the Secure Enclave to create the key, securely store it, and perform operations with it. You receive only the output of these operations, such as encrypted data or a cryptographic signature verification outcome.
- The Secure Enclave cannot import keys from other devices, it is exclusively designed to create nad use keys locally

**Secure Enclave Hacked**

- in 2017, hackers managed to decrypt the firmware of SE
- Gives them information on how enclave works but not how to retrieve encryption keys

**Note**

- When selling a Mac, SE should be wiped out