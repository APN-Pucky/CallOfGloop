package de.neuwirthinformatik.Alexander.CoG.Util;

public class ArrayUtil 
{
	public static byte[] clone(byte[] arr)
	{
		byte[] arr2 = new byte[arr.length];
		for(int i = 0; i < arr.length;i++)
		{
			arr2[i]=arr[i];
		}
		return arr2;
	}
	
	public static byte[] sub(byte[] arr, int pos, int len)
	{
		byte[] r = new byte[len];
		System.arraycopy(arr, pos, r, 0, len);
		return r;
	}
	
	public static byte[] append(byte[] arr1, byte[] arr2)
	{
		byte[] arr = new byte[arr1.length+arr2.length];
		for(int i = 0; i < arr1.length;i++)
		{
			arr[i]=arr1[i];
		}
		for(int i = 0; i < arr2.length;i++)
		{
			arr[arr1.length+i]=arr2[i];
		}
		return arr;
	}
}
