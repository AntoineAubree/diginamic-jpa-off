package fr.diginamic.entites;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Produit {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "nom")
	private String nom;
	@Column(name = "nutrition_grade_fr")
	private String nutritionGradeFr;
	@Column(name = "energie_100g")
	private float energie100g;
	@Column(name = "graisse_100g")
	private float graisse100g;
	@Column(name = "sucres_100g")
	private float sucres100g;
	@Column(name = "fibres_100g")
	private float fibres100g;
	@Column(name = "proteines_100g")
	private float proteines100g;
	@Column(name = "sel_100g")
	private float sel100g;
	@ManyToOne
	@JoinColumn(name = "id_categorie")
	private Categorie categorie;
	@ManyToOne
	@JoinColumn(name = "id_marque")
	private Marque marque;
	@ManyToMany
	@JoinTable(name = "produit_ingredient", joinColumns = @JoinColumn(name = "id_produit", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_ingredient", referencedColumnName = "id"))
	private Set<Ingredient> ingredients = new HashSet<>();
	@ManyToMany
	@JoinTable(name = "produit_additif", joinColumns = @JoinColumn(name = "id_produit", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_additif", referencedColumnName = "id"))
	private Set<Additif> additifs = new HashSet<>();

	public Produit() {
	}

	public Produit(String nom) {
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNutritionGradeFr() {
		return nutritionGradeFr;
	}

	public void setNutritionGradeFr(String nutritionGradeFr) {
		this.nutritionGradeFr = nutritionGradeFr;
	}

	public float getEnergie100g() {
		return energie100g;
	}

	public void setEnergie100g(float energie100g) {
		this.energie100g = energie100g;
	}

	public float getGraisse100g() {
		return graisse100g;
	}

	public void setGraisse100g(float graisse100g) {
		this.graisse100g = graisse100g;
	}

	public float getSucres100g() {
		return sucres100g;
	}

	public void setSucres100g(float sucres100g) {
		this.sucres100g = sucres100g;
	}

	public float getFibres100g() {
		return fibres100g;
	}

	public void setFibres100g(float fibres100g) {
		this.fibres100g = fibres100g;
	}

	public float getProteines100g() {
		return proteines100g;
	}

	public void setProteines100g(float proteines100g) {
		this.proteines100g = proteines100g;
	}

	public float getSel100g() {
		return sel100g;
	}

	public void setSel100g(float sel100g) {
		this.sel100g = sel100g;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void addIngredient(Ingredient ingredient) {
		this.getIngredients().add(ingredient);
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Set<Additif> getAdditifs() {
		return additifs;
	}

	public void addAdditif(Additif additif) {
		this.getAdditifs().add(additif);
	}

	public void setAdditifs(Set<Additif> additifs) {
		this.additifs = additifs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Produit [id=");
		builder.append(id);
		builder.append(", nom=");
		builder.append(nom);
		builder.append(", nutritionGradeFr=");
		builder.append(nutritionGradeFr);
		builder.append(", energie100g=");
		builder.append(energie100g);
		builder.append(", graisse100g=");
		builder.append(graisse100g);
		builder.append(", sucres100g=");
		builder.append(sucres100g);
		builder.append(", fibres100g=");
		builder.append(fibres100g);
		builder.append(", proteines100g=");
		builder.append(proteines100g);
		builder.append(", sel100g=");
		builder.append(sel100g);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((marque == null) ? 0 : marque.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produit other = (Produit) obj;
		if (marque == null) {
			if (other.marque != null)
				return false;
		} else if (!marque.equals(other.marque))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

}
