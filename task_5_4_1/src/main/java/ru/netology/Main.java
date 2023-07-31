package ru.netology;

import ru.netology.assortments.Assortment;
import ru.netology.items.Item;
import ru.netology.items.Product;

import java.util.Scanner;

public class Main {
    static Assortment assortment = new Assortment();
    static Assortment basket = new Assortment();
    static final String[] commands = {"display", "order", "rate", "exit"};
    static Scanner scanner = new Scanner(System.in);
    static String input;

    public static void main(String[] args) {
        populateAssortment(assortment);

        boolean flag = true;
        while (flag) {
            printInstruction();
            input = scanner.nextLine();

            switch (input) {
                case ("display"): {
                    displayCommand();
                    break;
                }
                case ("order"): {
                    orderCommand();
                    break;
                }
                case ("rate"): {
                    rateCommand();
                    break;
                }
                case ("exit"): {
                    flag = false;
                    break;
                }
                default: {
                    System.out.println("Wrong command.");
                    break;
                }
            }

            if ("exit".equals(input)) {
                break;
            }
        }
    }

    public static void populateAssortment(Assortment assortment) {
        // Create new items
        Product egg = new Product(1, "Egg", 70, 10, 21);
        Product apples = new Product(2, "Apples", 160, 20, 30);
        Product milk = new Product(3, "Milk", 90, 36, 60);
        Product bread = new Product(4, "Bread", 50, 16, 3);
        Product onion = new Product(5, "Onion", 45, 8, 14);

        // Add new items to assortment
        assortment.addProduct(egg);
        assortment.addProduct(apples);
        assortment.addProduct(milk);
        assortment.addProduct(bread);
        assortment.addProduct(onion);
    }

    public static void printCommands() {
        for (String command : commands) {
            System.out.println(command);
        }
    }

    public static void printInstruction() {
        System.out.println("\nAvailable products:");
        assortment.printAssortment();
        System.out.println("Available commands:");
        printCommands();
    }

    public static void displayCommand() {
        System.out.println("Ordered products:");
        basket.printAssortment();
    }

    public static void rateCommand() {
        System.out.println("To rate product input two numbers which are separated with space -");
        System.out.print("product number and its rating from 0 to 5: ");

        String input = scanner.nextLine();
        try {
            String[] parts = input.split(" ");
            int id = Integer.parseInt(parts[0]);
            int rating = Integer.parseInt(parts[1]);
            if (checkId(assortment, id)) {
                for (Item item : assortment.getAssormentList()) {
                    if (item.getId() == id) {
                        item.rate(rating);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Wrong input format.");
        }
    }

    public static void orderCommand() {
        System.out.println("\nTo order product input two numbers which are separated with space -");
        System.out.print("product number and its quantity: ");

        String input = scanner.nextLine();
        try {
            String[] parts = input.split(" ");
            int id = Integer.parseInt(parts[0]);
            int quantity = Integer.parseInt(parts[1]);
            boolean orderedItemFound = false;
            if (checkId(assortment, id) && checkQuantity(assortment, id, quantity)) {
                for (Product item : assortment.getAssormentList()) {
                    if (item.getId() == id) {
                        for (Item orderedItem : basket.getAssormentList()) {
                            if (orderedItem.getId() == id) {
                                orderedItem.setAvailableQty(orderedItem.getAvailableQty() + quantity);
                                item.setAvailableQty(item.getAvailableQty() - quantity);
                                orderedItemFound = true;
                            }
                        }
                        if (!orderedItemFound) {
                            Product orderedProduct = new Product(item);
                            orderedProduct.setAvailableQty(quantity);
                            item.setAvailableQty(item.getAvailableQty() - quantity);
                            basket.addProduct(orderedProduct);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Wrong input format.");
        }
    }

    public static boolean checkId(Assortment assortment, int id) {
        if (id < 1 || id > assortment.getSize()) {
            System.out.println("Wrong product number. Input correct product number.");
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkQuantity(Assortment assortment, int id, int quantity) {
        if (quantity <= 0) {
            System.out.println("Product quantity must be positive. "
                    + "Input correct product quantity.");
            return false;
        }
        for (Item item : assortment.getAssormentList()) {
            if (item.getId() == id) {
                int availableQty = item.getAvailableQty();
                if (quantity > availableQty) {
                    System.out.println("Our store has no such quantity of product \""
                            + item.getTitle() + "\".");
                    System.out.println("You can order only "
                            + item.getAvailableQty() + " pieces of it.");
                    return false;
                }
            }
        }
        return true;
    }
}