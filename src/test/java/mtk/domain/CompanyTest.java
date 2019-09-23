package mtk.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CompanyTest
{
    private Company company;

    @Before
    public void setUp()
    {
        this.company = new Company("Megadyne, Inc.");
    }

    @After
    public void tearDown()
    {
        this.company = null;
    }

    @Test
    public void createCompany()
    {
        /*
         * TEST SMELL: Mocks are used unnecessarily
         */
        // TODO: Add mocks
        Assert.assertNotNull(this.company);
        System.out.println(this.company.getName());
    }

    @Test
    public void renameCompany()
    {
        String proposedName = "Cybertron Unlimited, Ltd.";
        String renamedName = this.company.renameCompanyAndReturnNewName(proposedName);
        Assert.assertEquals(proposedName, renamedName);
    }

    @Test
    public void addEmployee()
    {
        /*
         * TEST SMELL: Irrelevant assertions
         */
        this.company.addEmployee(new Employee("123", "Dave", 100_000.00));
        Assert.assertTrue(this.company.numberOfEmployees() > 0);

        this.company.addEmployee(new Employee("456", "Bob", 50_000.00));
        Assert.assertTrue(this.company.numberOfEmployees() > 0);
    }

    @Test
    public void everybodyGetsRaise()
    {
        /*
         * TEST SMELL: Calculated expected value
         */
        double increaseBy = 0.1;
        double davesOriginalSalary = 100_000.00;

        this.company.addEmployee(new Employee("123", "Dave",  100_000.00));
        this.company.addEmployee(new Employee("456", "Alice", 120_000.00));
        this.company.addEmployee(new Employee("789", "Bob",   110_000.00));

        this.company.everybodyGetsRaiseBy(increaseBy);

        Employee dave = this.company.findEmployeeById("123");

        Assert.assertEquals(davesOriginalSalary * increaseBy, dave.getSalary(), 0.0001);
    }

    @Test
    public void findEmployeeById()
    {
        /*
         * TEST SMELL: No assertions
         */
        this.company.addEmployee(new Employee("123", "Dave",  100_000.00));
        this.company.addEmployee(new Employee("456", "Alice", 100_000.00));
        this.company.addEmployee(new Employee("789", "Bob",   100_000.00));

        Employee hopefullyDave = this.company.findEmployeeById("123");
        Employee hopefullyNoOne = this.company.findEmployeeById("999");
    }

    @Test
    public void employeeNameChange()
    {
        /*
         * TEST SMELL: using a print/log statement
         */
        this.company.addEmployee(new Employee("123", "Dave",  100_000.00));
        this.company.addEmployee(new Employee("456", "Alice", 100_000.00));
        this.company.addEmployee(new Employee("789", "Bob",   100_000.00));

        Employee employee = this.company.findEmployeeById("123");
        employee.setName("Tom");
        employee = this.company.findEmployeeById("123");
        System.out.println(employee.getName().equals("Tom") ? "PASSED" : "FAILED");
    }
}
