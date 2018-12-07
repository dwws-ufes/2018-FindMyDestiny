package br.inf.ufes.findmydestiny.core.persistence;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.Query;

import br.inf.ufes.findmydestiny.core.domain.TourismPackage;

 @ApplicationScoped
public class TourismPackageDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName="FindMyDestiny")
	private EntityManager em;
	
	public boolean insertPackage(String tp, Long userid,String startDate,String endDate) {
		
		try {
			TourismPackage tpa = new TourismPackage();
			tpa.setUserid(userid);
			tpa.setPackageName(tp);	
			tpa.setStartDate(startDate);
			tpa.setEndDate(endDate);
			em.persist(tpa);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
/*	public List<Tuple> searchPackages(Long userid)
	{
		
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
		       CriteriaQuery<Tuple> q = cb.createTupleQuery();
		       Root<TourismPackage> pkg = q.from(TourismPackage.class);
		       Path<Integer> pkgid = pkg.get("packageId");
		       Path<Long> pkgname = pkg.get("packageName");
		       q.multiselect(pkgname,pkgid);		       
		       q.where(cb.equal(pkg.get("userid"), userid));
		     
			return em.createQuery(q).getResultList();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	
	}*/
	
	public List<TourismPackage> searchPackages(Long userid)
	{
		
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
		       CriteriaQuery<TourismPackage> q = cb.createQuery(TourismPackage.class);
		       Root<TourismPackage> pkg = q.from(TourismPackage.class);
		       //Path<Integer> pkgid = pkg.get("packageId");
		       //Path<Long> pkgname = pkg.get("packageName");
		       q.select(pkg);		       
		       q.where(cb.equal(pkg.get("userid"), userid));
		     
			return em.createQuery(q).getResultList();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	public List<TourismPackage> retrieveAll()
	{
		
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
		       CriteriaQuery<TourismPackage> q = cb.createQuery(TourismPackage.class);
		       Root<TourismPackage> pkg = q.from(TourismPackage.class);		    		       
		       q.select(pkg);		       		       		     
			return em.createQuery(q).getResultList();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	public void deletePackage(int id) {
		try {
			TourismPackage tp = em.find(TourismPackage.class, id);
			em.remove(tp);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
