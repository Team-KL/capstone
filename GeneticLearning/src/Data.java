import java.awt.Point;

class Data {
	Point aim;
	int speed;
	
	public Data(Point aim, int speed) {
		this.aim = aim;
		this.speed = speed;
	}
	
	public String toString(){
		return "" + aim.x + " " + aim.y + " " + speed;
	}
}
