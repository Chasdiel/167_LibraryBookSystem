package model;

import dbConnect.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class BaseModel {
    protected int id=-1;    // if id is -1 the book doesn't exist

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    protected BaseModel(int id) {
        setId(id);
    }

    abstract PreparedStatement saveStatement();

    abstract PreparedStatement updateStatement();

}
