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

### US011 - Game Creation and Management
**As a** user,  
**I want to** create a new game, add players, and start the game,  
**So that** I can play with others.  

**Acceptance criteria:**  
- **GIVEN** I'm on the game creation screen, **WHEN** I select a game type (e.g., Belote), **THEN** the game options appear  
- **GIVEN** I've added players, **WHEN** I reach the minimum required players, **THEN** the PLAY button becomes enabled  
- **GIVEN** I've configured the game, **WHEN** I click PLAY, **THEN** the game starts and I'm redirected to the game screen  

### US012 - Score Management During Game
**As a** user,  
**I want to** update player scores during gameplay,  
**So that** I can track progress accurately.  

**Acceptance criteria:**  
- **GIVEN** a game is ongoing, **WHEN** I access the score editor, **THEN** all players' scores are editable  
- **GIVEN** I modify a score, **WHEN** I save changes, **THEN** the UI updates instantly  
- **GIVEN** I enter an invalid score, **WHEN** I attempt to save, **THEN** I receive immediate validation feedback  

### US013 - External Device Control
**As a** user,  
**I want to** connect to an external card device,  
**So that** I can automate card shuffling/dealing.  

**Acceptance criteria:**  
- **GIVEN** Bluetooth is enabled, **WHEN** I select a device, **THEN** pairing completes within 10s  
- **GIVEN** a successful connection, **WHEN** I send shuffle command, **THEN** the device executes within 3s  
- **GIVEN** I'm connected, **WHEN** I send deal command, **THEN** cards distribute as configured  

### US014 - Bluetooth Error Handling
**As a** user,  
**I want to** receive clear connection error messages,  
**So that** I can troubleshoot effectively.  

**Acceptance criteria:**  
- **GIVEN** Bluetooth is disabled, **WHEN** I attempt connection, **THEN** I see activation instructions  
- **GIVEN** the device is unavailable, **WHEN** I attempt pairing, **THEN** I receive "Device unavailable" alert  
- **GIVEN** connection drops mid-game, **WHEN** failure occurs, **THEN** I see reconnect options  

### US015 - Screen Navigation
**As a** user,  
**I want to** navigate between all application screens,  
**So that** I can access all features seamlessly.  

**Acceptance criteria:**  
- **GIVEN** I'm on any screen, **WHEN** I use navigation controls, **THEN** transitions complete in <500ms  
- **GIVEN** I navigate away, **WHEN** I return, **THEN** previous state is preserved  
- **GIVEN** I use back navigation, **WHEN** I confirm, **THEN** I return to logical previous screen  

### US016 - Score Reset
**As a** user,  
**I want to** reset all scores during gameplay,  
**So that** I can start a new round easily.  

**Acceptance criteria:**  
- **GIVEN** scores exist, **WHEN** I trigger reset, **THEN** confirmation dialog appears  
- **GIVEN** I confirm reset, **WHEN** action completes, **THEN** all scores show 0  
- **GIVEN** I cancel reset, **WHEN** dialog closes, **THEN** scores remain unchanged  

### US017 - Multi-Resolution Support
**As a** user,  
**I want to** use the app on different devices,  
**So that** I have consistent experience across screens.  

**Acceptance criteria:**  
- **GIVEN** I'm on mobile (e.g., 1080x1920), **WHEN** I view UI elements, **THEN** no cropping occurs  
- **GIVEN** I'm on tablet (e.g., 2560x1600), **WHEN** I use layouts, **THEN** space utilization adapts  
- **GIVEN** I rotate device, **WHEN** orientation changes, **THEN** UI reorganizes correctly  

### US018 - Permission Management
**As a** user,  
**I want to** control app permissions,  
**So that** my privacy is respected.  

**Acceptance criteria:**  
- **GIVEN** I access Bluetooth feature, **WHEN** permission missing, **THEN** I see justification prompt  
- **GIVEN** I deny permission, **WHEN** retrying action, **THEN** I see graceful degradation  
- **GIVEN** I revoke permission, **WHEN** reopening app, **THEN** features disable appropriately  

### US019 - Bluetooth Security
**As a** user,  
**I want to** ensure secure connections,  
**So that** my device remains protected.  

**Acceptance criteria:**  
- **GIVEN** I pair devices, **WHEN** establishing connection, **THEN** encryption activates automatically  
- **GIVEN** unauthorized devices, **WHEN** they attempt pairing, **THEN** they receive connection refused  
- **GIVEN** active connection, **WHEN** man-in-middle attack occurs, **THEN** connection terminates  

### US020 - Data Protection
**As a** user,  
**I want to** keep my game data private,  
**So that** others can't access it.  

**Acceptance criteria:**  
- **GIVEN** game data is stored, **WHEN** I inspect storage, **THEN** files are encrypted  
- **GIVEN** other apps, **WHEN** they request access, **THEN** they receive permission denied  
- **GIVEN** rooted devices, **WHEN** accessing app sandbox, **THEN** data remains unreadable  

## Structural Tests
Structural tests are written to test the structure of the application. They are run using the Android testing framework.


### US021 - Conditional Branch Coverage
**As a** developer,  
**I want to** verify all code paths are tested,  
**So that** logic works in all scenarios.  

**Acceptance criteria:**  
- **GIVEN** unit tests run, **WHEN** coverage generated, **THEN** critical components show ≥80% coverage  
- **GIVEN** validation logic, **WHEN** testing edge cases, **THEN** all condition branches execute  
- **GIVEN** uncovered branches, **WHEN** identified, **THEN** tests are added for missing paths