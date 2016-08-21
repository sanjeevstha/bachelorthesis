   #include <fstream>
   #include <iostream>
   #include <string>	
   
    using std::string;
    using std::ios;
    using std::cerr;
    using std::cout;
    using std::endl;

    #include <fstream>
    using std::ifstream;
    using std::ofstream;

    #include <cstdlib> 

    int main()
    {
       ifstream indata; // indata is like cin
      // ofstream outdata;
       //ofstream outdata("./Data/Test.txt");
       string msg,msg_num,msg_rep;
       string *msg_split;
     while(1)
	{

      indata.open("./Data/output.txt"); // opens the file for input
     
       if(!indata)
	 	{ // file couldn't be opened
         	 cerr << "Error: file could not be opened" << endl;
         	 exit(1);
       		}
	
	getline(indata,msg);
	if(msg!="")
		{	
		msg_num=msg.substr(13,10);
		int msg_len=msg.length()-24;
		msg_rep=msg.substr(24,msg_len);
		 ofstream outfile("./Data/output.txt",ios::out);
		 outfile <<"";
		cout<<msg_num<<endl;
		cout<<msg_rep<<endl;
		}
	indata.close();	
	}

	
	return 0;
    }





