package com.example.rocketsapi;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional

public class PropellerService {

    private RocketRepository rocketRepository;
    private PropellerRepository propellerRepository;


    public PropellerService (RocketRepository rocketRepository, PropellerRepository propellerRepository){

        this.rocketRepository = rocketRepository;
        this.propellerRepository = propellerRepository;
    }
    private Rocket findRocket(Long rocketId) throws Exception {
        return this.rocketRepository.findById(rocketId).get();}

    public Rocket assignPropellersToRocket(Long rocketId, Propeller propeller) throws Exception {

        Rocket rocket = findRocket(rocketId);
        propeller.setRocket(rocket);
        propellerRepository.save(propeller);
        return rocket;
    }

    public List<Propeller> getPropellerOfRockets(Long rocketId) throws Exception {

        Rocket rocket = findRocket(rocketId);
        return rocket.getPropellerList();
    }

    public void removeAllPropellers(Long rocketId) throws Exception {

        propellerRepository.deleteAllByRocketId(rocketId);
    }

    public Propeller updateMaxpowerOfPropeller(Propeller propeller, Long propellerId) {

        Propeller propellers = findPropeller(propellerId);
        propellers.setMaxPower(propeller.getMaxPower());
        propellerRepository.save(propellers);
        return propellers;

    }

    private Propeller findPropeller(Long propellerId) {

        return this.propellerRepository.findById(propellerId).get();
    }

    public Propeller getPropeller(Long propellerId) {return propellerRepository.findById(propellerId).get();}

    public Propeller deletePropeller(Long propellerId) {

        Propeller propellers = findPropeller(propellerId);
        propellerRepository.deleteById(propellerId);
        return propellers;
    }
}
