#ifndef _GTUVECTOR_H
#define _GTUVECTOR_H

#include "GTUContainer.h"
#include "GTUIterator.h"

namespace kal{

    template <typename T>
    class GTUVector{

        public:
        GTUVector();
        
        virtual bool empty() const noexcept;
        virtual int size() const noexcept;
        virtual int max_size() const noexcept; 
        virtual void erase(const T &val);
        virtual void erase(GTUIterator<T> position);
        virtual void clear() noexcept;
        virtual void insert(const T &val);
        virtual GTUIterator<T> begin() const noexcept
        {
            return (this->data).get();
        }
        virtual GTUIterator<T> end() const noexcept
        {
            return (this->data).get() + used;
        }
        virtual GTUIteratorConst<T> cbegin() const noexcept
        {
            return (this->data).get();
        }
        virtual GTUIteratorConst<T> cend() const noexcept
        {
            return (this->data).get() + used;
        }       

        int& operator[](int index);

        

        protected:
        shared_ptr<T> data;
        int capacity;
        int used;
    };


}










#endif //GTUVECTOR_H