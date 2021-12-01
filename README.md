# RogueLikeL3
__Projet RogueLike (Conception, 3ème année de Licence)__
BOMPAIS Solal, LESBROS Raphaël, PUPET Gaëtan (G2)


__Execution :__
Se placer dans le repertoire 'RogueLike' et exécuter ``gradle build`` puis ``gradle run``


__Contrôles :__
	Déplacements : Z-Q-S-D ( = HAUT-GAUCHE-BAS-DROITE)
	Menus :
		Carte : M
		Inventaire : I
		Quitter un menu : ESCAPE
	Interagir (ramasser objet au sol ou attaquer ennemi) : F


__Carte :__
	Sur la carte apparait les salles, portes, et ce qu'il y a à l'interieur de la salle grace à un code couleur.
		- Bleu : position du joueur
		- Rose : position du boss
		- Jaune : au minimum un objet
		- Rouge : au minimum un monstre

__Fonctionnalités :__
Notre RogueLike implémente les fonctionnalités notables suivantes :
	- Possibilité de se déplacer dans une salle comportant des collisions, et d'en changer aux sorties
	- Carte générée aléatoirement sous la forme d'un labyrinthe, visible dans un écran dédié
	- Possibilité d'intéragir avec des objets au sol, des pièges et des ennemis
	- Inventaire permettant d'utiliser des objets, ou d'en équiper (un seul à la fois)
	- Système de combat au tour par tour : le joueur tape, suivi de l'ennemi


__Implémentations notables dans le code :__
Le code a été fait avec pour objectif de respecter un maximum les principes vus en cours, et de rendre aussi simple que possible les itérations et améliorations du jeu :
Entre autres, plusieurs design patterns ont été utilisés :
	Composite : Nous avons codé un système de Sprites pouvant être composés d'autres sprites, et Composite était le patron adapté. Il en a été de même pour les collisions.
	State : Le jeu pouvant être dans plusieurs états (Jeu, Combat, GameOver, Inventaire, Map), nous avons implémenté le pattern State
	Template Method : Ce pattern a été utilisé plusieurs fois, notamment pour Room, une classe abstraite dont la fonction generate initialise la grille de Tile, puis appelle une fonction abstraite, generateRoom() avant d'actualiser son sprite et ses collisions.

A part les patrons de conceptions utilisés, nous avons régulièrement utilisé des abstractions, des interface et des classes abstraites, afin de respecter au mieux les principes de développement.

__Notes :__
Certaines implémentations n'ont peut être pas été nécessaires ou optimales, cependant, connaissant très peu JavaFX, nous avons fait de notre mieux dans le temps imparti.

Le jeu prend du temps à se lancer, car la création du labyrithe n'est pas optimale.


__Bugs non corrigé :__
	- Mur sans collision : Nous avons préferé retirer totalement les collisions avec les murs des salles, car dans le cas contraire, le personnage pouvait rester bloqué à l'intérieur de l'un d'eux
	- De temps à autre, le joueur ne passe pas au travers des portes
	- Ayant tout les trois un ordinateur puissant, nous ne pouvons pas savoir si le jeu tourne bien, donc nous n'excluons pas la possibilité que le jeu puisse tout simplement planter pour certain ordinateur
