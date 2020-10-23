public abstract class Character {
	private String name;
	private String description;
	private Room roomReference;
	
	public Character (String newName, String newDesc) {
		name = newName;
		description = newDesc;
	}
	
	public void setRoomReference (Room newRoom) {
		roomReference = newRoom;
	}
		
	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return description;
	}
	
	
	public Room getRoomRef() {
		return roomReference;
	}
	
	public abstract boolean react(String action);
	
	public abstract int getScare();
	
	public abstract void move();
     
	@Override
	public String toString() {
		return name +": " +description+" Currently located in: " +roomReference.getName();
	}
}
