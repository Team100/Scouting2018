###############################################################################
# Team List retrieval for FIRST Robotics FRC using The Blue Alliance API
#
##############
# INVOCATION #
##############
# The program  can be invoked  from the command  line with two  arguments.
# The first argument is the year of the event,
# the second is the event code,
# the third  is the  name of a  file to  which the  results will  be exported.
# example: python MatchScheduleViaTBI.py 2016 cada ds.txt
#
# The program can also be invoked without any arguments. In this case, the user
# will be prompted to input the commands directly.
#
##########
# OUTPUT #
##########
# The program exports  the results into a  given file with one team  per line, tab delimited.
###############################################################################
import requests                # to get the page HTML code
import json                    # to parse JSON response
import sys                     # to get the arguments

filePath = ''


def showUsage():
    print('MatchScheduleViaTBA.py')
    print('Usage: python TeamListViaTBI.py [ <YEAR> , <EVENT>, <FILE> ]')
    print('where: ')
    print('  <YEAR>  is the numeric year of the event.')
    print('  <EVENT>  is the eventCode of the event.')
    print('  <FILE> is the name of a file to which the results will be saved.')
    print('If no arguments are provided, the user will be prompted.')

# The application requires two arguments: a URL and a file location.
if (len(sys.argv) != 3 and len(sys.argv) != 1):  # first is the script location
    # Bad number of arguments.
    showUsage()
    exit(1)
elif len(sys.argv) == 1:  # No arguments; prompt
    print('Match Schedule Formatter for FRC')
    print('Input arguments:')
    year = input('Event Year: ')
    event = input('Event Code: ')
    filePath = year + event + ".txt"
else:
    year = sys.argv[1]
    event = sys.argv[2]
    filePath = year + event + ".txt"

print('Program begun...')
# Now, we have to write the data to a file.
matchfile = open(filePath, 'w')  # open for write
print('File opened successfully...')



url = 'http://www.thebluealliance.com/api/v3/event/'
url = url + year
url = url + event
url = url + "/matches"

#req.add_header('X-TBA-App-Id', 'frc100:scouting-system:v01')
#req.add_header('X-TBA-Auth-Key', 'VIE42623fMKkxFqCcCuRX6X9vGvkl07V68uUM3lGkBlw6GiKj6ubk8p7cV2ONmHd')
#req.add_header('User-Agent', "Mozilla/5.0 (X11; U; Linux i686) Gecko/20071127 Firefox/2.0.0.11")
headers = {'X-TBA-App-Id': 'frc100:scouting-system:v01', 'X-TBA-Auth-Key': 'VIE42623fMKkxFqCcCuRX6X9vGvkl07V68uUM3lGkBlw6GiKj6ubk8p7cV2ONmHd', 'User-Agent': "Mozilla/5.0 (X11; U; Linux i686) Gecko/20071127 Firefox/2.0.0.11"}
print (url)
try:
    print('Fetching team data from The Blue Alliance...')
    response = requests.get(url, headers=headers)
    print('Parsing returned data from The Blue alliance...')
    thepage = response.text # interpret the response
    print(thepage)
    matchList = response.json()  # load the JSON data
    matches = {}
    for match in matchList:
        if match['comp_level'] == 'qm':
            matchId = match['match_number']
            alliances = match['alliances']
            blue = alliances['blue']
            blueteams = blue['team_keys']
            red = alliances['red']
            redteams = red['team_keys']
            rawMatchData = [str(matchId)]
            rawMatchData.extend(redteams)
            rawMatchData.extend(blueteams)
            matchStr = ",".join(rawMatchData)
            matchStr = matchStr.replace("frc", "")
            matches[matchId] = matchStr
    for k in sorted(matches.keys()):
        print((matches[k]))
        matchfile.write(matches[k])
        matchfile.write('\n')
          
except  requests.HTTPError as e:
    print(e)
    
matchfile.close()
print('Data written successfully...')
print('Done.')
