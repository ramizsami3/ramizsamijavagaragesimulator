public class AutoParkLoadTester {
    public static void main(String[] args){

        System.out.println("Loading an auto park from a file that doesn't exist, should be null: " + AutoPark.loadFromFile("fileNotFound.txt"));

        System.out.println("\n\nLoading an auto park for file that does exist (assuming you have run AutoParkTester already)...");
        AutoPark park = AutoPark.loadFromFile("myAutoPark.txt");

        System.out.println("\n\nPrinting out all items sorted by their sold quantity");
        for(Item anItem: park.getXPopularItems(park.getItems().size() + 1)){
            System.out.println(anItem);
        }

        for(Customer c: park.getCustomers()){
            if(c.getName().equals("Miss Marblesnort")){
                System.out.println("\n\nPrinting Miss Marblesnort's purchases");
                System.out.println("Should be: 2 x AWD SUV, 5 x Truck, 6 x Sedan, 15 x Passenger tire");
                c.printPurchaseHistory();
            }
        }


    }
}
