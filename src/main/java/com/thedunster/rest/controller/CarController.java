package com.thedunster.rest.controller;

import com.thedunster.jpa.entities.CarEntity;
import com.thedunster.jpa.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/citation/car")
public class CarController {
    private static final Logger log = LoggerFactory.getLogger(CarController.class);

    @Autowired
    CarRepository carRepository;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<CarEntity> index(@RequestParam(value = "make", required = false) String make) {
        final String method = "index";

        List<CarEntity> carEntities;
        log.info("{}: Request with make: {}", method, make);
        if (make == null) {
            carEntities = (List<CarEntity>) carRepository.findAll();
        } else {
            carEntities = carRepository.findByMake(make);
        }
        return carEntities;
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<String> listMake(){
        List<String> makeList = carRepository.findUniqueMakes();
        return makeList;
    }

}