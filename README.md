# kmmify - Kotlin Multiplatform Template

Welcome to Kmmify, a streamlined template to kickstart your Kotlin Multiplatform projects. This
template provides a foundation for building cross-platform applications sharing business logic
across iOS, Android, and Web platforms while allowing for platform-specific implementations.

![kmmify](.art/kmmify.webp)

[![Style Guide-Kotlin](https://img.shields.io/badge/Style%20Guide-Kotlin-7F52FF.svg?style=flat&labelColor=black&color=7F52FF&logo=kotlin)](https://styles.goatbytes.io/lang/kotlin)
![License](https://img.shields.io/badge/License-Apache%20V2.0-blue)
![Platform](https://img.shields.io/badge/Platform-Android%20|%20iOS%20|%20Desktop%20|%20Web-green)

## Features

- Multiplatform project setup for Android, iOS, and Web
- Pre-configured Gradle scripts for building and testing
- Documentation ready with MkDocs
- Code analysis setup with Detekt

## Getting Started

### Prerequisites

Ensure you have the following installed:

- JDK 1.8 or newer
- Kotlin Multiplatform compatible IDE (IntelliJ IDEA recommended)
- Gradle 7.0 or newer

### Setup

Clone the repository:

```sh
git clone https://github.com/goatbytes/kmmify.git
cd kmmify
```

### Building the Project

Build the project using Gradle:

```sh
./gradlew build
```

## Documentation

### Running Detekt

To run Detekt for static code analysis:

```sh
./gradlew detektAll
```

### Generating Documentation with Dokka

Generate project documentation using Dokka:

```sh
./gradlew dokkaHtml
```

### Serving Documentation Locally with MkDocs

To view the documentation locally, first ensure you have MkDocs installed:

```sh
pip install mkdocs
```

Then serve the documentation:

```sh
mkdocs serve
```

## Publishing

### Publishing to GitHub Packages

Ensure you have the appropriate permissions and your GitHub Token is set up. Then, run:

```sh
./gradlew publish
```

### Release Signing

To sign your artifacts before publishing, ensure the following properties are set in your
gradle.properties or passed via environment variables:

- SIGNING_KEY_ID
- SIGNING_KEYRING
- SIGNING_PASSWORD

## Contributing

Contributions are welcome! Please read our [contributing guide](CONTRIBUTING.md) and submit pull
requests to our repository.

## License

Distributed under the Apache v2.0 License. See `LICENSE.txt` for more information.

## About GoatBytes.IO

![GoatBytesLogo](https://storage.googleapis.com/ktor-goatbytes.appspot.com/images/logo/1000h/goatbytes-logo-with-text.png)

At **GoatBytes.IO**, our mission is to develop secure software solutions that empower businesses to
transform the world. With a focus on innovation and excellence, we strive to deliver cutting-edge
products that meet the evolving needs of businesses across various industries.

[![GitHub](https://img.shields.io/badge/GitHub-GoatBytes-181717?logo=github)](https://github.com/goatbytes)
[![Twitter](https://img.shields.io/badge/Twitter-GoatBytes-1DA1F2?logo=twitter)](https://twitter.com/goatbytes)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-GoatBytes-0077B5?logo=linkedin)](https://www.linkedin.com/company/goatbytes)
[![Instagram](https://img.shields.io/badge/Instagram-GoatBytes.io-E4405F?logo=instagram)](https://www.instagram.com/goatbytes.io/)

