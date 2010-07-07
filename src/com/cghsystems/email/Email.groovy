package com.cghsystems.email
;


import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeMessage
import java.util.logging.Logger

class Email {
	
	private static final Logger log = Logger.getLogger(Email.class
	.getName());
	
	private MimeMessage msg;
	
	def getAttatchment() {
		log.info("Searching for attatchment..")
		
		def content = msg.getContent()
		if(content instanceof Multipart) {
			handleMultipart(content)
		}
	}
	
	def handleMultipart(Multipart multipart) {
		for (int i = 0; i < multipart.getCount(); i++) {
			Part part = multipart.getBodyPart(i);
			
			String disposition = part.getDisposition();
			String contentType = part.getContentType();
			
			if (disposition != null) { // When just body
				if (disposition.equalsIgnoreCase(Part.ATTACHMENT)) {
					log.info("Processing Attatchment");
					return saveFile(part.getFileName(), part.getInputStream());
				} else {  // Should never happen
					System.out.println("Other: " + disposition);
				}
			}
		}
	}
	
	def saveFile(String name, InputStream is) {
		List<String> f = is.readLines();
		EmailAttatchment e = new EmailAttatchment(name:name, content:f)
		return e;
	}
}
