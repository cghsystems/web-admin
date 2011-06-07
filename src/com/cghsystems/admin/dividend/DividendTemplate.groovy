package com.cghsystems.admin.dividend
;

import net.cghsystems.div.DividendDeclaration;

public class DividendTemplate {
	
	DividendDeclaration dividend
	
	def body() {
		"""Hi ${dividend.client.contact.name}
	        
	    Please find enclosed the latest dividend declaration form for ${dividend.company.name}
	        
Thanks

Chris

Director
CGH Systems Limited
t. 07828030854
e. chris@cghsystems.net
"""
	}
	
	def subject() {
		"${dividend.taxPointDate2.monthAsString()} ${dividend.taxPointDate2.year()} Invoice for CGH Systems"
	}
}
