import java.awt.Color;
import javax.swing.ActionMap;

import javax.swing.JPanel;


public class Player extends JPanel{
	int x, y; 

	
    public Player() {
    	this.setBackground(Color.getHSBColor(0.3f, 0.3f, 1));
    	this.setSize(Maze.panelSize, Maze.panelSize);
//    	running = true;
//        run();
    }

    public void moveLeft() {
    	if(x > 0 && Maze.map[x-1][y] == 1){
	    	// return back if my code doesn't work
    		this.setLocation(this.getX()-Maze.panelSize, this.getY());
    		
    		// my codes
//    		up = false;
//    		down = false;
//    		left = true;
    		//
	    	x--;
    	}
    }

    public void moveRight() {
    	if(x < Maze.columns-1 && Maze.map[x+1][y] == 1){
    		// return back if my code doesn't work
    		this.setLocation(this.getX()+Maze.panelSize, this.getY());
	    	
	    	// my codes
//    		up = false;
//    		down = false;
//    		right = true;
    		//
	    	x++;
    	} 
    }

    public void moveUp() {
    	if(y > 0 && Maze.map[x][y-1] == 1){
    		// return back if my code doesn't work
    		this.setLocation(this.getX(), this.getY()-Maze.panelSize);
    		
    		// my codes
//    		left = false;
//    		right = false;
//    		up = true;
    		//
	    	y--;
    	}
    }

    public void moveDown() {
    	if(y < Maze.rows-1 && Maze.map[x][y+1] == 1){
    		// return back if my code doesn't work
    		this.setLocation(this.getX(), this.getY()+Maze.panelSize);
    		
    		// my codes
//    		left = false;
//    		right = false;
//    		down = true;
    		//
	    	y++;
    	}
    }
}
