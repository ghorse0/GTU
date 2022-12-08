#ifndef _GTUConstIterator_H
#define _GTUConstIterator_H

#include<memory>


namespace kal{

    template<class T>
    class GTUIteratorConst{

    public:
        GTUIteratorConst() : current(nullptr){}
        GTUIteratorConst(T *current_) : current(current_){}


        GTUIteratorConst& operator=(const GTUIteratorConst &other){
            current = other.current;
            return *this;
        }
        GTUIteratorConst& operator++(){          //prefix
            ++current;
            return *this;
        }
        GTUIteratorConst& operator+(int val){
            current = current + val;
            return *this;
        }
        GTUIteratorConst& operator-(int val){
            current = current - val;
            return  *this;
        }
        GTUIteratorConst& operator++(int val){   //postfix
            GTUIteratorConst tmp(current);
            current++;
            return tmp;

        }
      
        GTUIteratorConst& operator--(){          //prefix
            --current;
            return *this;
        }

        GTUIteratorConst& operator--(int val){   //postfix
            GTUIteratorConst tmp(current);
            current--;
            return tmp;
        }
        bool operator==(const GTUIteratorConst &other){
            return (current == other.current);
        }

        bool operator!=(const GTUIteratorConst &other){
            return (current != other.current);
        }
        const T& operator*(){
            return *current;
        }
        const T& operator->(){
            return current;
        }


    protected:
    T *current;

    };


}










#endif //_GTUITERATOR_H