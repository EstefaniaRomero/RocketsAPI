package com.example.rocketsapi;



import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RocketService {

    private RocketRepository rocketRepository;
    private PropellerRepository propellerRepository;


    public RocketService (RocketRepository rocketRepository, PropellerRepository propellerRepository){

        this.rocketRepository = rocketRepository;
        this.propellerRepository = propellerRepository;
    }

    public Rocket createRockets(Rocket rockets) {

      return  rocketRepository.save(rockets);
    }

    public List<Rocket> getRockets() {

        List<Rocket>users = new ArrayList<>();
        for(Rocket r : this.rocketRepository.findAll()){
            users.add(r);

        }
        return users;

    }

    public void removeAllRockets() {

        this.rocketRepository.deleteAll();
    }
    Rocket findRocket(Long rocketId) throws Exception {
        return this.rocketRepository.findById(rocketId).get();

    }

    public Rocket updateRocket(Long rocketId, Rocket rocketToUpdate) throws Exception {
        Rocket rocket = findRocket(rocketId);
        rocket.setCode(rocketToUpdate.getCode());
        rocketRepository.save(rocket);
        return rocket;
    }

    public Rocket getOneRocket(Long rocketId) {return rocketRepository.findById(rocketId).get();}

    public void removeRocketById(Long id) {this.rocketRepository.deleteById(id);}


    public Rocket moveRocket(Rocket rocket, Movement movement) {

        for (int i = 0; i < movement.getTimes(); i++) {
            if(movement.getMovementType().equals(Movement.ACCELERATE)){
                rocket.speedUpPropeller();
            }else if(movement.getMovementType().equals(Movement.BRAKE)){
                rocket.bakerPropeller();
            }
        }
        propellerRepository.saveAll(rocket.getPropellerList());
        return rocket;
    }
}
