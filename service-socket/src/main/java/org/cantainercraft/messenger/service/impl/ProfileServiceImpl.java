package org.cantainercraft.messenger.service.impl;


import lombok.RequiredArgsConstructor;
import org.cantainercraft.messenger.dto.ProfileDTO;
import org.cantainercraft.messenger.service.ProfileService;
import org.cantainercraft.messenger.webclient.ProfileClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileClient profileClient;

    @Override
    public ProfileDTO update(ProfileDTO profileDTO) {
        if(profileDTO.getUser().getId() == null) throw new NullPointerException("missed param:user is null");

        return profileClient.update(profileDTO);
    }
}
