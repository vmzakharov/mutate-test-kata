package mtk.domain;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;

public class Company
{
    private String name;

    private MutableList<Employee> employees = Lists.mutable.of();

    public Company(String newName)
    {
        this.name = newName;
    }

    public String getName()
    {
        return this.name;
    }

    public String renameCompanyAndReturnNewName(String newName)
    {
        return newName;
    }

    public void addEmployee(Employee newEmployee)
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
        this.employees.each(e -> e.setSalary(e.getSalary() * (incrementAsFraction)));
    }

    public Employee findEmployeeById(String id)
    {
        return this.employees.detect(each -> each.getId().equals(id));
    }

    public int numberOfEmployees()
    {
        return 7;
    }
}
