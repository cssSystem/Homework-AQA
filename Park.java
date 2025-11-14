package sys;

import java.util.ArrayList;
import java.util.List;

public class Park {
    private List<Park.Attraction> attractions = new ArrayList<>();

    public void setAttractions(Park.Attraction attractions) {
        this.attractions.add(attractions);
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public static class Attraction {
        private String name, time;
        private float price;

        @Override
        public String toString() {
            return "Attraction{" +
                    "name='" + name + '\'' +
                    ", time='" + time + '\'' +
                    ", price=" + price +
                    '}';
        }

        public Attraction(String name, String time, float price) {
            this.name = name;
            this.time = time;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

    }
}
