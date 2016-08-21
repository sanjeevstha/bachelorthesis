import java.sql.*;
import java.io.*;


public class writeResult

{
	FileOutputStream out; // declare a file output object
	PrintStream p; // declare a print stream object
	
	public writeResult(String date,String number,String rep_msg)
	{
	try
	{
	out = new FileOutputStream("./Data/OutputSMS.txt");
     // Connect print stream to the output stream
    p = new PrintStream( out );
    p.println (date+":"+number+":"+rep_msg);
    p.close();
	
	}catch(Exception e)
	{
	System.out.println(e);
	}
	}
}




