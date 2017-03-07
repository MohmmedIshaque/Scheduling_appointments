import java.util.*;

//our main class through which we will insert,delete and reschedule appointments
public class appointment_scheduling
{    
	public static String tempinput[];//store the 5 appointment details like doc_name,date,start_time,end_time,patient_name in string array
	public static String key;//key of a hashmap consisting of doc_name+date as string
	public static String new_schedule_of_this_doctor="";//linked list of appointments after changes has been made(insertion,deletion or rescheduling)
	public static doctor_register this_doctor_register;//linked list of appoinments we want to make changes to.
	public static boolean no_use_boolean;//of no use,just to consume boolean returns which we will not require this time.
	public static boolean insert_boolean;//whether insertion was successful
	public static boolean remove_boolean;//whether removal was successful
	public static Scanner scan = new Scanner(System.in); 
	public static HashMap <String, doctor_register> all_doctors_register= new HashMap<String, doctor_register>();
	//hashmap with key doc_name+dat as key and value=linked list of appointments of that particular doctor on tht particular date.


	public String insert_routine(String appointment_to_insert)
	{	
		tempinput=appointment_to_insert.split(",");
		key=tempinput[0]+tempinput[1];//key=doc_name+dat
		this_doctor_register=all_doctors_register.get(key);//getting the linked list of appointments to modify

		if(this_doctor_register==null)//no entry exists for this key in hashmap
		{
			doctor_register lq = new doctor_register();//create new linked list of appointments            
			insert_boolean=lq.insert(Float.parseFloat(tempinput[2]),Float.parseFloat(tempinput[3]),tempinput[4] );//insert the appointment to the newly created linked list
			all_doctors_register.put(key,lq);//put the (key,linked list) in the hashmap
			new_schedule_of_this_doctor=lq.display();//display the changes
		}
		else//entry exists for this key in hashmap
		{
			insert_boolean=this_doctor_register.insert(Float.parseFloat(tempinput[2]),Float.parseFloat(tempinput[3]),tempinput[4] );//try to insert the appointment
			new_schedule_of_this_doctor=this_doctor_register.display();
			//return the string form of linked lists of appointments,whether appointment was inserted or not due to conflict
		}

		return new_schedule_of_this_doctor;	
	}

	public String remove_routine(String appointment_to_remove)
	{
		tempinput=appointment_to_remove.split(",");
		key=tempinput[0]+tempinput[1];
		this_doctor_register=all_doctors_register.get(key);//getting the linked list of appointments to modify

		if(this_doctor_register!=null)//entry exists for this key in hashmap
		{
			remove_boolean=this_doctor_register.remove(Float.parseFloat(tempinput[2]),Float.parseFloat(tempinput[3]),tempinput[4] );//try to remove the appointment
			new_schedule_of_this_doctor=this_doctor_register.display();
			//return the string form of linked lists of appointments,whether appointment was removed or not
		}	
		else//no entry exists for this key in hashmap
		{
			new_schedule_of_this_doctor="";
		}
		return new_schedule_of_this_doctor;
	}

	public String reschedule_routine(String appointment_to_insert,String appointment_to_remove)
	{
		insert_boolean=false;
		remove_boolean=false;
		String no_use;
		no_use=insert_routine(appointment_to_insert);
		
		if(insert_boolean)//if insertion successful
		{
			no_use=remove_routine(appointment_to_remove);
			if(!remove_boolean)//if removal fails
			{
				remove_routine(appointment_to_insert);//revert back the insertion
			}
		}

		new_schedule_of_this_doctor=this_doctor_register.display();
		return new_schedule_of_this_doctor;
	}

}