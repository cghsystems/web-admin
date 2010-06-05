package com.cghsystems.servlet

import javax.servlet.ServletContextEvent 

import net.cghsystems.pdf.PdfComponenets;

class ServletContextListener implements javax.servlet.ServletContextListener {
	
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		println "Initialising Context"
		PdfComponenets.setup();
		println "Initialising Context complete"
	}
	
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		
	}
}
