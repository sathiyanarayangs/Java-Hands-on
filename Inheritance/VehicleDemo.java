import java.util.ArrayList;
import java.util.List;

// Parent class
class Vehicle {
    String brand;
    int speed;

    // Constructor
    public Vehicle(String brand, int speed) {
        this.brand = brand;
        this.speed = speed;
    }

    // Method to display vehicle details
    public void displayInfo() {
        System.out.println("Brand: " + brand + ", Speed: " + speed + " km/h");
    }
}

// Car class (extends Vehicle)
class Car extends Vehicle {
    int doors;

    public Car(String brand, int speed, int doors) {
        super(brand, speed); // Call Vehicle constructor
        this.doors = doors;
    }

    @Override
    public void displayInfo() {
        System.out.println("Car - Brand: " + brand + ", Speed: " + speed + " km/h, Doors: " + doors);
    }
}

// Bike class (extends Vehicle)
class Bike extends Vehicle {
    boolean hasGear;

    public Bike(String brand, int speed, boolean hasGear) {
        super(brand, speed);
        this.hasGear = hasGear;
    }

    @Override
    public void displayInfo() {
        System.out.println("Bike - Brand: " + brand + ", Speed: " + speed + " km/h, Has Gear: " + hasGear);
    }
}

// Main class
public class VehicleDemo {
    public static void main(String[] args) {
        // Create a list to store vehicles
        List<Vehicle> vehicles = new ArrayList<>();

        // Add Car and Bike objects to the list
        vehicles.add(new Car("Toyota", 180, 4));
        vehicles.add(new Bike("Yamaha", 120, true));
        vehicles.add(new Car("Honda", 200, 2));
        vehicles.add(new Bike("Ducati", 250, true));

        // Loop through the list and display vehicle details
        for (Vehicle v : vehicles) {
            v.displayInfo();
        }
    }
}
