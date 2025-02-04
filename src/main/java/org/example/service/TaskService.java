package org.example.service;

import org.example.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAll();

    Task findById(long id);

    Task save(Task task);

    Task update(Task task);

    void deleteById(long id);
}
