package db.ndydb;

import db.Bean;

public class BizcalendarBn extends Bean {
	private String companyid = "";
	private String bizday = "";
	private String description = "";
	private String openflg ;
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public String getBizday() {
		return bizday;
	}
	public void setBizday(String bizday) {
		this.bizday = bizday;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOpenflg() {
		return openflg;
	}
	public void setOpenflg(String openflg) {
		this.openflg = openflg;
	}
}
