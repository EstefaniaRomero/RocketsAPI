package com.example.rocketsapi;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RocketRestController {

    private RocketService rocketService;
    private PropellerService propellerService;

    public RocketRestController(RocketService rocketService, PropellerService propellerService) {
        this.rocketService = rocketService;
        this.propellerService = propellerService;
    }

    @PostMapping("/rockets")
    public Rocket createRocker(@RequestBody Rocket rockets) {
        return rocketService.createRockets(rockets);
    }

    @GetMapping("/rockets")
    public List<Rocket> getRockets() throws Exception {

        return rocketService.getRockets();

    }

    @DeleteMapping("/rockets")
    public void removeAllRockets() throws Exception {

        rocketService.removeAllRockets();
    }

    @PutMapping("/rockets/{rocketId}")
    public void updateRocket(@RequestBody Rocket rocketToUpdate, @PathVariable Long rocketId) throws Exception {

        rocketService.updateRocket(rocketId,rocketToUpdate);}

    @GetMapping("/rockets/{rocketId}")
    public Rocket getOneRocket(@PathVariable Long rocketId) throws Exception {

        return rocketService.getOneRocket(rocketId);
    }

    @DeleteMapping("/rockets/{rocketId}")
    public void removeRocket(@PathVariable Long rocketId){rocketService.removeRocketById(rocketId);}

    @PostMapping("/rockets/{rocketId}/propellers")
    public Rocket assignPropellersToRocket(@PathVariable Long rocketId, @RequestBody Propeller propeller) throws Exception {

        return propellerService.assignPropellersToRocket(rocketId, propeller);
    }

    @GetMapping("/rockets/{rocketId}/propellers")
    public List<Propeller> getPropellerOfRockets(@PathVariable Long rocketId) throws Exception {

        return propellerService.getPropellerOfRockets(rocketId);
    }

    @DeleteMapping("/rockets/{rocketId}/propellers")
    public void removeAllPropellers(@PathVariable Long rocketId) throws Exception {

        propellerService.removeAllPropellers(rocketId);
    }

    @PutMapping("/rockets/{rocketId}/propellers/{propellerId}")
    public void updateMaxpowerOfPropeller(@RequestBody Propeller propeller, @PathVariable Long propellerId) throws Exception {

        propellerService.updateMaxpowerOfPropeller(propeller,propellerId );
    }

    @GetMapping("/rockets/{rocketId}/propellers/{propellerId}")
    public Propeller getPropeller(@PathVariable Long propellerId) throws Exception {

        return propellerService.getPropeller(propellerId);
    }

    @DeleteMapping("/rockets/{rocketId}/propellers/{propellerId}")
    public Propeller deletePropeller(@PathVariable Long propellerId) throws Exception {

        return propellerService.deletePropeller(propellerId);
    }

    @PostMapping("/rockets/{rocketId}/movement")
    public Rocket moveRocket(@PathVariable Long rocketId, @RequestBody Movement movement) throws Exception {
       
        Rocket rocket = rocketService.findRocket(rocketId);
        rocketService.moveRocket(rocket, movement);
        return rocket;
    }

}
