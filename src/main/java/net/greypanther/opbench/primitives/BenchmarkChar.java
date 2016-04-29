package net.greypanther.opbench.primitives;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class BenchmarkChar {
	private char a;
	private char b;

	@Setup
	public void setUp() {
		a = 42;
		b = 7;
	}

	@Benchmark
	public char div() {
		return (char) (a / b);
	}

	@Benchmark
	public char mul() {
		return (char) (a * b);
	}

	@Benchmark
	public char mod() {
		return (char) (a % b);
	}

	@Benchmark
	public char sum() {
		return (char) (a + b);
	}

	@Benchmark
	public char diff() {
		return (char) (a - b);
	}

	@Benchmark
	public char shr() {
		return (char) (a >> b);
	}

	@Benchmark
	public char ushr() {
		return (char) (a >>> b);
	}

	@Benchmark
	public char shl() {
		return (char) (a << b);
	}
}
