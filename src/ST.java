class ITEM {
	public int k;
	
	public int key() {
		return k;
	}
}

class ST {
	public int M = 3;
	private class Node {
		int numOfRecords; // number of records
		Record[] bucket;
		int k;

		Node() {
			bucket = new Record[M];
			numOfRecords = 0;
			k = 0;
		}
	}

	private Node[] dir;
	private int global, numOfEntries;

	ST(int maxN) {
		global = 0;
		numOfEntries = 1;
		dir = new Node[numOfEntries];
		dir[0] = new Node();
	}

	private Record search(Node h, int v) {
		for (int j = 0; j < h.numOfRecords; j++)
			if (v == h.bucket[j].key)
				return h.bucket[j];
		return null;
	}

	Record search(int v) {
		return search(dir[bits(v, 0, global)], v);
	}

	private void insert(Node h, Record x) {
		int j;
		int v = x.key;
		for (j = 0; j < h.numOfRecords; j++)
			if (v < h.bucket[j].key)
				break;
		for (int i = h.numOfRecords; i > j; i--)
			h.bucket[i] = h.bucket[i - 1];
		h.bucket[j] = x;
		h.numOfRecords += 1;
		if (h.numOfRecords == M)
			split(h);
	}

	void insert(Record x) {
		insert(dir[bits(x.key, 0, global)], x);
	}

	private void insertDIR(Node t, int k) {
		int i, m;
		int v = t.bucket[0].key;
		int x = bits(v, 0, k);
		while (global < k) {
			Node[] old = dir;
			global += 1;
			numOfEntries += numOfEntries;
			dir = new Node[numOfEntries];
			for (i = 0; i < numOfEntries; i++)
				dir[i] = old[i / 2];
			for (i = 0; i < numOfEntries / 2; i++)
				old[i] = null;
			if (global < k)
				dir[bits(v, 0, global) ^ 1] = new Node();
		}
		for (m = 1; k < global; k++)
			m *= 2;
		for (i = 0; i < m; i++)
			dir[x * m + i] = t;
	}

	private void split(Node node) {
		Node temp = new Node();
		while (node.numOfRecords == 0 || node.numOfRecords == M) {
			node.numOfRecords = temp.numOfRecords = 0;
			for (int j = 0; j < M; j++)
				if (bits(node.bucket[j].key, node.k, 1) == 0)
					node.bucket[node.numOfRecords++] = node.bucket[j];
				else
					temp.bucket[temp.numOfRecords++] = node.bucket[j];
			node.k += 1;
			temp.k = node.k;
		}
		insertDIR(temp, temp.k);
	}
}
