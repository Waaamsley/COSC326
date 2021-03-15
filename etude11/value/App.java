package value;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Read site and customer info from command argument filenames, and test bargain
 * finder on them.
 * 
 * @author Michael Albert
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //Scanner siteReader = new Scanner(new File(args[0]));
        HashMap<String, Integer> siteData = new HashMap<>();
        /**
        while (siteReader.hasNextLine()) {
            Scanner sc = new Scanner(siteReader.nextLine());
            String item = sc.next();
            int cost = sc.nextInt();
            siteData.put(item, cost);
            System.out.println(item + " " + cost);
        }
        System.out.println();
        */
        //Scanner customerReader = new Scanner(new File(args[1]));
        HashMap<String, Integer> customerData = new HashMap<>();
        /**
        while (customerReader.hasNextLine()) {
            Scanner sc = new Scanner(customerReader.nextLine());
            String item = sc.next();
            int value = sc.nextInt();
            customerData.put(item, value);
            System.out.println(item + " " + value);
        }
        */
        siteData.put("Dog", 12);
        siteData.put("Cat", 15);
        siteData.put("Mouse", 5);
        siteData.put("Horse", 35);
        siteData.put("goldFish", 14);
        //siteData.put("Snake", 20);
        //siteData.put("Hampster", 17);
        //siteData.put("Ginny", 25);
        //siteData.put("ppl", 3);
        //siteData.put("rock", 28);
        
        customerData.put("Dog", 20);
        customerData.put("Cat", 17);
        customerData.put("Mouse", -3);
        customerData.put("Horse", -10);
        customerData.put("goldFish", 400);
        //customerData.put("Snake", 5);
        //customerData.put("Hampster", 12);
        //customerData.put("Ginny", 10);
        //customerData.put("ppl", 1);
        //customerData.put("rock", 60);
        SimpleSite site = new SimpleSite(siteData);
        CustomerInfo customer = new Customer(customerData);
        
        /**
        ArrayList<String> items = new ArrayList<>();
        items.add("Cat");
        System.out.println(items + " " + site.getCost(items));
        items.add("Mouse");
        System.out.println(items + " " + site.getCost(items));
        */
        
        BargainFinder bf = new BargainFinder(site, customer, 40);
        for(String s: bf.shoppingList()) System.out.println(s);
        
    }
    
}
