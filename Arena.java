/*
 * Class: CMSC203 
 * Instructor:
 * Description: Arena class - this is a building where the circus shows take place
 * Due: 05/04
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming  
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here:Kamel tchantchampo
*/

public class Arena implements Building {
	//instance variables
    private String color;
    private double length;
    private double width;
    private String buildingType;
    private String imagePath;

    //constructor
    public Arena(String color, double length, double width, String imagePath) {
        this.color = color;
        this.length = length;
        this.width = width;
        this.buildingType = "Arena";
        this.imagePath = imagePath;
    }

    @Override
    public void setSize(double length, double width) {
    	this.length = length;
		this.width = width;

    }

    @Override
	public double getLength() {
		return length;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String getColor() {
		return color;
	}

	// sets what type of building this is
	@Override
	public void setBuildingType(String type) {
		buildingType = type;
	}

	@Override
	public String getBuildingType() {
		return buildingType;
	}
    @Override
    public String getImagePath() { return imagePath; }
    
    @Override
    public String toString() {
        return String.format("\nBuilding Type: %s \nColor: %s \nSize: %.1f x %.1f\n", buildingType, color, length, width);      
    }   
}

