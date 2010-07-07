package com.cghsystems.mail;


import com.cghsystems.email.EmailAttatchment
import org.junit.Test
import static org.junit.Assert.assertEquals

class EmailAttatchmentTest {
	
	@Test
	void shouldGetLinesWithLIneBreak() {
		
		def lines = ["line1", "line2"]
		EmailAttatchment unit = new EmailAttatchment(content:lines, name:"testname")
		
		assertEquals("testname", unit.getName());
		
		String expectedLines = "line1\nline2"
		assertEquals(expectedLines, unit.getAttatchment())
	}
}
