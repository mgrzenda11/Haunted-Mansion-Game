# Haunted-Mansion-Game
Scare everyone out of the mansion before the clock runs out!

This is a text interface game written in Java. 
the goal of the game is to scare all of the people out of the house by "levitating", "shaking", or "throwing" different objects in a room.

# Design
The game is designed to flexible and configurable by letting the user input a custom made xml file to design the layout of the house, which items are in play, what rooms those items are in, and what rooms the people start in. You can also specify what actions (SHAKE, THROW, POSESS) an item has in this file. Once a file name is given, the file is parsed (assuming the file is found) and a graph is created to connect all the rooms. A sample xml file is included for reference.

This game is set up so it can be built with gradle, and comes with a rather simple gradle.build file which can edited to add extensions such as errorprone, or other plugins. 

# Controls
| Command | Description |
----------- | --------------------------------------- 
| help | This outputs the list of all possible commands. |
| look | This will display the contents of the current room that you are in. |
| north, south, east, or west | Moves you into the room that is north, south, east, or west of your current room if possible. |
| shake, possess, throw | These commands manipulate the items in the room in the way specified. |
| exit | Ends the game. |


# Game Rules
1. Each person in the house has a scare level, and once it hits 0, that person is scared out of the house. However, once the scare level reaches below 50, the person may run away from you into a different room. 

2. You have 300 seconds to complete the game. The amount of time left is displayed with every command entered.

3. Items have actions (either shake, possess, or throw) whicha are used to scare the people in the house. Throwing an item will break it, and that item can no longer be used to scare people in the room.

4. If the timer hits 0 before everyone is out of the house, you lose.
