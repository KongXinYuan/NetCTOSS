package org.tarena.netctoss.pojo;

/**
 * AgeSegment entity. @author MyEclipse Persistence Tools
 */

public class AgeSegment implements java.io.Serializable {

	// Fields

	private Boolean id;
	private String name;
	private Byte lowage;
	private Byte hiage;

	// Constructors

	/** default constructor */
	public AgeSegment() {
	}

	/** minimal constructor */
	public AgeSegment(String name) {
		this.name = name;
	}

	/** full constructor */
	public AgeSegment(String name, Byte lowage, Byte hiage) {
		this.name = name;
		this.lowage = lowage;
		this.hiage = hiage;
	}

	// Property accessors

	public Boolean getId() {
		return this.id;
	}

	public void setId(Boolean id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getLowage() {
		return this.lowage;
	}

	public void setLowage(Byte lowage) {
		this.lowage = lowage;
	}

	public Byte getHiage() {
		return this.hiage;
	}

	public void setHiage(Byte hiage) {
		this.hiage = hiage;
	}

}