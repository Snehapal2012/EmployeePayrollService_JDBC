package com.employeepayrollservicejdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {
    public EmployeePayrollService() {

    }

    public enum IOService{
        CONSOLE_IO,FILE_IO,DB_IO,REST_IO
    }
    private ArrayList<EmployeePayrollData> employeePayrollList;

    public EmployeePayrollService(ArrayList<EmployeePayrollData> employeePayrollList){
        this.employeePayrollList=employeePayrollList;
    }
    public void readEmployeePayrollData(Scanner inputReader){
        System.out.print("Enter Employee ID: ");
        int id=inputReader.nextInt();
        System.out.print("Enter Employee name: ");
        String name=inputReader.next();
        System.out.print("Enter Employee salary: ");
        Double salary=inputReader.nextDouble();
        employeePayrollList.add(new EmployeePayrollData(id,name,salary));
    }
    public void writeEmployeePayrollData(IOService ioService){
        if (ioService.equals(IOService.CONSOLE_IO))
            System.out.println("Employee Payroll Data to Console\n" + employeePayrollList);
        else if (ioService.equals(IOService.FILE_IO))
            new EmployeePayrollFileIOService().writeData(employeePayrollList);
    }
    public void printData(IOService ioService) {
        new EmployeePayrollFileIOService().printData();
    }
    public long countEntries(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            return new EmployeePayrollFileIOService().countEntries();
        return 0;
    }
    public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService) {
        if (ioService.equals(IOService.DB_IO))
            this.employeePayrollList= (ArrayList<EmployeePayrollData>) new EmployeePayrollDbService().readData();
        return this.employeePayrollList;
    }

    public static void main(String[] args) {
        ArrayList<EmployeePayrollData> employeePayrollDataArrayList=new ArrayList<>();
        EmployeePayrollService employeePayrollService=new EmployeePayrollService(employeePayrollDataArrayList);
        Scanner inputReader=new Scanner(System.in);
        employeePayrollService.readEmployeePayrollData(inputReader);
        employeePayrollService.writeEmployeePayrollData(IOService.CONSOLE_IO);
    }
}
