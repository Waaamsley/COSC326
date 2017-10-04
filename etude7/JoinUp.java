package etude7;

import java.util.*;

public class JoinUp{

    private static int count = 0;
    private static List<Tree<String>> childs;

    private static StringBuilder sb;

    private static String rooty;

    private static Tree<String> treeC;

    private static int[] indexesO = new int[26];
    private static int[] indexes = new int[26];
    private static int[] indexEnd = new int[26];

    private static int mod = 97;

    public static void main(String[] args){
        ArrayList<String> dict = new ArrayList<String>();
        ArrayList<String> dict2 = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < indexes.length; i++){
            indexes[i] = -1;
        }
        try{
            while(scan.hasNext()){
                String w = scan.next(); 
                dict.add(w);
            }
            if (!dict.contains(args[1])){
                dict.add(args[1]);
            }
            Collections.sort(dict);
            dict2 = new ArrayList<String>(dict);
            char c = '!';
            for (int i = 0; i < dict.size(); i++){
                char t = dict.get(i).charAt(0);
                if (c != t && (int) t - mod >= 0 && (int) t - mod <= 25){
                    indexes[(int) t - mod] = i;
                    c = t;
                }
            }
            System.arraycopy(indexes, 0, indexesO, 0, 26);
            System.arraycopy(indexesO, 1, indexEnd, 0, 25);
            indexEnd[25] = dict.size();
        }catch(NoSuchElementException e){
            e.printStackTrace();
            System.out.println("Read in failed");
        }
        List<Tree<String>> root = new ArrayList<Tree<String>>();
        Tree<String> r = new Tree<String>(args[0]);
        root.add(r);
        rooty = r.rootValue;
            
        if (args[0].equals(args[1])){
            System.out.println(2 + " " + args[0] + " " + args[1]);
            System.out.println(2 + " " + args[0] + " " + args[1]);
        }else{
            join(root, args[1], dict, false);
            System.arraycopy(indexesO, 0, indexes, 0, 26);
            System.arraycopy(indexesO, 1, indexEnd, 0, 25);
            indexEnd[25] = dict2.size();
            root.clear();
            r = new Tree<String>(args[0]);
            root.add(r);
            childs.clear();
            count = 0;
            join(root, args[1], dict2, true);
        }
    }

    
    public static boolean presufSingle(String w1, String w2, boolean ord, int mlen){
        double len1 =  w1.length();
        double len2 =  w2.length();
        //System.out.println(w1 + " " + w2 + " " + mlen);
        if (ord){
            if (mlen >= Math.ceil(len2/2) && w1.substring((int) len1 - mlen, (int) len1).matches(w2.substring(0, mlen))){
                return true;
            }
        }else{
            if (mlen >= Math.ceil(len2/2) && w1.substring(0, mlen).matches(w2.substring((int)len2 - mlen, (int) len2))){
                return true;
            }
        }
        return false;
    }


    public static boolean presufDouble(String w1, String w2, boolean ord, int mlen){
        double len1 = w1.length();
        double len2 = w2.length();
        if (mlen < Math.ceil(len1/2) || mlen < Math.ceil(len2/2)){
            return false;
        }
        if (ord){
            if (w1.substring((int)len1 - mlen,(int) len1).matches(w2.substring(0, mlen))){
                return true;
            }
        }else{
            if (w1.substring(0, mlen).matches(w2.substring((int)len2 - mlen, (int) len2))){
                return true;
            }
        }
        return false;
    } 
 
    public static  void join(List<Tree<String>> input, String fin, ArrayList<String> dict, boolean d){
        count++;
        //System.out.println(count + " --- " + input.size() + " --- " + dict.size());
        childs = new ArrayList<Tree<String>>();
        if (input.size() == 0){
            System.out.println(0);
            return;
        }
        String word2;
        boolean contains = false;
        boolean finito = false;
        //System.out.println("---___---");
        try{
            for (Tree<String> word : input){
                //System.out.println(word.rootValue);
                //System.out.println("[][][][]");
                int plen = word.rootValue.length();
                for (int p = 0; p < plen; p++){
                    char pc = word.rootValue.charAt(p);
                    int start = (int) pc - mod;
                    int temp = start;
                    int t1 = -1;
                    int t2 = -1;
                    if (start >= 0 && start < 26){
                        t1 = indexes[start];
                        t2 = indexEnd[start];
                        if(t2 == -1){
                            start++;
                            while (t2 == -1 && start < 26){
                                t2 = indexes[start];
                                indexEnd[temp] = t2;
                                start++;
                            }
                            if (start == 26){
                                t2 = dict.size();
                                indexEnd[temp] = dict.size();
                            }
                        }
                    }
                    if (t1 == -1){
                        t2 = t1;
                    }
                    //System.out.println("___--___");
                    //System.out.println(t1 + " " + t2);
                    for (int i = t1; i < t2; i++){
                        word2 = dict.get(i);
                        if (plen - p <= word2.length() && indexes[temp] < indexEnd[temp] && !word.rootValue.equals(word2)){
                            if (orders(word.rootValue, word2)){
                                if (d){
                                    contains = presufDouble(word.rootValue, word2, true, plen - p);
                                }else{
                                    contains = presufSingle(word.rootValue, word2, true, plen - p);
                                }
                            }else{
                                if (d){
                                    contains = presufDouble(word2, word.rootValue, false, plen - p);
                                }else{
                                    contains = presufSingle(word2, word.rootValue, false, plen - p);
                                }
                            }
                        }
                        if(contains && i != indexEnd[(int) pc - mod]){
                            //System.out.println(word2 + " " + p);
                            contains = false;
                            treeC = new Tree<String>(word2);
                            for (String s : word.parentList){
                                treeC.parentList.add(s);
                            }
                            if (count > 1){
                                treeC.parentList.add(word.rootValue);
                            }
                            word.add(treeC);
                            if (word2.equals(fin)){
                                finito = true;
                                printTrace(treeC);
                            }
                            //System.out.println(word2 + " " + indexes[temp] + indexEnd[temp]);
                            dict.set(i, dict.get(indexEnd[(int) pc - mod] - 1));
                            indexEnd[(int) pc - mod]--;
                            t2--;
                            i--;
                        }
                        if (finito){
                            return;
                        }
                    }
                }
                getChildren(word);
               
            }
        }catch(StackOverflowError e){
            e.printStackTrace();
        }
        join(childs, fin, dict, d);
        return;
    }

    private static void getChildren(Tree<String> p){
        //if (count < 2){
        for (Tree<String> c : p.children){
            //System.out.println(c.rootValue);
                childs.add(c);
                }
        //System.out.println("___---___");
        //}
    }

    private static void printTrace(Tree<String> kid){
        sb = new StringBuilder();
        List<String> past = kid.parentList;
        for (String s : past){
            sb.append(s + " ");
        }
        System.out.println(count + 1 + " " + rooty + " " + sb.toString() + kid.rootValue);
    }

    public static boolean orders(String w1, String w2){
        if (w2.length() >= w1.length()){
            return false;
        }
        return true;
    }
}
