import java.util.LinkedList;

public class Gene {
	int index;
	int score;
	int[] g;
	
	LinkedList<Data> majorDataList;
	LinkedList<Data> minorDataList;

	public Gene(int n) {
		g = new int[n];
	}
}