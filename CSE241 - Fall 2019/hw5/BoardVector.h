#ifndef BOARDVECTOR_H
#define BOARDVECTOR_H

#include <iostream>
#include <fstream>
#include <vector>

#include "AbstractBoard.h"

using namespace std;

namespace absBoard{

    class BoardVector : public  AbstractBoard{

        public:
        BoardVector();
        BoardVector(int theWidth , int theHeight);
        BoardVector(const BoardVector &other);
        ~BoardVector();


        virtual bool move(char direction) override; 
        virtual void readFromFile(fstream &fin) override; 
        virtual void writeToFile(ofstream &outf) override;
        virtual void reset() override; 
        virtual const int getContent(int col , int row) const override; 
        virtual const int& operator()(int col , int row)const override;         





        private:
        vector <vector <int> >board;

        int getBlankX();
        int getBlankY();   
        void FillBoard();

    };


}

#endif