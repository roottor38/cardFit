package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.Count;
import model.dto.Summation;
import model.util.DBUtil;

public class Countdao {
   
   public static Summation get40() throws SQLException{
      Summation data = null;
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      try{
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement("select sum(movie), sum(cafe), sum(telecom), sum(transportation), sum(onshop), sum(offshop), sum(food), sum(others) from search where customer like '40%'");
         rset = pstmt.executeQuery();
         if(rset.next()){
            data = new Summation(rset.getLong(1), rset.getLong(2), rset.getLong(3), rset.getLong(4)
                  , rset.getLong(5), rset.getLong(6), rset.getLong(7), rset.getLong(8));
         }
      }finally{
         DBUtil.close(con, pstmt, rset);
      }
      return data;
   }
   
   public static Summation get30() throws SQLException{
      Summation data = null;
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      try{
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement("select sum(movie), sum(cafe), sum(telecom), sum(transportation), sum(onshop), sum(offshop), sum(food), sum(others) from search where customer like '30%'");
         rset = pstmt.executeQuery();
         if(rset.next()){
            data = new Summation(rset.getLong(1), rset.getLong(2), rset.getLong(3), rset.getLong(4)
                  , rset.getLong(5), rset.getLong(6), rset.getLong(7), rset.getLong(8));
         }
      }finally{
         DBUtil.close(con, pstmt, rset);
      }
      return data;
   }
   
   public static Summation get20() throws SQLException{
      Summation data = null;
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      try{
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement("select sum(movie), sum(cafe), sum(telecom), sum(transportation), sum(onshop), sum(offshop), sum(food), sum(others) from search where customer like '20%'");
         rset = pstmt.executeQuery();
         if(rset.next()){
            data = new Summation(rset.getLong(1), rset.getLong(2), rset.getLong(3), rset.getLong(4)
                  , rset.getLong(5), rset.getLong(6), rset.getLong(7), rset.getLong(8));
         }
      }finally{
         DBUtil.close(con, pstmt, rset);
      }
      return data;
   }
   
   public static Summation getWomenSummation() throws SQLException{
      Summation data = null;
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      try{
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement("select sum(movie), sum(cafe), sum(telecom), sum(transportation), sum(onshop), sum(offshop), sum(food), sum(others) from search where customer like '%F'");
         rset = pstmt.executeQuery();
         if(rset.next()){
            data = new Summation(rset.getLong(1), rset.getLong(2), rset.getLong(3), rset.getLong(4)
                  , rset.getLong(5), rset.getLong(6), rset.getLong(7), rset.getLong(8));
         }
      }finally{
         DBUtil.close(con, pstmt, rset);
      }
      return data;
   }
   
   public static Summation getMenSummation() throws SQLException{
      Summation data = null;
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      try{
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement("select sum(movie), sum(cafe), sum(telecom), sum(transportation), sum(onshop), sum(offshop), sum(food), sum(others) from search where customer like '%M'");
         rset = pstmt.executeQuery();
         if(rset.next()){
            data = new Summation(rset.getLong(1), rset.getLong(2), rset.getLong(3), rset.getLong(4)
                  , rset.getLong(5), rset.getLong(6), rset.getLong(7), rset.getLong(8));
         }
      }finally{
         DBUtil.close(con, pstmt, rset);
      }
      return data;
   }
   
   public static Summation getSummation() throws SQLException{
      Summation data = null;
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      try{
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement("select sum(movie), sum(cafe), sum(telecom), sum(transportation), sum(onshop), sum(offshop), sum(food), sum(others) from search");
         rset = pstmt.executeQuery();
         if(rset.next()){
            data = new Summation(rset.getLong(1), rset.getLong(2), rset.getLong(3), rset.getLong(4)
                  , rset.getLong(5), rset.getLong(6), rset.getLong(7), rset.getLong(8));
         }
      }finally{
         DBUtil.close(con, pstmt, rset);
      }
      
      return data;
   }
   
   public static ArrayList<Count> getAllCount() throws SQLException{
      ArrayList<Count> data = new ArrayList<>();
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      try{
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement("select * from search");
         rset = pstmt.executeQuery();
         while(rset.next()){
            data.add(new Count(rset.getString(1), rset.getLong(2), rset.getLong(3), rset.getLong(4)
                  , rset.getLong(5), rset.getLong(6), rset.getLong(7), rset.getLong(8), rset.getLong(9)));
         }
      }finally{
         DBUtil.close(con, pstmt, rset);
      }
      return data;
   }
   
   public static boolean updateSearch(String order) throws SQLException{
      Connection con = null;
      PreparedStatement pstmt = null;
      try{
         con = DBUtil.getConnection();
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
   
   public static Count getSearchByName(String name) throws SQLException{
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      Count count = null;
      
      try{
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement("select * from search where customer = " + "\"name\"");
         rset = pstmt.executeQuery();
         if(rset.next()){
            count = new Count(rset.getString(1), rset.getLong(2), rset.getLong(3), rset.getLong(4)
                  , rset.getLong(5), rset.getLong(6), rset.getLong(7), rset.getLong(8), rset.getLong(9));
         }
      }finally{
         DBUtil.close(con, pstmt, rset);
      }
      return count;
   }
}