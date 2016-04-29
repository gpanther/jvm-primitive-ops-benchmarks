package net.greypanther.opbench.primitives;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class BenchmarkShort {
	private short a;
	private short b;

	@Setup
	public void setUp() {
		a = 42;
		b = 7;
	}

	@Benchmark
	public short div() {
		return (short) (a / b);
	}

	@Benchmark
	public short mul() {
		return (short) (a * b);
	}

	@Benchmark
	public short mod() {
		return (short) (a % b);
	}

	@Benchmark
	public short sum() {
		return (short) (a + b);
	}

	@Benchmark
	public short diff() {
		return (short) (a - b);
	}

	@Benchmark
	public short shr() {
		return (short) (a >> b);
	}

	@Benchmark
	public short ushr() {
		return (short) (a >>> b);
	}

	@Benchmark
	public short shl() {
		return (short) (a << b);
	}
}
