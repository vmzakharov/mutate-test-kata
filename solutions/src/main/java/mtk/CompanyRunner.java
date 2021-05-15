package mtk;

import mtk.domain.CompanyFixed;
import mtk.domain.EmployeeFixed;

public class CompanyRunner
{
    public static void main(String[] args)
    {
        CompanyFixed company = new CompanyFixed("Schnitzels and Bits");

        System.out.println("Welcome to our company, " + company.getName());

        company.setName("Bob's Bicycle Repair");

        System.out.println("Renamed the company to " + company.getName());

        company.addEmployee(new EmployeeFixed("001", " Alice", 100_000.00));
        company.addEmployee(new EmployeeFixed("002", "Bob",    120_000.00));
        company.addEmployee(new EmployeeFixed("003", "Carl",    80_000.00));
        company.addEmployee(new EmployeeFixed("004", "Bob ",    90_000.00));

        System.out.println("There are " + company.numberOfEmployees() + " employees at the company");

        company.addEmployee(new EmployeeFixed("005", "Billy Bob", 70_000.00));
        company.addEmployee(new EmployeeFixed("006", "Anna Lee",  90_000.00));

        System.out.println("Welcome " + company.findEmployeeById("005").getName() + " and "
                + company.findEmployeeById("006").getName() + " to the company");

        System.out.println("Now there are " + company.numberOfEmployees() + " employees at the company");

        System.out.println("Time for a pay raise for everyone!");

        EmployeeFixed bob = company.findEmployeeById("002");
        System.out.printf("%s's salary before the raise is %,.2f\n",  bob.getName(), bob.getSalary());

        company.everybodyGetsRaiseBy(0.1);

        System.out.printf("%s's salary after the raise is %,.2f\n",  bob.getName(), bob.getSalary());
    }
}
