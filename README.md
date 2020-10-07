# war-game
OO implementation of 3 variations of the card game, War

Make sure Maven is installed on your computer

You can run this project from this directory by typing:
```mvn package javafx:run```

<b>Domain Models:</b>  
- War  
- Hand  
- Card  
- Deck  

<b>Implemented GRASP Patterns:</b>  
- High Cohesion:  
When designing the project structure, we started with the basics.  
For War to work, we would have to implement a Card and Hand object.  
From there, a Deck was created to help with initializing the game.  
Enums Rank and Suit were created to better define the values required  
for output and comparisons. This design seems simple, focused, and understandable.  

- Low Coupling:  
Our design minimizes the dependency between classes. The majority of the functionality  
for the game exists within our War class, with the other classes serving as defined objects  
within the environment.

- Controller:  
In the main package exists TableController.java, which responds to user input events.  
Currently, the only user input that involves a response in clicking on the beginGame button  
which simulates an entire copy of War with variation selected within the TableController.

<b>Implemented SOLID Principles:</b>

- Liskov Substitution  
The War class implements several Hand objects, which is defined simply as ArrayList<Card>.  
This means that the current design correctly implements each dedined object already through  
a series of layers.

<b>Project Bugs</b>
- Game variation 1 - Placing cards in bottom of players' hands 
    - When war starts, duplicate hands are added the winner player's deck, causing greater than 52 cards total between the two players at the end of the game
- Game variation 2 - 2 player OR 3 player 
    - Occasionally throws an "Out of bounds error"

