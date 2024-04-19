# Kmmify - Kotlin Multiplatform Template

Welcome to Kmmify, a streamlined template to kickstart your Kotlin Multiplatform projects. This
template provides a foundation for building cross-platform applications sharing business logic
across iOS, Android, and Web platforms while allowing for platform-specific implementations.

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

## Contact

GoatBytes.IO - engineering@goatbytes.io
