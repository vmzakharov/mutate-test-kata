package mtk.domain;

import java.util.ArrayList;
import java.util.List;

public class CompanyFixed
{
    private String name = "No name";

    private List<EmployeeFixed> employees = new ArrayList<>();

    public CompanyFixed(String newName)
    {
        this.setName(newName);
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String newName)
    {
        this.name = newName;
    }

    public void addEmployee(EmployeeFixed newEmployee)
    {
        this.employees.add(newEmployee);
    }

    /**
     * Increase every employee's salary by the specified fraction
     * @param incrementAsFraction salary increase as a fraction of the original salary. e.g. if the value of the
     *                            parameter is 0.1, everyone at the company gets a 10% raise
     */
    public void everybodyGetsRaiseBy(double incrementAsFraction)
    {
        this.employees.forEach(e -> e.setSalary(e.getSalary() * (1 + incrementAsFraction)));
    }

    /**
     * Finds an employee by their id
     * @param id the id of the employee to be found
     * @return the employee with the id passed as the parameter or null if no such employee exists
     */
    public EmployeeFixed findEmployeeById(String id)
    {
// TODO: pick the least ugly option
        EmployeeFixed found = this.employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);

        for (int i = 0; i < this.employees.size(); i++)
        {
            if (this.employees.get(i).getId().equals(id))
            {
                return this.employees.get(i);
            }
        }
        return null;
    }

    public int numberOfEmployees()
    {
        return this.employees.size();
    }
}
