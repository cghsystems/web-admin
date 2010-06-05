package invoice
html.html() {
	head {
		title "Invoice Generator" 
		script(type:"text/javascript", src:"../js/jquery-1.4.2.js") {  mkp.yield("Text")  }
	}
	
	body() {
		p {h1("Invoice Generator") } 
		
		div(id:"pdfForm") {
			form(name:"Generate", action:"InvoiceBuilder.groovy") {
				table {
					tr {
						td "Invoice Number:" 
						td{
							input(type:"text", size:3, name:"number")
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
						td{
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
		
		div(id:"emailForm") {
			form(name:"Email", action:"InvoiceEmailSender.groovy") {
				table {
					tr {
						td "to" 
						td{
							input(type:"text", size:50, name:"toAddress", value:"hedley.christopher@gmail.com")
						}  
					}
					tr {
						td "from:" 
						td{
							input(type:"text", size:50, name:"fromAddress", value:"chris@cghsystems.net")
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
							input(type:"text", size:50, name:"subject", value:"Invoice...")
						}  
					}
					tr {
						td "Body" 
						td{
							input(type:"text", size:100, name:"body", value:"Hi...")
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
		
		div(id:"emailSent") { p("Email Sent") }
	}
}
