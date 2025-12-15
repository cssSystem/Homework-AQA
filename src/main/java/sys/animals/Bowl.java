package sys.animals;

public class Bowl {
    private int food = 0;

    public void addFood(int food) {
        this.food += food;
    }

    public boolean takeFood(int food) {
        if (this.food - food < 0) {
            return false;
        }
        this.food -= food;
        return true;
    }
}
