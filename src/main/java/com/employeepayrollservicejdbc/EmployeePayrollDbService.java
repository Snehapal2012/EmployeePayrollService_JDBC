package com.employeepayrollservicejdbc;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class EmployeePayrollDbService {
    public List<EmployeePayrollData> readData() {
        String sql = "Select * from employee_payroll;";
        List<EmployeePayrollData> employeePayrollDataList = new ArrayList<EmployeePayrollData>();
        try (Connection connection = this.getConnection();) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                LocalDate startDate = resultSet.getDate("start").toLocalDate();
                employeePayrollDataList.add(new EmployeePayrollData(id, name, salary, startDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePayrollDataList;
    }

    private Connection getConnection() throws SQLException{
        Connection connection;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/paroll_service?useSSL=false", "root",
                "MySQL@123");
        System.out.println("Connection successful!!!! " + connection);
        return connection;
    }
}
