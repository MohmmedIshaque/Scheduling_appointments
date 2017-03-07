import static org.junit.Assert.*;

import org.junit.Test;


public class Reschedule_test {

	@Test
	public void test() {
		appointment_scheduling example=new appointment_scheduling();
		String test="2.0,2.59,Patient_1 ";
		assertEquals(test, example.insert_routine("Doc1,01/01/2017,2.0,2.59,Patient_1")); 
	
		test="2.0,2.59,Patient_1 4.0,4.59,Patient_2 ";
		assertEquals(test, example.insert_routine("Doc1,01/01/2017,4.0,4.59,Patient_2"));
		
		//reschedule successful,appointment to remove was present,and new appointment had no conflict
		test="2.0,2.59,Patient_1 5.0,5.59,Patient_2 ";
		assertEquals(test, example.reschedule_routine("Doc1,01/01/2017,5.0,5.59,Patient_2","Doc1,01/01/2017,4.0,4.59,Patient_2"));
	
		//reschedule unsuccessful,appointment to remove was not present
		test="2.0,2.59,Patient_1 5.0,5.59,Patient_2 ";
		assertEquals(test, example.reschedule_routine("Doc1,01/01/2017,6.0,6.59,Patient_2","Doc1,01/01/2017,5.0,5.59,No_Patient"));
		
		}

}
