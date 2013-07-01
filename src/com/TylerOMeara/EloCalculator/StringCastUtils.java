package com.TylerOMeara.EloCalculator;

public class StringCastUtils 
{
	public static boolean isBoolean(String value)
	{
		try
		{
			Boolean.valueOf(value);
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
	public static boolean isDouble(String value)
	{
		try
		{
			Double.valueOf(value);
		}
		catch(NumberFormatException e)
		{
			return false;
		}
		return true;
	}
	
	public static boolean isInteger(String value)
	{
		try
		{
			Integer.valueOf(value);
		}
		catch(NumberFormatException e)
		{
			return false;
		}
		return true;
	}
}
