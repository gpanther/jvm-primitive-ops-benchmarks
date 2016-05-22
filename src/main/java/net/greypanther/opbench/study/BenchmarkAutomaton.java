package net.greypanther.opbench.study;

import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class BenchmarkAutomaton {
	private static final byte[] TABLE = constructTable();
	private static final byte[] TO_ADD = { 0, 0, 0, 0, 1 };

	private byte[] bytes;

	@Setup
	public void setUp() {
		bytes = new byte[1000];
		Random r = new Random(71);
		r.nextBytes(bytes);
	}

	private static int computeIndex(int state, int value) {
		return state << 6 + value & 0x3F;
	}

	private static byte[] constructTable() {
		byte[] result = new byte[5 * 64];
		result[computeIndex(0, 'H')] = 1;
		result[computeIndex(1, 'e')] = 2;
		result[computeIndex(2, 'l')] = 3;
		result[computeIndex(3, 'l')] = 4;
		result[computeIndex(4, 'o')] = 0;
		return result;
	}

	@Benchmark
	public int codeAutomaton() {
		int counter = 0;
		int state = 0;
		for (int i = 0; i < bytes.length; ++i) {
			byte b = bytes[i];
			switch (state) {
			case 0:
				state = b == 'H' ? 1 : 0;
				break;
			case 1:
				state = b == 'e' ? 2 : 0;
				break;
			case 2:
				state = b == 'l' ? 3 : 0;
				break;
			case 3:
				state = b == 'l' ? 4 : 0;
				break;
			case 4:
				if (b == 0) {
					counter += 1;
				}
				state = 0;
				break;
			default:
				throw new IllegalArgumentException();
			}
		}
		return counter;
	}

	@Benchmark
	public int tableAutomaton() {
		int counter = 0;
		int state = 0;
		for (int i = 0; i < bytes.length; ++i) {
			state = TABLE[computeIndex(state, bytes[i] & 0xFF)];
			counter += TO_ADD[state];
		}
		return counter;
	}
}
