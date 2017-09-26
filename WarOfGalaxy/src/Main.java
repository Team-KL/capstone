
public class Main {

	public static void main(String[] args) {
		makeBeginningScreen();
	}
	
	public static void makeBeginningScreen() {
		new Screen_Beginning();
	}
	
	public static void makeGameScreen() {
		new Screen_Game().run();
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