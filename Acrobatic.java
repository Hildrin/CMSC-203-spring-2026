/*
 * Class: CMSC203 
 * Instructor:
 * Description: Acrobatic class extends Person and repesents a acrobat performer in the circus
 * Due: 05/04
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming  
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: kamel tchantchampo
*/

public class Acrobatic extends Person {

	/*
	 * constructor - passes everything up to person
	 */
	public Acrobatic(String name, int age, int yearsWorked, String job, String imagePath)
	{
		super(name, age, yearsWorked, job, imagePath);
	}

	@Override
	public String toString()
	{
		return "Acrobatic " + super.toString();
	}

}