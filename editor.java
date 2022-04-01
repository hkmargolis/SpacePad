package notepadAPP;
import javax.swing.*;
import java.io.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import java.io.BufferedWriter;
import java.sql.Timestamp;
import java.util.Random;


    
	
public class editor extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

		//text component
		JTextArea t;
		
		// frame
		JFrame f;

		//constructor
		editor()
		{
			//create a frame
			f = new JFrame("SpacePad");
			
			try {
				//set metal look
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				
				//set the them to ocean
				MetalLookAndFeel.setCurrentTheme(new OceanTheme());
			}
			catch (Exception e) {}
		
			//text component
			t = new JTextArea();
			t.setBackground(Color.BLACK);
			Font font = new Font("sans seriff", Font.BOLD, 16);
	        t.setFont(font);
	        t.setForeground(Color.WHITE);
			
			//create menu bar
			JMenuBar mb = new JMenuBar();
			
			//create file menu
			JMenu m1 = new JMenu("File");
			
			//Create file menu items
			JMenuItem mi1 = new JMenuItem("New");
			JMenuItem mi2 = new JMenuItem("Open");
			JMenuItem mi3 = new JMenuItem("Save");
			JMenuItem mi9 = new JMenuItem("About");
			
			//add action listener for file
			mi1.addActionListener(this);
			mi2.addActionListener(this);
			mi3.addActionListener(this);
			mi9.addActionListener(this);
			
			m1.add(mi1);
			m1.add(mi2);
			m1.add(mi3);
			m1.add(mi9);
			
			//create edit menu
			JMenu m2 = new JMenu("Edit");
			
			//Create edit menu items
			JMenuItem mi4 = new JMenuItem("cut");
			JMenuItem mi5 = new JMenuItem("copy");
			JMenuItem mi6 = new JMenuItem("paste");
			
			//add action listener for edit
			mi4.addActionListener(this);
			mi5.addActionListener(this);
			mi6.addActionListener(this);
			
			m2.add(mi4);
			m2.add(mi5);
			m2.add(mi6);
			
			JButton red = new JButton();
			JButton orange = new JButton();
			JButton yellow = new JButton();
			JButton green = new JButton();
			JButton blue = new JButton();
			JButton purple = new JButton();
			JButton white = new JButton();
			
			red.setText("Red");
			orange.setText("Orange");
			yellow.setText("Yellow");
			green.setText("Green");
			blue.setText("Blue");
			purple.setText("Purple");
			white.setText("White");
			
			
			red.setBounds(50,100,50,50);
			orange.setBounds(50,200,50,50);
			yellow.setBounds(50,300,50,50);
			green.setBounds(50,400,50,50);
			blue.setBounds(50,500,50,50);
			purple.setBounds(50,600,50,50);
			white.setBounds(50, 700, 50, 50);

			//set button color
			red.setBackground(Color.RED);
			orange.setBackground(Color.ORANGE);
			yellow.setBackground(Color.YELLOW);
			green.setBackground(Color.GREEN);
			blue.setBackground(Color.CYAN);
			purple.setBackground(Color.MAGENTA);
			white.setBackground(Color.WHITE);
			
			red.addActionListener(this);
			orange.addActionListener(this);
			yellow.addActionListener(this);
			green.addActionListener(this);
			blue.addActionListener(this);
			purple.addActionListener(this);
			white.addActionListener(this);
			
			
			
			//create exit menu
			JMenu m3 = new JMenu("Exit");
			JMenuItem mx = new JMenuItem("Exit");
			mx.addActionListener(this);
			m3.add(mx);
			
			//add menu items to menu bar
			mb.add(m1);
			mb.add(m2);
			mb.add(m3);
			
			mb.add(red);
			mb.add(orange);
			mb.add(yellow);
			mb.add(green);
			mb.add(blue);
			mb.add(white);
			
			f.setJMenuBar(mb);
			f.add(t);
			f.setSize(1000,1000);
			f.setVisible(true);
			
		}
		
		//if a button is pressed...
		JMenuItem mx = new JMenuItem("Exit");
		public void actionPerformed(ActionEvent e) {
			
			String s = e.getActionCommand();
			
			if(s.equals("cut")) 
			{t.cut();}
			
			else if(s.equals("copy")) 
			{t.copy();}
			
			else if(s.equals("paste"))
			{t.paste();}
		
			else if(s.equals("Save"))
			{
				//create an object of JFileChooser class
				JFileChooser j = new JFileChooser("f:");
				
				//invoke the showSaveDialog function
				int r = j.showSaveDialog(null);
				
				if(r ==JFileChooser.APPROVE_OPTION) {
					//set the label to the path of the selected directory
					File file = new File(j.getSelectedFile().getAbsolutePath());
					
					try {
						//create a file writer
						FileWriter wr = new FileWriter(file, false);
						
						//create a buffered writer to write
						BufferedWriter w = new BufferedWriter(wr);
						
						//write
						w.write(t.getText());
						
						w.flush();
						w.close();
						
						}
						catch(Exception evt)
						{JOptionPane.showMessageDialog(f, evt.getMessage());
					}
				}
				//if operation cancelled
				else JOptionPane.showMessageDialog(f,"Command was cancelled");
			}
			
			
			else if (s.equals("Open")) {
			//create an object of JFileChooser class...same as above
				JFileChooser j = new JFileChooser("f:");
				int r = j.showOpenDialog(null);
				
				if( r == JFileChooser.APPROVE_OPTION) {
					File file = new File(j.getSelectedFile().getAbsolutePath());
					
					try {
						String s1 = "", sl = "";
						
						FileReader fr = new FileReader(file);
						
						BufferedReader br = new BufferedReader(fr);
						
						s1 = br.readLine();
						
						while((s1 = br.readLine()) != null) {
							sl = sl + "\n" + s1;
						}
					t.setText(s1);
					}catch(Exception evt) 
						{JOptionPane.showMessageDialog(f, evt.getMessage());}
				
				}
					else JOptionPane.showMessageDialog(f, "Command was cancelled");
			}
			
			else if(s.equals("New")) 
			{t.setText("");}
			
			else if(s.equals("Red"))
			{t.setForeground(Color.RED);}
			
			else if(s.equals("Orange"))
			{t.setForeground(Color.ORANGE);}
			
			else if(s.equals("Yellow"))
			{t.setForeground(Color.YELLOW);}
			
			else if(s.equals("Green"))
			{t.setForeground(Color.GREEN);}
			
			else if(s.equals("Blue"))
			{t.setForeground(Color.CYAN);}
			
			else if(s.equals("Purple"))
			{t.setForeground(Color.MAGENTA);}
			
			else if(s.equals("White"))
			{t.setForeground(Color.WHITE);}
			
			else if (s.equals("close"))
			{f.setVisible(false);}
			}

		//main class
		public static void main(String[] args)
		{editor np = new editor();}
		
	}


