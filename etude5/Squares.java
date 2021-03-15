import java.util.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;

public class Squares extends JPanel{

    private ArrayList<Square> square = new ArrayList<Square>();
    public Color color;
    public static double dS;
    private String[] iElements;
    private static int dim = 400;
    private double scale;

    public Squares(String input, double dS){
        this.dS = dS;
        int mid = (dim/2) - ((int)dS/2);
        iElements = input.split(" ");
        color = new Color(Integer.parseInt(iElements[1]),
              Integer.parseInt(iElements[2]), Integer.parseInt(iElements[3]));
        square.add(new Square(color, dS, mid, mid));
    }

    public Squares(Squares previous, String input){
        int mid = (dim/2) - ((int)dS/2);
        setOpaque(false);
        iElements = input.split(" ");
        scale = Double.parseDouble(iElements[0]);
        color = new Color(Integer.parseInt(iElements[1]),
              Integer.parseInt(iElements[2]), Integer.parseInt(iElements[3]));
        int newSize = (int) (scale * dS);
        int px;
        int py;
        int size;
        for (Square s : previous.square){
            px = s.x;
            py = s.y;
            size = s.hw;
            square.add(new Square(color, newSize, px - (newSize/2), py - (newSize/2)));
            square.add(new Square(color, newSize, px + (size - (newSize/2)), py - (newSize/2)));
            square.add(new Square(color, newSize, px - (newSize/2), py + (size - (newSize/2))));
            square.add(new Square(color, newSize, px + (size - (newSize/2)), py + (size - (newSize/2))));
        }
        
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int i=0; i < square.size(); i++){
            square.get(i).display(g);
        }
    }
}
