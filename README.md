# FlightTrackerProject

Projet de suivi aéronotique. Ce logiciel a pour objectif de presenter les Avions actuellement en vol dans le monde.

Pour ce faire nous avons reparti le projet en deux :

    - Le Front (WebFlightTracker)
    - Le Back (FlightTracker)
    
    
    
# FlightTracker

FlightTracker est la parti Back du projet. Dans cette parti nous avons mit en place une automatisation de recuperation de donnée sur une API.
Ces informations sont selectionné puis push sur une Base De Données afin de conserver uniquement ce que l'on souhaite afficher.
Enfin il y a une dernière parti liant le Front, il s'agit de l'API créer pour récupérer les informations stoquer en Base De Données.
Cette parti a été développer en Java.



# WebFlightTracker

WebFlightTracker est une page Web développer en React.
Elle a pour but d'afficher une carte du monde sur laquel se deplacera des icons d'avions representant les avions actuellement en vol.
De plus il y a quelque fonctionnalité en plus.

  - Fonctionnalité :
    - [x] Séléction de la date
    - [x] Nombre d'avion au dessus d'un pays
    - [x] Affichage d'un avion detaillé lors d'un clique 
