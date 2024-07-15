package org.cantainercraft.messenger.controller.graphql;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.messenger.dto.ProfileDTO;
import org.cantainercraft.messenger.service.ProfileService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GProfileController {
    private final ProfileService profileService;
    @MutationMapping
    public ProfileDTO updateProfile(@Argument ProfileDTO input){
        return profileService.update(input);
    }
}
