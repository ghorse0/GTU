#ifndef _GTUCONTAINER_H
#define _GTUCONTAINER_H


#include <iostream>
#include <memory>
#include "GTUIterator.h"
#include "GTUIteratorConst.h"

using namespace std;

namespace kal{
    template<typename T>
    class GTUContainer{
        public:
        virtual bool empty() const noexcept = 0;
        virtual int size() const noexcept = 0;
        virtual int max_size() const noexcept = 0;     
        virtual void erase(const T &val) = 0;
        virtual void clear() noexcept = 0;
        virtual void insert(const T &val) = 0;
        virtual GTUIterator<T> begin() const noexcept = 0;
        virtual GTUIterator<T> end() const noexcept = 0;
        virtual GTUIteratorConst<T> cbegin() const noexcept = 0;
        virtual GTUIteratorConst<T> cend() const noexcept = 0;

        protected:
        

    };




}

















#endif //_GTUCONTAINER_H