import java.awt.Graphics;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.sun.glass.events.WindowEvent;

public class Instructions extends JFrame {
	static JFrame Instructions;
	static JLabel text;
	
//	public static void main (String[] args) {
//		Instructions = new JFrame ("Instructions");
//		text = new JLabel();
//		text.setText("Textt");
//		JPanel p = new JPanel();
//		p.add(text);
//		Instructions.add(p);
//		Instructions.setSize(500, 500);
//		Instructions.show();
//	}
	public Instructions () {
		
		Runnable r = () -> {
			String intro = "Welcome to (come up with title)!\r\n" + 
					"\r\n" + 
					"To win the game, traverse and color through the mazes." +"\n"
					+ "You can only move one direction at a time, so plan your moves well!\r\n\n" + 
					"Move using the direction pad or WASD pad." + 
					//"\n\n" + "if the maze is difficult, press enter to skip the maze." +
					"\n\n" + 
					"Have Fun!";
			
					JOptionPane.showMessageDialog(null, String.format(intro, 200, 200));
		};
		
		SwingUtilities.invokeLater(r);
	}
}
