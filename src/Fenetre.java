 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.*;

 
public class Fenetre extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private LinkedList<Joueur> joueurs;
	private String clic;
	private static JPanel contentGrille;
	private static  Grille grille;
	private JPanel cadre;
	private JLabel regle;
	private int longueur;
	private int largeur;
	private JLabel message = new JLabel ();
	private Joueur joueur;
	private Iterator<Joueur> itJoueur;
	

	public Fenetre(){
		//Constructeur sans argument pour la fenêtre de règles du jeu
		this.setSize(new Dimension(650,500));
		this.setBackground(Color.white);
		regle = new JLabel();
		this.setContentPane(regle);
		this.setVisible(true);	
	}
	public void texte(){
		regle.setBackground(Color.WHITE);
		regle.setText("<html><body><h2 font size = 10 >Règles du jeu : </h2><font size = 5 ><p>   Le jeu des 6 couleurs est un jeu de stratégie "
				+ "se déroulant sur un plateau (carré ou rectangulaire) dont les cases peuvent-être rouges, oranges, jaunes, vertes, bleues ou indigos.<br/>"
				+ " On peut y jouer à 2,3 ou 4.<br/> Au début de la partie chaque joueur possède l'un des coins.<br/>"
				+ " Chaque joueur ne peut jouer que les cases voisines à celles qu'il contrôle."
				+ " Il ne peut pas choisir les couleurs contrôlées par ses adversaires et lorsqu'il est bloqué il passe la main.<br/>"
				+ " La partie s'arrête lorsque toutes les cases sont contrôlées, ou lorsque tous les joueurs sont bloqués "
				+ " ou encore lorsque plus de la moitié des cases sont possédées par un même joueur."
				
				+ "</p></font></body></html>");
	}
	
	
    public Fenetre(int largeur,int longueur, Grille grille,LinkedList<Joueur> joueurs){
    	contentGrille = new JPanel();
    	cadre = new JPanel();
    	//On définit le layout manager
    	cadre.setLayout(new BorderLayout());
    	//Création de la fenêtre principale
    	this.longueur=longueur;
    	this.largeur = largeur;
    	this.joueurs = joueurs;
    	this.itJoueur = joueurs.listIterator();
    	this.joueur  = itJoueur.next(); // On pointe sur le premier joueur
    	this.message = new JLabel();
    	this.setTitle("Jeu des 6 couleurs");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); //fenêtre positionnée au centre de l'écran
        this.setAlwaysOnTop(true);
      //On définit le layout manager
        contentGrille.setLayout(new GridLayout(this.largeur,this.longueur));
        this.setContentPane(cadre);//On prévient notre JFrame que notre JPanel sera son content pane
        this.setVisible(true);
        this.setResizable(false);
    }
 
    //Getter
    public LinkedList<Joueur> getJoueurs(){
    	return joueurs;
 
    }
    
    
    public static Grille getGrille() {
		return grille;
	}
     
    
    public void fenetrePrincipale(){
    	fenetreCentrale();
    	fenetreCouleurs();
    	fenetreScore();
    	afficheMessage(joueur.getNom()+" à toi de jouer");
    }
    public void fenetreCentrale(){
    	
    	//contentGrille.setPreferredSize(new Dimension(400,400));
    	creationGrille();
    	cadre.add(contentGrille, BorderLayout.CENTER);
  
    }
    public void fenetreCouleurs(){
    	JPanel fenetreDroite = new JPanel();
    	fenetreDroite.setBackground(Color.WHITE);
    	fenetreDroite.setPreferredSize(new Dimension(80,400));
    	
    	JButton rouge = new JButton("r");
    	rouge.setBackground(Color.red);
    	rouge.setPreferredSize(new Dimension(50,50));
    	rouge.addActionListener(this);
    	
    	JButton orange = new JButton("o");
    	orange.setBackground(new Color(255,159,0));
    	orange.setPreferredSize(new Dimension(50,50));
    	orange.addActionListener(this);
    	
    	JButton jaune = new JButton("j");
        jaune.setBackground(Color.YELLOW);
        jaune.setPreferredSize(new Dimension(50,50));
    	jaune.addActionListener(this);
    	
    	JButton vert = new JButton("v");
    	vert.setBackground(Color.GREEN);
    	vert.setPreferredSize(new Dimension(50,50));
    	vert.addActionListener(this);
    	
    	JButton bleu = new JButton("b");
    	bleu.setBackground(new Color(0,153,204));
    	bleu.setPreferredSize(new Dimension(50,50));
    	bleu.addActionListener(this);
    	
    	JButton indigo = new JButton("i");
    	indigo.setBackground(new Color(153,0,204));
    	indigo.setPreferredSize(new Dimension(50,50));
    	indigo.addActionListener(this);
    	
    	
        fenetreDroite.add(rouge);
        fenetreDroite.add(orange);
        fenetreDroite.add(jaune);
        fenetreDroite.add(vert);
        fenetreDroite.add(bleu);
        fenetreDroite.add(indigo);
        
   
    	cadre.add(fenetreDroite,BorderLayout.EAST);
    }
    public void fenetreScore(){
    	JPanel fenetreBas = new JPanel();
    	fenetreBas.setBackground(new Color(0,51,102));
    	fenetreBas.setBorder(BorderFactory.createBevelBorder(1));
    	fenetreBas.setPreferredSize(new Dimension(500,125));
    	fenetreBas.add(message);
    	
    	
    	cadre.add(fenetreBas,BorderLayout.SOUTH);
    }
    public String nomJoueur(int ligne, int colonne){
    	String nom = "";
    	Iterator<Joueur> it = getJoueurs().listIterator();
    	
    	while(it.hasNext()){
    		Joueur j = it.next();
    		Iterator<Cellule> it2 = j.getCasesControlees().iterator();
    		while(it2.hasNext()){
    			Cellule c = it2.next();
    			if(c.getligne() == ligne && c.getcolonne()== colonne){
    				nom = j.getNom();
    			}
    		}
    	}
    	return nom;
    }
	public  void creationGrille(){
		JButton bouton ;
		contentGrille.removeAll();
		for(int i=0; i<this.largeur;i++){
        	for(int j=0;j<this.longueur;j++){  	
        		char couleur = Character.toLowerCase(getGrille().getcases()[i][j]);
    			bouton = new JButton();
    			bouton.setText(nomJoueur(i,j));
        		switch(couleur){
        		case 'r':
        			bouton.setBackground(Color.red);
        			break;
        		case 'o':
        			bouton.setBackground(new Color(255,159,0));
        			break;
        		case 'j':
        			bouton.setBackground(Color.yellow);
        			break;
        		case 'v':
        			bouton.setBackground(Color.green);
        			break;
        		case 'b':
        			bouton.setBackground(new Color(0,153,204));
        			break;
        		case 'i':
        			bouton.setBackground(new Color(153,0,204));
        			break;
        		}
    			contentGrille.add(bouton);
        	}
		}
		contentGrille.revalidate();
       }
		
	
	private  boolean tousBloques(){
		Iterator<Joueur> it = joueurs.iterator();
		while(it.hasNext()){
			Joueur j = it.next();
			if(!j.blocage()){
				return false;
			}
		}
		return true;
	}
	
	private  boolean finPartie(){
		int totalScore =0;
		Iterator<Joueur> it = joueurs.iterator();
		while(it.hasNext()){
			Joueur j = it.next();
			
			totalScore = totalScore + j.getScore();
			if(j.getScore()>=((int)(getGrille().getlargeur()*getGrille().getlongueur()/2)+1)){
				return true;
			}
		}
		
		if(totalScore >= getGrille().getlargeur()*getGrille().getlongueur()){// Plus de cases à jouer
			return true;
		}
		return false;
	}
	
	 String afficheScore (){
		Joueur j = new Joueur(0,getGrille());
		String txtScore="Scores : ";
		
		Iterator<Joueur> it = this.joueurs.iterator();
		while(it.hasNext()){
			j = it.next();
			txtScore = txtScore+ j.getNom()+ " :&nbsp  " + j.getScore()+" pts &nbsp &nbsp &nbsp  &nbsp   ";
			
		}
		return txtScore;
		
	}
	 
	 
	
	private void afficheMessage(String msg){
		message.setText("");
		 String msgScore = afficheScore();
		message.setText("<html><body><font size = 5 color = white>"+msgScore+"<br /><br />"+msg+"</font></body></html>");
	}
	
	private String couleurNonJouable(char couleur){
		String clr ="";
		switch(couleur){
		case 'r':
			clr = "rouge";
			break;
		case 'o':
			clr = "orange";
			break;
		case 'j':
			clr = "jaune";
			break;
		case 'v':
			clr = "vert";
			break;
		case 'b':
			clr = "bleu";
			break;
		case 'i':
			clr = "indigo";
			break;
		}
		
	return clr;
	};
	
	private static Joueur leGagnant(LinkedList<Joueur> listejoueurs){

		Joueur j = new Joueur(0,getGrille());
		Joueur jGagnant= new Joueur(0,getGrille());
		Iterator<Joueur> it = listejoueurs.iterator();
		while(it.hasNext()){
			j = it.next();
			if(j.getScore()>jGagnant.getScore()){
				jGagnant = j;
			}
		}
		return jGagnant;
	}
	
	public void actionPerformed(ActionEvent evt) {
			String s = "";
			String clr ="";
			clic = evt.getActionCommand();
			
			// ---------le joueur qui a cliqué joue ---------------------------------
			joueur.setSaisie(clic.charAt(0));
			if (joueur.saisieValide(joueur.getSaisie())== false){ // s'il a cliqué sur une mauvais couleur
				char couleur = joueur.getSaisie();
				clr = couleurNonJouable(couleur);
				afficheMessage(joueur.getNom()+" tu ne peux pas jouer la couleur " + clr );
				}
			else{												   // S'il a cliqué sur la bonne couleur
				getGrille().getcouleursbloquees().set(joueur.getNumero()-1, joueur.getSaisie()); // Recupère la liste des couleurs bloquées
				joueur.setCouleurControlee(joueur.getSaisie()); 						
				joueur.modifierGrille();													// Mise à jour de la grille													
				creationGrille();															// affichage de la nouvelle grille
				if(itJoueur.hasNext()== false){// Si c'est le dernier passe au premier
					itJoueur = joueurs.listIterator(0);
				}
				joueur = itJoueur.next();
				afficheMessage("");
				
				// -------- Est-ce que ce n'est pas la fin de la partie ? ---------------------
				if (!finPartie()){ //Oui ce n'est pas la fin de la partie
					
					// ----------- Est-ce qu'il ne sont pas tous bloqués ? --------------------
					if (!tousBloques()){  // Oui ils ne sont pas tous bloqués -----------------
						
						//------ Recherche de joueur bloqué --------------------------
						while(joueur.blocage()){
							s = joueur.getNom()+" passe la main. ";
							afficheMessage(s);
							if(!itJoueur.hasNext()){
								itJoueur = joueurs.listIterator(0);
								}
							joueur = itJoueur.next();
							}
						afficheMessage(s + joueur.getNom()+" à toi de jouer ");
						}
					else{
						afficheMessage(" Fin de la partie. Le gagnant est : "+leGagnant(joueurs).getNom()+ " avec un score de : "+leGagnant(joueurs).getScore()+" pts.");
						
					}
					}
				else{
					afficheMessage(" Fin de la partie. Le gagnant est : "+leGagnant(joueurs).getNom()+ " avec un score de : "+leGagnant(joueurs).getScore()+" pts.");
					}
				}
		}
	
	
	}   
