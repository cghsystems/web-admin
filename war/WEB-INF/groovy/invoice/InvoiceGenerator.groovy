package invoice


import com.cghsystems.admin.invoice.InvoiceId 
import com.cghsystems.admin.invoice.InvoiceTemplate;
import net.cghsystems.inv.Invoice 

def invoiceId = new InvoiceId();
def invoice = new Invoice(taxPointDate2: new Date())
def invoiceTemplate = new InvoiceTemplate(invoice:invoice)

html.html() {
	head {
		title "Invoice Generator" 
		script(type:"text/javascript", src:"../js/jquery-1.4.2.js") {  mkp.yield("Text") }
	}
	
	body() {
		p {h1("Invoice Generator") } 
		
		div(id:"pdfForm") {
			form(name:"Generate", action:"InvoiceBuilder.groovy") {
				table {
					tr {
						td "Invoice Number:" 
						td{
							input(type:"text", size:3, name:"number", value:invoiceId.nextId())
						}  
					}
					tr {
						td "Days:" 
						td{
							input(type:"text", size:3, name:"days")
						}  
					}
					tr {
						td "From Date:" 
						td{
							input(type:"text", size:12, name:"fromDate")
						}  
					}
					tr {
						td "To Date:" 
						td { 
							input(type:"text", size:12, name:"toDate") 
						}  
					}
					tr { 
						td{
							input(type:"submit", name:"invoice-generator", value:"Generate")
						}  
					}
				}
			}
		}
		
		div(id:"emailForm", style:"display: show") {
			form(name:"Email", action:"InvoiceEmailSender.groovy") {
				table {
					tr {
						td "to" 
						td{
							input(type:"text", size:50, name:"toAddress", value:"${invoice.client.contact.name}")
						}  
					}
					tr {
						td "from:" 
						td{
							input(type:"text", size:50, name:"fromAddress", value:"hedley.christopher@gmail.com")
						}  
					}
					tr {
						td "Attatchment:" 
						td{
							input(type:"text", size:50, name:"attatchment")
						}  
					}
					tr {
						td "Subject:" 
						td{
							input(type:"text", size:50, name:"subject", value:invoiceTemplate.subject())
						}  
					}
					tr {
						td "Body" 
						td{
							textarea(name:"body", cols:49, rows:14) {
								mkp.yield( invoiceTemplate.body() )
							}
						}    
					}
					tr { 
						td{
							input(type:"submit", name:"email", value:"Send Email")
						}  
					}
				}
			}
		}
		
		div(id:"emailSent", style:"display: show") { p("Email Sent") }
	}
}
