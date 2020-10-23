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
import java.util.Scanner;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;



public class Main { 
	private static Timer timer;
	private static AtomicInteger timeLeft;
	private static BST <Room> temp = null;
	private static int numNPC = 0;
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		XMLParser xp = new XMLParser();
		System.out.print("Please enter the XML file name: ");
		String file = sc.next();
		
		SAXParserFactory spf = SAXParserFactory.newInstance();
        try {
            InputStream xmlInput  = new FileInputStream(file);
            SAXParser saxParser = spf.newSAXParser();
            saxParser.parse(xmlInput, xp);
        }
        catch(SAXException|ParserConfigurationException|IOException e){
            e.printStackTrace();
			System.exit(0);
        }
		
		initTimer(300);
		Player p1 = xp.getPlayer();
	    numNPC = xp.getNPCs();
		temp = xp.getTree();
		p1.play(sc);
	}	
	
	
	public static void initTimer(int secs) {
		timeLeft = new AtomicInteger(secs);
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				int tl = timeLeft.decrementAndGet();
				if (tl == 0) {
					System.out.println("You Lost! Gotta be quicker than that!");
					System.exit(0);
				}
			}
		};

		timer = new Timer();
		timer.schedule(task, 0, 1000);
	}
	
	public static AtomicInteger getTimeLeft() {
		return timeLeft;
	}
	
	public static int getNumNPCs () {
		return numNPC;
	}
	
	public static void decrement() {
		numNPC--;
	}
	
	public static BST <Room> getTree() {
		return temp;
	}
}
