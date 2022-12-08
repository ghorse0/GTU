package com.automation;

public class Branch {
    private String name;
    private KWArrayList<BranchEmployee> branchEmp;
    private HybridList products;
    private KWArrayList<Customer> visitedCustomer;

    public Branch(String name){
        this.name = name;
        branchEmp = new KWArrayList<BranchEmployee>();
        products = new HybridList();
        products.addNode(new KWArrayList<Product>());
        visitedCustomer = new KWArrayList<Customer>();
    }

    public String getName(){
        return this.name;
    }


    public KWArrayList<BranchEmployee> getBranchEmployee(){
        return branchEmp;
    }

    public HybridList getProducts(){
        return products;
    }

    public KWArrayList<Customer> getCustomerList(){
        return visitedCustomer;
    }


    public Customer getCustomerById(int id){
        int index = -1;

        for(int i = 0 ; i < visitedCustomer.size() ; ++i){
            Customer temp = visitedCustomer.get(i);
            if(temp.getId() == id) index = i;
        }

        if(index != -1) return visitedCustomer.get(index);
        else return (new Customer("unknown", "unknown", "unknown", "unknown", -1));
    }


    /** get product with index
    @param index
    @return returns Product 
    */
    public Product getProductByIndex(int index){
        int nodeIndex = index / 100;
        if(index > products.size()) return null;
        return products.getProduct(index, nodeIndex);
    }

    public void insertProduct(Product.type_t type, Product.color_t color, String model, int stockNumber){
        Product temp = new Product(type,color,model);
        temp.increaseStock(stockNumber);
        products.addFurniture(temp);
        temp = null;
    }

    public boolean removeProduct(Product.type_t type, Product.color_t color, String model){
        Product temp = new Product(type,color,model);
        int index = getProducts().getIndexByData(temp);
        temp = null;
        return products.remove(index);
    }

    public boolean removeProductByIndex(int index){
        return getProducts().remove(index);
    }

    /** Adds customer to the visited list of branch
    @param customer
    */
    public boolean subscribeCustomer(Customer customer){
        boolean check = false;
        if(getCustomerById(customer.getId()).getId() == -1){
            visitedCustomer.add(customer);
            check = true;
        }
        return check;
    }

    public BranchEmployee getEmployeeById(int id){
        int index = -1;

        for(int i = 0 ; i < branchEmp.size() ; ++i){
            BranchEmployee temp = branchEmp.get(i);
            if(temp.getId() == id) index = i;
        }

        if(index != -1) return branchEmp.get(index);
        else return (new BranchEmployee("unknown", "unknown", -1, null));
    }

    public void printBranchEmployee(){
        for(int i = 0 ; i < branchEmp.size() ; ++i){
            BranchEmployee temp = branchEmp.get(i);
            System.out.println(temp.toString());
        }
    }
    
    public void printProducts(){
        for(int i = 0 ; i < products.size() ; ++i){
            if(products.getProduct(i).checkStock())
                System.out.println(i + " - " + products.getProduct(i).toString());
        }
    }

    public void printCustomer(){
        for(int i = 0 ; i < visitedCustomer.size() ; ++i){
            System.out.println(visitedCustomer.get(i).toString());
        }
    }


    

}
