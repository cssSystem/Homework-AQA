package sys.animals;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Cat("Tom"));
        animals.add(new Cat("Kittie"));
        animals.add(new Cat("Meow"));
        animals.add(new Dog("Bob"));
        animals.add(new Dog("Bird"));

        System.out.println("Cat создано: " + Cat.getCount());
        System.out.println("Dog создано: " + Dog.getCount());
        System.out.println("Animal создано: " + Animal.getCount());

        for (Animal animal : animals) {
            animal.run(100);
            animal.sail(200);
        }

        Bowl bowl = new Bowl();
        bowl.addFood(50);

        for (Animal animal : animals) {
            if (animal instanceof Cat) {
                ((Cat) animal).eatFromBowl(bowl, 25);
                System.out.println(((Cat) animal).getSatiety());
            }
        }
    }
}