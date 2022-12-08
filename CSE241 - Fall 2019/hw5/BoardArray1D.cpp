#include <iostream>
#include <fstream>
#include <iomanip>

#include "BoardArray1D.h"

using namespace std;







namespace absBoard{

    BoardArray1D::BoardArray1D() : AbstractBoard(){
        arr = new int[width * height];
        FillBoard();
    }
    BoardArray1D::BoardArray1D(int theWidth , int theHeight) 
    : AbstractBoard(theWidth,theHeight){
        arr = new int[width * height];
        FillBoard();
    }
    BoardArray1D::BoardArray1D(const BoardArray1D &other)
    : AbstractBoard(other.width,other.height){
        arr = new int[other.width * other.height];
        for(int i = 0 ; i < (width*height) ; ++i)
            arr[i] = other.arr[i];
            FillBoard();
    }
    BoardArray1D::~BoardArray1D(){
        --countBoard;
        delete [] arr;
    }
    const int& BoardArray1D::operator()(int col , int row) const
    {
        return arr[width * col + row];
    }

    const int BoardArray1D::getContent(int col , int row) const
    {
        return arr[width * col + row];
    }

    void BoardArray1D::reset(){
        for(int i = 0 ; i < (width * height) - 1 ; ++i){
            arr[i] = i + 1;
        }
        arr[width*height - 1] = -1;
    }
    const int BoardArray1D::get_pos_of_blank() const{
	    int pos_of_blank,i;
        for(i = 0 ; i < (width*height);i++){
            if(arr[i] == -1){
                pos_of_blank = i;
                break;
            }
        }
        return pos_of_blank;

    }


    bool BoardArray1D::move(char direction){
        int posBlank = get_pos_of_blank();
        int x = getX(posBlank);
        int y = getY(posBlank);
        bool isDone = 0;

        if(direction == 'R' || direction == 'r'){
            if((x+1) % width != 0 &&  (arr[posBlank+1] != 0) ){
                swap(arr[posBlank],arr[posBlank+1]);
                posBlank += 1;
                isDone = true;
            }
        }
        if(direction == 'L' || direction == 'l'){
            if(x > 0 && arr[posBlank-1] != 0){		
                swap(arr[posBlank-1],arr[posBlank]);
                posBlank -= 1;
                isDone = true;
                }
            }
        else if(direction == 'D' || direction == 'd'){
            if( (y+1) % height != 0 && arr[posBlank+width] != 0 ){
                swap(arr[posBlank+width],arr[posBlank]);
                posBlank += width;
                isDone = true;
                }
            }
        else if(direction == 'U' || direction == 'u'){
            if(y > 0 && arr[posBlank-width] != 0){
                swap(arr[posBlank-width],arr[posBlank]);
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
        void BoardArray1D::readFromFile(fstream& fin){

            string loadFileName;
            char ch2;
            char arr2[800];
            int *numbers;
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

            setSize(width , height);

            for(int i = 0 ; i < size2 ; i++){
                int j = i*2;
                if(char_to_int(&arr2[j]) == 550){
                    arr[i] = -1;

                }
                else
                    arr[i] = char_to_int(&arr2[j]);

                }


            fin.close();
            }
        void BoardArray1D::writeToFile(ofstream& outf){
                          
            int size = (width)*(height);
            
            string saveFileName;	
            cout << "Enter a file name to save current board as shape file:\n";
            cin >> saveFileName;
            outf.open(saveFileName);
            if(!outf) {
                cout <<"Saving the file has failed\n";
                exit(1);
            }

            for(int j = 0 ; j < height ; ++j){
                for(int i = 0 ; i < width ; ++i){
                    if(getContent(i,j) == -1) outf << "bb" << " ";
                    else{
                    if(getContent(i,j) < 10) outf << "0";
                    outf << getContent(i,j) << " ";
                    }
                }
                outf << endl;
            }
            outf.close();
        }

    
        int BoardArray1D::getX(int index){
            return(index % (width));
        }
	    int BoardArray1D::getY(int index){
            return(index / (width));
        }    

        char* BoardArray1D::charArray(char *ch,int size,int index)
        {

            if(size == 0) return ch;
            if(size != 0) {
                ch = int_to_char(arr[index],ch);
                charArray(&ch[2],size-1,index+1);
            }	
        }
        void BoardArray1D::FillBoard(){	//Filling board as final state
                for(int j = 0 ; j < (width * height)-1;j++){
                    arr[j] = j+1;    
                } 
            arr[(width * height)-1] = -1;
        }




}