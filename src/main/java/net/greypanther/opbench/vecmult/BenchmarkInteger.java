package net.greypanther.opbench.vecmult;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

public class BenchmarkInteger extends AbstractBenchmark {
	int[] input1, input2, input3, input4, input5;

	@Benchmark
	public int benchmarkSumOfMultiplication() {
		int result = 0;
		for (int i = 0; i < valueSize; ++i) {
			result += input1[i] * input2[i];
		}
		return result;
	}

	@Benchmark
	public int benchmarkMultiSumOfMultiplication() {
		int r1 = 0, r2 = 0, r3 = 0, r4 = 0, r5 = 0;
		for (int i = 0; i < valueSize; ++i) {
			r1 += input1[i] * input2[i];
			r2 += input2[i] * input3[i];
			r3 += input3[i] * input4[i];
			r4 += input4[i] * input5[i];
			r5 += input5[i] * input1[i];
		}
		return r1 + r2 + r3 + r4 + r5;
	}

	@Setup
	public void setUp() {
		input1 = new int[valueSize];
		fillArrayWithIncreasingRandomValues(42, input1);
		input2 = new int[valueSize];
		fillArrayWithIncreasingRandomValues(24, input2);
		input3 = new int[valueSize];
		fillArrayWithIncreasingRandomValues(9, input3);
		input4 = new int[valueSize];
		fillArrayWithIncreasingRandomValues(7, input4);
		input5 = new int[valueSize];
		fillArrayWithIncreasingRandomValues(11, input5);
	}

	static void fillArrayWithIncreasingRandomValues(long seed, int[] array) {
		fillArrayWithIncreasingRandomValues(seed, array.length, (index, value) -> array[index] = value);
	}
}
