package com.bignerdranch.android.geoquiz;


import android.util.Log;

public class Logger
{
	public static void d(Class theClass, String methodName, String description) {
		Log.d(theClass.getSimpleName(),  methodName + ";" + description + ";");
	}
}
