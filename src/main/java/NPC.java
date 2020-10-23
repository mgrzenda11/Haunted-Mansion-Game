import java.util.Random;

public abstract class NPC extends Character {
    int scareLevel=0;
	
	public NPC (String name, String description) {
		super(name, description);
	}
	
	@Override
        public abstract int getScare();
	
        public abstract void setScare(int fear);
	
	@Override
	public boolean react(String action) {
		Random r1 = new Random();
		if(action.equals("shake")) {
			setScare(r1.nextInt(10)+5);
		}
		
		if(action.equals("possess")) {
			setScare(r1.nextInt(15)+10);
		}
		
		if(action.equals("throw")) {
			setScare(r1.nextInt(20)+20);
		}
		
		if(scareLevel>50&&scareLevel<=100) {
			System.out.print(getName()+ " SCREAMS! and has run into the");
			return true;
		}
		
		if(scareLevel<=50){
			System.out.println(getName() +" shrieks!");
			return false;
		}
			
		if(scareLevel>100) {
			System.out.println(getName() +" SHRIEKS! and is out of the house!");
			Main.getTimeLeft().getAndAdd(30);
			Main.decrement();
			return true;
		}
		return false;
	}
	
	@Override
	public void move() {
		Room roomTemp = null;
		Random r1 = new Random();
		int randRoom = r1.nextInt(4)+1;
		if(randRoom==1){
			if(getRoomRef().getNorth()!=null) {
				roomTemp = getRoomRef().getNorth();
				NPC temp = this;
				getRoomRef().deleteCharacter(this);
				roomTemp.addCharacter(temp);
				getRoomRef().checkForBroken();
			}
			else{move();}
		}
			
		if(randRoom==2){
			if(getRoomRef().getSouth()!=null) {
				roomTemp = getRoomRef().getSouth();
				NPC temp = this;
				getRoomRef().deleteCharacter(this);
				roomTemp.addCharacter(temp);
				getRoomRef().checkForBroken();
			}
			else{move();}
		}
			
		if(randRoom==3){
			if(getRoomRef().getEast()!=null) {
				roomTemp = getRoomRef().getEast();
				NPC temp = this;
				getRoomRef().deleteCharacter(this);
				roomTemp.addCharacter(temp);
				getRoomRef().checkForBroken();
			}
			else{move();}
		}
			
		if(randRoom==4){
			if(getRoomRef().getWest()!=null) {
				roomTemp = getRoomRef().getWest();
				NPC temp = this;
				getRoomRef().deleteCharacter(this);
				roomTemp.addCharacter(temp);
				getRoomRef().checkForBroken();
			}
			else{move();}
		}
	}
}
