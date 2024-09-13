package com.annas.fullstack.practice;

import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service
{
    @Autowired
    private Repository repository;


    public Users addEntity(Users entity)
    {
        return repository.save(entity);
    }
}
