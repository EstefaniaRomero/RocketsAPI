package com.example.rocketsapi;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropellerRepository extends CrudRepository<Propeller,Long> {

     void deleteAllByRocketId(Long rocketId);
}