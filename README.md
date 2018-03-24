# Team 100 Match Scouting 2018
This is a match scouting system created by members of Team 100 for
the purpose of collecting data from this year's FRC game, FIRST
Powerup. There are two parts: an android app for tablets that
records data, and converts it to a QR code, and a Python script that
runs on a PC and uses a webcam to extract data from the QR code and
add it to an Excel file (It could probably be easily modified to
work with .csv files if you don't have Excel).

## Requirements:
* Windows PC (Laptop is best for this, it will be in the stands)
* Webcam (If the PC does not have one)
* Python 3 (Must be added to PATH)
* Python libraries (can be installed with pip3): pyzbar, opencv-python,
	Pillow, zbar(install from binary wheel)
* Python 2 (Only required for extra utilities; must rename binary from
	python.exe to python2.exe and add to PATH)
* Android Tablet(s) (6 is optimal, but less will work)
* Android Studio

**Thanks to @allenywang on GitHub for making RTQR.py**

>Copyright (C) 2018  Team 100 Wildhats
>
>This program is free software: you can redistribute it and/or modify it under
>the terms of the GNU General Public License as published by the Free Software
>Foundation, either version 3 of the License, or (at your option) any later
>version.
>
>This program is distributed in the hope that it will be useful, but WITHOUT ANY
>WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
>PARTICULAR PURPOSE. See the GNU General Public License for more details.
