package com.cghsystems.admin.ds.dao
;


import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;



class DAO<T> {
	
	private Logger log = Logger.getLogger("DAO")
	
	static PersistenceManagerFactory daoF =  JDOHelper.getPersistenceManagerFactory("transactions-optional")
	
	PersistenceManager dao = daoF.getPersistenceManager();
	
	def save(T persistent) {
		log.info("Saving ${persistent} ...")
		dao.makePersistent(persistent)
		dao.close();
		log.info("${persistent} saved")
	}
	
	def findObjects(Class clazz) {
		log.info("Locating ${clazz}")
		Query query = dao.newQuery(clazz)
		return query.execute();
	}
	
	def delete(T persistent) {
		log.info("Deleting ${persistent} ...")
		dao.deletePersistent(persistent)
		dao.close();
		log.info("${persistent} saved")
	}
}
