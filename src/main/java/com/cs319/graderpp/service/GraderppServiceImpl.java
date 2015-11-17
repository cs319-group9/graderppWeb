package com.cs319.graderpp.service;

import com.cs319.graderpp.adapter.*;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Service(value = "graderppService")
public class GraderppServiceImpl implements GraderppService {

    private List<User> userList;
    private List<Task> taskList;
    private List<Course> courseList;

    @PostConstruct
    public void init()
    {
        courseList = prepareCourseList();
        taskList = prepareTaskList();
        userList = prepareUserList();
    }

    private List<Task> prepareTaskList()
    {
        List<Task> tasks = new ArrayList<Task>();
        Task t1 = new Task(4, courseList.get(0), "lab1", "20 dec.");
        Task t2 = new Task(2, courseList.get(1), "hw1", "19 dec.");
        Task t3 = new Task(10, courseList.get(2), "hw2", "23 dec.");

        courseList.get(0).addTask(t1);
        courseList.get(0).addTask(t2);
        courseList.get(1).addTask(t3);

        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);
        return tasks;
    }

    private List<Course> prepareCourseList()
    {
        List<Course> courses = new ArrayList<Course>();
        courses.add(new Course("CS224", "Computer Organization"));
        courses.add(new Course("CS202", "Data Structures"));
        courses.add(new Course("CS102", "Introduction to Programming"));
        return courses;
    }
    private List<User> prepareUserList()
    {
        List<User> users = new ArrayList<User>();
        Student u1 = new Student("student", "123", 123);
        u1.setCourses(courseList);

        Instructor u2 = new Instructor("instructor", "123");
        u2.setCourses(courseList);

        Assistant u3 = new Assistant("ta", "123");
        List<Task> ts = new ArrayList<Task>();
        ts.add(courseList.get(1).getTasks().get(0));
        u3.setTasks(ts);

        users.add(u1);
        users.add(u2);
        users.add(u3);
        return users;
    }

    public void addSubmission(Submission submission)
    {
        submission.getTask().getSubmissions().add(submission);
    }

    public Task findTaskById (int taskId)
    {
        for(Task task: taskList)
        {
            if(task.getTaskId() == taskId)
                return task;
        }
        return null;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}