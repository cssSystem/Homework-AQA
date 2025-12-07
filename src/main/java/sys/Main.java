package sys;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Product[] product = {
                new Product("Samsung S25 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 5599, true),
                new Product("Samsung S26 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 7599, true),
                new Product("Samsung S27 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 9599, true),
                new Product("Samsung S28 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 15599, false),
                new Product("Samsung S29 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 25599, true)
        };
        for (Product p : product) {
            p.printConsole();
        }
        Park park = new Park();
        park.setAttractions(new Park.Attraction("Колесо обозрения", "14:50", 500));
        park.setAttractions(new Park.Attraction("Паровозики", "10:30", 500));
        park.setAttractions(new Park.Attraction("Летай ракета", "17:50", 500));
        park.setAttractions(new Park.Attraction("Комната страха", "14:30", 500));
        List<Park.Attraction> attractions = park.getAttractions();

        for (Park.Attraction p : attractions) {
            System.out.println(p);
        }
    }
}