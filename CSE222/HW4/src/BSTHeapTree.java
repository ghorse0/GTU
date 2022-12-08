public class BSTHeapTree<E extends Comparable<E>> extends HeapTree<E> {
    
    protected boolean addReturn;
    protected E deleteReturn;


    /**
     * Find function, takes generic object
     * @param item
     * @return occurence of the number, if it's not in tree returns 0
     */
    public int find(E item){
        return find(root, item);
    }
    
    /**
     * Recursive find function
     * @param localRoot
     * @param target
     * @return occurence of the wanted number
     */
    private int find(Node<E> localRoot, E target){
        if(localRoot == null) return 0;

        int compResult = target.compareTo(localRoot.data());
        if(compResult == 0) return localRoot.getOccurence(target);

        else if(compResult < 0){
            int index = localRoot.dataHeap.getIndex(target);
            if(index != -1){
                return localRoot.getOccurenceIndex(index);
            }
            return find(localRoot.left, target);
        }
        
        int index = localRoot.dataHeap.getIndex(target);
        if(index != -1){
            return localRoot.getOccurenceIndex(index);
        }
        return find(localRoot.right, target);
    }

    /**
     * Add function of the bst tree
     * @param item
     * @return occurence of the item after it is added to the tree
     */
    public int add(E item){
        root = add(root, item);
        return root.getOccurence(item);
    }

    /**
     * Recursive add function-Taken from textbook and modified-
     * @param localRoot
     * @param item
     * @return modified local root
     */
    private Node<E> add(Node <E> localRoot, E item){
        if(localRoot == null){
            addReturn = true;
            return new Node<E>(item);
        }
        else if(item.compareTo(root.data()) == 0){
            addReturn = false;
            localRoot.insert(item);
        }
        else if(item.compareTo(root.data()) < 0){
            boolean flag = localRoot.dataHeap.contains(item);
            if(flag){
                localRoot.dataHeap.insert(item);
            }

            else if(localRoot.dataHeap.size() < 7){
                localRoot.dataHeap.insert(item);
            }
            else{
                localRoot.left = add(localRoot.left, item);
            }
        }
        else {
            if(localRoot.dataHeap.size() < 7){
                localRoot.dataHeap.insert(item);
            }
            // item is greater than localRoot.data
            else
                localRoot.right = add(localRoot.right, item);
            
        }

        return localRoot;
    }




    //Chapter 6 of textbook, delete methods -Taken from textbook and modified-

    /**
     * Delete method takes generic object
     * 
     * @param target 
     * @return The object deleted from the tree
     *         or null if the object was not in the tree
     * 
     */
    public int delete(E target) {
        root = delete(root, target);
        return root.getOccurence(target);
    }

    /**
     * Recursive delete method.
     * @param localRoot The root of the current subtree
     * @param item
     * @return The modified local root that does not contain
     *         the item
     */
    private Node<E> delete(Node<E> localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree.
            deleteReturn = null;
            return localRoot;
        }

        int compResult = item.compareTo(localRoot.data());
        if (compResult < 0) {
            boolean check = localRoot.dataHeap.contains(item);
            if(check){
                int occurence = localRoot.getOccurence(item);
                if(occurence >= 1){
                    //int index = localRoot.dataHeap.getIndex(item);
                    localRoot.dataHeap.remove(item);
                }
            }
            else{
                localRoot.left = delete(localRoot.left, item);
                return localRoot;
            }
            
        } if (compResult > 0 || localRoot.dataHeap.size() == 0) {
            localRoot.right = delete(localRoot.right, item);
            return localRoot;
        } else if(compResult == 0){
            if(localRoot.dataHeap.size() != 0){
                localRoot.dataHeap.remove(item);
                if(localRoot.dataHeap.size() != 0)
                    return localRoot;
            }

            deleteReturn = localRoot.data();
            if (localRoot.left == null) {
                return localRoot.right;
            } else if (localRoot.right == null) {
                return localRoot.left;
            } else {
                if (localRoot.left.right == null) {
                    localRoot.dataHeap = localRoot.left.dataHeap;
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                } else {
                    localRoot.dataHeap = findLargestChild(localRoot.left);
                    return localRoot;
                }
            }
        }
        return localRoot;
    }

    private BSTHeap<E> findLargestChild(Node<E> parent) {
        // If the right child has no right child, it is
        // the inorder predecessor.
        if (parent.right.right == null) {
            BSTHeap<E> returnValue = parent.right.dataHeap;
            parent.right = parent.right.left;
            return returnValue;
        } else {
            return findLargestChild(parent.right);
        }
    }
 
    /**
     * Finds mode of the tree
     * @return mode of the tree
     */
    public int find_mode_freq(){
            if(root.dataHeap.size() == 0) {
                System.out.println("Tree is empty.");
                return -1;
            }
        return find_mode(root);
    }

    public E find_mode(){
        if(root.dataHeap.size() == 0){
            return null;
        }
        int freq = find_mode_freq();
        return getModeOfTree(root, freq);
    }
    /**
     * Recursive find_mode function
     * @param localRoot
     * @return mode of the tree
     */
    private int find_mode(Node<E> localRoot){


        if(localRoot == null) return -1;
        int res = localRoot.getMaxOccurence();
        int maxLeft = find_mode(localRoot.left);
        int maxRight = find_mode(localRoot.right);

        if(maxLeft > res){
            res = maxLeft;
        }

        if(maxRight > res){
            res = maxRight;
        }

        return res;


    }

    /**
     * Gets max occurence of a node
     * @param node
     * @return mode of the given node
     */
    public int getMaxOccurence(Node<E> node){
        return node.getMaxOccurence();
    }

    public E getModeOfHeap(Node<E> node){
        return node.getMaxOccuranceData();
    }

    public E getModeOfTree(Node<E> localRoot, int occurenceVal){

        if(localRoot == null) return null;
        E res = localRoot.getMaxOccuranceData();
        getModeOfTree(localRoot.left, occurenceVal);
        getModeOfTree(localRoot.right, occurenceVal);

        if(localRoot.getMaxOccurence() == occurenceVal){
            res = localRoot.getMaxOccuranceData();
            return res;
        }

        return res;
    }

    public void printHeap(){
        root.print();
    }

}
