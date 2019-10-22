package com.cardfit.project.service;

import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.Countdao;
import model.dto.Count;
import model.dto.Summation;

public class CardDBService {
	
	public static ArrayList<Count> getAllCount() throws SQLException{
		return Countdao.getAllCount();
	}
	
	public static Count getCount(String name) throws SQLException {
		return Countdao.getSearchByName(name);
	}
	
	public static Summation getSummation() throws SQLException {
		return Countdao.getSummation();
	}
	
	public static boolean updateCount(String[] keyword,String name) throws SQLException {
		String order = "update search set ";
		int length = keyword.length-1;
		for(int i = 0 ; i < length; i++) {
			order = order + keyword[i] +" = " + keyword[i]+"+ 1, ";
		}
		order = order + keyword[length] +" = " + keyword[length]+ "+ 1 " + "where customer = "+"'" + name+"'";
		System.out.println(order);
		return Countdao.updateSearch(order);
	}
}
