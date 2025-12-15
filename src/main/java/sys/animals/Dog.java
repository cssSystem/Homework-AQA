package sys.animals;

public class Dog extends Animal {
    private final int MAX_RUN = 500;
    private final int MIN_RUN = 0;
    private final int MAX_SAIL = 10;
    private final int MIN_SAIL = 0;

    private static int count = 0;

    public static int getCount() {
        return count;
    }

    public Dog(String name) {
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
        if (leng < 0) {
            System.out.println(super.getName() + " не может проплыть меньше" + MIN_SAIL + " метров");
            return;
        }
        if (leng > MAX_SAIL) {
            System.out.println(super.getName() + " не может проплыть более " + MAX_SAIL + " метров");
            return;
        }
        super.sail(leng);
    }
}
