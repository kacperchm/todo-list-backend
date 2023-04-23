package com.example.todolistbackend.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository repository;

    public List<ToDo> list(String order) {
        if(order == null) {
            return repository.findAll();
        }
        switch (order) {
            case "asc" :
            {
                return repository.findAll(Sort.by(Sort.Direction.ASC, "dueDate"));
            }case "desc" :
            {
                return repository.findAll(Sort.by(Sort.Direction.DESC, "dueDate"));
            }
            default:
            {
                return repository.findAll();
            }
        }
    }

    public ToDo getTodo(Integer id) {
        return repository.findById(id).get();
    }

    public ToDo create(String title, String dueDate) {
        ToDo toDo = new ToDo();
        toDo.setTitle(title);
        toDo.setDueDate(dueDate);
        toDo.setStatus(ToDoStatus.NEW);
        return repository.save(toDo);
    }

    public ToDo update(ToDo toDo) {
        ToDo updateToDo = repository.findById(toDo.getId()).orElseThrow();
        updateToDo.setTitle(toDo.getTitle());
        updateToDo.setDueDate(toDo.getDueDate());
        updateToDo.setStatus(toDo.getStatus());
        return repository.save(updateToDo);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public int count(ToDoStatus status) {
        return repository.countByStatus(status);
    }

    public List<ToDo> upcoming() {
        return repository.findTop3ByStatusIsNotOrderByDueDateAsc(ToDoStatus.COMPLETED);
    }

    public List<ToDo> search(ToDoStatus status) {
        return repository.findAllByStatus(status);
    }
}
