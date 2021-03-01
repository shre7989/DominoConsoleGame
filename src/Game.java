/**
 * Game represents the actual domino game, it handles how the game operates!
 * @author: Mausam Shrestha
 * @date: 02/18/2020
 * @project: 2 - Domino game
 * @version: 1
 * @UNMId: 101865530
 * @course: CS351
 */
package CS351;

import java.util.ArrayList;

public class Game {
    /* main components for the game */
    private final Player HUMAN;
    private final Player COMPUTER;
    private boolean quit = false;
    private final GameUI UI = new GameUI();
    private boolean start = true;
    private boolean invalidMove = false;

    /**
     * Game constructor that initializes the game
     */
    public Game(){
        Boneyard gameBoneYard = new Boneyard();
        gameBoneYard.init();

        GameBoard board = new GameBoard();

        HUMAN = new Player();
        HUMAN.setHands(gameBoneYard.distributeHands());

        COMPUTER = new Player();
        COMPUTER.setHands(gameBoneYard.distributeHands());

        this.play(board, gameBoneYard);
    }

    /**
     * play - Sets up a sequential play, taking turns and quitting when the game is over
     * @param board - Game board where dominos are placed
     * @param gameBoneYard - Boneyard from which dominos can be drawn
     */
    public void play(GameBoard board, Boneyard gameBoneYard){
        while(!quit){
            UI.showTray(HUMAN);
            UI.humanTurnDialogue();
            if(start) executeMove(HUMAN, "p", board,gameBoneYard,UI);
            else executeMove(HUMAN, UI.getInput(),board,gameBoneYard,UI);

            if(!quit && !invalidMove) {
                UI.computerUpdate(COMPUTER);
                UI.boneYardUpdate(gameBoneYard);
                board.show();
                UI.showTray(HUMAN);
                System.out.println("Computer's turn");
                COMPUTER.autoPlay(board, gameBoneYard);
                UI.computerUpdate(COMPUTER);
                UI.boneYardUpdate(gameBoneYard);
                board.show();
            }
            if(isOver(gameBoneYard,board)) gameOver(UI);
            invalidMove = false;
            start = false;
        }
    }

    /**
     * gameOver - called when the game is over
     * @param UI - Our GameUI that helps to print the message when the game is over
     */
    public void gameOver(GameUI UI){
        quit = true;
        UI.gameOver(HUMAN, COMPUTER);
    }

    /**
     * executeMove - Executes the given move in the game
     * @param player - player of the game
     * @param move - move the player wants to perform
     * @param board - game board where dominos are placed
     * @param boneyard - boneyard from which dominos can be drawn
     * @param UI - UI to communicate the events of the game
     */
    public void executeMove(Player player,String move, GameBoard board, Boneyard boneyard, GameUI UI){
        ArrayList<String> commands;
        ArrayList<Domino> hands;
        Domino domino;
        switch (move) {
            case "p":
                if(board.getBoard().isEmpty() || isStillPlayable(player.getHands(),board)){
                    commands = UI.getPlayerMoves(player);
                    hands = player.getHands();
                    domino = hands.get(Integer.parseInt(commands.get(0)));
                    if(!start) {
                        if(board.findMatch(domino)){
                            player.play(domino, board, commands.get(1), commands.get(2));
                        }
                        else{
                            invalidMove = true;
                            System.out.println("Play a matching domino please!!");
                            board.show();
                        }

                    }
                    else player.play(domino, board, commands.get(1), commands.get(2));
                }
                else if(!boneyard.getBones().isEmpty() && isStillPlayable(boneyard.getBones(), board)){
                    player.draw(boneyard);
                }
                else if(isOver(boneyard,board)){
                    gameOver(UI);
                }
                break;
            case "d":
                if(isStillPlayable(HUMAN.getHands(),board))  {
                    invalidMove = true;
                    System.out.println("You cannot draw if you have a playable domino!!\n");
                    board.show();
                }
                while(!isStillPlayable(HUMAN.getHands(), board)) {
                    player.draw(boneyard);
                }
                break;
            case "q":
                gameOver(UI);
                break;
            default:
                System.out.println("Enter 'p' to Play, 'd' to Draw or 'q' to Quit the game!!");
                play(board,boneyard);
                break;
        }
    }

    /**
     * isOver - checks if boneyard or player or COMPUTER have empty hands
     * @param boneyard - boneyard from which dominos can be drawn
     * @return - true if not playable, false if still playable
     */
    public boolean isOver(Boneyard boneyard, GameBoard board){
        boolean humanPlayable = isStillPlayable(HUMAN.getHands(),board);
        boolean computerPlayable = isStillPlayable(COMPUTER.getHands(),board);
        boolean boneyardEmpty = boneyard.getBones().isEmpty();
        boolean boneyardPlayable = isStillPlayable(boneyard.getBones(),board);

        if((!humanPlayable || !computerPlayable) && (boneyardEmpty || !boneyardPlayable)){
            quit = true;
            return true;
        }
        else if (boneyardEmpty && (HUMAN.getHands().isEmpty() || COMPUTER.getHands().isEmpty())){
            quit = true;
            return true;
        }
        return false;
    }
    /**
     * isStillPlayable - checks if the list of dominos is playable
     * @param list - list of dominos
     * @return - true if playable and false otherwise
     */
    public boolean isStillPlayable(ArrayList<Domino> list, GameBoard board){
        Domino first;
        Domino last;

        if(start) return true;
        else{
            first = board.getBoard().getFirst();
            last = board.getBoard().getLast();
        }
        for(Domino i: list){
            if(i.isMatchRight(last) || i.isMatchLeft(first)) return true;
        }
        return false;
    }

}
