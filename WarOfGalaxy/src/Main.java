import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	public static int stageNum = 1;
	static int[][] g = new int[10 + 1][16];
	static String[] majorData = new String[10 + 1];
	static String[] minorData = new String[10 + 1];

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("stage.txt"));
		for(int i = 1; i <= 10; i++) {
			String[] gStr = sc.nextLine().split(" ");
			for(int j = 0; j < gStr.length; j++)
				g[i][j] = Integer.parseInt(gStr[j]);
			majorData[i] = sc.nextLine().replace("[", "").replace("]", "").replaceAll(",", "");
			minorData[i] = sc.nextLine().replace("[", "").replace("]", "").replaceAll(",", "");
			sc.nextLine();
		}
		makeBeginningScreen();
	}
	
	public static void makeBeginningScreen() {
		new Screen_Beginning();
	}
	
	public static void makeGameScreen() {
		new Screen_Game(g[stageNum], majorData[stageNum], minorData[stageNum]).run();
	}

	public static void makeNextStageScreen() {
		new Screen_NextStage();
	}

	public static void makeCongratulationScreen() {
		new Screen_Congratulation();
	}

	public static void makeGameOverScreen() {
		new Screen_GameOver();
	}
}