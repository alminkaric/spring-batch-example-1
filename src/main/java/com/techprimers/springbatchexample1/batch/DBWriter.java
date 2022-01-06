package com.techprimers.springbatchexample1.batch;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.techprimers.springbatchexample1.dto.LineDTO;
import com.techprimers.springbatchexample1.model.Department;
import com.techprimers.springbatchexample1.model.Salary;
import com.techprimers.springbatchexample1.model.User;
import com.techprimers.springbatchexample1.repository.UserRepository;
import com.techprimers.springbatchexample1.validation.DateFormatValidator;

@Component
@Scope("prototype")
public class DBWriter implements ItemWriter<LineDTO> {
	
	private static final DateTimeFormatter LOCALDATE_FORMATTER = DateFormatValidator.LOCALDATE_FORMATTER;

    private UserRepository userRepository;
    
    private List<LineDTO> invalidLines;

    @Autowired
    public DBWriter (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void write(List<? extends LineDTO> lineDTOs) throws Exception{
    	// organize user by employee id
    	final Map<Integer, User> userByEmplyeeId = lineDTOs.stream()
    			.collect(Collectors.toMap(
    					LineDTO::getEmpId, 
    					this::initUser,
    					(a, b) -> b));

    	// add salaries
    	for (LineDTO lineDTO : lineDTOs) {
    		final User user = userByEmplyeeId.get(lineDTO.getEmpId());
    		final Salary salary = new Salary();
			salary.setAmount(Float.valueOf(lineDTO.getSalary()));
			salary.setDate(LocalDate.parse(lineDTO.getSalaryDate(), LOCALDATE_FORMATTER));
			user.addSalary(salary);
    	}

    	final Collection<User> users = userByEmplyeeId.values();
        System.out.println("Data Saved for users: " + users);
        userRepository.saveAll(users);
        
        logInvalidLines();
        logAverageSalaryPerUser(users);
    }

	public void setInvalidLines(List<LineDTO> invalidLines) {
		this.invalidLines = invalidLines;
	}
    
	private void logInvalidLines() {
        System.out.println("### number of errors " + invalidLines.size());
        System.out.println("### lines with errors: ");
        invalidLines.forEach(line -> System.out.println("### " + line.getRawLine()));
	}

	private void logAverageSalaryPerUser(Collection<User> users) {
		final Map<User, Float> averageSalaryPerUser = calculateAverageSalaryPerUser(users).entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(
						Entry::getKey, 
						Map.Entry::getValue));
		
		System.out.println("### Average salary per employee:");
		for (Entry<User, Float> entry : averageSalaryPerUser.entrySet()) {
			final User user = entry.getKey();
			final Float salary = entry.getValue();
			System.out.println("### " + user.getFullName() + ": " + salary + "EUR");
		}
	}

	
	private Map<User, Float> calculateAverageSalaryPerUser(Collection<User> users) {
		return users.stream()
				.collect(Collectors.toMap(
						user -> user, 
						this::calculateAverageSalaryOfUser));
	}
	
	private Float calculateAverageSalaryOfUser(User user) {
		final Float salarySum = user.getSalaries()
				.stream()
				.map(Salary::getAmount)
				.reduce(0f, Float::sum);
		
		return salarySum / user.getSalaries().size();
	}
    
    private User initUser(LineDTO lineDTO) {
		final User newUser = new User();
		newUser.setEmployeeId(lineDTO.getEmpId());
		newUser.setFirstName(lineDTO.getFirstName());
		newUser.setLastName(lineDTO.getLastName());
		newUser.setDepartment(Department.valueFromId(lineDTO.getDeptId()));
		
		return newUser;
    }

}
