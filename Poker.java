/**
 * James Walmsley - 8358979
 * 3 August 2017
 */

import java.util.*;
import java.lang.*;

/** 
 * This accepts a poker hand of 5 cards in the correct format and sorts them
 * in order from lowest to highest
 */
public class Poker{

    // global variables 
    private static List<String> outputs = new ArrayList<String>();
    private static String[] output;
    private static final List<String> numLogic = Arrays.asList("A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2");
    private static final List<String> nums = Arrays.asList("1", "13", "12", "11");
    private static final List<String> houseLogic = Arrays.asList("S", "H", "D", "C");
    private static String[] hand;
    /** 
     * reads in input sending it to methods
     * @Param args: input
     */
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String input;;
        int count = 0;
        boolean valid;
        while (scan.hasNextLine()){
            input = scan.nextLine();
            hand = input.split("-|/| ");
            valid = validChecker(input);
            if (valid){
                sort(hand);
            }
        }
        while (count < outputs.size() && outputs.get(count) != null){
           System.out.println(outputs.get(count));
           count++;
        }
        return;
    }
    
    /** 
     * Checks if input is a valid hand
     * @Param cHand: current hand being checked
     */
    private static boolean validChecker(String original){
        int cIndex;
        int hIndex;
        int switchC;
        String card;
        if (!Arrays.equals(original.split(" "), hand) && !Arrays.equals(original.split("-"), hand) && !Arrays.equals(original.split("/"), hand)){
            outputs.add("Invalid: " + original);
            return false;
        }else{
            for (int i = 0; i < 5; i++){
                card = hand[i];
                if (card.substring(0, card.length()-1).toUpperCase().equals("T")){
                    hand[i] = "10" + card.substring(card.length()-1, card.length());
                    card = hand[i];
                }
                if (card.length() < 2){
                    outputs.add("Invalid: " + original);
                    return false;
                }
                switchC = nums.indexOf(card.substring(0, card.length()-1));
                hIndex = houseLogic.indexOf(card.substring(card.length()-1, card.length()).toUpperCase());
                if (switchC != -1 && hIndex != -1){
                    hand[i] = numLogic.get(switchC) + houseLogic.get(hIndex);
                    card = hand[i];
                 
                }                
                cIndex = numLogic.indexOf(card.substring(0, card.length()-1).toUpperCase());
                if (cIndex == -1 || hIndex == -1 || hand.length != 5){
                    outputs.add("Invalid: " + original);
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Initial sort of vards by value
     * cards with same value then go into a method that sorts by house
     * @Param cHand: current hand being checked.
     */
    private static void sort(String[] cHand){
        StringBuilder builder = new StringBuilder();
        String[] output = new String[5];
        Integer comp;
        int index;
        int index2;
        Integer i1;
        Integer i2;
        int diffCount;
        for (String card1 : cHand){
            diffCount = 0;
            index = numLogic.indexOf(card1.substring(0, card1.length()-1).toUpperCase());
            for (String card2 : cHand){
                index2 = numLogic.indexOf(card2.substring(0, card2.length()-1).toUpperCase());
                i1 = (Integer) index;
                i2 = (Integer) index2;
                comp = i1.compareTo(i2);
                if (comp < 0){
                    diffCount++;
                }else if(comp == 0){
                    diffCount += houseSort(card1, card2);
                }
            }
            output[diffCount] = card1;
        }
        for (int i = 0; i < output.length; i++){
            if (output[i] == null){
                output[i] = output[i - 1];
            }
            builder.append(output[i] + " ");
        }
        outputs.add(builder.toString().substring(0, builder.length()-1).toUpperCase());
        return;
    }

    /**
     * sorts given cards with same value by house
     * @Param c1: card1
     * @Param c2: card2
     */
    private static int houseSort(String c1, String c2){
        int index1 = houseLogic.indexOf(c1.substring(c1.length()-1, c1.length()).toUpperCase()); 
        int index2 = houseLogic.indexOf(c2.substring(c2.length()-1, c2.length()).toUpperCase());
        Integer i1 = (Integer) index1;
        Integer i2 = (Integer) index2;
        Integer comp = i1.compareTo(i2);
        
        if (comp < 0){
            return 1;
        }
        return 0;
    }

}
