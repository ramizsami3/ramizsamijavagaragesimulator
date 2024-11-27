public abstract class PersonalVehicle extends Vehicle {
    private String color;

    public PersonalVehicle(String make, String model, String color, int year,
                           double price, int invQuantity){
        super(make,model,year,price,invQuantity);
        this.color = color;
    }
    public String getColor(){return color;}
}
