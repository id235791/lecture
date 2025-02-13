package model.dto;

public class ProductDTO {
	private int prodnum;
	private String prodname;
	private int prodprice;
	private int prodamount;
	private String prodinfo;
	private int likecnt;
	private String userid;
	
	public ProductDTO() {}

	public ProductDTO(int prodnum, String prodname, int prodprice,
			int prodamount, String prodinfo, int likecnt, String userid) {
		this.prodnum = prodnum;
		this.prodname = prodname;
		this.prodprice = prodprice;
		this.prodamount = prodamount;
		this.prodinfo = prodinfo;
		this.likecnt = likecnt;
		this.userid = userid;
	}
	
	public ProductDTO(String[] datas) {
		this.prodnum = Integer.parseInt(datas[0]);
		this.prodname = datas[1];
		this.prodprice = Integer.parseInt(datas[2]);
		this.prodamount = Integer.parseInt(datas[3]);
		this.prodinfo = datas[4];
		this.likecnt = Integer.parseInt(datas[5]);
		this.userid = datas[6];
	}

	public int getProdnum() {
		return prodnum;
	}

	public void setProdnum(int prodnum) {
		this.prodnum = prodnum;
	}

	public String getProdname() {
		return prodname;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}

	public int getProdprice() {
		return prodprice;
	}

	public void setProdprice(int prodprice) {
		this.prodprice = prodprice;
	}

	public int getProdamount() {
		return prodamount;
	}

	public void setProdamount(int prodamount) {
		this.prodamount = prodamount;
	}

	public String getProdinfo() {
		return prodinfo;
	}

	public void setProdinfo(String prodinfo) {
		this.prodinfo = prodinfo;
	}

	public int getLikecnt() {
		return likecnt;
	}

	public void setLikecnt(int likecnt) {
		this.likecnt = likecnt;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}





