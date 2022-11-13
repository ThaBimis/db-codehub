package gr.codehub.universitydb.model;

public class Student {

    private int studentId;

    private String studentName;
    private int studentDepartment;

    public Student(int studentId, String studentName, int studentDepartment) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentDepartment = studentDepartment;
    }

    public int getStudentId() {
        return studentId;
    }


    public int getStudentDepartment() {
        return studentDepartment;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentDepartment;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentDepartment(int studentDepartment) {
        this.studentDepartment = studentDepartment;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentDepartment=" + studentDepartment +
                '}';
    }
}
