public class Driver {
	public static void main (String args []) {
		BST <String> test = new BST<>();
		//Room r1 = new Room("Living Room", "A place in which we live");
		//Room r2 = new Room("Dining Room", "The place the family eats");
		//Room r3 = new Room("Theater", "The place we watch films");
		//Room r4 = new Room("Garden", "A place that should be beautiful, but is actually dead because Susan can't water plants");
		//Room r5 = new Room("Bedroom", "A place where magic happens");

		test.insert("This");
		System.out.println("adding 1");
		test.insert("Program");
		System.out.println("adding 2");
		test.insert("Is");
		System.out.println("adding 3");
		test.insert("The");
		System.out.println("adding 4");
		test.insert("Death");
		System.out.println("adding 5");
		test.printTree();
		System.out.println("Death " + test.search("Death"));
		System.out.println("This " + test.search("This"));
		test.printInOrder();
	}
}
