package invoice


import com.cghsystems.admin.invoice.InvoiceId 

def invoiceId = new InvoiceId();

def js = '''
	$(document).ready(function() {
	     
		$("#email-form").hide()
		$("#email-sent").dialog( { autoOpen: false, modal: true, title: "Email Sent" } )
	
		$("#view-invoice").hide()
	    $('#view-invoice').click(function() { 
	    	window.open("InvoicePreview.groovy");
	    });

	
		$('#fromDate').datepicker()
		$('#toDate').datepicker()
		$('#invoice-generator').button()
		
	    $('#send-email').button()
		$('#send-email').attr('disabled', true);
	
	    $("#invoice-generator").click(function() { 
	    	 var number = $("#number").val()
	         var days = $("#days").val()
        	 var fromDate = $("#fromDate").val()
	         var toDate = $("#toDate").val()
	         buildEmailTemplate(days, number, fromDate, toDate)
	         buildPdfAttatchment(days, number, fromDate, toDate)
	    });
	
	    $('#send-email').button()
	    $('#send-email').attr('disabled', true);
	    $('#send-email').click(function() {
	         var toAddress = $('#toAddress').val()
	         var fromAddress = $('#fromAddress').val()
	         var subject = $('#subject').val()
	         var body = $('#body').val()
	         sendEmail(toAddress, fromAddress, subject, body)
	    });
	});

    function sendEmail(toAddress, fromAddress, subject, body) {     
	     
	     $.ajax({ url: "InvoiceEmailSender.groovy", data: {toAddress:toAddress, fromAddress:fromAddress, subject:subject, body:body }, success: function(response){
             $('#email-sent').dialog('open')
	     }});
    }
	
    function buildPdfAttatchment(days, number, fromDate, toDate) {
	     $.ajax({ url: "InvoicePdfBuilder.groovy", data: {number: number, days: days, toDate: toDate, fromDate:"12/12/20210"  }, success: function(response){
	         var attatchment = $(response).find("attatchment-name").text()
	
	         $("#attatchment").val(attatchment)
	         $('#attatchment-building').hide()
	         $('#view-invoice').show()
	
	         $('#send-email').removeAttr('disabled');
         }});	
    }
	
	function buildEmailTemplate(days, number, fromDate, toDate) {
		$.ajax({ url: "InvoiceBuilder.groovy", data: {number: number, days: days, toDate: toDate, fromDate:"12/12/20210"  }, success: function(response){
            
        	 var subject = $(response).find("subject").text()
        	 $("#subject").val(subject)

        	 var emailBody = $(response).find("emailBody").text()
       	     $("#body").val(emailBody)

       	     var toAddress = $(response).find("toAddress").text()
       	     $("#toAddress").val(toAddress)
       	  
	         $("#email-form").show("slow")
         }});	
    }
	'''

html.html() {
	head {
		title "Invoice Generator" 
		link(type:"text/css", rel:"stylesheet", href:"http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/redmond/jquery-ui.css")
		script(type:"text/javascript", src:"http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js") {  mkp.yield( "" ) }
		script(type:"text/javascript", src:"http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.4/jquery-ui.min.js") {  mkp.yield( "" ) }
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
		
		div(id:"email-form") {
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
						input(type:"text", size:50, id:"fromAddress", name:"fromAddress", value:"hedley.christopher@gmail.com")
					}  
				}
				tr {
					td "Attatchment:" 
					td{
						input(type:"text", size:50, name:"attatchment", id:"attatchment")
					}
					td {
						div(id:"attatchment-building") {
							img(src:"/images/wait20.gif", alt:"Building...")
						}
						span(id:"view-invoice", class:"ui-icon ui-icon-document")
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
						textarea(name:"body", id:"body", cols:49, rows:14){ mkp.yield("") }
					}    
				}
				tr { 
					td{
						button(id:"send-email") { mkp.yield("Send Email") }
					}  
				}
			}
		}
		
		div(id:"email-sent") { p("Your invoice has been sent") }
	}
}
