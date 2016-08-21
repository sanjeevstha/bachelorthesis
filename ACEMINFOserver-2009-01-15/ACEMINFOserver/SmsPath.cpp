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


// ACEMINFO ENQUIRY via SMS 

#include <iostream>
#include <fstream>
#include <string.h>

#include "SmsPath.h"

using namespace std;

// constructor
CSmsPath::CSmsPath(){

	strcpy(path, ".");
}

// constructor
CSmsPath::CSmsPath(char * pt)
{
	SetPath(pt);
}

void CSmsPath::SetPath(char * pt)
{
	// copy the path 
	strcpy(path, pt);
}

