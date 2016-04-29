package net.greypanther.opbench.search;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import net.greypanther.opbench.search.BenchmarkFloatingPoint;

public final class FloatingPointTest {
	private int[] values;

	@Test
	public void testStandardBinarySearch_before() {
		int before = values[0] - 1;
		assertEquals(Arrays.binarySearch(values, before), BenchmarkFloatingPoint.binarySearch(values, 0, values.length,
				before, BenchmarkFloatingPoint.CalculateMidFunctionDouble.getMiddle()));
	}

	@Test
	public void testStandardBinarySearch_exactMatches() {
		for (int i = 0; i < values.length; ++i) {
			assertEquals(i, BenchmarkFloatingPoint.binarySearch(values, 0, values.length, values[i],
					BenchmarkFloatingPoint.CalculateMidFunctionDouble.getMiddle()));
		}
	}

	@Test
	public void testStandardBinarySearch_approximateMatches() {
		for (int i = 0; i < values.length; ++i) {
			int value = values[i] + 1;
			assertEquals(Arrays.binarySearch(values, value), BenchmarkFloatingPoint.binarySearch(values, 0,
					values.length, value, BenchmarkFloatingPoint.CalculateMidFunctionDouble.getMiddle()));
		}
	}

	@Test
	public void testStandardBinarySearch_after() {
		int after = values[values.length - 1] + 1;
		assertEquals(Arrays.binarySearch(values, after), BenchmarkFloatingPoint.binarySearch(values, 0, values.length,
				after, BenchmarkFloatingPoint.CalculateMidFunctionDouble.getMiddle()));
	}

	@Test
	public void testProportionalBinarySearch_before() {
		int before = values[0] - 1;
		assertEquals(Arrays.binarySearch(values, before), BenchmarkFloatingPoint.binarySearch(values, 0, values.length,
				before, BenchmarkFloatingPoint.CalculateMidFunctionDouble.getProportinal(values)));
	}

	@Test
	public void testProportionalBinarySearch_exactMatches() {
		for (int i = 0; i < values.length; ++i) {
			assertEquals(i, BenchmarkFloatingPoint.binarySearch(values, 0, values.length, values[i],
					BenchmarkFloatingPoint.CalculateMidFunctionDouble.getProportinal(values)));
		}
	}

	@Test
	public void testProportionalBinarySearch_approximateMatches() {
		for (int i = 0; i < values.length; ++i) {
			int value = values[i] + 1;
			assertEquals(Arrays.binarySearch(values, value), BenchmarkFloatingPoint.binarySearch(values, 0,
					values.length, value, BenchmarkFloatingPoint.CalculateMidFunctionDouble.getProportinal(values)));
		}
	}

	@Test
	public void testProportionalBinarySearch_after() {
		int after = values[values.length - 1] + 1;
		assertEquals(Arrays.binarySearch(values, after), BenchmarkFloatingPoint.binarySearch(values, 0, values.length,
				after, BenchmarkFloatingPoint.CalculateMidFunctionDouble.getProportinal(values)));
	}

	@Before
	public void setUp() {
		values = new int[1000];
		BenchmarkFloatingPoint.fillArrayWithIncreasingRandomValues(42, values);
	}
}
