package com.cghsystems.email
;

import java.util.List;


class EmailAttatchment {
	
	List<String> content;
	
	String name;
	
	String getAttatchment() {
		return content.join("\n")
	}
}
