import java.security.InvalidParameterException;

public class Main{



    public static void main(String[] args){
        GTUSet<Integer> set1 = new GTUSet<Integer>(Integer[].class);
        GTUIterator<Integer> setIter = set1.iterator();
        GTUVector<Integer> v1 = new GTUVector<Integer>(Integer[].class);
        GTUIterator<Integer> vIter = v1.iterator();
        System.out.println("Set tests");
        set1.insert(1);
        set1.insert(2);
        set1.insert(5);
        set1.insert(7);
        set1.insert(83);
        set1.insert(33);
        set1.insert(43);
        System.out.println("Set contents :");
        while(setIter.hasNext()){
            System.out.printf("%d ",setIter.next());
        }
        System.out.printf("\n");
        System.out.println("Erasing 1,2,83");
        set1.erase(1);
        System.out.println("After erasing 1 : ");
        setIter = set1.iterator();
        while(setIter.hasNext()){
            System.out.printf("%d ",setIter.next());
        }
        System.out.printf("\n");
        set1.erase(2);
        System.out.println("After erasing 2 : ");
        setIter = set1.iterator();
        while(setIter.hasNext()){
            System.out.printf("%d ",setIter.next());
        }
        System.out.printf("\n");
        set1.erase(83);
        System.out.println("After erasing 83 : ");
        setIter = set1.iterator();
        while(setIter.hasNext()){
            System.out.printf("%d ",setIter.next());
        }
        System.out.printf("\n");
        System.out.printf("size = %d\n",set1.size());
        System.out.printf("max size = %d\n",set1.max_size());
        System.out.printf("Calling contains(7): %b\n",set1.contains(7));
        System.out.println("Calling clear()");
        set1.clear();
        setIter = set1.iterator();
        System.out.println("Contents of set : ");
        while(setIter.hasNext()){
            System.out.printf("%d ",setIter.next());
        }
        System.out.printf("\n");

        System.out.println("Vector tests");
        v1.insert(1);
        v1.insert(2);
        v1.insert(5);
        v1.insert(7);
        v1.insert(83);
        v1.insert(33);
        v1.insert(43);
        System.out.println("Vector contents :");
        while(vIter.hasNext()){
            System.out.printf("%d ",vIter.next());
        }
        System.out.printf("\n");
        System.out.println("Erasing 1,2,83");
        v1.erase(1);
        System.out.println("After erasing 1 : ");
        vIter = v1.iterator();
        while(vIter.hasNext()){
            System.out.printf("%d ",vIter.next());
        }
        System.out.printf("\n");
        v1.erase(2);
        System.out.println("After erasing 2 : ");
        vIter = v1.iterator();
        while(vIter.hasNext()){
            System.out.printf("%d ",vIter.next());
        }
        System.out.printf("\n");
        v1.erase(83);
        System.out.println("After erasing 83 : ");
        vIter = v1.iterator();
        while(vIter.hasNext()){
            System.out.printf("%d ",vIter.next());
        }
        System.out.printf("\n");
        System.out.printf("size = %d\n",v1.size());
        System.out.printf("max size = %d\n",v1.max_size());
        System.out.printf("Calling contains(7): %b\n",v1.contains(7));
        System.out.println("Calling clear()");
        v1.clear();
        vIter = v1.iterator();
        System.out.println("Contents of vector : ");
        while(vIter.hasNext()){
            System.out.printf("%d ",vIter.next());
        }
        System.out.printf("\n");



        
    }
}