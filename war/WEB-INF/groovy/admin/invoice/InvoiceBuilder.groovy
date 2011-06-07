package admin.invoice
import com.cghsystems.admin.invoice.InvoiceTemplate 
import java.text.DateFormat 
import java.text.SimpleDateFormat 
import net.cghsystems.inv.Invoice 

if (!session) {
	session = request.getSession(true);
}


DateFormat df = new SimpleDateFormat("dd/MM/yyyy")

Date toDate = df.parse(request.getParameter("toDate"));
Date fromDate = df.parse(request.getParameter("fromDate"));
int number = Integer.valueOf(request.getParameter("number"));
int days = Integer.valueOf(request.getParameter("days"));

def invoice = new Invoice(taxPointDate2: toDate)
def invoiceTemplate = new InvoiceTemplate(invoice:invoice)

response.getWriter().write("<res><toAddress>${invoice.client.contact.emailAddress}</toAddress><ccAddress>${invoice.client.contact.ccEmailAddress}</ccAddress><subject>${invoiceTemplate.subject()}</subject><emailBody>${invoiceTemplate.body()}</emailBody><attatchment-name>cghsystems-invoice-${number}.pdf</attatchment-name></res>"); 