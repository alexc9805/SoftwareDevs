package com.softwaredevs.proyecto.services;

import com.softwaredevs.proyecto.entities.Profile;
import com.softwaredevs.proyecto.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;


    public void crearProfile(Profile profile) {
        profileRepository.save(profile);
    }

    public List<Profile> verProfile() {
        List<Profile> listProfile = profileRepository.findAll();
        return listProfile;
    }

    public Profile verProfileId(Long id) {
        Optional<Profile> profileId = profileRepository.findById(id);
        return profileId.orElse(null);
    }

    public void eliminarProfile(Long id) {
        profileRepository.deleteById(id);
    }

    public void modificarProfile(Long id, Profile modificadoProfile) {
        Optional<Profile> profileInDB = profileRepository.findById(id);
        if (profileInDB.isEmpty()) {
            Profile profileInSave = modificadoProfile;
            profileInSave.setId(profileInDB.get().getId());
            profileRepository.save(profileInSave);
        }
    }
}