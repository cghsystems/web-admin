package docs



import com.cghsystems.admin.ds.Document;
import com.cghsystems.admin.ds.dao.DAO 


html.html() {
	
	head { title("Document Overview") }
	
	body {
		p ("Documents overview on ${new Date()}")
		
		DAO<Document> dao = new DAO<Document>();
		Set<Document> docs = dao.findObjects(Document.class)
		for(d in docs) { 
			p()
			table {
				tr {
					td("Name: ${d.getName()}") td("Description ${d.getDescription()}")
				}
				tr {
					td("Type: ${d.getType()}") td("Description ${d.getDate()}")
				}
				tr { td("Keywords: ${d.getKeywords()}") }
			}
		}
	}
}
