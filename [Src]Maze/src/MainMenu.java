
import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.applet.*;
import java.io.File;
import java.net.*;

/**
 *
 * @author student
 */
public class MainMenu {

    JFrame Menu = new JFrame("Maze Day Color Maze");
    JButton Start = new JButton("Play");
    JButton Exit = new JButton("Exit");
    JButton MapMaker = new JButton("Map Maker");
    JButton Instructions = new JButton("Rules");
    ImageIcon picture = new ImageIcon("res/Images/BluePaintRoller2.png"); //MazePicture.png  // 1,3,4 is jpg, 2 is png
    JLabel imageLabel = new JLabel(picture);
    ArrayList<String> mapList = new ArrayList<String>();
    JComboBox<String> lvlList;
    int menuWidth = 100; //Width of each button/item on display
    int menuHeight = 30;//Height of each button/item on display
    int menuY = 460; //Button/item location on display
    int WIDTH = 490;
    int HEIGHT = 530;
    int index;
            
	public MainMenu() {
    	//Load map list
    	getMapList();
    	lvlList = new JComboBox<String>(mapList.toArray(new String[mapList.size()]));    
    	
        //Menu Variables
        Menu.setResizable(false);
        Menu.setSize(WIDTH, HEIGHT);
        Menu.setLayout(null);
        Menu.setLocationRelativeTo(null);
        Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        //Start Button Variables
        Start.setSize(menuWidth,menuHeight);
        Start.setLocation(10, menuY);
        Menu.add(Start);
        Start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				index = lvlList.getSelectedIndex();
				new Maze(lvlList.getSelectedItem().toString(), index);
				Menu.setVisible(false);
				
			}
			
        });	
        
        // Start key
        Start.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_B) {
					index = lvlList.getSelectedIndex();
					new Maze(lvlList.getSelectedItem().toString(), index);
					Menu.setVisible(false);
				}
				if (key == KeyEvent.VK_X) {
					Menu.dispose();
				}
				if (key == KeyEvent.VK_I) {
					
					File rule = new File("res/Audio/Rules.wav");
					try {
			    		Clip clip = AudioSystem.getClip();
			    		clip.open(AudioSystem.getAudioInputStream(rule));
			    		clip.start();
			    		Thread.sleep(clip.getMicrosecondLength()/1000);
			    	} 
			    	catch (Exception l) {
			    		
			    	}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        
        //Map Maker Button Variables
//        MapMaker.setSize(menuWidth,menuHeight);
//        MapMaker.setLocation(120, menuY);
//        Menu.add(MapMaker);
//        MapMaker.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				new MazeMapMaker();
//				Menu.setVisible(false);
//			}
//        	
//        });
         
        //Instruction Button Variables
        Instructions.setSize(menuWidth,menuHeight);
        Instructions.setLocation(120, menuY);
        Menu.add(Instructions);
        Instructions.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//JFrame instruction = new JFrame("Instruction");
				new Instructions();
		        //Menu.setVisible(false);
			}
        	
        });
        
        //Level Selector
        lvlList.setSize(menuWidth+35, menuHeight);
        lvlList.setLocation(230, menuY);
        Menu.add(lvlList);
        
        //Exit Button Variables
        Exit.setSize(menuWidth,menuHeight);
        Exit.setLocation(375,menuY);
        Menu.add(Exit);
        Exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
	            System.exit(0);
			}
        });
        
        //Display Picture
        imageLabel.setBounds((WIDTH-420)/2, 25, 420, 420); // 412
        imageLabel.setVisible(true);
        Menu.add(imageLabel);
        Menu.setVisible(true);
        
        new java.util.Timer().schedule(
        		new java.util.TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
				    	File Intro = new File("res/Audio/Introduction.wav");
				    	try {
				    		Clip clip = AudioSystem.getClip();
				    		clip.open(AudioSystem.getAudioInputStream(Intro));
				    		clip.start();
				    		Thread.sleep(clip.getMicrosecondLength()/1000);
				    	} 
				    	catch (Exception e) {
				    		
				    	}
					}
        			
        		},
        		1
        );
        
        
        
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
}
