package net.greypanther.opbench.study;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class BenchmarkLongDiv {
	private long c;
	private long d;

	@Benchmark
	public long testDivWithReturnAndInitializedFields() {
		return c / d;
	}

	@Setup
	public void setup() {
		c = 42L;
		d = 7L;
	}
}
