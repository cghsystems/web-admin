package admin.invoice


def js = '''
	$(document).ready(function() {
	     
		$("#email-form").hide()
		$("#email-sent").dialog( { autoOpen: false, modal: true, title: "Email Sent" } )
	
		$("#view-invoice").hide()
	        $('#view-invoice').click(function() { 
	    	window.open("InvoicePreview.groovy");
	    });

	
	    $('#dateHeld').datepicker({ dateFormat: 'dd/mm/yy' })
	    $('#dividend-generator').button()
		
	    $('#send-email').button()
	    $('#send-email').attr('disabled', true);
	
	    $("#dividend-generator").click(function() { 
	         var dateHeld = $("#dateHeld").val()
	         var dividendValue = $("#dividendValue").val()
	         buildEmailTemplate(company, dateHeld, director, dividendValue)
	         buildPdfAttatchment(days, number, fromDate, toDate)
	    });
	
	    $('#send-email').button()
	    $('#send-email').attr('disabled', true);
	    $('#send-email').click(function() {
	         var toAddress = $('#toAddress').val()
	         var ccAddress = $('#ccAddress').val()         
                 var fromAddress = $('#fromAddress').val()
	         var subject = $('#subject').val()
	         var body = $('#body').val()
	         sendEmail(toAddress, ccAddress, fromAddress, subject, body)
	    });
	});

    function sendEmail(toAddress, ccAddress, fromAddress, subject, body) {     
	     
	     $.ajax({ url: "InvoiceEmailSender.groovy", data: {toAddress:toAddress, ccAddress:ccAddress, fromAddress:fromAddress, subject:subject, body:body }, success: function(response){
             $('#email-sent').dialog('open')
	     }});
    }
	
    function buildPdfAttatchment(days, number, fromDate, toDate) {
	     $.ajax({ url: "InvoicePdfBuilder.groovy", data: {number: number, days: days, toDate: toDate, fromDate: fromDate }, success: function(response){
	         var attatchment = $(response).find("attatchment-name").text()
	
	         $("#attatchment").val(attatchment)
	         $('#attatchment-building').hide()
	         $('#view-invoice').show()
	
	         $('#send-email').removeAttr('disabled');
         }});	
    }
	
	function buildEmailTemplate(dateHeld, dividendValue) {
		$.ajax({ url: "DividendBuilder.groovy", data: {dateHeld: dateHeld, dividendValue: dividendValue}, success: function(response){
            
        	 var subject = $(response).find("subject").text()
        	 $("#subject").val(subject)

        	 var emailBody = $(response).find("emailBody").text()
       	         $("#body").val(emailBody)

       	         var ccAddress = $(response).find("ccAddress").text()
	         $("#ccAddress").val(ccAddress)
    
       	         var toAddress = $(response).find("toAddress").text()
       	         $("#toAddress").val(toAddress)
       	  
	         $("#email-form").show("slow")
         }});	
    }
	'''

html.html() {
	head {
		title "Dividend Declaration Generator" 
		link(type:"text/css", rel:"stylesheet", href:"http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/redmond/jquery-ui.css")
		script(type:"text/javascript", src:"http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js") {  mkp.yield( "" ) }
		script(type:"text/javascript", src:"http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.4/jquery-ui.min.js") {  mkp.yield( "" ) }
		script(type:"text/javascript") { mkp.yield(js) }
	}
	
	body() {
		
		p { h1("Dividend Declaration Generator") } 
		
		div(id:"pdfForm") {
			table {
				tr {
					td "Dividend Meeting Date:" 
					td{
						input(type:"text", size:12, name:"dateHeld", id:"dateHeld")
					}  
				}
				tr {
					td "Dividend Value:" 
					td{
						input(type:"text", size:7, name:"dividendValue", id:"dividendValue")
					}  
				}
				tr { 
					td{
						button(id:"dividend-generator") { mkp.yield("Generate") }
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
					td "cc" 
					td{
						input(type:"text", size:50, name:"ccAddress", id:"ccAddress")
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
