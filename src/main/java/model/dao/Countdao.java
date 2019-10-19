package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dto.Count;
import model.util.DBUtil;

public class Countdao {
	
	public static boolean updateCountViews(String order) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			
//			String order = "update countviews set" + option +"=" +option+"+1"; 
			pstmt = con.prepareStatement(order);
			
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public static Count getCountViewst() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Count count = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from countviews");
			rset = pstmt.executeQuery();
			if(rset.next()){
				count = new Count(rset.getLong(1), rset.getLong(2), rset.getLong(3), rset.getLong(4)
						, rset.getLong(5), rset.getLong(6), rset.getLong(7), rset.getLong(8));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return count;
	}
}
