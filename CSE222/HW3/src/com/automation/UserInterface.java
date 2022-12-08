package com.automation;
import java.util.Scanner;



public class UserInterface {
    //private static Person user;
    private static Scanner sc = new Scanner(System.in);
    
    public static void menu(){
        Adminstrator admin = new Adminstrator("Mehmet", "Yılmaz", 0);
        Adminstrator.db.createAdmin("Mehmet", "Yılmaz", 0);
        admin.initDatabase();
        int choice = 0;
        boolean running = true;
        System.out.println("Default admin id : 0, branchEmployee ids: 0,1,2,3");
        System.out.println("Default customer mail : abc@gmail.com pw : 1234");
        while(running){
            System.out.println("1 - Log in as administrator");
            System.out.println("2 - Log in as branch employee");
            System.out.println("3 - Log in as customer");
            System.out.println("4 - Register as customer");
            System.out.println("5 - Exit\n");
            choice = sc.nextInt();
            switch(choice){
                case 1:
                adminMenu();
                break;

                case 2:
                branchEmployeeMenu();
                break;

                case 3:
                customerLogin();
                break;

                case 4:
                customerRegister();
                break;

                case 5:
                System.out.println("Exiting.");
                running = false;
                break;

                default:
                System.out.println("Wrong choice.");
                break;


            }
        }


    }


    public static void adminMenu(){
        int input;
        System.out.println("Enter your id: ");
        input = sc.nextInt();
        if(Adminstrator.db.searchAdmin(input)){
            Adminstrator admin = Adminstrator.db.getAdmin(input);
            boolean logout = false;
            while(!logout){
                System.out.println("---ADMIN---");
                System.out.println("1- Add Branch");
                System.out.println("2- Remove Branch");
                System.out.println("3- List Branches");
                System.out.println("4- Add Branch Employee");
                System.out.println("5- Remove Branch Employee");
                System.out.println("6- List a Branch's Employees");
                System.out.println("0- Logout");

                int choice = sc.nextInt();
                int inputChoice, branchIndex;
                int colorChoice, typeChoice;
                String str, str2, str3;
                int id;
                switch(choice){
                    case 1:
                        System.out.println("Enter name of the branch to be created: ");
                        str = sc.next();
                        admin.createBranch(str);
                        break;

                    case 2:
                        admin.printBranchNames();
                        System.out.println("Select id of the branch to be removed: ");
                        inputChoice = sc.nextInt();
                        admin.deleteBranchByIndex(inputChoice);
                        break;
                    case 3:
                        admin.printBranchNames();
                        break;
                    case 4:
                        System.out.println("Enter name of the employee to be added: ");
                        str = sc.next();
                        System.out.println("Enter surname: ");
                        str2 = sc.next();
                        admin.printBranchNames();
                        System.out.println("Select branch: ");
                        branchIndex = sc.nextInt();
                        str3 = Adminstrator.db.getBranch().get(branchIndex).getName();
                        id = Adminstrator.db.getLastEmployeeId() + 1;
                        System.out.println("Id of the employee is : " + id);
                        admin.addBranchEmployee(str, str2, id, str3);
                        break;

                    case 5:
                        admin.printBranchNames();
                        System.out.printf("Select branch: ");
                        inputChoice = sc.nextInt();
                        admin.getBranch().get(inputChoice).printBranchEmployee();
                        System.out.printf("Select branch employee to be removed: ");
                        id = sc.nextInt();
                        admin.removeBranchEmployee(id, inputChoice);
                        break;
                    case 6:
                        admin.printBranchNames();
                        System.out.printf("Select branch: ");
                        inputChoice = sc.nextInt();
                        admin.getBranch().get(inputChoice).printBranchEmployee();
                        break;

                    case 7:
                        printColor();
                        System.out.println("Select product color: ");
                        colorChoice = sc.nextInt();
                        System.out.printf("\n\n");
                        printType();
                        typeChoice = sc.nextInt();
                        System.out.println("Enter model name: ");
                        str = sc.next();
                        System.out.println("Enter branch name: ");
                        str2 = sc.next();
                        Product.type_t tempType = Product.type_t.convertToType(typeChoice);
                        Product.color_t tempColor = Product.color_t.convertToColor(colorChoice);
                        admin.addProduct(tempType, tempColor, str, str2);

                        break;
                    
                    
                    case 0:
                        logout = true;
                        System.out.println("Returning to main menu...");
                        break;
            }
        }

    }
        else{
            System.out.println("Such admin does not exist, returning to main menu...");
            
        }

        
    }

    public static void branchEmployeeMenu(){

        int input;
        int branchId = -1;
        System.out.println("Enter your employee id: ");
        input = sc.nextInt();
        if((branchId = Adminstrator.db.searchBranchEmployee(input)) != -1){
            BranchEmployee employee = Adminstrator.db.getBranchEmployee(input);
            boolean exit = false;
            while(!exit){
                System.out.println("---Branch Employee---");
                System.out.println("1- Add Product");
                System.out.println("2- Remove Product");
                System.out.println("3- List Products");
                System.out.println("4- Access customer order list");
                System.out.println("5- Add sale");
                System.out.println("6- List previous customers");
                System.out.println("7- Add stock of a product");
                System.out.println("0- Logout");
                int choice = sc.nextInt();
                int colorChoice, typeChoice;
                int input0, input1;
                String str, str2, str3, str4;
                int id;
                switch(choice){
                    case 1:
                        printColor();
                        System.out.println("Select product color: ");
                        colorChoice = sc.nextInt();
                        System.out.printf("\n\n");
                        printType();
                        System.out.println("\nSelect type: ");
                        typeChoice = sc.nextInt();
                        System.out.println("Enter model name: ");
                        str = sc.next();
                        Product.type_t tempType = Product.type_t.convertToType(typeChoice);
                        Product.color_t tempColor = Product.color_t.convertToColor(colorChoice);
                        employee.addProduct(tempType, tempColor, str, 1);
                        break;

                    case 2:
                        employee.printProducts();
                        System.out.println("Select product to remove: ");
                        input0 = sc.nextInt();
                        employee.removeProductByIndex(input0);
                        break;
                    case 3:
                        employee.printProducts();
                        break;
                    case 4:
                        employee.printCustomer();
                        System.out.println("Select customer to look-up order: ");
                        input0 = sc.nextInt();
                        employee.lookUpCustomerOrders(input0);
                        break;

                    case 5:
                        employee.printProducts();
                        System.out.println("Select product to sale: ");
                        input0 = sc.nextInt();
                        employee.printCustomer();
                        System.out.println("Is customer subscribed to our store?");
                        System.out.println("0 - No");
                        System.out.println("1 - Yes");
                        System.out.printf("Select : ");
                        input1 = sc.nextInt();
                        if(input1 == 0){
                            System.out.printf("Enter customer's name: ");
                            str = sc.next();
                            System.out.printf("Enter customer's surname: ");
                            str2 = sc.next();
                            System.out.printf("Enter customer's mail: ");
                            str3 = sc.next();
                            System.out.printf("Enter customer's password: ");
                            str4 = sc.next();
                            employee.addCustomer(str, str2, str3, str4);
                            id = Adminstrator.db.getLastCustomerId();
                        }
                        else if(input1 == 1){
                            employee.printCustomer();
                            System.out.printf("Select customer: ");
                            id = sc.nextInt();
                        }
                        else{
                            System.out.println("Not a valid option");
                            break;
                        }
                        employee.removeProductByIndex(input0);
                        employee.sellProduct(input0, id, branchId);
                        break;
                    case 6:
                        employee.printCustomer();
                        break;           
                    case 7:
                        employee.printProducts();
                        System.out.println("Select a product: ");
                        input0 = sc.nextInt();
                        System.out.println("Enter stock number to be added:");
                        input1 = sc.nextInt();
                        employee.increaseProduct(input0, input1);
                        break;
                    case 0:
                        exit = true;
                        System.out.println("Returning to main menu...");
                    break;
            }
        }

    }
        else{
            System.out.println("Such employee does not exist, returning to main menu...");
            
        }
    }

    public static void customerLogin(){

        String mail,pw;
        System.out.println("Enter email: ");
        mail = sc.next();
        System.out.println("Enter password: ");
        pw = sc.next();
        int index;
        if((index = Adminstrator.db.validateUser(mail, pw)) != -1){
            Customer customer = Adminstrator.db.getCustomer().get(index);
            boolean exit = false;
            while(!exit){
                System.out.println("---Customer---");
                System.out.println("1- Search Products");
                System.out.println("2- List Products");
                System.out.println("3- Shop online");
                System.out.println("4- View order");
                System.out.println("0- Logout");

                int choice = sc.nextInt();
                int colorChoice, typeChoice;
                int input0, input1, branchIndex;
                String str, str2, str3, str4;
                switch(choice){
                    case 1:
                        printColor();
                        System.out.println("Select product color: ");
                        colorChoice = sc.nextInt();
                        System.out.printf("\n\n");
                        printType();
                        typeChoice = sc.nextInt();
                        System.out.println("Enter model name: ");
                        str = sc.next();
                        Product.type_t tempType = Product.type_t.convertToType(typeChoice);
                        Product.color_t tempColor = Product.color_t.convertToColor(colorChoice);
                        Product tempProduct = new Product(tempType, tempColor, str);
                        branchIndex = Adminstrator.db.searchProduct(tempType, tempColor, str);
                        if(branchIndex != -1){
                            System.out.println("Found desired product");
                            System.out.printf("%s %s %s\n", tempProduct.getType(), tempProduct.getColor(), tempProduct.getModel());
                            System.out.printf("1- Buy\n2- Go back to menu\n");
                            input0 = sc.nextInt();
                            if(input0 == 1){
                                System.out.println("Enter your address: ");
                                str2 = sc.next();
                                System.out.println("Enter your phone number: ");
                                str3 = sc.next();
                                customer.addOrder(tempProduct);
                                str4 = Adminstrator.db.getBranch().get(branchIndex).getName();
                                Adminstrator.db.decreaseProduct(tempType, tempColor, str, str4);
                            }
                        }
                        else{
                            System.out.println("Cannot find desired product returning to menu...");
                        }
                        break;

                    case 2:
                        Adminstrator.db.printAllProduct();
                        break;
                    case 3:
                        Adminstrator.db.printBranchNames();
                        System.out.println("Select branch:");
                        input0 = sc.nextInt();
                        Branch temp = Adminstrator.db.getBranch().get(input0);
                        temp.printProducts();
                        System.out.println("Select product:");
                        input1 = sc.nextInt();
                        Product tempProduct2 = temp.getProductByIndex(input1);
                        if(tempProduct2 != null){
                            System.out.println("Enter your address: ");
                            str2 = sc.next();
                            System.out.println("Enter your phone number: ");
                            str3 = sc.next();
                            customer.addOrder(tempProduct2);
                            str4 = Adminstrator.db.getBranch().get(input0).getName();
                            Adminstrator.db.decreaseProduct(tempProduct2.getEnumType(), tempProduct2.getEnumColor(), tempProduct2.getModel(), str4);
                        }
                    
                    else{
                        System.out.println("Cannot find desired product returning to menu...");
                    }
                        break;
                    case 4:
                        customer.viewOrder();
                        break;
                    
                    case 0:
                        exit = true;
                        System.out.println("Returning to main menu...");
                    break;
            }
        }

    }
        else{
            System.out.println("Such customer does not exist, returning to main menu...");
            
        }
    }

    public static void customerRegister(){
        boolean fail = false;
        String str,str2,str3,str4;
        System.out.println("Enter your name:");
        str = sc.next();
        System.out.println("Enter your surname:");
        str2 = sc.next();
        System.out.println("Enter your mail:");
        str3 = sc.next();
        System.out.println("Enter your password:");
        str4 = sc.next();
        
        fail = Adminstrator.db.duplicateMail(str3);
        if(!fail){
            int id = Adminstrator.db.getLastCustomerId() + 1;
            System.out.println("Your id is : " + id);
            Adminstrator.db.addCustomer(new Customer(str,str2,str3,str4,id));
        }
        else{
            System.out.println("Mail is already in use, You cannot register with same mail.");
        }

    }

    public static void printColor(){
        System.out.printf("0 - White\n1 - Black\n2 - Brown\n");
        System.out.printf("3 - Red\n4 - Green\n5 - Yellow\n6 - Orange\n7 - Purple\n8 - Pink\n");
    }  

    public static void printType(){
        System.out.printf("0 - Office Chair\n1 - Office Desk\n2 - Meeting table\n3 - Office Cabinet\n4 - Bookcase");
    }


    
    
    


    
}
