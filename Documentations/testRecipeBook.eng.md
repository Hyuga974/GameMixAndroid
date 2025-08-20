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

| Domain         | Covered Features | Types of Tests                | Tools             |
|----------------|------------------|-------------------------------|-------------------|
| Functional     | US001-US011      | Unit, Integration, E2E        | JUnit, Espresso   |
| Security       | US012-US014      | Security Tests                | OWASP ZAP         |
| Structural     | US015            | Code Coverage                 | JaCoCo, SonarQube |

# 2. Test Scenarios

## 2.1 Functional Tests

### US001 - User Registration
**As a** user,  
**I want** to join a game with a nickname,  
**So that** I can participate in the game.

**Acceptance Criteria:**
- **GIVEN** a game in creation, **WHEN** I join with a nickname, **THEN** I am added to the game and my nickname appears in the player list.
- **GIVEN** a game in creation, **WHEN** I try to join with a nickname already taken, **THEN** I receive an error message informing me that the nickname is already in use.
- **GIVEN** a game in creation, **WHEN** I try to join without a nickname, **THEN** I receive an error message stating that a nickname is required.
- **GIVEN** a game in creation, **WHEN** I try to join with a nickname longer than 20 characters, **THEN** I receive an error message stating that the nickname is too long.
- **GIVEN** a game in creation, **WHEN** I try to join with a nickname shorter than 2 characters, **THEN** I receive an error message stating that the nickname is too short.
- **GIVEN** a game in creation, **WHEN** I try to join with a nickname containing special characters, **THEN** I receive an error message stating that special characters are not allowed.
- **GIVEN** a game in creation, **WHEN** I try to join with a nickname containing spaces, **THEN** I receive an error message stating that spaces are not allowed.

### US002 - User Deletion
**As a** user,  
**I want** to leave a game,  
**So that** I no longer participate.

**Acceptance Criteria:**
- **GIVEN** a game in creation, **WHEN** I leave the game, **THEN** I am removed from the player list.
- **GIVEN** a game in creation, **WHEN** I leave the game, **THEN** I receive a departure confirmation message.

### US003 - Game Creation
**As a** user,  
**I want** to create a game,  
**So that** I can play.

**Acceptance Criteria:**
- **GIVEN** I am on the game creation screen, **WHEN** I create a game, **THEN** the game is created and I am redirected to the game screen.
- **GIVEN** I am on the game creation screen, **WHEN** I create a game with too few players, **THEN** I receive an error message stating that there are not enough players to start.
- **GIVEN** I am on the game creation screen, **WHEN** I create a game with too many players, **THEN** I receive an error message stating that there are too many players.
- **GIVEN** I am on the game creation screen, **WHEN** I create a game, **THEN** I receive the exact number of players requested.
- **GIVEN** I am on the game creation screen, **WHEN** I create a game, **THEN** all players start with a score of 0.

### US004 - Game Creation and Management
**As a** user,  
**I want** to create a game, add players and start it,  
**So that** I can play with others.

**Acceptance Criteria:**
- **GIVEN** I am on the game creation screen, **WHEN** I select a game type (e.g., Belote), **THEN** game options appear
- **GIVEN** I have added players, **WHEN** the minimum number is reached, **THEN** the PLAY button is enabled
- **GIVEN** I have configured the game, **WHEN** I click PLAY, **THEN** the game starts and I am redirected to the game screen

### US005 - Score Editing
**As a** user,  
**I want** to update scores,  
**So that** I can manage them.

**Acceptance Criteria:**
- **GIVEN** an ongoing game, **WHEN** I update a player's score, **THEN** the score is updated in the list.
- **GIVEN** an ongoing game, **WHEN** I input a negative score, **THEN** the score is updated accordingly.
- **GIVEN** an ongoing game, **WHEN** I try to update a nonexistent player’s score, **THEN** I receive an error message saying the player doesn’t exist.

### US006 - Score Updates
**As a** user,  
**I want** to see updated scores immediately,  
**So that** I can track the game in real time.

**Acceptance Criteria:**
- **GIVEN** a game in progress, **WHEN** I update a player's score, **THEN** the new score is displayed immediately.

### US007 - Score Reset
**As a** user,  
**I want** to reset all scores during a game,  
**So that** I can easily start a new round.

**Acceptance Criteria:**
- **GIVEN** existing scores, **WHEN** I trigger a reset, **THEN** a confirmation dialog appears
- **GIVEN** I confirm the reset, **WHEN** the action completes, **THEN** all scores display 0
- **GIVEN** I cancel the reset, **WHEN** the dialog closes, **THEN** scores remain unchanged

### US008 - External Device Connection and Control
**As a** user,  
**I want** to connect to a card device,  
**So that** shuffling and dealing are automated.

**Acceptance Criteria:**
- **GIVEN** Bluetooth is enabled, **WHEN** I select a device, **THEN** pairing completes in under 10s
- **GIVEN** a successful connection, **WHEN** I send a shuffle command, **THEN** the device executes in under 3s
- **GIVEN** I am connected, **WHEN** I send a dealing command, **THEN** cards are dealt as configured

### US009 - Bluetooth Connection and Device Control
**As a** user,  
**I want** to use Bluetooth to control an external device,  
**So that** the game flow is easier.

**Acceptance Criteria:**
- **GIVEN** I want to use an external device, **WHEN** I connect via Bluetooth and send a command, **THEN** the device executes the command (shuffle or deal)

### US010 - Bluetooth Connection Error Handling
**As a** user,  
**I want** to receive clear error messages,  
**So that** I can solve Bluetooth connection issues effectively.

**Acceptance Criteria:**
- **GIVEN** Bluetooth is off, **WHEN** I try to connect, **THEN** I see activation instructions
- **GIVEN** the device is unavailable, **WHEN** I try to pair, **THEN** I receive an “Unavailable Device” alert
- **GIVEN** a mid-game disconnection, **WHEN** it happens, **THEN** I see reconnection options
- **GIVEN** I try to connect to an unavailable Bluetooth device, **WHEN** the connection fails, **THEN** I receive an explicit error message

### US011 - Application Screen Navigation
**As a** user,  
**I want** to navigate smoothly between screens,  
**So that** I can access all features seamlessly.

**Acceptance Criteria:**
- **GIVEN** I’m on any screen, **WHEN** I use navigation controls, **THEN** transitions complete in <500ms
- **GIVEN** I switch screens, **WHEN** I do so, **THEN** the transition is smooth and the previous state is preserved
- **GIVEN** I use back navigation, **WHEN** I confirm, **THEN** I return to the logical previous screen

## 2.2 Security Tests

### US012 - Permission Management
**As a** user,  
**I want** to control app permissions,  
**So that** my privacy is respected.

**Acceptance Criteria:**
- **GIVEN** I access a Bluetooth feature, **WHEN** permission is missing, **THEN** I see a justification request
- **GIVEN** I deny a permission, **WHEN** I retry, **THEN** I see a graceful fallback
- **GIVEN** I revoke a permission, **WHEN** I reopen the app, **THEN** related features are appropriately disabled

### US013 - Bluetooth Security
**As a** user,  
**I want** secure connections,  
**So that** my device is protected.

**Acceptance Criteria:**
- **GIVEN** I pair devices, **WHEN** the connection is established, **THEN** encryption is automatically enabled
- **GIVEN** unauthorized devices, **WHEN** they attempt to pair, **THEN** pairing is refused
- **GIVEN** an active connection, **WHEN** a man-in-the-middle attack is simulated, **THEN** the connection is terminated

### US014 - Data Protection
**As a** user,  
**I want** my game data to remain private,  
**So that** others can’t access it.

**Acceptance Criteria:**
- **GIVEN** stored game data, **WHEN** I inspect storage, **THEN** files are encrypted
- **GIVEN** other apps, **WHEN** they request access, **THEN** permission is denied
- **GIVEN** a rooted device, **WHEN** it accesses the app sandbox, **THEN** data remains unreadable

## 2.3 Structural Tests

### US015 - Conditional Branch Coverage
**As a** developer,  
**I want** all code paths to be tested,  
**So that** logic works in all scenarios.

**Acceptance Criteria:**
- **GIVEN** unit tests executed, **WHEN** coverage is generated, **THEN** critical components show ≥80% coverage
- **GIVEN** validation logic, **WHEN** I test edge cases, **THEN** all conditional branches are executed
- **GIVEN** uncovered branches, **WHEN** identified, **THEN** tests are added for missing paths

# 3. Validation Matrix

| ID         | TITLE                            | DESCRIPTION                                                                                           | EXPECTED RESULT                                                                                 | OBTAINED RESULT | COMMENT      |
|------------|----------------------------------|-------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------|------------------|--------------|
| US001      | User Registration                |                                                                                                       |                                                                                                  |                  |              |
| US001-01   | Valid Nickname                   | I join a game with a valid nickname                                                                   | I am added to the game and my nickname is visible in the player list                            | OK               |              |
| US001-02   | Nickname Already Taken           | I try to join a game with an already used nickname                                                    | Error message: nickname already used                                                             | OK               |              |
| US001-03   | Missing Nickname                 | I try to join a game without entering a nickname                                                      | Error message: nickname required                                                                 | OK               |              |
| US001-04   | Nickname Too Long                | I try to join a game with a nickname longer than 20 characters                                        | Error message: nickname too long                                                                 | OK               |              |
| US001-05   | Nickname Too Short               | I try to join a game with a nickname shorter than 2 characters                                        | Error message: nickname too short                                                                | OK               |              |
| US001-06   | Special Characters in Nickname   | I try to join a game with a nickname containing special characters                                    | Error message: nickname must not contain special characters                                     | OK               |              |
| US001-07   | Spaces in Nickname               | I try to join a game with a nickname containing spaces                                                | Error message: nickname must not contain spaces                                                 | OK               |              |
| US002      | User Removal                     |                                                                                                       |                                                                                                  |                  |              |
| US002-01   | Leave Game                       | I leave a game                                                                                        | I am no longer in the player list                                                                | OK               |              |
| US002-02   | Leave Confirmation               | I leave a game                                                                                        | Confirmation message of my departure                                                             | OK               |              |
| US003      | Game Creation                    |                                                                                                       |                                                                                                  |                  |              |
| US003-01   | Simple Game Creation             | I create a game from the creation screen                                                              | The game is created and I am redirected to the game screen                                       |                  |              |
| US003-02   | Too Few Players                  | I create a game with not enough players                                                               | Error message: insufficient number of players                                                    | OK               |              |
| US003-03   | Too Many Players                 | I create a game with too many players                                                                 | Error message: too many players                                                                  | OK               |              |
| US003-04   | Exact Number of Players          | I create a game with the exact number of players                                                      | I get exactly the number of players I requested                                                  | OK               |              |
| US003-05   | Initial Player Scores            | I create a game                                                                                       | All players start with a score of 0                                                              | OK               |              |
| US004      | Game Setup and Management        |                                                                                                       |                                                                                                  |                  |              |
| US004-01   | Display Game Options             | I select a game type (e.g., Belote)                                                                   | Game options appear                                                                              | OK               |              |
| US004-02   | Enable PLAY Button               | I add the required minimum number of players                                                          | The PLAY button becomes enabled                                                                  | OK               |              |
| US004-03   | Start Game                       | I click PLAY after configuring                                                                        | The game starts and I am redirected to the game screen                                           | OK               |              |
| US005      | Score Editing                    |                                                                                                       |                                                                                                  |                  |              |
| US005-01   | Simple Update                    | I update a player’s score                                                                             | The score is updated in the list                                                                 | OK               |              |
| US005-02   | Negative Score                   | I update a score with a negative value                                                                | The score is updated with the negative value                                                     | OK               |              |
| US005-03   | Nonexistent Player               | I try to update the score of a nonexistent player                                                     | Error message: player does not exist                                                             | OK               |              |
| US006      | Real-time Score Display          |                                                                                                       |                                                                                                  |                  |              |
| US006-01   | Real-time Update                 | I update a score                                                                                      | The score is immediately displayed                                                               | OK               |              |
| US007      | Score Reset                      |                                                                                                       |                                                                                                  |                  |              |
| US007-01   | Confirmation Dialog              | I trigger a reset                                                                                     | A confirmation dialog appears                                                                    | OK               |              |
| US007-02   | Reset Confirmed                  | I confirm the reset                                                                                   | All scores display 0                                                                             | OK               |              |
| US007-03   | Reset Cancelled                  | I cancel the reset                                                                                    | Scores remain unchanged                                                                          | OK               |              |
| US008      | Connection to External Device    |                                                                                                       |                                                                                                  |                  |              |
| US008-01   | Fast Pairing                     | I select a Bluetooth device                                                                           | Pairing completes in less than 10 seconds                                                        | NT               |              |
| US008-02   | Shuffle Command                  | I am connected and send a shuffle command                                                             | The device executes it in under 3 seconds                                                        | NT               |              |
| US008-03   | Deal Command                     | I am connected and send a deal command                                                                | Cards are dealt as configured                                                                    | NT               |              |
| US009      | Simplified Bluetooth Control     |                                                                                                       |                                                                                                  |                  |              |
| US009-01   | Full Bluetooth Command           | I connect via Bluetooth and send a command                                                            | The device executes the command (shuffle or deal)                                                | NT               |              |
| US010      | Bluetooth Error Handling         |                                                                                                       |                                                                                                  |                  |              |
| US010-01   | Bluetooth Disabled               | I try to connect with Bluetooth turned off                                                            | Instruction to enable Bluetooth                                                                  | NT               |              |
| US010-02   | Device Unavailable               | I try to pair with an unavailable device                                                              | Alert: "Device unavailable"                                                                      | NT               |              |
| US010-03   | Mid-game Disconnection           | A disconnection occurs during the game                                                                | Reconnection options are visible                                                                 | NT               |              |
| US010-04   | Connection Failed                | Connecting to an unavailable Bluetooth device fails                                                   | Explicit error message                                                                           | NT               |              |
| US011      | Screen Navigation                |                                                                                                       |                                                                                                  |                  |              |
| US011-01   | Fast Transitions                 | I navigate between app screens                                                                        | Transitions take less than 500ms                                                                 | NT               |              |
| US011-02   | State Preservation               | I navigate to another screen                                                                          | Smooth transition and previous state is preserved                                                | NT               |              |
| US011-03   | Logical Back Navigation          | I use back navigation                                                                                 | I return to the previous logical screen                                                          | NT               |              |
| US012      | Permission Management            |                                                                                                       |                                                                                                  |                  |              |
| US012-01   | Permission Justification         | I attempt to use Bluetooth without permission                                                         | A justification request is displayed                                                             | NT               |              |
| US012-02   | Graceful Degradation             | I deny a permission and retry                                                                         | Action fails with an acceptable visual fallback                                                  | NT               |              |
| US012-03   | Revocation and Relaunch          | I revoke a permission and reopen the app                                                              | Corresponding features are disabled                                                              | NT               |              |
| US013      | Bluetooth Security               |                                                                                                       |                                                                                                  |                  |              |
| US013-01   | Encryption Enabled               | I pair devices via Bluetooth                                                                          | Encryption is automatically enabled                                                              | NT               |              |
| US013-02   | Unauthorized Devices Denied      | Unauthorized devices attempt to connect                                                               | Connection is denied                                                                             | NT               |              |
| US013-03   | Network Attack Simulation        | A man-in-the-middle attack is simulated                                                               | Bluetooth connection is interrupted                                                              | NT               |              |
| US014      | Data Protection                  |                                                                                                       |                                                                                                  |                  |              |
| US014-01   | File Encryption                  | I inspect stored data files                                                                           | Files are encrypted                                                                              | NT               |              |
| US014-02   | Deny Access to Other Apps        | Another app requests access to the data                                                               | Permission is denied                                                                             | NT               |              |
| US014-03   | Rooted Device                    | A rooted device tries to access the app’s sandbox                                                     | Data remains unreadable                                                                          | NT               |              |
| US015      | Branch Coverage                  |                                                                                                       |                                                                                                  |                  |              |
| US015-01   | ≥80% Coverage                    | I run unit tests                                                                                      | Critical components show ≥80% coverage                                                           | NT               |              |
| US015-02   | Edge Case Testing                | I test conditional logic with edge cases                                                              | All branches are covered                                                                         | NT               |              |
| US015-03   | Add Missing Tests                | Uncovered branches are identified                                                                     | Tests are added                                                                                  | NT               |              |
