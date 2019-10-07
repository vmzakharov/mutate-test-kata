package mtk.domain;

public class EmployeeFixed
{
    private String id;
    private String name;
    private double salary;

    public EmployeeFixed(String newId, String newName, double newSalary)
    {
        this.id = newId;
        this.setName(newName);
        this.setSalary(newSalary);
    }

    public String getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    /**
     * Set the employee name after removing leading and trailing spaces, which could be left by upstream system
     * @param newName the new name for the employee, possibly with leading and trailing white space to be removed
     */
    public void setName(String newName)
    {
        this.name = newName.trim();
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
