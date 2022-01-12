package com.example.demo;

import com.example.demo.entity.Child;
import com.example.demo.entity.Parent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnection {

    Connection connection;

    public Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://127.0.0.1:5432/homework1java";
        String username = "postgres";
        String password = "1234";

        return DriverManager.getConnection(url,username,password);
    }

    public int executeUpdate(String query) throws SQLException {
        this.connection = getConnection();
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate(query);
        return result;
    }

    public void parentTableCREATE() throws SQLException{
        String createTableParent =
                "CREATE TABLE IF NOT EXISTS parent " +
                        "(id SERIAL PRIMARY KEY, " +
                        "first_name TEXT NOT NULL," +
                        "last_name TEXT NOT NULL," +
                        "patronymic_name TEXT NOT NULL," +
                        "address_id INT NOT NULL);";

        executeUpdate(createTableParent);
    }

    public void parentTableDROP() throws SQLException{
        String dropTableParent = "DROP TABLE parent;";

        executeUpdate(dropTableParent);
    }

    public void childTableCREATE() throws SQLException{
        String createTableChild =
                "CREATE TABLE IF NOT EXISTS child " +
                        "(id SERIAL PRIMARY KEY, " +
                        "first_name TEXT NOT NULL," +
                        "last_name TEXT NOT NULL," +
                        "patronymic_name TEXT NOT NULL," +
                        "age INT NOT NULL," +
                        "ed_inst_id INT);";

        executeUpdate(createTableChild);
    }

    public void childTableDROP() throws SQLException{
        String dropTableChild = "DROP TABLE child;";

        executeUpdate(dropTableChild);
    }

    public void addParent(Parent parent) throws SQLException{
        String addParent = "INSERT INTO parent (id,first_name,last_name,patronymic_name,address_id) VALUES (" +
                parent.getId() + "," +
                "'" + parent.getFirstName() + "'," +
                "'" + parent.getLastName() + "'," +
                "'" + parent.getPatronymicName() + "'," +
                parent.getAddressParent().getId() +
                ");";

        executeUpdate(addParent);

    }

    public void addChild(Child child) throws SQLException{
        String addParent = "INSERT INTO child (id, first_name,last_name,patronymic_name,age,ed_inst_id) VALUES (" +
                child.getId() + "," +
                "'" + child.getFirstName() + "'," +
                "'" + child.getLastName() + "'," +
                "'" + child.getPatronymicName() + "'," +
                child.getAge() + "," +
                "NULL);";

        executeUpdate(addParent);

    }

    public void childParentTableCREATE() throws SQLException{
        String createTableChild =
                "CREATE TABLE IF NOT EXISTS parent_child " +
                        "(parent_id INT NOT NULL, " +
                        "child_id INT NOT NULL);";

        String fk1 = "ALTER TABLE IF EXISTS parent_child " +
                        "add constraint parentchild_child_fk " +
                        "foreign key (child_id) " +
                        "references child;";

        String fk2 = "ALTER TABLE IF EXISTS parent_child " +
                        "add constraint parentchild_parent_fk " +
                        "foreign key (parent_id) " +
                        "references parent;";

        executeUpdate(createTableChild);
        executeUpdate(fk1);
        executeUpdate(fk2);
    }

    public void childParentTableDROP() throws SQLException{
        String dropTableChild = "DROP TABLE parent_child;";

        executeUpdate(dropTableChild);
    }

    public void addChildParentConnect(Child child, Parent parent) throws SQLException{
        String addParent = "INSERT INTO parent_child (child_id, parent_id) VALUES (" +
                child.getId() + "," +
                parent.getId() + ");";

        executeUpdate(addParent);
    }
}
