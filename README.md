==================================================================================================================================================================================================================
Ennoncé :

Pharmacie

Dans une pharmacie, les personnes viennent
Chercher des médicaments
Commander des préparations.

Il y a plusieurs médicaments dans une pharmacie :
Certains sont de stock
Les autres doivent être commandés

Il y a 2 catégories de médicaments :
Ceux qui sont délivrables librement
Ceux pour lesquels il faut une ordonnance

Pour chaque médicament, il est possible de demander la version générique plutôt que la marque (sauf pour les préparations). Les médecins peuvent éventuellement mettre le médicament générique sur l'ordonnance.

Les génériques coûtent moins chers.

Pour commander une préparation, il faut une ordonnance.
Une préparation contient plusieurs médicaments et est délivrable le lendemain (sauf le dimanche).
S'il ne faut que 50% d'un médicament pour la préparation, il faut malgré tout payer le prix complet du médicament et le reste est donné au patient. Il peut revenir avec le reste pour une prochaine préparation.

Les médicaments commandés arrivent le lendemain (sauf le dimanche)

Les ordonnances sont délivrées par des médecins de manière électronique à la pharmacie.

Une ordonnance comprend :
Références du médecin
Références du patient
Une date de prescription
Liste de médicaments
Ingrédients pour une préparation

=======================================================================================================================================================================================================================================================

Note importante : Ce projet est une application de gestion de pharmacie développée en Java avec une interface graphique en JavaFX. Il s'agit d'un projet académique ne respectant pas le modèle MVC. Il est uniquement réalisé dans un but pédagogique.

=======================================================================================================================================================================================================================================================
Diagramme des classes et explication des fonctionnalités du projet Pharmacie
=======================================================================================================================================================================================================================================================

Voici le diagramme des classes de l'application :

Diagramme des Classes - Application Pharmacie

Légende des Classes

Médicament:

    nom: Nom du médicament
    marque: Marque du médicament (si applicable)
    generique: Indication si le médicament est générique
    prix: Prix du médicament
    quantiteStock: Quantité du médicament en stock
    prescriptionRequise: Indication si une ordonnance est requise pour le médicament

Ordonnance:

    id: Identifiant unique de l'ordonnance
    refMedecin: Référence du médecin prescripteur
    refPatient: Référence du patient
    datePrescription: Date de prescription de l'ordonnance
    listeMedicaments: Liste des médicaments prescrits (objets Médicament)
    ingredientsPreparation: Liste des ingrédients pour une préparation (si applicable)

Patient:

    nom: Nom du patient
    prenom: Prénom du patient
    adresse: Adresse du patient
    historiqueOrdonnances: Liste des ordonnances du patient (objets Ordonnance)

Commande:

    id: Identifiant unique de la commande
    dateCommande: Date de la commande
    listeMedicaments: Liste des médicaments commandés (objets Médicament)
    statut: Statut de la commande (en cours, livrée)
    Relations entre les Classes

Un médicament peut apparaître dans plusieurs ordonnances. (Relation "Plusieurs à plusieurs" entre Médicament et Ordonnance)
Une ordonnance est associée à un seul patient. (Relation "Un à plusieurs" entre Ordonnance et Patient)
Une commande peut contenir plusieurs médicaments. (Relation "Plusieurs à plusieurs" entre Commande et Médicament)
Un patient peut avoir plusieurs ordonnances. (Relation "Un à plusieurs" entre Patient et Ordonnance)

Fonctionnalités des Classes

Classe Médicament:
    Gérer les informations de base sur les médicaments (nom, marque, prix, etc.).
    Suivre la quantité de médicaments en stock.
    Identifier si une ordonnance est requise pour le médicament.

Classe Ordonnance:
    Stocker les informations d'une ordonnance (médecin, patient, date, médicaments).
    Gérer la liste des médicaments prescrits, en différenciant les médicaments génériques.
    Pouvoir inclure les ingrédients d'une préparation magistrale.

Classe Patient:
    Enregistrer les informations personnelles des patients (nom, prénom, adresse).
    Conserver l'historique des ordonnances délivrées au patient.

Classe Commande:
    Gérer les commandes de médicaments manquants.
    Suivre l'état des commandes (en cours, livrée).
    Associer les médicaments commandés à une commande spécifique.

=======================================================================================================================================================================================================================================================

Explication des fonctionnalités de l'application Pharmacie

L'application de gestion de pharmacie propose plusieurs fonctionnalités pour gérer les médicaments, les ordonnances, les patients et les commandes. Voici un aperçu des principales fonctionnalités de l'application :

Gestion des Médicaments:
    Ajouter un nouveau médicament à la liste des médicaments disponibles.
    Mettre à jour les informations d'un médicament existant (prix, stock, etc.).
    Rechercher un médicament par son nom ou sa marque.
    Afficher la liste des médicaments en stock et ceux qui doivent être commandés.
    Vérifier si une ordonnance est requise pour un médicament donné.

Gestion des Ordonnances:
    Créer une nouvelle ordonnance pour un patient.
    Ajouter des médicaments à une ordonnance, en précisant s'ils sont génériques ou de marque.
    Inclure les ingrédients d'une préparation magistrale dans une ordonnance.
    Afficher les détails d'une ordonnance (médecin, patient, médicaments prescrits, etc.).
    Rechercher les ordonnances par patient ou médecin.

Gestion des Patients:
    Enregistrer les informations personnelles d'un nouveau patient.
    Consulter l'historique des ordonnances délivrées à un patient.
    Rechercher un patient par son nom ou prénom.
    Afficher la liste des patients enregistrés dans la pharmacie.

Gestion des Commandes:
    Passer une commande pour des médicaments manquants.
    Suivre l'état des commandes (en cours, livrée).
    Recevoir les médicaments commandés le lendemain (sauf le dimanche).
    Afficher la liste des commandes en cours et celles déjà livrées.

L'application de gestion de pharmacie permet de gérer efficacement les médicaments, les ordonnances, les patients et les commandes, en offrant une interface conviviale pour les utilisateurs. Elle facilite le suivi des stocks, des prescriptions médicales et des livraisons de médicaments, contribuant ainsi à une meilleure gestion des activités pharmaceutiques.

================================================================================================================================================================================================================================================================================================================================================================================================

Conclusion

L'application de gestion de pharmacie est un outil informatique pratique pour les pharmacies, permettant de gérer efficacement les médicaments, les ordonnances, les patients et les commandes. Grâce à son interface conviviale et ses fonctionnalités avancées, elle facilite le suivi des activités pharmaceutiques et contribue à une meilleure organisation des tâches quotidiennes.

