import java.lang.*;
public class MeanFilterSerial{
        static double StartTime;
        public static void main(String[] args){
                int dim = Integer.parseInt(args[2]);
                if (dim < 3 || dim%2 != 1){
                        System.out.println("Invalid Window Value.");
                        System.exit(0);
                        }
                ImagePixels myImage = new ImagePixels(args[0], args[1]);
                tic();
                int[][] alphaVals = myImage.getAlpha();
                int[][] redVals = myImage.getRed();
                int[][] greenVals = myImage.getGreen();
                int[][] blueVals = myImage.getBlue();
                int n = dim*dim;
                int xinit = Math.floorDiv(dim,2);
                int yinit = Math.floorDiv(dim,2);
                int xlim = myImage.getX()-xinit;
                int ylim = myImage.getY()-yinit;
                for (int x = xinit; x < xlim; x++){
                        for (int y = yinit; y < ylim; y++){
                                int temp0 = 0;
                                int temp1 = 0;
                                int temp2 = 0;
                                int temp3 = 0;
                                int i = 0;
                                for (int a = x-xinit; a < x+xinit+1; a++){
                                        for (int b = y-yinit; b < y+yinit+1; b++){
                                                temp0 += alphaVals[a][b];
                                                temp1 += redVals[a][b];
                                                temp2 += greenVals[a][b];
                                                temp3 += blueVals[a][b];
                                                i++;
                                                }
                                        }     
                                myImage.setPixels(x, y, temp0/n, temp1/n, temp2/n, temp3/n); 
                                }
                        }
                toc();
                myImage.filterImage();
        }
        public static void tic(){
                StartTime = System.currentTimeMillis();
                }
        public static void toc(){
                System.out.println("Serial path takes "+(System.currentTimeMillis()-StartTime)/1000+"seconds."); 
                }
}