package com.transactrules.accounts;

import javax.persistence.MappedSuperclass;

/**
 * Created by Igor Music on 2016/11/11.
 */
@MappedSuperclass
public class NamedAbstractEntity extends AbstractEntity {

    protected String name;

    public NamedAbstractEntity()
    {

    }


    public NamedAbstractEntity(String name){
        this.name = name;
    }

    public String name(){

        return this.name;
    }



}
