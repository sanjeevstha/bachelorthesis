/* 
 *   ACEM INFO ENQUIRY via SMS- ACEMINFO.h
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
 

// bluetooth related
#include <bluetooth/bluetooth.h>
#include <bluetooth/rfcomm.h>
#include "SmsPath.h"
#include <fstream>


#ifndef __ACEMINFOServer_H__
#define __ACEMINFOServer_H__


 
class CACEMINFOServer{
	
	// Construction
	
public:
	CACEMINFOServer();	// standard constructor
	~CACEMINFOServer();
	bool Initialize(char * path, bool daemonize = false);

	// Implementation

private:
	int BluetoothConnectToSocket(int Channel);
	int BluetoothWaitforConnect(int s);
	void ReadBluetoothCommand();
	void HandleBluetooth();

	bool ReadFile(int handle,char* buffer,int maxbytes,long unsigned int* bytesread,int flag);
	bool WriteFile(int handle, char* buffer,int maxbytes, long unsigned int* byteswritten,int flag);
	char* GetLastError();

	void Log(char* aFormatString, ...);

	int iCommHandle;
	bool iExitNow;
	bool iStopEnd;
	bool iStopNow;

	int startAutomatically;
	int socket1;
	int channel;

	FILE *stream;	
	//ofstream of;
	
	
	bdaddr_t curr_ba; // current bluetooth address
	CSmsPath parser;
};


#endif // __ACEMINFOServer_H__
