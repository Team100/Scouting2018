#! python2
import csv

year = input("Enter year: ")
event = input("Enter event code: ")
filename = year + event + ".txt"
with open(filename) as inputfile:
    results = list(csv.reader(inputfile))
    print("Loaded match data")

team = str(eval(input("Team Number: ")))
output = ""
for i in range(len(results)):
    if team in results[i]:
        output = output + str(results[i][0])
        output = output + ", "

if output.endswith(", "):
    output = output[:-2]
elif output == "":
    output = "Team " + str(team) + " is not playing in any matches for this event."
print(output)

e = eval(input("Press ENTER to exit"))
