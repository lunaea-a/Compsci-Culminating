//name: Lucia Gong
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //initializing array lists
        ArrayList<String> categories = new ArrayList<>();
        ArrayList<String> serviceNames = new ArrayList<>();
        ArrayList<Double> hourlyRate = new ArrayList<>();
        //cart array lists
        ArrayList<String> cartCategory = new ArrayList<>();
        ArrayList<String> cartServiceNames = new ArrayList<>();
        ArrayList<Double> cartHourlyRate = new ArrayList<>();
        //boolean for singular service availability
        ArrayList<Boolean> serviceAvailability = new ArrayList<>();

        //index 0 preset category
        categories.add("cleaning"); serviceNames.add("Bob's cleaning service"); hourlyRate.add(18.7); serviceAvailability.add(false);
        //index 1 preset category
        categories.add("catering"); serviceNames.add("John's catering"); hourlyRate.add(20.3); serviceAvailability.add(false);

        //for 2b categories (if category matches input from user, found = true
        boolean found = false;

        //creating boolean for the loop (functions same as addService boolean)
        boolean bookService = true;

        //first text user will see
        System.out.println("Welcome to your local service marketplace.");
        int option1 = 0;

        //while loop for the menu so it always goes back to this screen after each selection
        while (option1 != 5) {
                                                                                   //a               //b               //c             //d
            System.out.println("Enter the corresponding number for the option:\n1. Add a service\n2. Book a service\n3. Update cart\n4. Checkout\n5. Exit\n");
            option1 = sc.nextInt();
            sc.nextLine();
            //invalid option
            while (option1 < 1 || option1 > 5) {
                System.out.println("Invalid option, enter again.");
                option1 = sc.nextInt();
            }

            //1a: add service
            if (option1 == 1) {
                boolean addService = true;
                while (addService) {
                    //2a: asking for details
                    System.out.println("*Option 1 - Adding a service.*\nEnter your service name:");
                    serviceNames.add(sc.nextLine());
                    System.out.println("Enter the category your service is from:");
                    categories.add(sc.nextLine().toLowerCase());
                    System.out.println("Enter the hourly rate:");
                    hourlyRate.add(sc.nextDouble()); sc.nextLine();
                    System.out.println("Service added successfully.\nWould you like to add another service? (yes/no)");
                    String anotherService = sc.nextLine().toLowerCase();
                    serviceAvailability.add(false);
                    //if anotherService doesn't equal yes, exit loop
                    if (!anotherService.equals("yes")) {
                        addService = false;
                    }
                }

                //1b: book service
            } else if (option1 == 2) {
                //2b: displaying categories
                System.out.println("*Option 2 - Booking a service.*\nAvailable categories:");
                //index for services under category
                int serviceIndex = 0;
                ArrayList<String> noRepeatCategories = new ArrayList<>();
                //make sure no categories repeat only for use in printing
                for (String cat : categories) {
                    if (!noRepeatCategories.contains(cat.toLowerCase())) {
                       noRepeatCategories.add(cat.toLowerCase());
                    }
                }

                while(bookService) {
                    //3b: display services
                    for (String cat : noRepeatCategories) {
                        System.out.println("-" + cat);
                    }
                    System.out.println("Enter the category that you want: (ex. cleaning, catering)");
                    String categoryInput = sc.nextLine().toLowerCase();

                    //get category and index for services
                    for (int i = 0; i < categories.size(); i++) {
                        if (categoryInput.equals(categories.get(i))) {
                            found = true;
                            serviceIndex = i;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Invalid input.");
                    }

                    for (int i = 0; i <categories.size(); i++) {
                        String availability;
                        if (!serviceAvailability.get(i)) {
                            availability = "(Available)";
                        } else {
                            availability = "(Unavailable)";
                        }
                        if(found){
                            System.out.println((i + 1) + ": " + serviceNames.get(i) + "\n $" + hourlyRate.get(i) + "/hr" + (availability));
                        }
                    }

                    System.out.println("Enter the corresponding number for the service you want:");
                    int serviceInput = sc.nextInt(); sc.nextLine();
                    //handling out of index input
                    for(int i = 0; i<serviceNames.size(); i++) {
                        if (serviceInput > categories.size()) {
                            System.out.println("Invalid input. Try again.\n");
                            break;

                            //service fully booked
                        } else if (serviceAvailability.get(serviceIndex)) {
                            System.out.println("This service is fully booked, sorry.");
                        } else {
                            // add all values into the cart array lists
                            cartCategory.add(categoryInput);
                            int serviceIndex2 = serviceInput - 1;
                            cartServiceNames.add(serviceNames.get(serviceIndex2));
                            cartHourlyRate.add(hourlyRate.get(serviceIndex2));
                            System.out.println("Successfully added to cart.\nCart:\n" + cartCategory.get(serviceIndex) + " - " + cartServiceNames.get(serviceIndex) + "\n Hourly rate: $" + cartHourlyRate.get(serviceIndex) + "/hr\n");
                            serviceAvailability.set(serviceIndex, true);
                            break;
                        }
                    }
                    System.out.println("Would you like to book another service? (yes/no). You will not be able to book again.");
                    String anotherBook = sc.nextLine();
                    if(!anotherBook.equals("yes")){
                        bookService = false;
                    }else{
                        System.out.println("Available categories:");
                    }
                }

                //1c: update cart
            }else if (option1 == 3){
                System.out.println("*Option 3: Update cart.* \nDo you want to change or remove a service? Enter the corresponding number.\n1. Change\n2. Remove");
                int cartOption = sc.nextInt(); sc.nextLine();
                //change service in cart
                if (cartOption == 1) {
                    System.out.println("*Option 1: Change service.*\nEnter the number of the service you want to remove:");
                    int removeIndex = sc.nextInt() - 1; sc.nextLine();
                    if(removeIndex >= 0 && removeIndex < cartServiceNames.size()){
                        cartServiceNames.remove(removeIndex);
                        serviceAvailability.set(removeIndex, false);
                        System.out.println("Please rebook a service from the main menu.");
                        bookService = true;
                    }else{
                        System.out.println("Invalid index.");
                    }

                    //remove service in cart
                } else if (cartOption == 2) {
                    System.out.println("*Option 2: Remove service.*\nEnter the number of the service you want to remove:");
                    int removeIndex = sc.nextInt() - 1; sc.nextLine();
                    if(removeIndex >= 0 && removeIndex < cartServiceNames.size()){
                        cartServiceNames.remove(removeIndex);
                        serviceAvailability.set(removeIndex, false);
                    }else{
                        System.out.println("Invalid index.");
                    }
                    System.out.println("Service has been removed.");

                } else {
                    System.out.println("Invalid input. Enter again.");
                }


                //1d: checkout
            }else if(option1 == 4){
                double total = 0;
                System.out.println("*Option 4: Checkout.*\n Are you sure you want to check out? (yes/no)");
                String checkoutOption = sc.nextLine().toLowerCase();
                if(checkoutOption.equals("yes")){
                    for(int i = 0; i < cartServiceNames.size(); i++){
                        System.out.println(cartServiceNames.get(i) + " - $" +cartHourlyRate.get(i));
                        total += cartHourlyRate.get(i);
                    }
                    System.out.println("Total: $" +total+ "\nBooked successfully.\n");
                //don't check out
                }else if(checkoutOption.equals("no")){
                    System.out.println("Returning to main menu.\n");
                //invalid input
                }else{
                    System.out.println("Invalid input. Enter again.");
                }
            }

        }
    System.out.println("Thank you for visiting our marketplace.");
    sc.close();
    }
}
