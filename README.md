# GameMixAndroid

This project is an Android app for managing multiplayer games, with potential IoT integration (e.g., Bluetooth-controlled card shufflers).

## Table of Contents
- [Work in Progress](#work-in-progress)
- [Tests](#tests)
  - [Unit Tests](#unit-tests)
  - [Integration Tests](#integration-tests)
  - [E2E Tests](#e2e-tests)
  - [Security Tests](#security-tests)
  - [Structural Tests](#structural-tests)
- [Setting Up Deployment and Testing Environments with Performance and Quality Monitoring Tools](#setting-up-deployment-and-testing-environments-with-performance-and-quality-monitoring-tools)
- [Quality and Performance Indicators](#quality-and-performance-indicators)
- [Review Process and Non-Conformity Management](#review-process-and-non-conformity-management)
- [Automated Reports](#automated-reports)
- [Tools Ensuring Tracking](#tools-ensuring-tracking)
- [Development Team](#development-team)

# Work in Progress
See you soon for more things 😉  
Currently working on the CI/CD pipeline to automate build and test processes.

# Tests

## Unit Tests
Unit tests verify individual functionalities such as validation, data integrity, and single component behavior.

### US001 - User Registration
- Valid pseudonym registration
- Pseudonym already used
- Empty pseudonym
- Too short / too long pseudonym
- Pseudonym with special characters or spaces

### US002 - User Removal
- User leaves a game and disappears from player list
- Confirmation message is shown on exit

### US003 - Game Creation
- Create a game and be redirected to the game screen
- Handle too few or too many players
- Players initialized with score = 0

### US004 - Game Setup & Start
- Game options displayed after selecting a game type
- Start button activation with minimum players
- Game starts and user is redirected

## Integration Tests
Integration tests validate the interaction between components: game engine, UI, state, Bluetooth, etc.

### US005 - Score Editing
- Update a player's score
- Handle negative scores
- Handle updates for nonexistent players

### US006 - Real-Time Score Display
- Scores update instantly in the UI

### US007 - Score Reset
- Reset confirmation dialog
- Scores reset to 0 on confirmation
- Scores remain unchanged on cancel

### US008 - Bluetooth Device Connection
- Pair with external card device in <10s
- Shuffle command executes in <3s
- Cards are dealt as configured

### US009 - Bluetooth Error Handling
- Bluetooth off: instructions shown
- Unavailable device: alert message
- Mid-game disconnect: reconnection options
- Explicit error on connection failure

### US010 - Navigation
- Transition between screens in <500ms
- State preserved on screen change
- Back navigation returns to logical previous screen

## E2E Tests
E2E tests simulate full user flows from game creation to interaction with external devices.

### US011 - Full Game Management
- Create a new game, add players, and start it

### US012 - Score Management In-Game
- Track and modify scores during gameplay

### US013 - External Device Control
- Connect and control card-shuffling hardware

### US014 - Bluetooth Resilience
- Clear error feedback for all connection issues

### US015 - Multi-Resolution Support *(optional / UI-level)*
- Responsive layout on tablets and rotated screens

## Security Tests
Security tests ensure privacy, data safety, and robust permission handling.

### US012 - Permission Handling
- Request justifications for missing permissions
- Graceful degradation on refusal
- Feature deactivation after revocation

### US013 - Bluetooth Security
- Encrypted connections during pairing
- Reject unauthorized devices
- Terminate on man-in-the-middle detection

### US014 - Data Protection
- Stored game data is encrypted
- Other apps cannot access the sandbox
- Data stays unreadable on rooted devices

**OWASP Top 10 Compliance**:  
Relevant OWASP Top 10 risks are evaluated and mitigated:
- **A01:2021 – Broken Access Control** → Scoped permissions & validation before actions
- **A02:2021 – Cryptographic Failures** → Encrypted storage and Bluetooth pairing
- **A03:2021 – Injection** → No dynamic SQL; parameterized queries only
- **A05:2021 – Security Misconfiguration** → Secure defaults for Gradle builds and permissions
- **A07:2021 – Identification and Authentication Failures** → Pseudonym validation and session handling
- **A09:2021 – Security Logging and Monitoring Failures** → CI/CD logs and crash reports stored securely

## Structural Tests
These verify internal code logic: branching, conditions, and edge case handling.

### US015 - Conditional Branch Coverage
- ≥80% code coverage on critical modules
- All branches tested (including edge cases)
- Add missing tests for uncovered paths

## Setting Up Deployment and Testing Environments with Performance and Quality Monitoring Tools

For the **GameMix Android** project, a set of tools and technologies has been selected to ensure a structured, traceable, and high-quality development process.

---

### 1. Development Environment

Development is carried out using **Android Studio**, on systems running **Windows 11** and **Manjaro Linux**.

The codebase is written in **Kotlin 1.9.20**, compiled with **Java 20**, and built using **Gradle 8.11.1**.

The project relies on modern Android libraries, including **Jetpack Compose**, **Navigation**, and **Datastore**.

---

### 2. Tools and Technologies

- **Version Control**: Git (hosted on GitHub)
- **Continuous Integration**: GitHub Actions (basic structure implemented, deployment in progress)
- **Code Quality Monitoring**: SonarQube (static analysis and maintainability tracking)
- **Test Coverage**: JaCoCo (currently being configured for unit testing coverage)
- **Containerization (planned)**: Docker (for automated testing environments)

#### Quality & Coverage Badges:

[![Android CI](https://github.com/Hyuga974/GameMixAndroid/actions/workflows/android.yml/badge.svg)](https://github.com/Hyuga974/GameMixAndroid/actions/workflows/android.yml)  
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Hyuga974_GameMixAndroid&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Hyuga974_GameMixAndroid)  
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Hyuga974_GameMixAndroid&metric=coverage)](https://sonarcloud.io/summary/new_code?id=Hyuga974_GameMixAndroid)  
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=Hyuga974_GameMixAndroid&metric=bugs)](https://sonarcloud.io/summary/new_code?id=Hyuga974_GameMixAndroid)  
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=Hyuga974_GameMixAndroid&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=Hyuga974_GameMixAndroid)

---

### 3. Deployment Pipeline (in progress)

The goal is to implement a CI/CD pipeline using **GitHub Actions**, with the following stages:

1. **Build the application** using `./gradlew build`
2. **Run unit tests** with `./gradlew testDebugUnitTest`
3. **Generate test coverage reports** via JaCoCo
4. **Perform SonarQube analysis**
5. *(optional / future)* **Deploy APK** to Firebase App Distribution or a Docker-based emulator

This pipeline is still under development but follows an incremental and scalable approach.

---

### 4. Quality and Performance Objectives

- Code is continuously analyzed through **SonarQube** to detect complexity issues, duplication, and convention violations.
- **JaCoCo integration** (in progress) will allow tracking **unit test coverage**, especially for critical business logic.
- Performance monitoring tools like **Android Profiler** or **Firebase Performance Monitoring** may be added later for runtime analysis.
- **Accessibility Compliance**: UI components are tested against [WCAG 2.1 AA](https://www.w3.org/TR/WCAG21/) guidelines using automated accessibility scans (Accessibility Scanner) and manual validation on Android devices.

# Quality and Performance Indicators

- **Code Coverage**: Target ≥ 80% on critical modules (measured via JaCoCo)
- **Cyclomatic Complexity**: Target < 10 per method (tracked in SonarQube)
- **Code Duplication**: Target < 5% (SonarQube)
- **Bugs & Vulnerabilities**: 0 blocker in production
- **UI Performance**: Navigation between screens < 500 ms
- **Bluetooth Stability**: Successful connection rate > 95%
- **Accessibility Compliance**: ≥ 95% of UI screens passing WCAG 2.1 AA automated checks

---

# Review Process and Non-Conformity Management

- **Pull Requests required** for all changes, with manual review and SonarQube analysis before merge
- **CI test failure procedure**:
  1. Identify failing commit via GitHub Actions logs
  2. Fix in a dedicated branch
  3. Re-run tests before merging
- **Post-deployment bug**: Create a GitHub issue with the `bug` label and link to the impacted user story

---

# Automated Reports

- **JaCoCo report**: `build/reports/jacoco/test/html/index.html`
- **SonarQube report**: Available on [SonarCloud](https://sonarcloud.io/summary/new_code?id=Hyuga974_GameMixAndroid)
- **Build artifacts**: APK downloadable from the GitHub Actions *Actions* tab

---

# Tools Ensuring Tracking

- **GitHub Actions**: Automates build, test, and static analysis
- **SonarQube**: Code quality and security monitoring
- **JaCoCo**: Unit test coverage tracking
- *(planned)* **Firebase Performance Monitoring**: Real-world performance metrics
- **Accessibility Scanner**: Automated WCAG compliance checks

# Development Team

This project was developed solo by [**Costa Reype**](#https://github.com/Hyuga974), who assumed all key roles throughout the development process — from planning to deployment.
