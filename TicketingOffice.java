public class TicketingOffice implements Building {
	//instance variables
    private String color;
    private double length;
    private double width;
    private String buildingType;
    private String imagePath;
    

    //constructor
    public TicketingOffice(String color, double length, double width, String imagePath) {
        this.color = color;
        this.length = length;
        this.width = width;
        this.buildingType = "Ticketing Office";
        this.imagePath = imagePath;
    }

    @Override
	public void setSize(double length, double width)
	{
		this.length = length;
		this.width  = width;
	}

	@Override
	public double getLength() { 
		return length; 
		}

	@Override
	public double getWidth()  { 
		return width; 
		}

	@Override
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String getColor() {
		return (color != null) ? color : "No color set";
	}

	@Override
	public void setBuildingType(String type) {
		this.buildingType = type;
	}

	@Override
	public String getBuildingType() {
		return buildingType;
	}

	@Override
	public String getImagePath() {
		return imagePath;
	}

	// displays all information about the ticketing office
	@Override
	public String toString() {
		return String.format("\nBuilding Type: %s \nColor: %s \nSize: %.1f x %.1f\n",
				buildingType, color, length, width);
	}

}

