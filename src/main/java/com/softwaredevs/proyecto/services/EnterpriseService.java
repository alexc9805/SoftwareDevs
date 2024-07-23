package com.softwaredevs.proyecto.services;

import com.softwaredevs.proyecto.entities.Enterprise;
import com.softwaredevs.proyecto.repositories.EnterpriseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnterpriseService {
    private EnterpriseRepository enterpriseRepository;
    public EnterpriseService(EnterpriseRepository enterpriseRepository){
        this.enterpriseRepository=enterpriseRepository;
    }
    public Enterprise createEnterprise(Enterprise enterprise){
        enterprise.setCreateAt(LocalDate.now());
        return this.enterpriseRepository.save(enterprise);
    }
    public Enterprise getEnterpriseId(long id){
        Optional<Enterprise> enterprise= this.enterpriseRepository.findById(id);
        return enterprise.orElse(null);
    }
    public List<Enterprise> getEnterpriseList(){
        return this.enterpriseRepository.findAll();
    }
    public Boolean removeEnterprise(long id){
        try{
            this.enterpriseRepository.deleteById(id);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
    public String modifyEnterprise(long id, Enterprise enterprise){
        Optional<Enterprise> dbData=this.enterpriseRepository.findById(id);
        if(dbData.isPresent()){
            Enterprise e = dbData.get();
            e=enterprise;
            e.setId(dbData.get().getId());
            e.setCreateAt(dbData.get().getCreateAt());
            e.setUpdateAt(LocalDate.now());
            this.enterpriseRepository.save(e);
            return "Empresa modificada con éxito.";
        }
        return "No se pudo modificar la empresa";
    }
}