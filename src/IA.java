import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class IA extends Joueur{
	
	public IA(int numero,Grille grille){
		super(numero,grille);
	}
	
	/***********************************************************************
	 * 
	 * Fonction de choix de couleur par l'intelligence artificielle
	 * 
	 *********************************************************************/
	public String gestionClic(ActionEvent e){
		ArrayList <Cellule> choix = new ArrayList<Cellule>();
		Iterator <Cellule> it = casesJouables.iterator();
		while (it.hasNext()){ // On fait un tri parmi les cases jouables : les cases bloquées par l'adversaire sont exclues
			Cellule copie = it.next();
			if(!(grille.getcouleursbloquees().contains(copie.getcouleur()) || (copie.getcouleur() == this.couleurControlee))){
				choix.add(copie);
			};
		}
		//Cellule hasard = it.next();
		return Character.toString(choix.get(0).getcouleur());
	}
		
	
	/*public void modifierGrille(){
		//A modifier demain! 
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
		
	}*/
}