package com.automation;

public class Adminstrator extends Person {
    public static Database db = new Database();

    public Adminstrator(String name, String surname, int id){
        super(name, surname, id);
    }


    public void initDatabase(){
        db.createBranch("Ankara");
        db.createBranch("Istanbul");
        db.createBranch("Izmir");
        db.createBranch("Antalya");
        db.addBranchEmployee("Ahmet", "Yıldız", 0, "Ankara");
        db.addBranchEmployee("Mehmet", "Yıldız", 1, "Istanbul");
        db.addBranchEmployee("Orhan", "Yıldız", 2, "Izmir");
        db.addBranchEmployee("Mehmet", "Yılmaz", 3, "Antalya");
        db.addCustomer(new Customer("Mahmut", "Korkmaz", "abc@gmail.com", "1234", db.getLastCustomerId()+1));
        db.addProduct(Product.type_t.chair, Product.color_t.red, "OC1", "Ankara", 1);
        db.addProduct(Product.type_t.chair, Product.color_t.red, "OC2", "Ankara", 1);
        db.addProduct(Product.type_t.chair, Product.color_t.red, "OC3", "Ankara", 1);
        db.addProduct(Product.type_t.chair, Product.color_t.red, "OC4", "Ankara", 1);
        db.addProduct(Product.type_t.chair, Product.color_t.red, "OC5", "Ankara", 1);
        db.addProduct(Product.type_t.chair, Product.color_t.red, "OC6", "Ankara", 1);
        db.addProduct(Product.type_t.chair, Product.color_t.red, "OC7", "Ankara", 1);

        db.addProduct(Product.type_t.desk, Product.color_t.brown, "OD1", "Izmir", 1);
        db.addProduct(Product.type_t.desk, Product.color_t.brown, "OD2", "Izmir", 1);
        db.addProduct(Product.type_t.desk, Product.color_t.brown, "OD3", "Izmir", 1);
        db.addProduct(Product.type_t.desk, Product.color_t.brown, "OD4", "Izmir", 1);
        db.addProduct(Product.type_t.desk, Product.color_t.brown, "OD5", "Izmir", 1);

        db.addProduct(Product.type_t.table, Product.color_t.brown, "MT1", "Istanbul", 1);
        db.addProduct(Product.type_t.table, Product.color_t.red, "MT2", "Istanbul", 1);
        db.addProduct(Product.type_t.table, Product.color_t.yellow, "MT3", "Istanbul", 1);
        db.addProduct(Product.type_t.table, Product.color_t.brown, "MT4", "Istanbul", 1);
        db.addProduct(Product.type_t.table, Product.color_t.red, "MT5", "Istanbul", 1);

        db.addProduct(Product.type_t.bookcase, Product.color_t.white, "B1", "Antalya", 1);
        db.addProduct(Product.type_t.bookcase, Product.color_t.white, "B2", "Antalya", 1);
        db.addProduct(Product.type_t.bookcase, Product.color_t.white, "B3", "Antalya", 1);
        db.addProduct(Product.type_t.bookcase, Product.color_t.white, "B4", "Antalya", 1);
        db.addProduct(Product.type_t.bookcase, Product.color_t.white, "B5", "Antalya", 1);
        db.addProduct(Product.type_t.bookcase, Product.color_t.white, "B6", "Antalya", 1);

        db.addProduct(Product.type_t.cabinet, Product.color_t.white, "C1", "Antalya", 1);
        db.addProduct(Product.type_t.cabinet, Product.color_t.white, "C2", "Antalya", 1);
        db.addProduct(Product.type_t.cabinet, Product.color_t.white, "C3", "Antalya", 1);
        db.addProduct(Product.type_t.cabinet, Product.color_t.white, "C4", "Antalya", 1);
        db.addProduct(Product.type_t.cabinet, Product.color_t.white, "C5", "Antalya", 1);
        db.addProduct(Product.type_t.cabinet, Product.color_t.white, "C6", "Antalya", 1);
        
    }

    /** Creates a new admin
    @param name,surname,id takes 2 string and 1 int
    */
    public void createAdmin(String name, String surname, int id){
        db.createAdmin(name, surname, id);

    }


    /** Gets branch list of automation
    @return returns branch list
    */
    public KWLinkedList<Branch> getBranch(){
        return db.getBranch();
    }

    /** Creates a new branch
    @param name
    */
    public void createBranch(String name){
        db.getBranch().add(new Branch(name));
    }

    public void deleteBranch(String name){
        int branchIndex = db.getIndexOfBranch(name);
        db.getBranch().remove(branchIndex);
    }

    /** Deletes the branch found at indexed data of branch list
    @param index
    */
    public void deleteBranchByIndex(int index){
        db.getBranch().remove(index);
    }

    /** get branch employee found at indexed data of branchEmployee list
    @param id
    @return returns branchEmployee
    */
    public BranchEmployee getBranchEmployee(int id){
        return db.getBranchEmployee(id);
    }

    /** Add branch employee to the list with branch name
    @param name,surname,id,branchName
    @return returns true 
    */
    public boolean addBranchEmployee(String name, String surname, int id, String branchName){
        int branchIndex = db.getIndexOfBranch(branchName);
        return db.getBranch().get(branchIndex).getBranchEmployee().add(new BranchEmployee(name, surname, id, db.getBranch().get(branchIndex)));
    }

    /** Add branch employee to the list with branch index
    @param name,surname,id,branchIndex
    @return returns true 
    */
    public boolean addBranchEmployee(String name, String surname, int id, int branchIndex){
        return db.getBranch().get(branchIndex).getBranchEmployee().add(new BranchEmployee(name, surname, id, db.getBranch().get(branchIndex)));
    }


    /** remove branch employee using branch name
    @param name,surname,id,branchName
    */
    public void removeBranchEmployee(String name, String surname, int id, String branchName){
        int branchIndex = db.getIndexOfBranch(branchName);
        int index = db.getBranchEmployeeIndex(id, branchIndex);
        if(index != -1)
            db.getBranch().get(branchIndex).getBranchEmployee().remove(index);
    }

    /** remove branch employee using branch index
    @param name,surname,id,branchIndex
    */
    public void removeBranchEmployee(int employeeId, int branchIndex){
        int index = db.getBranchEmployeeIndex(employeeId, branchIndex);
        if(index != -1)
            db.getBranch().get(branchIndex).getBranchEmployee().remove(index);
    }

    public void printBranchNames(){
        Adminstrator.db.printBranchNames();
    }

    public void printBranchEmployee(){
        db.printBranchEmployee();
    }
    
    public void addCustomerToSystem(Customer customer){
        db.getCustomer().add(customer);   
    }

    /** Add product to the products' list
    @param type,color,model,branchName
    */
    public void addProduct(Product.type_t type, Product.color_t color, String model, String branchName){
        int branchIndex = db.getIndexOfBranch(branchName);

        db.getBranch().get(branchIndex).getProducts().addFurniture(new Product(type, color, model));
    }

    /** Erase product using branch name
    @param type,color,model,branchName 
    */
    public void eraseProduct(Product.type_t type, Product.color_t color, String model, String branchName){
        db.eraseProduct(type, color, model, branchName);
    }

    public void showProducts(){
        db.showProducts();
    }


}

