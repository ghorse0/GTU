#ifndef _GTUIterator_H
#define _GTUIterator_H

#include<memory>


namespace kal{

    template<class T>
    class GTUIterator{

    public:
        GTUIterator() : current(nullptr){}
        GTUIterator(T *current_) : current(current_){}


        GTUIterator& operator=(const GTUIterator &other){
            current = other.current;
            return *this;
        }
        GTUIterator& operator++(){          //prefix
            ++current;
            return *this;
        }
        GTUIterator& operator+(int val){
            current = current + val;
            return *this;
        }
        GTUIterator& operator-(int val){
            current = current - val;
            return *this;
        }
        GTUIterator& operator++(int val){   //postfix
            GTUIterator tmp(current);
            current++;
            return tmp;

        }
      
        GTUIterator& operator--(){          //prefix
            --current;
            return *this;
        }

        GTUIterator& operator--(int val){   //postfix
            GTUIterator tmp(current);
            current--;
            return tmp;
        }
        bool operator==(const GTUIterator &other){
            return (current == other.current);
        }

        bool operator!=(const GTUIterator &other){
            return (current != other.current);
        }
        T& operator*(){
            return *current;
        }
        T& operator->(){
            return current;
        }


    protected:
    T *current;

    };


}










#endif //_GTUITERATOR_H