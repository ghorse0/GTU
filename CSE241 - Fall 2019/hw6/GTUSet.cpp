#include "GTUSet.h"



namespace kal{
    template <typename T>
    GTUSet<T>::GTUSet(){
        size_ = 1;
        used = 0;
        std::shared_ptr<T> temp(new T[size_], std::default_delete<T[]>());
        this->data = temp;
    }

    template <typename T>
    int GTUSet<T>::max_size() const noexcept{
        return size_;
    }

    template <typename T>
    int GTUSet<T>::size() const noexcept{      
        return used;
    }


    template <typename T>
    bool GTUSet<T>::empty() const noexcept{
        return (used == 0);
    }
    template<typename T>
    void GTUSet<T>::erase(const T& val) {
        int count = 0,index = -1,i = 0;
        std::shared_ptr< T > temp(new T[size_], std::default_delete<T[]>());
        for(auto it = this->begin() ; it != this->end() ; ++it){
            ++count;
            if( (*it) == val){            
                index = count - 1;
            }
            
        }
        if(index == -1) {
         throw invalid_argument("This value is not in the set");
        }
        for(auto it = this->begin() ; it != this->end() ; ++it){
            if( *(it) != this->data.get()[index]){
                temp.get()[i] = *(it);
                ++i;
            }

        }
        this->data = nullptr;
        this->data = temp;
        --used;
    }
    template<typename T>
    void GTUSet<T>::erase(GTUIterator<T> position){
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
    void GTUSet<T>::insert(const T& val){
        int i = 0;
        if(size_ <= used || size_ == 0){
            std::shared_ptr<T> temp(new T[size_ * 2], std::default_delete<T[]>());
            for(auto it = this->begin() ; it != this->end() ; ++it){
                if( (*it) == val){
                    throw invalid_argument("This value is already in the set!");
                }
                temp.get()[i] = *(it);
                ++i;
            }
            temp.get()[i] = val;
             this->data = nullptr;
             this->data = temp;           
             size_ = size_ * 2;
             ++used;
        }
        else{
            for(auto it = this->begin() ; it != this->end() ; ++it){
                if( (*it) == val){
                    return;
                }
            }        
                data.get()[used] = val;
                ++used;              

        }

        
    }

    template<typename T>
    void GTUSet<T>::clear() noexcept{
        this->used = 0;
        this->data = nullptr;
    }




}