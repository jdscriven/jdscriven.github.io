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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class FindBestBinarySearchSize {

	public static void main(String[] args) throws IOException {

		String outputFilename = args[0];
		findBest(outputFilename);

	}

	private static void findBest(String outputFilename) throws IOException {
		Random random = new Random(42);

		//prevent compiler optimizing away method calls
		int accumulate = 0;

		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outputFilename, false)), true)) {

			out.println("algorithm,arraysize,time");

			// 1/findRatio is the number of random lookups that will result in a match
			int findRatio = 2;
			int num_warmups = 1;
			int num_unique_needles = 256;

			int haystack[] = new int[1024 * 1024 * 1024];
			for (int i = 0; i < haystack.length; i++) {
				haystack[i] = i * findRatio;
			}

			for (int iteration = 0; iteration < num_warmups + 1; iteration++) {
				for (int power = 1024; power <= 1024 * 1024 * 512 && power > 0; power *= 2) {
					System.err.println(power);
					final int[] needles = new int[num_unique_needles];
					for (int i = 0; i < needles.length; i++) {
						int nextInt = random.nextInt(power * findRatio);
						needles[i] = nextInt;
					}

					for (int subrange = 4096; subrange <= 1024 * 64; subrange *= 2) {
						//System.out.println(subrange);
						int size = power + subrange;
						size = Math.max(size, 0);
						//System.out.println(size);
						for (int k = 0; k < 1500; k++) {
							long start = System.nanoTime();
							for (int i = 0; i < num_unique_needles; i++) {
								accumulate += Arrays.binarySearch(haystack, 0, size, needles[i]);
							}
							long stop = System.nanoTime();
							long time = stop - start;
							if (iteration == num_warmups && k > 950) {
								out.printf("%s,%d,%d\n", subrange, (power + subrange) * 4L, time);
							}
						}

					}
					out.flush();
				}
			}
			System.out.println(accumulate);
		}
	}

}
