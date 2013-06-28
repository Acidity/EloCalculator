package com.TylerOMeara.EloCalculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Main 
{
	static int numTeams;
	static HashMap<String,Team> Teams = new HashMap<String,Team>();
	static boolean reddit = true;
	static boolean roundWinPercs = true;
	static int currentWeek = 0;
	public static void main(String[] args)
	{
		BufferedReader teams;
		try {
			teams = new BufferedReader(new FileReader("Teams.txt"));
			String line;
			int x = 0;
			while((line = teams.readLine()) != null)
			{
				if(x == 0)
				{
					numTeams = Integer.valueOf(line);
				}
				else
				{
					Teams.put(line.split(":")[0], new Team(line.split(":")[1],line.split(":")[0]));
				}
				x++;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		BufferedReader games;
		
		try {
			games = new BufferedReader(new FileReader("Games.txt"));
			String line;
			int x = 0;
			while((line = games.readLine()) != null)
			{
				//System.out.println("Reading line " + x + " of the file.");
				x++;
				if(!line.startsWith("//"))
				{
					String winner = line.split(":")[0];
					String loser = line.split(":")[1];
					if(Teams.get(winner) == null || Teams.get(loser) == null)
					{
						System.out.println("Unable to find team specified on line " + x + ". Stopping execution.");
						return;
					}
					CalculateEloChange(Teams.get(winner),Teams.get(loser));
				}
				else
				{
					if(!line.equals("//Week 1"))
					{
						endOfWeekEloRatings();
					}
					
					for(Team team : Teams.values())
					{
						team.endOfWeek();
					}
					
					System.out.println();
					System.out.println("Begin " + line.substring(2));
					currentWeek = Integer.valueOf(line.substring(line.length()-1));
					System.out.println();
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		endOfWeekEloRatings();
		winProbabilities();
	}
		
	public static void CalculateEloChange(Team winner, Team loser)
	{
		double winPerc = Math.pow(10, winner.getElo()/400)/(Math.pow(10, winner.getElo()/400) + Math.pow(10, loser.getElo()/400));
		double winnerEloChange = winner.getK()*(1-winPerc);
		double loserEloChange = loser.getK()*(winPerc-1);
		winner.setElo(winner.getElo()+winnerEloChange);
		loser.setElo(loser.getElo()+loserEloChange);
		winner.setWins(winner.getWins()+1);
		loser.setLosses(loser.getLosses()+1);
		winner.calculateWinRate();
		loser.calculateWinRate();
		System.out.println(winner.getName() + "(+" + winnerEloChange + ") won against " + loser.getName() + "(" + loserEloChange + ")" + " with an expected win chance of " + winPerc);
	}
	
	public static void endOfWeekEloRatings()
	{
		System.out.println();
		System.out.println("End of week " + currentWeek + " Elo ratings");
		
		HashMap<Double, String> eloMap = new HashMap<Double,String>();
		for(Team team : Teams.values())
		{
			
			if(eloMap.containsKey(team.getElo()))
			{
				eloMap.put(team.getElo(), eloMap.get(team.getElo()) + ":" + team.getShortName());
			}
			else
			{
				eloMap.put(team.getElo(), team.getShortName());
			}
		}
	
		Double[] eloArray = new Double[eloMap.size()];
		eloMap.keySet().toArray(eloArray);
		Arrays.sort(eloArray,Collections.reverseOrder());
		int y = 1;
		if(!reddit)
		System.out.println("---------------------------------");
		if(reddit)
		{
			System.out.println("| Rank | Team | Elo Rating | Elo Change | Win-Loss | Elo (Raw) | Elo Chg (Raw) |");
			System.out.println("|:---:|:-----:|:---------:|:-------------:|:-----:|:---------:|:-------------:|");
		}
		for(double elo : eloArray)
		{
			if(!eloMap.get(elo).contains(":"))
			{
				double lastWeeksElo = Teams.get(eloMap.get(elo)).getEloByWeek().get(Teams.get(eloMap.get(elo)).getEloByWeek().size()-1);
				if(!reddit)
				{
					System.out.println("#" + y + " " + eloMap.get(elo) + " | " + elo + " (" + (elo - lastWeeksElo) + ")" + " | " + (int)Teams.get(eloMap.get(elo)).getWins() + "-" + (int)Teams.get(eloMap.get(elo)).getLosses() + " | " + Math.round(elo) + " (" + (Math.round(elo) - Math.round(lastWeeksElo)) + ")");
				}
				else
				{
					System.out.println("|" + y + "|" + eloMap.get(elo) + "|" + Math.round(elo) + "|" + (Math.round(elo) - Math.round(lastWeeksElo))
							+ "|" + (int)Teams.get(eloMap.get(elo)).getWins() + "-" + (int)Teams.get(eloMap.get(elo)).getLosses() + "|"
							+ elo + "|" + (elo - lastWeeksElo) + "|");
				}
			}
			else
			{
				String[] teams = eloMap.get(elo).split(":");
				for(String team : teams)
				{
					double lastWeeksElo = Teams.get(team).getEloByWeek().get(Teams.get(team).getEloByWeek().size()-1);
					if(!reddit)
					{
						System.out.println("#" + y + " " + team + " | " + elo + " (" + (elo - lastWeeksElo) + ")" + " | " + (int)Teams.get(team).getWins() + "-" + (int)Teams.get(team).getLosses() + " | " + Math.round(elo) + " (" + (Math.round(elo) - Math.round(lastWeeksElo)) + ")");
					}
					else
					{
						System.out.println("|" + y + "|" + team + "|" + Math.round(elo) + "|" + (Math.round(elo) - Math.round(lastWeeksElo))
								+ "|" + (int)Teams.get(team).getWins() + "-" + (int)Teams.get(team).getLosses() + "|"
								+ elo + "|" + (elo - lastWeeksElo) + "|");
					}
				}
			}
			y++;
		}
	}
	
	public static void winProbabilities()
	{
		System.out.println();
		System.out.println();
		System.out.println("ESTIMATED WIN PERCENTAGES");
		BufferedReader games;
		
		try {
			games = new BufferedReader(new FileReader("FutureGames.txt"));
			String line;
			int x = 0;
			while((line = games.readLine()) != null)
			{
				//System.out.println("Reading line " + x + " of the file.");
				x++;
				if(!line.startsWith("//"))
				{
					String blue = line.split(":")[0];
					String red = line.split(":")[1];
					double blueWinPerc = Math.pow(10, Teams.get(blue).getElo()/400)/(Math.pow(10, Teams.get(blue).getElo()/400) + Math.pow(10, Teams.get(red).getElo()/400));
					double redWinPerc = 1-blueWinPerc;
					System.out.print("|" + blue);
					if(roundWinPercs)
					{
						if(blueWinPerc >= .5)
						{
							System.out.println("|**" + Math.round(blueWinPerc*100) + "%**|vs|" + Math.round(redWinPerc*100) + "%|" + red + "|");
						}
						if(redWinPerc >= .5)
						{
							System.out.println("|" +  Math.round(blueWinPerc*100) + "%|vs|**" +  Math.round(redWinPerc*100) + "%**|" + red + "|");
						}
					}
					else
					{
						if(blueWinPerc >= .5)
						{
							System.out.println("|**" + blueWinPerc*100 + "%**|vs|" + redWinPerc*100 + "%|" + red + "|");
						}
						if(redWinPerc >= .5)
						{
							System.out.println("|" + blueWinPerc*100 + "%|vs|**" + redWinPerc*100 + "%**|" + red + "|");
						}
					}
				}
				else
				{				
					System.out.println();
					System.out.println("Begin " + line.substring(2));
					System.out.println();
					System.out.println("|Blue Team|Est. Win%||Est. Win%|Red Team|");
					System.out.println("|:---:|:---:|:---:|:---:|:---:|");
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
