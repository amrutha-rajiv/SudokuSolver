
public class SudokuStack {
	
	public Node top;
	
	SudokuStack(){ 
		top=null;
	}

	public boolean isEmpty() {
		if (top==null) return true;
		return false;
	}

	public int[] pop() {
		Node temp = null;
		if (!this.isEmpty())
		{
			temp=top;
			if(temp.next!=null)top=temp.next ;
			else top=null;
			return temp.val;
		}
		temp.val[0]=-1;
		temp.val[1]=-1;
		return temp.val;
	}

	public void push(int pos[]) {
		Node temp=new Node();
		if(top==null) top=new Node();
		temp.val=pos;
		temp.next=top;
		top=temp;
	}
	
	
		
	
}
