package etude13;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;

public class Quilt{

    private static ArrayList<Squares> layers = new ArrayList<Squares>();
    private static ArrayList<String> inputs = new ArrayList<String>();
 private static JButton newL = new JButton("New Layer");
 private static JButton deleteL = new JButton("Delete Layer");
 private static JSlider size = new JSlider(JSlider.HORIZONTAL,
     0, 100, 50);
 private static JSlider red = new JSlider(JSlider.HORIZONTAL,
     0, 255, 0);
 private static JSlider green = new JSlider(JSlider.HORIZONTAL,
     0, 255, 0);
 private static JSlider blue = new JSlider(JSlider.HORIZONTAL,
     0, 255, 0);
    private static JLayeredPane panels;
 private static ButtonPanel buttonPanel;
 private static JPanel master;
 private static JFrame quiltFrame;
 private static Color color;
    private static double dS = 300;
 private static double dSo = 300;
 private static double sizeValue = 0.5;
 private static int redValue = 0;
 private static int greenValue = 0;
 private static int blueValue = 0;
 private static int x = 100;
 private static int y = 337;
 private static int hw = 50;

    public static void main(String[] args){
        quiltFrame = new JFrame();
  master = new JPanel();
  new Quilt();

        try{
            Scanner scan = new Scanner(System.in);
   System.out.println("\nEnter input to build quilt layers or enter stop to load gui.");
   String in = "";
            while(scan.hasNextLine() && !(in = scan.nextLine()).equals("stop")){
    inputs.add(in);
            }
        }catch(NoSuchElementException|NullPointerException e){
            System.out.println("There was no input!");
        }
  
  if (inputs.size() > 0){
   panels = new JLayeredPane();
   builder();
   String[] str = inputs.get(inputs.size()-1).split(" ");
    
    size.setValue((int)(Double.parseDouble(str[0])*100.0));                      
    red.setValue(Integer.parseInt(str[1]));
    green.setValue(Integer.parseInt(str[2]));
    blue.setValue(Integer.parseInt(str[3]));
	quiltFrame.repaint();
  }else{
   panels = new JLayeredPane();
  }
        panels.setPreferredSize(new Dimension(400, 400));
  master.add(buttonPanel);
  master.add(panels);
        quiltFrame.getContentPane().add(master);
        quiltFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quiltFrame.pack();
        quiltFrame.setLocationRelativeTo(null);
        quiltFrame.setVisible(true);
    }
 
 private Quilt(){
  buttonPanel = new ButtonPanel();
  buttonPanel.setBackground(Color.pink);
  buttonPanel.setPreferredSize(new Dimension(250, 400));
  Font font = new Font("Serif", Font.BOLD, 15);
  size.setMajorTickSpacing(50);
  size.setMinorTickSpacing(5);
  red.setMajorTickSpacing(255);
  red.setMinorTickSpacing(15);
  green.setMajorTickSpacing(255);
  green.setMinorTickSpacing(15);
  blue.setMajorTickSpacing(255);
  blue.setMinorTickSpacing(15);
  size.setPaintTicks(true);
  size.setPaintLabels(true);
  red.setPaintTicks(true);
  red.setPaintLabels(true);
  green.setPaintTicks(true);
  green.setPaintLabels(true);
  blue.setPaintTicks(true);
  blue.setPaintLabels(true);
  size.setFont(font);
  red.setFont(font);
  green.setFont(font);
  blue.setFont(font);
  size.setSnapToTicks(true);
  red.setSnapToTicks(true);
  green.setSnapToTicks(true);
  blue.setSnapToTicks(true);
  size.setName("sizeS");
  red.setName("redS");
  green.setName("greenS");
  blue.setName("blueS");
  ButtonListener listener = new ButtonListener();
  newL.addActionListener(listener);
  deleteL.addActionListener(listener);
  SliderListener cListener = new SliderListener();
  size.addChangeListener(cListener);
  red.addChangeListener(cListener);
  green.addChangeListener(cListener);
  blue.addChangeListener(cListener);
  buttonPanel.add(newL);
  buttonPanel.add(deleteL);
  buttonPanel.add(new JLabel("Size (Percentage)"));
  buttonPanel.add(size);
  buttonPanel.add(new JLabel("Red value"));
  buttonPanel.add(red);
  buttonPanel.add(new JLabel("Green value"));
  buttonPanel.add(green);
  buttonPanel.add(new JLabel("Blue value"));
  buttonPanel.add(blue);
  size.setValue(100);
  color = new Color(redValue, greenValue, blueValue);
  buttonPanel.sq = new Square(color, hw, x, y);
 }
 
 private static void  builder(){
  for (int i = 0; i < inputs.size(); i++){
            if (i == 0){
                panels.removeAll();
    layers.clear();
                layers.add(i, new Squares(inputs.get(i), dS));
                layers.get(i).setBounds(0, 0, 400, 400);
                panels.add(layers.get(i), new Integer(i));
                red.setValue(redValue);
                blue.setValue(blueValue);
                green.setValue(greenValue);
                
               
                
            }else{
                double dsp = dS;
                dimensionChecker();
                if (dS < dsp){
                    i = -1;
                }else{
                    layers.add(i, new Squares(layers.get(i-1), inputs.get(i)));
                    layers.get(i).setBounds(0, 0, 400, 400);
                    panels.add(layers.get(i), new Integer(i));
                }
            }
        }
  if (inputs.size() == 0){
   panels.removeAll();
   layers.clear();
  }
 }
    
    private static void dimensionChecker(){
        double used = 0;
        String[] elements;
        for (String input : inputs){
            elements = input.split(" ");
            used += (Double.parseDouble(elements[0]) * dS);
        }
        double used2 = used;
        double dS2 = dS;
        while(used2 >= 400){
            used2 = 0;
            dS2 -= 10;
            for (String input : inputs){
                elements = input.split(" ");
                used2 += (Double.parseDouble(elements[0]) * dS2);
            }
        }
        dS = dS2;
    }
 
 private class ButtonListener implements ActionListener{
  
  public void actionPerformed(ActionEvent e){
   JButton b = (JButton) e.getSource();
   if (b.getText().equals("New Layer")){
    //quiltFrame.remove(master);
    //master.remove(panels);
    if (inputs.size() == 0){
     inputs.add(1 + " " + redValue + " " + greenValue
     + " " + blueValue);
    }else if (inputs.size() > 0){
     inputs.add(sizeValue + " " + redValue + " " + greenValue
      + " " + blueValue);
    }
    dS = dSo;
    builder();
    //panels.setPreferredSize(new Dimension(400, 400));
    //master.add(panels);
    //quiltFrame.add(master);
   }
   else if (b.getText().equals("Delete Layer") && inputs.size() > 0){
    inputs.remove(inputs.size()-1);
    
    dS = dSo;
    builder();
    
    String[] str = inputs.get(inputs.size()-1).split(" ");
    
    size.setValue((int)(Double.parseDouble(str[0])*100.0));                      
    red.setValue(Integer.parseInt(str[1]));
    green.setValue(Integer.parseInt(str[2]));
    blue.setValue(Integer.parseInt(str[3]));
   }
   quiltFrame.repaint();
  }
  
 }
 
 private class SliderListener implements ChangeListener{
  
  public void stateChanged(ChangeEvent e){
   JSlider s = (JSlider) e.getSource();
   if (s.getName().equals("sizeS")){
    sizeValue = ((double)s.getValue()/100);
   }else if (s.getName().equals("redS")){
    redValue = s.getValue();
   }else if (s.getName().equals("greenS")){
    greenValue = s.getValue();
   }else if (s.getName().equals("blueS")){
    blueValue = s.getValue();
   }
   color = new Color(redValue, greenValue, blueValue);
   buttonPanel.sq = new Square(color, hw, x, y);
   if (layers.size() == 1){
    inputs.remove(0);
    inputs.add(1 + " " + redValue + " " + greenValue + " " + blueValue);
    dS = dSo;
    builder();
   }else if (layers.size() > 1){
    inputs.remove(inputs.size()-1);
    inputs.add(sizeValue + " " + redValue + " " + greenValue + " " + blueValue);
    dS = dSo;
    builder();
   }
   quiltFrame.repaint();
  }
  
 }

}
