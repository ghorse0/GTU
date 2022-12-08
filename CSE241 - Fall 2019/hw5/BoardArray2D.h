#ifndef BOARDARRAY2D_H
#define BOARDARRAY2D_H

#include <fstream>
#include <iostream>

#include "AbstractBoard.h"

using namespace std;

namespace absBoard{

    class BoardArray2D : public AbstractBoard {
        public:
        BoardArray2D();
        BoardArray2D(int theWidth , int theHeight);
        BoardArray2D(const BoardArray2D &other);
        ~BoardArray2D();


        virtual bool move(char direction) override; 
        virtual void readFromFile(fstream &fin) override; 
        virtual void writeToFile(ofstream &outf) override;
        virtual void reset() override; 
        virtual const int getContent(int col , int row) const override; 
        virtual const int& operator()(int col , int row)const override; 




        private:
        int **arr;

        int getBlankX();
        int getBlankY();   
        void FillBoard();
    };







}


#endif