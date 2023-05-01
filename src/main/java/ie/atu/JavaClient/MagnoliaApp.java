package ie.atu.JavaClient;

import ie.atu.dbTables.dbBooks;
import ie.atu.jdbc.dbConnection;
import ie.atu.dbTables.dbStationary;
import java.sql.Connection;
import java.util.Scanner;

public class MagnoliaApp {
    public static void main(String[] args) {
        int libraryMenu = 0;
        Connection connection = null;

        dbConnection dbConnection = new dbConnection();
        Scanner scanner = new Scanner(System.in);
        try {
            connection = dbConnection.connection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String userInput = "0";
        while (!userInput.equals("q")) {
            System.out.println("\n\nWelcome to the Magnolia Library Terminal.");
            System.out.println("Please select from the following options: ");
            System.out.println("---[MENU]---\n1. Books\n2. Stationary\n3. Music\n4. Computers\n\nEnter[1 , 2 , 3 , 4]");
            userInput = scanner.next();

            switch (userInput) {
                case "1":
                    System.out.println("You have selected: Books");
                    libraryMenu = 1;
                    break;
                case "2":
                    libraryMenu = 2;
                    System.out.println("You have selected: Stationary");
                    break;
                case "3":
                    libraryMenu = 3;
                    System.out.println("You have selected: Music");
                    break;
                case "4":
                    libraryMenu = 4;
                    System.out.println("You have selected: Computers");
                default:
                    System.out.println("Invalid Entry. Please select from the following options: ");
                    System.out.println("---[MAIN MENU]---\n1. Books\n2. Stationary\n3. Music\n4. Computers\n\nEnter[1 , 2 , 3 , 4]");
                    break;
            }
            //BOOK MENU
            if (libraryMenu == 1) {
                String refID;
                String refColumn;
                int stringSize;
                System.out.println("\nPlease select from the following options: ");
                System.out.println("---[BOOKS]---\n1. Add new book to database\n2. Edit existing books\n3. Checkout book\n4. Check available books\n5. Delete book\n6. Back to selection\n\nEnter[1 , 2 , 3 , 4 , 5]");
                String bookInput = scanner.next();
                switch (bookInput) {
                    case "1" -> { //Add new book
                        System.out.println("To add a new book to the database, please input the following: ");
                        System.out.print("Book name: ");
                        String name = scanner.next();
                        System.out.print("\nAuthor: ");
                        String author = scanner.next();
                        System.out.print("\nPublication date: ");
                        String publication = scanner.next();
                        dbBooks newBook = new dbBooks(connection, name, author, publication, false);
                        newBook.addItem();
                    }
                    case "2" -> { //Edit existing data
                        System.out.println("To edit an existing book in the database, please input the following: ");
                        System.out.print("Input either name of the book or book ID you want to edit: ");
                        refID = scanner.next();
                        stringSize = refID.length();
                        if (stringSize <= 2) {
                            refColumn = "id";
                        } else {
                            refColumn = "name";
                        }
                        System.out.print("\nInformation to edit(name, author, publication): ");
                        String columnToChange = scanner.next();
                        System.out.print("\nInput the updated information: ");
                        String newInfo = scanner.next();
                        dbBooks updateBook = new dbBooks(connection);
                        updateBook.editItem(columnToChange, newInfo, refColumn, refID);
                    }
                    case "3" -> { //Checkout book
                        System.out.print("Input the name or ID no. of the book to check out: ");
                        refID = scanner.next();
                        stringSize = refID.length();
                        if (stringSize <= 2) {
                            refColumn = "id";
                        } else {
                            refColumn = "name";
                        }
                        dbBooks checkoutBook = new dbBooks(connection);
                        checkoutBook.checkout(refColumn, refID);
                    }
                    case "4" -> { //Check available books
                        System.out.println("\nPlease select from the following: ");
                        System.out.println("1. All books in database\n2. Specific book search");
                        dbBooks availableBooks = new dbBooks(connection);
                        String rentedInput = scanner.next();
                        switch (rentedInput) {
                            case "1":
                                System.out.println("Please select from the following: ");
                                System.out.println("1. Show available books\n2. Show rented books\n3. Show All");
                                rentedInput = scanner.next();
                                if (rentedInput.equals("1")) {
                                    availableBooks.isAvailable();
                                } else if (rentedInput.equals("2")) {
                                    availableBooks.isRented();
                                } else {
                                    availableBooks.showAll();
                                }
                                break;
                            case "2":
                                System.out.print("Input the name or ID no. of the book to view: ");
                                refID = scanner.next();
                                stringSize = refID.length();
                                if (stringSize <= 2) {
                                    refColumn = "id";
                                } else {
                                    refColumn = "name";
                                }
                                availableBooks.isAvailable(refColumn, refID);
                                break;
                            default:
                                System.out.println("Invalid Selection.");
                                System.out.println("\nPlease select from the following: ");
                                System.out.println("1. All books in database\n2. Specific book search");
                                break;
                        }
                    }
                    case "5" -> {   //DELETE BOOK FROM DB
                        System.out.println("Input the name or ID no. of the book to DELETE from database.");
                        System.out.println("NOTE: THIS CANNOT BE UNDONE");
                        dbBooks removeBook = new dbBooks(connection);
                        refID = scanner.next();
                        stringSize = refID.length();
                        if (stringSize <= 2) {
                            refColumn = "id";
                        } else {
                            refColumn = "name";
                        }
                        removeBook.deleteItem(refColumn, refID);
                    }
                    case "6" -> //Return to main menu
                            libraryMenu = 0;
                    default -> {
                        System.out.println("\nInvalid entry.\nPlease select from the following options: ");
                        System.out.println("---[BOOKS]---\n1. Add new book to database\n2. Edit existing books\n3. Checkout book\n4. Check available books\n5. Delete book\n\nEnter[1 , 2 , 3 , 4 , 5]");
                    }
                }
            }
            //STATIONARY MENU
            if (libraryMenu == 2) {
                String refID;
                String refColumn;
                int stringSize;
                System.out.println("\nPlease select from the following options: ");
                System.out.println("---[STATIONARY]---\n1. Add new stationary to database\n2. Edit existing items\n3. Buy item\n4. check stock\n5. delete item\n\nEnter[1 , 2 , 3 , 4, 5]");
                String StationaryInput = scanner.next();
                switch (StationaryInput) {
                    case "1":
                        System.out.println("To add a new Stationary item to the database, please input the following: ");
                        System.out.print("item name: ");
                        String name = scanner.next();
                        System.out.print("\nDescription: ");
                        scanner.nextLine(); //Absorb newline character after scanner.next();
                        String description = scanner.nextLine();
                        System.out.print("\nPrice: ");
                        int price = Integer.parseInt((scanner.next()));
                        System.out.print("\nUser discount: ");
                        int user_discount = Integer.parseInt((scanner.next()));
                        System.out.print("\nStock: ");
                        int stock = Integer.parseInt((scanner.next()));
                        dbStationary newStationary = new dbStationary(connection, name, description, price, user_discount, stock);
                        newStationary.addItem();
                        break;
                    case "2":
                        System.out.println("To edit an existing stationary item in the database, please input the following: ");
                        System.out.print("Input either name of the stationary item or item ID you want to edit: ");
                        refID = scanner.next();
                        stringSize = (refID.length());
                        if (2 >= stringSize) {
                            refColumn = "id";
                        } else {
                            refColumn = "name";
                        }
                        System.out.print("\nInformation to edit(name, description, price, user_discount, stock): ");
                        String columnToChange = scanner.next();
                        System.out.print("\nInput the updated information: ");
                        String newInfo = scanner.next();
                        dbStationary updateStationary = new dbStationary(connection);
                        updateStationary.editItem(columnToChange, newInfo, refColumn, refID);
                        break;
                    case "3":
                        System.out.print("Input the name or ID no. of the stationary for purchase: ");
                        refID = scanner.next();
                        stringSize = refID.length();
                        if (stringSize <= 2) {
                            refColumn = "id";
                        } else {
                            refColumn = "name";
                        }
                        dbStationary checkoutStationary = new dbStationary(connection);
                        checkoutStationary.purchaseItem(refColumn, refID);
                        break;
                    case "4":
                        System.out.println("\nPlease select from the following: ");
                        System.out.println("1. All stationary in database\n2. Specific stationary search");
                        dbStationary availableStationary = new dbStationary(connection);
                        String availableInput = scanner.next();
                        switch (availableInput) {
                            case "1":
                                System.out.println("Please select from the following: ");
                                System.out.println("1. Show available stationary\n2. Show available stationary\n3. Show All");
                                availableInput = scanner.next();
                                if (availableInput.equals("1")) {
                                    availableStationary.isAvailable();
                                } else if (availableInput.equals("2")) {
                                    //availableStationary.isRented();
                                } else {
                                    //availableStationary.showAll();
                                }
                                break;
                            case "2":
                                System.out.print("Input the name or ID no. of the stationary to view: ");
                                refID = scanner.next();
                                stringSize = refID.length();
                                if (stringSize <= 2) {
                                    refColumn = "id";
                                } else {
                                    refColumn = "name";
                                }
                                availableStationary.isAvailable(refColumn, refID);
                                break;
                            default:
                                System.out.println("Invalid Selection.");
                                System.out.println("\nPlease select from the following: ");
                                System.out.println("1. All stationary in database\n2. Specific stationary search");
                                break;


                        }
                    case "5":
                        System.out.println("Input the name or ID no. of the stationary to DELETE from database.");
                        System.out.println("NOTE: THIS CANNOT BE UNDONE");
                        dbStationary removeStationary = new dbStationary(connection);
                        refID = scanner.next();
                        stringSize = refID.length();
                        if (stringSize <= 2) {
                            refColumn = "id";
                        } else {
                            refColumn = "name";
                        }
                        removeStationary.deleteItem(refColumn, refID);
                        break;
                    default:
                        System.out.println("\nInvalid entry.\nPlease select from the following options: ");
                        System.out.println("---[STATIONARY]---\n1. Add new book to database\n2. Edit existing books\n3. Checkout book\n4. Check available books\n5. Delete book\n\nEnter[1 , 2 , 3 , 4 , 5]");
                        break;
                }


                }
                //MUSIC MENU
                if (libraryMenu == 3) {
                    System.out.println("\nPlease select from the following options: ");
                    System.out.println("---[MUSIC]---\n1. Add new music to database\n2. Edit existing items\n3. Checkout item\n4. Check available music\n\nEnter[1 , 2 , 3 , 4]");
                    userInput = scanner.next();
                }
                //COMPUTER MENU
                if (libraryMenu == 4) {
                    System.out.println("\nPlease select from the following options: ");
                    System.out.println("---[COMPUTERS]---\n1. Add new computer to database\n2. Edit existing items\n3. Book a computer\n4. Check available computers\n\nEnter[1 , 2 , 3 , 4]");
                    userInput = scanner.next();
                    //Add switch case for class specific methods
                }
            }
        }
    }

