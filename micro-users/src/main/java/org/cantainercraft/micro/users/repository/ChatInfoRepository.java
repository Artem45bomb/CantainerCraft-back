package org.cantainercraft.micro.users.repository;

import org.cantainercraft.project.entity.users.Chat_Info;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatInfoRepository extends JpaRepository<Chat_Info, UUID> {

}
