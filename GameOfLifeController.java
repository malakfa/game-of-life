import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/*the class GameOfLife construct game objects , each object represent a game of life*/
public class GameOfLifeController {

    @FXML
    private Canvas canv;
    
    private GraphicsContext gc;
    final int SIZE = 10;
    final double RECSIZE = 40 ;//canv.getHeight()/SIZE=400/10
    private boolean LifeMat[][] = new boolean[SIZE][SIZE];
    

    @FXML
    void pressedLifeButton(ActionEvent event) {
    	int change[][]=new int[SIZE][SIZE];//change[i][j]=1 if the block in place [i][j] will change its state in the next round
    	int status;
    	for(int i = 0; i<SIZE; i++) {
    		for(int j = 0 ;j<SIZE ;j++) {
    			status = neighbors(i,j);
    			if(LifeMat[i][j]==false && status == 3)//birth
    				change[i][j]=1;
    			if(LifeMat[i][j]==true && (status == 0 || status == 1 || status>=4) )//death
    				change[i][j]=1;
    			if(LifeMat[i][j]==true && (status == 2 || status == 3))//existence
    				change[i][j]=0;
    		}	
    	}
    	
    	for(int i = 0; i<SIZE; i++) {//Change the color of some cells as needed
    		for(int j = 0 ;j<SIZE ;j++) {
    			if(change[i][j]==1) {
    				if(LifeMat[i][j]==true) {
    					LifeMat[i][j]= false;
    					gc.setFill(Color.WHITE);
    				}
    				else {
    					LifeMat[i][j]= true;
    					gc.setFill(Color.BLACK);
    				}
    				gc.fillRect(j*RECSIZE,i*RECSIZE,RECSIZE,RECSIZE);
    					
    			}
    		}	
    	}
    	draw_grid();
    	
    	
    }
    
    public void initialize() {// initialize the grid
    	gc = canv.getGraphicsContext2D();
    	boolean b ;
    	Random random = new Random();
    	
    	for(int i = 0; i<SIZE; i++) {
    		for(int j = 0 ;j<SIZE ;j++) {
    			b = random.nextBoolean();
    			
    			if(b == true) {
    				gc.setFill(Color.BLACK);
    				LifeMat[i][j] = true;
    			}
    			else {
    				gc.setFill(Color.WHITE);
    				LifeMat[i][j] = false;
    			}
    			gc.fillRect(j*RECSIZE,i*RECSIZE,RECSIZE,RECSIZE);
    			
    			
    		}
    		
    	}
    	
    	 draw_grid();
    	
    	
    }
    private int neighbors (int x , int y ) {//this method returns the number of neighbors of the block in place x,y
    	int count = 0;
    	if(y+1 < SIZE && LifeMat[x][y+1]==true) count++;
    	if(x+1 < SIZE && y+1 < SIZE && LifeMat[x+1][y+1]==true) count++;
    	if(x+1 < SIZE && LifeMat[x+1][y]==true) count++;
    	if(x+1 < SIZE && y-1 >= 0 && LifeMat[x+1][y-1]==true) count++;
    	if(y-1 >= 0 && LifeMat[x][y-1]==true) count++;
    	if(x-1 >= 0 && y-1 >= 0 && LifeMat[x-1][y-1]==true) count++;
    	if(x-1 >= 0 && LifeMat[x-1][y]==true) count++;
    	if(x-1 >= 0 && y+1 < SIZE && LifeMat[x-1][y+1]==true) count++;
    	return count;
    	
    }
    private void draw_grid() {//drawing the grid
    	for(int i = 0 ; i <= SIZE ; i++) {
    		gc.strokeLine(0, i*RECSIZE,RECSIZE*SIZE , i*RECSIZE);
    	}
    	for(int i = 0 ; i <= SIZE ; i++) {
    		gc.strokeLine(i*RECSIZE, 0 ,i*RECSIZE , SIZE*RECSIZE);
    		
    	}
    }
    
}
