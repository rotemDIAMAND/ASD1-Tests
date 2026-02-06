package test1;

public class MainTrain2 {

	public static void main(String[] args) {
		ConditionsManager<Integer> cm=new ConditionsManager<>();
		cm.update("age", x->x>=18 && x<=45);// age 18-45
		cm.update("experience", x->x>5 ); 	// experience of 5 years
		cm.update("average", x->x>=85);		// college average
		
		if(cm.test("degree",3))
			System.out.println("wrong condition result1 (-5)");
		
		if(cm.test("age", 17))
			System.out.println("wrong condition result2 (-5)");

		if(cm.test("age", 46))
			System.out.println("wrong condition result3 (-5)");

		if(!cm.test("age", 25))
			System.out.println("wrong condition result4 (-5)");
		
		if(!cm.test("experience", 6))
			System.out.println("wrong condition result5 (-3)");
		
		if(!cm.test("average", 85))
			System.out.println("wrong condition result6 (-3)");
		
		cm.update("age", x->x>21);

		if(!cm.test("age", 60))
			System.out.println("wrong condition result7 (-3)");

		if(cm.test("age", 19))
			System.out.println("wrong condition result8 (-3)");
		
		System.out.println("done");
	}

}
