/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group3;

import java.util.Scanner;

/**
 *
 * @author jean
 */
public class Main {

    /**
     * Use to test code
     */
    public static void main(String[] args) {
        
        Scanner userInput = new Scanner(System.in);

        Game newGame = new Game();
        
        newGame.setTitle();
        newGame.generatePlayDeck();

        //Default playerMoney is 100.0 
        double playerMoney = newGame.getPlayerMoney();

        //If player running out of money the game terminates
        while (playerMoney > 0) {
            
            System.out.println("You have a default money of $" + playerMoney);
            System.out.print("YBet: ");
            double playerBet = userInput.nextDouble();
            newGame.setPlayerBet(playerBet);
            newGame.getPlayerBet();

            if (playerBet > playerMoney) {
                System.out.println("You cannot bet more than you have.");
                break;
            }

            System.out.println("Dealing cards...");
            newGame.draw();

            //While loop for drawing new cards
            while (true) {
                //Display player 
                System.out.println("Your Hand:" + newGame.playerCards.toString());
                System.out.println("Your hand's value: " + newGame.playerCards.cardsValue());

                //Display dealer 
                System.out.println("Dealer Hand: " + newGame.dealerCards.getCard(0).toString() + " and [hidden]");

                //Hit or stand?
                System.out.print("Hit(1) or Stand(2)?: ");
                int response = userInput.nextInt();
                boolean endRound = false;

                newGame.hitStand(response);
                newGame.endGame(endRound, newGame.playingDeck, newGame.playerCards, newGame.dealerCards);

            }
        }

    }
}
