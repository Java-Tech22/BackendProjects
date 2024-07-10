package com.binarylogicit.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.binarylogicit.address.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}

