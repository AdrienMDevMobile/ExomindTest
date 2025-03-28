README

J'ai fait l'appel réseau avec Retrofit.
L'appel réseau est enrobé de la classe Result afin de gérer les erreurs.
La structure suit des principes SOLID
Les dépendances sont injectées par Hilt.


Changements à faire :
utiliser les flavours pour
Pour les réponses web mockées : les déplacer dans leurs fichiers à part.
Dans le projet, toutes les exceptions ont été rangées dans la même action par le view model.
Professionnellement, Les exceptions renvoyées doivent être gérés plus finement : s'agit t il d'une absence de reseau ? une erreur back end ?


Note : Une clé d'API doit être gardée secrète.
Je me suis contenté de ne pas la présenter dans le projet (un système plus robuste aux erreurs doit être présent dans un projet professionel)
Veuillez l'ajouter à WeatherWebDataSource la clé.
