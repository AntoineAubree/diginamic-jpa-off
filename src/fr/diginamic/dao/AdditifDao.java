package fr.diginamic.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Additif;
import fr.diginamic.entites.Produit;

public class AdditifDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	public AdditifDao() {
	}

	void insererListeDepuisProduit(Produit produit) {
		Set<Additif> additifsSelect = new HashSet<>();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		for (Additif additif : produit.getAdditifs()) {
			TypedQuery<Additif> query = em.createQuery("SELECT a FROM Additif a WHERE a.nom = :nom_additif",
					Additif.class);
			query.setParameter("nom_additif", additif.getNom());
			List<Additif> additifSelect = query.getResultList();
			
			if (additifSelect.isEmpty()) {
				em.persist(additif);
				additifsSelect.add(additif);
			} else {
				additifsSelect.add(additifSelect.get(0));
			}
		}
		produit.setAdditifs(additifsSelect);
		transaction.commit();

	}

}
