package com.cs319.graderpp.misc;

import com.cs319.graderpp.models.*;
import com.cs319.graderpp.service.DataService;
import org.springframework.stereotype.Service;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

/**
 * Created by burak on 03.12.2015.
 */

//All methods of this  class is called only when necessary objects are not instantiated earlier
//And member objects are instantiated and set to the model
// All methods are invoked from getter methods of models

@Service
public class LazyLoading {

    @ManagedProperty("#{dataService}")
    private DataService dataService;

    public  List<Course> getCoursesOfUser(User user)
    {
        List<Course> courses;

        if(user instanceof Assistant)
            return null;

        //if user is instructor or student
        else
        {
            courses = dataService.getRealDataService().findCoursesOfUser(user);

            if( user instanceof Student)
                ((Student) user).setCourses(courses);
            else if( user instanceof Instructor)
                ((Instructor) user).setCourses(courses);

            return courses;
        }
    }

    public  List<Task> getTasksOfCourse(Course course)
    {
        List<Task> tasks;

        tasks = dataService.getRealDataService().findTasksOfCourse(course.getCourseId());
        course.setTasks(tasks);
        return tasks;

    }

    public  List<Task> getTasksOfAssistant(Assistant assistant)
    {
        List<Task> tasks;
        tasks = dataService.getRealDataService().findAllTasksOfUser(assistant);
        assistant.setTasks(tasks);
        return tasks;

    }

    public  List<Submission> getSubmissionsOfStudent(Student student)
    {
        List<Submission> submissions;

        submissions = dataService.getRealDataService().findSubmissionsOfUser(student);
        student.setSubmissions(submissions);
        return submissions;

    }

    public  List<Submission> getSubmissionsOfTask(Task task)
    {
        List<Submission> submissions;

        submissions = dataService.getRealDataService().findSubmissionsOfTask(task.getTaskId());
        task.setSubmissions(submissions);
        return submissions;

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
