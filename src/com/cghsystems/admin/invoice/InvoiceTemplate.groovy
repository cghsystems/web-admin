package com.cghsystems.admin.invoice;

import net.cghsystems.inv.Invoice;

public class InvoiceTemplate {
	
	Invoice invoice
	
	def body() {
		"""Hi ${invoice.client.contact.name}
	        
Please find enclosed the ${invoice.taxPointDate2.monthAsString()} ${invoice.taxPointDate2.year()} invoice for CGH Systems.
	        
Thanks

Chris

Director
CGH Systems Limited
t. 07828030854
e. chris@cghsystems.net
"""
	}
	
	def subject() {
		"${invoice.taxPointDate2.monthAsString()} ${invoice.taxPointDate2.year()} Invoice for CGH Systems"
	}
}
