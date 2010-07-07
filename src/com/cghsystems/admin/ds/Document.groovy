package com.cghsystems.admin.ds

;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key

@PersistenceCapable
public class Document {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	Key id;
	
	@Persistent
	Blob document;
	
	@Persistent
	String type;
	
	@Persistent
	String keywords;
	
	@Persistent
	String description;
	
	@Persistent
	Date date;
	
	@Persistent
	String name;
	
	void setDocument(String doc) {
		document = new Blob(doc.getBytes())
	}
	
	def getDocument() {
		new String(document.getBytes())
	}
}
