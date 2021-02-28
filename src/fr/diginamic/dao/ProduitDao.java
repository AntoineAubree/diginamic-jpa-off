package fr.diginamic.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Produit;

public class ProduitDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();
	private MarqueDao marqueDao = new MarqueDao();
	private CategorieDao categorieDao = new CategorieDao();
	private IngredientDao ingredientDao = new IngredientDao();
	private AdditifDao additifDao = new AdditifDao();

	public ProduitDao() {
	}

	public void insererSet(Set<Produit> produits) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		int compteur = 1;
		for (Produit produit : produits) {
			System.out.println(compteur++);
			TypedQuery<Produit> query = em.createQuery("SELECT p FROM Produit p Join p.marque m WHERE p.nom = :nom_produit AND m.nom = :nom_marque",
					Produit.class);
			query.setParameter("nom_produit", produit.getNom());
			query.setParameter("nom_marque", produit.getMarque().getNom());
			List<Produit> produitSelect = query.getResultList();
			
			if (produitSelect.isEmpty()) {
				
				marqueDao.insererDepuisProduit(produit);
				categorieDao.insererDepuisProduit(produit);
				ingredientDao.insererListeDepuisProduit(produit);
				additifDao.insererListeDepuisProduit(produit);
				
				em.persist(produit);
			}
			
		}

		transaction.commit();
	}

}
