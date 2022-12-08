package com.automation;

public class HybridList {

    KWLinkedList<KWArrayList<Product>> hybridData;
    int size;
    private int currentIndex;
    private static final int MAX_NUMBER = 100;
    HybridList(){
        hybridData = new KWLinkedList<KWArrayList<Product>>();
        size = 0;
        currentIndex = 0;
    }

    public void addNode(KWArrayList<Product> item){
        hybridData.add(item);
        ++size;
    }

    public void addNode(){
        KWArrayList<Product> temp = new KWArrayList<Product>();
        hybridData.add(temp);
        ++size;
    }

    public boolean addFurniture(Product item){
        if(hybridData.get(currentIndex).size() == MAX_NUMBER) {
            currentIndex++;
            addNode();
        }
        return hybridData.get(currentIndex).add(item);
    }

    public Product getProduct(int index){
        int nodeIndex = index / MAX_NUMBER;
        int temp = index;
        if(nodeIndex > 0){
            temp = index - MAX_NUMBER * nodeIndex;
        }
        return hybridData.get(0).get(temp);
    }

    public Product getProduct(int index, int nodeIndex){
        return hybridData.get(nodeIndex).get(index);
    }
    public KWArrayList<Product> getNode(int nodeIndex){
        return hybridData.get(nodeIndex);
    }

    public Product removeProduct(int index, int nodeIndex){
        Product temp;
        int tempIndex = index - nodeIndex * MAX_NUMBER;
        temp = hybridData.get(nodeIndex).remove(tempIndex);
        if(hybridData.get(nodeIndex).size() == 0){
            hybridData.remove(nodeIndex);
            size--;
        }
        return temp;
    }

    public void decreaseProduct(int index, int nodeIndex, int no){
        hybridData.get(nodeIndex).get(index).decreaseStock(no);
        if(hybridData.get(nodeIndex).size() == 0){
            hybridData.remove(nodeIndex);
        }
    }

    public int size(){
        int temp = 0;
        for(int i = 0 ; i < hybridData.size() ; ++i){
            temp += hybridData.get(i).size();
        }

        return temp;
    }


    public int getIndexByData(Product product){
        int index = 0;
        for(int i = 0 ; i < hybridData.size() ; ++i){
            KWArrayList<Product> temp = hybridData.get(i);
            for(int j = 0 ; j < temp.size() ; ++j){
                Product tempProduct = temp.get(j);
                if(tempProduct.equals(product)) return index;
                index++;
            }
        }
        return -1;
    }

    public boolean remove(int index){
        int nodeIndex = index / MAX_NUMBER;
    
        removeProduct(index, nodeIndex);
        return true;
    }

    public Product getData(int index){
        return getProduct(index);
    }

    public int getMax(){
        return MAX_NUMBER;
    }

}
