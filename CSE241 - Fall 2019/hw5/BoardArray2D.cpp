#include<iostream>
#include<fstream>

#include "BoardArray2D.h"

using namespace std;

namespace absBoard{

    BoardArray2D::BoardArray2D() :AbstractBoard(){
        arr = new int*[width];
        for(int i = 0 ; i < width ; ++i)
            arr[i] = new int[height];
          FillBoard();
    }
    BoardArray2D::BoardArray2D(int theWidth , int theHeight) :AbstractBoard(theWidth,theHeight)
    {
        arr = new int*[theWidth];
        for(int i = 0 ; i < theWidth ; ++i)
            arr[i] = new int[theHeight];
            FillBoard();
            
    }
    BoardArray2D::BoardArray2D(const BoardArray2D &other) :AbstractBoard(other.width,other.height)
    {
        arr = new int*[other.width];
        for(int i = 0 ; i < other.width ; ++i)
            arr[i] = new int[other.height];

        for(int j = 0 ; j < height ; ++j){
            for(int i = 0 ; i < width ; ++i)
                arr[j][i] = other.arr[j][i];
        }   
    }
    
    BoardArray2D::~BoardArray2D(){
        --countBoard;
        for(int i = 0 ; i < height ; ++i)
            delete[] arr[i];

            delete arr;
    }
    const int& BoardArray2D::operator()(int col , int row) const
    {
        return arr[col][row];
    }
    const int BoardArray2D::getContent(int col , int row) const
    {
        return arr[col][row];
    }

    void BoardArray2D::reset(){
        for(int j = 0 ; j < height ; ++j){
            for(int i = 0 ; i < width ; ++i){
            arr[j][i] = (width * j) + i + 1;
            }
        }
        arr[height-1][width-1] = -1;
    }

    void BoardArray2D::FillBoard(){
        reset();
    }

    bool BoardArray2D::move(char direction){
        bool isDone = false;
        FillBoard();
        int blankX = getBlankX();
        int blankY = getBlankY();
        if(direction == 'R' || direction == 'r'){
            if((blankX+1) % width != 0 &&  (arr[blankY][blankX+1] != 0) ){
                swap(arr[blankY][blankX],arr[blankY][blankX+1]);
                isDone = true;
            }
        }
        if(direction == 'L' || direction == 'l'){
            if(blankX > 0 && arr[blankY][blankX-1] != 0){		
                swap(arr[blankY][blankX-1],arr[blankY][blankX]);
                isDone = true;
                }
            }
        else if(direction == 'D' || direction == 'd'){
            if( (blankY+1) % height != 0 && arr[blankY+1][blankX] != 0 ){
                swap(arr[blankY+1][blankX],arr[blankY][blankX]);
                isDone = true;
                }
            }
        else if(direction == 'U' || direction == 'u'){
            if(blankY > 0 && arr[blankY-1][blankX] != 0){
                swap(arr[blankY-1][blankX],arr[blankY][blankX]);
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

        void BoardArray2D::writeToFile(ofstream& outf){
                          
            int size = (width)*(height);
            
            string saveFileName;	
            cout << "Enter a file name to save as shape file:\n";
            cin >> saveFileName;
            outf.open(saveFileName);
            if(!outf) {
                cout <<"Saving the file has failed\n";
                exit(1);
            }

            for(int j = 0 ; j < height ; ++j){
                for(int i = 0 ; i < width ; ++i){
                    if(getContent(j,i) == -1) outf << "bb" << " ";
                    else{
                    if(getContent(j,i) < 10) outf << "0";
                    outf << getContent(j,i) << " ";
                    }
                }
                outf << endl;
            }
            outf.close();
        }


        void BoardArray2D::readFromFile(fstream& fin){

            string loadFileName;
            char ch2;
            char arr2[800];
            int *arr3;
            int height2 = 0,width2 = 0;
            int size2 = 0;
            int m = 0;

            cout << "Enter a file name to load as shape file for board:\n";
            cin >> loadFileName;
            fin.open(loadFileName);	

            if(!fin){
                cout << "File opening has failed\n"; exit(1);
            }
            while (fin >> noskipws >> ch2){

                if(ch2 == '\n') height2++;
                if(ch2 != ' ' && ch2 != '\n'){
                arr2[m] = ch2;
                size2++;
                m++;	
                }
            }
            size2 = size2 / 2;
            width2 = size2 / height2;

            (width) = (width2);
            (height) = (height2);

            arr3 = new int [width * height];
            setSize(width , height);

            for(int i = 0 ; i < size2 ; i++){
                int j = i*2;
                if(char_to_int(&arr2[j]) == 550){
                    arr3[i] = -1;

                }
                else
                    arr3[i] = char_to_int(&arr2[j]);

                }

            for(int i = 0 ; i < (width*height);++i){
                int k = 0;
                int j = 0;
                arr[j][k] = arr3[i];
                if(k < width) ++k;
                if(k == width - 1 && j != height - 1){ 
                k = 0;
                j++;
                }
                
            }

            fin.close();
            }

    int BoardArray2D::getBlankX() {
        int blankX;
        for(int j = 0 ; j < height;++j){
            for(int i = 0 ; i < width ; ++i){
                if(arr[j][i] == -1){
                    blankX = i;
                    break;
                }
            }
        }
        return blankX;
    }

    int BoardArray2D::getBlankY() {
        int blankY;
        for(int j = 0 ; j < height;++j){
            for(int i = 0 ; i < width ; ++i){
                if(arr[j][i] == -1){
                    blankY = j;
                    break;
                }
            }
        }
        return blankY;
    }














    
}