import java.util.*;

public class RG{

    private static List<String> colours;
    private static List<Integer> indexes;
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
        Integer index;
        sb = new StringBuilder();
        colours.add("G");
        colours.add("G");
        int r;
        int g;
        int n = inputs[0];
        for (int a = 2; a < (n + inputs[1]); a++){;
            indexes = new ArrayList<Integer>();
            r = 0;
            g = 0;
            for (int i = a/2; i > 1; i--){
                index = a/i;
                indexes.add(index);
            }
            g++;
            for (Integer i : indexes){
                //System.out.println(i);
            }
            //System.out.println("-----");
            for (int i = 0; i < indexes.size(); i++){
                if (indexes.size() == 1){
                    r++;
                }else if(i == 0){
                    if (colours.get(indexes.get(i)).equals("G")){
                        g++;
                    }else{
                        r++;
                    }
                }else if (indexes.get(i-1) < indexes.get(i)){
                    if (colours.get(indexes.get(i)).equals("G")){
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
        System.out.println(inputs[0] + " " + inputs[1]);
        System.out.println(sb.toString());
    }
    
}