# GameMixAndroid

This project is an android project with a possible connection IoT...

## Table of Contents
- [Work in Progress](#work-in-progress)
- [Tests](#tests)
  - [Unit Tests](#unit-tests)
  - [Integration Tests](#integration-tests)
  - [E2E Tests](#e2e-tests)
  - [Security Tests](#security-tests)

# Work in Progress ....
See you soon for more things ;)
Actually, I work on the CI/CD pipeline to automate the build and test process.

# Tests

## Unit Tests
Unit tests are written to test individual components of the application. They are run using the Android testing framework.

So we have the following unit tests:

### US001 - User Registration
  **As a** user, **I want to** join a game with a username, **So that** I can participate in the game.

### US002 - User Deleted
  **As a** user, **I want to** leave a game, **So that** I no longer participate in the game.

### US003 - Party Creation 
  **As a** user, **I want to** create a game, **So that** I can play a game.

### US004 - Score Edition
  **As a** user,**I want to** update scores,**So that** I can manage them.

## Integration Tests
Integration tests are written to test the interaction between different components of the application. They are run using the Android testing framework.

### US005 - **Adding a player to a game**
  **GIVEN** I have an ongoing game, **WHEN** I add a new player, **THEN** the player list is updated in real-time in the interface.

### US006 - **Player limit and error handling**
  **GIVEN** the player limit is reached in a game, **WHEN** I try to add another player, **THEN** I receive an error message informing me that the limit is exceeded and the list is not modified.

### US007 - **Score updates**
  **GIVEN** I have an ongoing game with players, **WHEN** I update a player's score, **THEN** the new score is immediately displayed in the interface.

### US008 - **Bluetooth connection and device control**
  **GIVEN** I want to use an external device, **WHEN** I connect via Bluetooth and send a command, **THEN** the external device executes the command (shuffling or dealing cards).

### US009 - **Bluetooth connection error handling**
  **GIVEN** I try to connect to an unavailable Bluetooth device, **WHEN** the connection fails, **THEN** I receive a clear error message.

### US010 - **Navigation between screens**
  **GIVEN** I am on an application screen, **WHEN** I click on a navigation button, **THEN** the new screen is displayed and I can interact with its features.

## E2E Tests
E2E tests are written to test the application as a whole. They are run using the Android testing framework.

### US011 - Game Creation and Management
**As a** user,  **I want to** create a new game, add players, and start the game,  **So that** I can play with others.

### US012 - Score Management During Game
**As a** user,  **I want to** update player scores during gameplay,  **So that** I can track progress accurately.

### US013 - External Device Control
**As a** user,  **I want to** connect to an external card device,  **So that** I can automate card shuffling/dealing.  

### US014 - Bluetooth Error Handling
**As a** user,  **I want to** receive clear connection error messages,  **So that** I can troubleshoot effectively.

### US015 - Screen Navigation
**As a** user,  **I want to** navigate between all application screens,  **So that** I can access all features seamlessly.   

### US016 - Score Reset
**As a** user,  **I want to** reset all scores during gameplay,  **So that** I can start a new round easily.  

### US017 - Multi-Resolution Support
**As a** user,  **I want to** use the app on different devices,  **So that** I have consistent experience across screens.  

## Security Tests
Security tests are written to test the security of the application. They are run using the Android testing framework.

### US018 - Permission Management
**As a** user,  **I want to** control app permissions,  **So that** my privacy is respected.  

### US019 - Bluetooth Security
**As a** user,  **I want to** ensure secure connections,  **So that** my device remains protected.

### US020 - Data Protection
**As a** user,  **I want to** keep my game data private,  **So that** others can't access it. 

## Structural Tests
Structural tests are written to test the structure of the application. They are run using the Android testing framework.


### US021 - Conditional Branch Coverage
**As a** developer,  **I want to** verify all code paths are tested,  **So that** logic works in all scenarios.  