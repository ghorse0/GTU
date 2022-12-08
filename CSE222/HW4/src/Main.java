import java.util.Random;

public class Main {
    public static void main(String[] args) {
        BSTHeapTree<Integer> bst = new BSTHeapTree<Integer>();
        Random rand =  new Random();

        int[] arr = new int[3000];

        for(int i = 0 ; i < 3000 ; ++i){
            arr[i] = rand.nextInt(5000) + 1;
            bst.add(arr[i]);
        }
        for(int i = 0 ; i < 2999 ; ++i){
            for(int j = 0 ; j < 2999 - i ; ++j){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.printf("Printing first 100 elements of sorted array\n");
        for(int i = 0 ; i < 100 ; ++i){
            System.out.printf("%d ", arr[i]);
        }


        System.out.printf("\n");
        for(int i = 0 ; i < 100 ; ++i){
            System.out.printf("Occurence of %d = %d\n",arr[i], bst.find(arr[i]));
        }
        int j = 0;
        for(int i = 0 ; i < 5000 ; ++i){
            if(bst.find(i) == 0){
                System.out.printf("Trying to find %d, occurence = %d\n", i, bst.find(i));
                ++j;
            }
            if(j == 10) break;
        }

        System.out.printf("\n");
        for(int i = 0 ; i < 20 ; ++i){
            System.out.printf("Occurence of %d = %d\n", i, bst.find(i));
        }      
        System.out.printf("\n");

        System.out.println("------Remove test----");

        System.out.printf("Printing first 100 elements before remove\n");
        for(int i = 0 ; i < 100 ; ++i){
            System.out.printf("%d ", arr[i]);
            if(i == 50 || i == 99) System.out.printf("\n");
        }

        j = 0;
        for(int i = 0 ; i < 5000 ; ++i){
            if(bst.find(i) == 0){
                System.out.printf("Trying to remove %d, current occurence = %d, ", i, bst.find(i));
                bst.delete(i);
                System.out.printf("After removing, occurence = %d\n", bst.find(i));
                j++;
            }
            if(j == 10) break;
        }

        
        for(int i = 0 ; i < 100 ; ++i){
            System.out.printf("Removing %d, current occurence = %d, ",arr[i], bst.find(arr[i]));
            bst.delete(arr[i]);
            System.out.printf("After removing, occurence = %d\n", bst.find(arr[i]));
        }

        System.out.println("-----Finding mode of tree-----");
        System.out.printf("Mode of tree = %d\n", bst.find_mode());
        j = 1;
        int count = 1, max = 0, index = -1;
        max = bst.find_mode();
        for(int i = 0 ; i < 2999 ; ++i){
            if(arr[i] == arr[i+1]) count++;
            else if(arr[i] != arr[i+1]){
                if(count == max){
                    index = i;
                    break;
                }
                count = 1;
            }
        }
        System.out.printf("Mode frequency is : %d\n", bst.find_mode_freq());
    }

}
