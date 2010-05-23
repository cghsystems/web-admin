package docs
import com.cghsystems.admin.ds.Document;
import com.cghsystems.admin.ds.dao.DAO;


def date = new Date()
Document doc = new Document(document:"document", description:"desc", keywords:"keywords", date:date, name:"docname.txt")
DAO<Document> dao = new DAO<Document>();
dao.save doc

html.html() {
	
	head { title("Test Page") }
	
	body { p("Refresh to create new document in development datastore") }
	
}
