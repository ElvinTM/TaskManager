package org.example.repository.imp;

import lombok.RequiredArgsConstructor;
import org.example.model.Task;
import org.example.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
public class InTaskRepository implements TaskRepository {

    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();

    private final AtomicLong taskId = new AtomicLong();

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public Optional<Task> findById(long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    @Override
    public Task save(Task task) {
        long id = taskId.incrementAndGet();
        task.setId(id);
        tasks.put(id, task);
        return task;
    }

    @Override
    public Task update(Task task) {;
        Task taskToUpdate = findById(task.getId()).orElse(null);
        if (taskToUpdate != null) {
            taskToUpdate.setTitle(task.getTitle());
            taskToUpdate.setDescription(task.getDescription());
            taskToUpdate.setPriority(task.getPriority());
        }
        return taskToUpdate;
    }

    @Override
    public void deleteById(long id) {
        Task taskToDelete = findById(id).orElse(null);
        if (taskToDelete != null) {
            tasks.remove(id);
        }
    }
}
