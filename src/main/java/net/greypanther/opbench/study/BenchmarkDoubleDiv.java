package net.greypanther.opbench.study;

import java.util.Arrays;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class BenchmarkDoubleDiv {
	// @Benchmark
	// public void testDiv() {
	// 42D / 7D;
	// }

	@Benchmark
	public void testDiv() {
		@SuppressWarnings("unused")
		double result = 42D / 7D;
	}

	@Benchmark
	public double testDivWithReturn() {
		return 42D / 7D;
	}

	private double a;
	private double b;

	@Benchmark
	public double testDivWithReturnAndFields() {
		return a / b;
	}

	private double c;
	private double d;

	@Benchmark
	public double testDivWithReturnAndInitializedFields() {
		return c / d;
	}

	@Benchmark
	@OperationsPerInvocation(10)
	public double testDivWith10SummedFields() {
		double result = 0;
		for (int i = 0; i < 10; ++i) {
			result += c / d;
		}
		return result;
	}

	@Benchmark
	@OperationsPerInvocation(100)
	public double testDivWith100SummedFields() {
		double result = 0;
		for (int i = 0; i < 100; ++i) {
			result += c / d;
		}
		return result;
	}

	@Benchmark
	@OperationsPerInvocation(10)
	public long testDivWith10XoredFields() {
		long result = 0;
		for (int i = 0; i < 10; ++i) {
			result ^= Double.doubleToRawLongBits(c / d);
		}
		return result;
	}

	@Benchmark
	@OperationsPerInvocation(100)
	public double testDivWith100XoredFields() {
		long result = 0;
		for (int i = 0; i < 100; ++i) {
			result ^= Double.doubleToRawLongBits(c / d);
		}
		return result;
	}

	private double[] as, bs;

	@Benchmark
	@OperationsPerInvocation(10)
	public double[] testDivWith10Fields() {
		double[] result = new double[10];
		for (int i = 0; i < 10; ++i) {
			result[i] = as[i] / bs[i];
		}
		return result;
	}

	private double[] cs;

	@Benchmark
	@OperationsPerInvocation(10)
	public double[] testDivWith10FieldsNoAllocation() {
		for (int i = 0; i < 10; ++i) {
			cs[i] = as[i] / bs[i];
		}
		return cs;
	}

	@Setup
	public void setup() {
		c = 42D;
		d = 7D;

		as = new double[10];
		Arrays.fill(as, 42D);
		bs = new double[10];
		Arrays.fill(bs, 7D);

		cs = new double[10];
	}
}
