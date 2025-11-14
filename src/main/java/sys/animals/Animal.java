package sys.animals;

public class Animal {
    protected String name;
    private static int count = 0;

    public static int getCount() {
        return count;
    }

    public Animal(String name) {
        this.name = name;
        count++;
    }

    public String getName() {
        return name;
    }

    public void run(int leng) {
        System.out.println(name + " пробежал " + leng + "м.");
    }

    public void sail(int leng) {
        System.out.println(name + " проплыл " + leng + "м.");
    }

}
