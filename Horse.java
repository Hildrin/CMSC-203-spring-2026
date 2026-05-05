

public class Horse implements Animal {
    private String name;
    private int age;
    private String species;
    private String color;
    private String imagePath;

    public Horse(String name, int age, String species, String color, String imagePath) {
        this.name = name;
        this.age = age;
        this.species = species;
        this.color = color;
        this.imagePath = imagePath;
    }

    @Override
    public void makeSound() {
        System.out.println("Neigh!");
    }

    @Override
    public void move() {
        System.out.println("Gallops.\n");
    }

    // ===== GETTERS =====
    @Override
    public String getName() { return name; }

    @Override
    public int getAge() { return age; }

    @Override
    public String getSpecies() { return species; }

    @Override
    public String getColor() { return color; }

    @Override
    public String getImagePath() { return imagePath; }

    @Override
    public String toString() {
        return String.format("Horse [Name: %s, Age: %d, Species: %s, Color: %s]", name, age, species, color);
    }
}
