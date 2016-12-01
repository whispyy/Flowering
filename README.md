# TP Android - Flowering

*Jean-Frédéric Durand*

---

# Liste des fichiers

- Fichiers source : Projet/
  + Contient toutes les sources du projet en respectant la hierarchie android studio.
- Guide d'utilisation : GuideUtilisation/
  + Version markdown + Version PDF
- Rapport technique : Doc/
  + Version markdown + Version PDF
- APK signée : APK/Flowering.apk

# Différences par rapport au cahier des charges

- Nous pouvons arroser une plante même si elle n'en a pas besoin. C'est-à-dire qu'une plante avec le fond vert peut être arrosée.

# Bug identifiés :

- Les conditions stipulent qu'il ne faut pas créer deux plantes avec des noms identiques. En réalité c'est possible, cependant je récupère les plantes via une requête sur le nom de la plante (pour des questions de simplicités).

# Note technique :

- API utilisée : API 15 (Android 4.0.3)
- J'ai testé mon application sur trois appareils :
  + Samsung Galaxy S3 : Android 5.0.1 (Aucun problème)
  + Samsung Galaxy S2 : Android 4.2 (Aucun problème)
  + Elephone Q : Android 4.3
