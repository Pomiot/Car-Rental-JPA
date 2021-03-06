package com.capgemini.jstk.carrentaljpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.jstk.carrentaljpa.dao.CarDao;
import com.capgemini.jstk.carrentaljpa.dao.CustomerDao;
import com.capgemini.jstk.carrentaljpa.dao.LocationDao;
import com.capgemini.jstk.carrentaljpa.dao.RentalDao;
import com.capgemini.jstk.carrentaljpa.domain.CarEntity;
import com.capgemini.jstk.carrentaljpa.domain.CustomerEntity;
import com.capgemini.jstk.carrentaljpa.domain.LocationEntity;
import com.capgemini.jstk.carrentaljpa.domain.RentalEntity;
import com.capgemini.jstk.carrentaljpa.mappers.RentalMapper;
import com.capgemini.jstk.carrentaljpa.service.RentalService;
import com.capgemini.jstk.carrentaljpa.types.RentalTO;

@Service
@Transactional(readOnly = true)
public class RentalServiceImpl implements RentalService{
	@Autowired
	private RentalDao rentalRepository;
	
	@Autowired 
	private CarDao carRepository;
	
	@Autowired
	private CustomerDao customerRepository;
	
	@Autowired
	private LocationDao locationRepository;
	
	@Transactional(readOnly = false)
	@Override
	public RentalTO saveRental(RentalTO rental) {
		RentalEntity rentalEntity = RentalMapper.toRentalEntity(rental);
		CarEntity car = carRepository.getOne(rental.getCar().getId());
		rentalEntity.setCar(car);
		car.getRentals().add(rentalEntity);
		CustomerEntity customer = customerRepository.getOne(rental.getCustomer().getId());
		rentalEntity.setCustomer(customer);
		customer.getRentals().add(rentalEntity);
		LocationEntity startLocation = locationRepository.getOne(rental.getStartLocation().getId());
		LocationEntity endLocation = locationRepository.getOne(rental.getEndLocation().getId());
		rentalEntity.setStartLocation(startLocation);
		rentalEntity.setEndLocation(endLocation);
		startLocation.getStartRentals().add(rentalEntity);
		endLocation.getEndRentals().add(rentalEntity);
		rentalRepository.save(rentalEntity);
		return RentalMapper.toRentalTO(rentalEntity);
	}
	
	@Override
	public RentalTO findRentalById(Long id) {
		if(rentalRepository.exists(id)){
			return RentalMapper.toRentalTO(rentalRepository.getOne(id));
		}
		return null;
	}
	
	@Override
	public RentalEntity findRentalEntityById(Long id) {
		if(rentalRepository.exists(id)){
			return rentalRepository.getOne(id);
		}
		return null;
	}
	
	@Override
	public boolean contains(Long id){
		return rentalRepository.exists(id);
	}
}
