# Guide d'utilisation

*Jean-Frédéric Durand*

---

## Description des Interfaces

### Home (Accueil)

Il s'agit de la page d'accueil comme son nom l'indique mais également du coeur de l'application, puisque c'est d'ici qu'on accède à pratiquement toutes les autres activités.
C'est également ici qu'est répertorié la liste des plantes.

### FlowerDetail

Cette interface est accessible en effectuant un simple clic sur la liste des éléments de la page d'Accueil.
Elle renseigne des informations supplémentaires sur la plante tel que son nom, la dernière fois qu'elle a été arrosée, et sa fréquence d'arrosage. On a la possibilité de modifier le nom de la plante ainsi que de la supprimé, auquel cas vous serez redirigez vers la page d'accueil.

### AddFlowerActivity

Cette interface est accessible en cliquant sur le petit "float" bouton en bas a droite de l'écran, elle permet d'ajouter une plante. Les critères pour ajouter une plante sont de rentrer un nom (unique pour chaque plante) et une fréquence d'arrosage.
La plante sera donc crée avec pour date d'arrosage aujourd'hui.

### SettingsActivity

Cette interface est accessible via deux manières :
* le menu contextuel d'android accessible depuis des touches (physiques ou virtuelles en fonction des appareils).
* le petit bouton en haut à gauche dans la barre supérieure.

Cette activité donne la possibilité de régler la date du jour et de charger les fixtures.

#### Date d'aujourd'hui

Via le bouton modifier en face de l'affichage de la date du jour, il est possible de modifier "temporairement" la date d'aujourd'hui pour simuler l'avancement dans le temps.
Il est a noter que la date n'est pas sauvegardée en base et sera donc remise à la date du jour au prochain lancement de l'application.

#### Fixtures

Appuyer sur le bouton fixture charge automatiquement 10 plantes dans la liste des plantes avec différentes date d'arrosage.

---

## Cas d'utilisations

### Ajouter une plante

Sur l'écran d'accueil :
* Cliquer sur le bouton "+" en bas a droite.
* Entrer un nom de plante.
* Entrer une fréquence d'arrosage (en nombre de jour).
* Valider

Si la plante est crée vous recevez un message Toast vous le confirmant.

PS : La fréquence d'arrosage doit être différente de 0 ou de 1 pour respecter le cahier des charges.

### Arroser une plante

Pour arroser une plante il vous suffit de laissé appuyer plusieurs secondes sur un élément de la liste dont la couleur est différente du vert. L'application colore l'item sélectionné en vert une fois la manipulation validée. Ceci ajuste également la date de dernière arrosage de la plante à aujourd'hui.

### Modifier le nom d'une plante

Sur l'écran d'accueil :
* Effectuer un simple clic sur l'item souhaité dans la liste.
* Cliquer sur le bouton modifier.
* Entrer le nouveau nom et Cliquer sur "Ok"
