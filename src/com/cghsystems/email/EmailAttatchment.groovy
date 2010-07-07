package com.cghsystems.email
;


class EmailAttatchment {
	
	List<String> content;
	
	String name;
	
	String getAttatchment() {
		return content.join("\n")
	}
}
