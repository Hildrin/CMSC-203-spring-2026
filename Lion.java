/*
 * Class: CMSC203 
 * Instructor:
 * Description: the Lion class, implements the Animal interface
 * Due: 05/04
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming  
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: Kamel Tchantchampo
*/
public class Lion implements Animal {
    private String name;
    private int age;
    private String species;
    private String color;
    private String imagePath;

    public Lion(String name, int age, String species, String color, String imagePath) {
        this.name = name;
        this.age = age;
        this.species = species;
        this.color = color;
        this.imagePath = imagePath;
    }

    @Override
	public void makeSound()
	{
		// lion roars loudly
		System.out.println("Roaaarrr!!");
	}

	@Override
	public void move()
	{
		System.out.println("Stalks and prowls around the arena.\n");
	}

	// getter methods below
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getAge() {
		return this.age;
	}

	@Override
	public String getSpecies() {
		return species;
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public String getImagePath() {
		return this.imagePath;
	}

	//print the lion details
	@Override
	public String toString() {
		String result = "Lion [Name: " + name + ", Age: " + age + 
				        ", Species: " + species + ", Color: " + color + "]";
		return result;
	}

}




