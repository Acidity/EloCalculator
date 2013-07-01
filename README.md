//Copyright 2013 Tyler O'Meara
//See License.txt for more details

EloCalculator
=============

Program for generating elo ratings for a group of competing teams or players in sporting events.
Every file has a specific syntax that must be adhered to in order for the program to work. Specifics for all files can be found below. Examples of all files are included with the source.

Participants.txt
----------------

Participants.txt is where all of the participants who are going to be ranked are specified for the program. The order of the participants inside the file does not matter.

The first line *must* include an integer (and only an integer) specifying exactly how many participants are included below.
Every subsequent line must be a single participant (either an individual or a team) and should be in the following format:
ShortName:Full Name:Starting Elo:Starting K value

Games.txt
---------

Games.txt has the outcomes of all prior matches that are to be tracked and used for rating. Order DOES MATTER, games are to be entered in the order in which they were played, otherwise the ratings will not be accurate.

The default line format for Games.txt is
WinnerShortName:LoserShortName

Matches must be in order, and the winner is always on the left side.
There are also special commands that must be on their own lines. All Special command lines start with // and have the command directly following it.
There are 2 special line types, Week special lines, and K-Value change lines.

Week special lines have the following format:
//Week <#>
where <#> represents and integer. Weeks are used to make the Games.txt file readable as well as provide rankings after each week and allow for weekly Elo Change metrics.

K-Value Change lines have the following format:
//K-Change:ParticipantShortHand:NewKValue
The ParticipantShortHand must be the shorthand of a valid participant as defined in Participants.txt and the NewKValue must be a valid double (decimal number), though integers are strongly recommended.

FutureGames.txt
---------------

FutureGames.txt provides the program with upcoming matches so that it may provide estimated win probabilities based on the participants current Elo ratings. Note that these probabilities are only accurate if the Elo Ratings reflect the true skill of the participants.

The default line format for FutureGames.txt is
LeftShortName:RightShortName

Matches must be in order, and the winner is always on the left side.
There are also special commands that must be on their own lines. All Special command lines start with // and have the command directly following it.
There is 1 special line types, Week special lines.

Week special lines have the following format:
//Week <#>
where <#> represents and integer. Weeks are used to make the FutureGames.txt file readable as well as provide for predictions relating to that weeks games.

Settings.txt
------------

Settings.txt allows the end user to specify certain program-wide settings.
The available settings are listed below:

reddit:true/false
Determines whether or not the program should output it's data into a reddit-friendly table format.

roundWinPercentages:true/false
Determines whether or not the program should output the win percentages rounded to the nearest percentage point or not.

useFullName:true/false
Determines whether or not the program should use the full team names as specified in Participants.txt or not

displayPerWinEloChanges:true/false
Determines whether or not the program should display the elo changes after every match for the participants involved.
