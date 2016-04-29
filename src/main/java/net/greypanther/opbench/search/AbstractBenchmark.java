package net.greypanther.opbench.search;

import java.util.Random;
import java.util.function.IntToLongFunction;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public abstract class AbstractBenchmark {
	static final int LOOKUPS = 1_000;

	@Param({ "1000", "10000", "100000", "500000", "1000000" })
	public int valueSize;

	int[] values;

	AbstractBenchmark() {
		// to be instantiated only inside the package
	}

	@Benchmark
	@OperationsPerInvocation(LOOKUPS)
	public long benchmarkRandoms() {
		return lookupRandomIndexes(v -> v);
	}

	static long lookupRandomIndexes(IntToLongFunction func) {
		long sumOfIndexes = 0;
		Random r = new Random(31);
		for (int i = 0; i < LOOKUPS; i++) {
			int index = 1 + r.nextInt(10);
			sumOfIndexes += func.applyAsLong(index);
		}
		return sumOfIndexes;
	}

	static void fillArrayWithIncreasingRandomValues(long seed, int[] array) {
		Random r = new Random(seed);
		int value = 0;
		for (int i = 0; i < array.length; ++i) {
			value += 1 + r.nextInt(10);
			array[i] = value;
		}
	}

	@Setup
	public void setUp() {
		values = new int[valueSize];
		fillArrayWithIncreasingRandomValues(42, values);
	}
}
