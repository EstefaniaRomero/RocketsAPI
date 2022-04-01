package com.example.rocketsapi;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;



@Entity(name ="propellers")
public class Propeller {


    private int maxPower;
    private int actualPower = 0;
    private static final int INCREMENT_POWER = 10;
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "rocketId")
    private Rocket rocket;


    public Propeller() {

    }

    public Propeller(int maxPower) throws Exception {
        checkPowerPropeller(maxPower);

        this.maxPower = maxPower;
    }



    private void checkPowerPropeller(int power) throws Exception {
        if(power < 0) throw new Exception("La potencia no pot ser menor que 0!");

    }

    public int getMaxPower() {
        return maxPower;
    }

    public int getActualPower() {
        return actualPower;
    }


    public void speedUp(){

        actualPower+= INCREMENT_POWER;
        if(actualPower > maxPower){
            actualPower = maxPower;
        }
    }

    public void brake(){

        actualPower-= INCREMENT_POWER;
        if(actualPower < 0){

            actualPower = 0;
        }
    }

    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
    }

    public void setActualPower(int actualPower) {
        this.actualPower = actualPower;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    @Override
    public String toString() {
        return "Propeller{" +
                "power=" + maxPower + "," +
                "actualPower=" + actualPower +
                "}\n";
    }


}
