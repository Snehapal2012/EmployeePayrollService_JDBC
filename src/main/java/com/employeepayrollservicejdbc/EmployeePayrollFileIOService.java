package com.employeepayrollservicejdbc;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollFileIOService {
    public static String FILE_NAME ="C:\\Users\\DELL\\IdeaProjects\\EmployeePayrollServiceJDBC\\src\\main\\resources\\EmployeePayrollService.txt";
    public void writeData(ArrayList<EmployeePayrollData> employeePayrollDataList) {
        StringBuffer empBuffer = new StringBuffer();
        employeePayrollDataList.forEach(employee -> {
            String employeeData = employee.toString().concat("\n");
            empBuffer.append(employeeData);
        });
        try {
            Files.write(Paths.get(FILE_NAME), empBuffer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void printData() {
        try {
            Files.lines(new File(FILE_NAME).toPath()).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public long countEntries() {
        long entries = 0;
        try {
            entries = Files.lines(new File(FILE_NAME).toPath()).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }
    public List<EmployeePayrollData> readData() {
        List<EmployeePayrollData> employeePayrollDataList = new ArrayList<EmployeePayrollData>();
        try {
            Files.lines(new File(FILE_NAME).toPath()).map(line -> line.trim()).forEach(line -> {
                String data = line.toString();
                String[] dataArray = data.split(",");
                for (int i = 0; i < dataArray.length; i++) {
                    int id = Integer.parseInt(dataArray[i].replaceAll("id =", ""));
                    i++;
                    String name = dataArray[i].replaceAll("name =", "");
                    i++;
                    double salary = Double.parseDouble(dataArray[i].replaceAll("salary =", ""));
                    EmployeePayrollData employeePayrollData = new EmployeePayrollData(id, name, salary);
                    employeePayrollDataList.add(employeePayrollData);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeePayrollDataList;
    }
}
