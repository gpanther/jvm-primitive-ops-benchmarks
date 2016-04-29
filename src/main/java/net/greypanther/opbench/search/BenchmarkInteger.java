package net.greypanther.opbench.search;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OperationsPerInvocation;

public class BenchmarkInteger extends AbstractBenchmark {
	@Benchmark
	@OperationsPerInvocation(LOOKUPS)
	public long benchmarkStandardBinsearch() {
		return lookupRandomIndexes(
				value -> binarySearch(values, 0, valueSize, value, CalculateMidFunctionInt.getMiddle()));
	}

	@Benchmark
	@OperationsPerInvocation(LOOKUPS)
	public long benchmarkStandardBinsearchWithUSHL() {
		return lookupRandomIndexes(
				value -> binarySearch(values, 0, valueSize, value, CalculateMidFunctionInt.getMiddleUSHL()));
	}

	@Benchmark
	@OperationsPerInvocation(LOOKUPS)
	public long benchmarkInterpolationSearch() {
		return lookupRandomIndexes(
				value -> binarySearch(values, 0, valueSize, value, CalculateMidFunctionInt.getProportinal(values)));
	}

	static int binarySearch(int[] values, int startIdx, int stopIdx, int value,
			CalculateMidFunctionInt calculateMidFunction) {
		int low = startIdx;
		int high = stopIdx - 1;

		while (true) {
			int diff = high - low;
			if (diff <= 0) {
				return -(low + 1);
			} else if (diff < 16) {
				for (int i = low; i <= high; i += 1) {
					int idx = i;
					int midVal = values[idx];
					if (midVal == value) {
						return idx;
					} else if (midVal > value) {
						return -(idx + 1);
					}
				}
				return -(high + 2);
			}

			int mid = calculateMidFunction.calculate(low, high, diff, value);
			long midVal = values[mid];

			if (midVal < value) {
				low = mid + 1;
			} else if (midVal > value) {
				high = mid - 1;
			} else {
				return mid;
			}
		}
	}

	@FunctionalInterface
	interface CalculateMidFunctionInt {
		int calculate(int low, int high, int diff, int value);

		static CalculateMidFunctionInt getMiddle() {
			return (low, high, diff, v) -> (low + high) / 2;
		}

		static CalculateMidFunctionInt getMiddleUSHL() {
			return (low, high, diff, v) -> (low + high) >>> 1;
		}

		static CalculateMidFunctionInt getProportinal(int[] values) {
			return (low, high, diff, v) -> {
				int x1 = low, y1 = values[x1], x2 = high, y2 = values[x2];
				int result = diff * (v - y1) / (y2 - y1) + x1;
				if (Math.abs(result - x1) < 16) {
					return x1 + 16;
				} else if (Math.abs(result - x2) < 16) {
					return x2 - 16;
				} else {
					return result;
				}
			};
		}
	}
}
