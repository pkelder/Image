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
	
	/*création d'un objet image à partir d'un fichier pgm*/
	
	public static image fill_img(String path) throws IOException
	{
		 File f = new File(path);
	     FileInputStream fin = new FileInputStream(f);
	     BufferedReader in = new BufferedReader(new InputStreamReader(fin));
	     String s = "";
	     s = in.readLine(); //System.out.println(s);
	     s = in.readLine(); //System.out.println(s);
	     s = in.readLine(); System.out.println(s); /*s contient les dimensions de l'image */
	     String lon = s.substring(0,3);  /* oblige à avoir une image de dimension comprise entre 100 et 1000 */
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
	
	//création du tableau histogramme à partir de l'objet image
	
	public static int[]  histogramme(image img)
	{
		int lon=img.matrice_img.length;
		int [][] mat=img.matrice_img;
		int [] histo=new int[lon];
		
	for (int i=0;i<lon;i++){
		for (int j=0;j<lon;j++)
		{
			int ent=mat[i][j];
			histo[ent]=histo[ent]+1;
		
		}
	}
	System.out.println(histo[200]);
		return histo;
	}
	
	
	
	//création de l'histogramme sous forme de fichier pgm de chemin path à partir de l'histogramme lui même
	
	public static String HistoPGM(int[] histo,String path) throws IOException
	{
		File f=new File(path);
		FileWriter fichier = new FileWriter(path );
		fichier.write("P2"+"\n");
		fichier.write("# CREATOR PAULINE KELDER TP OBJET"+"\n"); 
		fichier.write(histo.length+" "+histo.length+"\n"); 
		fichier.write("255"+"\n"); 
		
	     for (int i = 0; i < histo.length; i++){
	    	 int ent=	histo[i];
	    	 fichier.write(ent+"\n");
	    	 
	    	 }
	     fichier.close();
		return path;
	}
	
	public static String seuillage(int seuil, String endpath,int[] histo) throws IOException
	{
		File f=new File(endpath);
		FileWriter fichier = new FileWriter(endpath);
		fichier.write("P2"+"\n");
		fichier.write("# CREATOR PAULINE KELDER TP OBJET"+"\n"); 
		fichier.write(histo.length+" "+histo.length+"\n"); 
		fichier.write("255"+"\n"); 
		
	     for (int i = 0; i < histo.length; i++){
	    	 int ent=histo[i];
	    	 if (ent<seuil){
	    	 fichier.write("0"+"\n");
	    	 }
	    	 else{
	    		 fichier.write("255"+"\n");	 
	    	 }
	    	 }
	     fichier.close();
		return endpath;		
	
	}
	
	
	
	public static void main(String[] args) throws IOException{
		
		
		image im=fill_img("/Users/loukelder/Desktop/lena512x512.pgm");
		
		HistoPGM(histogramme(im),"/Users/loukelder/Desktop/essai.txt");
		
		seuillage(125,"/Users/loukelder/Desktop/seuil.txt",histogramme(im));
	
	}
	
	

}
