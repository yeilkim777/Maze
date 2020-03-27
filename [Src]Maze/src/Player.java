import java.awt.Color;

import javax.swing.ActionMap;

import javax.swing.JPanel;



public class Player extends JPanel{
	int x, y; 

	
    public Player() {
    	this.setBackground(Color.RED);
    	this.setSize(Maze.panelSize, Maze.panelSize);
    }

    public void moveLeft() {
    	if(x > 0 && Maze.map[x-1][y] >= 1){
    		this.setLocation(this.getX()-Maze.panelSize, this.getY());
    		Maze.tiles[x][y].setBackground(Color.GREEN);
    		Maze.map[x][y] = 2;
    		x--;
    	}
    }

    public void moveRight() {
    	if(x < Maze.columns-1 && Maze.map[x+1][y] >= 1){
    		this.setLocation(this.getX()+Maze.panelSize, this.getY());
    		Maze.tiles[x][y].setBackground(Color.GREEN);
    		Maze.map[x][y] = 2;
	    	x++;
    	} 
    }

    public void moveUp() {
    	if(y > 0 && Maze.map[x][y-1] >= 1){
    		this.setLocation(this.getX(), this.getY()-Maze.panelSize);
    		Maze.tiles[x][y].setBackground(Color.GREEN);
    		Maze.map[x][y] = 2;
    		y--;
    	}
    }

    public void moveDown() {
    	if(y < Maze.rows-1 && Maze.map[x][y+1] >= 1){
    		this.setLocation(this.getX(), this.getY()+Maze.panelSize);
    		Maze.tiles[x][y].setBackground(Color.GREEN);
    		Maze.map[x][y] = 2;
    		y++;
    	}
    }
}
