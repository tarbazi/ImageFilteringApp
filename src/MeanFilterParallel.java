import java.util.*;
import java.util.concurrent.*;

public class MeanFilterParallel{
    static double StartTime;
    
    public static void main(String[] args){
        int dim = Integer.parseInt(args[2]);
        if (dim < 2 || dim%2 != 1){
            System.out.println("Invalid Window Size.");
            System.exit(0);
            }
        ImagePixels myImage = new ImagePixels(args[0], args[1]);

        int xinit = Math.floorDiv(dim,2);
        int yinit = Math.floorDiv(dim,2);
        int xlim = myImage.getX()-xinit;
        int ylim = myImage.getY()-yinit;
        
        MeanThread myThread = new MeanThread(myImage, myImage.getAlpha(), myImage.getRed(), myImage.getGreen(), myImage.getBlue(), xinit, yinit, xlim, ylim, dim);
        ForkJoinPool myPool = new ForkJoinPool();
        tic();
        myPool.invoke(myThread);
        toc();    
        myImage.filterImage();
        }
        
    public static void tic(){
        StartTime = System.currentTimeMillis();
        }

    public static void toc(){
        System.out.println("Parallel path took "+(System.currentTimeMillis()-StartTime)/1000+"seconds.");
        }
}

