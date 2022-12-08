import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



abstract public class AbstractBoard {

    protected int width;
    protected int height;
    protected int countMoves;
    static int countBoard = 0;
    protected char lastmove = 'S';

    /** 
     * takes two indexes,returns the corresponding cell content
     * 
     */
    public abstract int cell(int col , int row);
    /** 
     * Makes move according to parameter char
     * @param  direction
     */
    public abstract boolean move(char direction);
    /** 
     * reads from file and creates a new array for board.
     * @param  fileName
     */
    public abstract void readFromFile(String fileName);

    /** 
     * Resets the board to solution.
     * 
     */
    public abstract void reset();

    
    public AbstractBoard(){
        width = 2;
        height = 2;
        countMoves = 0;
        ++countBoard;
    }
    public AbstractBoard(int newWidth , int newHeight){
        width = newWidth;
        height = newHeight;
        ++countBoard;
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    /**
     * Produces board as string
     * @return String
     */
    public String toString(){
        String myBoard = "";
        for(int j = 0 ; j < height ; ++j){
            for(int i = 0 ; i < width ; ++i){
                if(cell(j,i) == -1) myBoard += String.format("%-4s","_");
                else myBoard += (String.format("%-4d",cell(j,i))) ;
            }
            myBoard += "\n";
        }
        
        
        return myBoard;
    }


    public void setSize(int theWidth , int theHeight){
        width = theWidth;
        height = theHeight;
        reset();
    }
    /**
     *Returns x position of blank for  static method that takes array of AbstractBoard
     @return int */ 
    protected int getAbsBlankX(){
        int blankX = 0;
        for(int j = 0 ; j < height ; ++j){
            for(int i = 0 ; i < width ; ++i){
                if(cell(j,i) == -1){
                    blankX = i;
                    break;
                }
            }
        }
        return blankX;
    }
        /**
     *Returns y position of blank for  static method that takes array of AbstractBoard
     @return int */ 
    protected int getAbsBlankY(){
        int blankY = 0;
        for(int j = 0 ; j < height ; ++j){
            for(int i = 0 ; i < width ; ++i){
                if(cell(j,i) == -1){
                    blankY = j;
                    break;
                }
            }
        }
        return blankY;
    }
    /** 
     * returns number of board created so far
     * @return static int
     */
    public static int NumberOfBoards(){
        return countBoard;
    }
    /** 
     * returns number of moves made so far
     * @return int
     */
    public int numberOfMoves(){
        return countMoves;
    }
    /** 
     * returns last move the board made
     * @return char
     */
    public char lastMove(){
        return lastmove;
    }
        /** 
     * Writes the board to a file,takes input from user for file name.
     * @return void
     */
    public void writeToFile(){
        Scanner input = new Scanner(System.in);
        try{
        System.out.println("Enter a file name to save the current board : ");
        String fileName = input.nextLine();
        File newFile = new File(fileName);
        newFile.createNewFile();
        FileWriter fileWriter = new FileWriter(newFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for(int j = 0 ; j < height ; ++j){
            for(int i = 0 ; i < width ; ++i){
                if(cell(j,i) == -1) printWriter.write("bb");

                else{
                    if(cell(j,i) < 10) printWriter.write("0");
                        printWriter.write( String.valueOf( cell(j,i) ) );
                }   
                if( i != width - 1) 
                    printWriter.write(" ");     
            }
                if(j != height - 1)
                    printWriter.write("\n");
        }
        
        


        printWriter.close();
        }
        catch(IOException ie){
            ie.printStackTrace();
         }
        
    }
    
    /** 
     * Checks if two boards are equal
     * @return boolean
     */
    public boolean Equals(AbstractBoard b1 , AbstractBoard b2){
        boolean check = false;

        if(b1.width != b2.width || b1.height != b2.height) return false;
        else{
            for(int j = 0 ; j < b1.height ; ++j){
                for(int i = 0 ; i < b1.width ; ++i){
                    if(b1.cell(j,i) == b2.cell(j,i)) check = true;
                    else check = false;
                }
            }
            return check;
        }
    }
    /** 
     * Checks if board has reached the solution state
     * @return boolean
     */
    public boolean isSolved(){
        boolean check = true;
        for(int j = 0 ; j < height ; ++j){    
            for(int i = 0 ; i < width ; ++i){
                if(cell(j,i) == ((j*width) + i + 1)){
                    check = true;
                } 
                else if (cell(j,i) == -1) {
                    if(j == width - 1 && i == width - 1 && check == true) check = true;
                }
                else check = false;
            }
        }
        return check;  
    }

   

}

