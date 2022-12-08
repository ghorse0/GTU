#include "GTUVector.h"


namespace kal{

    template <typename T>
    GTUVector<T>::GTUVector(){
        capacity = 1;
        used = 0;
        std::shared_ptr<T> temp(new T[capacity], std::default_delete<T[]>());
        this->data = temp;
    }

    template <typename T>
    int GTUVector<T>::max_size() const noexcept{
        return capacity;
    }

    template <typename T>
    int GTUVector<T>::size() const noexcept{      
        return used;
    }


    template <typename T>
    bool GTUVector<T>::empty() const noexcept{
        return (used == 0);
    }

    template<typename T>
    void GTUVector<T>::clear() noexcept{
        this->used = 0;
        this->data = nullptr;
    }
    template<typename T>
    int& GTUVector<T>::operator[](int index){
        if(index >= used){
            std::cout << "Index is out of bounds" << endl;
            exit(0);
        }
        return data.get()[index];
    }


    template<typename T>
    void GTUVector<T>::erase(const T& val){
        int count = 0,index = -1,i = 0;
        std::shared_ptr< T > temp(new T[capacity], std::default_delete<T[]>());
        for(auto it = this->begin() ; it != this->end() ; ++it){
            ++count;
            if( (*it) == val){            
                index = count - 1;
            }
            
        }
        if(index == -1) {
         cout << "This value is not in the vector" << endl;
         return;
        }
        if(index == used - 1) --used;
        else{
            int j = index;
            for(auto it = begin() + index ; it != this->end() ; ++it){
                data.get()[j] = data.get()[j + 1];
                ++j;
            }
            --used;
        }

    }
    template<typename T>
    void GTUVector<T>::erase(GTUIterator<T> position){
        int index = -1,count = 0;
        for(auto it = this->begin() ; it != this->end() ; ++it){
            ++count;
            if( it == position){            
                index = count - 1;
            }

        }
        if(index == -1){
            cout << "Invalid iterator" << endl;
            return;
        }
        if(index == used - 1) --used;
        else{
            int j = index;    
            for(auto it = position  ; it != this->end() ; ++it){
                data.get()[j] = data.get()[j + 1];
                ++j;
            }
            --used;
        }
    }

    template <typename T>
    void GTUVector<T>::insert(const T& val){
        int i = 0;
        if(capacity <= used || capacity == 0){
            std::shared_ptr<T> temp(new T[capacity * 2], std::default_delete<T[]>());
            for(auto it = this->begin() ; it != this->end() ; ++it){
                temp.get()[i] = *(it);
                ++i;
            }
            temp.get()[i] = val;
             this->data = nullptr;
             this->data = temp;           
             capacity = capacity * 2;
             ++used;
        }
        else{       
            data.get()[used] = val;
            ++used;              
        }

        
    }


}