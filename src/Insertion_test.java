import static org.junit.Assert.*;
import org.junit.Test;


public class Insertion_test {

	@Test
	public void test1() {
		appointment_scheduling example=new appointment_scheduling();
		//successful
		String test="2.0,2.59,Patient_1 ";
		assertEquals(test, example.insert_routine("Doc1,01/01/2017,2.0,2.59,Patient_1")); 
	
		//successful
		test="2.0,2.59,Patient_1 4.0,4.59,Patient_2 ";
		assertEquals(test, example.insert_routine("Doc1,01/01/2017,4.0,4.59,Patient_2")); 
	
		//successful
		test="1.0,1.3,Patient_3 2.0,2.59,Patient_1 4.0,4.59,Patient_2 ";
		assertEquals(test, example.insert_routine("Doc1,01/01/2017,1.0,1.3,Patient_3")); 
	
		//successful
		test="1.0,1.3,Patient_3 2.0,2.59,Patient_1 4.0,4.59,Patient_2 12.0,13.3,Patient_4 ";
		assertEquals(test, example.insert_routine("Doc1,01/01/2017,12.0,13.3,Patient_4")); 
	
		//unsuccessful,collision with 2.0,2.59,Patient_1
		test="1.0,1.3,Patient_3 2.0,2.59,Patient_1 4.0,4.59,Patient_2 12.0,13.3,Patient_4 ";
		assertEquals(test, example.insert_routine("Doc1,01/01/2017,2.30,3.30,Patient_5")); 
	
		//unsuccessful,collision with 2.0,2.59,Patient_1 and others
		test="1.0,1.3,Patient_3 2.0,2.59,Patient_1 4.0,4.59,Patient_2 12.0,13.3,Patient_4 ";
		assertEquals(test, example.insert_routine("Doc1,01/01/2017,0.30,5.0,Patient_6")); 
	
		//unsuccessful,collision with 2.0,2.59,Patient_1	
		test="1.0,1.3,Patient_3 2.0,2.59,Patient_1 4.0,4.59,Patient_2 12.0,13.3,Patient_4 ";
		assertEquals(test, example.insert_routine("Doc1,01/01/2017,2.15,2.45,Patient_7")); 
	
		//unsuccessful,collision with 2.0,2.59,Patient_1
		test="1.0,1.3,Patient_3 2.0,2.59,Patient_1 4.0,4.59,Patient_2 12.0,13.3,Patient_4 ";
		assertEquals(test, example.insert_routine("Doc1,01/01/2017,1.45,2.15,Patient_8")); 
	
		//unsuccessful,collision with 2.0,2.59,Patient_1
		test="1.0,1.3,Patient_3 2.0,2.59,Patient_1 4.0,4.59,Patient_2 12.0,13.3,Patient_4 ";
		assertEquals(test, example.insert_routine("Doc1,01/01/2017,2.45,3.15,Patient_9")); 
	
		//unsuccessful,collision with 4.0,4.59,Patient_2
		test="1.0,1.3,Patient_3 2.0,2.59,Patient_1 4.0,4.59,Patient_2 12.0,13.3,Patient_4 ";
		assertEquals(test, example.insert_routine("Doc1,01/01/2017,3.0,4.0,Patient_10")); 
	
		//successful,insertion with different date
		test="2.0,2.59,Patient_11 ";
		assertEquals(test, example.insert_routine("Doc1,01/02/2017,2.0,2.59,Patient_11")); 
	
		//successful,insertion with different doctor/physician
		test="2.0,2.59,Patient_12 ";
		assertEquals(test, example.insert_routine("Doc2,01/01/2017,2.0,2.59,Patient_12")); 
	}
}
