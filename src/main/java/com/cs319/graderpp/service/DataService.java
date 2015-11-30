package com.cs319.graderpp.service;

import com.cs319.graderpp.models.*;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Service(value = "dataService")
public class DataService {

    private DataServiceImpl realDataService;

    public DataService()
    {
        this.realDataService = new DummyDataImpl();
        //this.realDataService = new DatabaseDataImpl();
    }

    @PostConstruct
    public void init() {
        //this.dataService = new DummyDataImpl();
    }

    public DataServiceImpl getRealDataService() {
        return realDataService;
    }

    public void setRealDataService(DataServiceImpl realDataService) {
        this.realDataService = realDataService;
    }
}