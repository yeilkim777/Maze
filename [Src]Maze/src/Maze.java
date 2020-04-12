import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Maze extends JFrame implements Runnable{
    public static int rows = 13; //20
    public static int columns = 13; //20
    public static int panelSize = 55; //40
    public static int map[][] = new int[columns][rows];
    public static int endLevelLoc;
    Player p;
    
    private boolean running;
    public boolean right = false, left= false, up = false, down = false;
    public int ticks;
    public Thread thread;
    public static Tile tiles[][] = new Tile[columns][rows];
    public static int returnMap[][] = new int[columns][rows];
    ArrayList<String> mapList = new ArrayList<String>();
    JComboBox<String> lvlList;
    public int level;
    
    public Maze(String str, int counter){
        
    	level = counter;
    	loadMap(str);
        this.setResizable(true);
        this.setSize((columns*panelSize)+50, (rows*panelSize)+70);
        this.setTitle("Maze " + str );
        this.setLayout(null); 
        
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
            	stop();
            	dispose();
                new MainMenu();
            }
        });
        
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
                    tile.setBackground(Color.DARK_GRAY);
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
//                    if (map[x][y] == 3) {
//                    	p = new Player();
////                    	p.x = x;
////                    	p.y = y;
//                    	p.setVisible(true);
//                    	this.add(p);
//                    }
                }
                
                //start();
                
                tile.setVisible(true);
                tiles[x][y] = tile;
                this.add(tile);
            }
        }
        this.setVisible(true);
        returnMap = map;
        
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
	
	public void checkMap() {
		boolean thereIs1 = false;
		for(int y = 0; y < columns; y++){
			for(int x = 0; x < rows; x++){
				if (map[x][y] == 1) {
					thereIs1 = true;
					break;
				}
			}
		}
		if (!thereIs1) {
			stop();
			map = returnMap;
			left = false; 
			right = false; 
			up = false;
			down = false;
			JOptionPane.showMessageDialog(null, "Congratulations, you've beaten the level!", "End Game", JOptionPane.INFORMATION_MESSAGE);
			dispose();
			nextMaze();
		}
	}
	
	public void tick() {
		ticks++;
		map[p.x][p.y] = 2;
		if (ticks > 250000) {
			if (right) p.moveRight(); {
				//System.out.println(endLevelLoc);
				if (p.x == columns-1 && p.y == endLevelLoc) {
					right = false;
					//possibly redundant after implementing maze coloring
				} else if (map[p.x+1][p.y] == 0) {
					right = false;
					//checkMap();
				} 
			}
			if (left) p.moveLeft();{
				if (p.x == 0) {
					left = false;
				} else if (map[p.x-1][p.y] == 0 || map[p.x-1][p.y] == -1) {
					left = false;
					//checkMap();
				} 
			}	
			if (up) p.moveUp(); {
				if (map[p.x][p.y-1] == 0) {
					up = false;
					//checkMap();
				} 
			}
			if (down) p.moveDown(); {
				if (map[p.x][p.y+1] == 0) {
					down = false;
					//checkMap();
				} 
			}
			checkMap();
			ticks = 0;
		}
	}
	static boolean levelsExistAlready = false;
	public void getMapList(){
    	for(int i = 0; i < 99; i++){
    		File map = new File("./Level "+i+".map");
    		if(map.exists()){
    			//System.out.println("Level "+i+" exists");
    			mapList.add("Level "+i+".map");
    			levelsExistAlready = true;
    		}
    	}
    }
	public void nextMaze() {
		getMapList();
		String progress = "";
		if (level == mapList.size()-1) {
			stop();
			dispose();
			new MainMenu();
		}
		else if(level < mapList.size()-1) {
			level = level + 1;
			progress = mapList.get(level);
			new Maze(progress, level);
		}
		
	}
}
