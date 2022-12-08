#include <iostream>
#include <fstream>
#include <vector>

#include "AbstractBoard.h"
#include "BoardArray1D.h"
#include "BoardArray2D.h"
#include "BoardVector.h"

using namespace std;
using namespace absBoard;

bool globalFunction(const AbstractBoard **arr,const int size);



int main(){
    ofstream ptr;
    BoardArray2D board2(5,5);
    BoardArray1D board1(3,3);
    BoardVector board3(6,6);
    AbstractBoard *b1 = &board1;
    AbstractBoard *b2 = &board2;
    AbstractBoard *b3 = &board3;
    const AbstractBoard *arr[3] = { b2,b1,b3 } ;
    
    cout << "1D array board tests" << endl;
    cout << "Printing" << endl;
    board1.print();
    cout << "Moving left,up,right,down" << endl;
    board1.move('l');
    board1.print();
    board1.move('u');
    board1.print();
    board1.move('r');
    board1.print();
    board1.move('d');
    board1.print();
    cout << "isSolved =" << board1.isSolved() << endl;
    cout << "Number of moves = " << board1.numberOfMoves() << endl;
    board1.writeToFile(ptr);

    cout << "Resetting board" << endl;
    board1.reset();
    cout << "isSolved = " << board1.isSolved() << endl;
    board1.print();
    cout << "Calling setSize(4,4)" << endl; 
    board1.setSize(4,4);
    board1.print();

    cout << "2D array board tests (5,5) board" << endl;
    cout << "Printing" << endl;
    board2.print();
    cout << "Moving left,up,right,down" << endl;
    board2.move('l');
    board2.print();
    board2.move('u');
    board2.print();
    board2.move('r');
    board2.print();
    board2.move('d');
    board2.print();
    
    cout << "isSolved =" << board2.isSolved() << endl;
    cout << "Number of moves = " << board2.numberOfMoves() << endl;
    board2.writeToFile(ptr);

    cout << "Resetting board" << endl;
    board2.reset();
    cout << "isSolved = " << board2.isSolved() << endl;
    board2.print();
    cout << "Calling setSize(4,4)" << endl; 
    board2.setSize(4,4);
    board2.print();

    cout << "Vector board tests (6,6) board" << endl;
    cout << "Printing" << endl;
    board3.print();
    cout << "Moving left,up,right,down" << endl;
    board3.move('l');
    board3.print();
    board3.move('u');
    board3.print();
    board3.move('r');
    board3.print();
    board3.move('d');
    board3.print();
    
    cout << "isSolved =" << board3.isSolved() << endl;
    cout << "Number of moves = " << board3.numberOfMoves() << endl;
    board3.writeToFile(ptr);

    cout << "Resetting board" << endl;
    board3.reset();
    cout << "isSolved = " << board3.isSolved() << endl;
    board3.print();
    cout << "Calling setSize(3,3)" << endl; 
    board3.setSize(3,3);
    board3.print();
    cout << "Number of boards so far";
    cout << board3.NumberOfBoards() << endl;
    
    cout << "Calling the global function for these 3 boards" << endl;
    cout << "Result is : " << globalFunction(arr,3);
    



}

bool globalFunction(const AbstractBoard **arr,const int size){
    int yDiff = 0;
    int xDiff = 0;
    bool check = true;
    
    for(int i = 0 ; i < size - 1 ; ++i){
        if( arr[i]->getWidth() != arr[i+1]->getWidth() || arr[i]->getHeight() != arr[i+1]->getHeight() ) return true;

        yDiff = (arr[i]->getAbsBlankY()) - (arr[i+1]->getAbsBlankY());
        xDiff = (arr[i]->getAbsBlankX()) - (arr[i+1]->getAbsBlankX());
        if( yDiff == 1 || yDiff == -1){
            if(xDiff != 0) return false;
        }
        if(xDiff == 1 || xDiff == -1){
            if(yDiff != 0) return false;
        }
    }
    if( arr[size-1]->isSolved() == 0 ) check = false;
    return true;
    
}