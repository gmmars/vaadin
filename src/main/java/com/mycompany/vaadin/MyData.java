
package com.mycompany.vaadin;

import java.sql.*;
import java.util.ArrayList;

public class MyData {
private final String url;
private String addSql= "INSERT INTO testentity(name, CREATIONDATE) VALUES(?,?)";
private String delSql= "DELETE FROM testentity WHERE id=?";
private PreparedStatement stmntSelect;
private PreparedStatement stmntAdd;
private PreparedStatement stmntDel;

       public MyData() {
        this.url ="jdbc:derby://localhost:1527/mytestdb";
           }
    
    public void connectDB(){
try
    {
    Connection con=DriverManager.getConnection(url, "test", "test");
    stmntSelect=con.prepareStatement("SELECT * FROM testentity");
    stmntAdd=con.prepareStatement(addSql);
    stmntDel=con.prepareStatement(delSql);
    }
catch(SQLException e)  {  e.printStackTrace();  }
finally {  // con.close();
    }

}
public ArrayList<TestEntity> getRecords()
{
ArrayList<TestEntity> returnList=new ArrayList<>(); 

try{
    connectDB();
    ResultSet rs=stmntSelect.executeQuery();
    while(rs.next()){
    returnList.add(new TestEntity(rs.getInt("Id"),rs.getString("Name"), rs.getString("CreationDate")));
    }
   }
catch(SQLException e)
    {
    e.printStackTrace();
    }

    return returnList;
}

public void addRecord(String name) 
{
    try{
        connectDB(); 
    stmntAdd.setString(1, name);
    java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
    stmntAdd.setDate(2, sqlDate);
    stmntAdd.executeUpdate();
   
    }
    catch(SQLException e){e.printStackTrace();}
}

public void delRecord(int delId) 
{
    try{
        connectDB();
        stmntDel.setInt(1, delId);
        stmntDel.executeUpdate();
        }
    catch(SQLException e){e.printStackTrace();}
    
}

public void editRecord(){}
}