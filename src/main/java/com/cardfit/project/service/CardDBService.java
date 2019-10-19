package com.cardfit.project.service;

import java.sql.SQLException;

import model.dao.Countdao;
import model.dto.Count;

public class CardDBService {
	
	private static CardDBService instance;
	
	private CardDBService() {}
	
	public static CardDBService getInstance() {
		return instance;
	}
	
	public Count getCount() throws SQLException {
		return Countdao.getCountViewst();
		
	}
	
	public boolean updateCount(String[] keyword) {
		String order = ""
		return updateCountViews();
	}

}
