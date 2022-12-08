#ifndef ABSTRACTBOARD_H
#define ABSTRACTBOARD_H

#include <iostream>
#include <fstream>


using namespace std;



namespace absBoard {
class AbstractBoard
{
    public:
    AbstractBoard();
    AbstractBoard(int newWidth , int newHeight);
    //AbstractBoard(const AbstractBoard &other);
    virtual ~AbstractBoard();

    int getWidth() const { return width; };
    int getHeight() const { return height; };
    void setWidth(int newWidth) { width = newWidth; };
    void setHeight(int newHeight) { height = newHeight; };
    int getAbsBlankX() const;
    int getAbsBlankY() const;
    int NumberOfBoards();
    int numberOfMoves();
    char lastMove();
    bool isSolved() const;
    void print();
    void setSize(int theWidth , int theHeight);
    
    virtual bool move(char direction) = 0;
    virtual void readFromFile(fstream &fin) = 0;
    virtual void writeToFile(ofstream &outf) = 0;
    virtual void reset() = 0;
    virtual const int getContent(int row , int col) const = 0;
    virtual const int& operator()(int row , int col) const = 0;
    friend bool operator== (const AbstractBoard &b1 , const AbstractBoard &b2);


     

    protected:
    int width;
    int height;
    static int countBoard;
    static int countMoves;
    static char lastmove;

    
    
    void swap(int& n1,int& n2);
    int char_to_int(char ch[]);
    char* int_to_char(int x,char *ch);

};

}


#endif  //AbstractBoard_h