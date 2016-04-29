package net.greypanther.opbench.search;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OperationsPerInvocation;

public class BenchmarkFloatingPoint extends AbstractBenchmark {
	@Benchmark
	@OperationsPerInvocation(LOOKUPS)
	public long benchmarkStandardBinsearch() {
		return lookupRandomIndexes(
				value -> binarySearch(values, 0, valueSize, value, CalculateMidFunctionDouble.getMiddle()));
	}

	@Benchmark
	@OperationsPerInvocation(LOOKUPS)
	public long benchmarkInterpolationSearch() {
		return lookupRandomIndexes(
				value -> binarySearch(values, 0, valueSize, value, CalculateMidFunctionDouble.getProportinal(values)));
	}

	static int binarySearch(int[] values, int startIdx, int stopIdx, int value,
			CalculateMidFunctionDouble calculateMidFunction) {
		float low = startIdx;
		float high = stopIdx - 1;

		while (true) {
			float diff = high - low;
			if (diff <= 0) {
				return -((int) low + 1);
			} else if (diff < 16) {
				for (float i = low; i <= high; i += 1) {
					int idx = (int) i;
					float midVal = values[idx];
					if (midVal == value) {
						return idx;
					} else if (midVal > value) {
						return -(idx + 1);
					}
				}
				return -((int) high + 2);
			}

			int mid = (int) calculateMidFunction.calculate(low, high, diff, value);
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
	interface CalculateMidFunctionDouble {
		float calculate(float low, float high, float diff, float value);

		static CalculateMidFunctionDouble getMiddle() {
			return (low, high, diff, v) -> (low + high) / 2;
		}

		static CalculateMidFunctionDouble getProportinal(int[] values) {
			return (low, high, diff, v) -> {
				float x1 = low, y1 = values[(int) x1], x2 = high, y2 = values[(int) x2];
				float result = diff * (v - y1) / (y2 - y1) + x1;
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
