package com.infy.demo.dao;
import java.util.Optional;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.demo.dao.TravellerDAO;
import com.infy.demo.entity.TravellerEntity;
import com.infy.demo.model.Traveller;

@Repository(value = "travellerDAO" )
public class TravellerDAOImpl implements TravellerDAO {
	
	@Autowired
	EntityManager entityManager;
	
	@Override
	public Optional<String> getPasswordOfTraveller(String emailId) {
		Optional<String> password = Optional.empty();
		Optional<TravellerEntity> travellerEntity = Optional.of(entityManager.find(TravellerEntity.class, emailId));
		if (travellerEntity.isPresent()){
			password = Optional.of(travellerEntity.get().getPassword());
		}
		
		return password;
	}

	@Override
	public Traveller getTravellerByEmailId(String emailId) {
		// TODO Auto-generated method stub
		TravellerEntity TravellerEntity = entityManager.find(TravellerEntity.class, emailId);
		Traveller traveller = new Traveller();
		
		traveller.setEmailId(TravellerEntity.getEmailId());
		traveller.setFirstName(TravellerEntity.getFirstName());
		traveller.setLastName(TravellerEntity.getLastName());
		traveller.setPassword(TravellerEntity.getPassword());
		traveller.setPhoneNumber(TravellerEntity.getPhoneNumber());
		
		return traveller;
	}

	@Override
	public String registerTraveller(Traveller traveller) {
		// TODO Auto-generated method stub
		TravellerEntity travellerEntity = new TravellerEntity();
		
		travellerEntity.setEmailId(traveller.getEmailId());
		travellerEntity.setFirstName(traveller.getFirstName());
		travellerEntity.setLastName(traveller.getLastName());
		travellerEntity.setPassword(traveller.getPassword());
		travellerEntity.setPhoneNumber(traveller.getPhoneNumber());
		
		entityManager.persist(travellerEntity);
		
		return travellerEntity.getEmailId();
	}

	@Override
	public Boolean checkAvailabilityOfEmailId(String emailId) {
		// TODO Auto-generated method stub
		Boolean available = false;
		TravellerEntity TravellerEntity = entityManager.find(TravellerEntity.class, emailId);

		if(TravellerEntity == null)
		{
			available = true;
		}
		
		return available;
	}
}
