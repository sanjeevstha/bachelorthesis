/* 
 *   ACEM INFO ENQUIRY via SMS
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



#ifndef __SMSPATH_H__
#define __SMSPATH_H__

class CSmsPath{
	
	// Construction
	
public:
	
	CSmsPath(char * pt);	// standard constructor
	CSmsPath();

	int ParseMessage(char * msg, char * ba);
	void SetPath(char pt[]);

	// Implementation

private:


	bool FileExists(char path[]);
	bool ExistsIn(char phone[], char path[]);

	bool CheckForDuplicate();
	void WriteToInbox();
	void DumpToNull();
	int SplitMessage();
	int IsValidMessage();

	int countchr(char s[], char c);
	int trim(char s[]);
	int copytoken(char tok[], char str[], char delimit[], int n);
	

	
	
	char btaddr[20]; // bluetooth address
	char path[1024]; // path where data files are stored
	char message[1024]; // main message from cell phone
	char dt[20]; // date 
	char ph[20]; // phone no
	char sms[512]; // sms message
	
	char curr_runningsmv_line[1024]; // current line mentioned in running.smv for current message
	
	char inboxpath[1024];
	char allowpath[1024];
	char disallowpath[1024];
	char nullpath[1024];
	char foldername[512];
	char checkdup[10];

};


#endif // __SMSPATH_H__
