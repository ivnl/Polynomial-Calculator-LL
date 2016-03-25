public class main {

	public static void main(String[] args) {

		polyList pOne = new polyList(); //create two poly list objects 'pOne, pTwo'
		polyList pTwo = new polyList();

		//pOne.insert(new poly(300,500));
		pOne.insert(new poly(10, 10));
		pOne.insert(new poly(3, 4));
		pOne.insert(new poly(2, 3));
		pOne.insert(new poly(1, 1));
		pOne.insert(new poly(1, 0)); //append terms to end of polyList pOne (coefficient, degree)

		//pTwo.insert(new poly(100, 200));
		pTwo.insert(new poly(1, 1));
		pTwo.insert(new poly(-2, 0)); //append terms to end of polyList pTwo

		System.out.print("f(x): ");pOne.print(); //print out lists
		System.out.print("g(x): ");pTwo.print();

		pOne.add(pTwo).print(); //call addition method 'add' and print result

		pOne.sub(pTwo).print(); //call subtraction method 'sub' and print result
		
		pOne.multi(pTwo).print(); //call multiplication method 'multi' and print result
		
		polyList.evaluate(pOne, 2); //call evaluation method using list pOne and value of '2'
		

	}

}
