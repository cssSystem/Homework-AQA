package sys;

public class Product {
    private String name, productionDate, manufacturer, countryOfOrigin;
    private float price;
    private boolean customerReservationStatus;

    public Product(String name, String productionDate, String manufacturer, String countryOfOrigin, float price, boolean customerReservationStatus) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.countryOfOrigin = countryOfOrigin;
        this.customerReservationStatus = customerReservationStatus;
        this.price = price;
    }

    public void printConsole() {
        System.out.println(
                "\n" +
                        "Name: " + name + "\n" +
                        "Production Date: " + productionDate + "\n" +
                        "Manufacturer: " + manufacturer + "\n" +
                        "Country of Origin: " + countryOfOrigin + "\n" +
                        "Price: " + price + "\n" +
                        "Customer Reservation Status: " + customerReservationStatus);
    }
}
