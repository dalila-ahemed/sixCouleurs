
public class OptionInfo{
	private String nombre,longueur,largeur,j1,j2,j3,j4;
	public Grille grille;
	public OptionInfo(){
		
	}
	public OptionInfo(String nombre,String longueur, String largeur,String j1,String j2,String j3,String j4){
		this.nombre=nombre;
		this.longueur=longueur;
		this.largeur = largeur;
		this.j1=j1;
		this.j2=j2;
		this.j3=j3;
		this.j4=j4;
		
	}
	
	public int getNombre(){
		return Integer.parseInt(nombre);
	}
	public int getLongueur(){
		return Integer.parseInt(longueur);
	}
	public int getLargeur(){
		return Integer.parseInt(largeur);
	}
	public String getNom(int numero){// Retourne le nom du joueur en fonction de son numéro
		String nom = "";
		switch(numero){
			case 1:
				nom=j1;
				break;
			case 2:
				nom=j2;
				break;
			case 3:
				nom=j3;
				break;
			case 4:
				nom=j4;
				break;
		}
		return nom;
	}
public boolean  validationFormulaire(){
	boolean valide = false;
	switch(this.nombre){
	case "1":
		valide = !((this.j1.equals("")));
	case "2" :
		valide =!((this.j1.equals(""))|| (this.j2.equals("")));
		break;
	case "3" :
		valide =!((this.j1.equals("")) || (this.j2.equals("")) || (this.j3.equals("")));
		break;
	case "4" :
		valide =!((this.j1.equals("")) || (this.j2.equals("")) || (this.j3.equals("")) || (this.j4.equals("")));
		break;
	}
	return valide;
	}

}