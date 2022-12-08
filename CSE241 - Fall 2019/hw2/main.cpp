#include<iostream>
#include<fstream>
#include<stdlib.h>
#include<time.h>
#include<cmath>  //for abs function
#include<iomanip> 
using namespace std;


void PrintBoard(int *arr,int width,int height);
void FillBoard(int *arr,int size);
void swap (int& n1,int& n2);
void RandomPuzzle(int arr[],int size);
void Gameplay(int *arr,int *width,int *height);
void Shuffle(int arr[],int size);
void RandomMove(int arr[],int width,int height);
void newIntelligentMove(int arr[],int goalArr[],int width,int height,int& pos_of_0,int *prev);
void oldIntelligentMove(int arr[],int goalArr[],int width,int height,int& pos_of_0,int *prev);

int move_r(int arr[],int width,int& pos_of_0);
int move_l(int arr[],int width,int& pos_of_0);
int move_d(int arr[],int width,int height,int& pos_of_0);
int move_u(int arr[],int width,int height,int& pos_of_0);


int get_pos_of_blank(int arr[],int size);
int IsSolvable(int arr[],int size);

int getX(const int arr[],const int size,const int index);
int getY(const int arr[],const int size,const int index);
int getCost(const int arr[],const int goalArr[],int width,int height);

int minimum(int x1,int x2,int x3,int x4);

int char_to_int(char ch[]);
char *int_to_char(int x,char *ch);
char *charArray(int *arr,char *ch,int size,int index);

char RandomChar();
int calculateCost(int arr[],int goalArr[]);
bool IsSolutionFound(int arr[],int goalArr[],int size);

//void intelligentMove(int arr[],int goalArr[],int width,int height,int& pos0);



int main(int argc, char **argv){
	

	int arr[81];
	char *save;
	int final_arr[81];
	int goal_arr[81];
	int size;
	int size3;
	int j = 0;
	int totalblock,is_solvable;
	srand(time(NULL));	
	if(argc == 1){

	cout << "Please enter the problem size:" << '\n';
	cin >> size;
	size3 = size;
	totalblock = size*size3;	

	for(int i = 0 ; i < 81 ; i++) arr[i] = -2;

	FillBoard(arr,size);
	FillBoard(final_arr,size);	
	do{
	RandomPuzzle(arr,size);
	is_solvable = IsSolvable(arr,size);
	}while(is_solvable == 0);	

	cout << "Your initial random board is:" <<'\n';
	PrintBoard(arr,size,size3);


	
	Gameplay(arr,&size,&size3);	
	}
	else if(argc == 2){
		
		
		char ch;
		char arr2[250];
		int m = 0;
		int height = 0;
		int width = 0;
		int numbers[81];
		int size2 = 0;
	
		for(int i = 0 ; i < 81 ; i++){
			goal_arr[i] = -2;
		}
		for(int j = 0 ; j < 81 ; j++){
			numbers[j] = -2;
		}
		for(int i = 0 ; i < 82 ; i++){
			arr2[i] = 'e';
		}
		fstream fin("myText.txt",fstream::in);	//start of reading from file
		while (fin >> noskipws >> ch){
			//cout << ch;
			if(ch == '\n') height++;
			if(ch != ' ' && ch != '\n'){
			arr2[m] = ch;
			size2++;
			m++;	
			}
		}
		size2 = size2 / 2;
		width = size2 / height;
		

		//for(int k = 0 ; k < 100 ; k++)
			//cout << arr2[k] << ' ';
		//int n = ((int(arr2[98]) -48)*10 + int(arr2[99]) -48) ;
		//cout << n;
		for(int i = 0 ; i < size2 ; i++){
			int j = i*2;
			if(char_to_int(&arr2[j]) == 550){
				numbers[i] = -1;

			}
			else
				numbers[i] = char_to_int(&arr2[j]);

			}
			int k = 1;
			for(int j = 0 ; j < size2 ;j++){
				if(numbers[j] == 0)
					goal_arr[j] = 0;

				else if(j != size2-1){
					goal_arr[j] = k;
					k++;
				}
			} 
			goal_arr[size2-1] = -1;
		
			//cout << numbers[i] <<' ';
			//PrintBoard(goal_arr,width,height);
			//cout << endl;
			PrintBoard(numbers,width,height);
			int cost; 
			cost = calculateCost(numbers,goal_arr);
			//cout << endl << cost;
			
			Gameplay(numbers,&width,&height);
			//IntelligentMove(numbers,goal_arr,width,pos00);
			
	


		fin.close();	//reading from file done
	}
	//free(arr);
	//free(final_arr);
	return 0;

}



void Gameplay(int *arr,int *width,int *height){

	int end_flag = 0,totalMove = 0;
	char input;
	int size = (*width)*(*height);
	int pos0;
	int goal_arr[81];
	char *ch;
	int k = 1;
	int check = -1;
	int cost;
	char randomch;
	int previous = -1;
	string saveFileName;

	ch = (char*) malloc(300 * sizeof(char));
	pos0 = get_pos_of_blank(arr,size);

	for(int j = 0 ; j < size ;j++){
		if(arr[j] == 0)
			goal_arr[j] = 0;

		else if(j != size-1){
			goal_arr[j] = k;
			k++;
			}
		} 
	goal_arr[size-1] = -1;
 

	do
	{
		end_flag = IsSolutionFound(arr,goal_arr,size);
		if(end_flag == 1) cout << "Solution has been found,Press q to quit\n";

		pos0 = get_pos_of_blank(arr,size);
		cout << "Your move: ";
		cin >> input;
		if(input == 'Q' || input == 'q') exit(1);
		if(input == 'L' || input == 'l') check = move_l(arr,(*width),pos0);		
		else if(input == 'R' || input == 'r') check = move_r(arr,(*width),pos0);		
		else if(input == 'U' || input == 'u') check = move_u(arr,(*width),(*height),pos0);		
		else if(input == 'D' || input == 'd') check = move_d(arr,(*width),(*height),pos0);
		if(check == 0) cout << "Not possible\n";
		if(check == 1) ++totalMove;	


		
		if(input == 'T' || input == 't'){
			cout << totalMove << " move has been done";
			if(end_flag == 1) cout << " and the solution has been found.\n";;
			if(end_flag == 0) cout << " and the solution couldn't be found.\n";
		}

		if(input == 'E' || input == 'e'){
			charArray(arr,ch,size,0);
			
			cout << "Enter a file name to save as shape file:\n";
			cin >> saveFileName;
			ofstream outf(saveFileName);
			if(!outf) {
				cout <<"Saving the file has failed\n";
				exit(1);
			}

			for(int j = 0 ; j < size*2;j++)
				{
				outf << ch[j];
				if( (j+1) % 2 == 0 && (j+1) % (2* (*width)) != 0 ) outf << ' ';
				if( (j+1) % (2* (*width))  == 0 ) outf << '\n';
				}
			
			}

		else if(input == 'S' || input == 's'){
			cout << "Shuffling board...\n";
			for(int i = 0 ; i < size ; i++){
				arr[i] = goal_arr[i];
			}
			PrintBoard(arr,(*width),(*height));
			for(int i = 0 ; i < size*size ; i++){
				Shuffle(arr,size);	
				}
			}
		else if(input == 'Y' || input == 'y'){
			string loadFileName;
			char ch2;
			char arr2[250];
			int numbers[81];
			int height2 = 0,width2 = 0;
			int size2 = 0;
			int m = 0;

			cout << "Enter a file name to load as shape file for board:\n";
			cin >> loadFileName;
			fstream fin(loadFileName,fstream::in);	//start of reading from file
			if(!fin){
				cout << "File opening has failed\n"; exit(1);
			}
			while (fin >> noskipws >> ch2){
				//cout << ch;
				if(ch2 == '\n') height2++;
				if(ch2 != ' ' && ch2 != '\n'){
				arr2[m] = ch2;
				size2++;
				m++;	
				}
			}
			size2 = size2 / 2;
			width2 = size2 / height2;

			(*width) = (width2);
			(*height) = (height2);
	
			//for(int k = 0 ; k < 100 ; k++)
				//cout << arr2[k] << ' ';
			//int n = ((int(arr2[98]) -48)*10 + int(arr2[99]) -48) ;
			//cout << n;
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
						goal_arr[j] = 0;

					else if(j != size2-1){
						goal_arr[j] = k;
						k++;
					}
				} 
				goal_arr[size2-1] = -1;


			size = size2;
			fin.close();
			}

		if(input == 'I' || input == 'i'){
			
			//RandomMove(arr,(*width),(*height));
			oldIntelligentMove(arr,goal_arr,*width,*height,pos0,&previous);




		}
		else if(input == 'V' || input == 'v'){
			for(int m = 0 ; m < 1000000 ; m++){
			newIntelligentMove(arr,goal_arr,*width,*height,pos0,&previous);
			PrintBoard(arr,(*width),(*height));
			end_flag = IsSolutionFound(arr,goal_arr,size);
			if(end_flag == 1) {
				cout << "Solution has been found,exiting..." << endl;
				PrintBoard(arr,(*width),(*height));
				exit(0);
			}
			
			}
			if(end_flag == 0) cout << "Solution couldn't be found" << endl;

		}

		if(input != 'v' || input != 'V') PrintBoard(arr,(*width),(*height));
			
		}while(end_flag == 0); //end of while
		


}

void newIntelligentMove(int arr[],int goalArr[],int width,int height,int& pos_of_0,int *prev){
	int costl = -1,costr = -1,costu = -1,costd = -1; 
	int selectedIndex;
	int indexArr[2];
	int minCost = -1;
	int checkl = -1,checkr = -1,checku = -1,checkd = -1;
	int count = 0,random;
	int costarr[4] = {costl,costr,costu,costd};

	checkl = move_l(arr,width,pos_of_0);

	if(checkl == 1){
		costl = getCost(arr,goalArr,width,height);
		move_r(arr,width,pos_of_0);
	}

	
	checkr = move_r(arr,width,pos_of_0);

	if(checkr == 1){
		costr = getCost(arr,goalArr,width,height);	
		move_l(arr,width,pos_of_0);
	}

	checku = move_u(arr,width,height,pos_of_0);
	if(checku == 1){
		costu = getCost(arr,goalArr,width,height);	
		move_d(arr,width,height,pos_of_0);
	}


	checkd = move_d(arr,width,height,pos_of_0);
	if(checkd == 1){
		costd = getCost(arr,goalArr,width,height);
		move_u(arr,width,height,pos_of_0);
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
	//cout << minCost;
	if(selectedIndex == *prev){
		cout << "Stuck,making random moves\n";
		for(int k = 0 ; k < 5 ; k++)
		RandomMove(arr,width,height);
		


	}
	else{
		if(selectedIndex == 0) {
			move_l(arr,width,pos_of_0);
			*prev = 1; 
			cout << "Intelligent move chooses L\n";
			}
		else if(selectedIndex == 1){ 
			move_r(arr,width,pos_of_0);
			*prev = 0;
			cout << "Intelligent move chooses R\n";
		}
		else if(selectedIndex == 2){ 
			move_u(arr,width,height,pos_of_0);
			*prev = 3;
			cout << "Intelligent move chooses U\n";
		}
		else if(selectedIndex == 3) {
			move_d(arr,width,height,pos_of_0);
			*prev = 2;
			cout << "Intelligent move chooses D\n";
		}
	}


}


void oldIntelligentMove(int arr[],int goalArr[],int width,int height,int& pos_of_0,int *prev){
		int costl = -1,costr = -1,costu = -1,costd = -1; 
	int selectedIndex;
	int indexArr[3];
	int minCost = -1;
	int checku = -1,checkd = -1,checkl = -1,checkr = -1;
	int count = 0,random;
	int costarr[4] = {costl,costr,costu,costd};

	checkl = move_l(arr,width,pos_of_0);
	cout << "checkl = " << checkl;
	if(checkl == 1){
		costl = getCost(arr,goalArr,width,height);
		move_r(arr,width,pos_of_0);
	}
	

	
	checkr = move_r(arr,width,pos_of_0);
		cout << "checkr = " << checkr;
	if(checkr == 1){
		costr = getCost(arr,goalArr,width,height);	
		move_l(arr,width,pos_of_0);
	}


	checku = move_u(arr,width,height,pos_of_0);
		cout << "checku = " << checku;
	if(checku == 1){
		costu = getCost(arr,goalArr,width,height);	
		move_d(arr,width,height,pos_of_0);
	}
	

	checkd = move_d(arr,width,height,pos_of_0);
		cout << "checkd = " << checkd;
	if(checkd == 1){
		costd = getCost(arr,goalArr,width,height);
		move_u(arr,width,height,pos_of_0);
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



	}


	cout << "\nselected index : "<<selectedIndex <<endl;
	cout << costarr[selectedIndex];
	if(selectedIndex == *prev){
		cout << "Stuck,making random move\n";
		for(int k = 0 ; k < 1 ; k++)
		RandomMove(arr,width,height);
	}
	else{
		if(selectedIndex == 0) {
			move_l(arr,width,pos_of_0);
			*prev = 1; 
			cout << "Intelligent move chooses L\n";
			}
		else if(selectedIndex == 1){ 
			move_r(arr,width,pos_of_0);
			*prev = 0;
			cout << "Intelligent move chooses R\n";
		}
		else if(selectedIndex == 2){ 
			move_u(arr,width,height,pos_of_0);
			*prev = 3;
			cout << "Intelligent move chooses U\n";
		}
		else if(selectedIndex == 3) {
			move_d(arr,width,height,pos_of_0);
			*prev = 2;
			cout << "Intelligent move chooses D\n";
		}
	}
	


}

	
void PrintBoard(int *arr,int width,int height){

	for(int i = 0 ; i < (width*height) ; i++){
		if(i % width == 0 && i != 0){
			cout << endl;
		}
		if(arr[i] == -1) cout << setw(5) << left << "_" ;
		else cout << setw(5) << left << arr[i] ;
	}
	cout <<'\n';

}




void FillBoard(int *arr,int size){	//Filling board as final state

	int i,j,totalblock = size*size;

	for(i = 0 ; i < size ; i++){
		for(j = 0 ; j < totalblock-1;j++){
			arr[j] = j+1;
			
		}

	}
	arr[totalblock-1] = -1;
}

void swap (int& n1,int& n2){
	int temp;
	temp = n1;
	n1 = n2;
	n2 = temp;
}

void RandomPuzzle(int arr[],int size){	//Shuffling puzzle size*size times for initial random configuration
	
	int i,j,totalblock = size*size;
	srand(time(NULL));

	for(i = totalblock-1 ; i > 0; i--){
		j = rand() % (i+1);

		swap(arr[j],arr[i]);

	}


}


int IsSolvable(int arr[],int size){
	
	int i,totalblock,j,inverse_count;

	totalblock = size*size;
	inverse_count = 0;

	for(i = 0 ; i<size-1 ; i++)
	{
		for(j = 1 ; j<size ; j++)
		{
			if(arr[i] != 0 && arr[j] != 0 && arr[i] > arr[j])
				inverse_count++;
		}
	}

	if(inverse_count % 2 == 0) return 1;
	else return 0;



}

char RandomChar(){


	char random_char;
	char arr[4] = {'R','L','U','D'};
	int j;
	j = rand() % (4);
	
	random_char = arr[j];
	return random_char;

}

void Shuffle(int *arr,int size){

	int i,check = -1;
	int j;
	char random_char = RandomChar();
	i = rand() % size;
	j = rand() % size;
	if(arr[j] != 0 && arr[i] != 0)
	swap(arr[j],arr[i]);
	else Shuffle(arr,size);


}



int getX(const int arr[],const int width,const int index){
	
	int result;
	result = (index % width);	
	//cout  << '\n' << arr[index] << " this number's x distance to the final position is: " << result << '\n';
	return result;	
}



int getY(const int arr[],const int width,const int index){

	int result;
	result = (index / width);
	//cout  << '\n' << arr[index]<< " this number's y distance to the final position is: " << result << '\n';
	return result;
}

int getCost(const int arr[],const int goalArr[],int width,int height){
	int x1,x2,y1,y2;
	int x,y,cost = 0;
	int i,j;
	for(i = 0 ; i < (width*height) ; i++){
		for(j = 0 ; j < (width*height) ; j++){
			if(arr[j] == goalArr[i] && arr[j] != -1){
				x1 = getX(arr,width,j);
				y1 = getY(arr,width,j);
				x2 = getX(goalArr,width,i);
				y2 = getY(goalArr,width,i);
				x = abs(x1-x2);
				y = abs(y1-y2);
				cost += x + y;
			}
		}
	}
	return cost;
}

void RandomMove(int arr[],int width,int height){
	char randomDirection = RandomChar();
	int check = -1 ;
	int pos0 = get_pos_of_blank(arr,width*height);
	if(randomDirection == 'L') check = move_l(arr,width,pos0);
	else if(randomDirection == 'R') check = move_r(arr,width,pos0);
	else if(randomDirection == 'D') check = move_d(arr,width,height,pos0);
	else if(randomDirection == 'U') check = move_u(arr,width,height,pos0);

	if(check == 0) RandomMove(arr,width,height);
}

int minimum(int x1,int x2,int x3,int x4){

	int min = x1;
	if(min > x2)
		min = x2;
	if(min > x3)
		min = x3;
	if(min > x4)
		min = x4;
	//cout << "\n x1,x2,x3,x4 ->" << x1<< x2 << x3 << x4;
	//cout << "\n min is : " << min << '\n';

	return min;
}


int move_r(int arr[],int width,int& pos_of_0){

	int x;
	int isDone = 0;
	x = getX(arr,width,pos_of_0);
	if((x+1) % width != 0 &&  (arr[pos_of_0+1] != 0) ){
	swap(arr[pos_of_0],arr[pos_of_0+1]);
	pos_of_0 += 1;
	isDone = 1;
	}

	return isDone;
}
int move_l(int arr[],int width,int& pos_of_0){
	int x;
	int isDone = 0;
	x = getX(arr,width,pos_of_0);
	if(x > 0 && arr[pos_of_0-1] != 0){
		swap(arr[pos_of_0-1],arr[pos_of_0]);
		pos_of_0 -= 1;
		isDone = 1;
	}
	return isDone;
}
int move_d(int arr[],int width,int height,int& pos_of_0){
	int y;
	int isDone = 0;
	y = getY(arr,width,pos_of_0);
	if( (y+1) % height != 0 && arr[pos_of_0+width] != 0 ){
		swap(arr[pos_of_0+width],arr[pos_of_0]);
		pos_of_0 += width;
		isDone = 1;
	}	
	return isDone;
}
int move_u(int arr[],int width,int height,int& pos_of_0){

	int y;
	int isDone = 0;
	y = getY(arr,width,pos_of_0);
	if(y > 0 && arr[pos_of_0-width] != 0){
		swap(arr[pos_of_0-width],arr[pos_of_0]);
		pos_of_0 -= width;
		isDone = 1;
	}
	return isDone;

}

int get_pos_of_blank(int arr[],int size){

	int pos_of_blank,i;
	for(i = 0 ; i < (size*size);i++){
		if(arr[i] == -1){
			pos_of_blank = i;
			break;
		}
	}
	return pos_of_blank;

}



int char_to_int(char ch[]){
		int x;
		x = int((ch[0]-48))*10 + int(ch[1]-48);
		return x;
}

char *int_to_char(int x,char *ch){
	
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





















int calculateCost(int arr[],int goalArr[]){

	int i;
	int cost = 0;
	
	for(i = 0; i < 54 ; i++){
			if(arr[i] != goalArr[i] && arr[i] != -1 && goalArr[i] != 0 && arr[i] != 0)
					cost++;
			
				
		}
		
	
	return cost;
}





/*void intelligentMove(int arr[],int goalArr[],int width,int height,int& pos0){

	int cost = 82,costl = 82,costr = 82,costu = 82,costd = 82,mincost;
	int totalmove = 0;
	int flag = -1;
	int size = width*height;
	char L = 'L',R = 'R',U = 'U',D = 'D';
	char move,lastMove = '0';
    //srand(time(NULL));		
    

	while(flag != 0 && totalmove < 20000){
		cost = calculateCost(arr,goalArr);
		flag = calculateCost(arr,goalArr);
		//cout << flag;
		if(flag == 0) cout << "buldu " << endl;
			if(arr[pos0-1] != 0 ){
				move_l(arr,width,pos0);
				costl = calculateCost(arr,goalArr);
				move_r(arr,width,pos0);
			}
		
			if( arr[pos0+1] != 0 ){
				move_r(arr,width,pos0);
				costr = calculateCost(arr,goalArr);
				move_l(arr,width,pos0);	
			}
	
			if(  arr[pos0-width] != 0 ){
				move_u(arr,width,height,pos0);
				costu = calculateCost(arr,goalArr);
				move_d(arr,width,height,pos0);
				}
		

			if( arr[pos0+width] != 0){
				move_d(arr,width,height,pos0);
				costd = calculateCost(arr,goalArr);	
				move_u(arr,width,height,pos0);
				}
			//if(lastMove == 'D') costu = 83; else if(lastMove == 'L') costr = 83;
			//else if(lastMove == 'U') costd = 83; else if(lastMove == 'R') costl = 83;

			mincost = minimum(costd,costu,costr,costl);
			if( costd == costu && costd == costr && costd == costl && costu == costr && costu == costl && costr == costl){
				move = RandomChar(); 

				lastMove = move;
				//cout << move;

				}
				
			else if(mincost == costd && (pos0+width) < size && arr[pos0+width] != 0 && lastMove != 'U') move = 'D';
			else if(mincost == costl && pos0 % width != 0 && arr[pos0-1] != 0 && lastMove != 'R') move = 'L';
			else if(mincost == costr && (pos0+1) % width != 0 && arr[pos0+1] != 0 && lastMove != 'L') move = 'R';
			else if(mincost == costu && (pos0-width)>= 0 && arr[pos0-width] != 0 && lastMove != 'D') move = 'U';

			if(move == 'D' && arr[pos0+width] != 0 && lastMove != 'U' ) {
				
				move_d(arr,width,height,pos0);
				cout << "Intelligent move chooses D" << endl;
			}
			else if(move == 'U' && arr[pos0-width] != 0 && lastMove != 'D' ){
			
				move_u(arr,width,height,pos0);
				cout << "Intelligent move chooses U" << endl;
			}
			else if(move == 'L'  && arr[pos0-1] != 0 && lastMove != 'R' ){
			
				move_l(arr,width,pos0);
				cout << "Intelligent move chooses L" << endl;
			}
			else if(move == 'R' && (arr[pos0+1] != 0) && lastMove != 'L'){
				move = 'R';
			 	move_r(arr,width,pos0);
			 	cout << "Intelligent move chooses R" << endl ;
			}
			lastMove = move;
			totalmove++;
			//PrintBoard(arr,width,size/width);
	}
	if(totalmove == 200000) cout << "\nCouldnt solve\n";

	//cout << cost;

}*/



void ShuffleMove2(int *arr,int size,int& pos_of_0,char str){

	int i;

	pos_of_0 = get_pos_of_blank(arr,size);

	if(str == 'R'){
			if((pos_of_0+1) % size != 0){
				move_r(arr,size,pos_of_0);
			}
			//else cout << "Not possible" << '\n';
		}
		if(str == 'L'){
			if((pos_of_0) % size != 0 || (pos_of_0-1) == 0 ){
				move_l(arr,size,pos_of_0);
			}
		    //else cout << "Not possible\n";
		}
		if(str == 'U'){
			if((pos_of_0-size) >= 0){
				move_u(arr,size,size,pos_of_0);
			}
			//else cout << "Not possible\n";
		}
		if(str == 'D'){
			if((pos_of_0+size) < size){
				move_d(arr,size,size,pos_of_0);
			}
			//else cout << "Not possible\n";
		}


}



char *charArray(int *arr,char *ch,int size,int index){
	
		if(size == 0) return ch;
		if(size != 0) {

			ch = int_to_char(arr[index],ch);
			charArray(arr,&ch[2],size-1,index+1);

		}
		
		
}

bool IsSolutionFound(int arr[],int goalArr[],int size){
	int check = 0;
	int number = 0;

	for(int i = 0 ; i < size ; i++){
		if(arr[i] == goalArr[i]) check = 1;
		else
		{
			check = 0;
			number++;
		}
	}
	if(check == 1 && number == 0) return true;
	else return false;
}