public class Minivan extends CommercialVehicle {
    private boolean covered;

    public Minivan(String make, String model, int year,
                   String carries, boolean covered, double price, int invQuantity) {
        super(make, model, year, carries, price, invQuantity);
        this.covered = covered;
    }

    public String toString(){
        String result = "";
        if(covered) result += "Covered ";
        return result +=  "Minivan: " + getYear() + " " + getMake() + ", " + getModel() + ", carries " + getCarries() +
                ", price $" + String.format("%,.2f",getPrice()) + " each (" +getInvQuantity()+ " in stock, " + getSoldQuantity() + " sold).";
    }


}
