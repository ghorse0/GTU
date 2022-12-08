all: board

main.o: main.cpp
	g++ -std=c++11 -c main.cpp
AbstractBoard.o: AbstractBoard.cpp
	g++ -std=c++11 -c AbstractBoard.cpp
BoardArray1D.o:	BoardArray1D.cpp
	g++ -std=c++11 -c BoardArray1D.cpp
BoardArray2D.o: BoardArray2D.cpp
	g++ -std=c++11 -c BoardArray2D.cpp
BoardVector.o: BoardVector.cpp
	g++ -std=c++11 -c BoardVector.cpp


board:	main.o AbstractBoard.o BoardArray1D.o BoardArray2D.o BoardVector.o
	g++ -std=c++11 main.o AbstractBoard.o BoardArray1D.o BoardArray2D.o BoardVector.o

clean:
	rm -rf *.o exe
