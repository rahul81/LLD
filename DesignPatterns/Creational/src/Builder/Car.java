package Builder;

public class Car {

    private String engine;
    private int wheels;
    private int seats;
    private String color;
    private boolean sunroof;
    private boolean navigationSystem;

    private Car(CarBuilder builder) {
        this.engine = builder.engine;
        this.seats = builder.seats;
        this.wheels = builder.wheels;
        this.color = builder.color;
        this.sunroof = builder.sunroof;
        this.navigationSystem = builder.navigationSystem;
    }

    public String getEngine() {
        return this.engine;
    };

    public int getWheels() {
        return this.wheels;
    }

    public int getSeats() {
        return this.seats;
    }

    public String getColor() {
        return this.color;
    }

    public boolean getSunroof() {
        return this.sunroof;
    }

    public boolean getNavigationSystem() {
        return this.navigationSystem;
    }

    // Provide named methods for setting attributes instead of constructor overloading with params.
    // with new increasing params it becomes difficult to maintain different constructors and also can cause issue while creating objects.
    // builder pattern solves this problem.

    static class CarBuilder {
        private String engine;
        private int wheels = 4;
        private int seats = 5;
        private String color;
        private boolean sunroof = false;
        private boolean navigationSystem = false;

        public CarBuilder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public CarBuilder setWheels(int wheels) {
            this.wheels = wheels;
            return this;
        }

        public CarBuilder setSeats(int seats) {
            this.seats = seats;
            return this;
        }

        public CarBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public CarBuilder setSunroof(boolean sunroof) {
            this.sunroof = sunroof;
            return this;
        }

        public CarBuilder setNavigationSystem(boolean navigationSystem) {
            this.navigationSystem = navigationSystem;
            return this;
        }

        public Car build() {
            return new Car(this);
        }

    }
}
