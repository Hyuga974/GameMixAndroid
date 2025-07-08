
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
**En tant qu'** utilisateur,
**Je veux** m'ajouter à une partie via un pseudo,
**Afin de** pouvoir participer à la partie.

**Critères d'acceptation :**
- **ÉTANT DONNÉ QUE** j'ai une partie en création, **LORSQUE** je m'ajoute à la partie avec un pseudo, **ALORS** je suis ajouté à la partie et mon pseudo est visible dans la liste des joueurs.
- **ÉTANT DONNÉ QUE** j'ai une partie en création, **LORSQUE** je tente  à la partie avec un pseudo déjà utilisé, **ALORS** je reçois un message d'erreur m'informant que le pseudo est déjà pris.
- **ÉTANT DONNÉ QUE** j'ai une partie en création, **LORSQUE** je tente  à la partie sans pseudo, **ALORS** je reçois un message d'erreur m'informant que le pseudo est obligatoire.
- **ÉTANT DONNÉ QUE** j'ai une partie en création, **LORSQUE** je tente  m'ajoute à la partie avec un pseudo de plus de 20 caractères, **ALORS** je reçois un message d'erreur m'informant que le pseudo est trop long.
- **ÉTANT DONNÉ QUE** j'ai une partie en création, **LORSQUE** je tente  m'ajoute à la partie avec un pseudo de moins de 2 caractères, **ALORS** je reçois un message d'erreur m'informant que le pseudo est trop court.
- **ÉTANT DONNÉ QUE** j'ai une partie en création, **LORSQUE** je tente  m'ajoute à la partie avec un pseudo contenant des caractères spéciaux, **ALORS** je reçois un message d'erreur m'informant que le pseudo ne doit pas contenir de caractères spéciaux.
- **ÉTANT DONNÉ QUE** j'ai une partie en création, **LORSQUE** je tente m'ajoute à la partie, **ALORS** je reçois un message d'erreur m'informant que le pseudo ne doit pas contenir d'espaces.

### US002 - User Deleted
**En tant qu'** utilisateur,
**Je veux** me retirer d'une partie,
**Afin de** ne plus participer à la partie.

**Critères d'acceptation :**
- **ÉTANT DONNÉ QUE** j'ai une partie en création, **LORSQUE** je me retire de la partie, **ALORS** je ne suis plus dans la liste des joueurs.
- **ÉTANT DONNÉ QUE** j'ai une partie en création, **LORSQUE** je me retire de la partie, **ALORS** je reçois un message de confirmation de mon retrait.

### US003 - Party Creation 
**En tant qu'** utilisateur,
**Je veux** créer une partie,
**Afin de** pouvoir jouer à un jeu.

**Critères d'acceptation :**
- **ÉTANT DONNÉ QUE** je suis sur l'écran de création de partie, **LORSQUE** je crée une partie, **ALORS** la partie est créée et je suis redirigé vers l'écran de la partie.
- **ÉTANT DONNÉ QUE** je suis sur l'écran de création de partie, **LORSQUE** je crée une partie avec trop peu de participant, **ALORS** je reçois un message d'erreur m'informant que le nombre de joueurs est insuffisant pour lancer la partie.
- **ÉTANT DONNÉ QUE** je suis sur l'écran de création de partie, **LORSQUE** je crée une partie avec trop de participant, **ALORS** je reçois un message d'erreur m'informant que le nombre de joueurs est trop élevé pour lancer la partie.
- **ÉTANT DONNÉ QUE** je suis sur l'écran de création de partie, **LORSQUE** je crée une partie, **ALORS** j'ai réceptionne bien autant de joueurs que j'en ai demandé.
- **ÉTANT DONNÉ QUE** je suis sur l'écran de création de partie, **LORSQUE** je crée une partie, **ALORS** tout les joueurs commence à 0 de score.

### US004 - 
## Integration Tests
Integration tests are written to test the interaction between different components of the application. They are run using the Android testing framework.
## E2E Tests
E2E tests are written to test the application as a whole. They are run using the Android testing framework.
## Security Tests
Security tests are written to test the security of the application. They are run using the Android testing framework.
