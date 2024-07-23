package com.softwaredevs.proyecto.services;

import com.softwaredevs.proyecto.entities.Employee;
import com.softwaredevs.proyecto.entities.Enterprise;
import com.softwaredevs.proyecto.entities.Profile;
import com.softwaredevs.proyecto.repositories.EmployeeRepository;
import com.softwaredevs.proyecto.repositories.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    //Atributos
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EnterpriseRepository enterpriseRepository;
    @Autowired
    ProfileService profileService;

    //COnstructor
    public EmployeeService(EmployeeRepository employeeRepository, EnterpriseRepository enterpriseRepository){
        this.employeeRepository = employeeRepository;
        this.enterpriseRepository = enterpriseRepository;
    }

    //Crear empleado
    public Employee createEmployee (Employee employee) {
        Optional<Enterprise> dbEnterprise = this.enterpriseRepository.findById(employee.getEnterprise().getId());
        if (dbEnterprise.isPresent()) {
            employee.setEnterprise(dbEnterprise.get());
            employee.setCreatedAt(LocalDate.now());
            return this.employeeRepository.save(employee);
        }
        else
            return null;
    }

    //Encontrar Empleado por ID
    public Employee getEmployeeId(long id){
        Optional<Employee> employee= this.employeeRepository.findById(id);
        return employee.orElse(null);

    }

    //Encontrar todos los empleados
    public List<Employee> getEmployeeList(){
        return this.employeeRepository.findAll();
    }


    //Remover empleado
    public boolean removeEmployee(long id){

        try {
            this.employeeRepository.deleteById(id);
            return true;
        }catch ( Exception ex){
            return false;
        }

    }


    public void modifyEmployee(Long id,Employee employeeModify, Profile profileModify){
        Optional<Employee> employeeInDB = employeeRepository.findById(id);
        Profile profileInDB = employeeInDB.get().getProfile();
        employeeModify.setId(employeeInDB.get().getId());
        profileModify.setId(profileInDB.getId());
        employeeRepository.save(employeeModify);
        profileModify.setEmployee(employeeModify);
        profileService.crearProfile(profileModify);

    }
    public Employee getEmployee(Map<String,Object> userData){
        String email= (String) userData.get("email");
        Employee employee= this.employeeRepository.findByEmail(email);
        if(employee!=null){
            //TODO actualizar foto perfil
            String image = (String) userData.get("picture");
            return employee;
        }else{
            return null;
        }
    }
}