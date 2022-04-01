package com.example.rocketsapi;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity(name ="rockets")
public class Rocket {


    private String code;

    @Id
    @GeneratedValue
    private Long id;


    @OneToMany(mappedBy = "rocket")
    @JsonManagedReference
    private List<Propeller> propellerList = new ArrayList<>();

    public Rocket() {

    }

    public Rocket(String code) throws Exception {
        checkCode(code);
        this.code = code;

    }

    private void checkCode(String code) throws Exception {
        if (code.equals("") || !code.matches("^([0-9A-Za-z]){8}$")) throw new Exception();

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) throws Exception {
        checkCode(code);
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Propeller> getPropellerList() {
        return propellerList;
    }

    /*public void addPropeller(int power) throws Exception {

        Propeller propeller = new Propeller(power);

        propellerList.add(propeller);


    }*/

    public void speedUpPropeller() {


        for (Propeller currentPropeller : propellerList) {
            currentPropeller.speedUp();

        }

    }

    public void bakerPropeller() {


        for (Propeller currentPropeller : propellerList) {
            currentPropeller.brake();

        }


    }

    @Override
    public String toString() {
        return "Rocket{" +
                "code='" + code + '\'' + '\n' +
                ", propellerList=" + propellerList +
                "}\n";
    }


}

