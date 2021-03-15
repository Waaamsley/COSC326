package etude13;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;



public class ButtonPanel extends JPanel{
	
	protected static Square sq;
	
	public ButtonPanel(){
		
	}
	
	public void paintComponent(Graphics g){
			super.paintComponent(g);
			sq.display(g);
		}
		
}