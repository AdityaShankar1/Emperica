package com.emperica.demo.service;

import com.emperica.demo.model.Employee;
import com.emperica.demo.repository.EmployeeRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final ChatClient chatClient;
    private final EmployeeRepository employeeRepository;

    public AiService(ChatClient.Builder chatClientBuilder, EmployeeRepository employeeRepository) {
        this.chatClient = chatClientBuilder.build();
        this.employeeRepository = employeeRepository;
    }

    public String summarizeEmployee(Long id) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        String promptText = String.format(
                "You are a professional HR assistant. Summarize the following employee's performance data in a concise paragraph. " +
                "Include their accomplishments, productivity score, and recent salary hike. " +
                "DO NOT recommend if they are the best employee and DO NOT rank them against others. " +
                "Just provide a neutral, professional summary.\n\n" +
                "Employee Name: %s\n" +
                "Productivity Score: %s/100\n" +
                "Last Salary Hike: %s%%\n" +
                "Accomplishments: %s\n",
                emp.getEmpName(), 
                emp.getProductivityScore() != null ? emp.getProductivityScore().toString() : "N/A",
                emp.getLastSalaryHike() != null ? String.format("%.1f", emp.getLastSalaryHike()) : "N/A",
                emp.getAccomplishments() != null ? emp.getAccomplishments() : "No accomplishments recorded."
        );

        return chatClient.prompt(new Prompt(promptText)).call().content();
    }
}
