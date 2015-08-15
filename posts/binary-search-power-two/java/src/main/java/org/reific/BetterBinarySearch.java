/*
    Copyright (C) 2015 James Scriven

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.reific;

public class BetterBinarySearch {

	private static void rangeCheck(int arrayLength, int fromIndex, int toIndex) {
		if (fromIndex > toIndex) {
			throw new IllegalArgumentException(
					"fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
		}
		if (fromIndex < 0) {
			throw new ArrayIndexOutOfBoundsException(fromIndex);
		}
		if (toIndex > arrayLength) {
			throw new ArrayIndexOutOfBoundsException(toIndex);
		}
	}

	public static int binarySearch(int[] a, int fromIndex, int toIndex,
			int key) {
		rangeCheck(a.length, fromIndex, toIndex);
		return binarySearch0(a, fromIndex, toIndex, key);
	}

	// Like public version, but without range checks.
	private static int binarySearch0(int[] a, int fromIndex, int toIndex,
			int key) {
		int low = fromIndex;
		int high = toIndex - 1;

		int sizeMinusOne = high - low;

		int fractionalPowerOfTwo = flp2(sizeMinusOne >>> 4);
		int mid = sizeMinusOne & -fractionalPowerOfTwo; // round down to multiple of fractionalPowerOfTwo
		mid -= fractionalPowerOfTwo >>> 1;
		mid += fractionalPowerOfTwo >>> 9;
		mid += fractionalPowerOfTwo >>> 17;

		while (low <= high) {
			int midVal = a[mid];

			if (midVal < key)
				low = mid + 1;
			else if (midVal > key)
				high = mid - 1;
			else
				return mid; // key found
			mid = (low + high) >>> 1;
		}
		return -(low + 1); // key not found.
	}

	/**
	 * "Floor Power of 2". Round x down to the closest power of 2. 
	 * Adapted from Hacker's Delight, by Henry S. Warren Jr.
	 */
	private static int flp2(int x)
	{
		x = x | (x >>> 1);
		x = x | (x >>> 2);
		x = x | (x >>> 4);
		x = x | (x >>> 8);
		x = x | (x >>> 16);
		return x - (x >>> 1);
	}

}
