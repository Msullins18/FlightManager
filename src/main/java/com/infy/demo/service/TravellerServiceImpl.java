package com.infy.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.infy.demo.dao.TravellerDAO;
import com.infy.demo.model.Traveller;
@Transactional
public class TravellerServiceImpl implements TravellerService {

	@Autowired
	TravellerDAO travellerDAO;
	
	@Override
	public Traveller loginTraveller(Traveller traveller) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean registerTraveller(Traveller traveller) {
		// TODO Auto-generated method stub
		return null;
	}

}
