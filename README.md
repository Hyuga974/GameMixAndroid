
# GameMixAndroid



This project is an android project with a possible connection IoT...



## Table of Contents

- [Work in Progress](#work-in-progress)

- [Tests](#tests)

  - [Functional Tests](#functional-tests)

  - [Structural Tests](#structural-tests)

  - [Security Tests](#security-tests)



# Work in Progress ....

See you soon for more things ;)



# Tests

We have three main types of tests: Security, Functional (Working), and Structural.

- **Functional Tests:** Verify that the software functions as expected from the user's perspective.

- **Structural Tests:** Verify that each component of the software functions correctly internally.

- **Security Tests:** Verify that the software is secure against potential threats and vulnerabilities.



## Functional Tests

| **Test ID** | **Objective** | **Steps** | **Expected Result** | **Status** |
|------------|---------------|-----------|----------------------|------------|
| FCT-001 | Add a player | In setGameScreen > Click "add" icon > Enter a name | Player appears in the list | ⬜ To Do |
| FCT-002 | Remove a player | In setGameScreen > Click the “remove” icon next to a player | Player is removed from the list | ⬜ To Do |
| FCT-003 | Exceed player limit | Try adding 5 players in belote mode who is limited to 4 players | Action blocked | ⬜ To Do |
| FCT-004 | Increment score | Click Score in game | Menu with all players score editable | ⬜ To Do |
| FCT-005 | Reset scores | Click "Reset" inside Score menu | All scores reset to 0 | ⬜ To Do |


## Structural Tests

| **Test ID** | **Objective** | **Verification** | **Expected Result** | **Status** |
|------------|---------------|------------------|----------------------|------------|
| STR-001 | MVVM separation | Logic is handled in ViewModel, not in UI | No business logic in composables | ⬜ To Do |
| STR-002 | State management during navigation | Navigate between screens | State is preserved through ViewModel | ⬜ To Do |


## Security Tests

| **Test ID** | **Objective**  |  **Steps** | **Expected Result** | **Status** |
|------------|---------------|-----------|----------------------|------------|
| SEC-001 | Secure BLE connection | Enable Bluetooth > Scan > Connect to device | Device connects only after system permission | ⬜ To Do |
| SEC-002 | Permission handling | Launch the app for the first time | App  Bluetooth permissions | ⬜ To Do |
| SEC-003 | Reconnection handling | Turn off the BLE device and turn it back on | App shows appropriate error | ⬜ To Do |