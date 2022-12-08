import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class BoardArray2D extends AbstractBoard {

    private int[][] arr;

    public BoardArray2D(){
        arr = new int[height][width];
    
    }
    public BoardArray2D(int newWidth , int newHeight){
        super(newWidth , newHeight);
        arr = new int[newHeight][newWidth];
        FillBoard();
    }
    public BoardArray2D(BoardArray2D other){
        super(other.width , other.height);
        arr = new int[other.height][other.width];
        for(int i = 0 ; i < (height) ; ++i){
            for(int j = 0 ; j < width ; ++j){
                arr[height][width] = other.arr[height][width];
            }


        }
        
        FillBoard();
    }

    public void reset(){
        for(int j = 0 ; j < height ; ++j){
            for(int i = 0 ; i < width ; ++i){
            arr[j][i] = (width * j) + i + 1;
            }
        }
        arr[height-1][width-1] = -1;
    }
    
    /**
     * Calling reset() in this function to fill the board.
     */
    public void FillBoard(){
        reset();
    }

    @Override
    public int cell(int col , int row){
        if(col >= height || row >= width){
            System.out.println("Indexes are not valid,please enter indexes as cell(y,x),Terminating the program...");
            System.exit(0);
        }
        return arr[col][row];
    }
    @Override
    public void readFromFile(String fileName){
        File file = new File(fileName);
        
        int j = 0,i = 0;
        int posBlank = 0;
        int blankX = 0,blankY = 0;
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
         arr = new int[newHeight][newWidth];
         int []tempArr = new int [width * height]; 
         blankX = posBlank % width;
         blankY = posBlank / width;
         try(Scanner scanner = new Scanner(file , StandardCharsets.UTF_8.name())){
            arr[blankY][blankX] = -1;
            while(scanner.hasNext()){
                test1 = scanner.next();
                if(!test1.equals("bb"))
                    j = Integer.parseInt(test1);

                if(i == posBlank)
                    ++i;
                 else
                    tempArr[i++] = j;
            }
              scanner.close();
         }
         catch(FileNotFoundException e){
             e.printStackTrace();
         }
         int n = 0;
        for(int k = 0 ; k < height ; ++k){
            for(int m = 0 ; m < width ; ++m){

                if( n == posBlank)
                    ++n;
                else
                    arr[k][m] = tempArr[n++];
            }
        }
     
         
    }



    public boolean move(char direction){

        boolean isDone = false;
        int blankX = getBlankX();
        int blankY = getBlankY();
    
        if(direction == 'R' || direction == 'r'){
            if((blankX+1) % width != 0 &&  (arr[blankY][blankX+1] != 0) ){
                swap(arr,blankY,blankX,blankY,blankX+1);
                isDone = true;

            }
        }
        else if(direction == 'L' || direction == 'l'){
            if(blankX > 0 && arr[blankY][blankX-1] != 0){		
                swap(arr,blankY,blankX-1,blankY,blankX);
                isDone = true;
                }
            }
        else if(direction == 'D' || direction == 'd'){
            if( (blankY+1) % height != 0 && arr[blankY+1][blankX] != 0 ){
               swap(arr,blankY+1,blankX,blankY,blankX);
               isDone = true;
                }
            }
        else if(direction == 'U' || direction == 'u'){
            if(blankY > 0 && arr[blankY-1][blankX] != 0){
                swap(arr,blankY-1,blankX,blankY,blankX);
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

        public int getBlankX() {
            int blankX = -1;
            for(int j = 0 ; j < height;++j){
                for(int i = 0 ; i < width ; ++i){
                    if(arr[j][i] == -1){
                        blankX = i;
                    }
                }
            }
            return blankX;
        }
    
        public int getBlankY() {
            int blankY = -1;
            for(int j = 0 ; j < height;++j){
                for(int i = 0 ; i < width ; ++i){
                    if(arr[j][i] == -1){
                        blankY = j;
                    }
                }
            }
            return blankY;
        }

        public static void swap(int[][] arr , int y1 , int x1 , int y2 , int x2){
            int temp = arr[y1][x1];
            arr[y1][x1] = arr[y2][x2];
            arr[y2][x2] = temp;
            
        }


}