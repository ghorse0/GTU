package com.automation;

public class Customer extends Person{

    private String mail;
    private String pw;
    private HybridList currentOrders;

    public Customer(String  name, String surname, String mail, String pw, int id){
        super(name,surname,id);
        this.mail = mail;
        this.pw = pw;
        currentOrders = new HybridList();
        currentOrders.addNode(new KWArrayList<Product>());

    }

    public String getMail(){
        return mail;
    }
    public String getPw(){
        return pw;
    }

    public HybridList getOrder(){
        return currentOrders;
    }

    public void addOrder(Product product){
        currentOrders.addFurniture(product);
    }
    
    public void buyProduct(Product.type_t type, Product.color_t color, String model){
        currentOrders.addFurniture(new Product(type, color, model));
    }

    public void viewOrder(){
        for(int i = 0 ; i < currentOrders.size() ; ++i){
            Product temp = currentOrders.getData(i);
            System.out.println(temp.toString());
        }
    }

    public String toString(){
        String str = getId() + " - " + getName() + " " + getSurname() + " " + getMail();

        return str;
    }
    

}