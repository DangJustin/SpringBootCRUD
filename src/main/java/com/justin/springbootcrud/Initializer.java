package com.justin.springbootcrud;

import com.justin.springbootcrud.model.Event;
import com.justin.springbootcrud.model.Group;
import com.justin.springbootcrud.model.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.stream.Stream;

@Component
public class Initializer implements CommandLineRunner {
    private final GroupRepository repository;
    public Initializer(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        Stream.of("Toronto Group", "Montreal Group", "Ottawa Group", "Vancouver Group").forEach(name ->
                repository.save(new Group(name)));
        Group group = repository.findByName("Toronto Group");
        Event e = Event.builder().title("Dinner")
                .description("Found a new restaurant to eat at.")
                .date(Instant.parse("2024-09-13T17:00:00.000Z"))
                .build();
        group.getEvents().add(e);
        repository.save(group);
        repository.findAll().forEach(System.out::println);
    }
}
