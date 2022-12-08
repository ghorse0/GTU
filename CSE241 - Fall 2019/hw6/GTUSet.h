#ifndef _GTUSET_H
#define _GTUSET_H


#include"GTUContainer.h"
#include"GTUIterator.h"

namespace kal{

    template<typename T>
    class GTUSet{
        public:
        GTUSet();

        virtual bool empty() const noexcept;
        virtual int size() const noexcept;
        virtual int max_size() const noexcept; 
        virtual void erase(const T &val);
                void erase(GTUIterator<T> position);
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

        

        protected:
        shared_ptr<T> data;
        int size_;
        int used;


    };




}






#endif