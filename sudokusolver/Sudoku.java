import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Sudoku {

	SudokuStack unassigned;
	int n;
	int counter=0;
	int root_n;
	int[][] grid;
	
	LinkedList[][] adjlist;

	boolean solveSudoku(){
		int[] pos;
		
		if(unassigned.isEmpty())
			return true;
		pos=unassigned.pop();counter++;
		if(pos==null) return true;

		for(int i=1;i<=n;i++){
			counter+=((3*n)-(2*root_n)-1);
			if(!adjlist[pos[0]][pos[1]].check(i,grid,n)){
				grid[pos[0]][pos[1]]=i; counter++;
			//	System.out.println("\n");
			//	printGrid(n*n);
				if(solveSudoku()) return true; 
				grid[pos[0]][pos[1]]=-1;counter++;
			}
		}unassigned.push(pos);counter++;
		
	return false;
	}


	private int[][] generateGrid() {
		int[][] tgrid=new int[n+1][n+1];
		unassigned=new SudokuStack();
	//	grid[0][0]=-1;
		
		for(int i=1;i<=n;i++){
		for(int j=1;j<=n;j++){
			int[] temp=new int[2];
		//	System.out.println("["+i+"]["+j+"]=");
			tgrid[i][j]=-1;
			temp[0]=i;
			temp[1]=j;
			unassigned.push(temp);
		}
			
		} 
		return tgrid;
	}
	
	void printGrid(int len){
		for(int i=1;i<=n;i++){
			for(int j=1;j<=n;j++)
				System.out.print(grid[i][j]+"\t");
			System.out.print("\n");
		}
	}

	private void createLists() {
	
		adjlist=new LinkedList[n+1][n+1];
		for(int i=1;i<=n;i++){
			for(int j=1;j<=n;j++){
				
				adjlist[i][j]=new LinkedList();

				for(int k=1;k<=n;k++){
					if(k==j) continue;
						counter++;
					int[] x={i,k};
					adjlist[i][j].add(x);	
		
				}// check all values in the same row 

				for(int k=1;k<=n;k++){
					if(k==i) continue;
					counter++;
					int[] x={k,j};
					adjlist[i][j].add(x);	
		
				}// check all values in the same column
				
				int k,l;

				if(i%root_n==0)
					k=i-root_n+1;
				else
					k=i-i%root_n+1;
				

				if(j%root_n==0)
					l=j-root_n+1;
				else
					l=j-j%root_n+1;
					
				for(int k_cnt=1;k_cnt<=root_n;k++,k_cnt++){
					if(k==i){ continue;}
					for(int l_cnt=1;l_cnt<=root_n;l_cnt++,l++){
						if(l==j) { continue;}
						counter++;
						int[] x={k,l};
						adjlist[i][j].add(x);	
		
					}l=l-root_n;
				}//checked all block values
		
			
			}
		}		
	}
	
	private int[][] problemInstance() {
		int tgrid[][]=new int[10][10];
		BufferedReader br = null;
		unassigned=new SudokuStack();
		try {
			br = new BufferedReader(new FileReader("G:/SkyDrive/Project/MyCode/givens - 2/inputwith17filled.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String s1;
		String[] s2;
		int i=1;
		try {
			while((s1=br.readLine())!=null){
				s2=s1.split(" ");
				for(int j=1;j<=n;j++){	
					int[] temp=new int[2];
				//	System.out.println("["+i+"]["+j+"]=");
					tgrid[i][j]=Integer.parseInt(s2[(j-1)]);
				//	System.out.println(" i"+i+" j"+j );
					temp[0]=i;
					temp[1]=j;
					if(tgrid[i][j]==-1) unassigned.push(temp);
				}i++;
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tgrid;
		
	}
	
	public static void main(String args[]){
		Sudoku S=new Sudoku();
		S.n=9;
		S.root_n=(int)Math.sqrt(S.n);
		S.grid=S.generateGrid();
		S.grid=S.problemInstance();
	//	S.printGrid(S.n*S.n);
		S.createLists();
		System.out.println("size of grid is "+(S.grid.length-1));
		if(S.solveSudoku()){
			System.out.println("Result: ");
			S.printGrid(S.n*S.n);
		}
		else
			System.out.println("Unsuccessful! This Sudoku is Unsolvable :(");
		System.out.println("Running time: "+S.counter);
	}
}
