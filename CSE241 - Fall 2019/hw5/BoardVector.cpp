#include<iostream>
#include<fstream>
#include<vector>

#include "BoardVector.h"

using namespace std;


namespace absBoard{


    BoardVector::BoardVector() : AbstractBoard()
    {
       board.resize(width , vector<int>(height));
       reset();

    }

    BoardVector::BoardVector(int theWidth , int theHeight) : AbstractBoard(theWidth,theHeight)
    {
        board.resize(theWidth , vector<int>(theHeight));
        reset();
    }

    BoardVector::BoardVector(const BoardVector &other) 
    : AbstractBoard(other.width,other.height)
    , board(width , vector <int>(height)) 
    {
    }

    BoardVector::~BoardVector(){
        --countBoard;
    }
    
    const int BoardVector::getContent(int col , int row) const{
        return board[col][row];
    }
    const int& BoardVector::operator()(int col , int row) const
    {
        return board[col][row];
    }

    void BoardVector::reset(){
        for(int j = 0 ; j < height ; ++j){
            for(int i = 0 ; i < width ; ++i){
            board[j][i] = (width * j) + i + 1;
            }
        }
        board[height-1][width-1] = -1;
    }

    void BoardVector::FillBoard(){
        reset();
    }

    bool BoardVector::move(char direction){
        bool isDone = false;
        int blankX = getBlankX();
        int blankY = getBlankY();
        if(direction == 'R' || direction == 'r'){
            if((blankX+1) % width != 0 &&  (board[blankY][blankX+1] != 0) ){
                swap(board[blankY][blankX],board[blankY][blankX+1]);
                isDone = true;
            }
        }
        if(direction == 'L' || direction == 'l'){
            if(blankX > 0 && board[blankY][blankX-1] != 0){		
                swap(board[blankY][blankX-1],board[blankY][blankX]);
                isDone = true;
                }
            }
        else if(direction == 'D' || direction == 'd'){
            if( (blankY+1) % height != 0 && board[blankY+1][blankX] != 0 ){
                swap(board[blankY+1][blankX],board[blankY][blankX]);
                isDone = true;
                }
            }
        else if(direction == 'U' || direction == 'u'){
            if(blankY > 0 && board[blankY-1][blankX] != 0){
                swap(board[blankY-1][blankX],board[blankY][blankX]);
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



    void BoardVector::writeToFile(ofstream& outf){
                          
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

        void BoardVector::readFromFile(fstream& fin){

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
                board[j][k] = arr3[i];
                if(k < width) ++k;
                if(k == width - 1 && j != height - 1){ 
                k = 0;
                j++;
                }
                
            }

            fin.close();
            }

     int BoardVector::getBlankX() {
        int blankX;
        for(int j = 0 ; j < height;++j){
            for(int i = 0 ; i < width ; ++i){
                if(board[j][i] == -1){
                    blankX = i;
                    break;
                }
            }
        }
        return blankX;
    }           

    int BoardVector::getBlankY() {
        int blankY;
        for(int j = 0 ; j < height;++j){
            for(int i = 0 ; i < width ; ++i){
                if(board[j][i] == -1){
                    blankY = j;
                    break;
                }
            }
        }
        return blankY;
    }


    


}