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

# Tests

## Unit Tests
Unit tests are written to test individual components of the application. They are run using the Android testing framework.

So we have the following unit tests:

### US001 - User Registration
  **As a** user,
  **I want to** join a game with a username,
  **So that** I can participate in the game.

  **Acceptance criteria:**
  - **GIVEN** I have a game being created, **WHEN** I join the game with a username, **THEN** I am added to the game and my username is visible in the player list.
  - **GIVEN** I have a game being created, **WHEN** I try to join the game with an already taken username, **THEN** I receive an error message informing me that the username is already taken.
  - **GIVEN** I have a game being created, **WHEN** I try to join the game without a username, **THEN** I receive an error message informing me that the username is required.
  - **GIVEN** I have a game being created, **WHEN** I try to join the game with a username longer than 20 characters, **THEN** I receive an error message informing me that the username is too long.
  - **GIVEN** I have a game being created, **WHEN** I try to join the game with a username shorter than 2 characters, **THEN** I receive an error message informing me that the username is too short.
  - **GIVEN** I have a game being created, **WHEN** I try to join the game with a username containing special characters, **THEN** I receive an error message informing me that the username must not contain special characters.
  - **GIVEN** I have a game being created, **WHEN** I try to join the game, **THEN** I receive an error message informing me that the username must not contain spaces.

### US002 - User Deleted
  **As a** user,
  **I want to** leave a game,
  **So that** I no longer participate in the game.

  **Acceptance criteria:**
  - **GIVEN** I have a game being created, **WHEN** I leave the game, **THEN** I am no longer in the player list.
  - **GIVEN** I have a game being created, **WHEN** I leave the game, **THEN** I receive a confirmation message of my departure.

### US003 - Party Creation 
  **As a** user,
  **I want to** create a game,
  **So that** I can play a game.

  **Acceptance criteria:**
  - **GIVEN** I am on the game creation screen, **WHEN** I create a game, **THEN** the game is created and I am redirected to the game screen.
  - **GIVEN** I am on the game creation screen, **WHEN** I create a game with too few players, **THEN** I receive an error message informing me that the number of players is insufficient to start the game.
  - **GIVEN** I am on the game creation screen, **WHEN** I create a game with too many players, **THEN** I receive an error message informing me that the number of players is too high to start the game.
  - **GIVEN** I am on the game creation screen, **WHEN** I create a game, **THEN** I correctly receive as many players as I requested.
  - **GIVEN** I am on the game creation screen, **WHEN** I create a game, **THEN** all players start with a score of 0.

### US004 - Score Edition
  **As a** user,
  **I want to** update scores,
  **So that** I can manage them.

  **Acceptance criteria:**
  - **GIVEN** I have an ongoing game, **WHEN** I update a player's score, **THEN** the player's score is updated in the player list.
  - **GIVEN** I have an ongoing game, **WHEN** I update a player's score with a negative value, **THEN** I receive an error message informing me that the score cannot be negative.
  - **GIVEN** I have an ongoing game, **WHEN** I try to update the score of a non-existent player, **THEN** I receive an error message informing me that the player does not exist.

## Integration Tests
Integration tests are written to test the interaction between different components of the application. They are run using the Android testing framework.

### US005 - **Adding a player to a game**
  - **GIVEN** I have an ongoing game, **WHEN** I add a new player, **THEN** the player list is updated in real-time in the interface.

### US006 - **Player limit and error handling**
  - **GIVEN** the player limit is reached in a game, **WHEN** I try to add another player, **THEN** I receive an error message informing me that the limit is exceeded and the list is not modified.

### US007 - **Score updates**
  - **GIVEN** I have an ongoing game with players, **WHEN** I update a player's score, **THEN** the new score is immediately displayed in the interface.

### US008 - **Bluetooth connection and device control**
  - **GIVEN** I want to use an external device, **WHEN** I connect via Bluetooth and send a command, **THEN** the external device executes the command (shuffling or dealing cards).

### US009 - **Bluetooth connection error handling**
  - **GIVEN** I try to connect to an unavailable Bluetooth device, **WHEN** the connection fails, **THEN** I receive a clear error message.

### US010 - **Navigation between screens**
  - **GIVEN** I am on an application screen, **WHEN** I click on a navigation button, **THEN** the new screen is displayed and I can interact with its features.

## E2E Tests
E2E tests are written to test the application as a whole. They are run using the Android testing framework.

### US011 - **Game creation and management**
  _**Scenario**_ : As a user, I want to create a new game, add players, and start the game.

  _**Steps**_:
    - Open the application and navigate to the game creation screen.
    - Select the game type (e.g., Belote).
    - Add players until reaching the minimum required number.
    - Click the "PLAY" button to start the game.
          
  _**Expected Result**_ : The game is created, players are successfully added, and the application navigates to the game screen.

### US012 - **Score management during a game**
  _**Scenario**_ : As a user, I want to update player scores during a game.

  _**Steps**_:
    - Start a game with multiple players.
    - Navigate to the score update option.
    - Update scores for each player.
    - Save changes.

  -**Expected Result**- : Player scores are updated and correctly displayed in the user interface.

### US013 - **Connection and control of an external device**
  _**Scenario**_ : As a user, I want to connect to an external device to shuffle and deal cards.

  _**Steps**_:
    - Navigate to the device connection screen.
    - Enable Bluetooth connection and select the external device.
    - Use commands to start shuffling and dealing cards.

  _**Expected Result**_ : The application successfully connects to the external device, and the shuffle and deal commands work as expected.

### US014 - **Bluetooth connection error handling**
  _**Scenario**_ : As a user, I want to receive a clear error message if the connection to the external device fails.

  _**Steps**_:
    - Navigate to the device connection screen.
    - Try to connect to an unavailable Bluetooth device.

  _**Expected Result**_ : The application displays a clear error message indicating that the connection failed.

### US015 - **Navigation between application screens**
  _**Scenario**_ : As a user, I want to navigate between different application screens to access all features.

  _**Steps**_:
    - Open the application and navigate to the home screen.
    - Click buttons to access game management screens, device connection, and game screens.

  _**Expected Result**_ : The application correctly navigates between different screens, and each screen displays appropriate information.

### US016 - **Score reset**
  _**Scenario**_ : As a user, I want to reset all player scores during a game.

  _**Steps**__ :
    - Start a game and update player scores.
    - Navigate to the score reset option.
    - Confirm the reset.

  _**Expected Result**_ : All player scores are reset to zero.

### US017 - **Screen adaptability on different resolutions**
  _**Scenario**_ : As a user, I want the application to display correctly on different devices with different screen resolutions.

  _**Steps**_ :
    - Open the application on a mobile device and a tablet.
    - Navigate through different screens.

  _**Expected Result**_ : The user interface correctly adapts to the screen resolution and remains functional.

## Security Tests
Security tests are written to test the security of the application. They are run using the Android testing framework.

### US018 - **Permission management**
  _**Scenario**_ : Verify that the application correctly requests and manages necessary permissions, such as Bluetooth access and location.

  _**Steps**_:
    - Install and open the application on an Android device.
    - Verify that the application requests necessary permissions before accessing corresponding features.
    - Deny a permission and verify that the application handles this denial correctly.

  _**Expected Result**_ : The application requests necessary permissions and correctly handles permission denials, informing the user of the consequences.

### US019 - **Bluetooth connection security**
  _**Scenario**_ : Verify that the Bluetooth connection between the application and external device is secure and cannot be intercepted by unauthorized devices.

  _**Steps**_:
        - Establish a Bluetooth connection between the application and external device.
        - Try to intercept or connect to the external device with another unauthorized device.

  _**Expected Result**_ : The Bluetooth connection is secure, and unauthorized devices cannot intercept or establish a connection.

### US020 - **Local data protection**

  _**Scenario**_ : Verify that local data, such as scores and player information, is stored securely and is not accessible by other applications.
  
  _**Steps**_:
    - Store player and score data locally.
    - Try to access this data through another application or file explorer.
  
  _**Expected Result**_ : Local data is not accessible or readable by other applications or unauthorized users.