package day2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PasswordPhilosophyTest {

	@Test
	void testPartOne() {
		assertEquals(true,PasswordPhilosophy.isValidPasswordPartOne().test("3-11 z: zzzzwqesafwa"));
		assertEquals(true,PasswordPhilosophy.isValidPasswordPartOne().test("3-4 h: hhhjh"));
		assertEquals(false,PasswordPhilosophy.isValidPasswordPartOne().test("10-11 n: nntnnvnnncn"));
		assertEquals(false,PasswordPhilosophy.isValidPasswordPartOne().test("6-8 r: drbzrhdr"));
	}
	
	@Test
	void testPartTwo() {
		assertEquals(false,PasswordPhilosophy.isValidPasswordPartTwo().test("1-3 z: zzzzwqesafwa"));
		assertEquals(true,PasswordPhilosophy.isValidPasswordPartTwo().test("1-3 z: zzdzwqesafwa"));
		assertEquals(false,PasswordPhilosophy.isValidPasswordPartTwo().test("1-3 z: abcdws"));
		assertEquals(true,PasswordPhilosophy.isValidPasswordPartTwo().test("1-6 q: qqqwwwqqq"));
		assertEquals(true,PasswordPhilosophy.isValidPasswordPartTwo().test("1-7 g: grdtttttttt"));
	}

}
