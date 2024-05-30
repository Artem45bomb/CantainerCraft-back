package org.cantainercraft.micro.users.repository;


import org.cantainercraft.project.entity.users.Profile;
import org.cantainercraft.project.entity.users.Profile_Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProfileImageRepository extends JpaRepository<Profile_Image, UUID> {



}

