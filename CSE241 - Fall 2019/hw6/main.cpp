#include <iostream>

#include "GTUSet.h"
#include "GTUSet.cpp"
#include "GTUVector.h"
#include "GTUVector.cpp"
#include <string>

using namespace std;
using namespace kal;

template <typename T>
void printSet(const GTUSet<T> &test);

template <typename T>
void printVector(const GTUVector<T> &test);

template <typename T>
GTUIterator<T> find(GTUIterator<T> first,GTUIterator<T> last,const int &val);

template<typename T, typename Function>
GTUIterator<T> find_if(GTUIterator<T> first, GTUIterator<T> last, Function foo)
{
    auto it = first;
    for(;it != last ; ++it){
        if (foo(*it)) return it;
    }
    return last;
}

template<typename T, typename Function>
Function for_each(GTUIterator<T> first, GTUIterator<T> last, Function foo)
{
    for(auto it = first ; it != last ; ++it){
        foo(*it);
    }
    return foo;
}
bool isEven(int i){
    return( (i%2) == 0);
}

int square(int x){
    cout << x * x << ' '; 
}
int foo2(int x){
    if(x < 100) cout << x << " ";
}
bool foo(int x){
    return (x > 300);
}

int main(){

    GTUSet<int> s;
    GTUVector<int> v;
    
    cout << "Start of vector test!" << endl;

    cout << "size() : " << v.size() << endl;
    cout << "max_size() : " << v.max_size() << endl;

    cout << "Inserting 1,3,5,23,32,345,654,-12 to vector" << endl;
    v.insert(1);
    v.insert(3);
    v.insert(5);
    v.insert(23);
    v.insert(32);
    v.insert(345);
    v.insert(654);
    v.insert(-12);
    cout << "Inserting done,printing vector..." << endl;
    printVector(v);
    cout << "Erasing the value at v.begin()" << endl;
    v.erase(v.begin());
    printVector(v);
    cout << "Erasing the value at v.begin() + 3" << endl;
    v.erase(v.begin() + 3);
    printVector(v);
    cout << "Erasing the value at v.end() - 2" << endl;
    v.erase(v.end() - 2);
    printVector(v);
    cout << endl;
    cout << "size() : " << v.size() << endl;
    cout << "max_size() : " << v.max_size() << endl;
    cout << "find() test for vector" << endl;
    cout << "Finding the value 345 in vector" << endl;
    auto it = find(v.begin(),v.end(),345);
    cout << *it << endl;
    cout << endl;
    cout << "Finding the value -12 in vector" << endl;
    it = find(v.begin(),v.end(),-12);
    cout << *it << endl;
    cout << endl;
    cout << "find_if() test for vector" << endl;
    cout << "Finding a even value in vector" << endl;
    it = find_if(v.begin() , v.end() , isEven);
    cout << *it << endl;
    cout << endl;
    cout << "Finding a value that is greater than 300 in vector" << endl;
    it = find_if(v.begin(),v.end(),foo);
    cout << *it << endl;
    cout << endl;
    cout << "for_each() test for vector" << endl;
    cout << "Printing square of the numbers in vector" << endl;
    cout << for_each(v.begin(),v.end(),square) << endl;
    cout << "Printing values that are less than 100" << endl;
    cout << for_each(v.begin(),v.end(),foo2) << endl;
    cout << endl;



    cout << "Start of set test!" << endl;
    cout << "size() : " << s.size() << endl;
    cout << "max_size() : " << s.max_size() << endl;

    cout << "Inserting \n1,3,5,23,32,345,654,-12,333,15,8 to set" << endl;
    s.insert(1);
    s.insert(3);
    s.insert(5);
    s.insert(23);
    s.insert(32);
    s.insert(345);
    s.insert(654);
    s.insert(-12);
    s.insert(333);
    s.insert(15);
    s.insert(8);
    cout << "Inserting 1 to set again" << endl;;
    s.insert(1);
    cout << "Inserting done,printing set..." << endl;
    printSet(s);
    cout << "Erasing the value at s.begin()" << endl;
    s.erase(s.begin());
    printSet(s);
    cout << "Erasing the value at s.begin() + 3" << endl;
    s.erase(s.begin() + 3);
    printSet(s);
    cout << "Erasing the value at s.end() - 2" << endl;
    s.erase(s.end() - 2);
    printSet(s);
    cout << endl;
    cout << "size() : " << s.size() << endl;
    cout << "max_size() : " << s.max_size() << endl;
    cout << "find() test for set" << endl;
    cout << "Finding the value 23 in set" << endl;
    it = find(s.begin(),s.end(),23);
    cout << *it << endl;
    cout << endl;
    cout << "Finding the value -12 in set" << endl;
    it = find(s.begin(),s.end(),-12);
    cout << *it << endl;
    cout << endl;
    cout << "find_if() test for set" << endl;
    cout << "Finding a even value in set" << endl;
    it = find_if(s.begin() , s.end() , isEven);
    cout << *it << endl;
    cout << endl;
    cout << "Finding a value that is greater than 300 in set" << endl;
    it = find_if(s.begin(),s.end(),foo);
    cout << *it << endl;
    cout << "for_each() test for set" << endl;
    cout << "Printing square of the numbers in set" << endl;
    cout << for_each(s.begin(),s.end(),square) << endl;
    cout << "Printing values that are less than 100" << endl;
    cout << for_each(s.begin(),s.end(),foo2) << endl;
    cout << endl;
    cout << "Exception tests for a char set" << endl;
    try{
        GTUSet<char> set1;
        cout << "Inserting 'a' then printing set" << endl;
        set1.insert('a');
        printSet(set1);
        cout << "Inserting 'b' then printing set" << endl;
        set1.insert('b');
        printSet(set1);
        cout << "Inserting 'a' then printing set" << endl;
        set1.insert('a');
        printSet(set1);
    }catch(const exception &a){
        cout << a.what() << endl;
    }

}

template <typename T>
void printSet(const GTUSet<T> &test){

    for(auto it = test.begin() ; it != test.end() ; ++it){
        cout << *(it) << " ";
    }
    cout << endl;
}

template <typename T>
void printVector(const GTUVector<T> &test){

    for(auto it = test.begin() ; it != test.end() ; ++it){
        cout << *(it) << " ";
    }
    cout << endl;
}


template <typename T>
GTUIterator<T> find(GTUIterator<T> first,GTUIterator<T> last,const int &val){
    auto it = first;
    for(; it != last ; ++it){
        if(*it == val) return it; 
    }
    return last;

}