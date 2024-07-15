# word-guessing-game
## Description
A simple Java-based word guessing game where players guess a word based on hints and have a limited number of tries.

## Features
- Guess the word
- User authentication
- Track remaining tries

## Installation
1. Clone the repository.
2. Install dependencies using `mvn install` or `gradle build`.
3. Run `Main.java` to start the game.

## Usage
- Launch the application.
- Follow the on-screen instructions to play the game.

## Dependencies
- Apache POI for handling Excel files.
- Apache Commons IO
- Apache Commons Compress
- Log4j 2

## Known Issues
- **Runtime Errors:** Missing dependencies can cause runtime errors. Common errors include:
  - `java.lang.NoClassDefFoundError: org/apache/commons/compress/archivers/zip/ZipFile`
  - `java.lang.NoClassDefFoundError: org/apache/logging/log4j/LogManager`

  Make sure to add the following dependencies to resolve these issues:
  - `commons-compress-1.21.jar`
  - `log4j-core-2.20.0.jar`
  - `log4j-api-2.20.0.jar`
  - `commons-collections4-4.4.jar`
  - `commons-io-2.11.0.jar`

## Note
I have chosen not to spend more time fixing the errors in this project. The primary goal of sharing this project is to showcase my Java development skills and provide a starting point for others who might want to contribute or use the code as a reference. If you are interested in resolving these issues or improving the game, feel free to contribute!

## Screenshots

### Main Menu
![Main Menu](https://github.com/user-attachments/assets/0270ba86-3367-4ff2-9e43-282236160258)
![Sign Up](https://github.com/user-attachments/assets/370a06b6-c36b-4467-82c5-d76ae4d3cd05)
![Login](https://github.com/user-attachments/assets/3a0d4949-6903-49b2-ad86-bb5ec2469638)

## License
This project is licensed under the MIT License.
