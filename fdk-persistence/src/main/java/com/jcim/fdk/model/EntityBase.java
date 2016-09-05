package com.jcim.fdk.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.StandardToStringStyle;

/**
 * Base class for all entities.
 * 
 * @author o.prinz@binaere-bauten.de
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class EntityBase implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Primary key for every entity.
	 * 
	 * Changelog: - 2013-07-02,o.prinz@binaere-bauten.de: Change type of id to
	 * long @see #LCAG-123
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="standard_sequence")
	@SequenceGenerator(name="standard_sequence", sequenceName="STANDARD_SEQ")
	private long id;
	
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	/**
	 * Creation date of entity.
	 */
	private Date creationDate;
	
	public Date getCreationDate()
	{
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}
	
	/**
	 * Last change of entity.
	 */
	private Date lastUpdated;
	
	public Date getLastUpdated()
	{
		return lastUpdated;
	}
	
	public void setLastUpdated(Date lastUpdated)
	{
		this.lastUpdated = lastUpdated;
	}
	
	public String toString()
	{
		StandardToStringStyle style = new StandardToStringStyle();
		style.setDefaultFullDetail(false); // needed for large arrays as in
											// Attachment
		return ReflectionToStringBuilder.toString(this);
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((lastUpdated == null) ? 0 : lastUpdated.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityBase other = (EntityBase) obj;
		if (creationDate == null)
		{
			if (other.creationDate != null)
				return false;
		}
		else if (!creationDate.equals(other.creationDate))
			return false;
		if (id != other.id)
			return false;
		if (lastUpdated == null)
		{
			if (other.lastUpdated != null)
				return false;
		}
		else if (!lastUpdated.equals(other.lastUpdated))
			return false;
		return true;
	}
}
