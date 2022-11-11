package gr.codehub.actorsdb.service;

import gr.codehub.actorsdb.model.Department;
import gr.codehub.actorsdb.repository.DepartmentRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DepartmentService {

    private DepartmentRepository repository = null;

    public DepartmentService(Connection connection){
        repository = new DepartmentRepository(connection);
    }

    public Department findDepartmentWithId(int departmentId) throws SQLException {
        return repository.getDepartment(2);
    }

    public List<Department> findAllDepartments() throws SQLException {
        return repository.getAllDepartments();
    }

    public Department createDepartment(int departmentId, String name, String numOfTrucks, String sql) throws SQLException {
        return repository.createDepartment(departmentId, name, numOfTrucks, sql);

    }
    public void updateDepartment(int departmentId, String departmentName ,String sql) throws SQLException {
        repository.updateDepartment(departmentId, departmentName, sql);
    }

    public void deleteDepartment(int departmentId) throws SQLException {
        repository.deleteDepartment(departmentId);
    }
}
