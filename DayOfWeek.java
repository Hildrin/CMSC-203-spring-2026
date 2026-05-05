
/*
 * Class: CMSC203 
 * Instructor:
 * Description: DayOfWeek enum - holds each day and there discount rate for tickets
 * Due: 05/04
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming  
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: Kamel Tchantchampo
*/


//Each day has a specific discount rate associated with it.
//Weekdays have 10% discount, weekends have no discount

public enum DayOfWeek {
	MONDAY(0.10),
	TUESDAY(0.10),WEDNESDAY(0.10),THURSDAY(0.10),FRIDAY(0.10),SATURDAY(0.0),   // no discount on weekends
	SUNDAY(0.0);
    private final double discount; // instance variable

    //Constructor for the DayOfWeek enum
    DayOfWeek(double discount) {
        this.discount = discount;
    }

    //Public method provides access to the discount value associated with a specific enum constant.
    public double getDiscount() {
        return discount;
    }
}

