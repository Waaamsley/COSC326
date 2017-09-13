import java.util.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;

public class Quilt{

    private static ArrayList<Squares> layers = new ArrayList<Squares>();
    private static ArrayList<String> inputs = new ArrayList<String>();
    private static JLayeredPane panels;
    private static double dS = 300;

    public static void main(String[] args){
        JFrame quiltFrame = new JFrame();
        try{
            //File file = new File("C:\\Users\\jbwal\\OneDrive\\Documents\\Uni - 2017\\COSC326\\etude5\\t.txt");
            Scanner scan = new Scanner(System.in);
            while(scan.hasNextLine()){
                inputs.add(scan.nextLine());
            }
        }catch(/**FileNotFoundException|*/NoSuchElementException|NullPointerException e){
            System.out.println("There was no input!");
        }
        for (int i = 0; i < inputs.size(); i++){
            if (i == 0){
                panels = new JLayeredPane();
                layers.add(i, new Squares(inputs.get(i), dS));
                layers.get(i).setBounds(0, 0, 400, 400);
                panels.add(layers.get(i), new Integer(i));
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
        panels.setPreferredSize(new Dimension(400, 400));
        quiltFrame.getContentPane().add(panels);
        quiltFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quiltFrame.pack();
        quiltFrame.setLocationRelativeTo(null);
        quiltFrame.setVisible(true);
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
 
}
