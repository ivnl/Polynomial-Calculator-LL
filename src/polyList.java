public class polyList {

	polyNode first = new polyNode();
	polyNode last = first;
	int length = 0;

	public polyList() {

	}

	/**
	 * Evaluates one polynomial with the given integer and returns the final sum
	 *
	 * @param  polyList (z)  the given polyList to do an evaluation on
	 * @param  int (k) the given integer to use with the evaluation
	 * @return none - the sum is returned within a system out statement
	 * @runTime O(n)
	 */
	public static void evaluate(polyList z, int k) {

		System.out.println();
		polyNode a = z.first.next;
		System.out.print("Evaluation of polynomial: ");
		z.print();
		System.out.print("Using the integer value of " + "(" + k + ")");

		int sum = 0;

		while (a != null) {

			if (a.data.getBase() != 0) {
				sum += (int) (a.data.getBase() * Math.pow((k), (a.data.getPower())));
			} else {
				sum += a.data.getBase();
			}

			a = a.next;
		}

		System.out.print(" results in a sum of: " + "(" + sum + ")");

	}
	
	/**
	 * Computes the sum of two polynomial lists in order of first + next (this + x)
	 *
	 * @param  polyList (this) the first polynomial to add to
	 * @param  polyList (x) the second polynomial to add with the first
	 * @return polyList (sum) - the sum of both polynomials is returned
	 * @runTime O(n)
	 */
	public polyList add(polyList x) {

		polyList sum = new polyList();
		System.out.println("\nsum: ");

		polyNode l = larger(this, x).first;
		polyNode s = smaller(this, x).first;

		while (s.next != null) {

			if (l.next != null && l.next.data.getPower() == s.next.data.getPower()) {
				if ((l.next.data.getBase() + s.next.data.getBase() != 0)) {
					poly p = new poly(l.next.data.getBase() + s.next.data.getBase(), l.next.data.getPower());
					sum.append(p);
				}
				l = l.next;
				s = s.next;
			}

			else if (l.next != null && l.next.data.getPower() > s.next.data.getPower()) {
				// System.out.print("b");
				sum.append(new poly(l.next.data.getBase(), l.next.data.getPower()));
				l = l.next;
			}

			else if (l.next != null && l.next.data.getPower() < s.next.data.getPower()) {
				// System.out.print("c");

				poly n = new poly(s.next.data.getBase(), s.next.data.getPower());
				sum.append(n);
				s = s.next;
			}

			else if (s.next != null && l.next == null) {
				// System.out.print("d");

				sum.append(new poly(s.next.data.getBase(), s.next.data.getPower()));
				s = s.next;
			}

			else if (l.next != null && s.next == null) {
				// System.out.print("e");

				poly z = new poly(l.next.data.getBase(), l.next.data.getPower());
				sum.append(z);
				l = l.next;
			}

		}

		return sum;
	}

	/**
	 * Computes the difference between the first polynomial taking away the second polynomial(x)
	 *
	 * @param  polyList (this) the first polynomial to subtract from
	 * @param  polyList (x) the second polynomial to take away from the first
	 * @return polyList (diff) - the difference of the polynomials is returned 
	 * @runTime O(n)
	 */
	public polyList sub(polyList x) {

		polyList diff = new polyList();
		System.out.println("\ndifference: ");

		polyNode a = this.first;
		polyNode b = x.first;

		while (a.next != null) {

			if (b.next != null && a.next.data.getPower() == b.next.data.getPower()) {
				if ((a.next.data.getBase() - b.next.data.getBase() != 0)) {
					poly p = new poly(a.next.data.getBase() - b.next.data.getBase(), a.next.data.getPower());
					diff.append(p);
				}
				a = a.next;
				b = b.next;
			}

			else if (b.next != null && a.next.data.getPower() > b.next.data.getPower()) {
				poly m = new poly(a.next.data.getBase(), a.next.data.getPower());
				diff.append(m);
				a = a.next;
			}

			else if (b.next != null && a.next.data.getPower() < b.next.data.getPower()) {
				poly n = new poly(-b.next.data.getBase(), b.next.data.getPower());
				diff.append(n);
				b = b.next;
			}

			else if (a.next != null) {
				poly z = new poly(a.next.data.getBase(), a.next.data.getPower());
				diff.append(z);
				a = a.next;
			} else if (b.next != null) {
				poly g = new poly(b.next.data.getBase(), b.next.data.getPower());
				diff.append(g);
				b = b.next;
			}

		}
		return diff;
	}

	/**
	 * Computes the product of two given polynomial lists
	 *
	 * @param  polyList (this) the first polynomial to be multiplied
	 * @param  polyList (x) the second polynomial to multiply with the first
	 * @return polyList (product) - the product of both polynomials is returned
	 * @runTime O(n^2)
	 */
	public polyList multi(polyList x) {

		polyList product = new polyList();
		System.out.println("\nproduct: ");
		polyNode i = this.first.next;
		polyNode j = x.first.next;

		while (j != null) {

			i = this.first.next; // reset the i list to check against again
			while (i != null) {
				poly p = new poly(i.data.getBase() * j.data.getBase(), i.data.getPower() + j.data.getPower());
				product.insert(p);
				i = i.next;
			}

			j = j.next;
		}

		return product;

	}
	
	/**
	 * Used to 'insert' polynomial terms in sorted descending order after computation with compareTo
	 *
	 * @param  poly (x) the incoming term to be sorted 'x'
	 * @return none
	 * @runTime O(n)
	 */

	public void insert(poly x) {
		polyNode s = new polyNode(x); //incoming node s with the value of 'x'
		polyNode a = first; //pointer before
		polyNode b = a.next; //pointer after

		while (b != null) {

			int preBase = 0, prePower = 0; //initialize preBase and prePower to store previous values

			//if degree of next node is equal to incoming term's degree, and their coefficients add up to '0'
			//we reset the degree and coefficient of next node to previous values and skip current node
			if (b.data.getPower() == x.getPower() && b.data.getBase() + s.data.getBase() == 0) {
				b.data.setBase(preBase);
				b.data.setPower(prePower);
				length--;
				break;
			}

			//if degree of next node is equal to incoming term's degree, and their coefficients do NOT add up to '0'
			//we store values to preBase and prePower to use in future cases of two terms adding to '0'
			//combine coefficients and move to next node
			else if (b.data.getPower() == x.getPower() && b.data.getBase() + s.data.getBase() != 0) {
				preBase = b.data.getBase() + s.data.getBase();
				prePower = b.data.getPower() + s.data.getPower();
				b.data.setBase(b.data.getBase() + x.getBase());
				break;
			}

			//if incoming term has greater power than current term, we insert incoming term inbetween last and current terms
			else if (s.data.getPower() > b.data.getPower()) {
				a.next = s;
				s.next = b;
				length++;
				break;
			}

			//move the linked list along set pointers
			a = b;
			b = b.next;
		}

		//if list is empty, append incoming data
		if (b == null) {
			append(s.data);
		}

	}

	/**
	 * Evaluates the greater between two given polynomial lists and returns the larger
	 * if list sizes are the same length, return larger as the first list and smaller as second
	 *
	 * @param  polyList (this) the first polynomial to check
	 * @param  polyList (x) the second polynomial to check
	 * @return polyList (j/k) - return j if j is larger else return k
	 * @runTime O(1)
	 */
	private polyList larger(polyList j, polyList k) {

		if (j.length > k.length)
			return j;
		else if (j.length == k.length)
			return j;
		else
			return k;

	}

	/**
	 * Evaluates the lesser between two given polynomial lists and returns the larger
	 * if list sizes are the same length, return larger as the first list and smaller as second
	 *
	 * @param  polyList (this) the first polynomial to check
	 * @param  polyList (x) the second polynomial to check
	 * @return polyList (r/v) - return r if r is smaller else return v
	 * @runTime O(1)
	 */
	private polyList smaller(polyList r, polyList v) {

		if (r.length < v.length)
			return r;
		else if (r.length == v.length)
			return v;
		else
			return v;
	}

	public void append(poly x) { // append method to attach items to end of
									// linked list
		polyNode n = new polyNode(x); // create new node 'n' with data from 's'
		last.next = n; // point the PREVIOUS last node on the linked list to new
						// node 'n'
		last = n; // the idea behind 'append' is that we attach a node to the
					// end of the list, so 'n' becomes the new last node
		length++;
	}

	public int getLength() {
		return length;
	}

	public String toString() {
		polyNode p = first.next;
		String returnString = "";
		while (p != null) {
			returnString += p.data + " ";
			p = p.next;
		}
		return returnString;
	}

	public void print() {

		polyNode a = this.first;

		while (a.next != null) {

			if (a.next.next == null && a.next.data.getPower() != 0 && a.next.data.getPower() != 1) // normal case exclude 0 and 1
				System.out.print(a.next.data.getBase() + "x^" + a.next.data.getPower() + " ");
			else if (a.next.data.getPower() == 1 && a.next.next == null) // 1 pwr case exponent at end
				System.out.print(a.next.data.getBase() + "x");
			else if (a.next.data.getPower() == 1 && a.next.next.data.getBase() > 0) // 1 pwr case exponent at middle
				System.out.print(a.next.data.getBase() + "x^" + a.next.data.getPower() + " + ");
			else if (a.next.data.getPower() == 0 && a.next.next == null) // 0 pwr case at the end
				System.out.print(a.next.data.getBase() + " ");
			else if (a.next.data.getPower() == 0) // 0 pwr case exponent in middle
				System.out.print(a.next.data.getBase() + " + ");
			else if (a.next.next.data.getBase() > 0) // normal case middle
				System.out.print(a.next.data.getBase() + "x^" + a.next.data.getPower() + " + ");
			else if (a.next.data.getBase() < 0 && a.next.next.data.getBase() < 0) // negative case 1: next term is neg
				System.out.print(a.next.data.getBase() + "x^" + a.next.data.getPower() + " ");
			else if (a.next.next.data.getBase() < 0) // negative case 2: next term is not negative
				System.out.print(a.next.data.getBase() + "x^" + a.next.data.getPower() + " ");
			a = a.next;
		}
		System.out.println();
	}

}
