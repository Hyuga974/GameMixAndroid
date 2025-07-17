# Recipe Book - GameMixAndroid

* **Version**: 1.0  
* **Date**: 07/16/2025
* **Quality Manager**: REYPE Costa

## Table of Contents

- [1. Test Plan](#1-test-plan)
- [2. Test Scenarios](#2-test-scenarios)
  - [2.1 Functional Tests](#21-functional-tests)
  - [2.2 Security Tests](#22-security-tests)
  - [2.3 Structural Tests](#23-structural-tests)
- [3. Validation Matrix](#3-validation-matrix)

# 1. Test Plan

| Domain      | Covered Features      | Test Types                  | Tools            |
|-------------|----------------------|-----------------------------|------------------|
| Functional  | US001-US015          | Unit, Integration, E2E      | JUnit, Espresso  |
| Security    | US016-US018          | Security tests              | OWASP ZAP        |
| Structural  | US019                | Code coverage               | JaCoCo, SonarQube|

# 2. Test Scenarios

## 2.1 Functional Tests

### US001 - User Registration
**As a** user,  
**I want to** join a game with a nickname,  
**So that** I can participate in the game.  

**Acceptance Criteria:**  
  - **GIVEN** a game being created, **WHEN** I join the game with a nickname, **THEN** I am added to the game and my nickname is visible in the player list.  
  - **GIVEN** a game being created, **WHEN** I try to join with an already taken nickname, **THEN** I receive an error message stating the nickname is already used.  
  - **GIVEN** a game being created, **WHEN** I try to join without a nickname, **THEN** I receive an error message stating the nickname is required.  
  - **GIVEN** a game being created, **WHEN** I try to join with a nickname longer than 20 characters, **THEN** I receive an error message stating the nickname is too long.  
  - **GIVEN** a game being created, **WHEN** I try to join with a nickname shorter than 2 characters, **THEN** I receive an error message stating the nickname is too short.  
  - **GIVEN** a game being created, **WHEN** I try to join with a nickname containing special characters, **THEN** I receive an error message stating the nickname must not contain special characters.  
  - **GIVEN** a game being created, **WHEN** I try to join with a nickname containing spaces, **THEN** I receive an error message stating the nickname must not contain spaces.  

### US002 - User Removal
**As a** user,  
**I want to** leave a game,  
**So that** I no longer participate in the game.  

**Acceptance Criteria:**  
  - **GIVEN** a game being created, **WHEN** I leave the game, **THEN** I am no longer in the player list.  
  - **GIVEN** a game being created, **WHEN** I leave the game, **THEN** I receive a confirmation message of my departure.  

### US003 - Game Creation 
**As a** user,  
**I want to** create a game,  
**So that** I can play a game.  

**Acceptance Criteria:**  
  - **GIVEN** I am on the game creation screen, **WHEN** I create a game, **THEN** the game is created and I am redirected to the game screen.  
  - **GIVEN** I am on the game creation screen, **WHEN** I create a game with too few participants, **THEN** I receive an error message stating the number of players is insufficient to start the game.  
  - **GIVEN** I am on the game creation screen, **WHEN** I create a game with too many participants, **THEN** I receive an error message stating the number of players is too high to start the game.  
  - **GIVEN** I am on the game creation screen, **WHEN** I create a game, **THEN** I get as many players as I requested.  
  - **GIVEN** I am on the game creation screen, **WHEN** I create a game, **THEN** all players start with a score of 0.  

### US004 - Game Creation and Management
**As a** user,  
**I want to** create a new game, add players, and start the game,  
**So that** I can play with others.  

**Acceptance Criteria:**  
  - **GIVEN** I am on the game creation screen, **WHEN** I select a game type (e.g., Belote), **THEN** the game options appear  
  - **GIVEN** I have added players, **WHEN** I reach the minimum required number of players, **THEN** the PLAY button becomes enabled  
  - **GIVEN** I have configured the game, **WHEN** I click PLAY, **THEN** the game starts and I am redirected to the game screen  

### US005 - **Add a Player to a Game**
**As a** user,  
**I want to** add a new player to an ongoing game,  
**So that** others can join the game.

**Acceptance Criteria:**  
  - **GIVEN** a game in progress, **WHEN** I add a new player, **THEN** the player list is updated in real time in the interface.

### US006 - **Player Limit and Error Handling**
**As a** user,  
**I want to** be informed when the player limit is reached,  
**So that** I cannot add more players than allowed.

**Acceptance Criteria:**  
  - **GIVEN** the player limit is reached in a game, **WHEN** I try to add an extra player, **THEN** I receive an error message stating the limit is exceeded and the list is not updated.

### US007 - Score Editing
**As a** user,  
**I want to** update scores,  
**So that** I can manage them.  

**Acceptance Criteria:**  
  - **GIVEN** a game in progress, **WHEN** I update a player's score, **THEN** the player's score is updated in the player list.  
  - **GIVEN** a game in progress, **WHEN** I update a player's score with a negative value, **THEN** I receive an error message stating the score cannot be negative.  
  - **GIVEN** a game in progress, **WHEN** I try to update the score of a non-existent player, **THEN** I receive an error message stating the player does not exist. 

### US008 - Score Management During a Game
**As a** user,  
**I want to** update player scores during a game,  
**So that** I can accurately track progress.  

**Acceptance Criteria:**  
  - **GIVEN** a game in progress, **WHEN** I access the score editor, **THEN** all player scores are editable  
  - **GIVEN** I modify a score, **WHEN** I save the changes, **THEN** the UI updates instantly  
  - **GIVEN** I enter an invalid score, **WHEN** I try to save it, **THEN** I receive immediate validation feedback  

### US009 - **Score Update**
**As a** user,  
**I want to** see updated scores immediately,  
**So that** I can track game progress in real time.

**Acceptance Criteria:**  
  - **GIVEN** a game in progress with players, **WHEN** I update a player's score, **THEN** the new score is displayed immediately in the interface.

### US010 - Score Reset
**As a** user,  
**I want to** reset all scores during a game,  
**So that** I can easily start a new round.  

**Acceptance Criteria:**  
  - **GIVEN** existing scores, **WHEN** I trigger the reset, **THEN** a confirmation dialog appears  
  - **GIVEN** I confirm the reset, **WHEN** the action is completed, **THEN** all scores show 0  
  - **GIVEN** I cancel the reset, **WHEN** the dialog closes, **THEN** scores remain unchanged  


### US011 - Connection and Control of an External Device
**As a** user,  
**I want to** connect to an external card device,  
**So that** shuffling and dealing cards is automated.  

**Acceptance Criteria:**  
  - **GIVEN** Bluetooth is enabled, **WHEN** I select a device, **THEN** pairing completes in less than 10s  
  - **GIVEN** a successful connection, **WHEN** I send a shuffle command, **THEN** the device executes in less than 3s  
  - **GIVEN** I am connected, **WHEN** I send a deal command, **THEN** cards are dealt as configured  

### US012 - **Bluetooth Connection and Device Control**

**As a** user,  
**I want to** use Bluetooth to control an external device,  
**So that** the game runs smoothly.

**Acceptance Criteria:**  
  - **GIVEN** I want to use an external device, **WHEN** I connect via Bluetooth and send a command, **THEN** the external device executes the command (shuffle or deal cards).

### US013 - Bluetooth Connection Error Handling
**As a** user,  
**I want to** receive clear error messages,  
**So that** I can efficiently resolve Bluetooth connection issues.  

**Acceptance Criteria:**  
  - **GIVEN** Bluetooth is disabled, **WHEN** I try to connect, **THEN** I see activation instructions  
  - **GIVEN** the device is unavailable, **WHEN** I try to pair, **THEN** I receive an "Device unavailable" alert  
  - **GIVEN** a mid-game disconnection, **WHEN** a failure occurs, **THEN** I see reconnection options  
  - **GIVEN** I try to connect to an unavailable Bluetooth device, **WHEN** the connection fails, **THEN** I receive an explicit error message.

### US014 - Multi-Resolution Support
**As a** user,  
**I want to** use the app on different devices,  
**So that** I have a consistent experience on all screens.  

**Acceptance Criteria:**  
  - **GIVEN** I am on mobile (e.g., 1080x1920), **WHEN** I view UI elements, **THEN** no cropping occurs  
  - **GIVEN** I am on tablet (e.g., 2560x1600), **WHEN** I use layouts, **THEN** space usage adapts  
  - **GIVEN** I rotate the device, **WHEN** orientation changes, **THEN** the UI reorganizes correctly  

### US015 - Navigation Between App Screens
**As a** user,  
**I want to** smoothly navigate between all app screens,  
**So that** I can access all features seamlessly and easily switch between functionalities.

**Acceptance Criteria:**  
  - **GIVEN** I am on any screen, **WHEN** I use navigation controls, **THEN** transitions complete in <500ms.
  - **GIVEN** I navigate to another screen, **WHEN** I switch screens, **THEN** the transition is smooth and the previous state is preserved.
  - **GIVEN** I use back navigation, **WHEN** I confirm, **THEN** I return to the logical previous screen.


## 2.2 Security Tests

### US016 - Permission Management
**As a** user,  
**I want to** control app permissions,  
**So that** my privacy is respected.  


**Acceptance Criteria:**  
- **GIVEN** I access a Bluetooth feature, **WHEN** permission is missing, **THEN** I see a justification request  
- **GIVEN** I deny a permission, **WHEN** I retry the action, **THEN** I see graceful degradation  
- **GIVEN** I revoke a permission, **WHEN** I reopen the app, **THEN** features are disabled appropriately  

### US017 - Bluetooth Security
**As a** user,  
**I want to** ensure secure connections,  
**So that** my device remains protected.  

**Acceptance Criteria:**  
- **GIVEN** I pair devices, **WHEN** I establish the connection, **THEN** encryption activates automatically  
- **GIVEN** unauthorized devices, **WHEN** they try to pair, **THEN** they receive a connection refusal  
- **GIVEN** an active connection, **WHEN** a man-in-the-middle attack occurs, **THEN** the connection terminates  

### US018 - Data Protection
**As a** user,  
**I want to** keep my game data private,  
**So that** others cannot access it.  

**Acceptance Criteria:**  
- **GIVEN** game data is stored, **WHEN** I inspect storage, **THEN** files are encrypted  
- **GIVEN** other apps, **WHEN** they request access, **THEN** they receive a permission denial  
- **GIVEN** rooted devices, **WHEN** they access the app sandbox, **THEN** data remains unreadable  

## 2.3 Structural Tests


### US019 - Conditional Branch Coverage
**As a** developer,  
**I want to** verify all code paths are tested,  
**So that** logic works in all scenarios.  

**Acceptance Criteria:**  
- **GIVEN** unit tests are run, **WHEN** coverage is generated, **THEN** critical components show ≥80% coverage  
- **GIVEN** validation logic, **WHEN** I test edge cases, **THEN** all conditional branches execute  
- **GIVEN** uncovered branches, **WHEN** they are identified, **THEN** tests are added for missing paths


# 3. Validation Matrix

| ID | TITLE | DESCRIPTION | EXPECTED RESULT | ACTUAL RESULT | COMMENT |
|----|-------|-------------|-----------------|---------------|---------|
| US001 | User Registration | | | | |
| US001 - 01 | | As a user, I want to join a game with a nickname, so that I can participate in the game | I am added to the game and my nickname is visible in the player list | NT | |
| US001 - 02 | | As a user, I try to join the game with an already taken nickname, to ensure my nickname is unique | I receive an error message stating the nickname is already used | NT | |
| US001 - 03 | | As a user, I try to join the game without a nickname, to ensure I have a nickname | I receive an error message stating the nickname is required | NT | |
| US001 - 04 | | As a user, I try to join the game with a nickname longer than 20 characters, to respect character limits | I receive an error message stating the nickname is too long | NT | |
| US001 - 05 | | As a user, I try to join the game with a nickname shorter than 2 characters, to respect character limits | I receive an error message stating the nickname is too short | NT | |
| US001 - 06 | | As a user, I try to join the game with a nickname containing special characters, to respect format rules | I receive an error message stating the nickname must not contain special characters | NT | |
| US001 - 07 | | As a user, I try to join the game with a nickname containing spaces, to respect format rules | I receive an error message stating the nickname must not contain spaces | NT | |
| US002 | User Removal | | | | |
| US002 - 01 | | As a user, I want to leave a game, so that I no longer participate in the game | I am no longer in the player list | NT | |
| US002 - 02 | | As a user, I leave the game, to get confirmation of my action | I receive a confirmation message of my departure | NT | |
| US003 | Game Creation | | | | |
| US003 - 01 | | As a user, I want to create a game, so that I can play a game | The game is created and I am redirected to the game screen | NT | |
| US003 - 02 | | As a user, I create a game with too few participants, to respect the minimum number of players | I receive an error message stating the number of players is insufficient to start the game | NT | |
| US003 - 03 | | As a user, I create a game with too many participants, to respect the maximum number of players | I receive an error message stating the number of players is too high to start the game | NT | |
| US003 - 04 | | As a user, I create a game, to have the desired number of players | I get as many players as I requested | NT | |
| US003 - 05 | | As a user, I create a game, so all players start with an initial score | All players start with a score of 0 | NT | |
| US004 | Game Creation and Management | | | | |
| US004 - 01 | | As a user, I want to create a new game, add players, and start the game, so that I can play with others | The game options appear | NT | |
| US004 - 02 | | As a user, I have added players and reached the minimum required, so that I can start the game | The PLAY button becomes enabled | NT | |
| US004 - 03 | | As a user, I have configured the game and click PLAY, so that I start the game | The game starts and I am redirected to the game screen | NT | |
| US005 | Add a Player to a Game | | | | |
| US005 - 01 | | As a user, I add a new player to an ongoing game, so that others can join the game | The player list is updated in real time in the interface | NT | |
| US006 | Player Limit and Error Handling | | | | |
| US006 - 01 | | As a user, I try to add an extra player when the limit is reached, to respect the player limit | I receive an error message stating the limit is exceeded and the list is not updated | NT | |
| US007 | Score Editing | | | | |
| US007 - 01 | | As a user, I want to update scores, so that I can manage them | The player's score is updated in the player list | NT | |
| US007 - 02 | | As a user, I update a player's score with a negative value, to respect score rules | I receive an error message stating the score cannot be negative | NT | |
| US007 - 03 | | As a user, I try to update the score of a non-existent player, to ensure the player exists | I receive an error message stating the player does not exist | NT | |
| US008 | Score Management During a Game | | | | |
| US008 - 01 | | As a user, I want to update player scores during a game, so that I can accurately track progress | All player scores are editable | NT | |
| US008 - 02 | | As a user, I modify a score and save changes, to see the changes | The UI updates instantly | NT | |
| US008 - 03 | | As a user, I enter an invalid score and try to save it, to respect score rules | I receive immediate validation feedback | NT | |
| US009 | Score Update | | | | |
| US009 - 01 | | As a user, I want to see updated scores immediately, so that I can track game progress in real time | The new score is displayed immediately in the interface | NT | |
| US010 | Score Reset | | | | |
| US010 - 01 | | As a user, I want to reset all scores during a game, so that I can easily start a new round | A confirmation dialog appears | NT | |
| US010 - 02 | | As a user, I confirm the reset, to reset the scores | All scores show 0 | NT | |
| US010 - 03 | | As a user, I cancel the reset, to keep current scores | Scores remain unchanged | NT | |
| US011 | Connection and Control of an External Device | | | | |
| US011 - 01 | | As a user, I want to connect to an external card device, so that shuffling and dealing cards is automated | Pairing completes in less than 10s | NT | |
| US011 - 02 | | As a user, I am connected and send a shuffle command, to shuffle the cards | The device executes in less than 3s | NT | |
| US011 - 03 | | As a user, I am connected and send a deal command, to deal the cards | Cards are dealt as configured | NT | |
| US012 | Bluetooth Connection and Device Control | | | | |
| US012 - 01 | | As a user, I want to use Bluetooth to control an external device, so that the game runs smoothly | The external device executes the command (shuffle or deal cards) | NT | |
| US013 | Bluetooth Connection Error Handling | | | | |
| US013 - 01 | | As a user, I want to receive clear error messages, so that I can efficiently resolve Bluetooth connection issues | I see activation instructions | NT | |
| US013 - 02 | | As a user, the device is unavailable and I try to pair, to handle connection errors | I receive an "Device unavailable" alert | NT | |
| US013 - 03 | | As a user, I am disconnected mid-game, to handle connection errors | I see reconnection options | NT | |
| US013 - 04 | | As a user, I try to connect to an unavailable Bluetooth device, to handle connection errors | I receive an explicit error message | NT | |
| US014 | Multi-Resolution Support | | | | |
| US014 - 01 | | As a user, I want to use the app on different devices, so that I have a consistent experience on all screens | No cropping occurs | NT | |
| US014 - 02 | | As a user, I am on tablet and use layouts, to adapt the interface | Space usage adapts | NT | |
| US014 - 03 | | As a user, I rotate the device, to adapt the interface | The UI reorganizes correctly | NT | |
| US015 | Navigation Between App Screens | | | | |
| US015 - 01 | | As a user, I want to smoothly navigate between all app screens, so that I can access all features seamlessly and easily switch between functionalities | Transitions complete in <500ms | NT | |
| US015 - 02 | | As a user, I navigate to another screen | The transition is smooth and the previous state is preserved | NT | |
| US015 - 03 | | As a user, I use back navigation and confirm | I return to the logical previous screen | NT | |

