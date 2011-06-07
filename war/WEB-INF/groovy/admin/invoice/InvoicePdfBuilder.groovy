package admin.invoice
import com.cghsystems.admin.invoice.InvoiceAdapter 
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
def name = "cghsystems-invoice-${number}.pdf"

InvoiceAdapter adapter = new InvoiceAdapter();
adapter.build(fromDate,toDate,number,days);

OutputStream os = adapter.build(fromDate,toDate,number,days);
session.setAttribute("invoice", os.toByteArray())
session.setAttribute("invoice-name", name)

response.getWriter().write("<res><attatchment-name>${name}</attatchment-name></res>"); 