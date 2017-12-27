package com.gd.studentapp;

import com.gd.studentapp.dao.StudentDao;
import com.gd.studentapp.db.JDBCConnection;
import com.gd.studentapp.db.JDBCConnectionImpl;
import com.gd.studentapp.repository.StudentRepository;
import com.gd.studentapp.service.StudentService;
import com.gd.studentapp.service.StudentServiceImpl;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class DependencyBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(JDBCConnectionImpl.class).to(JDBCConnection.class);
        bind(StudentServiceImpl.class).to(StudentService.class);
        bind(StudentDao.class).to(StudentRepository.class);
    }
}