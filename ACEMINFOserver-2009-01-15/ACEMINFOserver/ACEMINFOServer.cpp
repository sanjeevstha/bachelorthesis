/* 
 *   ACEM INFO ENQUIRY via SMS- 
 *
 *   Copyright -  Raju Bhai Shrestha(ra_raj_raju@hotmail.com)
 *		Sanjeev Shrestha(stha_san2@hotmail.com)
 *		Saugat Kc(saugat_2001@hotmail.com)
 *		Prarthana Bataju(prarthana_bataju@hotmail.com) *
*/

/***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/


// ACEMINFO ENQUIRY via SMS Class
 

#include <stdlib.h>
#include <iostream>
#include <fstream>
#include <stdarg.h>

#include <errno.h>
#include <signal.h>
#include <unistd.h>

#include <sys/types.h>
#include <sys/stat.h>
#include <dirent.h>

// serial comm
#include <netinet/in.h>
#include <arpa/inet.h>
#include <resolv.h>
#include <netdb.h>
#include <sys/socket.h>

// bluetooth related
#include <bluetooth/bluetooth.h>
#include <bluetooth/rfcomm.h>

#include "ACEMINFOServer.h" 
#include "SmsPath.h"

using namespace std;

bdaddr_t bdaddr;

// constructor
CACEMINFOServer::CACEMINFOServer(){

}

CACEMINFOServer::~CACEMINFOServer(){
	close(iCommHandle);
	close(socket1);

	char tmpstring[50];

}

bool CACEMINFOServer::Initialize(char * path, bool daemonize){
	
	Log("\nACEMINFO ENQUIRY via SMS\n\r");

	socket1=BluetoothConnectToSocket(channel);

	if (daemonize){
		Log("daemonizing\n");
		if (daemon(1, 1) < 0){
			perror("daemon");
			exit(1);
		}
		fclose (stdout);
	}
	
	parser.SetPath(path);

	while(1){
		iCommHandle=BluetoothWaitforConnect(socket1);
		HandleBluetooth();
	}
	return true;  
}

void CACEMINFOServer::HandleBluetooth(){
	iExitNow = false;
	do {
		ReadBluetoothCommand();
	} while (!iExitNow);
	close(iCommHandle);
}



bool CACEMINFOServer::WriteFile(int handle, char* buffer,int maxbytes, long unsigned int* byteswritten,int flag){
	long unsigned int b;
	int br;
	
	br=write(handle,(void*)buffer,maxbytes);
	if (br==-1) {
		return false;
	}
	else {
		b=(long unsigned int)(br);
		*(byteswritten)=b;
		return true;
	}
}


bool CACEMINFOServer::ReadFile(int handle,char* buffer,int maxbytes,long unsigned int* bytesread,int flag){
	long unsigned int b;
	int br;
	br=recv(handle, (void*)buffer, maxbytes,flag);
	if (br<=0) {
		return false;
	}
	else {
		b=(long unsigned int)(br);
		*(bytesread)=b;
		return true;
	}
}
void CACEMINFOServer::ReadBluetoothCommand(){
	char buffer[1024];
	unsigned long bytes_read = 0;
		

	memset(buffer, 0, 1024);
	if (!ReadFile(iCommHandle, buffer, 1024, &bytes_read, 0)){
		Log("%s\r\n", GetLastError());
		iExitNow = true;
	}
	else{

	cout << "String Received: " << "(" << bytes_read << " bytes), "; // << endl;
	
	// string received from client

	// process it here..

	ofstream outfile("./Data/InputSMS.txt",ios::app);
	outfile << buffer<< endl;
	outfile.close();
	
	WriteFile(iCommHandle, "ACKDEL\0", (7), &bytes_read, 0); //Automatically Delete the SMS from Mobile.
		
	
	
	}
}


int CACEMINFOServer::BluetoothConnectToSocket(int channel){
	int s=0;

	struct sockaddr_rc loc_addr;
	struct sockaddr_in iploc_addr;

	int pf;
	int proto;

	pf=PF_BLUETOOTH;
	proto=BTPROTO_RFCOMM;

	if( (s = socket(pf, SOCK_STREAM, proto)) < 0 ) {
		Log("Can't create socket. %s\r\n", strerror(errno));
		exit(1);
	}

	loc_addr.rc_family = AF_BLUETOOTH;
	loc_addr.rc_bdaddr = bdaddr;
	loc_addr.rc_channel = uint8_t(channel);

	char tmpstring[50];
	sprintf(tmpstring, "sdptool add --channel=%i SP", channel);
	system(tmpstring);
	Log("Registered SP for channel %i\r\n", channel);

	struct sockaddr* socketaddr=(struct sockaddr *)&loc_addr;

	if( bind(s, socketaddr, sizeof(*socketaddr)) < 0 ) {
		Log("Can't bind socket. %s, %f\r\n", strerror(errno),float(s));
		exit(1);
	 }

	if( listen(s, 10) ) {
		Log("Can not listen on the socket. %s\r\n", strerror(errno));
		exit(1);
	}

	return s;

}


int CACEMINFOServer::BluetoothWaitforConnect(int s){
	int s1=0,opt;

	bdaddr_t ba;
	struct sockaddr_rc rem_addr;

	struct sockaddr_in iprem_addr;

	Log("\r\nWaiting for connection over bluetooth...\r\n");

	struct sockaddr* socketaddr=(struct sockaddr *)&rem_addr;

	opt = sizeof(rem_addr);
	if( (s1 = accept(s, socketaddr, (socklen_t *)&opt)) < 0 ){
		Log("Accept failed. %s\r\n", strerror(errno));
		exit(1);
	}

	baswap(&ba, &rem_addr.rc_bdaddr);
	Log("Connect from %s\r\n", batostr(&ba));
	
	curr_ba = ba; // for future use

	return s1;
}


char* CACEMINFOServer::GetLastError(){
  //return strerror(errno);
}

	
void CACEMINFOServer::Log(char* aFormatString, ...){
	char logBuffer[1024];
	va_list argList;
	va_start(argList, aFormatString);
	vsprintf(logBuffer, aFormatString, argList);
	va_end(argList);
	cout << logBuffer;
}	
