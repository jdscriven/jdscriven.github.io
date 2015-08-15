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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

public class TestBetterBinarySearch {

	@Test
	/**
	 */
	public void testManySizes() throws Exception {
		int MAX_SIZE = 1024 * 1024;
		for (int size = 1; size <= MAX_SIZE; size = (int) (size * 1.10 + 1)) {
			int haystack[] = new int[size];
			for (int i = 0; i < haystack.length; i++) {
				haystack[i] = i;
			}
			for (int i = 0; i < haystack.length; i++) {
				assertEquals(i, BetterBinarySearch.binarySearch(haystack, 0, haystack.length, i));
			}
			assertTrue(BetterBinarySearch.binarySearch(haystack, 0, haystack.length, -1) < 0);
			assertTrue(BetterBinarySearch.binarySearch(haystack, 0, haystack.length, Integer.MAX_VALUE) < 0);
			assertTrue(BetterBinarySearch.binarySearch(haystack, 0, haystack.length, Integer.MIN_VALUE) < 0);

		}
	}

	@Test
	@Ignore
	public void testOneGig() throws Exception {

		int haystack[] = new int[1024 * 1024 * 1024];
		for (int i = 0; i < haystack.length; i++) {
			haystack[i] = i;
		}
		for (int i = 0; i < haystack.length; i++) {
			assertEquals(i, BetterBinarySearch.binarySearch(haystack, 0, haystack.length, i));
		}
		assertTrue(BetterBinarySearch.binarySearch(haystack, 0, haystack.length, -1) < 0);
		assertTrue(BetterBinarySearch.binarySearch(haystack, 0, haystack.length, Integer.MAX_VALUE) < 0);
		assertTrue(BetterBinarySearch.binarySearch(haystack, 0, haystack.length, Integer.MIN_VALUE) < 0);
	}

	@Test
	/**
	 */
	public void testHybrid() throws Exception {
		int MAX_SIZE = 1024 * 1024;
		for (int size = 1; size <= MAX_SIZE; size = (int) (size * 1.10 + 1)) {
			int haystack[] = new int[size];
			for (int i = 0; i < haystack.length; i++) {
				haystack[i] = i;
			}
			for (int i = 0; i < haystack.length; i++) {
				assertEquals(i, HybridBinarySearch.binarySearch(haystack, 0, haystack.length, i));
			}
			assertTrue(BetterBinarySearch.binarySearch(haystack, 0, haystack.length, -1) < 0);
			assertTrue(BetterBinarySearch.binarySearch(haystack, 0, haystack.length, Integer.MAX_VALUE) < 0);
			assertTrue(BetterBinarySearch.binarySearch(haystack, 0, haystack.length, Integer.MIN_VALUE) < 0);

		}
	}


	@Test
	@Ignore
	/**
	 */
	public void testHybridLargeSizes() throws Exception {
		int MAX_SIZE = 1024 * 1024 * 512;
		for (int size = 1024 * 1024 * 512; size <= MAX_SIZE; size *= 2) {
			System.out.println(size);
			int haystack[] = new int[size];
			for (int i = 0; i < haystack.length; i++) {
				haystack[i] = i;
			}
			System.out.println("." + size);
			for (int i = 0; i < haystack.length; i++) {
				assertEquals(i, HybridBinarySearch.binarySearch(haystack, 0, haystack.length, i));
			}
			assertTrue(BetterBinarySearch.binarySearch(haystack, 0, haystack.length, -1) < 0);
			assertTrue(BetterBinarySearch.binarySearch(haystack, 0, haystack.length, Integer.MAX_VALUE) < 0);
			assertTrue(BetterBinarySearch.binarySearch(haystack, 0, haystack.length, Integer.MIN_VALUE) < 0);

		}
	}

}
