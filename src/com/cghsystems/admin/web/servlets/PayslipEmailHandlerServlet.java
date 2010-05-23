package com.cghsystems.admin.web.servlets;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cghsystems.admin.mail.MailHandler;
import com.cghsystems.admin.mail.payslip.PayslipMailHandler;

public class PayslipEmailHandlerServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(PayslipEmailHandlerServlet.class
			.getName());

	private static final long serialVersionUID = 7116279450893136328L;

	private MailHandler payslipMailHandler = new PayslipMailHandler();

	public PayslipEmailHandlerServlet() {
		log.info("Registering Mail Servlet");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		log.info("Processing incoming email");
		try {
			MimeMessage msg = getMimeMessage(req);
			processPayslipMail(msg);
		} catch (Exception e) {
			log.log(Level.SEVERE, "Error processing incoming email", e);
		}
		log.info("Incoming email processing complete");
	}

	private void processPayslipMail(MimeMessage msg) {
		payslipMailHandler.process(msg);
	}

	private MimeMessage getMimeMessage(HttpServletRequest req)
			throws IOException, MessagingException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		return new MimeMessage(session, req.getInputStream());
	}
}
