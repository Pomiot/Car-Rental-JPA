package com.capgemini.jstk.carrentaljpa.service;

import java.util.List;

import com.capgemini.jstk.carrentaljpa.domain.LocationEntity;
import com.capgemini.jstk.carrentaljpa.types.EmployeeTO;
import com.capgemini.jstk.carrentaljpa.types.LocationTO;

public interface LocationService {
	public LocationTO saveLocation(LocationTO location);
	
	public LocationTO findLocationById(Long id);
	
	public LocationTO deleteLocation(Long id);
	
	public boolean contains(Long id);
	
	public LocationTO updateLocation(LocationTO updatedLocation);
	
	public LocationEntity findLocationEntityById(Long id);
	
	public boolean addEmployeeToLocation(Long locationId, Long employeeId);
	
	public boolean deleteEmployeeFromLocation(Long locationId, Long employeeId);
	
	public List<EmployeeTO> findEmployeesByLocationId(Long locationId);
	
	public List<EmployeeTO> findEmployeesByLocationWhoCaresCar(Long locationId, Long carId);
}
