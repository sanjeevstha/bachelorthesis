import java.io.*;
import java.util.*;

class FileInput
{
    public static void main(String args[])
	{
     	Thread th=new Thread();
	
          try
			{
	           	FileInputStream fstream = new FileInputStream("./Data/InputSMS.txt");
			DataInputStream in = new DataInputStream(fstream);
			String s=new String();
			String sms=new String();
			
			System.out.println("WAITING FOR THE STRING.");
           		s=in.readLine();
			 while (true)
				{
				System.out.print(".");
				th.sleep(1000);
				s=in.readLine();
				if(s!=null)
					{
					sms=s.toString();
					if(sms!="")
						{
						new SmsParser(sms);
						}
					}
				}
				
			} 
                        catch (Exception e)
			{
				System.err.println(e);
			}
		
	}
}


