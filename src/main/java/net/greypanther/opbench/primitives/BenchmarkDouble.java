package net.greypanther.opbench.primitives;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class BenchmarkDouble {
	@Param({ "NORMAL", "DENORMAL" })
	public FloatingPointKind kind;

	private double a;
	private double b;

	@Setup
	public void setUp() {
		switch (kind) {
		case NORMAL:
			a = 42D;
			b = 7D;
			break;
		case DENORMAL:
			a = 2 * Double.MIN_VALUE;
			b = Double.MIN_VALUE;
			break;
		default:
			throw new IllegalArgumentException(kind.toString());
		}
	}

	@Benchmark
	public double div() {
		return a / b;
	}

	@Benchmark
	public double mul() {
		return a * b;
	}

	@Benchmark
	public double mod() {
		return a % b;
	}

	@Benchmark
	public double sum() {
		return a + b;
	}

	@Benchmark
	public double diff() {
		return a - b;
	}
}
