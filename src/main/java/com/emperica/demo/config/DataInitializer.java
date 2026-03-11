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
        seedData();
    }

    private void seedAdmin() {
        String adminUser = System.getenv("ADMIN_USERNAME");
        String adminPass = System.getenv("ADMIN_PASSWORD");

        if (adminUser != null && adminPass != null && !adminUser.isEmpty() && !adminPass.isEmpty()) {
            if (userRepository.findByUsername(adminUser).isEmpty()) {
                User admin = new User(adminUser, passwordEncoder.encode(adminPass));
                userRepository.save(admin);
                System.out.println("Default admin created from .env: " + adminUser);
            }
        }
    }

    private void seedData() {
        Department it = departmentRepository.findByDeptName("IT")
                .orElseGet(() -> {
                    Department d = new Department();
                    d.setDeptName("IT");
                    return departmentRepository.save(d);
                });
        Department hr = departmentRepository.findByDeptName("HR")
                .orElseGet(() -> {
                    Department d = new Department();
                    d.setDeptName("HR");
                    return departmentRepository.save(d);
                });

        updateOrCreateEmployee("John Doe", "john.doe@example.com", it, 85, 15.5, "Developed core backend services and optimized database queries.");
        updateOrCreateEmployee("Jane Smith", "jane.smith@example.com", hr, 92, 12.0, "Spearheaded recruitment drive and improved employee engagement.");

        System.out.println("Sample data initialized/updated.");
    }

    private void updateOrCreateEmployee(String name, String email, Department dept, Integer score, Double hike, String accomplishments) {
        Employee emp = employeeRepository.findByEmail(email)
                .orElse(new Employee());
        emp.setEmpName(name);
        emp.setEmail(email);
        emp.setDepartment(dept);
        emp.setProductivityScore(score);
        emp.setLastSalaryHike(hike);
        emp.setAccomplishments(accomplishments);
        employeeRepository.save(emp);
    }
}
