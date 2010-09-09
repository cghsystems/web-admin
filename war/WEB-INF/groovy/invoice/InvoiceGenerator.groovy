package invoice


import com.cghsystems.admin.invoice.InvoiceId 

def invoiceId = new InvoiceId();

def js = '''
	$(document).ready(function() {
	
		$("#emailForm").hide()
		$("#emailSent").hide()
	
	    $("#invoice-generator").click(function() { 
	    	 var number = $("#number").val()
	         var days = $("#days").val()
        	 var fromDate = $("#fromDate").val()
	         var toDate = $("#toDate").val()
	         submit(days, number, fromDate, toDate)	    	
	    });
	});
	
	function submit(days, number, fromDate, toDate) {
		$.ajax({ url: "InvoiceBuilder.groovy", context: document.body, data: {number: number, days: days, toDate: toDate, fromDate:"12/12/20210"  }, success: function(response){
            
        	 var subject = $(response).find("subject").text()
        	 $("#subject").val(subject)

        	 var emailBody = $(response).find("emailBody").text()
       	     $("#body").val(emailBody)

       	     var toAddress = $(response).find("toAddress").text()
       	     $("#toAddress").val(toAddress)
	
       	     var attatchment = $(response).find("attatchment-name").text()
    	     $("#attatchment").val(attatchment)
       	  
	         $("#emailForm").show()
         }});	
    }
	'''

html.html() {
	head {
		title "Invoice Generator" 
		script(type:"text/javascript", src:"/js/jquery-1.4.2.js") {  mkp.yield( "" ) }
		script(type:"text/javascript") { mkp.yield(js) }
	}
	
	body() {
		
		p { h1("Invoice Generator") } 
		
		div(id:"pdfForm") {
			table {
				tr {
					td "Invoice Number:" 
					td{
						input(type:"text", size:3, name:"number", id:"number", value:invoiceId.nextId())
					}  
				}
				tr {
					td "Days:" 
					td{
						input(type:"text", size:3, name:"days", id:"days")
					}  
				}
				tr {
					td "From Date:" 
					td{
						input(type:"text", size:12, name:"fromDate", id:"fromDate")
					}  
				}
				tr {
					td "To Date:" 
					td { 
						input(type:"text", size:12, name:"toDate", id:"toDate") 
					}  
				}
				tr { 
					td{
						button(id:"invoice-generator") { mkp.yield("Generate") }
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
							input(type:"text", size:50, name:"toAddress", id:"toAddress")
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
							input(type:"text", size:50, name:"attatchment", id:"attatchment")
						}  
					}
					tr {
						td "Subject:" 
						td{
							input(type:"text", size:50, id:"subject", name:"subject")
						}  
					}
					tr {
						td "Body" 
						td{
							textarea(name:"body", id:"body", cols:49, rows:14){ mkp.yield("")}
						}    
					}
					tr { 
						td{
							button(name:"email") { mkp.yield("Send Email") }
						}  
					}
				}
			}
		}
		
		div(id:"emailSent") { p("Email Sent") }
	}
}
