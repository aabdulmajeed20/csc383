import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Record s1 = new Record("First", 0);
		Record s2 = new Record("Second", 1);
		Record s3 = new Record("Third", 2);
		Record s4 = new Record("Fourth", 3);
		Record s5 = new Record("Fifth", 4);
		
		Directory table = new Directory();
//		table.dir.ensureCapacity(4);
		table.dir.add("a");
		table.dir.add("b");
		table.dir.add("c");
		table.display();
		
	}
	
	public static String hash(int key) {
		String value = Integer.toBinaryString(key);
		for (int i = value.length(); i < 8; i++)
		value += "0";
		return value;
		}

}
