
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.LinkedList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class Main extends JFrame implements ActionListener  {	
	private static final long serialVersionUID = 1L;
	Grille grille;
	static LinkedList<Joueur> joueurs;
	Options dialogue;
	JButton jouer,options,regle;
	JPanel boutons;
	JPanel cadre;
	JPanel image;
	private String clic;

	public Main (String titre) {
	this.setTitle(titre);
	//fen�tre positionn�e au centre de l'�cran
	this.setAlwaysOnTop(false);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(800,800);
	this.setResizable(false);	
	boutons = new JPanel();
	boutons.setPreferredSize(new Dimension(800,100));
	cadre = new JPanel();
	this.setLocationRelativeTo(null); 
	cadre.setLayout(new BorderLayout());
	this.setVisible(true);

		}
	public void init(){
	AfficheImage afficheImage = new AfficheImage("./src/accueil.jpg");
	afficheImage.setSize(600,600);
	jouer=new JButton("Jouer");
	jouer.addActionListener(this);
	options=new JButton("Options");
	regle=new JButton("R�gles du jeu");
	regle.addActionListener(this);
	boutons.add(jouer);
	boutons.add(regle);	
	cadre.add(boutons,BorderLayout.SOUTH);
	cadre.add(afficheImage, BorderLayout.CENTER);
	this.setContentPane(cadre);
	this.pack();
		}

	public void actionPerformed(ActionEvent evt) {
		clic = evt.getActionCommand();
			switch(clic){
			case "Jouer":
				dialogue = new Options(null, "Options de jeu", true);
				if(dialogue.etatOk){
					int compteurBlocage;//compte le nombre de joueurs bloqu�s
						// **************Cr�ation de la grille et des joueurs ******************
					do{ //On r�initialise la grille si l'un au moins des joueurs est bloqu� au premier tour
							grille = new Grille(dialogue.infos.getLargeur(),dialogue.infos.getLongueur());
							grille.initGrille();
							compteurBlocage=0;
							joueurs = new LinkedList<Joueur>();
							for(int i=1;i<= dialogue.infos.getNombre();i++){
								Joueur J = new Joueur(i,grille);
								joueurs.add(J);	
								J.setNom(dialogue.infos.getNom(i));
								}
							Iterator<Joueur> J =  joueurs.listIterator();
							while(J.hasNext()){
								Joueur test = J.next();
								if (test.blocage()){
									compteurBlocage++;
									}
								}
							}
						
						while(compteurBlocage!=0);
							Fenetre jeu = new Fenetre(dialogue.infos.getLargeur(),dialogue.infos.getLongueur(),grille,joueurs);
							jeu.fenetrePrincipale();
							grille.imprime();//impression console
													
					}
					break;
				case "R�gles du jeu":
					Fenetre regle = new Fenetre();
					regle.texte();
					break;
				}
			}

	public static void main(String [] args){
		Main accueil = new Main("Jeu des 6 couleurs");
		accueil.init();
		
	}

}
