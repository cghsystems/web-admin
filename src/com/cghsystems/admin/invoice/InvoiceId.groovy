package com.cghsystems.admin.invoice

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.cghsystems.admin.ds.dao.DAO;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable
class InvoiceId {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key id;
	
	@Persistent
	private Integer currentId = 0
	
	private def dao = new DAO<InvoiceId>()
	
	def getCurrentId() {
		if(currentId == null) {
			def invoiceId = loadCurrentInvoiceIdFromDB();
			return invoiceId.currentId
		}
		return currentId
	}
	
	def nextId() {
		def invoiceId = loadCurrentInvoiceIdFromDB();
		currentId =  invoiceId.increment()
		dao.save(invoiceId)
		return currentId
	}
	
	private def increment() {
		currentId = currentId.next()
		return currentId
	}
	
	private def loadCurrentInvoiceIdFromDB() {
		dao.findObject(InvoiceId)
	}
	
	@Override
	public String toString() {
		"Id: $currentId"
	}	
}
