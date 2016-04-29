package net.greypanther.opbench.primitives;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class BenchmarkByte {
	private byte a;
	private byte b;

	@Setup
	public void setUp() {
		a = 42;
		b = 7;
	}

	@Benchmark
	public byte div() {
		return (byte) (a / b);
	}

	@Benchmark
	public byte mul() {
		return (byte) (a * b);
	}

	@Benchmark
	public byte mod() {
		return (byte) (a % b);
	}

	@Benchmark
	public byte sum() {
		return (byte) (a + b);
	}

	@Benchmark
	public byte diff() {
		return (byte) (a - b);
	}

	@Benchmark
	public byte shr() {
		return (byte) (a >> b);
	}

	@Benchmark
	public byte ushr() {
		return (byte) (a >>> b);
	}

	@Benchmark
	public byte shl() {
		return (byte) (a << b);
	}
}
