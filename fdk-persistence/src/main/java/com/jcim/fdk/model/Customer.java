package com.jcim.fdk.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


/**
 * Customer entity.
 * 
 * CHANGELOG:
 * 		- 2013-07-18: #LCAG-170
 * 
 * @author o.prinz@binaere-bauten.de
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Customer")
public class Customer extends EntityBase 
{	
	
//	/**
//	 * Sex of a contact.
//	 * 
//	 * @author o.prinz@binaere-bauten.de
//	 */
//	public static enum SEX
//	{
//		MALE, FEMALE;
//	}
//	
//	/**
//	 * Id of customer entity this contact person belongs to.
//	 */
//	private long customerId;
//	
//	/**
//	 * Contact person first name.
//	 */
//	private String firstName;
//	
//	/**
//	 * Contact person last name.
//	 */
//	private String lastName;
//	
//	/**
//	 * Position the contact person holds in the corresponding customer company.
//	 */
//	private String jobTitle;
//	
//	private String status;
//	private String contactCdb;
//	
//	@Enumerated(EnumType.ORDINAL)
//	private SEX sex;
	
	private String accountId;
	private String accountName;
	private String accountTeam;
	private String accountRole;
	private String ownerId;
	private String ownerName;
	private String saleslevel6;
	private String cdbNumber;
	private String stationCode;
	private String bookingShortName;
	private String url;
	private String sapCode;
	private String cassCode;
	private String lataCode;
	private String lavLsvCode;
	private String industryType;
	private String companyType;
	private String parentAccountCdb;
	private String parentAccountName;
	private String preferredCargoAirlines;
	private boolean creditWorthiness;
	private String status;
	private String inactiveReason;
	
	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}
	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	/**
	 * @return the accountTeam
	 */
	public String getAccountTeam() {
		return accountTeam;
	}
	/**
	 * @param accountTeam the accountTeam to set
	 */
	public void setAccountTeam(String accountTeam) {
		this.accountTeam = accountTeam;
	}
	/**
	 * @return the accountRole
	 */
	public String getAccountRole() {
		return accountRole;
	}
	/**
	 * @param accountRole the accountRole to set
	 */
	public void setAccountRole(String accountRole) {
		this.accountRole = accountRole;
	}
	/**
	 * @return the ownerId
	 */
	public String getOwnerId() {
		return ownerId;
	}
	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	/**
	 * @return the ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}
	/**
	 * @param ownerName the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	/**
	 * @return the saleslevel6
	 */
	public String getSaleslevel6() {
		return saleslevel6;
	}
	/**
	 * @param saleslevel6 the saleslevel6 to set
	 */
	public void setSaleslevel6(String saleslevel6) {
		this.saleslevel6 = saleslevel6;
	}
	/**
	 * @return the cdbNumber
	 */
	public String getCdbNumber() {
		return cdbNumber;
	}
	/**
	 * @param cdbNumber the cdbNumber to set
	 */
	public void setCdbNumber(String cdbNumber) {
		this.cdbNumber = cdbNumber;
	}
	/**
	 * @return the stationCode
	 */
	public String getStationCode() {
		return stationCode;
	}
	/**
	 * @param stationCode the stationCode to set
	 */
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	/**
	 * @return the bookingShortName
	 */
	public String getBookingShortName() {
		return bookingShortName;
	}
	/**
	 * @param bookingShortName the bookingShortName to set
	 */
	public void setBookingShortName(String bookingShortName) {
		this.bookingShortName = bookingShortName;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the sapCode
	 */
	public String getSapCode() {
		return sapCode;
	}
	/**
	 * @param sapCode the sapCode to set
	 */
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	/**
	 * @return the cassCode
	 */
	public String getCassCode() {
		return cassCode;
	}
	/**
	 * @param cassCode the cassCode to set
	 */
	public void setCassCode(String cassCode) {
		this.cassCode = cassCode;
	}
	/**
	 * @return the lataCode
	 */
	public String getLataCode() {
		return lataCode;
	}
	/**
	 * @param lataCode the lataCode to set
	 */
	public void setLataCode(String lataCode) {
		this.lataCode = lataCode;
	}
	/**
	 * @return the lavLsvCode
	 */
	public String getLavLsvCode() {
		return lavLsvCode;
	}
	/**
	 * @param lavLsvCode the lavLsvCode to set
	 */
	public void setLavLsvCode(String lavLsvCode) {
		this.lavLsvCode = lavLsvCode;
	}
	/**
	 * @return the industryType
	 */
	public String getIndustryType() {
		return industryType;
	}
	/**
	 * @param industryType the industryType to set
	 */
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	/**
	 * @return the companyType
	 */
	public String getCompanyType() {
		return companyType;
	}
	/**
	 * @param companyType the companyType to set
	 */
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getParentAccountCdb() {
		return parentAccountCdb;
	}
	public void setParentAccountCdb(String parentAccountCdb) {
		this.parentAccountCdb = parentAccountCdb;
	}
	public String getParentAccountName() {
		return parentAccountName;
	}
	public void setParentAccountName(String parentAccountName) {
		this.parentAccountName = parentAccountName;
	}
	public String getPreferredCargoAirlines() {
		return preferredCargoAirlines;
	}
	public void setPreferredCargoAirlines(String preferredCargoAirlines) {
		this.preferredCargoAirlines = preferredCargoAirlines;
	}
	public boolean isCreditWorthiness() {
		return creditWorthiness;
	}
	public void setCreditWorthiness(boolean creditWorthiness) {
		this.creditWorthiness = creditWorthiness;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInactiveReason() {
		return inactiveReason;
	}
	public void setInactiveReason(String inactiveReason) {
		this.inactiveReason = inactiveReason;
	}
}
