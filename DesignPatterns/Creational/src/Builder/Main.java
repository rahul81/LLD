package Builder;

public class Main {

    // Use Builder to create objects with different params

    Car.CarBuilder builder = new Car.CarBuilder();
    Car car1 = builder.setEngine("V6")
                    .setColor("Red")
                    .setSunroof(true)
                    .setNavigationSystem(true)
                    .build();

    Car car2 = builder.setColor("Black")
                .setEngine("V8")
                .setNavigationSystem(true)
                .build();

}
