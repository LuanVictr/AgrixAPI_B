package com.betrybe.agrix.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.security.Role;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PersonServiceTest {

  @MockBean
  private PersonRepository personRepository;

  @Autowired
  private PersonService personService;

  @Test
  public void getPersonByIdTest() {
    Person personToTest = new Person();
    personToTest.setId(1L);
    personToTest.setUsername("Lilas");
    personToTest.setPassword("lilas1011");
    personToTest.setRole(Role.ADMIN);

    Mockito.when(this.personRepository.findById(Mockito.any())).thenReturn(
        Optional.of(personToTest));

    Person personReturned = this.personService.getPersonById(1L);

    assertNotNull(personReturned.getId());
    assertEquals(personToTest.getId(), personReturned.getId());
    assertEquals(personToTest.getUsername(), personReturned.getUsername());
    assertEquals(personToTest.getPassword(), personReturned.getPassword());
    assertEquals(personToTest.getRole(), personReturned.getRole());

  }

  @Test
  public void getPersonByUsernameTest() {
    Person personToTest = new Person();
    personToTest.setId(1L);
    personToTest.setUsername("Lilas");
    personToTest.setPassword("lilas1011");
    personToTest.setRole(Role.ADMIN);

    Mockito.when(this.personRepository.findByUsername(Mockito.any())).thenReturn(
        Optional.of(personToTest));

    Person personReturned = this.personService.getPersonByUsername(personToTest.getUsername());

    assertNotNull(personReturned.getId());
    assertEquals(personToTest.getId(), personReturned.getId());
    assertEquals(personToTest.getUsername(), personReturned.getUsername());
    assertEquals(personToTest.getPassword(), personReturned.getPassword());
    assertEquals(personToTest.getRole(), personReturned.getRole());
  }

  @Test
  public void createPersonTest() {

    Person personToCreate = new Person();
    personToCreate.setId(1L);
    personToCreate.setUsername("Lilas");
    personToCreate.setPassword("lilas1011");
    personToCreate.setRole(Role.ADMIN);

    Person personToReturn = new Person();
    personToReturn.setId(personToCreate.getId());
    personToReturn.setUsername(personToCreate.getUsername());
    personToReturn.setPassword(personToCreate.getPassword());
    personToReturn.setRole(personToCreate.getRole());



    Mockito.when(this.personRepository.save(Mockito.any(Person.class)))
        .thenReturn(personToReturn);

    Person createdPerson = this.personService.create(personToCreate);

    assertNotNull(createdPerson.getId());
    assertEquals(createdPerson.getId(), personToCreate.getId());
    assertEquals(createdPerson.getUsername(), personToCreate.getUsername());
    assertEquals(createdPerson.getPassword(), personToCreate.getPassword());
    assertEquals(createdPerson.getRole(), personToCreate.getRole());

  }

}
