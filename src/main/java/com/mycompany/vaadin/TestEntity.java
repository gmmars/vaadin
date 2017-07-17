/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vaadin;

public class TestEntity {
private int idNumb;
private String testName;
private String testDate;

    TestEntity()
{
    super();
}    
TestEntity(int id, String name, String cdate)
{
    idNumb=id;
    testName=name;
    testDate=cdate;
}
public void setId(int id)
{
    idNumb=id;
}

public int getId()
{
    return idNumb;
}

public void setName(String name)
{
    testName=name;
}

public String getName()
{
    return testName;
}

public void setDate(String cdate)
{
    testDate=cdate;
}

public String getDate()
{
    return testDate;
}
}
