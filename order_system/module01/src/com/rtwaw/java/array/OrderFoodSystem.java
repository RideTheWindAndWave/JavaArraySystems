package com.rtwaw.java.array;

import java.util.Scanner;

public class OrderFoodSystem {

    //Define the data body: dish
    static String[] dishNames = {"Red ribbon fish", "Fish and meat silk", "Time vegetables"};
    static double[] prices = {38.0, 20.0, 10.0};
    static int[] numberOfLike = new int[3];
    //Define the data body: order
    static String[] names = new String[4];
    static String[] mealInformation = new String[4];//dishName + numberOfMeals
    static int[] times = new int[4];
    static String[] addresses = new String[4];
    static double[] totalAmounts = new double[4];
    static int[] states = new int[4];//0: Booked, 1: Completed

    public static void main(String[] args) {
        //Initialize two order information
        dataInitialization();

        //Build the overall process framework for project
        implementMenuSwitching();
    }

    //Level 1
    private static void dataInitialization() {
        names[0] = "San Zhang";
        mealInformation[0] = "Red ribbon fish x2";
        times[0] = 10;
        addresses[0] = "223 Kirin Road";
        totalAmounts[0] = 76;//If the cost of 500 free delivery fee, otherwise the delivery fee of 6 RMB.
        states[0] = 0;
        names[1] = "Si Li";
        mealInformation[1] = "Fish and meat silk x1";
        times[1] = 13;
        addresses[1] = "207 Tian Cheng Road";
        totalAmounts[1] = 26;
        states[1] = 1;
    }

    //Level 1
    private static void implementMenuSwitching() {
        Scanner input = new Scanner(System.in);
        int num = -1;//The main menu is repeated at num = 0;
        System.out.println("Welcome to the ordering system(Powered by Hengjia Liu)");
        boolean flag = false;//A flag that records whether the user is exiting
        do {
            System.out.println("********************");
            System.out.println("1. Order a meal");
            System.out.println("2. Check the bag");
            System.out.println("3. Receive the order");
            System.out.println("4. Delete the order");
            System.out.println("5. Like it");
            System.out.println("6. Exit the system");
            System.out.println("********************");
            System.out.print("Please select: ");
            int choose = input.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("*****Order a meal*****");
                    orderAMeal();
                    break;
                case 2:
                    System.out.println("*****Check the bag*****");
                    checkTheBag();
                    break;
                case 3:
                    System.out.println("*****Receive the order*****");
                    receiveTheOrder();
                    break;
                case 4:
                    System.out.println("*****Delete the order*****");
                    deleteTheOrder();
                    break;
                case 5:
                    System.out.println("*****Like it*****");
                    likeIt();
                    break;
                case 6:
                    //Exit the system
                    flag = true;
                    break;
                default:
                    //Exit the program
                    System.exit(0);
                    break;
            }
            if (!flag) {
                System.out.print("Please enter 0 back: ");
                num = input.nextInt();
            } else {
                break;
            }
        } while (num == 0);
        System.out.println("Thanks for using, welcome to come next time!");
    }

    //Level 2 for implementMenuSwitching()
    private static void orderAMeal() {
        Scanner input = new Scanner(System.in);
        boolean isCanAdd = false;
        for (int i = 0; i < names.length; i++) {
            if (names[i] == null) {
                isCanAdd = true;//Orders are not full and meals can be ordered
                System.out.print("Please enter your name: ");
                String name = input.nextLine();
                //Cycle through the output of vegetable information
                System.out.println("Serial number\t\tDish name\t\tPrice");
                for (int j = 0; j < dishNames.length; j++) {
                    String praise = (numberOfLike[j] == 0 ? "" : numberOfLike[j] + " Praise");
                    System.out.println((j + 1) + "\t" + dishNames[j] + "\t" + prices[j] + "\t\t" + praise);
                }
                System.out.print("Please enter the number of the dish you are asking for: ");
                int no = input.nextInt();
                while (no < 1 || no > dishNames.length) {
                    System.out.print("We don\'t have this dish, please choose again: ");
                    no = input.nextInt();
                }
                //The number of servings to order
                System.out.print("Please enter the number of servings to order: ");
                int number = input.nextInt();
                //Input and judgment of delivery time
                System.out.print("Please enter the delivery time(Meal delivery time is only between 10 and 20 o\'clock): ");
                int time = input.nextInt();
                while (time < 10 || time > 20) {
                    System.out.print("Your input is incorrect, please enter an integer between 10 and 20: ");
                    time = input.nextInt();
                }
                //Delivery address
                System.out.print("Please enter your address: ");
                String address = input.next();
                //Output the order information to the user to see
                System.out.println("The order was successful!");
                //Dish information
                String dishInformation = dishNames[no - 1] + " x" + number;
                System.out.println("You ordered: " + dishInformation);
                System.out.println("Delivery time: " + time + " o\'clock");
                //Total amount
                double dishPrice = prices[no - 1] * number;
                double deliveryFee = (dishPrice > 50) ? 0 : 6;
                double totalAmount = dishPrice + deliveryFee;
                System.out.println("Meals: " + dishPrice + " RMB; Delivery fee: " + deliveryFee
                        + "; Total amount: " + totalAmount + " RMB");
                //Add information to the order information
                names[i] = name;
                mealInformation[i] = dishInformation;
                times[i] = time;
                addresses[i] = address;
                totalAmounts[i] = totalAmount;
                //At the eng of this order, jump out of the loop
                break;
            }
        }
        if (!isCanAdd) {
            System.out.println("Sorry, your bag is full!");
        }
    }

    //Level 2 for implementMenuSwitching()
    private static void checkTheBag() {
        System.out.println("Serial number\t\tname\t\tMeal information\t\tTime\t\tAddress\t\tTotal amount\t\tState");
        for (int i = 0; i < names.length; i++) {
            if (names[i] != null) {
                String time = times[i] + " o\'clock";
                String state = (states[i] == 0) ? "Booked" : "Completed";
                System.out.println((i + 1) + "\t\t" + names[i] + "\t\t" + mealInformation[i] + "\t\t" + time
                        + "\t\t" + addresses[i] + "\t\t" + totalAmounts[i] + " RMB\t\t" + state);
            }
        }
    }

    //Level 2 for implementMenuSwitching()
    private static void receiveTheOrder() {
        Scanner input = new Scanner(System.in);
        boolean isFind = false;
        System.out.print("Please enter the order number you want to sign: ");
        int signNo = input.nextInt();
        for (int i = 0; i < names.length; i++) {
            if (names[i] != null && states[i] == 0 && i == signNo - 1) {
                isFind = true;
                states[i] = 1;
                System.out.println("The order was signed successfully!");
            } else if (names[i] != null && states[i] == 1 && i == signNo - 1) {
                isFind = true;
                System.out.println("The order you selected has been signed and cannot be re-signed!");
            }
        }
        if (!isFind) {
            System.out.println("The order you selected does not exist!");
        }
    }

    //Level 2 for implementMenuSwitching()
    private static void deleteTheOrder() {
        Scanner input = new Scanner(System.in);
        boolean isFind = false;
        System.out.print("Please enter the order number you want to delete: ");
        int deleteNo = input.nextInt();
        for (int i = 0; i < names.length; i++) {
            if (names[i] != null && states[i] == 0 && i == deleteNo - 1) {
                isFind = true;
                System.out.println("The order you selected was not signed and cannot be deleted!");
            } else if (names[i] != null && states[i] == 1 && i == deleteNo - 1) {
                isFind = true;
                for (int j = i; j < names.length - 1; j++) {
                    names[j] = names[j + 1];
                    mealInformation[j] = mealInformation[j + 1];
                    times[j] = times[i + 1];
                    addresses[j] = addresses[j + 1];
                    totalAmounts[j] = totalAmounts[j + 1];
                    states[j] = states[j + 1];
                }
                names[names.length - 1] = null;
                mealInformation[names.length - 1] = null;
                times[names.length - 1] = 0;
                addresses[names.length - 1] = null;
                totalAmounts[names.length - 1] = 0;
                states[names.length - 1] = 0;
                System.out.println("The order was deleted successfully!");
            }
        }
        if (!isFind) {
            System.out.println("The order you selected does not exist!");
        }
    }

    //Level 2 for implementMenuSwitching()
    private static void likeIt() {
        Scanner input = new Scanner(System.in);
        //Cycle through the output of vegetable information
        System.out.println("Serial number\t\tDish name\t\tPrice");
        for (int j = 0; j < dishNames.length; j++) {
            String praise = (numberOfLike[j] == 0 ? "" : numberOfLike[j] + " Praise");
            System.out.println((j + 1) + "\t\t" + dishNames[j] + "\t\t" + prices[j] + "\t\t" + praise);
        }
        //Like it
        System.out.print("Please enter the serial number of the dish you want to praise: ");
        int praiseNo = input.nextInt();
        while (praiseNo < 1 || praiseNo > dishNames.length) {
            System.out.print("Our shop does not have this dish, can not like! Please re-enter a food serial number: ");
            praiseNo = input.nextInt();
        }
        numberOfLike[praiseNo - 1]++;
        System.out.println("Like success!");
    }

}
