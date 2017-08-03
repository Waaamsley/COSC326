import java.util.*;
import java.lang.*;

public class Poker{
    
    private static List<String> outputs = new ArrayList<String>();
    private static String[] output;
    private static final List<String> numLogic = Arrays.asList("1", "13", "12", "11", "10", "9", "8", "7", "6", "5", "4", "3", "2");
    private static final List<String> numLogic2 = Arrays.asList("A", "K", "Q", "J", "T");
    private static final List<String> houseLogic = Arrays.asList("S", "H", "D", "C");

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String input;
        String[] hand;
        int count = 0;
        boolean valid;
        while (scan.hasNextLine()){
            input = scan.nextLine();
            hand = input.split("-|/| ");
            valid = validChecker(hand);
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

    private static boolean validChecker(String[] cHand){
        StringBuilder builder = new StringBuilder();
        int index;
        for (String card1 : cHand){
            if ((index = numLogic.indexOf(card1.substring(0, card1.length()-1).toUpperCase())) == -1){
                index = numLogic2.indexOf(card1.substring(0, card1.length()-1).toUpperCase()); 
            }
            if (index == -1 || houseLogic.indexOf(card1.substring(card1.length()-1, card1.length()).toUpperCase()) == -1 || cHand.length < 5){
                for (String s : cHand){
                    builder.append(s + " ");
                }
                outputs.add("Invalid: " + builder.toString().substring(0, builder.length()-1));
                return false;
            }
        }
        return true;
    }

    private static void sort(String[] cHand){
        StringBuilder builder = new StringBuilder();
        String[] output = new String[5];
        Integer comp;
        int index;
        int index2;
        int diffCount;
        for (String card1 : cHand){
            diffCount = 0;
            if ((index = numLogic.indexOf(card1.substring(0, card1.length()-1).toUpperCase())) == -1){
                index = numLogic2.indexOf(card1.substring(0, card1.length()-1).toUpperCase());
            }
            for (String card2 : cHand){
                if ((index2 = numLogic.indexOf(card2.substring(0, card2.length()-1).toUpperCase())) == -1){
                    index2 = numLogic2.indexOf(card2.substring(0, card2.length()-1).toUpperCase());
                }
                comp = Integer.compare(index, index2);
                if (comp < 0){
                    diffCount++;
                }else if(comp == 0){
                    diffCount += houseSort(card1, card2);
                }
            }
            output[diffCount] = card1;
        }
        for (String s : output){
            builder.append(s + " ");
        }
        outputs.add(builder.toString().substring(0, builder.length()-1).toUpperCase());
        return;
    }

    private static int houseSort(String c1, String c2){
        int index1 = houseLogic.indexOf(c1.substring(c1.length()-1, c1.length()).toUpperCase()); 
        int index2 = houseLogic.indexOf(c2.substring(c2.length()-1, c2.length()).toUpperCase());
        Integer comp = Integer.compare(index1, index2);
        if (comp < 0){
            return 1;
        }
        return 0;
    }

}
