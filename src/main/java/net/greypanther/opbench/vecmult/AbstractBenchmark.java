package net.greypanther.opbench.vecmult;

import java.util.Random;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public abstract class AbstractBenchmark {
	@Param({ "1000", "10000", "100000", "500000", "1000000" })
	public int valueSize;

	AbstractBenchmark() {
		// to be instantiated only inside the package
	}

	static int firstPowerOfTwoHigherOrEqualTo(int value) {
		int result = 0;
		int i = 1;
		while (i < value) {
			i <<= 1;
			result += 1;
		}
		return result;
	}

	static void fillArrayWithIncreasingRandomValues(long seed, int arrayLength, ArraySetter setter) {
		Random r = new Random(seed);
		int value = 0;
		for (int i = 0; i < arrayLength; ++i) {
			value += 1 + r.nextInt(10);
			setter.set(i, value);
		}
	}

	@FunctionalInterface
	interface ArraySetter {
		void set(int index, int value);
	}
}
