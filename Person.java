

public abstract class Person {
    private String name;
    private int age;
    private int yearsWorked;
    private String job;
    private String imagePath; 

    public Person(String name, int age, int yearsWorked, String job, String imagePath) {
        this.name = name;
        this.age = age;
        this.yearsWorked = yearsWorked;
        this.job = job;
        this.imagePath = imagePath;  // NEW
    }

    //Getter methods
    public String getName() { return name; }
    public int getAge() { return age; }
    public int getYearsWorked() { return yearsWorked; }
    public String getJob() { return job; }
    public String getImagePath() { return imagePath; }  

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Job: " + job + ", Years Worked: " + yearsWorked;
    }
}
