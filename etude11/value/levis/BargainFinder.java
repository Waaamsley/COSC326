package value;

import java.util.ArrayList;

/**
 * The class for bargain finders.
 * 
 * @author MichaelAlbert
 */
public class BargainFinder {
    
    private SiteInfo site;
    private CustomerInfo customer;
    private int budget;
 private int maxValue = 0;
 private ArrayList<String> maxArray = new ArrayList<String>();

    public BargainFinder(SiteInfo site, CustomerInfo customer, int budget) {
        this.site = site;
        this.budget = budget;
        this.customer = customer;
    }
    
    public ArrayList<String> shoppingList() { 

  ArrayList<String> startingList = new ArrayList<String>();  
  recCheckDeck(startingList, this.site, customer.getItems());  
     return this.maxArray;
    }
 private void recCheckDeck(ArrayList<String> currentItems, SiteInfo site, ArrayList<String> remainingValues){
   if(!currentItems.isEmpty()){
     int currentCost =  site.getCost(currentItems);
     int currentValue = 0;
     if(currentCost>this.budget){
       return;
     }
     for(String item: currentItems){
       currentValue+=this.customer.getValue(item);
     }
     if(currentValue>this.maxValue){
       this.maxValue = currentCost;
       this.maxArray = currentItems;
     }
   }
   if(!remainingValues.isEmpty()){
 ArrayList<String> newList = new ArrayList<String>(currentItems);
 ArrayList<String> newRemainder = new ArrayList<String>(remainingValues); 
 newList.add(remainingValues.get(0));
 newRemainder.remove(remainingValues.get(0));
 recCheckDeck(currentItems, site, newRemainder);
 recCheckDeck(newList, site, newRemainder);
  } 
 }
}
