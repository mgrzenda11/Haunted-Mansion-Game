import java.util.Random;

public class Adult extends NPC  {
	
	public Adult (String newName, String newDesc){
		super(newName, newDesc);	
	}
	
	@Override
	public int getScare() {
		return scareLevel;
	}
	
	@Override
	public void setScare(int fear) {
		scareLevel +=fear;
	}
	
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
			System.out.println(getName()+ " SCREAMS! and has run into another room");
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
    public String toString() {
		return super.toString()+"\n Scare level: " +scareLevel;
	}
}
