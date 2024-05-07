import java.util.Arrays;
import java.lang.*;

public class MedianFilterSerial{
        static double StartTime;
        public static void main(String[] args){
                int dim = Integer.parseInt(args[2]);
                if (dim<3 || dim%2 != 1){	
                        System.out.println("Invalid Window Size.");
                        System.exit(0);
                        }
                ImagePixels myImage = new ImagePixels(args[0], args[1]);
                tic();
                int[][] alphaVals = myImage.getAlpha();
                int[][] redVals = myImage.getRed();
                int[][] greenVals = myImage.getGreen();
                int[][] blueVals = myImage.getBlue();
                int n = dim*dim;
                int[] tempA = new int[n];
                int[] tempR = new int[n];
                int[] tempG = new int[n];
                int[] tempB = new int[n];
                int xinit = Math.floorDiv(dim,2);
                int yinit = Math.floorDiv(dim,2);
                int xlim = myImage.getX()-xinit;
                int ylim = myImage.getY()-yinit; 
                for (int x = xinit; x < xlim; x++){
                        for (int y = yinit; y < ylim; y++){
                                int i = 0;
                                for (int a = x-xinit; a < x+xinit+1; a++){
                                        for (int b = y-yinit; b < y+yinit+1; b++){
                                                tempA[i] = alphaVals[a][b];
                                                tempR[i] = redVals[a][b];
                                                tempG[i] = greenVals[a][b];
                                                tempB[i] = blueVals[a][b];
                                                i++;
                                                }
                                        }	
                                Arrays.sort(tempR);
                                Arrays.sort(tempG);
                                Arrays.sort(tempB);
                                int temp0 = tempA[Math.floorDiv(n, 2)];
                                int temp1 = tempR[Math.floorDiv(n, 2)];
                                int temp2 = tempG[Math.floorDiv(n, 2)];
                                int temp3 = tempB[Math.floorDiv(n, 2)];
                                myImage.setPixels(x, y, temp0, temp1, temp2, temp3); 
                        }
                }
                toc();
            myImage.filterImage();
            }
        public static void tic(){
                StartTime = System.currentTimeMillis(); 
                }
        public static void toc(){
                System.out.println("Serial path took "+(System.currentTimeMillis()-StartTime)/1000+"seconds");
                } 			
}
