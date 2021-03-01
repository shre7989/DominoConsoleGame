/**
 * GameUI is the Console UI interface for the domino game.
 * @author: Mausam Shrestha
 * @date: 02/18/2020
 * @project: 2 - Domino game
 * @version: 1
 * @UNMId: 101865530
 * @course: CS351
 */
package CS351;

import java.util.ArrayList;
import java.util.Scanner;

public class GameUI{
    /* scanner to get the input */
    private final Scanner scan = new Scanner(System.in);
    
    public GameUI(){
        this.welcomeMessage();
    }

    /**
     * welcomeMessage - prints the welcome message
     */
    public void welcomeMessage(){
        System.out.println("Dominos!");
        System.out.println("Computer has 7 Dominos");
        System.out.println("Boneyard has 14 Dominos");
        System.out.print("\n\n");
    }

    /**
     * getInput - gets the input from the console
     * @return - returns the given input
     */
    public String getInput(){
        return scan.nextLine();
    }

    /**
     * humanTurnDialogue - Prints the dialogue when a human player plays
     */
    public void humanTurnDialogue(){
        System.out.println("Human's turn");
        System.out.println("[p] Play Domino");
        System.out.println("[d] Draw from Boneyard");
        System.out.println("[q] Quit Game");
    }

    /**
     * showTray - shows the tray of the given player
     * @param player - player who plays the game
     */
    public void showTray(Player player){
        player.lookTray();
    }

    /**
     * getPlayerMoves - gets the move the player wants to perform
     * @param player - player who plays the game
     * @return - returns the list of commands given by the player
     * @throws NumberFormatException - exception when the player does not input a number for domino index
     */
    public ArrayList<String> getPlayerMoves(Player player) throws NumberFormatException{
        ArrayList<String> commands = new ArrayList<>();
        String move;
        
        System.out.println("Which Domino?");
        move = this.getInput();
        try {
            while (!(Integer.parseInt(move) < player.getHands().size())) {
                System.out.println("Please enter a valid index for Domino!!");
                System.out.println("Which Domino?");
                move = this.getInput();

            }
        } catch (NumberFormatException e){
            System.out.println("Enter a valid index number for domino!!");
            return getPlayerMoves(player);
        }
        commands.add(move);

        System.out.println("Left or Right?");
        move = this.getInput();
        while(!(move.equals("l") || move.equals("r"))){
            System.out.println("Please enter only 'l' or 'r'! ");
            System.out.println("Left or Right?");
            move = this.getInput();
        }
        commands.add(move);
        
        System.out.println("Rotate First?");
        move = this.getInput();
        while(!(move.equals("y") || move.equals("n"))){
            System.out.println("Please only enter 'y' or 'n'!");
            System.out.println("Rotate First?");
            move = this.getInput();
        }
        commands.add(move);
        return commands;
    }

    /**
     * computerUpdate - prints the number of dominos the player has
     * @param computer - computer who plays the game
     */
    public void computerUpdate(Player computer){
        System.out.printf("Computer has %d Dominoes\n", computer.getHands().size());
    }

    /**
     * boneYardUpdate - prints the number of dominos in the boneyard
     * @param boneyard - boneyard from which dominos can be drawn in the game
     */
    public void boneYardUpdate(Boneyard boneyard){
        System.out.printf("Boneyard contains %d Dominoes\n", boneyard.getBones().size());
    }

    /**
     * gameOver - Tells who won the game
     * @param human - human player who plays the game
     * @param computer - computer player who plays the game
     */
    public void gameOver(Player human, Player computer){
        System.out.println("Game Over!!!");
        System.out.println("Human Score: " + human.getScore());
        System.out.println("Computer Score: " + computer.getScore());
        if(human.getScore() <= computer.getScore()){
            System.out.printf("The winner is human with %d points\n",human.getScore());
        }
        else {
            System.out.printf("The winner is computer with %d points\n", computer.getScore());
        }
    }
}
