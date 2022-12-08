public class Main{

public static boolean myFunction(AbstractBoard arr[] , int size){
  int yDiff = 0;
  int xDiff = 0;
  boolean check = true;

  for(int i = 0 ; i < size - 1 ; ++i){
      if( arr[i].getWidth() != arr[i+1].getWidth() || arr[i].getHeight() != arr[i+1].getHeight())
        return false;
    yDiff = (arr[i].getAbsBlankY()) - (arr[i+1].getAbsBlankY());
    xDiff = (arr[i].getAbsBlankX()) - (arr[i+1].getAbsBlankX());
    
    if(yDiff > 1 || yDiff < -1 || xDiff > 1 || xDiff < -1)
      return false;

    if( yDiff == 1 || yDiff == -1){
        if(xDiff != 0) return false;
        
    }
    if(xDiff == 1 || xDiff == -1){
        if(yDiff != 0) return false;
    }

  }
  if ( arr[size - 1].isSolved() == false ) check = false;
  return check;
    
}

public static void main(String[] args){

    BoardArray1D obj1 = new BoardArray1D(5,5);
    BoardArray2D obj2 = new BoardArray2D(4,4);

  


    
    System.out.printf("Testing BoardArray1D class: \n");
    System.out.println("Testing a 5 x 5 board:");
    System.out.println("Using toString method:");
    System.out.println(obj1.toString());
    System.out.printf("cell(0,2) = %d\n",obj1.cell(0,2));
    System.out.println("Moving left,up,right and down");
    obj1.move('L');
    System.out.println(obj1);
    System.out.printf("IsSolved : %b\n\n",obj1.isSolved());
    obj1.move('U');
    System.out.println(obj1);
    System.out.printf("IsSolved : %b\n\n",obj1.isSolved());
    obj1.move('R');
    System.out.println(obj1);
    obj1.move('D');
    System.out.println(obj1);
    System.out.printf("IsSolved : %b\n",obj1.isSolved());
    System.out.printf("Last move is : %c\n",obj1.lastMove());
    System.out.printf("Number of moves this board made : %d\n",obj1.numberOfMoves());
    System.out.println("Resetting board");
    obj1.reset();
    System.out.println(obj1);
    System.out.println("Calling writeToFile()");
    obj1.writeToFile();
    System.out.println("Calling readFromFile(test.txt) , test.txt contains 10x10 board");
    obj1.readFromFile("test.txt");
    System.out.println(obj1);
    System.out.printf("Calling setSize(4,4)\n");
    obj1.setSize(4,4);
    System.out.println(obj1);
    System.out.printf("IsSolved : %b\n",obj1.isSolved());


    System.out.printf("Testing BoardArray2D class: \n");
    System.out.println("Testing a 4 x 4 board:");
    System.out.printf("Number of boards created so far :%d\n",BoardArray2D.NumberOfBoards());
    System.out.println("Calling Equal() method with 1D board");
    System.out.printf("%b\n",obj2.Equals(obj1,obj2));
    System.out.println("Using toString method:");
    System.out.println(obj2.toString());
    System.out.printf("cell(0,2) = %d\n",obj2.cell(0,2));
    System.out.printf("Last move is : %c\n",obj2.lastMove());
    System.out.printf("IsSolved : %b\n\n",obj2.isSolved());
    System.out.println("Moving left,up,right and down");
    
    obj2.move('L');
    System.out.println(obj2);
    obj2.move('U');
    System.out.println(obj2);
    obj2.move('R');
    System.out.println(obj2);
    obj2.move('D');
    System.out.println(obj2);
    System.out.printf("IsSolved : %b\n",obj2.isSolved());
    System.out.printf("Last move is : %c\n",obj2.lastMove());
    System.out.printf("Number of moves this board made : %d\n",obj2.numberOfMoves());
    System.out.println("Resetting board");
    obj2.reset();
    System.out.println(obj2);
    System.out.println("Calling writeToFile()");
    obj2.writeToFile();
    System.out.println("Calling readFromFile(test.txt) , test.txt contains 10x10 board");
    obj2.readFromFile("test.txt");
    System.out.println(obj2);
    System.out.printf("Calling setSize(4,4)\n");
    obj2.setSize(4,4);
    System.out.println(obj2);
    System.out.printf("IsSolved : %b\n",obj2.isSolved());

    
    BoardArray1D test1 = new BoardArray1D(4,4);
    BoardArray2D test2 = new BoardArray2D(4,4);
    BoardArray1D test3 = new BoardArray1D(4,4);
    BoardArray1D test4 = new BoardArray1D(4,4);
    AbstractBoard[] arr2 = new AbstractBoard[4]; 
    arr2[0] = test3;
    arr2[1] = test2;
    arr2[2] = test1;
    arr2[3] = test4;
    test1.move('L');

    test2.move('L');
    test2.move('U');

    test3.move('L');
    test3.move('L');
    test3.move('U');

    System.out.println("Testing static function:");
    System.out.println("These are the boards : ");
    System.out.println("Board 1 :\n" + test3);
    System.out.println("Board 2 :\n" + test2);
    System.out.println("Board 3 :\n" + test1);
    System.out.println("Board 4 :\n" + test4);
    System.out.println("Result of static function : ");
    System.out.println(myFunction(arr2,4));

    test1.move('R');
    test2.move('R');
    test3.move('L');
    System.out.println("Testing static function:");
    System.out.println("These are the boards : ");
    System.out.println("Board 1 :\n" + test3);
    System.out.println("Board 2 :\n" + test2);
    System.out.println("Board 3 :\n" + test1);
    System.out.println("Board 4 :\n" + test4);
    System.out.println("Result of static function : ");
    System.out.println(myFunction(arr2,4));

    

    }

}