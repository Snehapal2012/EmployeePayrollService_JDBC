package com.employeepayrollservicejdbc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static com.employeepayrollservicejdbc.EmployeePayrollService.IOService.DB_IO;
import static com.employeepayrollservicejdbc.EmployeePayrollService.IOService.FILE_IO;
public class EmployeePayrollServiceTest {

        @Test
    public void givenEmployeePayrollDB_WhenRetrieved_ShouldMatchEmployeeCount(){
        EmployeePayrollService employeePayrollService=new EmployeePayrollService();
            List<EmployeePayrollData> employeePayrollData=employeePayrollService.readEmployeePayrollData(DB_IO);
            System.out.println(employeePayrollData);
            Assertions.assertEquals(3,employeePayrollData.size());
        }
    }

