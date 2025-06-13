# Restaurant-Management-Java-Project-

Restaurant Management System is a java application. This system is developed to
automate day to day activity of a restaurant. Restaurant is a kind of business that
serves people all over world with ready-made food. This system is developed to
provide service facility to restaurant and also to the customer.
 This restaurant management system can be used by the employees and
managers in the restaurants to handle customer orders. The restaurant menu is
organized in a sorted manner if needed, this helps the customer to buy accordingly.
Main objective of this system is to provide food and the process of making purchase
in simplest way with all the details being saved. Ordering will become easier and
systematic.

## Data Structures:
The data structures used are Array List to store orders placed and files concept is used
to retrieve required data like menu items, orders placed in a day, manager and
employee login credentials. Comparator classes are used to sort data according to
items according to price and employee salary.
## Application:
Our Application is helpful to make customers ordering easier and restaurants can have
hassle free process 

## Description:
1. Firstly, the person has to either login as a employee or a manager. The User
information is retrieved through files.
2. After the login activity, the authorized person can take customer orders. The
display of menu is done after choosing the appropriate option from the main
menu.
3. The restaurant can have menu items viewed in sorted manner this facilitates
the customer in choosing their favourite dishes.
4. The orders if not present and user tries to modify the program no orders
exception.
5. If desired food item not found the program throws a food not found exception
6. Users can add quantity according to their choice and also later modify the
order placed as the like.
7. After the order is placed the user can also view the orders placed
8. Finally, A bill is displayed accordingly for the users according to their orders
this data is saved in the file with user details.
9. Managers can in addition to placing order can modify the menu and change,
view employee data and add new employees in their restaurant.
10. The number of orders placed daily is saved in a file day wise with latest order
placed time shown.
##  Strategy:
The main of the program is to implement the project using object-oriented
programming in Java so as to make the program efficient.

## MOTIVATION
The project is helpful to remove the existing system of restaurant’s handling
customers. It removes the need of managing huge data in paper work and instead
helps maintain data in system files which can be modified as and when required easily
without any additional requirements. Earlier restaurant’s used to manual compute the
bill and this seemed to cause several human errors but now with such applications
users can accurately get their bills with additional discounts as and when available.



##code##


import java.util.*;
public class RestaurantSystem {
    static Scanner sc = new Scanner(System.in);
    static String restaurantName = "QuadSquad Restaurant";

    static String[] menu = {
        "Dosa", "Idli", "Vada", "Masala Dosa", "Upma",
        "Chicken Biryani", "Veg Biryani", "Pulao", "Roti",
        "Paneer Curry", "Chicken Curry", "Dal Fry",
        "Tea", "Coffee", "Thums Up", "Sprite"
    };

    static double[] prices = {
        50, 40, 30, 70, 35,
        150, 120, 100, 15,
        90, 140, 80,
        10, 15, 20, 20
    };

    static HashMap<String, Double> priceMap = new HashMap<>();
    static LinkedList<String> orderList = new LinkedList<>();
    static Queue<String> orderQueue = new LinkedList<>();
    static Set<Integer> reservedTables = new HashSet<>();
    static Map<Integer, Boolean> reservationPaid = new HashMap<>();
    static ArrayList<String> feedbacks = new ArrayList<>();
    static String[] sortedMenu;
    static int lostDeposits = 0;

    public static void main(String[] args) {
        for (int i = 0; i < menu.length; i++) {
            priceMap.put(menu[i], prices[i]);
        }
        sortedMenu = menu.clone();
        bubbleSort(sortedMenu);

        while (true) {
            System.out.println("\n--- Welcome to " + restaurantName + " ---");
            System.out.println("1. View Menu");
            System.out.println("2. Place Order");
            System.out.println("3. Reserve Table");
            System.out.println("4. Process Orders");
            System.out.println("5. Make Payment");
            System.out.println("6. Leave Feedback");
            System.out.println("7. Exit");
            System.out.println("8. Clear Orders");
            System.out.println("9. Admin Panel");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> showMenu();
                case 2 -> placeOrder();
                case 3 -> reserveTable();
                case 4 -> processOrders();
                case 5 -> payBill();
                case 6 -> leaveFeedback();
                case 7 -> {
                    if (!orderList.isEmpty()) {
                        System.out.println("You have unpaid orders! Please pay before exiting.");
                    } else {
                        if (!reservedTables.isEmpty()) {
                            System.out.println("You reserved a table but didn't order. Deposit forfeited.");
                            lostDeposits += reservedTables.size() * 50;
                        }
                        reservedTables.clear();
                        reservationPaid.clear();
                        System.out.println("Thanks for visiting " + restaurantName);
                        return;
                    }
                }
                case 8 -> clearOrders();
                case 9 -> adminPanel();
                default -> System.out.println("Invalid choice");
            }
        }
    }

    static void showMenu() {
        System.out.println("\nMenu:");
        for (int i = 0; i < sortedMenu.length; i++) {
            System.out.printf("%d. %s - Rs%.2f\n", i + 1, sortedMenu[i], priceMap.get(sortedMenu[i]));
        }
    }

    static void placeOrder() {
        sc.nextLine();
        while (true) {
            System.out.print("Enter item to order (or 'done'): ");
            String item = sc.nextLine().trim();
            if (item.equalsIgnoreCase("done")) break;
            int idx = binarySearch(sortedMenu, item);
            if (idx == -1) {
                System.out.println("Item not found.");
                continue;
            }
            orderList.add(sortedMenu[idx]);
            orderQueue.add(sortedMenu[idx]);
            System.out.println(item + " added to your order.");
        }
    }

    static void processOrders() {
        if (orderQueue.isEmpty()) {
            System.out.println("No orders to process.");
            return;
        }
        System.out.println("\nProcessing Orders:");
        while (!orderQueue.isEmpty()) {
            String item = orderQueue.poll();
            System.out.println("Prepared: " + item);
        }
    }

    static void reserveTable() {
        System.out.print("Enter table number (1-10): ");
        int t = sc.nextInt();
        if (t < 1 || t > 10) {
            System.out.println("Invalid table number");
        } else if (reservedTables.contains(t)) {
            System.out.println("Table already reserved");
        } else {
            System.out.print("Pay Rs50 deposit to reserve (yes/no): ");
            sc.nextLine();
            String confirm = sc.nextLine().trim();
            if (confirm.equalsIgnoreCase("yes")) {
                reservedTables.add(t);
                reservationPaid.put(t, true);
                System.out.println("Table " + t + " reserved with deposit.");
            } else {
                System.out.println("Reservation not completed.");
            }
        }
    }

    static void payBill() {
        if (orderList.isEmpty()) {
            System.out.println("No items in your order.");
            return;
        }
        double total = 0;
        Map<String, Integer> itemCount = new HashMap<>();
        for (String item : orderList) {
            itemCount.put(item, itemCount.getOrDefault(item, 0) + 1);
        }
        System.out.println("\nYour Order:");
        for (String item : itemCount.keySet()) {
            int count = itemCount.get(item);
            double price = priceMap.get(item);
            System.out.printf("- %s x%d: Rs%.2f\n", item, count, count * price);
            total += count * price;
        }
        System.out.printf("Total: Rs%.2f\n", total);
        orderList.clear();
        System.out.println("Payment received. Thank you!");
    }

    static void leaveFeedback() {
        sc.nextLine();
        System.out.print("Enter feedback: ");
        String f = sc.nextLine();
        feedbacks.add(f);
        System.out.println("Thanks for your feedback!");
    }

    static void clearOrders() {
        if (!orderList.isEmpty()) {
            System.out.println("You have unpaid orders! Please pay before clearing.");
            return;
        }
        orderList.clear();
        orderQueue.clear();
        System.out.println("All orders cleared.");
    }

    static void adminPanel() {
        System.out.println("\n--- Admin Panel ---");
        System.out.println("1. View Feedbacks");
        System.out.println("2. View Reserved Tables");
        System.out.println("3. View All Orders");
        System.out.println("4. View Lost Deposits");
        System.out.print("Choose: ");
        int adminChoice = sc.nextInt();

        switch (adminChoice) {
            case 1 -> {
                System.out.println("\nFeedbacks:");
                if (feedbacks.isEmpty()) System.out.println("No feedbacks yet");
                else feedbacks.forEach(f -> System.out.println("- " + f));
            }
            case 2 -> {
                System.out.println("\nReserved Tables:");
                if (reservedTables.isEmpty()) System.out.println("No tables reserved");
                else {
                    for (Integer table : reservedTables) {
                        String status = reservationPaid.getOrDefault(table, false) ? "Paid" : "Unpaid";
                        System.out.println("Table " + table + " - " + status);
                    }
                }
            }
            case 3 -> {
                System.out.println("\nCurrent Orders:");
                if (orderList.isEmpty()) System.out.println("No orders placed yet");
                else orderList.forEach(item -> System.out.println("- " + item));
            }
            case 4 -> {
                System.out.println("\nLost Deposits Due to No-Shows: Rs" + lostDeposits);
            }
            default -> System.out.println("Invalid choice");
        }
    }

    static void bubbleSort(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareToIgnoreCase(arr[j + 1]) > 0) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    static int binarySearch(String[] arr, String key) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = key.compareToIgnoreCase(arr[mid]);
            if (cmp == 0) return mid;
            if (cmp < 0) high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }
}
