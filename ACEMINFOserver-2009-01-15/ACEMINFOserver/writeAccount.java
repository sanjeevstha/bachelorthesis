import java .io.*;
public class writeAccount
{
FileOutputStream out; // declare a file output object
PrintStream p; // declare a print stream object

public writeAccount(String date,String number,String out_msg)
{
	try
	{	
		out = new FileOutputStream("./Data/OutputSMS.txt");
     		// Connect print stream to the output stream
		p = new PrintStream( out );
		p.println (date+":"+number+":"+out_msg);
		p.close();
	}

	catch(Exception e)
		{
		System.out.println(e);
		}
	

}
	
}
