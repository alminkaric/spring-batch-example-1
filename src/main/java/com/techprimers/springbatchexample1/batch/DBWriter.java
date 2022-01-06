package com.techprimers.springbatchexample1.batch;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techprimers.springbatchexample1.dto.LineDTO;
import com.techprimers.springbatchexample1.model.Department;
import com.techprimers.springbatchexample1.model.Salary;
import com.techprimers.springbatchexample1.model.User;
import com.techprimers.springbatchexample1.repository.UserRepository;

@Component
public class DBWriter implements ItemWriter<LineDTO> {
	
	private static final DateTimeFormatter LOCALDATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private UserRepository userRepository;

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
