import java.io.*;
import java.awt.image.*;


import javax.imageio.ImageIO;

public class image {
	
	
	protected int longueur;
	protected int largeur;
	protected int [][]  matrice_img; 
	
	public image(int longueur, int largeur, int[][] matrice_img) {
		super();
		this.longueur = longueur;
		this.largeur = largeur;
		this.matrice_img=matrice_img;
	}
	
	public image() {
		super();
	}
	
	
	public static image fill_img(String path) throws IOException
	{
		 File f = new File(path);
	     FileInputStream fin = new FileInputStream(f);
	     BufferedReader in = new BufferedReader(new InputStreamReader(fin));
	     String s = "";
	     s = in.readLine(); //System.out.println(s);
	     s = in.readLine(); //System.out.println(s);
	     s = in.readLine(); System.out.println(s); /*s contient les dimensions de l'image */
	     String lon = s.substring(0,3);  /* oblige ˆ avoir une image de dimension comprise entre 100 et 1000 */
	     String larg = s.substring(4,s.length());
	     System.out.println("long "+lon);
	     System.out.println("larg "+larg);
	     int [][] mat=new int[Integer.parseInt(lon)][Integer.parseInt(larg)];
	    
	    while ( (s = in.readLine()) != null) {
	    	for (int j=0;j<Integer.parseInt(larg);j++){
	    	for (int i=0;i<Integer.parseInt(lon);i++)
	    	{
	    		s=in.readLine();
	    		mat[i][j]=Integer.parseInt(s);
	    	}
	    	}
	    	 
	     }
		System.out.println(mat[511][511]);
		image img=new image(Integer.parseInt(lon),Integer.parseInt(larg),mat);
		return img;
	}
	
	
	
	public static void main(String[] args) throws IOException{
		
		fill_img("/Users/loukelder/Desktop/lena512x512.pgm");
	   
	
	}
	
	

}
