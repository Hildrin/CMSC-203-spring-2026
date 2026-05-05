import java.util.ArrayList;
import java.util.List;

public class Circus {
	//holding the different circus memebers
    private List<Animal> animals;
    private List<Person> persons;
    private List<Building> buildings;

    public Circus() {
        animals = new ArrayList<>();
        persons = new ArrayList<>();
        buildings = new ArrayList<>();
    }


    // Add building
 // adds a building to the list
 	public void addBuilding(Building build) {
 		buildings.add(build);
 	}

 	// returns all buildings
 	public List<Building> getBuildings() {
 		return buildings;
 	}

    // Display all buildings
 // print out all buildings to console
 	public void displayAllBuildings() {
 		System.out.println("All Buildings:");
 		System.out.println("`````````````````");
 		int check = 0;
 		while (check < buildings.size()) {
 			System.out.println(buildings.get(check).toString());
 			System.out.println("````````````````````");
 			check++;
 		}
 	}

    // Add person
 	public void addPerson(Person pers) {
		persons.add(pers);
	}
 	public List<Person> getPersons() {
		return persons;
	}
    // Display all persons
 // loops through and displays all persons
 	public void displayAllPersons() {
 		System.out.print("All Personall :\n\n");
 		System.out.println("-------------------");
 		for (Person perslist : persons) {
 			System.out.println(perslist.toString());
 			System.out.println("---------------------");
 		}
 	}

    // Add animal
 	public void addAnimal(Animal ani) {
		animals.add(ani);
	}

	public List<Animal> getAnimals() {
		return animals;
	}
    // Display all animals
	public void displayAllAnimals() {
		System.out.println("all the animals in the list");
		System.out.println("-----------------");
		for (int index = 0; index < animals.size(); index++) {
			System.out.println(animals.get(index).toString());
			System.out.println("-----------------");
		}
	}
	//--- SORTING ---

		// selection sort - sort animals by age from youngest to oldest
		public void sortAnimalsByAge() {
			int net = animals.size();
			for (int isort = 0; isort < net - 1; isort++) {
				int smallest = isort;
				for (int a = isort + 1; a < net; a++) {
					if (animals.get(a).getAge() < animals.get(smallest).getAge()) {
						smallest = a;
					}
				}
				// swap the animals
				Animal tmp = animals.get(smallest);
				animals.set(smallest, animals.get(isort));
				animals.set(isort, tmp);
			}
		}

		// selection sort animals alphabetically by name
		public void sortAnimalsByName() {
			int n = animals.size();
			for (int i = 0; i < n - 1; i++) {
				int smallest = i;
				for (int j = i + 1; j < n; j++) {
					boolean isSmaller = animals.get(j).getName().compareToIgnoreCase(
							animals.get(smallest).getName()) < 0;
					if (isSmaller)
						smallest = j;
				}
				Animal tmp = animals.get(smallest);
				animals.set(smallest, animals.get(i));
				animals.set(i, tmp);
			}
		}

		//--- SEARCH ---

		// search for a animal by its name, returns null if not found
		public Animal searchAnimalByName(String name) {
			Animal found = null;
			for (int index = 0; index < animals.size(); index++) {
				if (animals.get(index).getName().equalsIgnoreCase(name)) {
					found = animals.get(index);
					break;
				}
			}
			return found;
		}


}