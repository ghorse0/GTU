package com.automation;

public class Driver {
    public static void main(String[] args){
        System.out.println("Creating default admin");
        Adminstrator admin = new Adminstrator("Default", "Admin", 0);
        System.out.println("Adding a branch called Kars");
        admin.createBranch("Kars");
        admin.printBranchNames();
        System.out.println("Adding a branch called Erzincan");
        admin.createBranch("Erzincan");
        admin.printBranchNames();
        System.out.println("Removing the branch in index 0");
        admin.deleteBranchByIndex(0);
        admin.printBranchNames();
        System.out.println("Adding a branch called Giresun");
        System.out.println("Adding a branch called Ankara");
        admin.createBranch("Giresun");
        admin.createBranch("Ankara");
        System.out.println("Adding a branch employee called e0 b0 to Erzincan...");
        System.out.println("Adding a branch employee called e1 b0 to Erzincan...");
        System.out.println("Adding a branch employee called e2 b1 to Giresun...");
        System.out.println("Adding a branch employee called e3 b1 to Giresun...");
        System.out.println("Adding a branch employee called e4 b1 to Giresun...");
        System.out.println("Adding a branch employee called e5 b2 to Ankara...");
        System.out.println("Adding a branch employee called e6 b2 to Ankara...");
        System.out.println("Adding a branch employee called e7 b2 to Ankara...");
        admin.addBranchEmployee("e0", "b0", 1, "Erzincan");
        admin.addBranchEmployee("e1", "b0", 7, "Erzincan");

        admin.addBranchEmployee("e2", "b1", 55, "Giresun");
        admin.addBranchEmployee("e3", "b1", 67, "Giresun");
        admin.addBranchEmployee("e4", "b1", 93, "Giresun");

        admin.addBranchEmployee("e5", "b2", 24, "Ankara");
        admin.addBranchEmployee("e6", "b2", 55, "Ankara");
        admin.addBranchEmployee("e7", "b2", 66, "Ankara");
        System.out.println("Printing branch employees...");
        admin.printBranchEmployee();

        System.out.println("Removing branch employee e2 b1 from Giresun");
        System.out.println("Removing branch employee e1 b0 from Erzincan");
        System.out.println("Removing branch employee e7 b2 from Ankara");
        admin.removeBranchEmployee(55,1);
        admin.removeBranchEmployee(7,0);
        admin.removeBranchEmployee(66,2);

        System.out.println("Printing branch employees...\nID - NAME - SURNAME - BRANCH");
        admin.printBranchEmployee();
        System.out.println("Adding chair0, table0 to Erzincan branch, bookcase0, cabinet0 to Ankara branch...");
        admin.addProduct(Product.type_t.desk, Product.color_t.red, "chair0", "Erzincan");
        admin.addProduct(Product.type_t.chair, Product.color_t.green, "table0", "Erzincan");
        admin.addProduct(Product.type_t.table, Product.color_t.green, "bookcase0", "Ankara");
        admin.addProduct(Product.type_t.cabinet, Product.color_t.green, "cabinet0", "Ankara");

        System.out.println("Adding desk1, chair1, table1 to Giresun branch");
        admin.addProduct(Product.type_t.desk, Product.color_t.red, "desk1", "Giresun");
        admin.addProduct(Product.type_t.chair, Product.color_t.green, "chair1", "Giresun");
        admin.addProduct(Product.type_t.table, Product.color_t.yellow, "table1", "Giresun");

        System.out.println("Switching to branch employee...\n");
        admin.addBranchEmployee("Default", "Employee", 44, "Giresun");
        BranchEmployee employee = admin.getBranchEmployee(44);
        System.out.println("Currently at Giresun branch.");
        System.out.println("Adding products..");
        System.out.println("Adding cabinet1, bookcase1...");
        employee.addProduct(Product.type_t.cabinet, Product.color_t.purple, "cabinet1", 10);
        employee.addProduct(Product.type_t.bookcase, Product.color_t.green, "bookcase1", 10);
        System.out.println("Printing products...");
        employee.printProducts();
        System.out.println("Removing chair1 and cabinet1...");
        employee.removeProductByIndex(1);
        employee.removeProductByIndex(2);
        employee.printProducts();
        

        System.out.println("Switching to customer...");
        admin.addCustomerToSystem(new Customer("Default", "Customer", "1234", "mail@mail.com", 1));
        Customer customer = Adminstrator.db.getCustomer().get(0);
        System.out.println("Printing products...");
        Adminstrator.db.printAllProduct();
        System.out.println("Buying table1 from Giresun branch");
            
        Branch tempBranch = Adminstrator.db.getBranch().get(1);
        Product tempProduct2 = tempBranch.getProductByIndex(0);
        customer.addOrder(tempProduct2);
        System.out.println("Viewing current order list");
        customer.viewOrder();

    }
    
}
