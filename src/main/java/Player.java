import java.util.Scanner;
import java.util.ArrayList;

public class Player extends Character {
	Character [] toBeDel = new Character [5];
	BST <Room> house = null;
	boolean cheatMode = false;
	
    public Player (String name, String description) {
		super(name, description);
    }

	@Override
    public String toString () {
		return super.toString();
    }
	
	@Override
	public int getScare() {
		return 0;
	}
	
	@Override
	public boolean react(String action) {
		if(action.equals("shake")) {
			System.out.println("You have shaken the item");
		}
		
		if(action.equals("possess")) {
			System.out.println("You have possessed the item");
		}
		
		if(action.equals("throw")) {
			System.out.println("You have thrown the item");
		}
		return true;
	}
	
    public boolean move(String direction) {
		Room roomTemp = null;
		if(direction.equals("north")) {
			roomTemp = getRoomRef().getNorth();
		}
		
		if(direction.equals("south")) {
			roomTemp = getRoomRef().getSouth();
		}
		
		if(direction.equals("east")) {
			roomTemp = getRoomRef().getEast();
		}
		
		if(direction.equals("west")) {
			roomTemp = getRoomRef().getWest();
		}
	
	        Player tempPlayer = this;
		getRoomRef().deleteCharacter(this);
		roomTemp.addCharacter(tempPlayer);
		return true;	
    }
	
	@Override
	public void move() {
		return;
	}
	
	public void play(Scanner s) {
		house = Main.getTree();
		System.out.println("Welcome to the game! The goal is to scare everyone out of the house before the timer runs out. Type commands below to play");
		System.out.println("The goal is to scare everyone out of the house before the timer runs out.");
		System.out.println("Type commands below to play. If you have any questions type 'help' Good Luck!");
		System.out.println("You have "+Main.getTimeLeft()+ " Seconds to scare everyone out of the house.\n\n");
		s.nextLine();
		
		System.out.print("Enter your command: ");
		String command = s.nextLine();
		
		while(!command.equals("exit")&&Main.getNumNPCs()!=0) {
			switch(command) {
				case "help": System.out.println("Command \t\t\t Description"+ "\n");
						     System.out.println("help \t\t\t\t This outputs the list of all possible commands.");
						     System.out.println("look \t\t\t\t This will display the contents of the current room that you are in.");
							 System.out.println("north, south, east, or west \t Moves you into the room that is north, south, east, or west of your current room if possible.");
							 System.out.println("shake, possess, throw \t\t These commands manipulate the items in the room in the way specified.");
							 System.out.println("exit \t\t\t\t Ends the game.");
							 if(cheatMode) {
								 System.out.println("look:all \t\t\t Shows the contents of all rooms in the house.");
								 System.out.println("look:in  \t\t\t Shows the contents of a specified room");
							 }
							 System.out.println("\n\nTime Left " +Main.getTimeLeft());
							 break;
				
				case "look": System.out.println("\n\n\n"+getRoomRef().toString());
							 System.out.println("Time Left " +Main.getTimeLeft());
							 
				break;
				
				case "north": 
					if(getRoomRef().getNorth()==null) {
						System.out.println("Unfortunately you happen to be the one ghost that can't walk through walls. Try another direction.");
					}
					else{
						boolean temp = move(command);
						if(temp) {
							System.out.println("You are now in the "+getRoomRef().getName());
						}
						else {
							System.out.println("Something has gone terribly wrong.");
						}
					}
					System.out.println("Time Left " +Main.getTimeLeft());
				break;
				
				case "south": 
					if(getRoomRef().getSouth()==null) {
						System.out.println("Unfortunately you happen to be the one ghost that can't walk through walls. Try another direction.");
					}
					else{
						boolean temp = move(command);
						if(temp) {
							System.out.println("You are now in the "+getRoomRef().getName());
						}
						else {
							System.out.println("Something has gone terribly wrong.");
						}
					}
					System.out.println("Time Left " +Main.getTimeLeft());
				break;
				
				case "east": 
					if(getRoomRef().getEast()==null) {
						System.out.println("Unfortunately you happen to be the one ghost that can't walk through walls. Try another direction.");
					}
					else{
						boolean temp = move(command);
						if(temp) {
							System.out.println("You are now in the "+getRoomRef().getName());
						}
						else {
							System.out.println("Something has gone terribly wrong");
						}
					}
					System.out.println("Time Left " +Main.getTimeLeft());
				break;
				
				case "west": 
					if(getRoomRef().getWest()==null) {
						System.out.println("Unfortunately you happen to be the one ghost that can't walk through walls. Try another direction.");
					}
					else{
						boolean temp = move(command);
						if(temp) {
							System.out.println("You are now in the "+getRoomRef().getName());
						}
						else {
							System.out.println("Something has gone terribly wrong.");
						}
					}
					System.out.println("Time Left " +Main.getTimeLeft());
				break;
				
				case "shake": System.out.print("Enter the name of the item you want to shake: "); 
							  String name = s.nextLine();
							  Item it = getRoomRef().getItem(name);
							  if(it!=null) {
								  if(it.itemSupports(Item.ItemActions.SHAKE)&&!it.isBroken()) {
									  LinkedList <Character> temp = getRoomRef().getChar();
									  boolean isGone = false;
									  Character x = null;
									  int counter = 0;
										for(Character c: temp) {
											x = c;
											isGone = c.react("shake");
											if(isGone&&c.getScare()>50) {
												toBeDel[counter] = x;
												counter++;
											}
										}
										for(int i = 0; i<toBeDel.length; i++) {
											if(toBeDel[i]!=null) {
												if(toBeDel[i].getScare()>100) {
													getRoomRef().deleteCharacter(toBeDel[i]);
													toBeDel[i] = null;
												}
												else {
													toBeDel[i].move();
													toBeDel[i] = null;
												}
											}
										}
										
								  }
								  else {
									  System.out.println("you can't perform that action on that item.");
								  }
							  }
							  else {
								  System.out.println("The item you are referencing does not appear to be in this room.");
							  }
							  System.out.println("Time Left " +Main.getTimeLeft());
							  break;
							  
				case "possess": System.out.print("Enter the name of the item you want to possess: ");
								String act = s.nextLine();
								Item it2 = getRoomRef().getItem(act);
								if(it2!=null) {
									if(it2.itemSupports(Item.ItemActions.POSSESS)&&!it2.isBroken()) {
										LinkedList <Character> temp1 = getRoomRef().getChar(); 
										boolean isGone2 = false;
										Character x1 = null;
										int counter1 = 0;
										for(Character c1: temp1) {
											x1 = c1;
											isGone2 = c1.react("possess");
											if(isGone2&&c1.getScare()>50) {
												toBeDel[counter1] = x1;
												counter1++;
											}
										}
										for(int i = 0; i<toBeDel.length; i++) {
											if(toBeDel[i]!=null) {
												if(toBeDel[i].getScare()>100) {
													getRoomRef().deleteCharacter(toBeDel[i]);
													toBeDel[i] = null;
												}
												else {
													toBeDel[i].move();
													toBeDel[i] = null;
												}
											}
										}
									}
									else {
										System.out.println("you can't perform that action on this item");
									}
								}
								else{
									System.out.println("The item you are referencing does not appear to be in this room");
								}
								System.out.println("Time Left " +Main.getTimeLeft());
								break;
				
				case "throw": System.out.print("Enter the name of the item you want to throw: ");
						      String act2 = s.nextLine();
							  Item it3 = getRoomRef().getItem(act2);
							  if(it3!=null) {
								  if(it3.itemSupports(Item.ItemActions.THROW)&&!it3.isBroken()) {
									  it3.itemState(true);
									  LinkedList <Character> temp2 = getRoomRef().getChar();
									  boolean isGone3 = false;
									  Character x2 = null;
									  int counter2 = 0;
										for(Character c2: temp2) {
											x2 = c2;
											isGone3 = c2.react("throw");
											if(isGone3&&c2.getScare()>50) {
												toBeDel[counter2] = x2;
												counter2++;
											}
										}
										for(int i = 0; i<toBeDel.length; i++) {
											if(toBeDel[i]!=null) {
												if(toBeDel[i].getScare()>100) {
													getRoomRef().deleteCharacter(toBeDel[i]);
													toBeDel[i] = null;
												}
												else {
													toBeDel[i].move();
													toBeDel[i] = null;
												}
											}
										}
								  }
								  else {
									  System.out.println("you can't perform that action on this item.");
								  }
							  }
							  else{
								  System.out.println("The item you are referencing does not appear to be in this room."); 
							  }
							  System.out.println("Time Left " +Main.getTimeLeft());
							  break;
							  
				case "cheatmode": cheatMode = true;
								    System.out.println("Ugh you noob. You are now in cheat mode");
								    break;
									
				case "nocheatmode": cheatMode = false;
									System.out.println("You have now deactivated cheat mode. Good luck! You'll need it!");
									break;
				
								  
				case "look:all": if(cheatMode) {
									System.out.println("\n\n");
									house.printInOrder();
									System.out.println("Time Left " +Main.getTimeLeft());
								 }
								 else {
									System.out.println("You can't scare people by not making sense, it doesn't work like that. Try again.");
									System.out.println("Time Left " +Main.getTimeLeft());
								 }
								 break;
	
				case "look:in": if(cheatMode) {
									System.out.println("What room do you want to look in?");
									String doThis = s.nextLine();
									Room t1 = house.get(new Room(doThis," "));
									if(t1 == null) {
										System.out.println("That room doesn't exist in this house");
									}
									else {
										System.out.println(t1);
									}
								}
								else {
									System.out.println("You can't scare people by not making sense, it doesn't work like that. Try again.");
								}
								break;
								
								
								
								
				default: System.out.println("You can't scare people by not making sense, it doesn't work like that. Try again.");
						 System.out.println("Time Left " +Main.getTimeLeft());
				break;
				
			}
			if(Main.getNumNPCs() != 0) {
				System.out.println();
				System.out.print("Enter your command: ");
				command = s.nextLine();
			}
		} 
		if(Main.getNumNPCs() == 0) {
			System.out.println("CONGRATULATIONS!!! You have won the game and are a very spooky ghost. Keep it classy!");
		}
		else {
			System.out.println("Goodbye see you next time!");
		}
		System.exit(0);
	}
}	
