package com.cs319.graderpp.service;

import com.cs319.graderpp.models.*;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.asList;

/**
 * Created by yusuf on 25-Nov-15.
 */

public class DatabaseDataImpl implements DataServiceImpl {

    private MongoCollection<Document> _user_list, _course_list,
    _task_list, _submission_list;

    private  String[] _collection_keys = {"user_list", "course_list",
            "task_list", "submission_list"};
    private String[] _user_keys = {"_id", "name", "password", "full_name",
            "user_type",
            "course_list", "task_list", "submission_list"};
    private String[] _course_keys = {"_id", "name", "code", "instructor_id"};
    private String[] _task_keys = {"_id", "name", "course_id", "ta_id",
            "due_date"};

    public enum MongoUserType {student, ta, instructor};
    public enum MongoModelType{course, task, submission};

    public DatabaseDataImpl(){
        MongoClient mongoClient = new MongoClient("localhost");
        MongoDatabase db = mongoClient.getDatabase("db0");
        _user_list = db.getCollection(_collection_keys[0]);
        _course_list = db.getCollection(_collection_keys[1]);
        _task_list = db.getCollection(_collection_keys[2]);
        _submission_list = db.getCollection(_collection_keys[3]);

        //System.out.println("collections created or connected successfuly");
        /*
        clearCollections();
        generateDemoUsers();
        */
        deleteDemoTasks();
    }

    private void connDB(){
        MongoClient mongoClient = new MongoClient("localhost");
        MongoDatabase db = mongoClient.getDatabase("db0");
        MongoCollection<Document> coll = db.getCollection("main_list");

        coll.deleteMany(new Document());
        coll.insertOne(new Document("name", "stu1"));
        coll.insertOne(new Document("name", "stu2"));
        coll.insertOne(new Document("name", "stu3"));

        FindIterable<Document> iterable =
                db.getCollection("main_list").find();

        iterable.forEach(new Block<Document>() {
            public void apply(final Document document) {
                System.out.println(document);
            }
        });
        System.out.println(iterable.first().toString());
    }

    private void clearCollections(){
        Document d = new Document();
        _user_list.deleteMany(d);
        _course_list.deleteMany(d);
        _submission_list.deleteMany(d);
        _task_list.deleteMany(d);
    }

    private void generateDemoUsers(){
        addUser("stu", "123", "student demo", MongoUserType.student);
        addUser("ta", "123", "ta demo", MongoUserType.ta);
        addUser("inst", "123", "instructor demo", MongoUserType.instructor);
    }

    private void generateDemoCourses(String user_id, String inst_id){
        addCourse(user_id, inst_id, "demo1", "demo 101");
        addCourse(user_id, inst_id, "demo2", "demo 102");
        addCourse(user_id, inst_id, "demo3", "demo 103");
    }

    private void deleteDemoCourses(String user_id){
        deleteCourse(user_id, "demo1", "demo 101");
        deleteCourse(user_id, "demo2", "demo 102");
        deleteCourse(user_id, "demo3", "demo 103");
    }

    private void generateDemoTasks(){

        Document ta_doc = new Document(_user_keys[1], "ta");

        FindIterable<Document> iterable = _user_list.find(ta_doc);
        String ta_id = iterable.first().getObjectId("_id").toHexString();
        String course_id = _course_list.find(new
                Document()).first().getObjectId("_id").toHexString();

        addTask("task1", course_id, ta_id, "30.12.2015");
        addTask("task2", course_id, ta_id, "30.12.2015");
        addTask("task3", course_id, ta_id, "30.12.2015");
    }

    private void deleteDemoTasks(){
        String task_id = _task_list.find(new
                Document()).first().getObjectId("_id").toHexString();

        deleteTask(task_id);
    }

    private void subscribe2CourseDemo(){
        Document student_doc = new Document(_user_keys[1], "stu");
        Document student = _user_list.find(student_doc).first();

        Document course = _course_list.find(new Document()).first();

        subscribe2Course(student.getObjectId("_id").toHexString(),
                course.getObjectId("_id").toHexString());
    }

    private boolean addUser(String name, String pass, String full_name,
                            MongoUserType user_type){
        if (getSignedUser(name, pass) != null){
            return false;
        }

        Document new_user = new Document();
        new_user.append(_user_keys[1], name);
        new_user.append(_user_keys[2], pass);
        new_user.append(_user_keys[3], full_name);
        new_user.append(_user_keys[4], MongoUserType2string(user_type));
        new_user.append(_user_keys[5], asList());
        new_user.append(_user_keys[6], asList());
        new_user.append(_user_keys[7], asList());

        _user_list.insertOne(new_user);

        return true;
    }

    private boolean deleteUser(String user_id){

        return true;
    }

    private boolean addCourse(String user_id, String inst_id, String name,
                              String code){
        Document new_course = new Document();

        new_course.append(_course_keys[1], name);
        new_course.append(_course_keys[2], code);
        new_course.append(_course_keys[3], inst_id);

        FindIterable<Document> iterable = _course_list.find(new_course);
        if (iterable.first() != null){  // already have this course
            return false;
        }

        //insert
        _course_list.insertOne(new_course);

        //subscribe to the newly generated course
        String course_id = new_course.getObjectId("_id").toHexString();
        return subscribe2Course(user_id, course_id);
    }

    private boolean deleteCourse(String user_id, String name, String code){
        Document dead_course_doc = new Document();

        dead_course_doc.append(_course_keys[1], name);
        dead_course_doc.append(_course_keys[2], code);

        FindIterable<Document> iterable = _course_list.find(dead_course_doc);
        Document dead_course = iterable.first();

        if (dead_course == null){  //do not have that course
            return false;
        }

        //delete
        String course_id = dead_course.getObjectId("_id").toHexString();

        //unsubscribe from newly deleted course
        _course_list.deleteOne(dead_course);

        //if anybody takes that course than delete course_id from his
        //course list
        return unsubscribeAllFromModel(course_id, course_id,
                MongoModelType.course, MongoModelType.course);
    }

    private boolean addTask(String name, String course_id, String ta_id,
                            String due_date){
        Document new_task = new Document();

        new_task.append(_task_keys[1], name);
        new_task.append(_task_keys[2], course_id);
        new_task.append(_task_keys[3], ta_id);
        new_task.append(_task_keys[4], due_date);

        FindIterable<Document> iterable = _task_list.find(new_task);
        if (iterable.first() != null){  // already have this task
            return false;
        }
        //insert
        _task_list.insertOne(new_task);
        String task_id = new_task.getObjectId("_id").toHexString();

        //ta should get subscribed to the course that he assigned by inst
        subscribe2Course(ta_id, course_id);

        //if anybody takes that course than subscribe to the tasks of that
        //course
        return subscribeAll2Model(task_id, course_id,
                MongoModelType.course, MongoModelType.task);
    }

    private boolean deleteTask(String task_id){

        Document dead_task_doc = new Document();
        dead_task_doc.append(_task_keys[0], task_id);

        FindIterable<Document> iterable = _task_list.find(dead_task_doc);
        Document dead_task = iterable.first();

        if (dead_task == null){
            return false;
        }
        _task_list.deleteOne(dead_task);

        //if task_list of a user contains that task than delete it
        return unsubscribeAllFromModel(task_id, task_id,
                MongoModelType.task, MongoModelType.task);
    }

    private boolean addSubmission(){
        return true;
    }

    private boolean deleteSubmission(String submission_id){
        return true;
    }

    private boolean subscribeAll2Model(String new_id, String model_id,
                                       MongoModelType query_coll,
                                       MongoModelType add_coll){

        int query_ind = getModelIndFromType(query_coll);
        int add_ind = getModelIndFromType(add_coll);

        //subscribe to the newly generated course
        FindIterable<Document> iterable1 = _user_list.find(new Document());

        for (Document curr_user : iterable1){
            String user_id = curr_user.getObjectId("_id").toHexString();
            if (inCustomList(user_id, model_id, query_ind)){
                subscribe2List(user_id, new_id, add_ind);
            }
        }

        return true;
    }

    private boolean unsubscribeAllFromModel(String dead_id, String
            model_id, MongoModelType query_coll, MongoModelType del_coll){

        int query_ind = getModelIndFromType(query_coll);
        int delete_ind = getModelIndFromType(del_coll);

        FindIterable<Document> iterable1 = _user_list.find(new Document());
        for (Document curr_user : iterable1){
            String user_id = curr_user.getObjectId("_id").toHexString();
            if (inCustomList(user_id, model_id, query_ind)){
                unsubscribeFromList(user_id, dead_id, delete_ind);
            }
        }
        return true;
    }

    private int getModelIndFromType(MongoModelType m){
        switch (m){
            case course:
                return 5;
            case task:
                return 6;
            case submission:
                return 7;
            default:
                return 5;
        }
    }

    private boolean inCustomList(String user_id, String elem_id, int
            list_ind){
        List l = getList4User(user_id, list_ind);
        return l.contains(elem_id);
    }

    private boolean subscribe2List(String user_id, String elem_id, int
            list_ind){
        List l = getList4User(user_id, list_ind);
        l.add(elem_id);

        Document curr_user = new Document().append("_id", new
                ObjectId(user_id));
        UpdateResult ur = _user_list.updateOne(curr_user,
                new Document("$set", new Document(_user_keys[list_ind], l)));

        return true;
    }

    private boolean unsubscribeFromList(String user_id, String elem_id,
                                        int list_ind){
        List l = getList4User(user_id, list_ind);
        l.remove(elem_id);

        Document curr_user = new Document().append("_id", new
                ObjectId(user_id));
        UpdateResult ur = _user_list.updateOne(curr_user,
                new Document("$set", new Document(_user_keys[list_ind], l)));

        return true;
    }

    private boolean subscribe2Course(String user_id, String course_id){
        return subscribe2List(user_id, course_id, 5);
    }

    private boolean unsubscribeFromCourse(String user_id, String course_id){
        return unsubscribeFromList(user_id, course_id, 5);
    }

    private boolean subscribe2Task(String user_id, String task_id){
        return subscribe2List(user_id, task_id, 6);
    }

    private boolean unsubscribeFromTask(String user_id, String task_id){
        return unsubscribeFromList(user_id, task_id, 6);
    }

    private boolean subscribe2Submission(String user_id, String task_id){
        return subscribe2List(user_id, task_id, 7);
    }

    private boolean unsubscribeFromSubmission(String user_id, String
            task_id){
        return unsubscribeFromList(user_id, task_id, 7);
    }

    private  List getCourses4User(String user_id){ return
            getList4User(user_id, 5); }

    private List getTasks4User(String user_id){ return
            getList4User(user_id, 6); }

    private List getSubmissions4User(String user_id){ return
            getList4User(user_id, 7); }

    private List getList4User(String user_id, int list_ind){

        List l = asList();
        Document user = new Document();
        user.append(_user_keys[0], new ObjectId(user_id));

        FindIterable<Document> iterable = _user_list.find(user);
        Document the_user = iterable.first();
        if (the_user == null ){
            return l;
        }
        l = (List)the_user.get(_user_keys[list_ind]);

        return l;
    }

    private ArrayList<String> getUserList(MongoUserType m){
        ArrayList<String> l = new ArrayList<String>();

        FindIterable<Document> iterable = _user_list.find(
                new Document(_user_keys[4], MongoUserType2string(m)));

        for (Document d : iterable){
            String s = d.getObjectId(_user_keys[0]).toHexString();
            l.add(s);
        }

        return l;
    }

    private ArrayList<String> getModelList(MongoModelType m){
        ArrayList<String> l = new ArrayList<String>();
        FindIterable<Document> coll;
        Document d = new Document();

        switch (m){

            case course:
                coll = _course_list.find(d);
                break;
            case task:
                coll = _task_list.find(d);
                break;
            case submission:
                coll = _submission_list.find(d);
                break;
            default:
                coll = _course_list.find(d);
        }

        for(Document doc : coll){
            l.add(doc.getObjectId("_id").toHexString());
        }

        return l;
    }

    //interface methods which are public
    public User getSignedUser(String name, String pass){
        Document user_doc = new Document();
        user_doc.append(_user_keys[1], name);
        user_doc.append(_user_keys[2], pass);

        FindIterable<Document> iterable = _user_list.find(user_doc);
        Document d = iterable.first();

        if (d == null){
            return null;
        }

        User u = new User(d.getString(_user_keys[1]),
                d.getString(_user_keys[2]), d.getString(_user_keys[3]));
       // u.setUserId(d.getObjectId(_user_keys[0]).toHexString()); //the most important value

        /*
        generateDemoCourses(u.getUserId(), u.getUserId());
        subscribe2CourseDemo();
        generateDemoTasks();

        List l = getModelList(MongoModelType.task);
        l = getModelList(MongoModelType.course);
        l = getModelList(MongoModelType.submission);
        l = getCourseList();
        l = getSubmissionList();
        l = getTaskList();
        */

        return u;
    }

    public boolean generateTask(){

        return true;
    }

    public boolean generateSubmission(){

        return true;
    }

    private String MongoUserType2string(MongoUserType mut){
        switch (mut) {
            case student:
                return "stu";

            case ta:
                return "ta";

            case instructor:
                return "inst";

            default:
                return "stu";
        }
    }

    public List<User> getUserList() {
        return null;
    }

    public void setUserList(List<User> userList) {

    }

    public List<Task> getTaskList() {
        return null;
    }

    public void setTaskList(List<Task> taskList) {

    }

    public void addSubmission(Submission submission) {

    }

    public Task findTaskById(int taskId) {
        return null;
    }

    public Course findCourseById(int courseId) {
        return null;
    }

    public User findUserById(int userId) {
        return null;
    }

    public Submission findSubmissionById(int submissionId) {
        return null;
    }

    public Task findTaskById(String taskId) {
        return null;
    }

    public Course findCourseById(String courseId) {
        return null;
    }

    public User findUserById(String userId) {
        return null;
    }

    public Submission findSubmissionById(String submissionId) {
        return null;
    }

    public List<Course> getCourseList() {
        return null;
    }

    public void setCourseList(List<Course> courseList) {

    }

    public List<Submission> getSubmissionList() {
        return null;
    }

    public void setSubmissionList(List<Submission> submissionList) {

    }

    public List<Assistant> findAllAssistants() {
        return null;
    }

    public void updateTask(int taskId, Task newTask) {

    }

    public List<Task> findAllTasksOfUser(User user) {
        return null;
    }

    public void addTask(Task task) {

    }

    public void addCourse(Course course) {

    }
}