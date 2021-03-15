/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forsale;

import java.util.*;

/**
 * Team name: Squatters
 * @author MichaelAlbert
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // A null strategy - never bid, always play your top card.
        Strategy s = new Strategy() {

                @Override
                public int bid(PlayerRecord p, AuctionState a) {
                    return -1;
                }

                @Override
                public Card chooseCard(PlayerRecord p, SaleState s) {
                    return p.getCards().get(0);
                }
            
            };
        
        // A random strategy - make a random bid up to your amount remaining,
        // choose a rand card to sell.
        Strategy bF = new Strategy() {

                @Override
                public int bid(PlayerRecord p, AuctionState a) {
                    return (int) (1 + (Math.random()*5));
                }

                @Override
                public Card chooseCard(PlayerRecord p, SaleState s) {
                    return p.getCards().get((int) (Math.random()*p.getCards().size()));
                }
            
            };
        

        Strategy bidFlopM = new Strategy(){
          
                @Override
                public int bid(PlayerRecord p, AuctionState a){
                    ArrayList<Card> cards = a.getCardsInAuction();
                    int highC = 0;
                    int lowC = 31;
                    int diff;
                    int highCIndex = 0;
                    int lowCIndex = 0;
                    for (int i = 0; i < cards.size(); i++){
                        if (cards.get(i).getQuality() > highC){
                            highCIndex = i;
                            highC = cards.get(i).getQuality();
                        }
                        if (cards.get(i).getQuality() < lowC){
                            lowCIndex = i;
                            lowC = cards.get(i).getQuality();
                        }
                    }
                    diff = highC - lowC;
                    //current bid
                    int cB = a.getCurrentBid();
                    if (cB <= 3){
                        if (diff >= 22 && lowC < 5){
                            return 4;
                        }else if (cB == 0 && p.getCash() >= 2){
                            return 2;
                        }else if(cB == 0){
                            return 1;
                        }else{
                            return cB +1;
                        }
                    }else{
                        return 0;
                    }
                }
          
               @Override
                public Card chooseCard(PlayerRecord p, SaleState s){
                    ArrayList<Card> cards = p.getCards();
                    ArrayList<Integer> cheque = s.getChequesAvailable();
                    Card playCard = null;
                    int lowCheques = 0; // cheques below 6
                    int midCheques = 0; // cheques between 6 - 10
                    int highCheques = 0; // cheques above 10
                    Collections.sort(cards);
                    // low = return lowest values card
                    // mid = return middle card
                    // high = return highest valued Card
                    // a+1 = return Card between mid and High
                    // a-1 = return Card between low and mid
                    String strat = ""; //used in Switch
                    
                    int averageChequeValue = 0;
                    int count = 0;
                    
                    // Goes through all avaiables cheques and determine how many
                    //low cheques are there, how many mid cheques and high valued
                    // cheques. And stores then in the variables above.
                    for(int i = 0; i < cheque.size(); i++){
                        if(cheque.get(i) < 6) lowCheques++;
                        if(cheque.get(i) > 5 && cheque.get(i) < 11) midCheques++;
                        if(cheque.get(i) > 10) highCheques++;
                        averageChequeValue += cheque.get(i);
                        count++;
                    }
                    averageChequeValue /= count; // calculates the average of the cheques
                    
                    //check how many Low values cheques there.
                    if(strat == ""){
                        if(lowCheques > 3) strat = "high";
                        else if(lowCheques > 2)strat = "a+1";
                        else if(lowCheques > 0 && lowCheques < 2) strat = "mid";
                        else if(lowCheques == 0) strat = "";
                    }
                    // if playCard is still empty.
                    // check how many Mid values cheques there.
                    if(strat == ""){
                        if(midCheques > 4) strat = "a-1";
                        else if(midCheques > 2 && midCheques < 5) strat = "mid";
                        else if(midCheques > 0 && midCheques < 3) strat = "a+1";
                        else if(lowCheques == 0) strat = "";
                    }
                    // if playCard is still empty.
                    // check how many High values cheques there.
                    if(strat == ""){
                        if(highCheques > 4) strat = "low";
                        else if(highCheques > 2 && midCheques < 5) strat = "low";
                        else if(highCheques > 0 && midCheques < 3) strat = "a-1";
                        else if(highCheques == 0) strat = "";
                    }
                    if(strat == ""){
                        if(averageChequeValue > 12) strat = "low";
                        else if(averageChequeValue > 8 && averageChequeValue < 13) strat = "a-1";
                        else if(averageChequeValue > 5 && averageChequeValue < 9) strat = "mid";
                        else if(averageChequeValue > 3 && averageChequeValue < 6) strat = "a+1";
                        else if(averageChequeValue >= 0 && averageChequeValue < 4) strat = "high";
                    }
                    
                    switch(strat){
                        case "low":
                            playCard = cards.get(0);
                            break;
                        case "mid":
                            if(cards.size() == 6 ) playCard = cards.get(3);
                            if(cards.size() == 5 ) playCard = cards.get(2);
                            if(cards.size() == 4 ) playCard = cards.get(2);
                            if(cards.size() == 3 ) playCard = cards.get(1);
                            if(cards.size() == 2 ) playCard = cards.get(1);
                            if(cards.size() == 1 ) playCard = cards.get(0);
                            break;
                        case "high":
                            playCard= cards.get(cards.size()-1);
                            break;
                        case "a+1":
                            if(cards.size() == 6 ) playCard = cards.get(3);
                            if(cards.size() == 5 ) playCard = cards.get(3);
                            if(cards.size() == 4 ) playCard = cards.get(2);
                            if(cards.size() == 3 ) playCard = cards.get(2);
                            if(cards.size() == 2 ) playCard = cards.get(1);
                            if(cards.size() == 1 ) playCard = cards.get(0);
                            break;
                        case "a-1":
                            if(cards.size() == 6 ) playCard = cards.get(2);
                            if(cards.size() == 5 ) playCard = cards.get(2);
                            if(cards.size() == 4 ) playCard = cards.get(1);
                            if(cards.size() == 3 ) playCard = cards.get(1);
                            if(cards.size() == 2 ) playCard = cards.get(0);
                            if(cards.size() == 1 ) playCard = cards.get(0);
                            break;
                    }
                    
                    return playCard;
                }
            };

        
        ArrayList<Player> players = new ArrayList<Player>();

        players.add(new Player("NA", bidFlopM));
        players.add(new Player("RA", bF));
        players.add(new Player("BF", s));
        players.add(new Player("BF", bF));
        players.add(new Player("SH", s));
        players.add(new Player("JW", bF));
        GameManager g = new GameManager(players);
        g.run();
        System.out.println(g.getLog());
    }

}
