package com.cghsystems.admin.mail.payslip

import javax.mail.internet.MimeMessage
import com.cghsystems.admin.ds.Document
import com.cghsystems.admin.ds.dao.DAO
import com.cghsystems.admin.mail.MailHandler
import com.cghsystems.email.Email
import com.cghsystems.email.EmailAttatchment

class PayslipMailHandler implements MailHandler {
	
	final String keywords = "Payslip"
	final Date today = new Date();
	
	DAO<Document> dao = new DAO<Document>();
	
	void process(MimeMessage msg) {
		
		Email email = new Email(msg:msg);
		EmailAttatchment at = email.attatchment
		
		String lines = at.getAttatchment()
		String desc = getDesc(at);
		
		Document doc = new Document(document:lines, keywords:keywords, description:desc, date:today)  
		dao.save(doc);
		
		//Save email attatchment
		//Show saved email in servlet
		//Display a pdf
	}
	
	def getDesc(EmailAttatchment at) {
		String name = at.getName();
		return "Payslip ${name} for ${today}"
	}
}
