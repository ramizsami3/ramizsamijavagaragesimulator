public class Sedan extends PersonalVehicle {
    public Sedan(String make, String model, String color, int year, double price, int invQuantity){
        super(make,model,color,year,price,invQuantity);
    }
    public String toString(){
        String result = "";
        return result += "Sedan: " + getYear() + " " + getMake() + ", " + getModel() + " (" + getColor() +
                "), price $" + String.format("%,.2f",getPrice()) + " each (" +getInvQuantity()+
                " in stock, " + getSoldQuantity() + " sold).";
    }
}
