
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Maze extends JFrame implements Runnable{
    public static int rows = 20;
    public static int columns = 20;
    public static int panelSize = 35;
    public static int map[][] = new int[columns][rows];
    public static int endLevelLoc;
    Player p;
    
    private boolean running;
    public boolean right = false, left= false, up = false, down = false;
    public int ticks;
    public Thread thread;
    
    public static Tile tiles[][] = new Tile[columns][rows];
    
    public Maze(String str){
        
    	loadMap(str);
        this.setResizable(false);
        this.setSize((columns*panelSize)+50, (rows*panelSize)+70);
        this.setTitle("Maze");
        this.setLayout(null); 
        
        this.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				
				revalidate();
				repaint();
				
				//Player movement
				if((key == KeyEvent.VK_W || key == KeyEvent.VK_UP) && !down && !left && !right){
						left = false;
						right = false;
						up = true; 
				}
				
				if((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && !right && !up && !down){					
						up = false;
						down = false;
						left = true;
				}
				if((key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) && !up && !left && !right){
					
						left = false;
						right = false;
						down = true;
				}
				if((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && !left && !up && !down){
					
						up = false;
						down = false;
						right = true;
				}
				
				
//				if(p.x == columns-1 && p.y == endLevelLoc){
//					JOptionPane.showMessageDialog(null, "Congratulations, you've beaten the level!", "End Game", JOptionPane.INFORMATION_MESSAGE);
//					dispose();
//					new MainMenu();
//				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                //System.out.println((columns*panelSize)+50+"-"+((rows*panelSize)+70));
                System.exit(0);
            }
        });
        
        this.setLocationRelativeTo(null);
        
        //Create player
    	p = new Player();
    	p.setVisible(true);
    	this.add(p);
    	
    	start();
    	
        //Color map
        for(int y = 0; y < columns; y++){
            for(int x = 0; x < rows; x++){
                Tile tile = new Tile(x, y);
                tile.setSize(panelSize, panelSize);
                tile.setLocation((x*panelSize)+23, (y*panelSize)+25);
                if(map[x][y] == 0){
                    tile.setBackground(Color.GRAY);
                }else{
                    tile.setBackground(Color.WHITE);
                    tile.setWall(false);
                    if(x == 0){
                    	p.setLocation((x*panelSize)+23, (y*panelSize)+25);
                    	p.y = y;
                    }
                    if(x == columns-1){
                    	endLevelLoc = y;
                    }
                }
                
                tile.setVisible(true);
                tiles[x][y] = tile;
                this.add(tile);
            }
        }
        this.setVisible(true);
        //this.add(tiles);
        
    }
    
    public static void main(String args[]){
    	new MainMenu();
    }
    
    public void loadMap(String str){
        try{
            BufferedReader br = new BufferedReader(new FileReader(str));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String mapStr = sb.toString();
            
            int counter = 0;
            for(int y = 0; y < columns; y++){
                for(int x = 0; x < rows; x++){
                    String mapChar = mapStr.substring(counter, counter+1);
                    if(!mapChar.equals("\r\n") && !mapChar.equals("\n")&& !mapChar.equals("\r")){//If it's a number
                        //System.out.print(mapChar);
                        map[x][y] = Integer.parseInt(mapChar);
                    }else{//If it is a line break
                        x--;
                        System.out.print(mapChar);
                    }
                    counter++;
                }
            }
        }catch(Exception e){
            System.out.println("Unable to load existing map(if exists), creating new map.");
        }
    }
    
    public void start() {
    	running = true;
    	thread = new Thread(this);
    	thread.start();
    	
    }
    
    public void stop() {
    	running = false;
    }
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(running) {
			tick();
			repaint();
		}
		
	}
	public void tick() {
		ticks++;
		if (ticks > 250000) {
			if (right) p.moveRight(); {
				//System.out.println(endLevelLoc);
				if (p.x == columns-1 && p.y == endLevelLoc) {
					right = true;
					//possibly redundant after implementing maze coloring
				} else if (map[p.x+1][p.y] == 0) {
					right = false;
				} 
			}
			if (left) p.moveLeft();{
				if (p.x == 0) {
					left = false;
				} else if (map[p.x-1][p.y] == 0 || map[p.x-1][p.y] == -1) {
					left = false;
				} 
			}	
			if (up) p.moveUp(); {
				if (map[p.x][p.y-1] == 0) {
					up = false;
				} 
			}
			if (down) p.moveDown(); {
				if (map[p.x][p.y+1] == 0) {
					down = false;
				} 
			}
			if(p.x == columns-1 && p.y == endLevelLoc){
				stop();
				JOptionPane.showMessageDialog(null, "Congratulations, you've beaten the level!", "End Game", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				new MainMenu();
			}
			ticks = 0;
		}
//		for(int y = 0; y < columns; y++){
//            for(int x = 0; x < rows; x++){
//            	if (map[x][y] != 2) {
//            		
//            	}
//            }
//		}
	}
}
