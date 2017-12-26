import java.util.*;

public class Directory {
	public ArrayList<String> dir;
	public int global;
	
	public Directory() {
		dir = new ArrayList<>(2);
		dir.add("0");
		dir.add("1");
		global = 1;
	}
	
//	public void checkKey(Record r) {
//		for(int i = 0; i<table.size(); i++) {
//			if(table.get(i) == )
//		}
//	}
	
	public void increaseDir() {
		dir.add("");
		int capacity = dir.size();
		global++;
		dir.clear();
		for(int i = 0; i < capacity; i++) {
			dir.add(String.format(("%" + global + "s"), Integer.toBinaryString(i)).replace(' ', '0'));
		}
	}
	
	public void insertRecord(Record r) {
		
	}
	
	public void display() {
		for(int i = 0; i < dir.size(); i++) {
			System.out.println(dir.get(i));
		}
	}

}
