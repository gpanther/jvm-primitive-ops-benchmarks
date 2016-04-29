package net.greypanther.opbench.primitives;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class BenchmarkFloat {
	@Param({ "NORMAL", "DENORMAL" })
	public FloatingPointKind kind;

	private float a;
	private float b;

	@Setup
	public void setUp() {
		switch (kind) {
		case NORMAL:
			a = 42F;
			b = 7F;
			break;
		case DENORMAL:
			a = 2 * Float.MIN_VALUE;
			b = Float.MIN_VALUE;
			break;
		default:
			throw new IllegalArgumentException(kind.toString());
		}
	}

	@Benchmark
	public float div() {
		return a / b;
	}

	@Benchmark
	public float mul() {
		return a * b;
	}

	@Benchmark
	public float mod() {
		return a % b;
	}

	@Benchmark
	public float sum() {
		return a + b;
	}

	@Benchmark
	public float diff() {
		return a - b;
	}
}
