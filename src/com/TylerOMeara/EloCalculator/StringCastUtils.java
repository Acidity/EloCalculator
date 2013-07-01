//Copyright 2013 Tyler O'Meara
//See License.txt for more details

package com.TylerOMeara.EloCalculator;

public class StringCastUtils 
{
	/**
	 * Checks if the supplied value is a boolean
	 * @param value String to be checked
	 * @return true if the value is a boolean, false otherwise
	 */
	
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
	
	/**
	 * Checks if the supplied value is a double
	 * @param value String to be checked
	 * @return true if the value is a double, false otherwise
	 */
	
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
	
	/**
	 * Checks if the supplied value is an integer
	 * @param value String to be checked
	 * @return true if the value is an integer, false otherwise
	 */
	
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
