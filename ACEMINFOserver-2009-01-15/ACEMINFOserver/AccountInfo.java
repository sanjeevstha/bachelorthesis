import java.sql.*;
import java.io.*;


public class AccountInfo
{
	Connection con;
	Statement stmt;
	ResultSet rs,rs1;

public AccountInfo(String sms_date,String sms_number,String sms_msg)
	{
	String date=sms_date;
	String number=sms_number;
	String msg=sms_msg;
	int len=msg.length();     
	String bal=msg.substring(15,len);
	String Id=msg.substring(4,14);
		
	try
	{
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	String connectionUrl = "jdbc:sqlserver://192.168.0.35:1433;" +"databaseName=ACEM_db;user=sa;password=123456;";
	con = DriverManager.getConnection(connectionUrl);
	stmt=con.createStatement();
	rs=stmt.executeQuery("Select * from Account_Info where Std_Id= '"+Id+"'");
	
	if(rs.next())
		{
		if(bal.equalsIgnoreCase("PreBal"))
			{
			String PreBal=rs.getString(2);
			rs1=stmt.executeQuery("Select * from Std_Info where Std_Id= '"+Id+"'");	
			rs1.next();	
			//String fn=rs1.getString(2);
			//int i=fn.length();	
			//String fn1=fn.substring(1,i);
			String name=rs1.getString(2)+" "+rs1.getString(3)+" "+rs1.getString(4);
			String out_msg="The PreBalance of "+name+" is Rs. "+PreBal;
			new writeAccount(date,number,out_msg);
			//System.out.println(out_msg);
			}
		else if(bal.equalsIgnoreCase("AmtPd"))
			{
			String AmtPd=rs.getString(3);
			rs1=stmt.executeQuery("Select * from Std_Info where Std_Id= '"+Id+"'");	
			rs1.next();
			String name=rs1.getString(2)+' '+rs1.getString(3)+' '+rs1.getString(4);
			String out_msg="The AmountPaid for "+name+" is Rs."+AmtPd;
			new writeAccount(date,number,out_msg);
			//System.out.println(AmtPd);
			}
		else if(bal.equalsIgnoreCase("BalDue"))
			{
			String BalDue=rs.getString(4);
			rs1=stmt.executeQuery("Select * from Std_Info where Std_Id= '"+Id+"'");
			rs1.next();
			String name=rs1.getString(2) +rs1.getString(3) +rs1.getString(4);
			String out_msg="The BalanceDue of "+name+" is Rs."+BalDue;
			new writeAccount(date,number,out_msg);
			//System.out.println(BalDue);
			}
		else if(bal.equalsIgnoreCase("TotAmt"))
			{
			String TotAmt=rs.getString(5);
			rs1=stmt.executeQuery("Select * from Std_Info where Std_Id= '"+Id+"'");
			rs1.next();
			String name=rs1.getString(2) +rs1.getString(3) +rs1.getString(4);
			String out_msg="The TotalAmount of "+name+" is Rs."+TotAmt;
			new writeAccount(date,number,out_msg);
			//System.out.println(TotAmt);
			}
		else
			new writeAccount(date,number,"Sorry code Error!! No such code exists.");
		}
	else
		new writeAccount(date,number,"Sorry! The ID you have typed is not found.");
	

	}catch(Exception e)
	{
	System.out.println(e);
	}


	}
}



