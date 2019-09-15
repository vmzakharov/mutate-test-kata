package mtk;

import mtk.domain.Company;
import mtk.domain.Employee;

public class CompanyRunner
{
    public static void main(String[] args)
    {
        Company company = new Company("Schnitzels and Bits");

        System.out.println("Welcome to " + company.getName());

        company.renameCompanyAndReturnNewName("Bob's Bicycle Repair");

        System.out.println("Renamed to " + company.getName());

        company.addEmployee(new Employee("001", "Alice", 100_000.00));
        company.addEmployee(new Employee("002", "Bob",   120_000.00));
        company.addEmployee(new Employee("003", "Carl",   80_000.00));
        company.addEmployee(new Employee("004", "Bob",    90_000.00));

        System.out.println("There are " + company.numberOfEmployees() + " employees at the company");

        System.out.println("Time for a pay raise for everyone!");

        Employee bob = company.findEmployeeById("002");
        System.out.printf("%s's salary before the raise is %,.2f\n",  bob.getName(), bob.getSalary());

        company.everybodyGetsRaiseBy(0.1);

        System.out.printf("%s's salary after the raise is %,.2f\n",  bob.getName(), bob.getSalary());
    }
}
