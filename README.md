Branche Master: 

	infos bdd: collation: utf8_bin
	durée d'exécution à partir d'une baase vide : 22min
	durée d'exécution à partir d'une base déjà remplie : non testé
	Le programme fonctionne de la manière suivante : 
		toutes les lignes du fichiers csv sont traiter de manière à créer des HashSet de Marques, Catégories, Ingrédients, Additifs et Produits: 
 		Ordre d'insersion : 
 		- toutes les marques (Set<Marque>)
 		- toutes les catégories (Set<Categorie>)
 		- tous les ingrédients (Set<Ingredient>)
 		- tous les additifs (Set<Additif>)
 		- tous les produits (Set<Produit>)
 		
 		
Branche New_version: 

	durée d'exécution à partir d'une baase vide : 1h20min
	durée d'exécution à partir d'une base déjà remplie : 7min
	Le programme fonctionne de la manière suivante : 
		Chaque ligne du fichier csv est insérée l'une après l'autre
		Ordre d'insersion : 
		- la marque d'un produit
		- la catégorie d'un produit
		- les ingrédients d'un produit
		- les additifs d'un produit
		- le produit