#include <fstream>
#include <iostream>
#include <iomanip> // for std::setw

#include "AbstractBoard.h"
using namespace std;

namespace absBoard{

        int AbstractBoard::countBoard = 0;
        int AbstractBoard::countMoves = 0;
        char AbstractBoard::lastmove = 'S';

        AbstractBoard::AbstractBoard()
        :width(2) , height(2) {
            ++countBoard;
        }
        AbstractBoard::AbstractBoard(int newWidth , int newHeight){
            width = newWidth;
            height = newHeight;
            ++countBoard;
        }
        AbstractBoard::~AbstractBoard(){}
        bool AbstractBoard::isSolved() const{
            bool check = true;
            for(int j = 0 ; j < height ; ++j){    
                for(int i = 0 ; i < width ; ++i){
                    if(getContent(j,i) == ((j*width) + i + 1)){
                        check = true;
                    } 
                    else if (getContent(j,i) == -1) {
                        if(j == width - 1 && i == width - 1) check = true;
                    }
                    else check = false;
                }
            }

            return check;

        }
        void AbstractBoard::setSize(int theWidth , int theHeight){
            width = theWidth;
            height = theHeight;
            reset();
        }

        int AbstractBoard::NumberOfBoards(){
            return countBoard;
        }
        int AbstractBoard::numberOfMoves(){
            return countMoves;
        }
        char AbstractBoard::lastMove(){
            return lastmove;
        }

        bool operator== (const AbstractBoard &b1 , const AbstractBoard &b2){
            bool check = false;

            if(b1.width != b2.width || b1.height != b2.height) return false;
            else{
                for(int j = 0 ; j < b1.height ; ++j){
                    for(int i = 0 ; i < b1.width ; ++i){
                        if(b1(j,i) == b2(j,i)) check = true;
                        else check = false;
                    }
                }
                return check;
            }
        }

        void AbstractBoard::print(){
            for(int j = 0 ; j < height ; ++j){
                for(int i = 0 ; i < width ; ++i){
                    if(getContent(j,i) == -1) cout << setw(5) << "_";
                    else cout << setw(5) << left << getContent(j,i);
                }
                cout << endl;
            
        }
        cout << endl;
    }
    void AbstractBoard::swap (int& n1,int& n2){
	    int temp;
	    temp = n1;
	    n1 = n2;
	    n2 = temp;
    }
    int AbstractBoard::char_to_int(char ch[]){
		int x;
		x = int((ch[0]-48))*10 + int(ch[1]-48);
		return x;
    }
    char* AbstractBoard::int_to_char(int x,char *ch){
        
        int x1,x2;
        if(x < 10){
            if(x == -1){
                ch[0] = 'b';
                ch[1] = 'b';
            }

            else{
                ch[0] = 48;
                ch[1] = char(x + 48);
            }
        }
        if(x >= 10)
        {
            x1 = x/10;
            x2 = x % 10;
            ch[0] = char(x1+48);
            ch[1] = char(x2+48);
        }
        return ch;
    }    
    
    int AbstractBoard::getAbsBlankX() const{ 
        int blankX;
        for(int j = 0 ; j < height;++j){
            for(int i = 0 ; i < width ; ++i){
                if(getContent(j,i) == -1){
                    blankX = i;
                    break;
                }
            }
        }
        return blankX;
    } 

    int AbstractBoard::getAbsBlankY() const{ 
        int blankY;
        for(int j = 0 ; j < height;++j){
            for(int i = 0 ; i < width ; ++i){
                if(getContent(j,i) == -1){
                    blankY = j;
                    break;
                }
            }
        }
        return blankY;
    } 

    

} 