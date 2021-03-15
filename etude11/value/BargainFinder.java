package value;

import java.util.*;

/**
 * The class for bargain finders.
 * James Walmsley (8358979), Kyle Goucher (8080806), Shonit Naidu (4126740), Alfred Pardoe (7150195)
 * @author MichaelAlbert
 */
public class BargainFinder {
    
    private SiteInfo site;
    private CustomerInfo customer;
    private int budget;

    private List<String> cart;
    private ArrayList<String> optimal = new ArrayList<String>();
    private int best = 0;

    public BargainFinder(SiteInfo site, CustomerInfo customer, int budget) {
        this.site = site;
        this.customer = customer;
        this.budget = budget;
    }
    
    public ArrayList<String> shoppingList() {
    	ArrayList<String> items = customer.getItems();
        ArrayList<String> cart = new ArrayList<String>();
        delve(cart, items);
        return optimal;
    }
    
    public void delve(ArrayList<String> currentCart, ArrayList<String> itemList){
        int cartCost = site.getCost(currentCart);
        int cartValue = 0;
        for (String sC: currentCart){
            cartValue += customer.getValue(sC);
        }
        if (cartCost > this.budget) {
            return;
        }
        if (cartValue > this.best){
            best = cartValue;
            optimal = new ArrayList<>(currentCart);
        }
        if (!itemList.isEmpty()){
            for (int i = 0; i < itemList.size(); i++){
                currentCart.add(itemList.get(i));
                String temp = itemList.get(i);
                itemList.remove(i);
                delve(currentCart, itemList);
                currentCart.remove(currentCart.size()-1);
                itemList.add(i, temp);
            }
        }
    }
}
