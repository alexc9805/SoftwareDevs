package com.softwaredevs.proyecto.repositories;

import com.softwaredevs.proyecto.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
}
