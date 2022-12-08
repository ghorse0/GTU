#ifndef BOARDARRAY1D_H
#define BOARDARRAY1D_H

#include <iostream>
#include <fstream>
#include "AbstractBoard.h"

using namespace std;


namespace absBoard{

    class BoardArray1D : public AbstractBoard{
        public:
        BoardArray1D();
        BoardArray1D(int theWidth , int theHeight);
        BoardArray1D(const BoardArray1D &other);
        ~BoardArray1D();


        virtual bool move(char direction) override; 
        virtual void readFromFile(fstream &fin) override; 
        virtual void writeToFile(ofstream &outf) override;
        virtual void reset() override; 
        virtual const int getContent(int col, int row) const override; 
        virtual const int& operator()(int col , int row)const override; 



        private:
        int *arr;

        char* charArray(char *ch,int size,int index);
        const int get_pos_of_blank() const;   
        void FillBoard(); 
	    int getX(int index);
	    int getY(int index);     

    };




}


#endif