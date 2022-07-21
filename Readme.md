# Rapport, Readme BlackJack

> Présenté par Léo Chardon, à l'ESIEE Paris en E4FI

Ce rapport à pour but de présenté et parler du mini projet BlackJack réalisé avec Android Studio dans le cadre du TP noté de développement mobile.

Voici les fonctionnalités implémenté:

 1.  Barre de mise interactive et dynamique
 2.  Bouton interactive et dynamique forçant un "meilleure" gameplay
 3. Changement de langue redémarrant l'application 
 4. Changement de nom de joueur, d'argent initial ou de nombre de deck redémarrant une partie 
 5. Réorganisation des layouts en mode paysage, redémarre l'activité
 6. Ecran d'Accueil "personnalisé" (en attendant une mise)
 7. Utilisation de la police Poppin en blanc. 
## Fonctionnalités

Comme présenté ci dessus, mon jeu offre un gameplay plutot complet et satisfaisant parfois, lorsque nous démarrons l'app, la partie ne commence pas aussitôt, il faut miser ! Et lorsque le joueur mise (pallier de 300€ ou $ pour éviter que le joueur mise trop, comme dans la vraie vie parfois) il voit son argent diminuer progressivement et dynamiquement !

Lorsqu'il gagne une manche sa mise est doublé (triplé si 21) et il vois donc son argent mis à jour et lorsqu'il perd alors l'argent misé tombe à 0.
Lorsque le joueur veut rejouer en appuyant sur Reset, alors il a la possibilité de rémiser et **l'argent affiché sur la mise bar à la fin est directement déduite de l'argent dont il dispose, soit une déduction de 300 maximum**, c'est marquant encore plus si il gagne car l'application par défaut va proposer de remiser avec l'argent actuelle de la mise. (3 variables sont utilisés pour ce processus de mise dynamique).

Le joueur peut changer son nom de joueur, le nombre de deck, son argent initial, ces changement viendrons restart une partie.

Lorsqu'il change la langue, alors l'application redémarre.

Le changement de la couleur de fond ne touche pas à la partie ou à l'application.

## Difficultés rencontrés

Au cours de la réalisation du TP, j'ai rencontré plusieurs difficultés, certaines ont été résolues, d'autres non.

 - Changement de la langue dynamique
 - Changement en mode paysage dynamique
 - Boite de dialogue et récupération des objets d'un autre layout
 - Constraint Layout

Pour le changement de la langue dynamique, je ne vois pas comment utiliser les valeurs string dynamiquement sans condition de test pour savoir quelle est la langue actuelle, alors j'ai utilisé une classe annexe (récupérée sur internet) pour changer la langue actuelle et ainsi redémarrer l'application,

J'aurais pu faire en sorte d'utiliser des conditions vérifiant la langue pour chacune des mes variables textes mais cela n'aurait pas rendu propre le code et dynamique en cas d'une nouvelle variable ajoutée.

Un groupe m'a cependant dit que c'était possible mais il fallait redéfinir alors tous les widgets impliquant les variables textuelles, cela aurait été long pour moi et je ne voulais pas recopier les travaux de ce groupe.

Pour le changement en mode paysage sans redémarrer l'activité j'ai cru que j'allais pouvoir faire ça vite en ajoutant au bundle mon objet BlackJack et en le récupérant en cas de changement paysage<->portrait cependant le bundle ne prend d'Objet Java directement ...

Une solution de contournement aurait été d'utiliser le Bundle plus l'interface Java Serializable afin de sauvegarder notre gros Objet et les petites variables ainsi que les réafficher.

La boîte de dialog à soulevé un mystère chez moi car elle est assez indépendante du code et se trouve finalement dans une seule grosse fonction ... Lorsqu'avant je la définissais comme toute mes autres variables au début il m'était impossible de récupérer les valeurs des EditText ainsi que des Radiobouton, même après l'appelle de `LayoutInflater inflater = getLayoutInflater();  
View dialog = inflater.inflate(R.layout.diag_config,null);`
J'ai donc réussi à combler mon problème en mettant tout ce dont j'avais besoin dans une seule grosse fonction.

L'utilisation des constraints Layout m'a aussi posé quelque problème notamment lorsque mon téléphone était au mode paysage, j'ai donc passé beaucoup de temps sur l'élaboration graphique. L'organisation des widgets présente sur Android Studio était différente de celle affiché sur mon appareil test (ici, mon téléphone).

## Bilan personnel et conclusion

Dans l'ensemble, le TP était plutôt intéressant et le fait d'avoir eu une semaine en plus m'a permis d'approfondir un peu plus les fonctionnalités (Je suis seul sur ce TP). Cependant le fait d'avoir passé du temps sur les layouts et parfois d'autres fonctionnalités finalement pas implémenté est frustrant. 
Je pense que c'est une bonne opportunité d'avoir pu faire ce TP. Cependant en terme de développement mobile nous sommes soumis à la technologie Android, je dirais que celle-ci est utile dans le cas d'une très grande application. Cependant dans le cas d'un jeu de carte sur mobile, certaines autres techno nous permettent de faire des plus rapidement à la fois pour IOS et Android , je pense notamment à Flutter, développé par Google et écrit en Dart ou bien Kivy, développé en Python et Cython et enfin Unity, développé en C et C#.
