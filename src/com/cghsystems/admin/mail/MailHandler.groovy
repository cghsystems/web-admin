package com.cghsystems.admin.mail
;

import javax.mail.internet.MimeMessage

interface MailHandler {
	
	void process(MimeMessage msg);
	
}
