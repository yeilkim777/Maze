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
					"To win the game, traverse and color through the mazes. You can only move one direction at a time, so plan your moves well!\r\n" + 
					"\n" + 
					"Have Fun!";
			
					JOptionPane.showMessageDialog(null, String.format(intro, 175, 175));
		};
		
		SwingUtilities.invokeLater(r);
		
//		text = new JLabel();
//		String intro = "<p>Welcome to (come up with title)!</p>\\r\\n\" + \r\n" + 
//				"\"\\r\\n\" + \r\n" + 
//				"\"<p>To win the game, traverse and color through the mazes. You can only move one direction at a time, so plan your moves well!</p>\\r\\n\" + \r\n" + 
//				"\"\\r\\n\" + \r\n" + 
//				"\"<p>Have Fun!</p";
//		text.setText(intro);
//		JPanel p = new JPanel();
//		p.add(text);
//		this.add(p);
//		this.setResizable(false);
//		this.setSize(500, 300);
//		this.setLocationRelativeTo(null);
//		this.setTitle("Instructions");
//		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setVisible(true);
	}
}
