/* Rectangle class */
// package rectangle;

public class Rectangle implements Comparable<Rectangle>{

	/* Private instance variables! Keeps them from being accessed explicitly */
	private int length;
	private int width;
	private int perimeter;

	/* Constructor method */
	public Rectangle(int length, int width){
		/* "this" is used if we are using duplicate variable names */
		this.length = length;
		this.width = width;
		perimeter = 2*width + 2*length;
	}

	/* Accessor method (How private instance variables are accessed) */
	public int getLength(){
		return length;
	}

	public int getWidth(){
		return width;
	}

	/* Override methods for Comparable and String classes */
	public int compareTo(Rectangle x){
		return perimeter - x.perimeter;
	}

	public String toString(){
		return "Rectangle Perimeters: Length " + length + " Width " + width 
				+ " Perimeter " + perimeter;
	}
}