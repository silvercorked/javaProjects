package org.millardps.Jframess;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel; //the individual objects and items (text / images)
import javax.swing.JFrame; //the main, should only have one
import javax.swing.JPanel; //Put content of JFrame into these

import java.awt.color.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Jframesss {
	private JFrame frame = new JFrame("Dank Derp De Herp");
	
	public Jframesss(){
		createAndShowGUI();
		something();
	}
	public void createAndShowGUI(){
		frame.setLayout(new GridLayout());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		
		//JPanel swag = new JPanel(); // I dont know about these
		
		JLabel emptyLabel3 = new JLabel("Hello");
		
		//an attempt at creating a button with a mouse listener looking for clicks
		JButton button1 = new JButton("This button makes text appear in the console onClick");
		//button1.setBounds(40, 50, 300, 100); //this appears to do nothing. It doenst change the range of the object
		button1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("Hello"); //this works but doesnt seem to like adding things to frame, most likely becuase frame is already shown by the time this runs
                JPanel billy = new JPanel();//this didnt work
                billy.setSize(new Dimension(300, 300));
                billy.setBackground(Color.RED);
                frame.add(billy);
                
                System.out.println(billy.getWidth());
                
                
                //perhaps we 
            }
        });
		frame.add(button1);
		
		
		JLabel emptyLabel = new JLabel("Hello I exist");
		//emptyLabel.setDisplayedMnemonic(key); //Calls focus and opens after you press a button
		emptyLabel.setPreferredSize(new Dimension(50, 50)); //nessacery to establish size of object
		emptyLabel.setForeground(Color.red);
		emptyLabel.addMouseMotionListener(new MouseAdapter() {
			public void MouseMoved(MouseEvent e) {
				System.out.println("The Mouse moved!");
			}
		});
		frame.getContentPane().add(emptyLabel);
		//Label.setBounds()//can be used to place specific labels around the page then can run onmousemove type methods on that area
		
		ImageIcon icon = new ImageIcon("H:/Java Programming/JFramePractice/JFrames/src/org/millardps/Jframess/Images/Shelves.gif");
		JLabel emptyLabel1 = new JLabel(icon);
		emptyLabel1.setPreferredSize(new Dimension(250, 250));
		emptyLabel1.setBackground(Color.GREEN);
		emptyLabel1.setToolTipText("This displays a tool tip");
		frame.getContentPane().add(emptyLabel1);
		
		
		ImageIcon icon2 = new ImageIcon("H:/Java Programming/JFramePractice/JFrames/src/org/millardps/Jframess/Images/Shelves.gif");
		JLabel emptyLabel2 = new JLabel("Hello");
		emptyLabel2.setPreferredSize(new Dimension(100, 100));
		emptyLabel2.setBackground(Color.GREEN);
		frame.getContentPane().add(emptyLabel2);
		
		
		frame.pack();
		frame.setVisible(true);
		frame.getBounds();
		System.out.println(frame.getHeight());
		System.out.println(frame.getWidth());
	}
	
	public void something(){
		
	}
}
