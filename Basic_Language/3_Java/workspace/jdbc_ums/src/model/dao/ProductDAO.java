package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DBConnection;
import model.dto.ProductDTO;

public class ProductDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	public ProductDAO() {
		conn = DBConnection.getConnection();
	}
	public boolean insertProduct(ProductDTO product) {
		String sql = "insert into product (prodname, prodprice, prodamount, prodinfo, userid) "
				+ "values(?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, product.getProdname());
			ps.setInt(2, product.getProdprice());
			ps.setInt(3, product.getProdamount());
			ps.setString(4, product.getProdinfo());
			ps.setString(5, product.getUserid());
			
			int result = ps.executeUpdate();
			
			return result == 1;
		} catch (SQLException e) {
		}
		return false;
	}
	public ArrayList<ProductDTO> getList(String userid, int choice, int asc) {
		//choice : 1(등록 순) 2(이름 순) 3(가격 순) 4(좋아요 순)
		//asc : 1(오름차순) 2(내림차순)
		//ex) choice : 2 / asc : 2
		//select * from product where userid = 'apple' order by prodname desc
		String[] cols = {"","prodnum","prodname","prodprice","likecnt"};
		String sql = "select * from product where userid = ? order by "+cols[choice]
				+" "+(asc==1?"asc":"desc");
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			
			rs = ps.executeQuery();
			
			ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
			
			while(rs.next()) {
				ProductDTO product = new ProductDTO(
						rs.getInt("prodnum"),
						rs.getString("prodname"),
						rs.getInt("prodprice"),
						rs.getInt("prodamount"),
						rs.getString("prodinfo"),
						rs.getInt("likecnt"),
						rs.getString("userid")
				);
				
				list.add(product);
			}
			
			return list;
		} catch (SQLException e) {
		}
		return null;
	}
	public boolean deleteByProductNum(int prodnum) {
		String sql = "delete from product where prodnum = "+prodnum;
		
		try {
			ps = conn.prepareStatement(sql);
			
			int result = ps.executeUpdate();
			
			return result == 1;
		} catch (SQLException e) {
		}
		return false;
	}
	public boolean updateProduct(int prodnum, int choice, String newData) {
		//1(가격 수정) 2(수량 수정) 3(설명 수정)
		String[] cols = {"","prodprice","prodamount","prodinfo"};
		String sql = "update product set "+cols[choice]+" = ? where prodnum = "+prodnum;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newData);
			
			int result = ps.executeUpdate();
			return result == 1;
		} catch (SQLException e) {
		}
		return false;
	}
	public ArrayList<ProductDTO> findProducts(String keyword) {
		//keyword : "10000"
		//select * from product where prodname like('%10000%')
		//or prodinfo like('%10000%') or prodprice between 10000*0.9 and 10000*1.1;
		String sql = "select * from product where prodname like('%"+keyword+"%') or "
				+"prodinfo like('%"+keyword+"%')";
//		try {
//			Double.parseDouble(keyword);
//			//숫자로 이루어져 있다는 뜻
//		} catch (NumberFormatException e) {
//			//숫자가 아닌것이 섞여있다는 뜻
//		}
		if(isDigit(keyword)) {
			sql += " or prodprice between 0.9*"+keyword+" and 1.1*"+keyword;
		}
		
		ArrayList<ProductDTO> result = new ArrayList<ProductDTO>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductDTO product = new ProductDTO(
						rs.getInt("prodnum"),
						rs.getString("prodname"),
						rs.getInt("prodprice"),
						rs.getInt("prodamount"),
						rs.getString("prodinfo"),
						rs.getInt("likecnt"),
						rs.getString("userid")
				);
				
				result.add(product);
			}
		} catch (SQLException e) {
		}
		return result;
	}
	private boolean isDigit(String msg) {
		for (int i = 0; i < msg.length(); i++) {
			char ch = msg.charAt(i);
			if(ch<'0' || ch>'9') {
				return false;
			}
		}
		return true;
	}
	public ProductDTO findProductByProdnum(int prodnum) {
		String sql = "select * from product where prodnum = "+prodnum;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				ProductDTO product = new ProductDTO(
						rs.getInt("prodnum"),
						rs.getString("prodname"),
						rs.getInt("prodprice"),
						rs.getInt("prodamount"),
						rs.getString("prodinfo"),
						rs.getInt("likecnt"),
						rs.getString("userid")
				);
				return product;
			}
		} catch (SQLException e) {
		}
		return null;
	}
	public void updateLikeCnt(int prodnum) {
		String sql = "update product set likecnt = likecnt+1 where prodnum = "+prodnum;
		try {
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
		}
	}
}











