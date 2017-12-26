//import ST.Node;

public class Bucket {

	public Record records[];
	int Local;
	public int numOfRecords;
	public int size;
	
	public Bucket(int size, int l) {
		this.size = size;
		records = new Record[size];
		Local = l;
		numOfRecords = 0;
	}
	
	private void insertToBucket(Record x) {
		int j;
		int temp = x.key;
		for (j = 0; j < numOfRecords; j++)
			if (temp < records[j].key)
				break;
		for (int i = numOfRecords; i > j; i--)
			records[i] = records[i - 1];
		records[j] = x;
		numOfRecords += 1;
//		if (numOfRecords == size)
//			split(this);
	}
	
}
