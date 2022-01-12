package com.example.demo;

import com.example.demo.entity.*;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OtherTests {

	private TestConnection conn = new TestConnection();

	//Проверка на подключение к БД
	@Test
	public void isJDBCConnectionValid() throws SQLException{
		try(Connection connection = conn.getConnection()){
			assertTrue(connection.isValid(1));
			assertFalse(connection.isClosed());
		}
	}

	//Проверка создания и удаления таблиц
	@Test
	public void createDeleteTablesTest() throws SQLException{
		try(Connection connection = conn.getConnection()){
			conn.parentTableCREATE();
			conn.childTableCREATE();
			conn.childParentTableCREATE();

			DatabaseMetaData metaData = connection.getMetaData();

			ResultSet resultCreate = metaData.getTables("jdbcTest", "public", "%", null);
			List<String> tablesC = new ArrayList<>();
			while(resultCreate.next()) {
				tablesC.add(resultCreate.getString(2) + "." + resultCreate.getString(3));
			}
			assertTrue(tablesC.contains("public.parent"));
			assertTrue(tablesC.contains("public.child"));
			assertTrue(tablesC.contains("public.parent_child"));

			conn.childParentTableDROP();
			conn.parentTableDROP();
			conn.childTableDROP();


			ResultSet resultDel = metaData.getTables("jdbcTest", "public", "%", null);
			List<String> tablesD = new ArrayList<>();
			while(resultDel.next()) {
				tablesD.add(resultDel.getString(2) + "." + resultDel.getString(3));
			}
			assertFalse(tablesD.contains("public.parent"));
			assertFalse(tablesD.contains("public.child"));
			assertFalse(tablesD.contains("public.parent_child"));
		}
	}

	//Проверка на добавление Родителя
	@Test
	public void addParentTest() throws SQLException{
		try(Connection connection = conn.getConnection()){

			conn.parentTableCREATE();

			District district = new District("D1");
			Address address = new Address("A1",district);
			Parent parent = new Parent("FName1", "LName1","",address);
			parent.setId(1L);
			address.setId(1L);

			conn.addParent(parent);


			String query = "SELECT * FROM parent WHERE " +
					"(first_name = ? and last_name = ? and patronymic_name = ? and address_id = ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, parent.getFirstName());
			statement.setString(2, parent.getLastName());
			statement.setString(3, parent.getPatronymicName());
			statement.setLong(4, parent.getAddressParent().getId());

			boolean hasResult = statement.execute();
			conn.parentTableDROP();
			assertTrue(hasResult);

			ResultSet resultSet = statement.getResultSet();
			resultSet.next();

			assertEquals(parent.getAddressParent().getId().intValue(), resultSet.getLong("address_id"));
			assertEquals(parent.getFirstName(), resultSet.getString("first_name"));
			assertEquals(parent.getLastName(), resultSet.getString("last_name"));
			assertEquals(parent.getPatronymicName(), resultSet.getString("patronymic_name"));
		}
	}

	//Проверка на добавление Ребенка
	@Test
	public void addChildTest() throws SQLException{
		try(Connection connection = conn.getConnection()){

			conn.childTableCREATE();

			Child child = new Child("Cf1","Cl1","",10);
			child.setId(1L);

			conn.addChild(child);

			String query = "SELECT * FROM child WHERE " +
					"(first_name = ? and last_name = ? and patronymic_name = ? and age = ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, child.getFirstName());
			statement.setString(2, child.getLastName());
			statement.setString(3, child.getPatronymicName());
			statement.setInt(4, child.getAge());

			boolean hasResult = statement.execute();
			conn.childTableDROP();
			assertTrue(hasResult);

			ResultSet resultSet = statement.getResultSet();
			resultSet.next();

			assertEquals(child.getAge(), resultSet.getInt("age"));
			assertEquals(child.getFirstName(), resultSet.getString("first_name"));
			assertEquals(child.getLastName(), resultSet.getString("last_name"));
			assertEquals(child.getPatronymicName(), resultSet.getString("patronymic_name"));
		}
	}

	//Проверка выборки родителей конкретного ребенка
	@Test
	public void isValidParents() throws SQLException{
		try(Connection connection = conn.getConnection()){
			conn.parentTableCREATE();
			conn.childTableCREATE();
			conn.childParentTableCREATE();

			Child testChild = new Child("TestF1","TestL1","TestP1",10);
			Child otherChild = new Child("OtherF1","OtherL1","",1);
			testChild.setId(1L);
			otherChild.setId(2L);

			District district = new District("D1");
			Address address = new Address("A1",district);
			address.setId(1L);

			Parent testParent1 = new Parent("TestP1", "LName1","",address);
			Parent testParent2 = new Parent("TestP2", "LName2","",address);
			testParent1.setId(1L);
			testParent2.setId(2L);

			Parent otherParent1 = new Parent("OtherF1","OtherL1","", address);
			Parent otherParent2 = new Parent("OtherF2","OtherL2","", address);
			otherParent1.setId(3L);
			otherParent2.setId(4L);

			conn.addChild(testChild);
			conn.addChild(otherChild);
			conn.addParent(testParent1);
			conn.addParent(testParent2);
			conn.addParent(otherParent1);
			conn.addParent(otherParent2);

			conn.addChildParentConnect(testChild,testParent1);
			conn.addChildParentConnect(testChild,testParent2);
			conn.addChildParentConnect(otherChild,otherParent1);
			conn.addChildParentConnect(otherChild,otherParent2);

			String query = "SELECT parent.* FROM child " +
					"JOIN parent_child ON child.id = parent_child.child_id " +
					"JOIN parent ON parent.id = parent_child.parent_id " +
					"WHERE (child.id = ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, testChild.getId());

			boolean hasResult = statement.execute();

			assertTrue(hasResult);

			ResultSet resultSet = statement.getResultSet();
			resultSet.next();

			conn.childParentTableDROP();
			conn.parentTableDROP();
			conn.childTableDROP();

			assertEquals(testParent1.getId().longValue(), resultSet.getLong("id"));
			assertEquals(testParent1.getAddressParent().getId().longValue(), resultSet.getLong("address_id"));
			assertEquals(testParent1.getFirstName(), resultSet.getString("first_name"));
			assertEquals(testParent1.getLastName(), resultSet.getString("last_name"));
			assertEquals(testParent1.getPatronymicName(), resultSet.getString("patronymic_name"));

			resultSet.next();

			assertEquals(testParent2.getId().longValue(), resultSet.getLong("id"));
			assertEquals(testParent2.getAddressParent().getId().longValue(), resultSet.getLong("address_id"));
			assertEquals(testParent2.getFirstName(), resultSet.getString("first_name"));
			assertEquals(testParent2.getLastName(), resultSet.getString("last_name"));
			assertEquals(testParent2.getPatronymicName(), resultSet.getString("patronymic_name"));

			assertFalse(resultSet.next());
		}
	}
}
