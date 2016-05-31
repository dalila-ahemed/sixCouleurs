
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


public class Joueur {
//********************* Attributs ***************************
	private char saisie;//lettre choisie par le joueur
	private int score;
	private int numero;
	private String nom;
	private HashSet<Cellule> casesJouables;
	private HashSet<Cellule> casesControlees;// liste des cases controlees par un joueur
	private ArrayList<Cellule> caseDesignee;//liste des cases jouables contenant la lettre jouee
	private Grille grille;
	private char couleurControlee;//couleur bloquée aux autres joueurs
	private ArrayList<Character> couleursJouables; // couleurs des cases jouables pour le joueur


	//***************Constructeur*********************
	public Joueur(int numero,Grille grille){
		this.numero = numero;
		this.score = 1;
		this.casesJouables   = new HashSet<Cellule>();
		this.casesControlees = new HashSet<Cellule>();
		this.caseDesignee= new ArrayList<Cellule>();
		this.couleursJouables = new ArrayList<Character>();
		this.grille = grille;
		int longueur = grille.getlongueur();
		int largeur = grille.getlargeur();
		this.nom = "";
		
		switch(this.numero)
		{
			case 1 :	// Si c'est le ppremier joueur
				casesControlees.add(new Cellule(0,0,grille)); // en haut à gauche dans la grille
				this.couleurControlee = new Cellule(0,0,grille).getcouleur();
				grille.getcouleursbloquees().add(new Cellule(0,0,grille).getcouleur());
				casesJouables.add(new Cellule(0,1,grille));
				casesJouables.add(new Cellule(1,0,grille));
				break;
			case 2 :
				casesControlees.add(new Cellule(largeur-1,longueur-1,grille));
				this.couleurControlee = new Cellule(largeur-1,longueur-1,grille).getcouleur();
				grille.getcouleursbloquees().add(new Cellule(largeur-1,longueur-1,grille).getcouleur());
				casesJouables.add(new Cellule(largeur-1,longueur-2,grille));
				casesJouables.add(new Cellule(largeur-2,longueur-1,grille));
				
			break;
			case 3 :
				casesControlees.add(new Cellule(0,longueur-1,grille));
				this.couleurControlee = new Cellule(0,longueur-1,grille).getcouleur();
				grille.getcouleursbloquees().add(new Cellule(0,longueur-1,grille).getcouleur());
				casesJouables.add(new Cellule(0,longueur-2,grille));
				casesJouables.add(new Cellule(1,longueur-1,grille));
				
			break;
			case 4 :
				casesControlees.add(new Cellule(largeur-1,0,grille));
				this.couleurControlee = new Cellule(largeur-1,0,grille).getcouleur();
				grille.getcouleursbloquees().add(new Cellule(largeur-1,0,grille).getcouleur());
				casesJouables.add(new Cellule(largeur-2,0,grille));
				casesJouables.add(new Cellule(largeur-1,1,grille));
				
			break;
		}
		Iterator<Cellule> it = casesJouables.iterator();
		while(it.hasNext()){
			Cellule c = it.next();
			couleursJouables.add(c.getcouleur());
		}
		
		}
	//********************* Getter ******************

	public int getScore(){
		return score;
	}
	public int getNumero(){
		return numero;
	}
	public HashSet<Cellule> getCasesControlees(){
		return casesControlees;
	}
	
	public char getSaisie(){
		return saisie;
	}
	
	public String getNom(){
		return nom;
	}

	//*********************** Setter ***********************************
	
	public void setSaisie(char e){
		this.saisie=e;
	}
	public void setCouleurControlee (char s){
		this.couleurControlee = s;
	}
	public void setNom(String s){
		this.nom = s;
	}
	
	//**********************  Méthodes **********************************
	/*------------------------------------------------------------------------------------------------
	 * Cette fonction permet de vérifier que la couleur jouée existe, est jouable et n'est pas bloquée
	 *------------------------------------------------------------------------------------------------ */
	
	public boolean saisieValide(char entree){
		boolean flagjouable = false;
		boolean flagnonbloque = !(grille.getcouleursbloquees().contains(this.saisie)) || (this.saisie == this.couleurControlee);
		Iterator<Cellule> it = casesJouables.iterator();
			while(it.hasNext()){
				Cellule copie = it.next(); 
				int ligne=copie.getligne();
				int colonne = copie.getcolonne();
				if(entree == grille.getcases()[ligne][colonne]){
					caseDesignee.add(copie);
					flagjouable = true;
				}
			}
		boolean flag = flagjouable && flagnonbloque;
		return flag;
		}
	/* -----------------------------------------------------------------------------------
	 * Cette fonction vérifie que les cases jouables ne sont pas bloquees par un adversaire
	 *Si toutes les cases jouables sont bloquées alors on passe son tour 
	-------------------------------------------------------------------------------------*/
	public boolean blocage(){
		Iterator<Cellule> itjouable = casesJouables.iterator();
		while(itjouable.hasNext()){
			char couleurjouable = itjouable.next().getcouleur();
			if(couleurjouable == this.couleurControlee){
				return false;
			}
			if(!grille.getcouleursbloquees().contains(couleurjouable)){
				return false;
			}
		}
		return true;
	}
	
	/*---------------------------------------------------------------------
	 * Cette fonction récupère les cases jouables de la même couleur que celle choisie par le joueur
	 ----------------------------------------------------------------------*/
	public void caseDesignee(){
		caseDesignee.clear();
		Iterator <Cellule> it = casesJouables.iterator();
		while(it.hasNext()){
			Cellule copie = it.next();
			int ligne = copie.getligne();
			int colonne = copie.getcolonne();
			if(grille.getcases()[ligne][colonne] == this.saisie){
				caseDesignee.add(copie);
			}
	}
	}
	public void modifierGrille(){
		ArrayList<Character> couleursJouables = new ArrayList<Character>();
		caseDesignee();//On enregistre les cases de la couleur tapée par le joueur dans la liste caseDesignee
				
		Iterator<Cellule> itc = caseDesignee.iterator();
		while(itc.hasNext()){
			recherche(itc.next(),"");
		}		
					
       majuscule();
       this.score=calcul_score();
		Iterator<Cellule> it = casesJouables.iterator();
		while(it.hasNext()){
			Cellule t = it.next();
			couleursJouables.add(t.getcouleur());
		}
		
		jouableBis();
		grille.imprime();
		
	}
	


/*-------------------------------------------------------------------
 * Methode permettant de mettre à jour la liste des cases adjacentes
 * Le paramètre est la case initiale : parametre ligne et colonne
 ---------------------------------------------------------------------*/
	public void recherche( Cellule c, String direction){
		if(Character.toLowerCase(c.getcouleur())== Character.toLowerCase(this.couleurControlee )){
		casesControlees.add(c); 
		
		// ------------ Mettre à jour les cases adjacentes vers le bas --------------------------------------
		if ((direction != "haut") && (c.getligne()< grille.getlargeur()-1)){ //Si l'on ne se trouve pas à la dernière ligne : on va vers le bas et on interdit de remonter  
		recherche(new Cellule(c.getligne()+1, c.getcolonne(), grille), "bas");
		 		}

		// -------------  Mettre a jour les cases adjacentes vers le haut ------------------------------------
		if((direction!= "bas") && (c.getligne() >= 1)){	// Si l'on ne se trouve pas sur la première ligne : on monte et on interdit de redescendre		
		recherche(new Cellule ( c.getligne()-1, c.getcolonne(),grille),"haut");
				}
		// -------------  Mettre a jour les cases adjacentes vers la droite ----------------------------------
		if((direction!= "gauche") && (c.getcolonne() < grille.getlongueur()-1)) {// Si l'on ne se trouve pas sur la dernière colonne : on va à droite et on interdit d'aller vers la gauche 
		recherche( new Cellule(c.getligne(), c.getcolonne()+1,grille),"droite");
				}

		// ------------ Mettre à jour les cases adjacentes vers la gauche ------------------------------------
		if((direction!= "droite") && (c.getcolonne() >= 1)){ // Si l'on ne se trouve pas sur la première colonne : on va à gauche et on interdit d'aller à roite 		
				recherche(new Cellule(c.getligne(), c.getcolonne()-1,grille),"gauche");
				}
		 	}
	}
	
	/* ----------------------------------------------------------
	 * Methode : remplir_casesControlees
	 * Cette méthode sert à recupérer les cellules de la meme couleur
	 * et de les mettre dans la liste des cases contrôlées 
	 ---------------------------------------------------------------*/
	
	public void majuscule(){
		Iterator<Cellule> iter = casesControlees.iterator();
		while(iter.hasNext()){
			Cellule copie = iter.next();
			grille.setcases(copie.getligne(), copie.getcolonne(),Character.toUpperCase(caseDesignee.get(0).getcouleur()));
		}
		
	}
	
	
	public void jouableBis(){
		
		casesJouables.clear();
		
		Iterator<Cellule> it = casesControlees.iterator();
		while(it.hasNext()){
			Cellule copie = it.next();      
			if(!copie.voisinDroit(casesControlees)){ //la cellule à droite n'est pas controlee
				Cellule droite = new Cellule(copie.getligne(),copie.getcolonne()+1,grille);
				if  (!(droite.getcouleur() > 'A'  && droite.getcouleur() <'Z')){ 
					casesJouables.add(droite);
				}
			}
			if(!copie.voisinGauche(casesControlees)){//la cellule à gauche n'est pas controlee
				Cellule gauche = new Cellule(copie.getligne(),copie.getcolonne()-1,grille);
				if  (!(gauche.getcouleur() > 'A'  && gauche.getcouleur() <'Z')){ 
					casesJouables.add(gauche);	
				}
			}
			
			if(!copie.voisinBas(casesControlees)){//la cellule du bas n'est pas controlee
				Cellule bas = new Cellule(copie.getligne()+1,copie.getcolonne(),grille);
				if  (!(bas.getcouleur() > 'A'  && bas.getcouleur() <'Z')){ 
					casesJouables.add(bas);
				}
			}
			if(!copie.voisinHaut(casesControlees)){//la cellule du haut n'est pas controlee
				Cellule haut = new Cellule(copie.getligne()-1,copie.getcolonne(),grille);
				if  (!(haut.getcouleur() > 'A'  && haut.getcouleur() <'Z')){ 
					casesJouables.add(haut);
				}
			}
		}
	}	
		
	public int calcul_score(){
		int score = getScore();
		HashSet<Cellule> controle=getCasesControlees();
		score = controle.size();
		return score;
	}

}