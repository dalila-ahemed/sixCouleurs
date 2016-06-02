import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Options extends JDialog {
	private static final long serialVersionUID = 1L;
	public OptionInfo infos = new OptionInfo();
	public boolean etatOk;
  public Options(JFrame parent, String title, boolean modal){
    super(parent, title, modal);
    this.setSize(440,235);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setAlwaysOnTop(false);
    this.setLayout(new BorderLayout());
    this.etatOk= false;
    this.init();
    this.setVisible(true);
   
  }
  
private void init(){
	JPanel panJoueur = new JPanel();
	panJoueur.setSize(new Dimension(220,60));
	panJoueur.setBorder(BorderFactory.createTitledBorder("Nombre de participants"));
	JLabel nombreJoueur = new JLabel("Nombre de joueurs"); 
	String [] combo1 = {"1","2","3","4"};
	JComboBox<String> joueurs = new JComboBox<String>(combo1);
	panJoueur.add(nombreJoueur);
	panJoueur.add(joueurs);
	
	
	JPanel panNom = new JPanel();
	panNom.setPreferredSize((new Dimension(400,100)));
	panNom.setBorder(BorderFactory.createTitledBorder("Nom des participants"));
	JLabel joueur1 = new JLabel ("Joueur 1 :");
	JLabel joueur2 = new JLabel ("Joueur 2 :");
	JLabel joueur3 = new JLabel ("Joueur 3 :");
	JLabel joueur4 = new JLabel ("Joueur 4 :");
	
	JTextField j1 = new JTextField();
	j1.setPreferredSize(new Dimension(new Dimension(100,25)));
	
	JTextField j2 = new JTextField();
	j2.setPreferredSize(new Dimension(new Dimension(100,25)));
	j2.setText("IA");
	j2.setEnabled(false);
	
	JTextField j3 = new JTextField();
	j3.setPreferredSize(new Dimension(new Dimension(100,25)));
	j3.setEnabled(false);
	
	JTextField j4 = new JTextField();
	j4.setEnabled(false);
	j4.setPreferredSize(new Dimension(new Dimension(100,25)));
	
	panNom.add(joueur1);
	panNom.add(j1);
	panNom.add(joueur2);
	panNom.add(j2);
	panNom.add(joueur3);
	panNom.add(j3);
	panNom.add(joueur4);
	panNom.add(j4);
	
	joueurs.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0) {
        	switch((String)joueurs.getSelectedItem()){
        	case "1":
        		j1.setEnabled(true);
        		j2.setEnabled(false);
        		j3.setEnabled(false);
        		j4.setEnabled(false);
        		break;
        	case "2":
        		j1.setEnabled(true);
        		j2.setEnabled(true);
        		if(j2.getText().equals("IA")){
        			j2.setText(null);
        		}
        		j3.setEnabled(false);
        		j4.setEnabled(false);
        		break;
        	case "3":
        		j1.setEnabled(true);
        		j2.setEnabled(true);
        		if(j2.getText().equals("IA")){
        			j2.setText(null);
        		}
        		j3.setEnabled(true);
        		j4.setEnabled(false);
        		break;
        	case "4":
        		j1.setEnabled(true);
        		j2.setEnabled(true);
        		if(j2.getText().equals("IA")){
        			j2.setText(null);
        		}
        		j3.setEnabled(true);
        		j4.setEnabled(true);
        	}
        }      
      });
	
	
	
	JPanel panDimension = new JPanel();
	panDimension.setSize(new Dimension(220,60));
	panDimension.setBorder(BorderFactory.createTitledBorder("Dimensions de la grille"));
	JLabel longueurGrille = new JLabel("Longueur : ");
	JLabel largeurGrille = new JLabel("Largeur : ");
	String [] combo2 = {"5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
    JComboBox<String> longueur = new JComboBox<String>(combo2);
    JComboBox<String> largeur = new JComboBox<String>(combo2);
    panDimension.add(longueurGrille);
    panDimension.add(longueur);
    panDimension.add(largeurGrille);
    panDimension.add(largeur);
    
    JButton ok = new JButton("ok"); 
    ok.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent arg0) {        
    		infos = new OptionInfo((String)joueurs.getSelectedItem(), (String)longueur.getSelectedItem(), (String)largeur.getSelectedItem(),j1.getText(),j2.getText(),j3.getText(),j4.getText());
 
			if(infos.validationFormulaire()){
				etatOk = true;
	    		setVisible(false);
			}
			else {
				etatOk = false;
				JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !", "Attention", JOptionPane.WARNING_MESSAGE);
			}
    		

        }
    
    });
    
    JButton annuler = new JButton("annuler");
    annuler.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0) {
        	etatOk = false;
          setVisible(false);
        }      
      });


    
    JPanel centre = new JPanel();
    JPanel bas = new JPanel();
    centre.add(panJoueur);
    centre.add(panDimension);
    centre.add(panNom);
    bas.add(ok);
    bas.add(annuler);
    
    this.getContentPane().add(centre, BorderLayout.CENTER);
    this.getContentPane().add(bas, BorderLayout.SOUTH);
    
}

}