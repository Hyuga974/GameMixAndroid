
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

- **Unit Tests:** Verify that individual components of the software function correctly in isolation from the rest of the system.

- **Integration Tests:** Verify that different modules or services used by your application work well together. These tests focus on checking the data flow between different parts of the system to ensure they integrate as expected.

- **End-to-End Tests:** Verify that the entire application flow works as expected from start to finish, simulating real user scenarios. These tests ensure that the system behaves correctly as a whole and meets the business requirements.

- **Security Tests:** Verify that the software is secure against potential threats and vulnerabilities.



## Functional Tests

| **Test ID** | **Objective** | **Steps** | **Expected Result** | **Status** |
|------------|---------------|-----------|----------------------|------------|
| FCT-001 | Add a player | In setGameScreen > Click "add" icon > Enter a name | Player appears in the list | ⬜ To Do |
| FCT-002 | Remove a player | In setGameScreen > Click the “remove” icon next to a player | Player is removed from the list | ⬜ To Do |
| FCT-003 | Exceed player limit | Try adding 5 players in belote mode who is limited to 4 players | Action blocked | ⬜ To Do |
| FCT-004 | Increment score | Click Score in game | Menu with all players score editable | ⬜ To Do |
| FCT-005 | Reset scores | Click "Reset" inside Score menu | All scores reset to 0 | ⬜ To Do |



## Security Tests

| **Test ID** | **Objective**  |  **Steps** | **Expected Result** | **Status** |
|------------|---------------|-----------|----------------------|------------|
| SEC-001 | Secure BLE connection | Enable Bluetooth > Scan > Connect to device | Device connects only after system permission | ⬜ To Do |
| SEC-002 | Permission handling | Launch the app for the first time | App  Bluetooth permissions | ⬜ To Do |
| SEC-003 | Reconnection handling | Turn off the BLE device and turn it back on | App shows appropriate error | ⬜ To Do |