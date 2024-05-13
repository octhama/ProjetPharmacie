Note importante
=======================================================================================================================================================================================================================================================

Ce projet est une application de gestion de pharmacie développée en Java avec une interface graphique en JavaFX. Il s'agit d'un projet académique ne respectant pas le design pattern MVC(Model-View-Controller). Il est uniquement réalisé dans un but pédagogique. Des fichiers CSV font office de base de données pour stocker les informations des médicaments, des patients, des ordonnances et des commandes.

Pharmacie
=======================================================================================================================================================================================================================================================
Contexte
=======================================================================================================================================================================================================================================================

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

Diagramme des classes et explication des fonctionnalités du projet Pharmacie
=======================================================================================================================================================================================================================================================

Voici le diagramme des classes de l'application :

Diagramme des Classes - Application Pharmacie (proposition d'analyse UML général pour une application de gestion de pharmacie)

Légende des Classes

Médicament:

    nom: Nom du médicament
    marque: Marque du médicament (si applicable)
    generique: Indication si le médicament est générique
    prix: Prix du médicament
    quantiteStock: Quantité du médicament en stock
    prescriptionRequise: Indication si une ordonnance est requise pour le médicament

Pharmacie:

    listeMedicaments: Liste des médicaments disponibles (objets Médicament)
    listePatients: Liste des patients enregistrés (objets Patient)
    listeOrdonnances: Liste des ordonnances enregistrées (objets Ordonnance)
    listeCommandes: Liste des commandes en cours (objets Commande)

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

Pharmacien:

    nom: Nom du pharmacien
    prenom: Prénom du pharmacien
    adresse: Adresse du pharmacien
    pharmacie: Nom de la pharmacie
    listeCommandes: Liste des commandes traitées par le pharmacien (objets Commande)

Médecin:
    
        nom: Nom du médecin
        prenom: Prénom du médecin
        specialite: Spécialité du médecin
        listeOrdonnances: Liste des ordonnances prescrites par le médecin (objets Ordonnance)

Commande:

    id: Identifiant unique de la commande
    dateCommande: Date de la commande
    listeMedicaments: Liste des médicaments commandés (objets Médicament)
    statut: Statut de la commande (en cours, livrée)
    Relations entre les Classes

Préparation:

    listeMedicaments: Liste des médicaments composant la préparation
    quantiteMedicaments: Quantité de chaque médicament nécessaire pour la préparation

DemandeVersionGenerique:

    listeMedicaments: Liste des médicaments pour lesquels la version générique est demandée

Relations entre les Classes

    La classe Médicament est associée à la classe Ordonnance par l'intermédiaire de la classe Préparation, qui permet de définir les médicaments nécessaires pour une préparation.
    La classe Ordonnance est associée à la classe Patient, qui permet de conserver l'historique des ordonnances délivrées à un patient.
    La classe Ordonnance est associée à la classe Médecin, qui permet de conserver la liste des ordonnances prescrites par un médecin.
    La classe Commande est associée à la classe Médicament, qui permet de gérer les médicaments commandés dans une commande spécifique.
    La classe Commande est associée à la classe Pharmacien, qui permet de conserver la liste des commandes traitées par un pharmacien spécifique.

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

Classe Pharmacie:
    Gérer les listes de médicaments, patients, ordonnances et commandes.
    Permettre d'ajouter, de modifier et de supprimer des éléments de ces listes.
    Proposer des fonctionnalités de recherche et de consultation des données.

Classe Préparation:
    Stocker les informations sur les médicaments composant une préparation.
    Déterminer la quantité de médicaments nécessaires pour une préparation.

Classe Pharmacien:
    Gérer les informations personnelles du pharmacien (nom, prénom, adresse, etc.).
    Associer les commandes traitées par le pharmacien à son profil.

Classe Médecin:
    Stocker les informations personnelles du médecin (nom, prénom, spécialité, etc.).
    Conserver la liste des ordonnances prescrites par le médecin.

Explication des fonctionnalités de l'application Pharmacie
=======================================================================================================================================================================================================================================================

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

Gestion des Préparations:
    Créer une nouvelle préparation magistrale à partir d'une ordonnance.
    Ajouter les médicaments nécessaires à la préparation, en précisant les quantités.
    Calculer le coût total de la préparation en fonction des médicaments utilisés.
    Gérer les restes de médicaments pour les préparations suivantes.

Gestion des Pharmaciens:
    Enregistrer les informations personnelles d'un pharmacien (nom, prénom, adresse, etc.).
    Associer les commandes traitées par le pharmacien à son profil.
    Afficher la liste des commandes traitées par un pharmacien spécifique.

Gestion des Médecins:
    Enregistrer les informations personnelles d'un médecin (nom, prénom, spécialité, etc.).
    Conserver la liste des ordonnances prescrites par le médecin.
    Rechercher un médecin par son nom ou sa spécialité.


L'application de gestion de pharmacie permet de gérer efficacement les médicaments, les ordonnances, les patients et les commandes, en offrant une interface conviviale pour les utilisateurs. Elle facilite le suivi des stocks, des prescriptions médicales et des livraisons de médicaments, contribuant ainsi à une meilleure gestion des activités pharmaceutiques.

Conclusion
=======================================================================================================================================================================================================================================================

L'application de gestion de pharmacie est un outil informatique pratique pour les pharmacies, permettant de gérer efficacement les médicaments, les ordonnances, les patients et les commandes. Grâce à son interface conviviale et ses fonctionnalités avancées, elle facilite le suivi des activités pharmaceutiques et contribue à une meilleure organisation des tâches quotidiennes.

