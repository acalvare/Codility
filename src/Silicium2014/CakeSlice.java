package Silicium2014;

public class CakeSlice implements Comparable<CakeSlice> {
	
	private int area; //area must be a long since the range for X and Y is 2-400Million, which could overflow an int if area is calculated
	private int x;
	private int y;
	private int previousX;
	private int previousY;
	
	public CakeSlice(){
		
	}

	
	public CakeSlice(int x, int y, int previousX, int previousY) {
		super();
		this.x = x;
		this.y = y;
		this.previousX = previousX;
		this.previousY = previousY;
	}


	public int getArea() {
		return area;
	}

	public void computeArea() {
		this.area = (this.x - this.previousX) * (this.y - this.previousY);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


	@Override
	public String toString() {
		return "CakeSlice [area=" + area + ", x=" + x + ", y=" + y + "]";
	}



	@Override
	public int compareTo(CakeSlice slice) {
		// TODO Auto-generated method stub
		if(this.area < slice.getArea())
			return 1;
		if(this.area > slice.getArea())
			return -1;
		return 0;
	}
	
	

}
