
import java.util.*;
public class Grille{
	//attributs
	private int longueur;
	private int largeur;
	private char[][] cases;
	private String couleurs;
	private  ArrayList<Character> couleursbloquees; 
	public Grille( int largeur,int longueur){
		this.longueur=longueur;      
		this.largeur=largeur;		
		
		this.cases=new char [largeur][longueur];
		this.couleurs="rojvbi";
		this.couleursbloquees = new ArrayList<Character> ();
		}
	
	//----------------------- getters ---------------------
	public int getlongueur(){
		return longueur;
	}
	public int getlargeur(){
		return largeur;
	}
	public String getcouleurs(){
		return couleurs;
	}
	
	public char [][] getcases(){
		return cases;
	}
	public char getcase(int i,int j){
		return cases[i][j];
	}

	public ArrayList<Character> getcouleursbloquees(){
		return couleursbloquees ;
	}
	
	public void setcases(int i, int j,char s){
		this.cases[i][j] = s;
	}

	//méthodes
	public static void trait(int longueur,int intervalle){
		int i;
		for (i=0; i <= longueur; i++){
			if (i%intervalle == 0){ 
				System.out.print("|");
			}
			else {
				System.out.print("-");
			}
			}
		System.out.println(" ");
		}

	public  void imprime(){
			int longueur=getlongueur();
			int largeur=getlargeur();
			trait(longueur*5,5);
			for(int i=0;i<largeur;i++){
				for(int j=0;j<longueur;j++){
					System.out.print("| ");
					System.out.print(cases[i][j]);
					System.out.print("  ");
				}
				System.out.println("|");
				trait(longueur*5,5);
			}
			
	}
	//Fonction d'initialisation de la grille
	public void initGrille(){
		int longueur=getlongueur();
		int largeur=getlargeur();
		String couleurs=getcouleurs();
		for (int i=0;i<largeur;i++){
			for(int j=0;j<longueur;j++){
				int hasard=(int) (6*(Math.random()));
				cases[i][j]=couleurs.charAt(hasard);
			}
		}
		HashSet<Character> bords = new HashSet<Character>();
		//On s'assure que les extrémités seront de couleurs différentes
		do{
			for(int i=0;i<4;i++){
				int hasard=(int) (6*(Math.random()));
				bords.add(couleurs.charAt(hasard));
			}
		}
		
		while(bords.size()<4);
		Iterator<Character> it = bords.iterator();
		cases[0][0]=it.next();
		cases[largeur-1][longueur-1] = it.next();
		cases[0][longueur-1] = it.next();
		cases[largeur-1][0]=it.next();
		
		}
			

}