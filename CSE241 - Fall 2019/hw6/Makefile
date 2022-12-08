all: hw6

main.o: main.cpp
	g++ -std=c++11 -c main.cpp
GTUIterator.o: GTUIterator.cpp
	g++ -std=c++11 -c GTUIterator.cpp
GTUIteratorConst.o: GTUIteratorConst.cpp
	g++ -std=c++11 -c GTUIteratorConst.cpp
GTUContainer.o: GTUContainer.cpp
	g++ -std=c++11 -c GTUContainer.cpp
GTUSet.o: GTUSet.cpp
	g++ -std=c++11 -c GTUSet.cpp
GTUVector.o: GTUVector.cpp
	g++ -std=c++11 -c GTUVector.cpp

hw6: main.o GTUContainer.o GTUIterator.o GTUSet.o GTUVector.o GTUIteratorConst.o
		g++ -std=c++11 main.o GTUContainer.o GTUIterator.o GTUSet.o GTUVector.o GTUIteratorConst.o
clean:
	rm -rf *.o exe