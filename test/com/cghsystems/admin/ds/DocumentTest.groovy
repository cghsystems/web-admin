package com.cghsystems.admin.ds;


import com.google.appengine.api.datastore.Blob;


import org.junit.Test
import static org.junit.Assert.assertEquals

class DocumentTest {
	
	@Test
	void shouldConstruct() {
		
		Date date = new Date();
		String keywords = "testKeywords"
		String document = "testDocument"
		String desc = "testDescription"
		
		def unit = new Document(date:date, keywords:keywords, document:document, description:desc)
		
		assertEquals(keywords, unit.getKeywords());
		assertEquals(date, unit.getDate());
		assertEquals(desc, unit.getDescription());
		
		Blob expectedDoc = new Blob(document.getBytes());
		assertEquals(expectedDoc, unit.getDocument());
	}
}
