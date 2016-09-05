package com.jcim.fdk.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;

import com.jcim.fdk.dao.CustomerDao;
import com.jcim.fdk.model.Customer;

public class CustomerDaoImpl extends AbstractDaoImpl<Customer> implements CustomerDao
{
//	private ChangeLogDao changeLogDao;

	private java.lang.reflect.Field[] fields;

	public CustomerDaoImpl(ApplicationContext context) 
	{
		super(context);
//		changeLogDao = context.getBean(ChangeLogDao.class);	
//		fields = changeLogDao.collectFields(Customer.class);
	}

	@Override
	public Customer createEntity() 
	{
		return new Customer();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.inubit.lcag.wfs.dao.CustomerDao#create(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public long create(String account_id, String account_name, String owner_id,
			String owner_name, String account_team, String saleslevel_6,
			String cdb_number, String station_code, String booking_short_time,
			String url, String sap_code, String cass_code, String lata_code,
			String lav_lsv_code, String industry_type, String account_role,
			String company_type,
			String parent_account_cdb, String parent_account_name, 
			String preferred_cargo_airlines, boolean credit_worthiness, 
			String status, String inactive_reason) 
	{
		Customer c = createEntity();
		c.setAccountId(account_id);
		c.setAccountName(account_name);
		c.setOwnerId(owner_id);
		c.setOwnerName(owner_name);
		c.setAccountTeam(account_team);
		c.setSaleslevel6(saleslevel_6);
		c.setCdbNumber(cdb_number);
		c.setStationCode(station_code);
		c.setBookingShortName(booking_short_time);
		c.setUrl(url);
		c.setSapCode(sap_code);
		c.setCassCode(cass_code);
		c.setLataCode(lata_code);
		c.setLavLsvCode(lav_lsv_code);
		c.setIndustryType(industry_type);
		c.setAccountRole(account_role);
		c.setCompanyType(company_type);
		c.setParentAccountCdb(parent_account_cdb);
		c.setParentAccountName(parent_account_name);
		c.setPreferredCargoAirlines(preferred_cargo_airlines);
		c.setCreditWorthiness(credit_worthiness);
		c.setStatus(status);
		c.setInactiveReason(inactive_reason);
		
		long id = super.create(c);
		
//		changeLogDao.logCreateOrUpdateEntity(null, c, fields);
		
		return id;
	}
	
	@Override
	public long uiCreate(String account_name, String industry_type,
			String station_code, String lav_lsv_code) 
	{
		Customer c = createEntity();
		c.setAccountName(account_name);
		c.setIndustryType(industry_type);
		c.setStationCode(station_code);
		c.setLavLsvCode(lav_lsv_code);		
		
		long id = super.create(c);
		
//		changeLogDao.logCreateOrUpdateEntity(null, c, fields);
		
		return id;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.inubit.lcag.wfs.dao.impl.AbstractDaoImpl#retrieve(long)
	 */
	@Override
	public Customer retrieve(long id)
	{
		Customer customer = super.retrieve(id);
		return customer;
	}
	
	@Override
	public Customer findByCdbNumber(String cdb_number) {
		
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.eq("cdbNumber", cdb_number));
		Customer customer = (Customer)criteria.uniqueResult();
		
		return customer;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.inubit.lcag.wfs.dao.CustomerDao#update(long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void update(long customerId, String account_id, String account_name, String owner_id,
			String owner_name, String account_team, String saleslevel_6,
			String cdb_number, String station_code, String booking_short_time,
			String url, String sap_code, String cass_code, String lata_code,
			String lav_lsv_code, String industry_type, String account_role,
			String company_type,
			String parent_account_cdb, String parent_account_name, 
			String preferred_cargo_airlines, boolean credit_worthiness, 
			String status, String inactive_reason)
	{
		Customer c = retrieve(customerId);
		
		Object[] oldValues = null; //changeLogDao.collect(c, fields);
		
		c.setAccountId(account_id);
		c.setAccountName(account_name);
		c.setOwnerId(owner_id);
		c.setOwnerName(owner_name);
		c.setAccountTeam(account_team);
		c.setSaleslevel6(saleslevel_6);
		c.setCdbNumber(cdb_number);
		c.setStationCode(station_code);
		c.setBookingShortName(booking_short_time);
		c.setUrl(url);
		c.setSapCode(sap_code);
		c.setCassCode(cass_code);
		c.setLataCode(lata_code);
		c.setLavLsvCode(lav_lsv_code);
		c.setIndustryType(industry_type);
		c.setAccountRole(account_role);
		c.setCompanyType(company_type);
		c.setParentAccountCdb(parent_account_cdb);
		c.setParentAccountName(parent_account_name);
		c.setPreferredCargoAirlines(preferred_cargo_airlines);
		c.setCreditWorthiness(credit_worthiness);
		c.setStatus(status);
		c.setInactiveReason(inactive_reason);
		
		super.update(c);
				
//		changeLogDao.logCreateOrUpdateEntity(oldValues, c, fields);
	}

	/*
	 * (non-Javadoc)
	 * @see com.inubit.lcag.wfs.dao.CustomerDao#uiUpdate(long, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void uiUpdate(long customerId, String account_name,
			String industry_type, String station_code, String lav_lsv_code) 
	{
		Customer c = retrieve(customerId);
		c.setAccountName(account_name);
		c.setIndustryType(industry_type);
		c.setStationCode(station_code);
		c.setLavLsvCode(lav_lsv_code);
		
//		Object[] oldValues = changeLogDao.collect(c, fields);
		super.update(c);
		
//		changeLogDao.logCreateOrUpdateEntity(oldValues, c, fields);
	}
}
