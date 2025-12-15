package sys.animals;

public class Cat extends Animal {
    private final int MAX_RUN = 200;
    private final int MIN_RUN = 0;
    private static int count = 0;

    public boolean getSatiety() {
        return satiety;
    }

    private boolean satiety = false; // Сытость

    public static int getCount() {
        return count;
    }

    public Cat(String name) {
        super(name);
        count++;
    }

    @Override
    public void run(int leng) {
        if (leng < 0) {
            System.out.println(name + " не может пробежать меньше" + MIN_RUN + " метров");
            return;
        }
        if (leng > MAX_RUN) {
            System.out.println(name + " не может пробежать более " + MAX_RUN + " метров");
            return;
        }
        super.run(leng);
    }

    @Override
    public void sail(int leng) {
        System.out.println(super.getName() + " не может плавать");
    }

    public void eatFromBowl(Bowl bowl, int amountFood) {
        satiety = bowl.takeFood(amountFood);
    }
}
