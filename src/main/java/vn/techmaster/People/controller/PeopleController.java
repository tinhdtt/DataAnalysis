package vn.techmaster.People.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.People.model.Person;
import vn.techmaster.People.repository.PersonRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class PeopleController {
    @Autowired
    private PersonRepository personRepo;

    @GetMapping("youngestdevs")
    public ResponseEntity<Map.Entry<String, Double>> younGestdevs() {
        return ResponseEntity.ok().body(personRepo.younGestdevs());
    }

    @GetMapping("devhanoisaigoshanghai")
    public ResponseEntity<Map<String, List<Person>>> devHanoiSaigoShanghai() {
        return ResponseEntity.ok().body(personRepo.devHanoiSaigoShanghai());
    }

    @GetMapping("malefemaleratio")
    public ResponseEntity<String> maleFemaleratio() {
        return ResponseEntity.ok().body(personRepo.maleFemaleratio());
    }

    @GetMapping("avgsalarypeopleabove30")
    public ResponseEntity<String> avgOverThirty() {
        return ResponseEntity.ok().body(personRepo.avgOverThirty());
    }


}

