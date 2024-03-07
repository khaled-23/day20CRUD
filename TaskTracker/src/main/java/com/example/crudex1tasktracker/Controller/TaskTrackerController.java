package com.example.crudex1tasktracker.Controller;

import com.example.crudex1tasktracker.ApiResponse.ApiResponse;
import com.example.crudex1tasktracker.Model.TaskTracker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/task-tracker")
public class TaskTrackerController {
    ArrayList<TaskTracker> tasks = new ArrayList<>();


    @PostMapping("/create")
    public ApiResponse createTask(@RequestBody TaskTracker task){
    tasks.add(task);
    return new ApiResponse("task added");
    }

    @GetMapping("/get")
    public ArrayList<TaskTracker> DisplayAllTask(){
        return tasks;
    }
    @GetMapping("/search/{title}")
    public TaskTracker task(@PathVariable String title){
        TaskTracker task = null;
        for(TaskTracker t:tasks){
            if(t.getTitle().equals(title)){
                task = t;
                break;
            }
        }
        return task;
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@PathVariable int index, @RequestBody TaskTracker task){
        tasks.set(index,task);
        return new ApiResponse("task updated");
    }
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index){
        tasks.remove(index);
        return new ApiResponse("task removed");
    }

    @PutMapping("/change-status/{index}")
    public ApiResponse updateStatus(@PathVariable int index){
        if(tasks.get(index).getStatus().equalsIgnoreCase("done")){
            return new ApiResponse("already done");
        }else{
            tasks.get(index).setStatus("done");
            return new ApiResponse("status changed to done");
        }

    }

}
