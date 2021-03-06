package com.cghsystems.admin.ds.dao
;


import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query
import java.util.logging.Logger

class DAO<T> {
	
	private Logger log = Logger.getLogger("DAO")
	
	private static final PersistenceManagerFactory daoF =  JDOHelper.getPersistenceManagerFactory("transactions-optional")
	
	private final PersistenceManager dao = daoF.getPersistenceManager();
	
	def save(T persistent) {
		log.info("Saving ${persistent} ...")
		dao.makePersistent(persistent)
		dao.close();
		log.info("${persistent} saved")
	}
	
	def findObjects(Class clazz) {
		log.info("Locating ${clazz}")
		dao.lo
		Query query = dao.newQuery(clazz)
		return query.execute();
	}
	
	T findObject(Class clazz) {
		log.info("Locating single class ${clazz}")
		Query query = dao.newQuery(clazz)
		return query.execute().get(0);
	}
	
	def delete(T persistent) {
		log.info("Deleting ${persistent} ...")
		dao.deletePersistent(persistent)
		dao.close();
		log.info("${persistent} saved")
	}
}
