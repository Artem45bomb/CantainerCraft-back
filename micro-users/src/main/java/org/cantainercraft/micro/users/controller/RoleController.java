package org.cantainercraft.micro.users.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.RoleDTO;
import org.cantainercraft.micro.users.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.cantainercraft.project.entity.users.Role;


@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@Tag(name = "role",description = "API for Role")
public class RoleController {
    private final RoleService roleService;

    @Operation(summary = "get role",
    description = "get role by id",
    tags = {"get"},
    parameters = @Parameter(
            name = "id",
            description = "Role id",
            required = true,
            schema = @Schema(implementation = Long.class))
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Role.class)
                    )),
            @ApiResponse(responseCode = "404",
                    description = "if role is not exist",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable Long id){
        return ResponseEntity.ok(roleService.findById(id));
    }

    @Operation(summary = "Get all roles",
    tags = {"get","all"})
    @ApiResponse(responseCode = "200",
    content = @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = Role.class))
    ))
    @GetMapping("/all")
    public ResponseEntity<List<Role>> findAll(){
        return ResponseEntity.ok(roleService.findAll());
    }

    @Operation(summary = "Add role",
    tags = {"add"},
    parameters = @Parameter(name="role",
            description = "role name",
            schema = @Schema( implementation = RoleDTO.class))
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Role.class))),
            @ApiResponse(responseCode = "409",
            description = "if role is exist",
            content = @Content(schema = @Schema()))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Role> save(@Valid @RequestBody RoleDTO RoleDTO){
        return ResponseEntity.ok(roleService.save(RoleDTO));
    }

    @Operation(summary = "Update role",
            description = "Update role by id",
            parameters = @Parameter(name = "role",
                    schema = @Schema(implementation = RoleDTO.class)),
            tags = {"update"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "success operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Role.class))),
            @ApiResponse(responseCode = "409",
                    description = "a role with the same name exists",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404",
                    description = "role is not exist",
                    content = @Content(schema = @Schema(implementation = Boolean.class))),
    })
    @PutMapping
    public ResponseEntity<Role> update(@Valid @RequestBody RoleDTO roleDTO){
            return ResponseEntity.ok(roleService.update(roleDTO));
    }

    @Operation(summary = "delete role",
    description = "delete role by id",
    parameters = @Parameter(name = "id",
            description = "role id",
            schema = @Schema(implementation = Long.class)),
    tags = {"delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
            description = "success operation",
            content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
            description = "role is not exist",
            content = @Content( schema = @Schema(implementation = Boolean.class))),
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id ){
            roleService.deleteById(id);
    }

}
