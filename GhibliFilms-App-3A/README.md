# GhibliFilms

## Présentation
Application Android réalisé en JAVA.
Projet ESIEA 3A S2.
Matière : programmation mobile
J'ai réalisé cette application portant sur les films du Studio Ghibli. Vous retrouverez l'ensemble des films avec leurs bande annonces et des détails les concernant.

## Prérequis
- Installer Android Studio et ouvrir ce projet.
- Installer l'APK dans un smartphone possédant le système d'exploitation : Android.

## Consignes respectées :
- Appel Rest
- Écran : 3 activités et 1 fragment
- Une RecyclerView horizontal
- Une activitée pour afficher en détail un élément de l'appel rest
- Utilisation de SharedPreferences pour le stockage de donnée en cache
- Un début de design
## Fonctionnalités :

### Dans toute l'application
- Une animation lors du changement d'activité.
- Suppression de la barre des notifications et de l'action bar pour un meilleur rendu.

### Écran de bienvenue
- Une activitée de bienvenue pour rediriger vers une activitée avec la liste des films.
- Un bouton qui pourrait dans un futur proche rediriger vers un menu pour enrichir l'application.

![welcome](1.png)

### Écran de présentation des films
- Une présentation des films par leurs affiches avant d'accéder aux détails.
- Recycler view en horizontal pour une meilleure présentation des affiches de film.
- Posibilité de scroll car le synopsis du film peut être long.

![films](2.png)

### Écran des détails
- Swipe vers la droite pour revenir en arrière dans l'activité détail
- Une activitée avec les détails du film sélectionné
- Utilisation de YouTubePlayerView en suivant ce tuto : https://www.sitepoint.com/using-the-youtube-api-to-embed-video-in-an-android-app/
- Un lot de détails concernant le film sélectionné.

![details](3.png)

## Remerciements :
Mon cousin Youssef pour m'avoir prêté son OnePlus doté de l'OS Android.
Mon professeur pour ses conseils et sa réactivité.
Hayao Miyazaki pour avoir accepté de vivre et de réaliser des chefs-d'œuvre pareils.
