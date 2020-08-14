/*
Logic of BlackJack
*/
package group3;

/**
 *
 * @author hoantran <Jean>
 */
public class Game {

    GroupOfCards playingDeck = new GroupOfCards();
    GroupOfCards playerCards = new GroupOfCards();
    GroupOfCards dealerCards = new GroupOfCards();
    
    private double playerMoney = 100.0;
    private double playerBet = 0.0;

    public Game() {

    }
    
    /*
    Set welcome/title message
    */
    public String setTitle() {
        return ("Welcome to BlackJack!");
    }

    public Game(GroupOfCards playingDeck, GroupOfCards playerCards, GroupOfCards dealerCards,
            double playerMoney, double playerBet) {
        this.dealerCards = dealerCards;
        this.playerCards = dealerCards;
        this.playingDeck = playingDeck;
        this.playerMoney = playerMoney;
        this.playerBet = playerBet;
    }

    public double getPlayerMoney() {
        return playerMoney;
    }

    public double getPlayerBet() {
        return playerBet;
    }

    /*
    Throew IllegalArgumentException if player tries to bet more than what they have
    */
    public void setPlayerBet(double playerBet) {
        if (playerBet > playerMoney) {
            throw new IllegalArgumentException("You can't bet more than you have");
        } else {
        this.playerBet = playerBet;
        }
    }
    

    public GroupOfCards getPlayingDeck() {
        return playingDeck;
    }

    public GroupOfCards getPlayerCards() {
        return playerCards;
    }

    public GroupOfCards getDealerCards() {
        return dealerCards;
    }

    public void generatePlayDeck() {
        playingDeck.createFullDeck();
        playingDeck.shuffle();

    }
    
    /*
    At the beginning of each game
    Player gets: 2 cards
    Dealer gets: 2 cards
    */
    public void draw() {
        playerCards.draw(playingDeck);
        playerCards.draw(playingDeck);

        dealerCards.draw(playingDeck);
        dealerCards.draw(playingDeck);
    }

    
    /*
    Conditions when user chose 
    (1) Hit
    (2) Stand
    */
    public boolean hitStand(int response) {
        boolean endRound = true;
        // Hit if response = 1
        if (response == 1) {
            playerCards.draw(playingDeck);
            System.out.println("You draw a:" + playerCards.getCard(playerCards.deckSize() - 1).toString());
            
            //Bust if they go over 21 when hitting
            if (playerCards.cardsValue() > 21) {
                System.out.println("Bust. Currently valued at: " + playerCards.cardsValue());
                endRound = true;
            }
            
         //Stand if response = 2
        } else if (response == 2) {
            //Show Dealer card
            System.out.println("Dealer Cards:" + dealerCards.toString());
            
            //Check if Dealer's value is > Players's
            if ((dealerCards.cardsValue() > playerCards.cardsValue()) && endRound == false) {
                System.out.println("Dealer beats you " + dealerCards.cardsValue() + " to " + playerCards.cardsValue());
                endRound = true;
            }

            //Display value of dealer
            System.out.println("Dealers hand value: " + dealerCards.cardsValue());
        }
        return endRound;
    }

    /*
    End game conditions
    */
    public void endGame(boolean endRound, GroupOfCards playingDeck, GroupOfCards playerCards, GroupOfCards dealerCards) {
        
        //Player's value > 21 = WIN
        if ((dealerCards.cardsValue() > 21) && endRound == false) {
            System.out.println("Dealer Busts. You win!");
            playerMoney += playerBet;
            endRound = true;
            
        } else if ((playerCards.cardsValue() > dealerCards.cardsValue()) && endRound == false) {
            System.out.println("You win the hand.");
            playerMoney += playerBet;
            endRound = true;
            
        //Player's value > Dealer's value
        } else if ((dealerCards.cardsValue() == playerCards.cardsValue()) && endRound == false) {
            System.out.println("Push.");
            endRound = true;
            
        //Dealer Wins
        } else if (endRound == false){
            System.out.println("Dealer wins.");
            playerMoney -= playerBet;
        }

        /*
        End game, return everything back
        */
        playerCards.moveAllToDeck(playingDeck);
        dealerCards.moveAllToDeck(playingDeck);
        System.out.println("End of Hand.");
        System.out.println ("Game over!");
    }

    
}
