import javax.mail.internet.InternetAddress 
import java.util.logging.Logger

import javax.activation.DataHandler;
import javax.mail.Message
import javax.mail.Multipart
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress 
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart
import javax.mail.util.ByteArrayDataSource;

private Logger log = Logger.getLogger("InvoiceEmailHandler")

String to = request.getParameter("toAddress")
String fromAddress = request.getParameter("fromAddress")
String subject = request.getParameter("subject")
String body = request.getParameter("body")


Properties properties = new Properties()
Session mailSession = Session.getDefaultInstance(properties, null)
mailSession.setDebug(true)

def invoice = session.getAttribute("invoice")

Multipart mp = new MimeMultipart("related")
MimeBodyPart attachment = new MimeBodyPart()
attachment.setFileName("invoice.pdf")
attachment.setContent(invoice, "application/pdf")
attachment.setHeader("Content-ID","<pdf>")
ByteArrayDataSource mimePartDataSource = new ByteArrayDataSource (invoice, "aplication/pdf"); 
attachment.setDataHandler(new DataHandler(mimePartDataSource));
mp.addBodyPart(attachment)

MimeBodyPart htmlPart = new MimeBodyPart();
htmlPart.setContent(body, "text/html");
mp.addBodyPart(htmlPart);

Message msg = new MimeMessage(mailSession)
msg.setFrom(new InternetAddress(fromAddress))
msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to))
msg.setSubject(subject)
msg.setText(body)
msg.setContent(mp)

log.info  "Sending invoice email"
Transport.send(msg)
log.info "Invoice email sent"
