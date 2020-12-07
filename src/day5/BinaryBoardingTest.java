package day5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinaryBoardingTest {

	@Test
	void testDecodeRow() {
		assertEquals(70, BinaryBoarding.decodeRow("BFFFBBFRRR"));
		assertEquals(14, BinaryBoarding.decodeRow("FFFBBBFRRR"));
		assertEquals(102, BinaryBoarding.decodeRow("BBFFBBFRLL"));
	}
	
	@Test
	void testDecodeColumn() {
		assertEquals(7, BinaryBoarding.decodeColumn("BFFFBBFRRR"));
		assertEquals(7, BinaryBoarding.decodeColumn("FFFBBBFRRR"));
		assertEquals(4, BinaryBoarding.decodeColumn("BBFFBBFRLL"));
	}
	
	@Test
	void testDecodeSeatID() {
		assertEquals(567, BinaryBoarding.decodeSeatID(70,7));
		assertEquals(119, BinaryBoarding.decodeSeatID(14,7));
		assertEquals(820, BinaryBoarding.decodeSeatID(102,4));
	}


}
