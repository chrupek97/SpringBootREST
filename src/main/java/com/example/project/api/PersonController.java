package com.example.project.api;

import com.example.project.model.Person;
import com.example.project.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody Person person){
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople(){
        return personService.selectAllPeople();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonById(id)
                .orElse(null);
    }

    @DeleteMapping
    public int deletePerson(@PathVariable("id") UUID id){
        return personService.deletePerson(id);
    }

    @PutMapping("/{id}")
    public int updatePerson(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Person person){
        return personService.updatePerson(id, person);
    }
}
