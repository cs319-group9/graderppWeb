package com.cs319.graderpp.misc;

import com.cs319.graderpp.models.*;
import com.cs319.graderpp.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

/**
 * Created by burak on 03.12.2015.
 */

//All methods of this  class is called only when necessary objects are not instantiated earlier
//And member objects are instantiated and set to the model
// All methods are invoked from getter methods of models

@ManagedBean(name = "lazyLoading")
@ApplicationScoped
public class LazyLoading {

    @ManagedProperty("#{dataService}")
    private DataService dataService;

    public Task getTaskOfSubmission(Submission submission)
    {
        if(submission.getTask() != null)
            return submission.getTask();
        else
        {
            Task task = dataService.getRealDataService().findTaskOfSubmission(submission.getSubmissionId());
            submission.setTask(task);
            return task;
        }
    }

    public  List<Course> getCoursesOfUser(User user)
    {
        List<Course> courses = null;

        if(user instanceof Assistant)
            return null;

        //if user is instructor or student
        else
        {
            if( user instanceof Student) {
                if(((Student) user).getCourses() != null)
                {
                    return ((Student) user).getCourses();
                }
                else
                {
                    courses = dataService.getRealDataService().findCoursesOfUser(user);
                    ((Student) user).setCourses(courses);
                }
            }
            else if( user instanceof Instructor) {
                if(((Instructor) user).getCourses() != null)
                {
                    return ((Instructor) user).getCourses();
                }
                else {
                    courses = dataService.getRealDataService().findCoursesOfUser(user);
                    ((Instructor) user).setCourses(courses);
                }
            }

            return courses;

        }
    }

    public  List<Task> getTasksOfCourse(Course course)
    {
        if(course.getTasks() != null)
        {
            return course.getTasks();
        }
        else
        {
            List<Task> tasks;
            tasks = dataService.getRealDataService().findTasksOfCourse(course.getCourseId());
            course.setTasks(tasks);
            return tasks;
        }
    }

    public  List<Task> getTasksOfUser(User user)
    {
        List<Task> tasks = null;

        if(user instanceof Instructor)
            return null;

        //if user is assistant or student
        else
        {
            if( user instanceof Student) {
                if(((Student) user).getTasks() != null)
                {
                    return ((Student) user).getTasks();
                }
                else
                {
                    tasks = dataService.getRealDataService().findAllTasksOfUser(user);
                    ((Student) user).setTasks(tasks);
                }

            }
            else if( user instanceof Assistant) {
                if(((Assistant) user).getTasks() != null)
                {
                    return ((Assistant) user).getTasks();
                }
                else {
                    tasks = dataService.getRealDataService().findAllTasksOfUser(user);
                    ((Assistant) user).setTasks(tasks);
                }
            }

            return tasks;
        }
    }

    public  List<Submission> getSubmissionsOfStudent(Student student)
    {
        if(student.getSubmissions() != null)
        {
            return student.getSubmissions();
        }
        else
        {
            List<Submission> submissions;
            submissions = dataService.getRealDataService().findSubmissionsOfUser(student);
            student.setSubmissions(submissions);
            return submissions;
        }
    }

    public  List<Submission> getSubmissionsOfTask(Task task)
    {
        if( task.getSubmissions() != null)
        {
            return task.getSubmissions();
        }
        else
        {
            List<Submission> submissions;
            submissions = dataService.getRealDataService().findSubmissionsOfTask(task.getTaskId());
            task.setSubmissions(submissions);
            return submissions;
        }
    }

    public DataService getDataService() {
        return dataService;
    }

    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    /*
    //NO NEED SINCE IT IS ASSIGNED IN FINDTASKBYID METHOD
    public Course getCourseOfTask(Task task)
    {
        if( task.getCourse() != null)
            return task.getCourse();
        else
        {
            dataService.getRealDataService().findCourseById(task.getTaskId())
        }
    }

    //NO NEED SINCE IT IS ASSIGNED IN FINDSUBMISSIONBYID METHOD
    public Task getTaskOfSubmission(Submission submission)
    {

    }
    */

}
