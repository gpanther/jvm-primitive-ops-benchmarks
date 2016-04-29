package net.greypanther.opbench.primitives;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class BenchmarkLong {
	private long a;
	private long b;

	@Setup
	public void setUp() {
		a = 42L;
		b = 7L;
	}

	@Benchmark
	public long div() {
		return a / b;
	}

	@Benchmark
	public long mul() {
		return a * b;
	}

	@Benchmark
	public long mod() {
		return a % b;
	}

	@Benchmark
	public long sum() {
		return a + b;
	}

	@Benchmark
	public long diff() {
		return a - b;
	}

	@Benchmark
	public long shr() {
		return a >> b;
	}

	@Benchmark
	public long ushr() {
		return a >>> b;
	}

	@Benchmark
	public long shl() {
		return a << b;
	}
}
