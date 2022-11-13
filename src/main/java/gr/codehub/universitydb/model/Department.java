package gr.codehub.universitydb.model;


public class Department {
    private int departmentId;
    private String firstName;
    private String lastName;


    public Department(int departmentId, String firstName, String lastName) {
        this.departmentId = departmentId;
        this.firstName = firstName;
        this.lastName = lastName;


    }

    public int getActorId() {
        return departmentId;
    }

    public void setActorId(int actorId) {
        this.departmentId = actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", DepartmentName='" + firstName + '\'' +
                ", Number of tracks='" + lastName + '\'' +
                '}';
    }
}
