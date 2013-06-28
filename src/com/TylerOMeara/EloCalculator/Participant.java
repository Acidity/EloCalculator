//Copyright 2013 Tyler O'Meara
//See License.txt for more details

package com.TylerOMeara.EloCalculator;

import java.util.ArrayList;

public class Participant 
{
	double elo = 1200;
	double wins = 0;
	double losses = 0;
	double winRate = 0;
	double k = 36;
	String name;
	String shortName;
	ArrayList<Double> eloByGame = new ArrayList<Double>();
	ArrayList<Double> eloByWeek = new ArrayList<Double>();
	
	
	public double getElo() {
		return elo;
	}
	public void setElo(double elo) {
		this.elo = elo;
		this.eloByGame.add(elo);
	}
	public double getWins() {
		return wins;
	}
	public void setWins(double wins) {
		this.wins = wins;
	}
	public double getLosses() {
		return losses;
	}
	public void setLosses(double losses) {
		this.losses = losses;
	}
	public double getWinRate() {
		return winRate;
	}
	public void setWinRate(double winRate) {
		this.winRate = winRate;
	}
	public ArrayList<Double> getEloByGame() {
		return eloByGame;
	}
	public void setEloByGame(ArrayList<Double> eloByGame) {
		this.eloByGame = eloByGame;
	}
	public double getK() {
		return k;
	}
	public void setK(double k) {
		this.k = k;
	}
	
	public Participant(String Name, String ShortName)
	{
		name = Name;
		shortName = ShortName;
		eloByGame.add(elo);
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void endOfWeek()
	{
		eloByWeek.add(elo);
	}
	public ArrayList<Double> getEloByWeek() {
		return eloByWeek;
	}
	public void setEloByWeek(ArrayList<Double> eloByWeek) {
		this.eloByWeek = eloByWeek;
	}
	
	public void calculateWinRate()
	{
		if(losses > 0)
			winRate = wins/losses;
		else
			winRate = 1;
	}
	
}
