package invoice
html.html() {
	head { title("Invoice Generator") }
	
	body() {
		p {h1("Invoice Generator") } 
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
						input(type:"text", size:3, name:"fromDate")
					}  
				}
				tr {
					td "To Date:" 
					td{
						input(type:"text", size:3, name:"toDate")
					}  
				}
				tr { 
					td{
						input(type:"submit", name:"submit")
					}  
				}
				
			}
		}
	}
}
