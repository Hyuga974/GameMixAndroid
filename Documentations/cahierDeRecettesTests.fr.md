# Cahier de Recettes - GameMixAndroid

* **Version**: 1.0
* **Date**: 16/07/2025
* **Responsable Qualité**: REYPE Costa

## Table des matières

1. [Plan des tests](#1-plan-des-tests)
2. [Spécifications fonctionnelles](#2spécifications-fonctionnelles---user-stories)
  1. [Tests fonctionnels](#21-tests-fonctionnels)
  2. [Tests de sécurité](#22-tests-de-sécurité)
  3. [Tests structurels](#23-tests-structurels)
3. [Matrice de validation](#3-matrice-de-validation)

# 1. Plan des tests

| Domaine     | Fonctionnalité couverte | Types de Testes            | Outils            |
|-------------|-------------------------|----------------------------|-------------------|
| Fonctionnel | US001-US011             | Unitaire, Integration, E2E | JUnit, Espresso   |
| Sécurité    | US012-US014             | Testes de Sécurité         | OWASP ZAP         |
| Structurel  | US015                   | Couverture du code         | JaCoCo, SonarQube |

# 2.Spécifications Fonctionnelles - User Stories

## 2.1 Tests fonctionnels

### US001 - Enregistrement d’Utilisateur
**En tant que** joueur,  
**Je veux** rejoindre une partie avec un pseudonyme,  
**Afin de** pouvoir participer à la partie.

**Critères d’acceptation :**
- **ÉTANT DONNÉE** une partie en création, **LORSQUE** je rejoins avec un pseudonyme, **ALORS** je suis ajouté à la partie et mon pseudonyme apparaît dans la liste des joueurs.
- **ÉTANT DONNÉE** une partie en création, **LORSQUE** j’essaie de rejoindre avec un pseudonyme déjà pris, **ALORS** je reçois un message d’erreur indiquant que le pseudonyme est déjà utilisé.
- **ÉTANT DONNÉE** une partie en création, **LORSQUE** j’essaie de rejoindre sans pseudonyme, **ALORS** je reçois un message d’erreur indiquant qu’un pseudonyme est obligatoire.
- **ÉTANT DONNÉE** une partie en création, **LORSQUE** j’essaie de rejoindre avec un pseudonyme de plus de 20 caractères, **ALORS** je reçois un message d’erreur indiquant que le pseudonyme est trop long.
- **ÉTANT DONNÉE** une partie en création, **LORSQUE** j’essaie de rejoindre avec un pseudonyme de moins de 2 caractères, **ALORS** je reçois un message d’erreur indiquant que le pseudonyme est trop court.
- **ÉTANT DONNÉE** une partie en création, **LORSQUE** j’essaie de rejoindre avec un pseudonyme contenant des caractères spéciaux, **ALORS** je reçois un message d’erreur indiquant que les caractères spéciaux ne sont pas autorisés.
- **ÉTANT DONNÉE** une partie en création, **LORSQUE** j’essaie de rejoindre avec un pseudonyme contenant des espaces, **ALORS** je reçois un message d’erreur indiquant que les espaces ne sont pas autorisés.

---

### US002 - Suppression d’Utilisateur
**En tant que** joueur,  
**Je veux** quitter une partie,  
**Afin de** ne plus y participer.

**Critères d’acceptation :**
- **ÉTANT DONNÉE** une partie en création, **LORSQUE** je quitte la partie, **ALORS** je suis retiré de la liste des joueurs.
- **ÉTANT DONNÉE** une partie en création, **LORSQUE** je quitte la partie, **ALORS** je reçois un message de confirmation de départ.

---

### US003 - Création de Partie
**En tant que** joueur,  
**Je veux** créer une partie,  
**Afin de** pouvoir jouer.

**Critères d’acceptation :**
- **ÉTANT DONNÉ** sur l’écran de création, **LORSQUE** je crée une partie, **ALORS** la partie est créée et je suis redirigé vers l’écran de jeu.
- **ÉTANT DONNÉ** sur l’écran de création, **LORSQUE** je crée une partie avec trop peu de joueurs, **ALORS** je reçois un message d’erreur indiquant qu’il n’y a pas assez de joueurs.
- **ÉTANT DONNÉ** sur l’écran de création, **LORSQUE** je crée une partie avec trop de joueurs, **ALORS** je reçois un message d’erreur indiquant qu’il y a trop de joueurs.
- **ÉTANT DONNÉ** sur l’écran de création, **LORSQUE** je crée une partie, **ALORS** le nombre exact de joueurs demandés est affecté.
- **ÉTANT DONNÉ** sur l’écran de création, **LORSQUE** je crée une partie, **ALORS** tous les joueurs commencent avec un score de 0.

---

### US004 - Gestion et Démarrage de Partie
**En tant que** joueur,  
**Je veux** créer une partie, ajouter des joueurs et la démarrer,  
**Afin de** jouer avec d’autres.

**Critères d’acceptation :**
- **ÉTANT DONNÉ** que je suis sur l’écran de création, **LORSQUE** je sélectionne un type de jeu (ex. Belote), **ALORS** les options du jeu apparaissent.
- **ÉTANT DONNÉ** que j’ai ajouté des joueurs, **LORSQUE** le nombre minimum est atteint, **ALORS** le bouton *Jouer* est activé.
- **ÉTANT DONNÉ** que j’ai configuré la partie, **LORSQUE** je clique sur *Jouer*, **ALORS** la partie démarre et je suis redirigé vers l’écran de jeu.

---

### US005 - Édition des Scores
**En tant que** joueur,  
**Je veux** mettre à jour les scores,  
**Afin de** les gérer.

**Critères d’acceptation :**
- **ÉTANT DONNÉ** une partie en cours, **LORSQUE** je modifie le score d’un joueur, **ALORS** il est mis à jour dans la liste.
- **ÉTANT DONNÉ** une partie en cours, **LORSQUE** j’entre un score négatif, **ALORS** il est correctement pris en compte.
- **ÉTANT DONNÉ** une partie en cours, **LORSQUE** j’essaie de modifier le score d’un joueur inexistant, **ALORS** je reçois un message d’erreur.

---

### US006 - Mise à Jour des Scores
**En tant que** joueur,  
**Je veux** voir les scores mis à jour immédiatement,  
**Afin de** suivre la partie en temps réel.

**Critères d’acceptation :**
- **ÉTANT DONNÉ** une partie en cours, **LORSQUE** je modifie le score d’un joueur, **ALORS** le nouveau score est affiché immédiatement.

---

### US007 - Réinitialisation des Scores
**En tant que** joueur,  
**Je veux** réinitialiser tous les scores pendant une partie,  
**Afin de** recommencer facilement une nouvelle manche.

**Critères d’acceptation :**
- **ÉTANT DONNÉ** des scores existants, **LORSQUE** je déclenche une réinitialisation, **ALORS** une boîte de confirmation apparaît.
- **ÉTANT DONNÉ** que je confirme la réinitialisation, **LORSQUE** l’action est validée, **ALORS** tous les scores affichent 0.
- **ÉTANT DONNÉ** que j’annule la réinitialisation, **LORSQUE** la boîte se ferme, **ALORS** les scores restent inchangés.

---

### US008 - Connexion et Contrôle d’un Appareil Externe
**En tant que** joueur,  
**Je veux** connecter un appareil de cartes,  
**Afin de** automatiser le mélange et la distribution.

**Critères d’acceptation :**
- **ÉTANT DONNÉ** que le Bluetooth est activé, **LORSQUE** je sélectionne un appareil, **ALORS** l’appairage se termine en moins de 10s.
- **ÉTANT DONNÉ** une connexion réussie, **LORSQUE** j’envoie une commande de mélange, **ALORS** l’appareil l’exécute en moins de 3s.
- **ÉTANT DONNÉ** que je suis connecté, **LORSQUE** j’envoie une commande de distribution, **ALORS** les cartes sont distribuées comme configuré.

---

### US009 - Connexion Bluetooth et Contrôle d’Appareil
**En tant que** joueur,  
**Je veux** utiliser le Bluetooth pour contrôler un appareil externe,  
**Afin de** faciliter le déroulement de la partie.

**Critères d’acceptation :**
- **ÉTANT DONNÉ** que je veux utiliser un appareil externe, **LORSQUE** je me connecte en Bluetooth et envoie une commande, **ALORS** l’appareil exécute la commande (mélange ou distribution).

---

### US010 - Gestion des Erreurs de Connexion Bluetooth
**En tant que** joueur,  
**Je veux** recevoir des messages d’erreur clairs,  
**Afin de** résoudre efficacement les problèmes de connexion Bluetooth.

**Critères d’acceptation :**
- **ÉTANT DONNÉ** que le Bluetooth est désactivé, **LORSQUE** j’essaie de me connecter, **ALORS** je vois des instructions pour l’activer.
- **ÉTANT DONNÉ** que l’appareil est indisponible, **LORSQUE** j’essaie de m’appairer, **ALORS** je reçois une alerte « Appareil indisponible ».
- **ÉTANT DONNÉ** une déconnexion en cours de partie, **LORSQUE** cela arrive, **ALORS** je vois des options de reconnexion.
- **ÉTANT DONNÉ** que j’essaie de me connecter à un appareil Bluetooth indisponible, **LORSQUE** la connexion échoue, **ALORS** je reçois un message d’erreur explicite.

---

### US011 - Navigation entre les Écrans
**En tant que** joueur,  
**Je veux** naviguer fluidement entre les écrans,  
**Afin de** accéder à toutes les fonctionnalités facilement.

**Critères d’acceptation :**
- **ÉTANT DONNÉ** que je suis sur un écran, **LORSQUE** j’utilise la navigation, **ALORS** la transition dure <500ms.
- **ÉTANT DONNÉ** que je change d’écran, **LORSQUE** je le fais, **ALORS** la transition est fluide et l’état précédent est préservé.
- **ÉTANT DONNÉ** que j’utilise la navigation retour, **LORSQUE** je confirme, **ALORS** je reviens à l’écran logique précédent.

---

## 2.2 Tests de Sécurité

### US012 - Gestion des Permissions
**En tant que** joueur,  
**Je veux** contrôler les permissions de l’application,  
**Afin de** protéger ma vie privée.

**Critères d’acceptation :**
- **ÉTANT DONNÉ** que j’accède à une fonctionnalité Bluetooth, **LORSQUE** la permission manque, **ALORS** je vois une demande justifiée.
- **ÉTANT DONNÉ** que je refuse une permission, **LORSQUE** j’essaie de réutiliser la fonctionnalité, **ALORS** une alternative propre est proposée.
- **ÉTANT DONNÉ** que je révoque une permission, **LORSQUE** je rouvre l’application, **ALORS** les fonctionnalités associées sont désactivées.

---

### US013 - Sécurité Bluetooth
**En tant que** joueur,  
**Je veux** des connexions sécurisées,  
**Afin de** protéger mon appareil.

**Critères d’acceptation :**
- **ÉTANT DONNÉ** que j’appaire des appareils, **LORSQUE** la connexion est établie, **ALORS** le chiffrement est activé automatiquement.
- **ÉTANT DONNÉ** des appareils non autorisés, **LORSQUE** ils tentent un appairage, **ALORS** la demande est refusée.
- **ÉTANT DONNÉ** une connexion active, **LORSQUE** une attaque de type « man-in-the-middle » est simulée, **ALORS** la connexion est interrompue.

---

### US014 - Protection des Données
**En tant que** joueur,  
**Je veux** que mes données de partie restent privées,  
**Afin** qu’elles ne soient pas accessibles aux autres.

**Critères d’acceptation :**
- **ÉTANT DONNÉ** des données stockées, **LORSQUE** j’inspecte le stockage, **ALORS** les fichiers sont chiffrés.
- **ÉTANT DONNÉ** d’autres applications, **LORSQUE** elles demandent un accès, **ALORS** la permission est refusée.
- **ÉTANT DONNÉ** un appareil rooté, **LORSQUE** il accède au bac à sable de l’app, **ALORS** les données restent illisibles.

---

## 2.3 Tests Structurels

### US015 - Couverture des Branches Conditionnelles
**En tant que** développeur,  
**Je veux** que tous les chemins du code soient testés,  
**Afin de** garantir que la logique fonctionne dans tous les scénarios.

**Critères d’acceptation :**
- **ÉTANT DONNÉ** des tests unitaires exécutés, **LORSQUE** la couverture est générée, **ALORS** les composants critiques affichent ≥80% de couverture.
- **ÉTANT DONNÉ** la logique de validation, **LORSQUE** je teste les cas limites, **ALORS** toutes les branches conditionnelles sont exécutées.
- **ÉTANT DONNÉ** des branches non couvertes, **LORSQUE** elles sont identifiées, **ALORS** des tests sont ajoutés pour les chemins manquants.

# 3. Matrice de validation
| ID         | TITRE                             | DESCRIPTION                                                                                               | RÉSULTAT SOUHAITÉ                                                                                 | RÉSULTAT OBTENU | COMMENTAIRE |
|------------|-----------------------------------|------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------|-----------------|-------------|
| US001      | Enregistrement Utilisateur        |                                                                                                            |                                                                                                    |                 |             |
| US001-01   | Pseudonyme valide                 | Je rejoins une partie avec un pseudonyme valide                                                            | Je suis ajouté à la partie et mon pseudonyme est visible dans la liste des joueurs                | OK              |             |
| US001-02   | Pseudonyme déjà pris              | Je tente de rejoindre une partie avec un pseudonyme déjà utilisé                                           | Message d'erreur : pseudonyme déjà utilisé                                                         | OK              |             |
| US001-03   | Pseudonyme manquant               | Je tente de rejoindre une partie sans entrer de pseudonyme                                                 | Message d'erreur : pseudonyme obligatoire                                                          | OK              |             |
| US001-04   | Pseudonyme trop long              | Je tente de rejoindre une partie avec un pseudonyme de plus de 20 caractères                              | Message d'erreur : pseudonyme trop long                                                            | OK              |             |
| US001-05   | Pseudonyme trop court             | Je tente de rejoindre une partie avec un pseudonyme de moins de 2 caractères                              | Message d'erreur : pseudonyme trop court                                                           | OK              |             |
| US001-06   | Caractères spéciaux dans pseudo   | Je tente de rejoindre une partie avec un pseudonyme contenant des caractères spéciaux                     | Message d'erreur : pseudonyme ne doit pas contenir de caractères spéciaux                         | OK              |             |
| US001-07   | Espaces dans pseudonyme           | Je tente de rejoindre une partie avec un pseudonyme contenant des espaces                                 | Message d'erreur : pseudonyme ne doit pas contenir d'espaces                                      | OK              |             |
| US002      | Suppression d'Utilisateur         |                                                                                                            |                                                                                                    |                 |             |
| US002-01   | Quitter la partie                 | Je quitte une partie                                                                                       | Je ne suis plus dans la liste des joueurs                                                          | OK              |             |
| US002-02   | Confirmation départ               | Je quitte une partie                                                                                       | Message de confirmation de mon départ                                                              | OK              |             |
| US003      | Création de Partie                |                                                                                                            |                                                                                                    |                 |             |
| US003-01   | Création simple de partie         | Je crée une partie depuis l'écran de création                                                              | La partie est créée et je suis redirigé vers l'écran de jeu                                       |               |             |
| US003-02   | Trop peu de participants          | Je crée une partie avec un nombre insuffisant de joueurs                                                   | Message d'erreur : nombre de joueurs insuffisant                                                   | OK             |             |
| US003-03   | Trop de participants              | Je crée une partie avec un nombre excessif de joueurs                                                      | Message d'erreur : nombre de joueurs trop élevé                                                    | OK              |             |
| US003-04   | Nombre exact de joueurs           | Je crée une partie avec un nombre exact de joueurs                                                         | Je reçois bien autant de joueurs que j'en ai demandé                                               | OK              |             |
| US003-05   | Score initial des joueurs         | Je crée une partie                                                                                         | Tous les joueurs commencent avec un score de 0                                                     | OK              |             |
| US004      | Création et Gestion de Partie     |                                                                                                            |                                                                                                    |                 |             |
| US004-01   | Affichage des options de jeu      | Je sélectionne un type de jeu (ex : Belote)                                                                | Les options du jeu apparaissent                                                                    | OK              |             |
| US004-02   | Activation du bouton JOUER        | J’ajoute le nombre minimum de joueurs requis                                                              | Le bouton JOUER devient activé                                                                     | OK              |             |
| US004-03   | Démarrage de partie               | Je clique sur JOUER après configuration                                                                   | La partie démarre et je suis redirigé vers l’écran de jeu                                         | OK              |             |
| US005      | Édition des Scores                |                                                                                                            |                                                                                                    |                 |             |
| US005-01   | Mise à jour simple                | Je modifie le score d’un joueur                                                                           | Le score est mis à jour dans la liste                                                              | OK              |             |
| US005-02   | Score négatif                     | Je modifie un score en entrant une valeur négative                                                        | Le score est mis à jour avec la valeur négative                                                    | OK              |             |
| US005-03   | Joueur inexistant                 | Je tente de modifier le score d’un joueur qui n’existe pas                                                | Message d’erreur : joueur inexistant                                                               | OK              |             |
| US006      | Affichage immédiat des scores     |                                                                                                            |                                                                                                    |                 |             |
| US006-01   | Mise à jour en temps réel         | Je modifie un score                                                                                       | Le score s’affiche immédiatement                                                                   | OK              |             |
| US007      | Réinitialisation des Scores       |                                                                                                            |                                                                                                    |                 |             |
| US007-01   | Dialogue de confirmation          | Je déclenche la réinitialisation                                                                          | Une boîte de dialogue de confirmation s’affiche                                                    | OK              |             |
| US007-02   | Réinitialisation confirmée        | Je confirme la réinitialisation                                                                           | Tous les scores affichent 0                                                                        | OK              |             |
| US007-03   | Réinitialisation annulée          | J’annule la réinitialisation                                                                              | Les scores restent inchangés                                                                       | OK              |             |
| US008      | Connexion à un appareil externe   |                                                                                                            |                                                                                                    |                 |             |
| US008-01   | Appairage rapide                  | Je sélectionne un appareil Bluetooth                                                                      | L'appairage se termine en moins de 10s                                                             | NT              |             |
| US008-02   | Commande de mélange               | Je suis connecté et j’envoie une commande de mélange                                                      | L'appareil exécute en moins de 3s                                                                  | NT              |             |
| US008-03   | Commande de distribution          | Je suis connecté et j’envoie une commande de distribution                                                 | Les cartes sont distribuées comme configuré                                                        | NT              |             |
| US009      | Contrôle Bluetooth simplifié      |                                                                                                            |                                                                                                    |                 |             |
| US009-01   | Commande Bluetooth complète       | Je me connecte via Bluetooth et envoie une commande                                                       | L'appareil exécute la commande (mélange ou distribution)                                           | NT              |             |
| US010      | Gestion erreurs Bluetooth         |                                                                                                            |                                                                                                    |                 |             |
| US010-01   | Bluetooth désactivé               | Je tente de me connecter avec Bluetooth désactivé                                                         | Message d’instruction d’activation Bluetooth                                                       | NT              |             |
| US010-02   | Appareil indisponible             | J’essaie d’appairer un appareil indisponible                                                              | Alerte : "Appareil indisponible"                                                                   | NT              |             |
| US010-03   | Déconnexion en partie             | Une déconnexion survient pendant la partie                                                                | Options de reconnexion visibles                                                                    | NT              |             |
| US010-04   | Connexion échouée                 | Connexion à un appareil Bluetooth indisponible échoue                                                     | Message d’erreur explicite                                                                         | NT              |             |
| US011      | Navigation entre les écrans       |                                                                                                            |                                                                                                    |                 |             |
| US011-01   | Transitions rapides               | Je navigue entre les écrans de l'application                                                              | Transitions < 500ms                                                                                | NT              |             |
| US011-02   | État préservé                     | Je navigue vers un autre écran                                                                            | Transition fluide et état préservé                                                                 | NT              |             |
| US011-03   | Navigation retour logique         | J’utilise la navigation retour                                                                            | Je retourne à l’écran précédent logique                                                             | NT              |             |
| US012      | Gestion des Permissions           |                                                                                                            |                                                                                                    |                 |             |
| US012-01   | Justification des permissions     | Je tente d’utiliser le Bluetooth sans permission                                                          | Une demande de justification s’affiche                                                             | NT              |             |
| US012-02   | Dégradation gracieuse             | Je refuse une permission puis réessaie                                                                    | L’action échoue avec une dégradation visuelle acceptable                                            | NT              |             |
| US012-03   | Révocation et redémarrage         | Je révoque une permission et rouvre l’application                                                         | Les fonctionnalités correspondantes sont désactivées                                               | NT              |             |
| US013      | Sécurité Bluetooth                |                                                                                                            |                                                                                                    |                 |             |
| US013-01   | Chiffrement activé                | J’appaire des appareils via Bluetooth                                                                     | Le chiffrement est automatiquement activé                                                          | NT              |             |
| US013-02   | Refus des appareils non autorisés | Des appareils non autorisés tentent une connexion                                                         | Connexion refusée                                                                                  | NT              |             |
| US013-03   | Attaque réseau                    | Une attaque de type "man-in-the-middle" est simulée                                                       | La connexion Bluetooth est interrompue                                                             | NT              |             |
| US014      | Protection des Données            |                                                                                                            |                                                                                                    |                 |             |
| US014-01   | Chiffrement des fichiers          | J’inspecte les fichiers de données stockés                                                                | Les fichiers sont chiffrés                                                                         | NT              |             |
| US014-02   | Refus d’accès aux autres apps     | Une autre app demande accès aux données                                                                   | Permission refusée                                                                                 | NT              |             |
| US014-03   | Appareil rooté                    | Un appareil rooté tente d’accéder au bac à sable de l’app                                                 | Les données restent illisibles                                                                     | NT              |             |
| US015      | Couverture des Branches           |                                                                                                            |                                                                                                    |                 |             |
| US015-01   | Couverture ≥80%                   | J’exécute les tests unitaires                                                                             | Les composants critiques montrent ≥80% de couverture                                               | NT              |             |
| US015-02   | Tests des cas limites             | Je teste la logique conditionnelle avec des cas limites                                                    | Toutes les branches sont couvertes                                                                 | NT              |             |
| US015-03   | Ajout des tests manquants         | Des branches non couvertes sont identifiées                                                               | Des tests sont ajoutés                                                                             | NT              |             |

