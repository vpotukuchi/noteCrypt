# Abstract

The app uses a basic implementation of AES 256 encryption to secure text-based information. The app's previous version had a hardcoded login mechanism and a 32-character alphanumeric key, which has been updated to include a more secure option called the "secure flow." This feature uses Android's built-in lock screen protection to authenticate access and can only be accessed if the user has set up lock screen protection on their device. As an alternative, the basic flow is still available, which has limited security and warns the user of its limitations.

The secure flow allows the user to type in a key of their choice or generate a secure random key that uses RSSI values captured from the Wi-Fi adapter as a source of entropy for the seed used by the secure random number generator. The report also provides a detailed description of the app's other security features, known issues and limitations, and a functional specification.

This Android app is suitable for personal data protection, business security, and in industries that require high levels of security and encryption. It can also serve as an educational tool to teach AES-256 encryption algorithm and its secure implementation. The app offers a comprehensive solution for anyone looking to protect their sensitive information and stay secure in today's digital age.
