import java.util.Iterator;

public class Room implements Comparable <Room> {
	private String name;
	private String description;
	private LinkedList <Character> bagOfPeople = new LinkedList <>();
	private int numChar = 0;
    private Room north;
    private Room south;
    private Room east;
    private Room west;
	private Item [] items = new Item [4];
	Iterator it;
        
	
	public Room (String newName, String newDesc) {
		name = newName;
		description = newDesc;
	}
	
	public boolean addCharacter(Character a) {
		a.setRoomReference(this);
		bagOfPeople.prepend(a);
		return true;
	}
	
	public boolean addItem (Item i) {
		for(int j = 0; j<items.length; j++) {
			if(items[j] == null) {
				items[j] = i; 
				return true;
			}
		}
		return false;
	}
	
	public boolean removeItem (String itemName) {
		for(int i=0; i<items.length; i++) {
			if(items[i]!=null) {
				if(items[i].getName().equals(itemName)) {
					items[i] = null;
					return true;
				}
			}
			
		}
		return false;
	}
	
	public Item getItem(String itemName) {
		for(int i=0; i<items.length; i++) {
			//System.out.println(items[i]);
			if(items[i]!=null&&items[i].getName().equals(itemName)) {
				return items[i];
			}
		}
		return null;
	}
	
	public void checkForBroken() {
		for(int i=0; i<items.length; i++) {
			if(items[i]!=null&&items[i].isBroken()) {
				removeItem(items[i].getName());
			}
		}
	}
	
	public LinkedList<Character> getChar() {
		return bagOfPeople;
	}
		
	public boolean deleteCharacter(Character c) {
		boolean temp =bagOfPeople.remove(c);
		return temp;
	}
	
	public String getName() {
		return name;
	}
	
	public Room getNorth() {
		return north;
	}
	
	public Room getSouth() {
		return south;
	}
	
	public Room getEast() {
		return east;
	}
	
	public Room getWest() {
		return west;
	}
	
	public String getDesc () {
		return description;
	}
	
	public void setNorth(Room r) {
		north = r;
	}
	
	public void setSouth(Room r) {
		south = r;
	}
	
	public void setEast(Room r) {
		east = r;
	}
	
	public void setWest(Room r) {
		west = r;
	}
	
	@Override
	public int compareTo(Room r) {
		return name.compareTo(r.getName());
	}
			
	@Override       
	public String toString () {
		String temp="ROOM: \n";
		temp +=name+ ": "+ description+"\n \n";
		temp += "CHARACTERS: \n";
		for(Character c: bagOfPeople) {
			temp+= c.toString()+"\n";
		}
		temp+="\n";
		temp+="BORDERING ROOMS: \n";
		if(north!=null){temp+="North: " +north.getName()+ ": " + north.getDesc()+"\n";}
		if(south!=null){temp+="South: " +south.getName()+ ": " + south.getDesc()+"\n";}
	    if(east!=null){temp+="East: " +east.getName()+ ": " + east.getDesc()+"\n";}
	    if(west!=null){temp+="West: " +west.getName()+ ": " + west.getDesc()+"\n";}
		temp+= "\n";
		
		
		temp+= "ITEMS:  \n";
		for(int j = 0; j<items.length; j++) {
			if(items[j]!=null) {
				temp+= items[j].toString() +"\n";
			}
		}
			
		return temp;
	}
}
			
		
	
	
	
	
	
	
