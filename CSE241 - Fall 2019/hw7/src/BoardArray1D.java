import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class BoardArray1D extends AbstractBoard {
    private int[] arr;
    public BoardArray1D(){
        arr = new int[width * height];
        FillBoard();
    }
    public BoardArray1D(int newWidth , int newHeight){
        super(newWidth , newHeight);
        arr = new int[width * height];
        FillBoard();
    }
    public BoardArray1D(BoardArray1D other){
        super(other.width , other.height);
        arr = new int[other.width * other.height];
        for(int i = 0 ; i < (width * height) ; ++i)
            arr[i] = other.arr[i];
        
        FillBoard();
    }
    @Override
    public int cell(int col , int row){
        if(col >= height || row >= width){
            System.out.println("Indexes are not valid,please enter indexes as cell(y,x),Terminating the program...");
            System.exit(0);
        }
        return arr[width * col + row];
    }
    @Override
    public void reset(){
        for(int j = 0 ; j < (width * height)-1 ; j++)
            arr[j] = j + 1;

        arr[ (width * height) - 1] = -1;
    }
    
    /**
     * Calling reset() in this function to fill the board.
     */
    public void FillBoard(){
        reset();
    }
    public int getPosOfBlank(){
        int pos_of_blank = -1,i;
        for(i = 0 ; i < (width*height) ; ++i){
            if( arr[i] == -1 ){
                pos_of_blank = i;
            }
        }
        return pos_of_blank;
    }

    @Override
    public boolean move(char direction){
        int posBlank = getPosOfBlank();
        int x = getX(posBlank);
        int y = getY(posBlank);
        boolean isDone = false;

        if(direction == 'R' || direction == 'r'){
            if((x+1) % width != 0 &&  (arr[posBlank+1] != 0) ){
                swap(arr,posBlank,posBlank+1);
                posBlank += 1;
                isDone = true;
            }
        }
        if(direction == 'L' || direction == 'l'){
            if(x > 0 && arr[posBlank-1] != 0){		
                swap(arr,posBlank-1,posBlank);
                posBlank -= 1;
                isDone = true;
                }
            }
        else if(direction == 'D' || direction == 'd'){
            if( (y+1) % height != 0 && arr[posBlank+width] != 0 ){
                swap(arr,posBlank+width,posBlank);
                posBlank += width;
                isDone = true;
                }
            }
        else if(direction == 'U' || direction == 'u'){
            if(y > 0 && arr[posBlank-width] != 0){
                swap(arr,posBlank-width,posBlank);
                posBlank -= width;
                isDone = true;
                }
            }
        if(isDone == true)
        {
            lastmove = direction;
            ++countMoves;
        }


        return isDone;
    }

    public int getX(int index){
        return index % width;
    }
    public int getY(int index){
        return index / width;
    }

    @Override
    public void readFromFile(String fileName){
        File file = new File(fileName);
        
        int j = 0, i = 0;
        int posBlank = 0;
        int newHeight = 0;
        int newWidth = 0;
        int totalNumbers = 0;
        String test1 = "";
        String line = "";
        try(Scanner scanner = new Scanner(file , StandardCharsets.UTF_8.name())){
           
           while(scanner.hasNextLine()){
            line = scanner.nextLine();
               ++newHeight;
            }

             scanner.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
         try(Scanner scanner = new Scanner(file , StandardCharsets.UTF_8.name())){
            System.out.printf("\n");
            while(scanner.hasNext()){

                test1 = scanner.next();
                ++totalNumbers;
                ++j;
                if(test1.equals("bb"))
                    posBlank = j - 1;  
            }
            
              scanner.close();
         }
         catch(FileNotFoundException e){
             e.printStackTrace();
         }
         newWidth = totalNumbers / newHeight;
         width = newWidth;
         height = newHeight;
         arr = new int[newWidth * newHeight];
 
         try(Scanner scanner = new Scanner(file , StandardCharsets.UTF_8.name())){
            arr[posBlank] = -1;
            while(scanner.hasNext()){
                test1 = scanner.next();
                if(!test1.equals("bb"))
                    j = Integer.parseInt(test1);

                if(i == posBlank)
                    ++i;
                else
                    arr[i++] = j;
            }
              scanner.close();
         }
         catch(FileNotFoundException e){
             e.printStackTrace();
         }
     
         
    }


    public static void main(){

    }

    public static void swap(int[] arr , int i , int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        
    }
}