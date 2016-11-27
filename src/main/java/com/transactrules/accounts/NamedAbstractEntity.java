package com.transactrules.accounts;

import javax.persistence.MappedSuperclass;

/**
 * Created by Igor Music on 2016/11/11.
 */
@MappedSuperclass
public class NamedAbstractEntity extends AbstractEntity {

    protected String name;

    protected NamedAbstractEntity() {

    }

    public NamedAbstractEntity(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

}
