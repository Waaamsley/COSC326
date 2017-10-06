package etude8;

import java.util.*;


public class Main{

    public static void main(String[] args){
        String [] operations = {"+","-","*","/","gcd", "<", ">","="};
        Scanner sc = new Scanner(System.in);
        
        while(sc.hasNextLine()){
            String str = sc.nextLine();
            String [] inputs = str.split("\\s+");
            if(!str.isEmpty() && str.charAt(0) != '#'){
                System.out.println(str);
                if(inputs.length == 3){
                    boolean op = Arrays.asList(operations).contains(inputs[1]);
                    if(!op){
                    System.out.println("# Syntax error");                       
                  }else{
                    
                    MassiveInt n1 = new MassiveInt(inputs[0]);
                    MassiveInt n2 = new MassiveInt(inputs[2]);
                    switch(inputs[1]){
                      case "+":
                        System.out.println("# " +
                                           MassiveInt.addition(n1,n2));;
                        break;
                      case "-":
                        System.out.println("# " +
                                           MassiveInt.subtraction(n1,n2));
                        break;
                      case "*":
                        System.out.println("# " +
                                           MassiveInt.multiply(n1,n2));
                        break;
                      case "/" :
                          String[] result =  MassiveInt.divide(n1,n2);
                          if(result[0].equals("-1")){
                              System.out.println("Not Possible");
                          }else{
                                  System.out.println("# " + result[0] + " " + result[1]);
                          }
                        break;
                      case "gcd":
                          System.out.println("# " +      MassiveInt.gcd(n1,n2));
                        break;
                      case "<":
                        System.out.println("# " +
                                           ( MassiveInt.compareTo(n1,n2) < 0));
                        break;
                      case ">" :
                        System.out.println("# " +
                                           ( MassiveInt.compareTo(n1,n2) > 0));
                        break;
                      case "=":
                        System.out.println("# " +
                                           (MassiveInt.compareTo(n1,n2) == 0));
                        break;
                        
                    }
                  }
                }else{
                  System.out.println("# Syntax error");
                }
            }
        }
    }
}




                    
                

            


      
