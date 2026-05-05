public class Bird implements Animal {
    private String name;
    private int age;
    private String species;
    private String color;
    private String imagePath;

    public Bird(String name, int age, String species, String color, String imagePath) {
        this.name = name;
        this.age = age;
        this.species = species;
        this.color = color;
        this.imagePath = imagePath;
    }

    
 // bird makes a tweet sound
 	@Override
 	public void makeSound() {
 		System.out.println("Tweet! Tweet!");
 	}


    @Override
	public void move() {
		System.out.println("The bird flaps its wings and flys away.\n");
	}
	

	@Override
	public String getName() {
		return name;
	}
    @Override
    //getAge()
	public int getAge() {
		return age;
	}
 // returns the species of bird
 	@Override
 	public String getSpecies() {
 		return species;
 	}
 	@Override
	public String getColor() { 
 		return color; 
 		
 	}

    
    //getImagePath()
    @Override
	public String getImagePath() {
		return imagePath;
	}
	// toString to print out the bird info
	@Override
	public String toString() {
		return "Bird [Name: " + name + ", Age: " + age + ", Species: " + species + ", Color: " + color + "]";
	}
}
