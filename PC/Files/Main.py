import openpyxl
import RTQR
import os
import time

book = openpyxl.load_workbook('ScoutData.xlsx') 
sheet = book.active

s = list(RTQR.code())
print(s)
for i in range(len(s)):
    a = list(s[i].split("^"))

sheet.append(a)
os.system("TASKKILL /F /IM EXCEL.EXE")
time.sleep(0.5)
book.save('ScoutData.xlsx')
print("Successfully saved data to file")
