/*
    Seam - Library for Transparent Compression of Java Strings.

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

public class DiscoverBestSmudge {

	private static final int L1_SIZE = 32 * 1024;
	private static final int L2_SIZE = 256 * 1024;
	private static final int L3_SIZE = 8 * 1024 * 1024;

	private static final int L1_ASSOCIATIVITY = 8;
	private static final int L2_ASSOCIATIVITY = 8;
	private static final int L3_ASSOCIATIVITY = 16;

	private static final int L1_STRIDE = L1_SIZE / L1_ASSOCIATIVITY;
	private static final int L2_STRIDE = L2_SIZE / L2_ASSOCIATIVITY;
	private static final int L3_STRIDE = L3_SIZE / L3_ASSOCIATIVITY;

	private static final int L1_STRIDE_LINES = L1_STRIDE / 8;
	private static final int L2_STRIDE_LINES = L2_STRIDE / 8;
	private static final int L3_STRIDE_LINES = L3_STRIDE / 8;

	private static final int SIZE_OF_INT = 4;

	public static void main(String[] args) {

		System.out.println(127 & -(256));
		System.out.println(127 & -(128));
		System.out.println(127 & -(64));
		System.out.println(127 & -(32));
		System.out.println(127 & -(16));
		System.out.println(127 & -(8));
		System.out.println(127 & -(4));
		System.out.println(127 & -(2));

		System.out.println(127 & -(128) + 127 & -(64) - 127 & -(32) + 127 & -(16) - 127 & -(8) + 127 & -(4) - 127
				& -(2));

		System.out.println(127 & -(2) - 127 & -(4) + 127 & -(8) - 127 & -(16));

		System.out.println(2 | 1);
		System.exit(0);

		for (int i = 8192; i < 1024 * 1024 * 512; i *= 2) {
			long lowestConflicts = Long.MAX_VALUE;
			int bestvalue = 0;
			for (int j = i; j <= i * 2; j++) {
				int numberOfConflicts = numConflicts(j, i);
				if (numberOfConflicts < lowestConflicts) {
					lowestConflicts = numberOfConflicts;
					bestvalue = j;
					System.out.printf("%s:  %s (%s) \n", i, bestvalue - i, lowestConflicts);

				}

			}


		}
	}

	private static int numConflicts(int x, int power) {
		int num = x;
		int l1Miss = 0;
		int l2Miss = 0;
		int l3Miss = 0;
		while (num > 1) {
			if (num > L1_STRIDE && (num % L1_STRIDE < 8 || num % L1_STRIDE >= L1_STRIDE - 8)) {
				l1Miss++;
			}
			if (num > L2_STRIDE && (num % L2_STRIDE < 8 || num % L2_STRIDE >= L2_STRIDE - 8)) {
				l2Miss++;
			}
			if (num > L3_STRIDE && (num % L3_STRIDE < 8 || num % L3_STRIDE >= L3_STRIDE - 8)) {
				l3Miss++;
			}
			num >>>= 1;
		}
		int total = l3Miss * 10 + l2Miss * 4 + l1Miss;
		return total;
	}
}
