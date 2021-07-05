package vn.techmaster.People.repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import vn.techmaster.People.model.Person;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Repository
public class PersonRepository {
    private List<Person> people = new ArrayList<Person>();

    public PersonRepository() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        File file;
        try {
            file = ResourceUtils.getFile("classpath:static/person.json");
            people.addAll(mapper.readValue(file, new TypeReference<List<Person>>() {
            }));
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getAll() {
        return people;
    }



    // Trả về danh sách 'developer' ở các thành phố Hanoi, Saigon, Shanghai
    public Map<String, List<Person>> devHanoiSaigoShanghai() {

        return people
                .stream()
                .filter(p -> p.getJob().equals("developer"))
                .filter(p -> p.getCity().equals("Hanoi") || p.getCity().equals("Saigon") || p.getCity().equals("Shanghai"))
                .collect(groupingBy(Person::getCity));
    }



    // Tuổi trẻ nhất có job là develop trong mỗi thành phố
    public Map.Entry<String, Double> younGestdevs() {
        Map<String, Double> result;
        result = people.stream().filter(p -> p.getJob().equals("developer"))
                .collect(groupingBy(Person::getCity, Collectors.averagingInt(Person::getAge)));
        Map.Entry<String, Double> min = null;
        for (Map.Entry<String, Double> entry : result.entrySet()) {
            if (min == null || min.getValue() > entry.getValue()) {
                min = entry;
            }
        }
        return min;
    }


    public String maleFemaleratio() {
        return "...";
    }

    // Tính mức lương trung bình của tất cả những người trên 30 tuổi
    public String avgOverThirty() {
        String message = "mức lương trung bình của tất cả những người trên 30 tuổi là: ";
        return message + people
                .stream()
                .filter(p -> p.getAge() >= 30)
                .collect(Collectors.averagingInt(Person::getSalary));
    }

}


