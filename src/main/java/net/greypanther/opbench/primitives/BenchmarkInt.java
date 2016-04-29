package net.greypanther.opbench.primitives;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class BenchmarkInt {
	private int a;
	private int b;

	@Setup
	public void setUp() {
		a = 42;
		b = 7;
	}

	@Benchmark
	public int div() {
		return a / b;
	}

	@Benchmark
	public int mul() {
		return a * b;
	}

	@Benchmark
	public int mod() {
		return a % b;
	}

	@Benchmark
	public int sum() {
		return a + b;
	}

	@Benchmark
	public int diff() {
		return a - b;
	}

	@Benchmark
	public int shr() {
		return a >> b;
	}

	@Benchmark
	public int ushr() {
		return a >>> b;
	}

	@Benchmark
	public int shl() {
		return a << b;
	}
}
