package etude13;

import java.util.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;

public class Square{

    private Color color;
    public int hw;
    public int x;
    public int y;
    
    public Square(Color color, double hw, int x, int y){
        this.color = color;
        this.hw = (int) hw;
        this.x = x;
        this.y = y;
    }

    public void display(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, hw, hw);
    }
    
}
