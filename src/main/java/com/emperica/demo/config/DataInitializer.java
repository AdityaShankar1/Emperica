package com.emperica.demo.config;

import com.emperica.demo.model.Department;
import com.emperica.demo.model.Employee;
import com.emperica.demo.model.User;
import com.emperica.demo.repository.DepartmentRepository;
import com.emperica.demo.repository.EmployeeRepository;
import com.emperica.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        seedAdmin();
        if (departmentRepository.count() == 0) {
            seedData();
        }
    }

    private void seedAdmin() {
        String adminUser = System.getProperty("ADMIN_USERNAME");
        String adminPass = System.getProperty("ADMIN_PASSWORD");

        if (adminUser != null && adminPass != null && !adminUser.isEmpty() && !adminPass.isEmpty()) {
            if (userRepository.findByUsername(adminUser).isEmpty()) {
                User admin = new User(adminUser, passwordEncoder.encode(adminPass));
                userRepository.save(admin);
                System.out.println("Default admin created from .env: " + adminUser);
            }
        }
    }

    private void seedData() {
        Department it = new Department();
        it.setDeptName("IT");
        it = departmentRepository.save(it);

        Department hr = new Department();
        hr.setDeptName("HR");
        hr = departmentRepository.save(hr);

        Employee emp1 = new Employee();
        emp1.setEmpName("John Doe");
        emp1.setEmail("john.doe@example.com");
        emp1.setDepartment(it);
        emp1.setProductivityScore(85);
        emp1.setLastSalaryHike(15.5);
        emp1.setAccomplishments("Developed core backend services and optimized database queries.");
        employeeRepository.save(emp1);

        Employee emp2 = new Employee();
        emp2.setEmpName("Jane Smith");
        emp2.setEmail("jane.smith@example.com");
        emp2.setDepartment(hr);
        emp2.setProductivityScore(92);
        emp2.setLastSalaryHike(12.0);
        emp2.setAccomplishments("Spearheaded recruitment drive and improved employee engagement.");
        employeeRepository.save(emp2);

        System.out.println("Sample data initialized.");
    }
}
