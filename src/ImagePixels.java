import java.util.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImagePixels{
    BufferedImage image1;
    String fileName, fileName1;
    int[][] alphaVals, alphaVals1, redVals, redVals1, greenVals, greenVals1, blueVals, blueVals1;
    int x, y;
    public ImagePixels(String fileName, String fileName1){
        this.fileName = fileName;
        this.fileName1 = fileName1;
        readImage();
        }
    public void readImage(){
        BufferedImage image= null;
        File file = null;
        try{
            file = new File(fileName);
            image = ImageIO.read(file);
            }
        catch(IOException e){
            System.out.println("File Not Found.");
            System.exit(0);
            }
        image1 = image;
        int xlim = image.getWidth();
        int ylim = image.getHeight();
        x = xlim;
        y = ylim;
        alphaVals = new int[xlim][ylim];
        alphaVals1 = new int[xlim][ylim];
        redVals = new int[xlim][ylim];
        redVals1 = new int[xlim][ylim];
        greenVals = new int[xlim][ylim];
        greenVals1 = new int[xlim][ylim];
        blueVals = new int[xlim][ylim];
        blueVals1 = new int[xlim][ylim];
        for (int x = 0; x < xlim; x++){
            for (int y = 0; y < ylim; y++){
                int rgb = image.getRGB(x,y);
                int a = (rgb>>24) & 0xff;
                int r = (rgb>>16) & 0xff;
                int g = (rgb>>8) & 0xff;
                int b = rgb & 0xff;
                alphaVals[x][y] = a;
                alphaVals1[x][y] = a;
                redVals[x][y] = r;
                redVals1[x][y] = r; 
                greenVals[x][y] = g;
                greenVals1[x][y] = g;
                blueVals[x][y] = b;
                blueVals1[x][y] = b;
                }
            }
        }
    public void filterImage(){
        for (int a = 0; a < x; a++){
            for (int b = 0; b < y; b++){
                int rgb = 0;
                rgb = rgb | (alphaVals1[a][b]<<24);
                rgb = rgb | (redVals1[a][b]<<16);
                rgb = rgb | (greenVals1[a][b]<<8);
                rgb = rgb | blueVals1[a][b];
                image1.setRGB(a,b,rgb);
                }
            }
        File myFile = null;
        try{
            myFile = new File(fileName1);
            ImageIO.write(image1,"jpeg",myFile);
            System.out.println("Image Printed.");
            }
        catch(IOException e){
            System.out.println("Invalid Output Picture Name.");
            System.exit(0);
            }

        }
    public void setPixels(int a, int b, int val0, int val1, int val2, int val3){
        alphaVals1[a][b] = val0;
        redVals1[a][b] = val1;
        greenVals1[a][b] = val2;
        blueVals1[a][b] = val3;
        }
    public int[][] getAlpha(){
        return alphaVals;
        }
    public int[][] getRed(){
        return redVals;
        }
    public int[][] getGreen(){
        return greenVals;
        }
    public int[][] getBlue(){
        return blueVals;
        }
    public int getX(){
        return x;
        }
    public int getY(){
        return y;
        }
}