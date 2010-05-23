import com.cghsystems.admin.invoice.InvoiceAdapter;
import java.text.DateFormat 
import java.text.SimpleDateFormat 

response.setContentType("application/pdf");
response.setHeader("Content-Disposition"," inline;filename=invoice.pdf")


DateFormat df = new SimpleDateFormat("dd/MM/yyyy")

Date toDate = df.parse(request.getParameter("toDate"));
Date fromDate = df.parse(request.getParameter("fromDate"));

int number = Integer.valueOf(request.getParameter("number"));
int days = Integer.valueOf(request.getParameter("days"));

InvoiceAdapter adapter = new InvoiceAdapter();
OutputStream os = adapter.build(fromDate,toDate,number,days);
sout.write(os.toByteArray())