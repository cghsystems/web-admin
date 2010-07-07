
package com.cghsystems.admin.invoice;


import net.cghsystems.inv.pdf.PDFInvoiceGenerator

class InvoiceAdapter {
	
	def build(fromDate,toDate,number,days) {
		println "Building Invoice from $fromDate, to $toDate, number $number, days $days"
		PDFInvoiceGenerator generator = new PDFInvoiceGenerator();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		generator.build(fromDate,toDate,days,number,outputStream)
		println "Invoice ${number} built"
		return outputStream;
	}
}
