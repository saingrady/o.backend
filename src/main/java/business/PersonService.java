package business;

import org.springframework.stereotype.Service;

import domain.Person;

@Service
public class PersonService {

    public Person exportCsv() {
    	
    	Person person = new Person();
        // person.setFirstName("Rady");
        // person.setLastName("Saing");

        return person;
    }

}
