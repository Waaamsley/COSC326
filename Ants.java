/** 
 * Ants.java
 * James Walmsley - 8358979, Kyle Goucher 8080806
 * August/2017
 */

import java.util.*;

public class Ants{

    /**
     * global variables needed.
     */
    private static Scanner scan = new Scanner(System.in);
    private static Scanner temp;
    private static HashMap<String, String> hash = new HashMap<String, String>();
    private static StringBuilder builder = new StringBuilder();
    private static Integer x;
    private static Integer y;
    
    /**
     *  Takes input and reads it in to a method in complete dna sets.
     */
    public static void main(String[] args){
        String input = "";
        String output = "";
        List<String> inputs = new ArrayList<String>();
        boolean read = false;
        try {
            input = scan.nextLine();
            while (scan.hasNextLine()){
                if (read){
                    input = scan.nextLine();
                    read = false;
                }
                if(scan.hasNextLine() && (input.isEmpty() || input.charAt(0) == '#')){
                    input = scan.nextLine();  
                }else if (!input.isEmpty() && input.charAt(0) != '#'){
                    inputs.add(input);
                    builder.append(input + "\n");
                    temp = new Scanner(input);
                    read = true;
                    if (temp.hasNextInt()){
                        brain(inputs);
                        builder.append("# " + x + " " + y + "\n\n");
                        inputs.clear();
                    }
                    temp.close();
                }
             }
        }catch (NoSuchElementException e){
            System.out.println("last line fail");
        }
        output = builder.toString();
        System.out.println(output.substring(0, output.length() - 2));
        return;
    }

    /**
     *  This method simulates ant logic
     *  @param genes contains each information to perform logic
    */
    private static void brain(List<String> genes){
        final String directions = "NESW";
        Integer stepsTaken = 0;
        String direction = "N";
        String oState = "";
        String state = "";
        final int mod = 2;
        final int mod2 = 5;
        Integer steps = 0;
        x = 0;
        y = 0;
        int newDirection = 2;
        int newState = 0;
        try{
            steps = Integer.parseInt(genes.get(genes.size() -1));
        }catch(NumberFormatException n){
            System.out.println("Last line of dna was not a number, insert correct dna.");
        }
        hash.clear();
        oState = genes.get(0).substring(0, 1);
        state = genes.get(0).substring(0, 1);
        while (stepsTaken < steps){
            if (hash.get(x + " " + y) == null){
                hash.put(x + " " + y, oState);
            }
            for (int i = 0; i < genes.size(); i++){
                if (hash.get(x + " " + y).equals(genes.get(i).substring(0, 1))){
                    stepsTaken++;
                    newState = newDirection + mod2;
                    newDirection = directions.indexOf(direction) + mod;
                    direction = genes.get(i).substring(newDirection, newDirection+1);
                    state = genes.get(i).substring(newState, newState+1);
                    hash.put(x + " " + y, state);
                    compass(direction);
                    i = genes.size();
                }
            }
        }   
    }
    
    /**
     *   manipulates global x and y values to give ants current position.
     *   @param direct is the current direction taken
    */
    private static void compass(String direct){
        if (direct.equals("N")){
            y+=1;
        }else if (direct.equals("E")){
            x+=1;
        }else if (direct.equals("S")){
            y-=1;
        }else{
            x-=1;
        }
    }
    
 }
