/*
 * Class: CMSC203 
 * Instructor:
 * Description: Clerk class - extends person, represents a clerk worker at the circus
 * Due: 05/04
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming  
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: Kamel Tchantchampo
*/

public class Clerk extends Person {

	// calls the parent constructor with all fields
	public Clerk(String name, int age, int yearsWorked, String job, String imagePath) {
		super(name, age, yearsWorked, job, imagePath);
	}

	// override toString to show clerk info
	@Override
	public String toString() {
		// adds "Clerk: "before the person toString
		return "Clerk:  " + super.toString();
	}

}