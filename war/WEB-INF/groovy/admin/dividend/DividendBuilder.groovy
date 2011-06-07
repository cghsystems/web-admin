package admin.dividend

import java.text.DateFormat 
import java.text.SimpleDateFormat 

import com.cghsystems.admin.dividend.DividendTemplate 

import net.cghsystems.div.CghSystemsDividendDeclarationBuilder 

if (!session) {
	session = request.getSession(true);
}


DateFormat df = new SimpleDateFormat("dd/MM/yyyy")

Date dateHeld = df.parse(request.getParameter("dateHeld"));
BigDecimal value = new BigDecimal(request.getParameter("dividendValue"))

def builder = new CghSystemsDividendDeclarationBuilder()
def dividend = builder.build(dateHeld, value)
def dividendTemplate = new DividendTemplate(dividend:dividend)

response.getWriter().write("<res><toAddress>${dividend.client.contact.emailAddress}</toAddress><ccAddress>${dividend.client.contact.ccEmailAddress}</ccAddress><subject>${dividendTemplate.subject()}</subject><emailBody>${dividendTemplate.body()}</emailBody><attatchment-name>cghsystems-dividend-declaration.pdf</attatchment-name></res>"); 