package com.boot.features.db2Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.features.entitiesS.Address;

public interface AddressRepo extends JpaRepository<Address, Long>{

}
