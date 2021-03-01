**Tutorial:**

**Instructions:**
- **play:**
   - At the start of the game the human player is forced to play a domino.
   - **Instructions:**
        - Enter "p" in the prompt to play a domino.
        - Enter a non-negative integer (0 <= index < total dominos) to play a domino. 
        - place the domino by entering "l" or "r" in the console to play in the left and right side respectively.
        - rotate the domino by entering "y" or "n" in the console to choose if you want to rotate the domino.
   - To play a domino, the domino must be playable according to domino rules.
    - it must match with either the left side of the leftmost domino or right side of the right side domino.
    - A blank domino is like a wild card that can be played at all times.
- **draw:**
    - **Instructions:**
        - Enter "d" in the prompt to draw a domino from the boneyard.
    - **Rules:**
        - You can only draw a domino if you do not have any playable piece.
        - You will draw a domino from the boneyard until a playable piece is found.
- **quit**
    - **Instructions:** 
        - Enter "q" in the prompt to quit the game.
        - Respective scores for the computer and human is diaplyed when you quit the game.
    - **quit Conditions:**
        - The game automatically quits when either of the players do not have any playable piece including the boneyard.
        - The game automatically quits when all dominos in the game has been placed on the Game Board.
    - **Scoring:**
        - Scores are allocated for each players by counting the dominos.
        - Player with the lowest score wins.
        - In case of a draw, the player with the last move wins.
