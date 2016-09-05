package com.jcim.fdk.dao;

import com.jcim.fdk.model.Customer;

/**
 * 
 * The @CustomerDao offers methods to persist the Customer Model in the database
 * 
 * @author o.prinz@binaere-bauten.de
 *
 */
public interface CustomerDao extends AbstractDao<Customer> 
{
	/**
	 * Create and persists a new customer.
	 * 
	 * @param account_id
	 * @param account_name
	 * @param owner_id
	 * @param owner_name
	 * @param account_team
	 * @param saleslevel_6
	 * @param cdb_number
	 * @param station_code
	 * @param bookin_short_time
	 * @param url
	 * @param sap_code
	 * @param cass_code
	 * @param lata_code
	 * @param lav_lsv_code
	 * @param industry_type
	 * @param account_role
	 * @param company_type
	 * 
	 * @return
	 * 		Id of customer object in database.
	 */
	public long create(
			String account_id, String account_name, String owner_id, String owner_name, String account_team, String saleslevel_6,
			String cdb_number, String station_code, String booking_short_time, String url, String sap_code, String cass_code,
			String lata_code, String lav_lsv_code, String industry_type, String account_role, String company_type, 
			String parent_account_cdb, String parent_account_name, String preferred_cargo_airlines, boolean credit_worthiness, 
			String status, String inactive_reason);
	
	/**
	 * Create method called by the ui.
	 * 
	 * @param account_name
	 * @param industry_type
	 * @param station_code
	 * @param lav_lsv_code
	 */
	public long uiCreate(String account_name, String industry_type, String station_code, String lav_lsv_code);
	
	/**
	 * 
	 * This method updates the given customer in the database.
	 * 
	 * @param customer The customer that should be updated.
	 */
	public void update(long customerId, String account_id, String account_name, String owner_id, String owner_name, String account_team, String saleslevel_6,
			String cdb_number, String station_code, String booking_short_time, String url, String sap_code, String cass_code,
			String lata_code, String lav_lsv_code, String industry_type, String account_role, String company_type,
			String parent_account_cdb, String parent_account_name, String preferred_cargo_airlines, boolean credit_worthiness, 
			String status, String inactive_reason);

	/**
	 * Update method called by the ui.
	 * 
	 * @param customerId
	 * @param account_name
	 * @param industry_type
	 * @param station_code
	 * @param lav_lsv_code
	 */
	public void uiUpdate(long customerId, String account_name, String industry_type, String station_code, String lav_lsv_code);
	
	/**
	 * 
	 * This method retrieves a customer from the database with the given id.
	 * 
	 * @param id The id from the customer that should be loaded.
	 * @return The loaded customer.
	 */
	Customer retrieve(long id);
	
	/**
	 * TODO: TEST!
	 * Finds a customer object by the unique account_id.
	 * 
	 * @param account_id
	 * 
	 * @return The Customer Object associated with the account_id
	 */
	public Customer findByCdbNumber(String cdb_number);
	
}
