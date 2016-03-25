
public class poly implements Comparable<poly> { 

	private int power; //initialize values for coefficient and degree set to private
	private int base;

	public String toString() {

		return (base + "^" + getPower());
	}

	public poly(int b, int p) { //single constructor used to create new polynomial terms using incoming integers b,p

		if(b == 0) {System.out.println("No zero coefficients accepted.");
		System.exit(1);
		}
		base = b;
		setPower(p);

	}

	public int compareTo(poly o) { //compare two polynomial terms to evaluate smaller and larger
		if (this.power>o.power) return 1;
		else if(this.power<o.power)return -1; 
		return 0;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}
	
	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}


}
