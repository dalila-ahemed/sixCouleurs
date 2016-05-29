import java.util.HashSet;

public class Cellule{
	
	private int ligne;
	private int colonne;
	private int longueur;
	private int largeur;
	private char couleur;
	private char couleurd;
	private char couleurg;
	private char couleurh;
	private char couleurb;

	private Grille grille;
	public Cellule(int ligne,int colonne,Grille grille)
	{
		this.ligne = ligne;
		this.colonne = colonne;
		this.grille = grille;
		this.longueur = grille.getlongueur();//longueur de la grille
		this.largeur = grille.getlargeur();//largeur de la grille
		this.couleur = grille.getcases()[ligne][colonne];
		if(colonne < longueur-1){
			this.couleurd = grille.getcases()[ligne][colonne+1];
		}
		if(colonne >=1 ){
			this.couleurg = grille.getcases()[ligne][colonne-1];
		}
		
		if(ligne >=1 ){
			this.couleurh = grille.getcases()[ligne-1][colonne];
		}
		
		if(ligne < largeur-1 ){
			this.couleurb = grille.getcases()[ligne+1][colonne];
		}
		
	}
	//*************** getter *******************
	public int getligne(){
		return ligne;
	}
	public int getcolonne(){
		return colonne;
	}
	public char getcouleur(){
		return couleur;
	}
	public char getcouleurd(){
		return couleurd;
	}
	public char getcouleurg(){
		return couleurg;
	}
	public char getcouleurh(){
		return couleurh;
	}
	public char getcouleurb(){
		return couleurb;
	}
	
	//************** setter *************************
	public void setcouleur(char s){
		this.couleur=s;
	}
	public void setcouleurd(char s){
		this.couleurd=s;
	}
	public void setcouleurg(char s){
		this.couleurg=s;
	}
	public void setcouleurh(char s){
		this.couleurh=s;
	}
	public void setcouleurb(char s){
		this.couleurb=s;
	}
	
	public void setligne(int l){
		this.ligne=l;
	}
	public void setcolonne(int c){
		this.colonne = c;
	}
// Les fonctions hashcode et equals ont été redéfinies pour pouvoir être utilisées avec des objets personnalisés
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + colonne;
		result = prime * result + ligne;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Cellule)) {
			return false;
		}
		Cellule other = (Cellule) obj;
		if (colonne != other.colonne) {
			return false;
		}
		if (ligne != other.ligne) {
			return false;
		}
		return true;
	}
	
public boolean voisinDroit(HashSet<Cellule> liste){
	if(colonne == longueur-1){
		return true;
	}
	return liste.contains(grille.getcase(ligne,colonne+1));
}

public boolean voisinGauche(HashSet<Cellule> liste){
	if(colonne == 0){
		return true;
	}
	return liste.contains(grille.getcase(ligne,colonne-1));
}

public boolean voisinBas(HashSet<Cellule> liste){
	if(ligne == largeur-1){
		return true;
	}
	return liste.contains(grille.getcase(ligne+1,colonne));
}

public boolean voisinHaut(HashSet<Cellule> liste){
	if(ligne == 0){
		return true;
	}
	return liste.contains(grille.getcase(ligne-1,colonne));
}
		
		
}
