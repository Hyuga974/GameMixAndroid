# Cahier de Recettes - GameMixAndroid

* **Version** : 1.0  
* **Date** : 16/07/2025
* **Responsable Qualité** : REYPE Costa

## Sommaire

- [1. Plan de Test](#1-plan-de-test)
- [2. Scénarios de Test](#2-scénarios-de-test)
  - [2.1 Tests Fonctionnels](#21-tests-fonctionnels)
  - [2.2 Tests de Sécurité](#22-tests-de-sécurité)
  - [2.3 Tests Structurels](#23-tests-structurels)
- [3. Matrice de validation](#3-matrice-de-validation)

# 1. Plan de Test

| Domaine          | Fonctionnalités Couvertes      | Types de Tests                  | Outils            |
|------------------|--------------------------------|---------------------------------|-------------------|
| Fonctionnel      | US001-US015                    | Unitaires, Intégration, E2E     | JUnit, Espresso   |
| Sécurité         | US016-US018                    | Tests de sécurité               | OWASP ZAP         |
| Structurel       | US019                          | Couverture de code              | JaCoCo, SonarQube |

# 2. Scénarios de Test

## 2.1 Tests Fonctionnels

### US001 - Enregistrement Utilisateur
**En tant qu'** utilisateur,  
**Je veux** rejoindre une partie avec un pseudonyme,  
**Afin de** pouvoir participer à la partie.  

**Critères d'acceptation :**  
  - **ÉTANT DONNÉ** une partie en création, **LORSQUE** je rejoins la partie avec un pseudonyme, **ALORS** je suis ajouté à la partie et mon pseudonyme est visible dans la liste des joueurs.  
  - **ÉTANT DONNÉ** une partie en création, **LORSQUE** j'essaie de rejoindre la partie avec un pseudonyme déjà pris, **ALORS** je reçois un message d'erreur m'informant que le pseudonyme est déjà utilisé.  
  - **ÉTANT DONNÉ** une partie en création, **LORSQUE** j'essaie de rejoindre la partie sans pseudonyme, **ALORS** je reçois un message d'erreur m'informant que le pseudonyme est obligatoire.  
  - **ÉTANT DONNÉ** une partie en création, **LORSQUE** j'essaie de rejoindre la partie avec un pseudonyme de plus de 20 caractères, **ALORS** je reçois un message d'erreur m'informant que le pseudonyme est trop long.  
  - **ÉTANT DONNÉ** une partie en création, **LORSQUE** j'essaie de rejoindre la partie avec un pseudonyme de moins de 2 caractères, **ALORS** je reçois un message d'erreur m'informant que le pseudonyme est trop court.  
  - **ÉTANT DONNÉ** une partie en création, **LORSQUE** j'essaie de rejoindre la partie avec un pseudonyme contenant des caractères spéciaux, **ALORS** je reçois un message d'erreur m'informant que le pseudonyme ne doit pas contenir de caractères spéciaux.  
  - **ÉTANT DONNÉ** une partie en création, **LORSQUE** j'essaie de rejoindre la partie, **ALORS** je reçois un message d'erreur m'informant que le pseudonyme ne doit pas contenir d'espaces.  

### US002 - Suppression d'Utilisateur
**En tant qu'** utilisateur,  
**Je veux** quitter une partie,  
**Afin de** ne plus participer à la partie.  

**Critères d'acceptation :**  
  - **ÉTANT DONNÉ** une partie en création, **LORSQUE** je quitte la partie, **ALORS** je ne suis plus dans la liste des joueurs.  
  - **ÉTANT DONNÉ** une partie en création, **LORSQUE** je quitte la partie, **ALORS** je reçois un message de confirmation de mon départ.  

### US003 - Création de Partie 
**En tant qu'** utilisateur,  
**Je veux** créer une partie,  
**Afin de** pouvoir jouer à un jeu.  

**Critères d'acceptation :**  
  - **ÉTANT DONNÉ** que je suis sur l'écran de création de partie, **LORSQUE** je crée une partie, **ALORS** la partie est créée et je suis redirigé vers l'écran de jeu.  
  - **ÉTANT DONNÉ** que je suis sur l'écran de création de partie, **LORSQUE** je crée une partie avec trop peu de participants, **ALORS** je reçois un message d'erreur m'informant que le nombre de joueurs est insuffisant pour lancer la partie.  
  - **ÉTANT DONNÉ** que je suis sur l'écran de création de partie, **LORSQUE** je crée une partie avec trop de participants, **ALORS** je reçois un message d'erreur m'informant que le nombre de joueurs est trop élevé pour lancer la partie.  
  - **ÉTANT DONNÉ** que je suis sur l'écran de création de partie, **LORSQUE** je crée une partie, **ALORS** je reçois bien autant de joueurs que j'en ai demandé.  
  - **ÉTANT DONNÉ** que je suis sur l'écran de création de partie, **LORSQUE** je crée une partie, **ALORS** tous les joueurs commencent avec un score de 0.  

### US004 - Création et Gestion de Partie
**En tant qu'** utilisateur,  
**Je veux** créer une nouvelle partie, ajouter des joueurs et démarrer la partie,  
**Afin de** pouvoir jouer avec d'autres.  

**Critères d'acceptation :**  
  - **ÉTANT DONNÉ** que je suis sur l'écran de création de partie, **LORSQUE** je sélectionne un type de jeu (par exemple, Belote), **ALORS** les options du jeu apparaissent  
  - **ÉTANT DONNÉ** que j'ai ajouté des joueurs, **LORSQUE** j'atteins le nombre minimum de joueurs requis, **ALORS** le bouton JOUER devient activé  
  - **ÉTANT DONNÉ** que j'ai configuré la partie, **LORSQUE** je clique sur JOUER, **ALORS** la partie démarre et je suis redirigé vers l'écran de jeu  

### US005 - **Ajout d'un joueur à une partie**
**En tant qu'** utilisateur,  
**Je veux** ajouter un nouveau joueur à une partie en cours,  
**Afin de** permettre à d'autres de rejoindre la partie.

**Critères d'acceptation :**  
  - **ÉTANT DONNÉ** une partie en cours, **LORSQUE** j'ajoute un nouveau joueur, **ALORS** la liste des joueurs est mise à jour en temps réel dans l'interface.

### US006 - **Limite de joueurs et gestion des erreurs**
**En tant qu'** utilisateur,  
**Je veux** être informé lorsque la limite de joueurs est atteinte,  
**Afin de** ne pas pouvoir ajouter plus de joueurs que prévu

**Critères d'acceptation :**  
  - **ÉTANT DONNÉ** que la limite de joueurs est atteinte dans une partie, **LORSQUE** j'essaie d'ajouter un joueur supplémentaire, **ALORS** je reçois un message d'erreur m'informant que la limite est dépassée et la liste n'est pas modifiée.

### US007 - Édition des Scores
**En tant qu'** utilisateur,  
**Je veux** mettre à jour les scores,  
**Afin de** pouvoir les gérer.  

**Critères d'acceptation :**  
  - **ÉTANT DONNÉ** une partie en cours, **LORSQUE** je mets à jour le score d'un joueur, **ALORS** le score du joueur est mis à jour dans la liste des joueurs.  
  - **ÉTANT DONNÉ** une partie en cours, **LORSQUE** je mets à jour le score d'un joueur avec un score négatif, **ALORS** je reçois un message d'erreur m'informant que le score ne peut pas être négatif.  
  - **ÉTANT DONNÉ** une partie en cours, **LORSQUE** j'essaie de mettre à jour le score d'un joueur qui n'existe pas, **ALORS** je reçois un message d'erreur m'informant que le joueur n'existe pas. 

### US008 - Gestion des Scores pendant une Partie
**En tant qu'** utilisateur,  
**Je veux** mettre à jour les scores des joueurs pendant une partie,  
**Afin de** pouvoir suivre précisément la progression.  

**Critères d'acceptation :**  
  - **ÉTANT DONNÉ** une partie en cours, **LORSQUE** j'accède à l'éditeur de scores, **ALORS** les scores de tous les joueurs sont modifiables  
  - **ÉTANT DONNÉ** que je modifie un score, **LORSQUE** j'enregistre les modifications, **ALORS** l'interface utilisateur se met à jour instantanément  
  - **ÉTANT DONNÉ** que j'entre un score invalide, **LORSQUE** j'essaie de l'enregistrer, **ALORS** je reçois un retour de validation immédiat  

### US009 - **Mise à jour des scores**
**En tant qu'** utilisateur,  
**Je veux** voir immédiatement les scores mis à jour,  
**Afin de** suivre en temps réel l'évolution du jeu.

**Critères d'acceptation :**  
  - **ÉTANT DONNÉ** une partie en cours avec des joueurs, **LORSQUE** je mets à jour le score d'un joueur, **ALORS** le nouveau score est affiché immédiatement dans l'interface.

### US010 - Réinitialisation des Scores
**En tant qu'** utilisateur,  
**Je veux** réinitialiser tous les scores pendant une partie,  
**Afin de** pouvoir commencer un nouveau tour facilement.  

**Critères d'acceptation :**  
  - **ÉTANT DONNÉ** des scores existants, **LORSQUE** je déclenche la réinitialisation, **ALORS** une boîte de dialogue de confirmation apparaît  
  - **ÉTANT DONNÉ** que je confirme la réinitialisation, **LORSQUE** l'action est terminée, **ALORS** tous les scores affichent 0  
  - **ÉTANT DONNÉ** que j'annule la réinitialisation, **LORSQUE** la boîte de dialogue se ferme, **ALORS** les scores restent inchangés  


### US011 - Connexion et Contrôle d'un Appareil Externe
**En tant qu'** utilisateur,  
**Je veux** me connecter à un appareil externe de cartes,  
**Afin de** automatiser le mélange et la distribution des cartes.  

**Critères d'acceptation :**  
  - **ÉTANT DONNÉ** le Bluetooth activé, **LORSQUE** je sélectionne un appareil, **ALORS** l'appairage se termine en moins de 10s  
  - **ÉTANT DONNÉ** une connexion réussie, **LORSQUE** j'envoie une commande de mélange, **ALORS** l'appareil exécute en moins de 3s  
  - **ÉTANT DONNÉ** que je suis connecté, **LORSQUE** j'envoie une commande de distribution, **ALORS** les cartes sont distribuées comme configuré  

### US012 - **Connexion Bluetooth et contrôle de l'appareil**

**En tant qu'** utilisateur,  
**Je veux** utiliser le Bluetooth pour contrôler un appareil externe,  
**Afin de** faciliter le déroulement du jeu.

**Critères d'acceptation :**  
  - **ÉTANT DONNÉ** que je souhaite utiliser un appareil externe, **LORSQUE** je me connecte via Bluetooth et envoie une commande, **ALORS** l'appareil externe exécute la commande (mélange ou distribution des cartes).

### US013 - Gestion des Erreurs de Connexion Bluetooth
**En tant qu'** utilisateur,  
**Je veux** recevoir des messages d'erreur clairs,  
**Afin de** résoudre efficacement les problèmes de connexion Bluetooth.  

**Critères d'acceptation :**  
  - **ÉTANT DONNÉ** le Bluetooth désactivé, **LORSQUE** j'essaie de me connecter, **ALORS** je vois des instructions d'activation  
  - **ÉTANT DONNÉ** l'appareil indisponible, **LORSQUE** j'essaie de l'appairer, **ALORS** je reçois une alerte "Appareil indisponible"  
  - **ÉTANT DONNÉ** une déconnexion en milieu de partie, **LORSQUE** un échec se produit, **ALORS** je vois des options de reconnexion  
  - **ÉTANT DONNÉ** que je tente de me connecter à un appareil Bluetooth indisponible, **LORSQUE** la connexion échoue, **ALORS** je reçois un message d'erreur explicite.

### US014 - Prise en Charge Multi-Résolution
**En tant qu'** utilisateur,  
**Je veux** utiliser l'application sur différents appareils,  
**Afin d'** avoir une expérience cohérente sur tous les écrans.  

**Critères d'acceptation :**  
  - **ÉTANT DONNÉ** que je suis sur mobile (ex: 1080x1920), **LORSQUE** je vois les éléments de l'interface, **ALORS** aucun rognage ne se produit  
  - **ÉTANT DONNÉ** que je suis sur tablette (ex: 2560x1600), **LORSQUE** j'utilise les mises en page, **ALORS** l'utilisation de l'espace s'adapte  
  - **ÉTANT DONNÉ** que je pivote l'appareil, **LORSQUE** l'orientation change, **ALORS** l'interface se réorganise correctement  

### US015 - Navigation entre les Écrans de l'Application
**En tant qu'** utilisateur,  
**Je veux** naviguer de manière fluide entre tous les écrans de l'application,  
**Afin d'** accéder à toutes les fonctionnalités de manière transparente et passer facilement d'une fonctionnalité à l'autre.

**Critères d'acceptation :**  
  - **ÉTANT DONNÉ** que je suis sur n'importe quel écran, **LORSQUE** j'utilise les contrôles de navigation, **ALORS** les transitions se terminent en <500ms.
  - **ÉTANT DONNÉ** que je navigue vers un autre écran, **LORSQUE** je change d'écran, **ALORS** la transition est fluide et l'état précédent est préservé.
  - **ÉTANT DONNÉ** que j'utilise la navigation retour, **LORSQUE** je confirme, **ALORS** je retourne à l'écran précédent logique.


## 2.2 Tests de Sécurité

### US016 - Gestion des Permissions
**En tant qu'** utilisateur,  
**Je veux** contrôler les permissions de l'application,  
**Afin que** ma vie privée soit respectée.  


**Critères d'acceptation :**  
- **ÉTANT DONNÉ** que j'accède à une fonctionnalité Bluetooth, **LORSQUE** la permission est manquante, **ALORS** je vois une demande de justification  
- **ÉTANT DONNÉ** que je refuse une permission, **LORSQUE** je réessaie l'action, **ALORS** je vois une dégradation gracieuse  
- **ÉTANT DONNÉ** que je révoque une permission, **LORSQUE** je rouvre l'application, **ALORS** les fonctionnalités se désactivent de manière appropriée  

### US017 - Sécurité Bluetooth
**En tant qu'** utilisateur,  
**Je veux** garantir des connexions sécurisées,  
**Afin que** mon appareil reste protégé.  

**Critères d'acceptation :**  
- **ÉTANT DONNÉ** que j'appaire des appareils, **LORSQUE** j'établis la connexion, **ALORS** le chiffrement s'active automatiquement  
- **ÉTANT DONNÉ** des appareils non autorisés, **LORSQUE** ils tentent de s'appairer, **ALORS** ils reçoivent un refus de connexion  
- **ÉTANT DONNÉ** une connexion active, **LORSQUE** une attaque de l'homme du milieu se produit, **ALORS** la connexion se termine  

### US018 - Protection des Données
**En tant qu'** utilisateur,  
**Je veux** garder mes données de jeu privées,  
**Afin que** d'autres ne puissent pas y accéder.  

**Critères d'acceptation :**  
- **ÉTANT DONNÉ** des données de jeu stockées, **LORSQUE** j'inspecte le stockage, **ALORS** les fichiers sont chiffrés  
- **ÉTANT DONNÉ** d'autres applications, **LORSQUE** elles demandent l'accès, **ALORS** elles reçoivent un refus de permission  
- **ÉTANT DONNÉ** des appareils rootés, **LORSQUE** ils accèdent au bac à sable de l'application, **ALORS** les données restent illisibles  

## 2.3 Tests Structurels


### US019 - Couverture des Branches Conditionnelles
**En tant que** développeur,  
**Je veux** vérifier que tous les chemins de code sont testés,  
**Afin que** la logique fonctionne dans tous les scénarios.  

**Critères d'acceptation :**  
- **ÉTANT DONNÉ** les tests unitaires exécutés, **LORSQUE** la couverture est générée, **ALORS** les composants critiques montrent ≥80% de couverture  
- **ÉTANT DONNÉ** une logique de validation, **LORSQUE** je teste les cas limites, **ALORS** toutes les branches conditionnelles s'exécutent  
- **ÉTANT DONNÉ** des branches non couvertes, **LORSQUE** elles sont identifiées, **ALORS** des tests sont ajoutés pour les chemins manquants


# 3. Matrice de validation

| ID | TITRE | DESCRIPTION | RÉSULTAT SOUHAITÉ | RÉSULTAT OBTENU | COMMENTAIRE |
|----|-------|-------------|-------------------|-----------------|-------------|
| US001 | Enregistrement Utilisateur | | | | |
| US001 - 01 | | En tant qu'utilisateur, je veux rejoindre une partie avec un pseudonyme, afin de pouvoir participer à la partie | Je suis ajouté à la partie et mon pseudonyme est visible dans la liste des joueurs | NT | |
| US001 - 02 | | En tant qu'utilisateur, j'essaie de rejoindre la partie avec un pseudonyme déjà pris, afin de m'assurer que mon pseudonyme est unique | Je reçois un message d'erreur m'informant que le pseudonyme est déjà utilisé | NT | |
| US001 - 03 | | En tant qu'utilisateur, j'essaie de rejoindre la partie sans pseudonyme, afin de m'assurer que j'ai un pseudonyme | Je reçois un message d'erreur m'informant que le pseudonyme est obligatoire | NT | |
| US001 - 04 | | En tant qu'utilisateur, j'essaie de rejoindre la partie avec un pseudonyme de plus de 20 caractères, afin de respecter les limites de caractères | Je reçois un message d'erreur m'informant que le pseudonyme est trop long | NT | |
| US001 - 05 | | En tant qu'utilisateur, j'essaie de rejoindre la partie avec un pseudonyme de moins de 2 caractères, afin de respecter les limites de caractères | Je reçois un message d'erreur m'informant que le pseudonyme est trop court | NT | |
| US001 - 06 | | En tant qu'utilisateur, j'essaie de rejoindre la partie avec un pseudonyme contenant des caractères spéciaux, afin de respecter les règles de format | Je reçois un message d'erreur m'informant que le pseudonyme ne doit pas contenir de caractères spéciaux | NT | |
| US001 - 07 | | En tant qu'utilisateur, j'essaie de rejoindre la partie avec un pseudonyme contenant des espaces, afin de respecter les règles de format | Je reçois un message d'erreur m'informant que le pseudonyme ne doit pas contenir d'espaces | NT | |
| US002 | Suppression d'Utilisateur | | | | |
| US002 - 01 | | En tant qu'utilisateur, je veux quitter une partie, afin de ne plus participer à la partie | Je ne suis plus dans la liste des joueurs | NT | |
| US002 - 02 | | En tant qu'utilisateur, je quitte la partie, afin d'avoir une confirmation de mon action | Je reçois un message de confirmation de mon départ | NT | |
| US003 | Création de Partie | | | | |
| US003 - 01 | | En tant qu'utilisateur, je veux créer une partie, afin de pouvoir jouer à un jeu | La partie est créée et je suis redirigé vers l'écran de jeu | NT | |
| US003 - 02 | | En tant qu'utilisateur, je crée une partie avec trop peu de participants, afin de respecter le nombre minimum de joueurs | Je reçois un message d'erreur m'informant que le nombre de joueurs est insuffisant pour lancer la partie | NT | |
| US003 - 03 | | En tant qu'utilisateur, je crée une partie avec trop de participants, afin de respecter le nombre maximum de joueurs | Je reçois un message d'erreur m'informant que le nombre de joueurs est trop élevé pour lancer la partie | NT | |
| US003 - 04 | | En tant qu'utilisateur, je crée une partie, afin d'avoir le nombre de joueurs souhaité | Je reçois bien autant de joueurs que j'en ai demandé | NT | |
| US003 - 05 | | En tant qu'utilisateur, je crée une partie, afin que tous les joueurs commencent avec un score initial | Tous les joueurs commencent avec un score de 0 | NT | |
| US004 | Création et Gestion de Partie | | | | |
| US004 - 01 | | En tant qu'utilisateur, je veux créer une nouvelle partie, ajouter des joueurs et démarrer la partie, afin de pouvoir jouer avec d'autres | Les options du jeu apparaissent | NT | |
| US004 - 02 | | En tant qu'utilisateur, j'ai ajouté des joueurs et j'atteins le nombre minimum requis, afin de pouvoir démarrer la partie | Le bouton JOUER devient activé | NT | |
| US004 - 03 | | En tant qu'utilisateur, j'ai configuré la partie et je clique sur JOUER, afin de démarrer la partie | La partie démarre et je suis redirigé vers l'écran de jeu | NT | |
| US005 | Ajout d'un joueur à une partie | | | | |
| US005 - 01 | | En tant qu'utilisateur, j'ajoute un nouveau joueur à une partie en cours, afin de permettre à d'autres de rejoindre la partie | La liste des joueurs est mise à jour en temps réel dans l'interface | NT | |
| US006 | Limite de joueurs et gestion des erreurs | | | | |
| US006 - 01 | | En tant qu'utilisateur, j'essaie d'ajouter un joueur supplémentaire lorsque la limite est atteinte, afin de respecter la limite de joueurs | Je reçois un message d'erreur m'informant que la limite est dépassée et la liste n'est pas modifiée | NT | |
| US007 | Édition des Scores | | | | |
| US007 - 01 | | En tant qu'utilisateur, je veux mettre à jour les scores, afin de pouvoir les gérer | Le score du joueur est mis à jour dans la liste des joueurs | NT | |
| US007 - 02 | | En tant qu'utilisateur, je mets à jour le score d'un joueur avec un score négatif, afin de respecter les règles de score | Je reçois un message d'erreur m'informant que le score ne peut pas être négatif | NT | |
| US007 - 03 | | En tant qu'utilisateur, j'essaie de mettre à jour le score d'un joueur qui n'existe pas, afin de m'assurer que le joueur existe | Je reçois un message d'erreur m'informant que le joueur n'existe pas | NT | |
| US008 | Gestion des Scores pendant une Partie | | | | |
| US008 - 01 | | En tant qu'utilisateur, je veux mettre à jour les scores des joueurs pendant une partie, afin de pouvoir suivre précisément la progression | Les scores de tous les joueurs sont modifiables | NT | |
| US008 - 02 | | En tant qu'utilisateur, je modifie un score et j'enregistre les modifications, afin de voir les changements | L'interface utilisateur se met à jour instantanément | NT | |
| US008 - 03 | | En tant qu'utilisateur, j'entre un score invalide et j'essaie de l'enregistrer, afin de respecter les règles de score | Je reçois un retour de validation immédiat | NT | |
| US009 | Mise à jour des scores | | | | |
| US009 - 01 | | En tant qu'utilisateur, je veux voir immédiatement les scores mis à jour, afin de suivre en temps réel l'évolution du jeu | Le nouveau score est affiché immédiatement dans l'interface | NT | |
| US010 | Réinitialisation des Scores | | | | |
| US010 - 01 | | En tant qu'utilisateur, je veux réinitialiser tous les scores pendant une partie, afin de pouvoir commencer un nouveau tour facilement | Une boîte de dialogue de confirmation apparaît | NT | |
| US010 - 02 | | En tant qu'utilisateur, je confirme la réinitialisation, afin de réinitialiser les scores | Tous les scores affichent 0 | NT | |
| US010 - 03 | | En tant qu'utilisateur, j'annule la réinitialisation, afin de conserver les scores actuels | Les scores restent inchangés | NT | |
| US011 | Connexion et Contrôle d'un Appareil Externe | | | | |
| US011 - 01 | | En tant qu'utilisateur, je veux me connecter à un appareil externe de cartes, afin d'automatiser le mélange et la distribution des cartes | L'appairage se termine en moins de 10s | NT | |
| US011 - 02 | | En tant qu'utilisateur, je suis connecté et j'envoie une commande de mélange, afin de mélanger les cartes | L'appareil exécute en moins de 3s | NT | |
| US011 - 03 | | En tant qu'utilisateur, je suis connecté et j'envoie une commande de distribution, afin de distribuer les cartes | Les cartes sont distribuées comme configuré | NT | |
| US012 | Connexion Bluetooth et contrôle de l'appareil | | | | |
| US012 - 01 | | En tant qu'utilisateur, je veux utiliser le Bluetooth pour contrôler un appareil externe, afin de faciliter le déroulement du jeu | L'appareil externe exécute la commande (mélange ou distribution des cartes) | NT | |
| US013 | Gestion des Erreurs de Connexion Bluetooth | | | | |
| US013 - 01 | | En tant qu'utilisateur, je veux recevoir des messages d'erreur clairs, afin de résoudre efficacement les problèmes de connexion Bluetooth | Je vois des instructions d'activation | NT | |
| US013 - 02 | | En tant qu'utilisateur, l'appareil est indisponible et j'essaie de l'appairer, afin de gérer les erreurs de connexion | Je reçois une alerte "Appareil indisponible" | NT | |
| US013 - 03 | | En tant qu'utilisateur, je suis déconnecté en milieu de partie, afin de gérer les erreurs de connexion | Je vois des options de reconnexion | NT | |
| US013 - 04 | | En tant qu'utilisateur, je tente de me connecter à un appareil Bluetooth indisponible, afin de gérer les erreurs de connexion | Je reçois un message d'erreur explicite | NT | |
| US014 | Prise en Charge Multi-Résolution | | | | |
| US014 - 01 | | En tant qu'utilisateur, je veux utiliser l'application sur différents appareils, afin d'avoir une expérience cohérente sur tous les écrans | Aucun rognage ne se produit | NT | |
| US014 - 02 | | En tant qu'utilisateur, je suis sur tablette et j'utilise les mises en page, afin d'adapter l'interface | L'utilisation de l'espace s'adapte | NT | |
| US014 - 03 | | En tant qu'utilisateur, je pivote l'appareil, afin d'adapter l'interface | L'interface se réorganise correctement | NT | |
| US015 | Navigation entre les Écrans de l'Application | | | | |
| US015 - 01 | | En tant qu'utilisateur, je veux naviguer de manière fluide entre tous les écrans de l'application, afin d'accéder à toutes les fonctionnalités de manière transparente et passer facilement d'une fonctionnalité à l'autre | Les transitions se terminent en <500ms | NT | |
| US015 - 02 | | En tant qu'utilisateur, je navigue vers un autre écran | La transition est fluide et l'état précédent est préservé | NT | |
| US015 - 03 | | En tant qu'utilisateur, j'utilise la navigation retour et je confirme | Je retourne à l'écran précédent logique | NT | |
