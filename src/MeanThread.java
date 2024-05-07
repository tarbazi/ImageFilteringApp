import java.util.concurrent.*;
import java.util.*;

public class MeanThread extends RecursiveAction{
    ImagePixels myPixels;
    int[][] alphaVals, redVals, greenVals, blueVals;
    int xinit, yinit, xlim, ylim, dim, cutoff, diff;
    public MeanThread(ImagePixels myPixels, int[][] alphaVals, int[][] redVals, int[][] greenVals, int[][] blueVals, int xinit, int yinit, int xlim, int ylim, int dim){
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
        cutoff = 2000;
        }
    public void compute(){
        int diff = Math.floorDiv(dim,2);
        int deltaY = ylim-yinit+1;
        int deltaX = xlim-xinit+1;
        int xy = ((ylim+diff)-(yinit-diff))*((xlim+diff)-(xinit-diff));
        if (xy < cutoff){
            int tem = dim*dim;
            for (int x = xinit; x < xlim; x++){
                for (int y = yinit; y < ylim; y++){
                    int a = 0;
                    int temp0 = 0;
                    int temp1 = 0;
                    int temp2 = 0;
                    int temp3 = 0;
                    for (int i = x-diff; i < x+diff+1; i++){
                        for (int j = y-diff; j < y+diff+1; j++){
                            temp0 += alphaVals[i][j];
                            temp1 += redVals[i][j];
                            temp2 += greenVals[i][j];
                            temp3 += blueVals[i][j];
                            a++;
                           }
                        }
                    myPixels.setPixels(x, y, temp0/a, temp1/a, temp2/a, temp3/a); 
                    }
                }
            }
        else if(deltaY > deltaX){
            int tempY = yinit+Math.floorDiv(ylim-yinit, 2);
            MeanThread upperThread = new MeanThread(myPixels, alphaVals, redVals, greenVals, blueVals, xinit, yinit, xlim, tempY, dim);
            MeanThread lowerThread = new MeanThread(myPixels, alphaVals, redVals, greenVals, blueVals, xinit, tempY, xlim, ylim, dim);
            upperThread.fork();
            lowerThread.compute();
            upperThread.join();
            }
        else{
            int tempX = xinit+Math.floorDiv(xlim-xinit, 2);
            MeanThread leftThread = new MeanThread(myPixels, alphaVals, redVals, greenVals, blueVals, xinit, yinit, tempX, ylim, dim);
            MeanThread rightThread = new MeanThread(myPixels, alphaVals, redVals, greenVals, blueVals, tempX, yinit, xlim, ylim, dim);
            leftThread.fork();
            rightThread.compute();
            leftThread.join();
            }
        }
}
