package mclaudio76.application.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.Cache;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Query;
import javax.persistence.SynchronizationType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;

import mclaudio76.application.core.multitenancy.TenantContext;

public class ProxyEntityManagerFactory implements EntityManagerFactory{

	private List<EntityManagerFactory> lst = null;
	
	public ProxyEntityManagerFactory() {
	
	}
	
	private EntityManagerFactory locate()  {
		for(EntityManagerFactory emf : lst) {
			if(emf.getProperties().getOrDefault("x-tenant-id", "STD").equals(TenantContext.getTenantID())) {
				return emf;
			}
		}
		return lst.get(0);
	}
	
	
	public ProxyEntityManagerFactory(List<EntityManagerFactory> lst) {
		this.lst = lst;
	}
	
	@Override
	public EntityManager createEntityManager() {
		return locate().createEntityManager();
	}

	@Override
	public EntityManager createEntityManager(Map map) {
		return locate().createEntityManager(map);
	}

	@Override
	public EntityManager createEntityManager(SynchronizationType synchronizationType) {
		return locate().createEntityManager(synchronizationType);
	}

	@Override
	public EntityManager createEntityManager(SynchronizationType synchronizationType, Map map) {
		return locate().createEntityManager(synchronizationType,map);
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return locate().getCriteriaBuilder();
	}

	@Override
	public Metamodel getMetamodel() {
		return locate().getMetamodel();
	}

	@Override
	public boolean isOpen() {
		return locate().isOpen();
	}

	@Override
	public void close() {
		locate().close();
	}

	@Override
	public Map<String, Object> getProperties() {
		return locate().getProperties();
	}

	@Override
	public Cache getCache() {
		return locate().getCache();
	}

	@Override
	public PersistenceUnitUtil getPersistenceUnitUtil() {
		return locate().getPersistenceUnitUtil();
	}

	@Override
	public void addNamedQuery(String name, Query query) {
		locate().addNamedQuery(name, query);
		
	}

	@Override
	public <T> T unwrap(Class<T> cls) {
		return locate().unwrap(cls);
	}

	@Override
	public <T> void addNamedEntityGraph(String graphName, EntityGraph<T> entityGraph) {
		 locate().addNamedEntityGraph(graphName, entityGraph);
	}

}
