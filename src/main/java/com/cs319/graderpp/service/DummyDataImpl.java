package com.cs319.graderpp.service;

import com.cs319.graderpp.models.*;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


public class DummyDataImpl {/*implements DataServiceImpl {

    private List<User> userList;
    private List<Task> taskList;
    private List<Course> courseList;
    private List<Submission> submissionList;

    public DummyDataImpl() {
        courseList = prepareCourseList();
        taskList = prepareTaskList();
        userList = prepareUserList();
        submissionList = new ArrayList<Submission>();
    }

    private List<Task> prepareTaskList() {
        List<Task> tasks = new ArrayList<Task>();
        Task t1 = new Task(courseList.get(0), "lab1", (new DateTime(2015, 10, 12, 23, 12)).toDate());
        t1.setTaskId(4);
        Task t2 = new Task(courseList.get(1), "hw1", (new DateTime(2015, 10, 21, 23, 59)).toDate());
        t2.setTaskId(2);
        Task t3 = new Task(courseList.get(2), "hw2", (new DateTime(2015, 11, 3, 0, 0)).toDate());
        t3.setTaskId(10);

        courseList.get(0).addTask(t1);
        courseList.get(0).addTask(t2);
        courseList.get(1).addTask(t3);

        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);
        return tasks;
    }

    private List<Course> prepareCourseList() {
        List<Course> courses = new ArrayList<Course>();
        Course c1 = new Course("CS224", "Computer Organization");
        c1.setCourseId((int) (Math.random() * 10000));

        Course c2 = new Course("CS202", "Data Structures");
        c2.setCourseId((int) (Math.random() * 10000));

        Course c3 = new Course("CS102", "Introduction to Programming");
        c3.setCourseId((int) (Math.random() * 10000));

        courses.add(c1);
        courses.add(c2);
        courses.add(c3);

        return courses;
    }

    private List<User> prepareUserList() {
        List<User> users = new ArrayList<User>();
        Student u1 = new Student("st", "123", "123", "Burak Ali");
        u1.setUserId((int) (Math.random() * 1000));
        u1.setCourses(courseList);

        Instructor u2 = new Instructor("in", "123", "Al-Muderris");
        u2.setUserId((int) (Math.random() * 1000));
        u2.setCourses(courseList);

        Assistant u3 = new Assistant("ta", "123", "Hasan Barış");
        List<Task> ts = new ArrayList<Task>();
        ts.add(courseList.get(1).getTasks().get(0));
        u3.setTasks(ts);
        u3.setUserId((int) (Math.random() * 1000));

        Assistant u4 = new Assistant("ta2", "123", "Ali Atlı");
        u4.setTasks(ts);
        u4.setUserId((int) (Math.random() * 1000));

        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        return users;
    }

    public void addSubmission(Submission submission) {
        submission.getTask().getSubmissions().add(submission);
    }

    public List<Task> findAllTasksOfUser(User user) {
        List<Task> tasks = new ArrayList<Task>();
        if (user instanceof Student) {
            for (Course course : ((Student) user).getCourses()) {
                for (Task task : course.getTasks()) {
                    tasks.add(task);
                }
            }
        } else if (user instanceof Assistant) {
            for (Task task : ((Assistant) user).getTasks()) {
                tasks.add(task);
            }
        } else if (user instanceof Instructor) {
            tasks = getTaskList();
        }
        return tasks;
    }

    public void addTask(Task task) {
        taskList.add(task);

        //update the course and assistant's references
        task.getCourse().getTasks().add(task);
        task.getAssistant().getTasks().add(task);

    }

    public void addCourse(Course course) {
        courseList.add(course);

        //TODO update neseccary things
    }


    public Task findTaskById(int taskId) {
        for (Task task : taskList) {
            if (task.getTaskId() == taskId)
                return task;
        }
        return null;
    }

    public void updateTask(int taskId, Task newTask) {
        int index = -1;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskId == taskList.get(i).getTaskId()) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            taskList.set(index, newTask);
        }
    }


    public Course findCourseById(int courseId) {
        for (Course course : courseList) {
            if (course.getCourseId() == courseId)
                return course;
        }
        return null;
    }

    public User findUserById(int userId) {
        for (User user : userList) {
            if (user.getUserId() == userId)
                return user;
        }
        return null;
    }

    public Submission findSubmissionById(int submissionId) {
        for (Submission submission : submissionList) {
            if (submission.getSubmissionId() == submissionId)
                return submission;
        }
        return null;
    }

    public List<Assistant> findAllAssistants() {
        List<Assistant> assistants = new ArrayList<Assistant>();
        for (User user : userList) {
            if (user instanceof Assistant) {
                assistants.add((Assistant) user);
            }
        }
        return assistants;
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

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<Submission> getSubmissionList() {
        return submissionList;
    }

    public void setSubmissionList(List<Submission> submissionList) {
        this.submissionList = submissionList;
    }*/
}