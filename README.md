# FlightTrackerProject

Projet de suivi aéronotique. Ce logiciel a pour objectif de presenter les avions actuellement en vol dans le monde.

Lien GitHub : https://github.com/DavidMTdev/FlightTrackerProject

<br>

Pour ce faire nous avons reparti le projet en deux :

    - Le Front (WebFlightTracker) en React
    - Le Back (FlightTracker) en Java

<br>
    
Prérequies du projet FlightTrackerProject :
  - Modifier le fichier application.properties et mettre vos paramatre de la DB
  - Executer l'application `(FlightTracker)` en Java avant l'application `(WebFlightTracker)` en React

<br>

# FlightTracker

FlightTracker est la parti Back du projet. Dans cette parti nous avons mit en place une automatisation de recuperation de donnée sur l'API `opensky-network.org`.
Ces informations sont selectionné puis push sur une Base De Données afin de conserver uniquement ce que l'on souhaite afficher (nous inserons en base 100 avions toute les minutes).
Enfin il y a une dernière parti liant le Front, il s'agit de l'API créer pour récupérer les informations stoquer en Base De Données.
Cette parti a été développer en Java.

<br>

Execution de l'application Java :
  - Faire la commande `mvn install` dans le dossier `flighttracker`
  - Faire la commande `mvn spring-boot:run` dans le dossier `flighttracker`
  - Ou via un IDE Java

<br>

# WebFlightTracker

WebFlightTracker est une page Web développer en React.
Elle a pour but d'afficher une carte du monde sur laquel se deplacera des icons d'avions representant les avions actuellement en vol.
De plus il y a quelque fonctionnalité en plus.

<br>

Execution de l'application React :
  - Faire la commande `npm install` dans le dossier `webflighttracker`
  - Puis faire la commande `npm start` dans le dossier `webflighttracker`

<br>

# Fonctionnalité 
  - [x] Séléction de la date
  - [x] Nombre d'avion au dessus d'un pays
  - [x] Affichage d'un avion detaillé lors d'un clique 
