import java.util.*;
// Represents linked list of appointments of a particular doctor on a particular date.
//ex: (appoint1_>appoint2->appoint3->null) for doc1 on 01/01/2017.
class doctor_register
{
    appointment front;//front of the linked list of appointments
    boolean conflict=false;//whether the appointment we want to insert conflicts with existing appointments or not.
			
    public doctor_register()
    {
        front = null;
    }   
	
    public boolean insert(float start_t,float end_t,String patient_ID)//insert an appointment to the linked list,i will be using
	//two pointers to traverse the linked list:current_pointer and previous_of_current_pointer
    {
        appointment new_appointment = new appointment(start_t,end_t,null,patient_ID);
        if (front == null)//no appointment exists
        {
            front = new_appointment;
            conflict=false;
        }
        else//linked list is not empty
		{
			appointment current_pointer=front;//position of current node(appointment)
			appointment previous_of_current_pointer=null;//position of previous(appointment) to current node(appointment)
			boolean continue_finding=true;//to stop the loop when we have found the correct position or found that conflict occurs on insertion
			conflict=false;
			while(continue_finding)
			{	
				if(current_pointer.start_time<new_appointment.start_time && current_pointer.end_time<new_appointment.start_time)//ex:current_node 2.0-2.59(start and end time),wants to insert 4.0-4.59(start and end time)
				{
					if(current_pointer.next_appointment!=null)//not inserting at last position of linked list
					{
						previous_of_current_pointer=current_pointer;
						current_pointer=current_pointer.next_appointment;
					}
					else//inserting at last position of linked list
					{
						current_pointer.next_appointment=new_appointment;
						continue_finding=false;
					}
					conflict=false;
				}
				else
				{ 
					if(current_pointer.start_time>new_appointment.start_time && current_pointer.start_time>new_appointment.end_time)//ex:current_node 2.0-2.59(start and end time),wants to insert 1.0-1.29(start and end time)
					{
						if (previous_of_current_pointer!=null)//not inserting at front of linked list
						{
							previous_of_current_pointer.next_appointment=new_appointment;
						}
						else//inserting at front of linked list
						{
							front=new_appointment;
						}
							
						new_appointment.next_appointment=current_pointer;
						continue_finding=false;
						conflict=false;
					}
					else//all these cases are when conflict arises
					{
						if(current_pointer.start_time>new_appointment.start_time && current_pointer.start_time<new_appointment.end_time && current_pointer.end_time>new_appointment.end_time)
						//ex:current_node 2.0-2.59(start and end time),wants to insert 1.30-2.29(start and end time)
						{
							conflict=true;
							continue_finding=false;
						}
						else
						{
							if(current_pointer.start_time>new_appointment.start_time && current_pointer.end_time<new_appointment.end_time)
							//ex:current_node 2.0-2.59(start and end time),wants to insert 1.0-3.59(start and end time)
							{
								conflict=true;
								continue_finding=false;
							}
							else
							{
								if(current_pointer.start_time<new_appointment.start_time && current_pointer.end_time>new_appointment.start_time)
								//ex:current_node 2.0-2.59(start and end time),wants to insert 2.30-anything(start and end time)
								{
									conflict=true;
									continue_finding=false;
								}
								else
								{
									if(current_pointer.start_time==new_appointment.start_time || current_pointer.end_time==new_appointment.start_time || current_pointer.start_time==new_appointment.end_time || current_pointer.end_time==new_appointment.end_time)
								//conflict at borders like between 1.0-2.0 and 2.0-3.0,correct appointments will have timings 1.0-1.59-2.0-2.59
								//ex:current_node 2.0-3.0(start and end time),wants to insert 1.30-2.0(start and end time) or 3.0-3.29(start and end time)
								{
									conflict=true;
									continue_finding=false;
								}
								}
							}
						}      
					}
				}
				
			}
		}
        return !conflict;
    }  
	
    public boolean remove(float start_t,float end_t,String patient_ID)//remove an appointment
    {
        appointment current_pointer_remove=front;//same as previous
		appointment previous_of_current_pointer_remove=null;//same as previous
		boolean removal_done=false;//whether removal was successful,(does the appointment we want to remove exists)
		
		while(current_pointer_remove!=null)//till we have traversed the whole linked list
		{
			if(current_pointer_remove.start_time==start_t && current_pointer_remove.end_time==end_t && current_pointer_remove.patient_id.equals(patient_ID))
			//we found the appointment we want to delete
			{
				if(previous_of_current_pointer_remove!=null)//appointment we want to remove is not first in linked list
				{
					previous_of_current_pointer_remove.next_appointment=current_pointer_remove.next_appointment;
					removal_done=true;
					break;
				}
				else//appointment we want to remove is first in linked list
				{	
					front=current_pointer_remove.next_appointment;
					removal_done=true;
					break;
				}
			}
			else	
			//keep on moving forward in the linke list	
			{
				previous_of_current_pointer_remove=current_pointer_remove;
				current_pointer_remove=current_pointer_remove.next_appointment;
			}
		}
		
		return removal_done;
			
    }    
        
    public String display()//display all appointments present in the linked list.
    {	
		String List_of_appointments="";
        System.out.print("\nAppointments = ");
        appointment current_pointer_display = front;
        
		while (current_pointer_display != null )//traverse till the end of the linked list
        {
            List_of_appointments=List_of_appointments+current_pointer_display.start_time+","+current_pointer_display.end_time+","+current_pointer_display.patient_id+" ";
            current_pointer_display = current_pointer_display.next_appointment;
        }
        
		return List_of_appointments;
    }
}
