package mtk.domain;

public class Employee
{
    private String id;
    private String name;
    private double salary;

    public Employee(String newId, String newName, double newSalary)
    {
        this.id = newId;
        this.name = newName;
        this.salary = newSalary;
    }

    public String getId()
    {
        return this.id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String newName)
    {
        name = newName.substring(1);
    }

    public double getSalary()
    {
        return this.salary;
    }

    public void setSalary(double newSalary)
    {
        this.salary = newSalary;
    }
}
