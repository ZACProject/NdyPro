package db.ndydb;

import db.Bean;

public class RoomsBn extends Bean {
	private String roomid = "";
	private String companyid = "";
	private String categorynamejp = "";
	private String categorynamecn = "";
	private String categorynameen = "";
	private String categorysubnamejp = "";
	private String categorysubnamecn = "";
	private String categorysubnameen = "";
	private String descripjp = "";
	private String descripcn = "";
	private String descripen = "";
	private String supplementjp = "";
	private String supplementcn = "";
	private String supplementen = "";
	private String pricejp;
	private String pricecn;
	private String priceen;
	private String runitnamejp = "";
	private String runitnamecn = "";
	private String runitnameen = "";
	private String capactiy;
	private String capactiy2;
	private String punitnamejp;
	private String punitnamecn;
	private String punitnameen;
	private String  linkname1= "";
	private String  linkurl1= "";
	private String  linkname2= "";
	private String  linkurl2= "";
	private String  linkname3= "";
	private String  linkurl3= "";
	private String dispnum;
	private String regdate = "";
	private String duedate = "";

	public String getRoomid() {
		return roomid;
	}
	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public String getCategorynamejp() {
		return categorynamejp;
	}
	public void setCategorynamejp(String categorynamejp) {
		this.categorynamejp = categorynamejp;
	}
	public String getCategorynamecn() {
		return categorynamecn;
	}
	public void setCategorynamecn(String categorynamecn) {
		this.categorynamecn = categorynamecn;
	}
	public String getCategorynameen() {
		return categorynameen;
	}
	public void setCategorynameen(String categorynameen) {
		this.categorynameen = categorynameen;
	}
	public String getCategorysubnamejp() {
		return categorysubnamejp;
	}
	public void setCategorysubnamejp(String categorysubnamejp) {
		this.categorysubnamejp = categorysubnamejp;
	}
	public String getCategorysubnamecn() {
		return categorysubnamecn;
	}
	public void setCategorysubnamecn(String categorysubnamecn) {
		this.categorysubnamecn = categorysubnamecn;
	}
	public String getCategorysubnameen() {
		return categorysubnameen;
	}
	public void setCategorysubnameen(String categorysubnameen) {
		this.categorysubnameen = categorysubnameen;
	}
	public String getDescripjp() {
		return descripjp;
	}
	public void setDescripjp(String descripjp) {
		this.descripjp = descripjp;
	}
	public String getDescripcn() {
		return descripcn;
	}
	public void setDescripcn(String descripcn) {
		this.descripcn = descripcn;
	}
	public String getDescripen() {
		return descripen;
	}
	public void setDescripen(String descripen) {
		this.descripen = descripen;
	}
	public String getSupplementjp() {
		return supplementjp;
	}
	public void setSupplementjp(String supplementjp) {
		this.supplementjp = supplementjp;
	}
	public String getSupplementcn() {
		return supplementcn;
	}
	public void setSupplementcn(String supplementcn) {
		this.supplementcn = supplementcn;
	}
	public String getSupplementen() {
		return supplementen;
	}
	public void setSupplementen(String supplementen) {
		this.supplementen = supplementen;
	}
	public String getPricejp() {
		return pricejp;
	}
	public void setPricejp(String pricejp) {
		this.pricejp = pricejp;
	}
	public String getPricecn() {
		return pricecn;
	}
	public void setPricecn(String pricecn) {
		this.pricecn = pricecn;
	}
	public String getPriceen() {
		return priceen;
	}
	public void setPriceen(String priceen) {
		this.priceen = priceen;
	}
	public String getRunitnamejp() {
		return runitnamejp;
	}
	public void setRunitnamejp(String runitnamejp) {
		this.runitnamejp = runitnamejp;
	}
	public String getRunitnamecn() {
		return runitnamecn;
	}
	public void setRunitnamecn(String runitnamecn) {
		this.runitnamecn = runitnamecn;
	}
	public String getRunitnameen() {
		return runitnameen;
	}
	public void setRunitnameen(String runitnameen) {
		this.runitnameen = runitnameen;
	}
	public String getCapactiy() {
		return capactiy;
	}
	public void setCapactiy(String capactiy) {
		this.capactiy = capactiy;
	}
	public String getCapactiy2() {
		return capactiy2;
	}
	public void setCapactiy2(String capactiy2) {
		this.capactiy2 = capactiy2;
	}
	public String getPunitnamejp() {
		return punitnamejp;
	}
	public void setPunitnamejp(String punitnamejp) {
		this.punitnamejp = punitnamejp;
	}
	public String getPunitnamecn() {
		return punitnamecn;
	}
	public void setPunitnamecn(String punitnamecn) {
		this.punitnamecn = punitnamecn;
	}
	public String getPunitnameen() {
		return punitnameen;
	}
	public void setPunitnameen(String punitnameen) {
		this.punitnameen = punitnameen;
	}
	public String getLinkname1() {
		return linkname1;
	}
	public void setLinkname1(String linkname1) {
		this.linkname1 = linkname1;
	}
	public String getLinkurl1() {
		return linkurl1;
	}
	public void setLinkurl1(String linkurl1) {
		this.linkurl1 = linkurl1;
	}
	public String getLinkname2() {
		return linkname2;
	}
	public void setLinkname2(String linkname2) {
		this.linkname2 = linkname2;
	}
	public String getLinkurl2() {
		return linkurl2;
	}
	public void setLinkurl2(String linkurl2) {
		this.linkurl2 = linkurl2;
	}
	public String getLinkname3() {
		return linkname3;
	}
	public void setLinkname3(String linkname3) {
		this.linkname3 = linkname3;
	}
	public String getLinkurl3() {
		return linkurl3;
	}
	public void setLinkurl3(String linkurl3) {
		this.linkurl3 = linkurl3;
	}

	public String getDispnum() {
		return dispnum;
	}
	public void setDispnum(String dispnum) {
		this.dispnum = dispnum;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getDuedate() {
		return duedate;
	}
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}



}
