
response.setContentType("application/pdf");
response.setHeader("Content-Disposition","invoice-preview.pdf")

def invoice = session.getAttribute("invoice")
sout.write(invoice)
