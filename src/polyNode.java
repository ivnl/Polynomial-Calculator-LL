public class polyNode {

	public poly data;
	public polyNode next;

	
	public polyNode(poly d) { // constructor for incoming node creation
		this.data = d;
		this.next = null;

	}

	public polyNode() { // constructor for dummy
		this.data = null;
		this.next = null;
	} 
	
}
