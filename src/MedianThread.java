import java.util.concurrent.*;
import java.util.*;

public class MedianThread extends RecursiveAction{
    ImagePixels myPixels;
    int[][] alphaVals, redVals, greenVals, blueVals;
    int xinit, yinit, xlim, ylim, dim, cutoff, diff;
    public MedianThread(ImagePixels myPixels, int[][] alphaVals, int[][] redVals, int[][] greenVals, int[][] blueVals, int xinit, int yinit, int xlim, int ylim, int dim){
        this.myPixels = myPixels;
        this.alphaVals = alphaVals;
        this.redVals = redVals;
        this.greenVals = greenVals;
        this.blueVals = blueVals;
        this.xinit = xinit;
        this.yinit = yinit;
        this.xlim = xlim;
        this.ylim = ylim;
        this.dim = dim;
        diff = Math.floorDiv(dim, 2);
        cutoff = 1000;
        }
    public void compute(){
        int diff = Math.floorDiv(dim,2);
        int deltaY = ylim-yinit+1;
        int deltaX = xlim-xinit+1;
        int xy = ((ylim+diff)-(yinit-diff))*((xlim+diff)-(xinit-diff));
        if (xy < cutoff){
            int tem = dim*dim;
            int alphas[] = new int[tem];
            int reds[] = new int[tem];
            int greens[] = new int[tem];
            int blues[] = new int[tem] ;
            for (int x = xinit; x < xlim; x++){
                for (int y = yinit; y < ylim; y++){
                    int a = 0;
                    for (int i = x-diff; i < x+diff+1; i++){
                        for (int j = y-diff; j < y+diff+1; j++){
                            alphas[a] = alphaVals[i][j];
                            reds[a] = redVals[i][j];
                            greens[a] = greenVals[i][j];
                            blues[a] = blueVals[i][j];
                            a++;
                            }
                        }
                    Arrays.sort(alphas);
                    Arrays.sort(reds);
                    Arrays.sort(greens);
                    Arrays.sort(blues);
                    int temp0 = alphas[Math.floorDiv(a, 2)];
                    int temp1 = reds[Math.floorDiv(a, 2)];
                    int temp2 = greens[Math.floorDiv(a, 2)];
                    int temp3 = blues[Math.floorDiv(a, 2)];
                    myPixels.setPixels(x, y, temp0, temp1, temp2, temp3); 
                    }
                }
            }
        else if(deltaY > deltaX){
            int tempY = yinit+Math.floorDiv(ylim-yinit, 2);
            MedianThread upperThread = new MedianThread(myPixels, alphaVals, redVals, greenVals, blueVals, xinit, yinit, xlim, tempY, dim);
            MedianThread lowerThread = new MedianThread(myPixels, alphaVals, redVals, greenVals, blueVals, xinit, tempY, xlim, ylim, dim);
            upperThread.fork();
            lowerThread.compute();
            upperThread.join();
            }
        else{
            int tempX = xinit+Math.floorDiv(xlim-xinit, 2);
            MedianThread leftThread = new MedianThread(myPixels, alphaVals, redVals, greenVals, blueVals, xinit, yinit, tempX, ylim, dim);
            MedianThread rightThread = new MedianThread(myPixels, alphaVals, redVals, greenVals, blueVals, tempX, yinit, xlim, ylim, dim);
            leftThread.fork();
            rightThread.compute();
            leftThread.join();
            }
        }
}