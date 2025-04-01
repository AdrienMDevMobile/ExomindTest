README
J'ai fait la NAVIGATON entre les deux écrans.

J'ai fait l'appel réseau avec RETROFIT.

J'ai utilisé FLOW pour la paralélisation

L'appel réseau est enrobé de la classe Result afin de gérer les erreurs.

Une présentation de l'intercepteur de reponse http est présent en commentaire (pour faire des tests sur les retours possibles)

La structure suit des principes SOLID

Pratiques clean code : le minimum de commentaires, le nom des fonctions et leurs sous divisions servant à rendre le code plus fluide

Les dépendances sont injectées par HILT.

Loading bar à partir de mon ancien projet personnel : https://github.com/AdrienMDevMobile/LoadingButton/
Qui a ensuite été adapté au projet (notamment le besoin de notifier la fin de l'animation "terminée" afin de la transformer en bouton)

Utilisation des resources catalogues pour les logos



Non fait : une gestion des erreurs API plus poussé.
Sans plus d'indication sur ce qu'il fallait faire, je me suis contenté d'ignorer des erreurs api



Changements que j'aurai fait dans un vrai projet :

Pour l'écran WeatherScreen : Créer une seule classe UiState qui indique tout le design de la page.

utiliser les flavours pour mettre ou non les intercepteur logger d'appel http

Pour les réponses web mockées : les déplacer dans leurs fichiers à part.

Dans le projet, toutes les exceptions ont été rangées dans la même action par le view model. 

Professionnellement, Les exceptions renvoyées doivent être gérés plus finement : s'agit t il d'une absence de reseau ? une erreur back end ?

Perfectionner les Previews de la loading bar afin de pouvoir acceler les tests

Rendre les commits plus propres : regrouper/séparer en fonction des sujets. Rendre les commits les plus fins et rapidement lisibles possibles.

Ajouter des tests automatisés (notamment la partie domaine)

Note : Une clé d'API doit être gardée secrète.
Je me suis contenté de ne pas la présenter dans le projet (un système plus robuste aux erreurs doit être présent dans un projet professionel)
Veuillez l'ajouter à WeatherWebDataSource la clé.
