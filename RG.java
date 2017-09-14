import java.util.*;

public class RG{

    private static List<String> colours;
    private static StringBuilder sb;

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Scanner s;
        String input;
        int[] elements;
        try{
            while (scan.hasNextLine()){
                input = scan.nextLine();
                s = new Scanner(input);
                if (!input.isEmpty() && input.charAt(0) != ('#')){
                    elements = new int[2];
                    for (int i = 0; i < 2; i++){
                        elements[i] = s.nextInt();
                    }
                    visualiser(elements);
                }
            }
        }catch(NoSuchElementException e){
            System.out.println("No input!");
        }
        
    }

    private static void visualiser(int[] inputs){
        colours = new ArrayList<String>();
        sb = new StringBuilder();
        colours.add("G");
        colours.add("G");
        //int n = inputs[0];
        int r;
        int g;
        int n = inputs[0];
        for (int a = 2; a < (n + inputs[1]); a++){;
            r = 0;
            g = 0;
            for (int i = 1; i <= a/2; i++){;
                if (a % i == 0 || a % i == 1){
                    if (colours.get(i).equals("G")){
                        g++;
                    }else{
                        r++;
                    }
                }
            }
            if (g > r){
                colours.add(a, "R");
            }else{
                colours.add(a, "G");
            }
        }

        for (int a = inputs[0]; a < colours.size(); a++){
            sb.append(colours.get(a));
        }
        System.out.println(sb.toString());
    }
    
}
