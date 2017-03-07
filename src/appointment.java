import java.util.*;

//objects of this class represent a particular appointment scheduled for a particular doctor at a particular date (ex doc1,01/01/2017)
public class appointment
{
    float start_time;
	float end_time;
    appointment next_appointment;//points to the next appointment of that doctor on that particular date(ex doc1,01/01/2017)
	String patient_id;
 
        
    public appointment(float start_t,float end_t,appointment next_appoint,String patient_ID)
    {
        start_time = start_t;
        end_time = end_t;
		next_appointment=next_appoint;
		patient_id=patient_ID;
    
	}    
    
}