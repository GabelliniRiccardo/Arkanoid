
package IO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.LinkedList;
import javax.imageio.ImageIO;

public class Utilities {

    private static boolean DIST=false;

    public static String getAbsolutePathFromRelativePath(String relativePath){
        String fileName=null;
        if(DIST){
            File f=new File(relativePath);
            fileName=f.getAbsolutePath();
            fileName=fileName.substring(0, fileName.length()-1-relativePath.length());
            fileName+="\\data\\"+relativePath;
            System.out.println("il percorso assoluto di " + relativePath + " è : "+fileName);
        }
        else{
            return new File(relativePath).getAbsolutePath();
        }
        return fileName;
    }

    public static Image getImageLeveli(int index){
        Image imm=null;
        try{
            imm=(Image)ImageIO.read(new File(getAbsolutePathFromRelativePath("images\\"+index+".gif")));
        }catch(IOException ex){
            System.out.println("immagine "+index+" non trovata");
        }
        return imm;
    }

    public static Image getImageFromPath(String path){
        Image imm=null;
        try{
            imm=(Image)ImageIO.read(new File(path));
        }catch(IOException ex){
            System.out.println("immagine non trovata");
        }
        return imm;
    }
    public static Color getColorFromString(String string){
        Color color;
        try {
            Field field = Color.class.getField(string);
            color = (Color)field.get(null);
        } catch (Exception e) {
            color = null; // Not defined
        }
        return color;
    }

    //LEGGERE DATI DA FILE
    public static LinkedList<String[]> readLeveli(int i, String charset) throws IOException{

        LinkedList<String[]> rows = null;
        BufferedReader buffRead = null;
        //File f=new File("levels\\"+i+".txt");

        try {
                buffRead = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(IO.Utilities.getAbsolutePathFromRelativePath("levels\\"+i+".txt")), charset));

                rows=new LinkedList<String[]>();
                String s = null;

                while ((s = buffRead.readLine()) != null)
                        if (!s.isEmpty() && s.contains(";"))
                                rows.add(s.trim().split(";"));
        }
        catch(IOException ioe) {
                ioe.printStackTrace();
        }
        finally {
                if (buffRead != null)
                        buffRead.close();
        }
        return rows;
    }

    public static LinkedList<String[]> readBestScores(String charset) throws IOException{

        LinkedList<String[]> rows = null;
        BufferedReader buffRead = null;
        //File f=new File("scores\\scores.txt");

        try {
                buffRead = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(IO.Utilities.getAbsolutePathFromRelativePath("scores\\scores.txt")), charset));

                rows=new LinkedList<String[]>();
                String s = null;

                while ((s = buffRead.readLine()) != null)
                        if (!s.isEmpty() && s.contains(";"))
                                rows.add(s.trim().split(";"));
        }
        catch(IOException ioe) {
                ioe.printStackTrace();
        }
        finally {
                if (buffRead != null)
                        buffRead.close();
        }
        return rows;
    }

    public static void writeBestScores( LinkedList<String[]> list, String charset)throws IOException{
        //File f=new File("scores\\scores.txt");
        PrintWriter printWriter = new PrintWriter(
			new BufferedWriter(
				new OutputStreamWriter(
					new FileOutputStream(IO.Utilities.getAbsolutePathFromRelativePath("scores\\scores.txt")), charset)), true);

		for (String[] sArr : list)
			for (int i = 0; i < sArr.length; i++)
				if (i < (sArr.length - 1))
					printWriter.print(sArr[i] + ";");
				else
					printWriter.print(sArr[i] + "\r\n");
		printWriter.close();
    }

    // serve per ridimensionare l'immagine (è stato utilizzato per gestire il resize della sbarretta in seguito all'acquisizione del bonus di allargamento)
    public static BufferedImage resizeImage(BufferedImage originalImage, int type, int width, int height ){
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();

    return resizedImage;
}

}
