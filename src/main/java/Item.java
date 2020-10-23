public class Item {
	private String name;
	private String description;
    private boolean broken = false;
	
	public enum ItemActions {THROW, POSSESS, SHAKE}
	private ItemActions actions [] =  new ItemActions [3];
	
	
	public Item (String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public boolean itemSupports (ItemActions a) {
		for(int i = 0; i<actions.length; i++) {
			if(actions[i] == a) {
				return true;
			}
		}
		return false;
	}
	
	public boolean addAction (ItemActions a) {
		if(hasDuplicate(a)) {
			return false;
		}
		else {
			for(int i = 0; i<actions.length; i++) {
				if(actions [i] == null) {
					actions [i] = a;
					return true;
				}
			}
			return false;
		}
	}
			
	
	public boolean hasDuplicate (ItemActions a) {
		for(int i = 0; i<actions.length; i++) {
			if(actions[i] == a) {
				return true;
			}
		}
		return false;
	}
	
	public String getName () {
		return name;
	}
	
	public String getDesc() {
		return description;
	}

	public void itemState(boolean state) {
		broken = state;
	}
	
	public boolean isBroken() {
		return broken;
	}
	
	@Override
	public String toString () {
		String temp = name+ ": " + description +"\n" +"Actions: ";
		for(int i = 0; i<actions.length; i++) {
			if(actions[i]!=null) {
				temp += actions[i]+ " ";
			}
		}
		if(broken){temp+="\n broken";}
		if(!broken){temp+="\n not broken";}
		return temp;
	}
}
	
	
		
	
	
