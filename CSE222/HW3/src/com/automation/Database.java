package com.automation;


public class Database{
    private KWArrayList<Adminstrator> admins;
    private KWLinkedList<Branch> branch;
    private KWArrayList<BranchEmployee> employee;
    private KWArrayList<Customer> customer;

    public Database(){
        admins = new KWArrayList<Adminstrator>();
        branch = new KWLinkedList<Branch>();
        employee = new KWArrayList<BranchEmployee>();
        customer = new KWArrayList<Customer>();
    }

    /** Create admin
    @param name,surname,id 
    */
    public void createAdmin(String name, String surname, int id){
        admins.add(new Adminstrator(name, surname, id));
    } 

    public Adminstrator getAdmin(int id){
        return admins.get(id);
    }
    
    public void createBranch(String name){
        getBranch().add(new Branch(name));
    }

    public void deleteBranch(String name){
        int branchIndex = getIndexOfBranch(name);
        getBranch().remove(branchIndex);
    }

    public void addCustomer(Customer customer){
        this.customer.add(customer);
    }

    public void addBranchEmployee(String name, String surname, int id, String branchName){
        int branchIndex = getIndexOfBranch(branchName);
        getBranch().get(branchIndex).getBranchEmployee().add(new BranchEmployee(name, surname, id, getBranch().get(branchIndex)));
    }

    /** add product to the product list
    @param type,color,model,branchName,stockNumber
    */
    public void addProduct(Product.type_t type, Product.color_t color, String model, String branchName, int stockNumber){
        int branchIndex = getIndexOfBranch(branchName);
        int productIndex;
        if(searchProduct(type,color,model,branchName) == -1)
            getBranch().get(branchIndex).getProducts().addFurniture(new Product(type, color, model));
        productIndex = searchProduct(type,color,model,branchName);
        getBranch().get(branchIndex).getProductByIndex(productIndex).increaseStock(stockNumber);

    }

    public void eraseProduct(Product.type_t type, Product.color_t color, String model, String branchName){
        int branchIndex = getIndexOfBranch(branchName);
        int productIndex = searchProduct(type, color, model, branchName);
        if(productIndex != -1)
            getBranch().get(branchIndex).getProducts().remove(productIndex);
    }

    public void decreaseProduct(Product.type_t type, Product.color_t color, String model, String branchName){
        int branchIndex = getIndexOfBranch(branchName);
        int productIndex = searchProduct(type, color, model, branchName);
        if(productIndex != -1)
            getBranch().get(branchIndex).getProducts().getData(productIndex).decreaseStock(1);
    }

    public void showProducts(){
        for(int i = 0 ; i < getBranch().size() ; ++i){
            Branch temp = getBranch().get(i);

            for(int j = 0 ; j < temp.getProducts().size() ; ++j){
                Product tempProduct = temp.getProducts().getData(j);
                if(tempProduct.checkStock())
                System.out.println(tempProduct.toString());
            }
        }
    }
    
    public BranchEmployee getBranchEmployee(int id){

        for(int i = 0 ; i < getBranch().size() ; ++i){
            Branch temp = getBranch().get(i);
            for(int j = 0 ; j < temp.getBranchEmployee().size() ; j++){
                BranchEmployee tempEmployee = temp.getBranchEmployee().get(j);
                if(tempEmployee.getId() == id) return tempEmployee;
            }
        }

        return (new BranchEmployee("unknown", "unknown", -1, null));
    }


    public KWLinkedList<Branch> getBranch(){
        return branch;
    }

    public KWArrayList<BranchEmployee> getEmployee(){
        return employee;
    }

    public KWArrayList<Customer> getCustomer(){
        return customer;
    }

    public int getIndexOfBranch(String name){
        int index = -1;

        for(int i = 0 ; i < branch.size(); ++i){
            Branch temp = branch.get(i);
            
            if((temp.getName()).equals(name)){
                return i;
            }
        }
        return index;
    }

    public void printBranchNames(){
        for(int i = 0 ; i < branch.size() ; ++i){
            Branch temp = branch.get(i);
            System.out.println(i + " - " + temp.getName());
        }
    }

    public void printBranchEmployee(){
        for(int i = 0 ; i < branch.size() ; ++i){
            Branch temp = branch.get(i);
            for(int j = 0 ; j < temp.getBranchEmployee().size() ; ++j)
                System.out.println(temp.getBranchEmployee().get(j).toString() + " "+ temp.getName());
        }
    }


    /** Search product using branchName
    @param type,color,model,branchName
    @return if successful product index else -1
    */
    public int searchProduct(Product.type_t type, Product.color_t color, String model, String branchName){
        int branchIndex = getIndexOfBranch(branchName);
        for(int i = 0 ; i < branch.get(branchIndex).getProducts().size() ; ++i){
            Product temp = branch.get(branchIndex).getProducts().getData(i);
            if(temp.getEnumType() == type && temp.getEnumColor() == color && temp.getModel().equals(model)) return i;
        }
        return -1;
    }

    /** Search product using only product information
    @param type,color,model
    @return if successful branch index else -1
    */
    public int searchProduct(Product.type_t type, Product.color_t color, String model){
        for(int j = 0 ; j < branch.size() ; ++j){
            Branch tempBranch = branch.get(j);
            for(int i = 0 ; i < tempBranch.getProducts().size() ; ++i){
                Product temp = tempBranch.getProducts().getData(i);
                if(temp.getEnumType() == type && temp.getEnumColor() == color && temp.getModel().equals(model)) return j;
            }
        }
        return -1;
    }

    public boolean searchAdmin(int id){
        for(int i = 0 ; i < admins.size(); ++i){
            Adminstrator temp = admins.get(i);
            if(temp.getId() == id) return true;
        }
        return false;
    }
    

    /**
     * @param id of employee
     * @return if found returns branch index else returns -1
     */
    public int searchBranchEmployee(int id){
        
        for(int j = 0 ; j < branch.size() ; ++j){
            Branch tempBranch = branch.get(j);
            for(int i = 0 ; i < tempBranch.getBranchEmployee().size(); ++i){
                BranchEmployee temp = tempBranch.getBranchEmployee().get(i);
                if(temp.getId() == id) return j;
            }
        }
        return -1;
    }

    /**
     * @param id of employee
     * @return if found returns employee index else returns -1
     */

    public int getBranchEmployeeIndex(int id){
        
        for(int j = 0 ; j < branch.size() ; ++j){
            Branch tempBranch = branch.get(j);
            for(int i = 0 ; i < tempBranch.getBranchEmployee().size(); ++i){
                BranchEmployee temp = tempBranch.getBranchEmployee().get(i);
                if(temp.getId() == id) return i;
            }
        }
        return -1;
    }

    /**
     * @param Employeeid,branchId
     * @return if found returns employee index else returns -1
     */

    public int getBranchEmployeeIndex(int id, int branchId){
        Branch tempBranch = branch.get(branchId);
        for(int i = 0 ; i < tempBranch.getBranchEmployee().size(); ++i){
            BranchEmployee temp = tempBranch.getBranchEmployee().get(i);
            if(temp.getId() == id) return i;
        }
        return -1;
    }


    /**
     * Print all the products in the system
     * 
     */

    public void printAllProduct(){
        for(int j = 0 ; j < branch.size() ; ++j){
            Branch tempBranch = branch.get(j);
            for(int i = 0 ; i < tempBranch.getProducts().size() ; ++i){
                Product tempProduct = tempBranch.getProducts().getData(i);
                if(tempProduct.checkStock())
                    System.out.printf("%s - %s\n", tempProduct.toString(), tempBranch.getName());
            }
        }
    }

    public int getLastCustomerId(){
        if(customer.size() == 0) return -1;
        Customer temp = customer.get(customer.size()-1);
        return temp.getId();
    }

    public int getLastEmployeeId(){
        if(branch.size() == 0) return -1;
        int max = -1;

        for(int i = 0 ; i < branch.size() ; ++i){
            Branch tempBranch = branch.get(i);
            for(int j = 0 ; j < tempBranch.getBranchEmployee().size() ; ++j){
                BranchEmployee tempEmployee = tempBranch.getBranchEmployee().get(j);
                if(max < tempEmployee.getId()) max = tempEmployee.getId();
            }
        }
        return max;
    }
    public int getLastAdminId(){
        if(branch.size() == 0) return -1;
        int max = -1;

        for(int i = 0 ; i < admins.size() ; ++i){
            if(max < admins.get(i).getId()) max = admins.get(i).getId();
        }
        return max;
    }


    /** Validate user using mail and password
     * @param mail,pw
     * @return if successful returns customer id else -1
     */

    public int validateUser(String mail, String pw){

        for(int i = 0 ; i < customer.size() ; ++i){
            Customer temp = customer.get(i);
            if(mail.equals(temp.getMail()) && pw.equals(temp.getPw())) return i;
        }
        return -1;
    }

    /** Checks if the mail already exists in system
     * @param mail
     * @return if duplicate exists returns true else false
     */
    public boolean duplicateMail(String mail){
        for(int i = 0 ; i <  customer.size() ; ++i){
            Customer temp = customer.get(i);
            if(mail.equals(temp.getMail())) return true;
        }
    return false;
    }

}
