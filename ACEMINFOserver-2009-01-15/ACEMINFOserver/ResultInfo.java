import java.sql.*;
import java.io.*;
public class ResultInfo
{
	Connection con;
	Statement stmt,stmt1;
	ResultSet rs,rs1;
	
	public ResultInfo(String sms_date,String sms_number,String sms_msg)
	{
	String date=sms_date;
	String number=sms_number;
	String msg=sms_msg;
	int len=msg.length();     
	String sem=msg.substring(15,len);
	String Id=msg.substring(4,14);
	try
	{
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl = "jdbc:sqlserver://192.168.0.35:1433;" +"databaseName=ACEM_db;user=sa;password=123456;";
	con = DriverManager.getConnection(connectionUrl);
	stmt=con.createStatement();
	stmt1=con.createStatement();
		
		if(sem.equalsIgnoreCase("I")||sem.equalsIgnoreCase("1"))
			{
				rs=stmt.executeQuery("Select * from Result_BctSemI where Std_Id= '"+Id+"'");
				if(rs.next())
					{
					rs1=stmt1.executeQuery("Select * from Std_Info where Std_Id= '"+Id+"'");
					rs1.next();
					//System.out.println(rs1.next());
					String name=rs1.getString(2)+" "+rs1.getString(3)+" "+rs1.getString(4);
					
					String subI=rs.getString(2);
					String subII=rs.getString(3);
					String subIII=rs.getString(4);
					String subIV=rs.getString(5);
					String subV=rs.getString(6);
					String subVI=rs.getString(7);
					String subVII=rs.getString(8);
					String Tot=rs.getString(9);
					String Per=rs.getString(10).substring(0,5);
					String rep_msg=("The result of "+name+" for sem1 is: Math:"+subI+" Phy:"+subII+" Prog1:"+subIII+" Eng:"+subIV+" Drawing:"+subV+" WrkTech:"+subVI+" AppMech:"+subVII+" Tot:"+Tot+" Per:"+Per);
					//System.out.print(rep_msg);
					new writeResult(date,number,rep_msg);
			
					}
				else
					new writeResult(date,number,"Sorry! The ID you have typed is not found.");
				
			} 
			else if(sem.equalsIgnoreCase("II")||sem.equalsIgnoreCase("2"))
				{
				rs=stmt.executeQuery("Select * from Result_BctSemII where Std_Id= '"+Id+"'");
					if(rs.next())
						{
						String subI=rs.getString(2);
						String subII=rs.getString(3);
						String subIII=rs.getString(4);
						String subIV=rs.getString(5);
						String subV=rs.getString(6);
						String subVI=rs.getString(7);
						String Tot=rs.getString(8);
						String Per=rs.getString(9).substring(0,5);
						String rep_msg=("Thermo:"+subI+" Math:"+subII+" Chem:"+subIII+" Matrl:"+subIV+" Ckt1:"+subV+" Drawing:"+subVI+" Tot:"+Tot+" Per:"+Per);
						System.out.print(rep_msg);
						new writeResult(date,number,rep_msg);
						}
			else
				new writeResult(date,number,"Sorry! The ID you have typed is not  found.");					
				}
			
			else if(sem.equalsIgnoreCase("III")||sem.equalsIgnoreCase("3"))
					{
					rs=stmt.executeQuery("Select * from Result_BctSemIII where Std_Id= '"+Id+"'");
					if(rs.next())
						{
						String subI=rs.getString(2);
						String subII=rs.getString(3);
						String subIII=rs.getString(4);
						String subIV=rs.getString(5);
						String subV=rs.getString(6);
						String subVI=rs.getString(7);
						String Tot=rs.getString(8);
						String Per=rs.getString(9).substring(0,5);
						String rep_msg=("Math:"+subI+" C++:"+subII+" CktII:"+subIII+" Semi:"+subIV+" Logic:"+subV+" BCC:"+subVI+" Tot:"+Tot+" Per:"+Per);
						System.out.print(rep_msg);
						new writeResult(date,number,rep_msg);
						}
					else
						new writeResult(date,number,"Sorry! The ID you have typed is not  found.");									
					}
			
			
			else if(sem.equalsIgnoreCase("IV")||sem.equalsIgnoreCase("4"))
					{
					rs=stmt.executeQuery("Select * from Result_BctSemIV where Std_Id= '"+Id+"'");
					if(rs.next())
						{
						String subI=rs.getString(2);
						String subII=rs.getString(3);
						String subIII=rs.getString(4);
						String subIV=rs.getString(5);
						String subV=rs.getString(6);
						String subVI=rs.getString(7);
						String Tot=rs.getString(8);
						String Per=rs.getString(9).substring(0,5);
						String rep_msg=("Math:"+subI+" InstI:"+subII+" ExCkt:"+subIII+" MP:"+subIV+" Machine:"+subV+" ExMag:"+subVI+" Tot:"+Tot+" Per:"+Per);
						System.out.print(rep_msg);
						new writeResult(date,number,rep_msg);
						}
					else
					new writeResult(date,number,"Sorry! The ID you have typed is not  found.");										
					}
			
			else if(sem.equalsIgnoreCase("V")||sem.equalsIgnoreCase("5"))
					{
					rs=stmt.executeQuery("Select * from Result_BctSemV where Std_Id= '"+Id+"'");
					if(rs.next())
						{
						String subI=rs.getString(2);								
						String subII=rs.getString(3);					
						String subIII=rs.getString(4);			
						String subIV=rs.getString(5);					
						String subV=rs.getString(6);					
						String subVI=rs.getString(7);					
						String subVII=rs.getString(8);			
						String Tot=rs.getString(9);			
						String Per=rs.getString(10).substring(0,5);
						String rep_msg=("NM:"+subI+" Eng:"+subII+" DSA:"+subIII+" TOC:"+subIV+" CAD:"+subV+" MBI:"+subVI+" CSys:"+subVII+" Tot:"+Tot+" Per:"+Per);
						System.out.print(rep_msg);
						new writeResult(date,number,rep_msg);
						}
					else
					new writeResult(date,number,"Sorry! The ID you have typed is not  found.");					
					
			                }
			
			else if(sem.equalsIgnoreCase("VI")||sem.equalsIgnoreCase("6"))
					{
					rs=stmt.executeQuery("Select * from Result_BctSemVI where Std_Id= '"+Id+"'");
					if(rs.next())
						{
						String subI=rs.getString(2);										
						String subII=rs.getString(3);					
						String subIII=rs.getString(4);			
						String subIV=rs.getString(5);					
						String subV=rs.getString(6);					
						String subVI=rs.getString(7);					
						String subVII=rs.getString(8);
						String Tot=rs.getString(9);					
						String Per=rs.getString(10).substring(0,5);
						String rep_msg=("Eco:"+subI+" Proab:"+subII+" Comm:"+subIII+" Graph:"+subIV+" OS:"+subV+" DBMS:"+subVI+" ProjI:"+subVII+" Tot:"+Tot+" Per:"+Per);
						System.out.print(rep_msg);
						new writeResult(date,number,rep_msg);
						}	
					else
					new writeResult(date,number,"Sorry! The ID you have typed is not  found.");									
					}
			
			else if(sem.equalsIgnoreCase("VII")||sem.equalsIgnoreCase("7"))
					{
					rs=stmt.executeQuery("Select * from Result_BctSemVII where Std_Id= '"+Id+"'");
					if(rs.next())
						{
						String subI=rs.getString(2);			
						String subII=rs.getString(3);
						String subIII=rs.getString(4);
						String subIV=rs.getString(5);
						String subV=rs.getString(6);
						String subVI=rs.getString(7);
						String Tot=rs.getString(8);
						String Per=rs.getString(9).substring(0,5);
						String rep_msg=("PE:"+subI+" O&M:"+subII+" Net:"+subIII+" S/wEngi:"+subIV+" AI:"+subV+" ElectI:"+subVI+" Tot:"+Tot+" Per:"+Per);
						System.out.print(rep_msg);
						new writeResult(date,number,rep_msg);
						}
					else
					new writeResult(date,number,"Sorry! The ID you have typed is not  found.");										
					}
			
			else if(sem.equalsIgnoreCase("VIII")||sem.equalsIgnoreCase("8"))
					{
					rs=stmt.executeQuery("Select * from Result_BctSemVIII where Std_Id= '"+Id+"'");
					if(rs.next())
						{
						String subI=rs.getString(2);
						String subII=rs.getString(3);
						String subIII=rs.getString(4);
						String subIV=rs.getString(5);
						String subV=rs.getString(6);					
						String subVI=rs.getString(7);			
						String subVII=rs.getString(8);			
						String Tot=rs.getString(9);			
						String Per=rs.getString(10).substring(0,5);
						String rep_msg=("EPP:"+subI+" TES:"+subII+" DSP:"+subIII+" S&M:"+subIV+" IS:"+subV+" ProjII:"+subVI+" ElectII:"+subVII+" Tot:"+Tot+" Per:"+Per);
						System.out.print(rep_msg);
						new writeResult(date,number,rep_msg);
						}
					else
					new writeResult(date,number,"Sorry! The ID you have typed is not  found.");										
					}
			
	else
		new writeResult(date,number,"Sorry! The semester you have typed is not valid.");
	
	}catch(Exception e)
	{
	System.out.println(e);
	}
	}
}

