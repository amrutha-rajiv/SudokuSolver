
public class LinkedList {
	class cell{
		public int val[];
		public cell next;
	}
	
	public cell head;
	
	LinkedList(){ 
		head=null;
	}

	public boolean isEmpty() {
		if (head==null) return true;
		return false;
	}

	public void remove(int[] val) {
		cell temp = null;
		if (!this.isEmpty())
		{
			temp=head;
			cell prev = null;

			if((temp.val[0]!=val[0])&&(temp.val[1]!=val[1])&&(temp.next!=null)){ prev=temp; temp=temp.next ;}
			if((temp.val[0]==val[0])&&(temp.val[0]==val[0])){
				if(temp==head)
					head=temp.next;
				else
					prev.next=temp.next;
			}
			
		}
		
	}

	public void add (int[] x) {
		cell temp=new cell();
		if(head==null) head=new cell();
		temp.val=x;
		temp.next=head;
		head=temp;
	}
	
	public boolean check(int x, int[][] grid,int n){
		if(head==null) return false;
		cell temp=head;
		while(temp.next!=null){
//			System.out.println("pos : "+temp.val[0]+" "+temp.val[1]);
			if(grid[temp.val[0]][temp.val[1]]==x) return true;
			temp=temp.next;
		}
		return false;
		
	}

}
