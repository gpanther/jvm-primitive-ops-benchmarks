package net.greypanther.opbench.primitives;

import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

// Based on an idea from https://www.youtube.com/watch?v=e08kOj2kISU
@State(Scope.Thread)
public class BenchmarkCacheLineEffect {
	@Param({ "1000", "10000", "100000", "500000", "1000000" })
	public int valueSize;

	private float[] accumulators, factors;

	@Setup
	public void setUp() {
		accumulators = new float[valueSize];
		factors = new float[valueSize];
		Random r = new Random(71);
		for (int i = 0; i < valueSize; ++i) {
			accumulators[i] = r.nextFloat();
			factors[i] = r.nextFloat();
		}
	}

	@Benchmark
	public float[] linear() {
		for (int i = 0; i < valueSize; ++i) {
			accumulators[i] += factors[i] * accumulators[i];
		}
		return accumulators;
	}

	@Benchmark
	public float[] skipping() {
		for (int startOffset = 0; startOffset < 4; ++startOffset) {
			for (int i = startOffset; i < valueSize; i += 4) {
				accumulators[i] += factors[i] * accumulators[i];
			}
		}
		return accumulators;
	}
}

