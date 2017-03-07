import static org.junit.Assert.*;

import org.junit.Test;


public class Removal_test {

	@Test
	public void test() {
		appointment_scheduling example=new appointment_scheduling();
		String test="2.0,2.59,Patient_1 ";
		assertEquals(test, example.insert_routine("Doc1,01/01/2017,2.0,2.59,Patient_1")); 
	
		
		test="2.0,2.59,Patient_1 4.0,4.59,Patient_2 ";
		assertEquals(test, example.insert_routine("Doc1,01/01/2017,4.0,4.59,Patient_2"));
		
		//removal succesful,appointment to remove was present
		test="4.0,4.59,Patient_2 ";
		assertEquals(test, example.remove_routine("Doc1,01/01/2017,2.0,2.59,Patient_1"));
		
		//removal unsuccesful,appointment to remove was not in the linked list
		test="4.0,4.59,Patient_2 ";
		assertEquals(test, example.remove_routine("Doc1,01/01/2017,4.0,4.59,No_patient"));
		
	}

}
