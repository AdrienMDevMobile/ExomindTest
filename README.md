README

J'ai fait l'appel réseau avec Retrofit.
L'appel réseau est enrobé de la classe Result afin de gérer les erreurs.
Une présentation de l'intercepteur de reponse http est présent en commentaire (pour faire des tests sur les retours possibles)
La structure suit des principes SOLID
Les dépendances sont injectées par Hilt.
Loading bar à partir de mon ancien projet personnel : https://github.com/AdrienMDevMobile/LoadingButton/
Utilisation du Flow pour parraléliser les messages de chargement et l'affichage de la barre
Utilisation des resources catalogues pour les logos

Changements à faire :
utiliser les flavours pour mettre ou non les intercepteur logger d'appel http
Pour les réponses web mockées : les déplacer dans leurs fichiers à part.
Dans le projet, toutes les exceptions ont été rangées dans la même action par le view model.  Professionnellement, Les exceptions renvoyées doivent être gérés plus finement : s'agit t il d'une absence de reseau ? une erreur back end ?
Perfectionner les Previews de la loading bar afin de pouvoir acceler les tests
Rendre les commits plus propres : regrouper/séparer en fonction des sujets. Rendre les commits les plus fins et rapidement lisibles possibles.

Note : Une clé d'API doit être gardée secrète.
Je me suis contenté de ne pas la présenter dans le projet (un système plus robuste aux erreurs doit être présent dans un projet professionel)
Veuillez l'ajouter à WeatherWebDataSource la clé.
