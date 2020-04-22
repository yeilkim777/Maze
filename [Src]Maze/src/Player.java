import java.awt.Color;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.JPanel;



public class Player extends JPanel{
	int x, y; 
	Color BluePaint = Color.decode("#00ccff");
	
    public Player() {
    	this.setBackground(Color.decode("#A52A2A")); // Blue or Yellow
    	this.setBorder(BorderFactory.createLineBorder(Color.white));
    	this.setSize(Maze.panelSize, Maze.panelSize);
    }

    public void moveLeft() {
    	if(x > 0 && Maze.map[x-1][y] >= 1){
    		this.setLocation(this.getX()-Maze.panelSize, this.getY());
    		Maze.tiles[x][y].setBackground(BluePaint); // Color.YELLOW for contrast, cyan for ice theme
    		//Maze.tiles[x][y].setBorder(BorderFactory.createLineBorder(Color.white));
    		Maze.map[x][y] = 2;
    		x--;
    	}
    }

    public void moveRight() {
    	if(x < Maze.columns-1 && Maze.map[x+1][y] >= 1){
    		this.setLocation(this.getX()+Maze.panelSize, this.getY());
    		Maze.tiles[x][y].setBackground(BluePaint); // Color.YELLOW for contrast, cyan for ice theme
    		//Maze.tiles[x][y].setBorder(BorderFactory.createLineBorder(Color.white));
    		Maze.map[x][y] = 2;
	    	x++;
    	} 
    }

    public void moveUp() {
    	if(y > 0 && Maze.map[x][y-1] >= 1){
    		this.setLocation(this.getX(), this.getY()-Maze.panelSize);
    		Maze.tiles[x][y].setBackground(BluePaint); // Color.YELLOW for contrast, cyan for ice theme
    		//Maze.tiles[x][y].setBorder(BorderFactory.createLineBorder(Color.white));
    		Maze.map[x][y] = 2;
    		y--;
    	}
    }

    public void moveDown() {
    	if(y < Maze.rows-1 && Maze.map[x][y+1] >= 1){
    		this.setLocation(this.getX(), this.getY()+Maze.panelSize);
    		Maze.tiles[x][y].setBackground(BluePaint); // Color.YELLOW for contrast, cyan for ice theme
    		//Maze.tiles[x][y].setBorder(BorderFactory.createLineBorder(Color.white));
    		Maze.map[x][y] = 2;
    		y++;
    	}
    }
}
