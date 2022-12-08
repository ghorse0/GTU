package com.automation;

public class BranchEmployee extends Person {
    private final Branch branch;

    public BranchEmployee(String name, String surname, int id, Branch branch){
        super(name, surname, id);
        this.branch = branch;
    }

    public Branch getBranch(){
        return branch;
    }



    public void addProduct(Product.type_t type, Product.color_t color, String model, int stockNumber){
        branch.insertProduct(type, color, model, stockNumber);
    }

    /** increase product stock with product index
    @param index,stockNumber
    */
    public void increaseProduct(int index, int stockNumber){
        branch.getProductByIndex(index).increaseStock(stockNumber);
    }

    /** Calls branch's remove product
    @param type,color,model
    @return true 
    */

    public boolean removeProduct(Product.type_t type, Product.color_t color, String model){
        return branch.removeProduct(type, color, model);
    }

    public boolean removeProductByIndex(int index){
        return branch.removeProductByIndex(index);
    }

    public void lookUpCustomerOrders(int customerID){
        Customer temp = branch.getCustomerById(customerID);

        temp.viewOrder();
    }

    /** Add order to a customer's order list
    @param customerId,product
    */
    public void updateOrder(int id, Product product){
        branch.getCustomerById(id).addOrder(product);
    }


    public void printProducts(){
        branch.printProducts();
    }
    
    public void printCustomer(){
        branch.printCustomer();
    }

    public void addCustomer(String name, String surname, String mail, String pw){
        int i = Adminstrator.db.getLastCustomerId();
        Adminstrator.db.addCustomer(new Customer(name,surname,mail,pw,i+1));
        branch.getCustomerList().add(new Customer(name,surname,mail,pw,i+1));
    }

    public void sellProduct(int productIndex, int customerId, int branchIndex){
        Product tempProduct = branch.getProductByIndex(productIndex);
        Adminstrator.db.getCustomer().get(customerId).addOrder(tempProduct);
        branch.getProducts().getProduct(productIndex).decreaseStock(1);
    }

    @Override
    public String toString(){
        String str = getId() + " - " + getName() + " " + getSurname();

        return str;
    }
}