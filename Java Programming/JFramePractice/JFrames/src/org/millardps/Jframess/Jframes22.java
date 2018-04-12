package org.millardps.Jframess;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Jframes22 {
	JPanel emptyPanel3 = new JPanel();
	
	public Jframes22(){
		newJFrame();
	}
	
	public void newJFrame(){
		
		JFrame dank = new JFrame("Herro pweeze");
		
		dank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dank.setSize(1000, 1000);
		
		JPanel emptyPanel = new JPanel();
		emptyPanel.setSize(50, 900);
		emptyPanel.setLocation(50, 25);
		emptyPanel.setBackground(Color.BLACK);
		
		JPanel emptyBetween = new JPanel();
		emptyBetween.setSize(5, 900);
		emptyBetween.setLocation(65, 90);
		emptyBetween.setBackground(Color.RED);
		
//		JPanel emptyPanel2 = new JPanel();
//		emptyPanel2.setSize(50, 100);
//		emptyPanel2.setLocation(40, 200);
//		emptyPanel2.setBackground(Color.BLACK);
		
		JPanel emptyBetween2 = new JPanel();
		emptyBetween2.setSize(50, 5);
		emptyBetween2.setLocation(40, 300);
		emptyBetween2.setBackground(Color.RED);
		
//		JPanel emptyPanel3 = new JPanel();
//		emptyPanel3.setSize(50, 100);
//		emptyPanel3.setLocation(40, 310);
//		emptyPanel3.setBackground(Color.BLACK);
		
		JPanel emptyBetween3 = new JPanel();
		emptyBetween3.setSize(50, 5);
		emptyBetween3.setLocation(40, 410);
		emptyBetween3.setBackground(Color.RED);
		
		emptyPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	emptyPanel.setSize(100, 100);
        		emptyPanel.setLocation(200, 40);
        		System.out.println("Hello");
        		dank.repaint();
        		
        	}	
        });
		dank.add(emptyBetween);
		dank.add(emptyBetween2);
		dank.add(emptyBetween3);
		dank.add(emptyPanel);
//		dank.add(emptyPanel2);
//		dank.add(emptyPanel3);
		
		
		
		
		JButton button1 = new JButton("This makes");
		button1.createImage(100, 200);
		dank.add(button1);
		
		JPanel emtpyPanel2 = new JPanel();
		emtpyPanel2.setSize(10, 10);
		emtpyPanel2.setBackground(Color.GRAY);
		emtpyPanel2.setOpaque(true);
		dank.add(emtpyPanel2);
		
		dank.show(true);
	}
	public int setX(){
		Random rand = new Random(499);
		int number = rand.nextInt();
		return number;
		
	}
}
