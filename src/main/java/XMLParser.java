import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

public class XMLParser extends DefaultHandler {
	//rooms
	String roomName;
	String roomDesc;
	Room r1 = null;
	ArrayList <Room> rooms = new ArrayList <>();
	BST <Room> roomTree = new BST<>();
	
	ArrayList <String> north = new ArrayList<>();
	ArrayList <String> south = new ArrayList<>();
	ArrayList <String> east = new ArrayList<>();
	ArrayList <String> west = new ArrayList<>();
	
	//items
	String itemName;
	String itemDesc;
	String actions;
	Item i;
	
	//player
	Player p1;
	
	//Characters/npcs/adults/child
	String name;
	String description;
	int numNPC = 0;
   
    @Override
    public void startDocument() throws SAXException {
		
    }

    @Override
    public void startElement(String namespaceURI,
                             String localName,
                             String qName, 
                             Attributes atts) throws SAXException {
		
		if(qName.equals("room")) {
			roomName = atts.getValue("name");
			roomDesc = atts.getValue("description");
			
			r1 = new Room(roomName, roomDesc);
			String rnorth = atts.getValue("north");
			String rsouth = atts.getValue("south");
			String reast = atts.getValue("east");
			String rwest = atts.getValue("west");
			
			north.add(rnorth);
			south.add(rsouth);
			east.add(reast);
			west.add(rwest);
		}
		
		if(qName.equals("item")) {
			itemName = atts.getValue("name");
			itemDesc = atts.getValue("description");
			
		    i = new Item(itemName, itemDesc);
			actions = atts.getValue("actions");
			int comma = actions.indexOf(",");
			if(comma==-1) {
				switch(actions) {
					case "shake": i.addAction(Item.ItemActions.SHAKE);
					break;
					
					case "possess": i.addAction(Item.ItemActions.POSSESS);
					break;
					
					case "throw": i.addAction(Item.ItemActions.THROW);
					break;
				}
			}
			else {
				String half = actions.substring(0, comma);
				String secondHalf = actions.substring(comma);
				switch(half) {
					case "shake": i.addAction(Item.ItemActions.SHAKE);
					break;
						
					case "possess": i.addAction(Item.ItemActions.POSSESS);
					break;
						
					case "throw": i.addAction(Item.ItemActions.THROW);
					break;
				}
				
				comma = secondHalf.indexOf(",");
				if(comma==-1) {
					switch(secondHalf) {
						case "shake": i.addAction(Item.ItemActions.SHAKE);
						break;
						
						case "possess": i.addAction(Item.ItemActions.POSSESS);
						break;
						
						case "throw": i.addAction(Item.ItemActions.THROW);
						break;
					}
				}
				else {
					half = secondHalf.substring(0, comma);
					String secondHalf1 = secondHalf.substring(comma+1);
					
					switch(half) {
						case "shake": i.addAction(Item.ItemActions.SHAKE);
						break;
							
						case "possess": i.addAction(Item.ItemActions.POSSESS);
						break;
							
						case "throw": i.addAction(Item.ItemActions.THROW);
						break;
					}
					
					switch(secondHalf1) {
						case "shake": i.addAction(Item.ItemActions.SHAKE);
						break;
						
						case "possess": i.addAction(Item.ItemActions.POSSESS);
						break;
						
						case "throw": i.addAction(Item.ItemActions.THROW);
						break;
					}
				}	
			}
			r1.addItem(i);
		}
		
		if(qName.equals("adult")) {
			name = atts.getValue("name");
			description = atts.getValue("description");
			Adult a1 = new Adult (name, description);
			r1.addCharacter(a1);
			numNPC++;
			
		}
		
		if(qName.equals("child")) {
			name = atts.getValue("name");
			description = atts.getValue("description");
			Child c1 = new Child (name, description);
			r1.addCharacter(c1);
			numNPC++;
		}
		
		if(qName.equals("player")) {
			name = atts.getValue("name");
			description = atts.getValue("description");
		        p1 = new Player (name, description);
			r1.addCharacter(p1);
		}
    }

    @Override
    public void endElement(String namespaceURI,
                           String localName,
                           String qName) throws SAXException {
		if(qName.equals("room")) {
			rooms.add(r1);
			roomTree.insert(r1);
			roomName = null;
			roomDesc = null;
			r1 = null;
		}
		
		if(qName.equals("item")){
			i=null;
			itemName = null;
			itemDesc = null;
		}
		
		if(qName.equals("adult")||qName.equals("child")||qName.equals("player")) {
			name = null;
			description = null;
		}
    }

    @Override
    public void endDocument() throws SAXException {
		for(int i = 0; i<rooms.size();i++) {
			if(north.get(i)!=null) {
				String newDir = north.get(i);
				for(int j = 0; j<rooms.size(); j++) {
					if(rooms.get(j).getName().equals(newDir)) {
						rooms.get(i).setNorth(rooms.get(j));
					}
				}
			}
			
			if(south.get(i)!=null) {
				String newDir1 = south.get(i);
				for(int k = 0; k<rooms.size(); k++) {
					if(rooms.get(k).getName().equals(newDir1)) {
						rooms.get(i).setSouth(rooms.get(k));
					}
				}
			}
			
			if(east.get(i)!=null) {
				String newDir2 = east.get(i);
				for(int l = 0; l<rooms.size(); l++) {
					if(rooms.get(l).getName().equals(newDir2)) {
						rooms.get(i).setEast(rooms.get(l));
					}
				}
			}
			
			if(west.get(i)!=null) {
				String newDir3 = west.get(i);
				for(int m = 0; m<rooms.size(); m++) {
					if(rooms.get(m).getName().equals(newDir3)) {
						rooms.get(i).setWest(rooms.get(m));
					}
				}
			}
		}
    }

    @Override
    public void characters(char ch[], int start, int length)
        throws SAXException {
    }
	
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	public Player getPlayer() {
		return p1;
	}
	
	public int getNPCs() {
		return numNPC;
	}
	
	public BST <Room> getTree() {
		return roomTree;
	}
	
}
