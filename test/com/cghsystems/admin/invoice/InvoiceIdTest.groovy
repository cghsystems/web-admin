package com.cghsystems.admin.invoice;

import com.cghsystems.admin.ds.dao.DAO;
import groovy.mock.interceptor.MockFor 
import org.junit.Test 

import static org.junit.Assert.*;

class InvoiceIdTest {
	
	@Test
	void shouldLoadCurrentIdFromDBIncrementAndSaveBackToDatabase() {
		
		def currentInvoiceNumber = 12;
		def returnedInvoiceId = new InvoiceId(currentId:currentInvoiceNumber)
		
		def dao = new MockFor(DAO);
		dao.demand.with {
			findObject(InvoiceId) { returnedInvoiceId }
			save(returnedInvoiceId) { returnedInvoiceId }
		}
		
		dao.use {
			InvoiceId unit = new InvoiceId()
			assert 13 == unit.nextId()
			assert 13 == unit.currentId
			assert 13 == returnedInvoiceId.currentId
		}
		
	}
	
	@Test
	void givenANullCurrentIdThenCurrentIdShouldReturnCurrentInvoiceIdFromDB() {
		
		def currentInvoiceNumber = 12;
		def returnedInvoiceId = new InvoiceId(currentId:currentInvoiceNumber)
		
		def dao = new MockFor(DAO);
		dao.demand.with {
			findObject(InvoiceId) { returnedInvoiceId }
		}
		
		dao.use {
			InvoiceId unit = new InvoiceId()
			assert 12 == unit.currentId
		}
	}
}
