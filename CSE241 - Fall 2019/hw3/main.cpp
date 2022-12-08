#include<iostream>
#include<fstream>
#include<stdlib.h>
#include<time.h>
#include<cmath>  //for abs function
#include<iomanip>
using namespace std;



class NPuzzle 
{
public :
	
	void print(){ board.print(); };
	int printReport();
	void readFromFile(fstream& fin){ board.readFromFile(fin); };
	void writeToFile(char **ch,ofstream& outf){ board.writeToFile(ch,outf); };
	void shuffle(int N);
	void reset(){ board.reset(); };
	int setsize(int width0,int height0){ board.setsize(width0,height0); };
	bool move(char direction){ board.move(direction); };
	void moveRandom();
	void moveIntelligent(int *prev);
	void solvePuzzle(int *prev);
	bool isSolved(){ board.isSolved(); };
	void Gameplay();
	void swap(int& n1,int& n2);
	char RandomChar();
	int get_pos_of_blank(){ board.get_pos_of_blank(); };
	int minimum(int x1,int x2,int x3,int x4);
	int getX(int index);
	int getY(int index);
	bool IsSolutionFound();
	int calculateCost();
	int IsSolvable();
	void RandomPuzzle();
	void FillBoard(int size);
	int update(int arr2[],int width3 , int height3);
	int char_to_int(char ch[]){ board.char_to_int(ch); };
	char *charArray(int *arr,char *ch,int size,int index);
	char *int_to_char(int x,char *ch);

private:


class Board
{
	public:
	void print();
	int setsize(int width0,int height0){width = width0; height = height0;};
	bool move(char direction);
	void readFromFile(fstream& fin);
	int* getArr(){ return arr; };
	int* setArr(int arr2[81]);
	void writeToFile(char **ch,ofstream& outf);
	void reset();
	void setsize();
	void solvePuzzle();
	bool isSolved();
	void swap(int& n1,int& n2);
	int getX(int index);
	int getY(int index);
	int char_to_int(char ch[]);
	char *int_to_char(int x,char *ch);
	char *charArray(int *arr,char *ch,int size,int index);
	int get_pos_of_blank();

	int width;
	int height;
	int posBlank;
	int arr[81];
	int goalArr[81];


};

Board board;

};


int main(int argc, char **argv){
	NPuzzle puzzle;
	srand(time(NULL));	
	char *save;
	int size;
	int size3;
	int j = 0;
	int totalblock,is_solvable;

	if(argc == 1){
	cout << "Please enter the problem size:" << '\n';
	cin >> size;
	size3 = size;
	totalblock = size*size3;	

	puzzle.FillBoard(size);	
	do{
	puzzle.RandomPuzzle();
	is_solvable = puzzle.IsSolvable();
	}while(is_solvable == 0);

	puzzle.Gameplay();	

	}
	else if(argc > 1){

		char ch;
		char arr2[250];
		int m = 0;
		int height2 = 0;
		int width2 = 0;
		int numbers[81];
		int size2 = 0;
	
		for(int j = 0 ; j < 81 ; j++){
			numbers[j] = -2;
		}
		for(int i = 0 ; i < 82 ; i++){
			arr2[i] = 'e';
		}
		fstream fin("myText.txt",fstream::in);
		while (fin >> noskipws >> ch){
			if(ch == '\n') height2++;
			if(ch != ' ' && ch != '\n'){
			arr2[m] = ch;
			size2++;
			m++;	
			}
		}
		size2 = size2 / 2;
		width2 = size2 / height2;
		
	
		for(int i = 0 ; i < size2 ; i++){
			int j = i*2;
			if(puzzle.char_to_int(&arr2[j]) == 550){
				numbers[i] = -1;

			}
			else
				numbers[i] = puzzle.char_to_int(&arr2[j]);

			}
			puzzle.update(numbers,width2,height2);		
			puzzle.Gameplay();

		fin.close();	//reading from file done
	}

	return 0;

}



void NPuzzle::Gameplay(){

	int end_flag = 0;
	char input;
	int size = (board.width)*(board.height);
	int pos0;
	int goal_arr[81];
	char *ch;
	int prev;
	int prevI;
	int k = 1;
	int check = -1;
	char randomch;
	int totalMove = 0;
	ofstream save;
	fstream load;
	
	ch = (char*) malloc(300 * sizeof(char));
	pos0 = get_pos_of_blank();
	

	for(int j = 0 ; j < size ;j++){
		if(board.arr[j] == 0)
			goal_arr[j] = 0;

		else if(j != size-1){
			goal_arr[j] = k;
			k++;
			}
		} 
	goal_arr[size-1] = -1;
	cout << "Press :" << endl << "R to move blank tile right,L to move left" << endl << "U to move up,D to move down" << endl;
	cout << "V to solve the board,T for how many moves have been done" << endl
			<< "O to load board from a file,E for saving current board" << endl
			<< "I to let computer make an intelligent move for you" << endl
			<< "S for shuffling the board size * size times" << endl;
	cout << "Press Q to quit." << endl << endl;		
	cout << "Your initial random board is:" <<'\n';
	do
	{
		print();
		end_flag = IsSolutionFound();
		if(end_flag == 1) cout << "Solution has been found,Press q to quit\n";

		pos0 = get_pos_of_blank();
		cout << "Your move: ";
		cin >> input;
		if(input == 'Q' || input == 'q') exit(1);
		else if(input == 'L' || input == 'l') check = move('L');		
		else if(input == 'R' || input == 'r') check = move('R');	
		else if(input == 'U' || input == 'u') check = move('U');		
		else if(input == 'D' || input == 'd') check = move('D');
		if(check == 0) cout << "Not possible\n";
		if(check == 1) totalMove++;	

		if(input == 'T' || input == 't'){
			cout << totalMove << " move has been done";
			if(end_flag == 1) cout << " and the solution has been found.\n";;
			if(end_flag == 0) cout << " and the solution couldn't be found.\n";
		}
		if(input == 'I'){ moveIntelligent(&prevI); }


		if(input == 'v' || input == 'V') {

			for(int m = 0 ; m < 1000000 ; m++){
				solvePuzzle(&prev);
				print();
				end_flag = IsSolutionFound();
				if(end_flag == 1) {
					cout << "Solution has been found,exiting..." << endl;
					print();
					exit(0);
				}
				
				}
				if(end_flag == 0) cout << "Solution couldn't be found" << endl;
				
				}
				
		if(input == 'E' || input == 'e'){
			writeToFile(&ch,save);
			
			}

		else if(input == 'S' || input == 's'){

				shuffle(size * size);
				
			}
		else if(input == 'O' || input == 'o'){
			readFromFile(load);
			}

			
		}while(end_flag == 0); //end of while
		


}









void NPuzzle::FillBoard(int size){	//Filling board as final state

	int i,j,totalblock = size * size;
	setsize(size,size);
		for(j = 0 ; j < (totalblock)-1;j++){
			board.arr[j] = j+1;
			board.goalArr[j] = j+1;
			
		}
	board.goalArr[totalblock-1] = -1; 
	board.arr[totalblock-1] = -1;
	board.posBlank = totalblock-1;
}

void NPuzzle::swap (int& n1,int& n2){
	int temp;
	temp = n1;
	n1 = n2;
	n2 = temp;
}

void NPuzzle::Board::swap (int& n1,int& n2){
	int temp;
	temp = n1;
	n1 = n2;
	n2 = temp;
}

void NPuzzle::RandomPuzzle(){	//Shuffling puzzle size*size times for initial random configuration
	int i,j,totalblock = (board.width * board.height);
	

	for(i = totalblock-1 ; i > 0; i--){
		j = rand() % (i+1);

		swap(board.arr[j],board.arr[i]);

	}
	update(board.arr,board.width,board.height);


}


int NPuzzle::IsSolvable(){
	
	int i,totalblock,j,inverse_count;
	auto size = board.width * board.height;
	totalblock = size*size;
	inverse_count = 0;

	for(i = 0 ; i<size-1 ; i++)
	{
		for(j = 1 ; j<size ; j++)
		{
			if(board.arr[i] != 0 && board.arr[j] != 0 && board.arr[i] > board.arr[j])
				inverse_count++;
		}
	}

	if(inverse_count % 2 == 0) return 1;
	else return 0;



}

char NPuzzle::RandomChar(){

	char random_char;
	char arr[4] = {'R','L','U','D'};
	int j;
	j = rand() % (4) + 1;
	random_char = arr[j];
	return random_char;

}

void NPuzzle::moveRandom(){

	char str;
	int check = -1;
	
	str = RandomChar();
	check = move(str);
	if(!check) moveRandom();

}

void NPuzzle::shuffle(int N){

	int i;
	char str;
	int check;
	int count = 0;
	auto size = (board.width) * (board.height);
	auto posBlank = get_pos_of_blank();
	
	for(i = 0 ; i < size ; i++) board.arr[i] = board.goalArr[i];
	
	
	do{
	str = RandomChar();
	check = move(str);
	count++;
	}while( count < N );

}



int NPuzzle::getX(int index){
	
	int result;
	result = (index % (board.width) );	
	return result;	
}



int NPuzzle::getY(int index){

	int result;
	result = (index / (board.width) );
	return result;
}
int NPuzzle::Board::getX(int index){
	
	int result;
	result = (index % (width) );	
	return result;	
}



int NPuzzle::Board::getY(int index){

	int result;
	result = (index / (width) );
	return result;
}


int NPuzzle::minimum(int x1,int x2,int x3,int x4){

	int min = x1;
	if(min > x2)
		min = x2;
	if(min > x3)
		min = x3;
	if(min > x4)
		min = x4;
	return min;
}

bool NPuzzle::Board::move(char direction){
	int x = getX(posBlank);
	int y = getY(posBlank);
	int isDone = 0;

	if(direction == 'R' || direction == 'r'){
		if((x+1) % width != 0 &&  (arr[posBlank+1] != 0) ){
			swap(arr[posBlank],arr[posBlank+1]);
			posBlank += 1;
			isDone = 1;
		}
	}
	if(direction == 'L' || direction == 'l'){
		if(x > 0 && arr[posBlank-1] != 0){		
			swap(arr[posBlank-1],arr[posBlank]);
			posBlank -= 1;
			isDone = 1;
			}
		}
	else if(direction == 'D' || direction == 'd'){
		if( (y+1) % height != 0 && arr[posBlank+width] != 0 ){
			swap(arr[posBlank+width],arr[posBlank]);
			posBlank += width;
			isDone = 1;
			}
		}
	else if(direction == 'U' || direction == 'u'){
		if(y > 0 && arr[posBlank-width] != 0){
			swap(arr[posBlank-width],arr[posBlank]);
			posBlank -= width;
			isDone = 1;
			}
		}
	
	return isDone;
}



int NPuzzle::Board::get_pos_of_blank(){

	int pos_of_blank,i;
	for(i = 0 ; i < (width*height);i++){
		if(arr[i] == -1){
			pos_of_blank = i;
			break;
		}
	}
	return pos_of_blank;

}



int NPuzzle::Board::char_to_int(char ch[]){
		int x;
		x = int((ch[0]-48))*10 + int(ch[1]-48);
		return x;
}

char* NPuzzle::Board::int_to_char(int x,char *ch){
	
	int x1,x2;
	if(x < 10){
		if(x == -1){
			ch[0] = 'b';
			ch[1] = 'b';
		}

		else{
			ch[0] = 48;
			ch[1] = char(x + 48);
		}
	}
	if(x >= 10)
	{
		x1 = x/10;
		x2 = x % 10;
		ch[0] = char(x1+48);
		ch[1] = char(x2+48);
	}
	return ch;
}






void NPuzzle::Board::print(){

	for(int i = 0 ; i < (width*height) ; i++){
		if(i % width == 0 && i != 0){
			cout << endl;
		}
		if(arr[i] == -1) cout << setw(5) << left << "_" ;
		else cout << setw(5) << left << arr[i] ;
	}
	cout <<'\n';

}


int NPuzzle::calculateCost(){

	int x1,x2,y1,y2;
	int x,y,cost = 0;
	int i,j;
	for(i = 0 ; i < (board.width*board.height) ; i++){
		for(j = 0 ; j < (board.width*board.height) ; j++){
			if(board.arr[j] == board.goalArr[i] && board.arr[j] != -1){
				x1 = getX(j);
				y1 = getY(j);
				x2 = getX(i);
				y2 = getY(i);
				x = abs(x1-x2);
				y = abs(y1-y2);
				cost += x + y;
			}
		}
	}
	return cost;
}


void NPuzzle::moveIntelligent(int *prev){

	int costl = -1,costr = -1,costu = -1,costd = -1; 
	int selectedIndex;
	int indexArr[2];
	int minCost = -1;
	int checkl = -1,checkr = -1,checku = -1,checkd = -1;
	int count = 0,random;
	int costarr[4] = {costl,costr,costu,costd};

	checkl = move('L');

	if(checkl == 1){
		costl = calculateCost();
		move('R');
	}

	
	checkr = move('R');

	if(checkr == 1){
		costr = calculateCost();	
		move('L');
	}

	checku = move('U');
	if(checku == 1){
		costu = calculateCost();	
		move('D');
	}


	checkd = move('D');
	if(checkd == 1){
		costd = calculateCost();
		move('U');
	}
	
	costarr[0] = costl; costarr[1] = costr; costarr[2] = costu; costarr[3] = costd;
	
	for(int i = 0 ; i < 4 ; i++){
		if(costarr[i] != -1 && minCost == -1){
			minCost = costarr[i];
			++count;
			selectedIndex = i;
		}  
		else if(costarr[i] < minCost && costarr[i] != -1) {
			minCost = costarr[i];
			selectedIndex = i;
		}
		else if(costarr[i] == minCost && costarr[i] != -1){
			indexArr[0] = selectedIndex;
			indexArr[1] = i;
			random = rand() % 2;
			selectedIndex = random;
		} 

	if(costarr[selectedIndex] == -1){
		while(random == selectedIndex)
			random = rand() % 4;
			selectedIndex = random;

	}

	}
	if(selectedIndex == *prev){
		cout << "Stuck,making random moves\n";
		moveRandom();
		


	}
	else{
		if(selectedIndex == 0) {
			move('L');
			*prev = 1; 
			cout << "Intelligent move chooses L\n";
			}
		else if(selectedIndex == 1){ 
			move('R');
			*prev = 0;
			cout << "Intelligent move chooses R\n";
		}
		else if(selectedIndex == 2){ 
			move('U');
			*prev = 3;
			cout << "Intelligent move chooses U\n";
		}
		else if(selectedIndex == 3) {
			move('D');
			*prev = 2;
			cout << "Intelligent move chooses D\n";
		}
	}

}


void NPuzzle::solvePuzzle(int *prev){
	int costl = -1,costr = -1,costu = -1,costd = -1; 
	int selectedIndex;
	int indexArr[2];
	int minCost = -1;
	int checkl = -1,checkr = -1,checku = -1,checkd = -1;
	int count = 0,random;
	int costarr[4] = {costl,costr,costu,costd};

	checkl = move('L');

	if(checkl == 1){
		costl = calculateCost();
		move('R');
	}

	
	checkr = move('R');

	if(checkr == 1){
		costr = calculateCost();	
		move('L');
	}

	checku = move('U');
	if(checku == 1){
		costu = calculateCost();	
		move('D');
	}


	checkd = move('D');
	if(checkd == 1){
		costd = calculateCost();
		move('U');
	}
	
	costarr[0] = costl; costarr[1] = costr; costarr[2] = costu; costarr[3] = costd;
	
	for(int i = 0 ; i < 4 ; i++){
		if(costarr[i] != -1 && minCost == -1){
			minCost = costarr[i];
			++count;
			selectedIndex = i;
		}  
		else if(costarr[i] < minCost && costarr[i] != -1) {
			minCost = costarr[i];
			selectedIndex = i;
		}
		else if(costarr[i] == minCost && costarr[i] != -1){
			indexArr[0] = selectedIndex;
			indexArr[1] = i;
			random = rand() % 2;
			selectedIndex = random;
		} 

	if(costarr[selectedIndex] == -1){
		while(random == selectedIndex)
			random = rand() % 4;
			selectedIndex = random;

	}

	}
	if(selectedIndex == *prev){
		cout << "Stuck,making random moves\n";
		for(int i = 0 ; i < 5 ; i++) moveRandom();
		


	}
	else{
		if(selectedIndex == 0) {
			move('L');
			*prev = 1; 
			cout << "Intelligent move chooses L\n";
			}
		else if(selectedIndex == 1){ 
			move('R');
			*prev = 0;
			cout << "Intelligent move chooses R\n";
		}
		else if(selectedIndex == 2){ 
			move('U');
			*prev = 3;
			cout << "Intelligent move chooses U\n";
		}
		else if(selectedIndex == 3) {
			move('D');
			*prev = 2;
			cout << "Intelligent move chooses D\n";
		}
	}



}




char* NPuzzle::Board::charArray(int *arr,char *ch,int size,int index){
	
		if(size == 0) return ch;
		if(size != 0) {

			ch = int_to_char(arr[index],ch);
			charArray(arr,&ch[2],size-1,index+1);

		}
		
		
}



void NPuzzle::Board::readFromFile(fstream& fin){

			string loadFileName;
			char ch2;
			char arr2[250];
			int numbers[81];
			int height2 = 0,width2 = 0;
			int size2 = 0;
			int m = 0;

			cout << "Enter a file name to load as shape file for board:\n";
			cin >> loadFileName;
			fin.open(loadFileName);	
	
			if(!fin){
				cout << "File opening has failed\n"; exit(1);
			}
			while (fin >> noskipws >> ch2){
	
				if(ch2 == '\n') height2++;
				if(ch2 != ' ' && ch2 != '\n'){
				arr2[m] = ch2;
				size2++;
				m++;	
				}
			}
			size2 = size2 / 2;
			width2 = size2 / height2;

			(width) = (width2);
			(height) = (height2);
	
			for(int i = 0 ; i < size2 ; i++){
				int j = i*2;
				if(char_to_int(&arr2[j]) == 550){
					arr[i] = -1;

				}
				else
					arr[i] = char_to_int(&arr2[j]);

				}
				int k = 1;
				for(int j = 0 ; j < size2 ;j++){
					if(arr[j] == 0)
						goalArr[j] = 0;

					else if(j != size2-1){
						goalArr[j] = k;
						k++;
					}
				} 
				goalArr[size2-1] = -1;
			posBlank = get_pos_of_blank();


			fin.close();
			}


void NPuzzle::Board::writeToFile(char **ch,ofstream& outf){
			
			int size = (width)*(height);
			charArray(arr,*ch,size,0);
			string saveFileName;	
			cout << "Enter a file name to save as shape file:\n";
			cin >> saveFileName;
			outf.open(saveFileName);
			if(!outf) {
				cout <<"Saving the file has failed\n";
				exit(1);
			}

			for(int j = 0 ; j < size*2;j++)
				{
				outf << (*ch)[j];
				if( (j+1) % 2 == 0 && (j+1) % (2* (width)) != 0 ) outf << ' ';
				if( (j+1) % (2* (width))  == 0 ) outf << '\n';
				}
			outf.close();
}



bool NPuzzle::IsSolutionFound(){
	int check = 0;
	int number = 0;
	int size = board.width * board.height;
	for(int i = 0 ; i < size ; i++){
		if(board.arr[i] == board.goalArr[i]) check = 1;
		else
		{
			check = 0;
			number++;
		}
	}
	if(check == 1 && number == 0) return true;
	else return false;
}


int NPuzzle::update(int arr2[],int width3 , int height3 ){ 
	int k = 1;
	for(int i = 0 ; i < 81 ; i++)
		board.arr[i] = arr2[i];
	
	
	
	board.width = width3; 
	board.height = height3; 
	board.posBlank = get_pos_of_blank();


	
			for(int j = 0 ; j < (width3*height3) ;j++){
				if(arr2[j] == 0)
					board.goalArr[j] = 0;

				else if(j != (width3*height3)-1){
					board.goalArr[j] = k;
					k++;
				}
			} 
			board.goalArr[(width3*height3)-1] = -1;














}


	void NPuzzle::Board::reset(){

		for(int i = 0 ; i < 81 ; i++){
		arr[i] = goalArr[i];

		}

	}