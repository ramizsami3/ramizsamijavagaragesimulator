public class AutoParkTester {
    public static void main(String[] args){
        AutoPark park = new AutoPark("VroomVille");

        Sedan i1 = new Sedan("Hyundai", "Sonata", "Black", 2020, 35000, 10);
        Sedan i2 = new Sedan("BMW", "3 Series", "White", 2022, 42000, 10);
        park.addItem(i1);
        park.addItem(i2);

        SUV i3 = new SUV("Chevy", "Trailblazer", "Red", 2021, true, 32000, 10);
        SUV i4 = new SUV("Jeep", "Grand Cherokee", "Green", 2018, false, 21000, 10);
        park.addItem(i3);
        park.addItem(i4);

        Truck i5 = new Truck("Toyota", "Tacoma", 2019, "goods", true, 28000, 10);
        Truck i6 = new Truck("Ford", "Ranger", 2022, "equipment", false, 30000, 10);
        park.addItem(i5);
        park.addItem(i6);

        Minivan i7 = new Minivan("Ford", "Transit", 2020, "goods", true, 22000, 10); // Updated name
        Minivan i8 = new Minivan("Ram", "ProMaster", 2019, "equipment", false, 19000, 10); // Updated name
        park.addItem(i7);
        park.addItem(i8);

        Tire i9 = new Tire(10, 30, true, 390, 20);
        Tire i10 = new Tire(12, 35, false, 320, 20);
        park.addItem(i9);
        park.addItem(i10);

        Sedan i11 = new Sedan("Honda", "Accord", "White", 2019, 20000, 10);
        Sedan i12 = new Sedan("Toyota", "Camry", "Gray", 2022, 32000, 10);
        park.addItem(i11);
        park.addItem(i12);

        SUV i13 = new SUV("Ford", "Explorer", "Blue", 2020, true, 35000, 10);
        SUV i14 = new SUV("Dodge", "Journey", "Red", 2015, false, 12000, 10);
        park.addItem(i13);
        park.addItem(i14);

        Truck i15 = new Truck("RAM", "1500", 2022, "goods", true, 55000, 10);
        Truck i16 = new Truck("Nissan", "Frontier", 2016, "equipment", false, 23000, 10);
        park.addItem(i15);
        park.addItem(i16);

        Customer c1 = new Customer("S. Gnomes");
        Customer c2 = new Customer("H. Parrot");
        Customer c3 = new Customer("Miss Marblesnort");

        park.registerCustomer(c1);
        park.registerCustomer(c2);
        park.registerCustomer(new Customer("N. Giggleson"));
        park.registerCustomer(c3);
        park.registerCustomer(new Customer("L. Wifflesniff"));
        park.registerCustomer(new Customer("N. Giggleson"));
        park.registerCustomer(new Customer("A. Giggleworthy"));
        park.registerCustomer(new Customer("Dr. Whiskers"));
        park.registerCustomer(new Customer("S. Gnomes"));
        park.registerCustomer(new Customer("H. Parrot"));

        System.out.println("# customers (should be 7): " + park.getCustomers().size());

        // Testing size of list returned by item search
        System.out.println("# matched items (should be 2): " + park.searchItems("white").size());
        System.out.println("# matched items (should be 1): " + park.searchItems("honda").size());
        System.out.println("# matched items (should be 4): " + park.searchItems("suv").size());
        System.out.println("# matched items (should be 1): " + park.searchItems("red", 10000, 20000).size());
        System.out.println("# matched items (should be 2): " + park.searchItems("", -1, 500).size());
        System.out.println("# matched items (should be 3): " + park.searchItems("red", 10, -1).size());
        System.out.println("# matched items (should be 3): " + park.searchItems("co", -1, 50000).size());

        park.sellItem(i1, c1, 5);
        park.sellItem(i2, c3, 6);
        park.sellItem(i5, c1, 1);
        park.sellItem(i6, c3, 5);
        park.sellItem(i3, c3, 2);
        park.sellItem(i1, c2, 3);

        System.out.println("\n\nPrinting 3 most popular items");
        System.out.println("Should be: Hyundai, Sonata (8), BMW, 3 Series (6), Ford, Ranger (5)");
        for (Item anItem : park.getXPopularItems(3)) {
            System.out.println(anItem);
        }

        park.sellItem(i9, c3, 15);
        park.sellItem(i4, c1, 7);
        park.sellItem(i7, c2, 2);

        System.out.println("\n\nPrinting 3 most popular items");
        System.out.println("Should be: Passenger tire (15), Hyundai, Sonata (8), Jeep, Grand Cherokee (7)");
        for (Item anItem : park.getXPopularItems(3)) {
            System.out.println(anItem);
        }

        System.out.println("\n\nPrinting Miss Marblesnort's purchases");
        System.out.println("Should have 2 x AWD SUV, 5 x Truck, 6 x Sedan, 15 x Passenger tire");
        c3.printPurchaseHistory();

        // Try to sell some items that don't exist:
        Minivan mysteryVan = new Minivan("Mercedes-Benz", "Sprinter", 2021, "goods", true, 65000, 10); // Updated name
        park.sellItem(mysteryVan, c3, 0);
        System.out.println("\n\nPrinting Miss Marblesnort's purchases");
        System.out.println("Should have 2 x AWD SUV, 5 x Truck, 6 x Sedan, 15 x Passenger tire");
        c3.printPurchaseHistory();

        // Try to sell some products with unregistered users
        Customer newCustomer = new Customer("Lord Tickleton");
        park.sellItem(i10, newCustomer, 12);
        System.out.println("\n\nPrinting Lord Tickleton's purchases");
        System.out.println("Should be: Customer didn't purchase anything");
        newCustomer.printPurchaseHistory();

        System.out.println("\n\nRegistering new user and retrying previous sell steps...");
        park.registerCustomer(newCustomer);
        park.sellItem(i10, newCustomer, 12);
        System.out.println("Printing Lord Tickleton's purchases");
        System.out.println("Should be: 12 x Front Tire");
        newCustomer.printPurchaseHistory();

        System.out.println("\n\nAdding 10 more Toyota, Tacoma (item#5)");
        park.addStock(i5, 10);
        park.sellItem(i5, c1, 6);
        park.sellItem(i5, c2, 3);
        park.sellItem(i5, c2, 1);
        System.out.println("Printing 4 most popular items");
        System.out.println("Should be: Passenger tire (15), Front Tire (12), Toyota, Tacoma (11), Hyundai, Sonata (8)");
        for (Item anItem : park.getXPopularItems(4)) {
            System.out.println(anItem);
        }

        System.out.println("\n\nPrinting out all items in sorted order of their sold quantity");
        System.out.println("First four should match above, followed by others in sorted order");
        for (Item anItem : park.getXPopularItems(park.getItems().size() + 1)) {
            System.out.println(anItem);
        }

        System.out.println("\n\nSaving store to myAutoPark.txt file");
        park.saveToFile("myAutoPark.txt");
    }
}
