public class HeapTree<E extends Comparable<E>>{

    protected static class Node<E extends Comparable<E>> {
        public BSTHeap<E> dataHeap;
        public Node<E> left;
        public Node<E> right;

        public Node(){
            dataHeap = new BSTHeap<>();
        }

        public Node(E data){
            dataHeap = new BSTHeap<>();
            dataHeap.insert(data);
        }

        public void insert(E data){
            dataHeap.insert(data);
        }
    
        public E remove(){
            if(dataHeap.getOccurenceByIndex(0) == 0){
                dataHeap.occurenceDec(0);
                //Occurence values are now different
                return dataHeap.remove();
            }
            dataHeap.occurenceDec(0);
            return dataHeap.get(0);
    
        }
        
        public int getOccurenceIndex(int index){
            return dataHeap.getOccurenceByIndex(index);
        }

        public int getOccurence(E data){
            return dataHeap.getOccurence(data);
        }

        public int getMaxOccurence(){
            return dataHeap.getMaxOccurence();
        }

        public E getMaxOccuranceData(){
            return dataHeap.getMaxOccurenceData();
        }


        public E data(){
            if(dataHeap.size() == 0)
                throw new NullPointerException("Heap is empty.");
            
            
            return dataHeap.get(0);


        }

        public void print(){
            dataHeap.print();
        }

        @Override
        public String toString(){
            return dataHeap.get(0).toString();
        }
        
    
    }

    protected Node<E> root;

    public HeapTree(){
        root = null;
    }

        /**
     * Constructs a new binary tree with data in its root,leftTree
     * as its left subtree and rightTree as its right subtree.
     */
    public HeapTree(E data, HeapTree<E> leftTree,
            HeapTree<E> rightTree) {
        root = new Node<E>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    protected HeapTree(Node<E> root){
        this.root = root;
    }

    public E getData(){
        if(root != null)
            return root.dataHeap.get(0);
        return null;
    }


    public boolean isLeaf(){
        return (root == null || (root.left == null && root.right == null));
    }
    
    public HeapTree<E> getLeftSubtree(){
        if(root != null && root.left != null)
            return new HeapTree<E>(root.left);
        return null;
    }

    public HeapTree<E> getRightSubtree(){
        if(root != null && root.right != null)
            return new HeapTree<E>(root.right);
        return null;
    }

    private void preOrderTraverse(Node<E> node, int depth,
    StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if (node == null) {
            sb.append("null\n");
        } 
        else{
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
   
    }   
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    public void printHeap(){
        root.print();
    }
    
}
