import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;



public class IA extends Joueur{
	ArrayList <Cellule> choix ;
	HashSet<Cellule> temp ;
	int gainMax;
	char couleurStrategique;
	Cellule premier ;
	
	
	public IA(int numero,Grille grille){
		super(numero,grille);
		this.choix = new ArrayList<Cellule>();
		this.temp = new HashSet<Cellule>();
		//this.temp = new SortedSet<>();
		this.gainMax = 0;
		this.couleurStrategique= ' ';
		this.premier = new Cellule(0,0,grille);
	}
	
	public int getGainMax(){
		return gainMax;
	}
	public void setGainMax(int s){
		this.gainMax = s;
	}
	public char getCouleurStrategique(){
		return couleurStrategique;
	}
	public void setCouleurStrategique(char c){
		this.couleurStrategique = c;
	}
	
	/***********************************************************************
	 * 
	 * Fonction de choix de couleur par l'intelligence artificielle
	 * 
	 *********************************************************************/
	public String gestionClic(ActionEvent e){
		choix.clear();
		Iterator <Cellule> it = casesJouables.iterator();
		while (it.hasNext()){ //-----  On fait un tri parmi les cases jouables : les cases bloquées par l'adversaire sont exclues ------- */
			Cellule copie = it.next();
			boolean flag1 =!(grille.getcouleursbloquees().contains(copie.getcouleur()));
			boolean flag2 = copie.getcouleur() == this.couleurControlee;
			if( flag1 || flag2){
				choix.add(copie);
			}
		}
		setGainMax(0);
		
		choixStrategique(); //On recherche la couleur rapportant le plus de points
		return Character.toString(this.getCouleurStrategique());
	}
	

	public void rechercheStrategique( Cellule c, String direction){
		try{
		if(Character.toLowerCase(c.getcouleur())== Character.toLowerCase(this.saisie )){
		temp.add(c); 
		
		// ------------ Mettre à jour les cases adjacentes vers le bas --------------------------------------
		if ((direction != "haut") && (c.getligne()< grille.getlargeur()-1)){ //Si l'on ne se trouve pas à la dernière ligne : on va vers le bas et on interdit de remonter  
			rechercheStrategique(new Cellule(c.getligne()+1, c.getcolonne(), grille), "bas");
		 		}

		// -------------  Mettre a jour les cases adjacentes vers le haut ------------------------------------
		if((direction!= "bas") && (c.getligne() >= 1)){	// Si l'on ne se trouve pas sur la première ligne : on monte et on interdit de redescendre		
			rechercheStrategique(new Cellule ( c.getligne()-1, c.getcolonne(),grille),"haut");
				}
		// -------------  Mettre a jour les cases adjacentes vers la droite ----------------------------------
		if((direction!= "gauche") && (c.getcolonne() < grille.getlongueur()-1)) {// Si l'on ne se trouve pas sur la dernière colonne : on va à droite et on interdit d'aller vers la gauche 
			rechercheStrategique( new Cellule(c.getligne(), c.getcolonne()+1,grille),"droite");
				}

		// ------------ Mettre à jour les cases adjacentes vers la gauche ------------------------------------
		if((direction!= "droite") && (c.getcolonne() >= 1)){ // Si l'on ne se trouve pas sur la première colonne : on va à gauche et on interdit d'aller à roite 		
			rechercheStrategique(new Cellule(c.getligne(), c.getcolonne()-1,grille),"gauche");
				}
		 	}
		}
		catch(java.lang.StackOverflowError e){
			
		}
	}
	
	/*----------------------------------------------------------------- *
	 * choixStrategique : 	Methode permettant de choisir la couleur    *
	 * 						qui rapporte le plus de points				*
	 ---------------------------------------------------------------	*/
	public void choixStrategique(){
		
		Iterator <Cellule> it = choix.iterator();
		while(it.hasNext()){						// on parcourt l'ensemble des cases jouables 
			int points = 0;
			Cellule copie = it.next();				
			setSaisie(copie.getcouleur());			// On suppose que c'est la couleur jouée
			caseDesignee();
			temp.clear();
			Iterator<Cellule> itc = caseDesignee.iterator();  // On récupère les cases jouables ayant la même couleur
			while(itc.hasNext()){							  
				rechercheStrategique(itc.next(),"");  // Trouver toutes les cases adjacentes à itc
				}
			points = temp.size();
			if(points> this.getGainMax()){
			setGainMax(points);
			ArrayList<Cellule> lst = new ArrayList<Cellule>(temp);
			premier = lst.get(0);
			this.setCouleurStrategique(premier.getcouleur());
			}
		}
	}
}