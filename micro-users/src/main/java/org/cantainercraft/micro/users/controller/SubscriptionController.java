package org.cantainercraft.micro.users.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.SubscriptionDTO;
import org.cantainercraft.micro.users.dto.SubscriptionUpdateDTO;
import org.cantainercraft.micro.users.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.cantainercraft.project.entity.users.Subscription;

import java.util.List;

@RestController
@RequestMapping("/api/subs")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService service;

    @Operation(summary = "get subscription",
            description = "get subscription by id",
            tags = {"get"},
            parameters = @Parameter(
                    name = "id",
                    description = "subscription id",
                    schema = @Schema(implementation = Long.class))
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Subscription.class)
                    )),
            @ApiResponse(responseCode = "404",
                    description = "subscription is not exist",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json")),
    })

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Get all subscription",
            tags = {"get","all"})
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Subscription.class))
            ))

    @GetMapping("/all")
    public ResponseEntity<List<Subscription>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Add role",
            tags = {"add"},
            parameters = @Parameter(name="subscription",
                    description = "subscription name",
                    schema = @Schema( implementation = SubscriptionDTO.class))
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Subscription.class))),
            @ApiResponse(responseCode = "409",
                    description = "subscription is exist",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json")),
    })

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Subscription> save(@Valid @RequestBody SubscriptionDTO subscriptionDTO){
        return ResponseEntity.ok(service.save(subscriptionDTO));
    }

    @Operation(summary = "Update subscription",
            description = "Update subscription by id",
            parameters = @Parameter(name = "subscription",
                    schema = @Schema(implementation = SubscriptionUpdateDTO.class)),
            tags = {"update"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "success operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Subscription.class))),
            @ApiResponse(responseCode = "404",
                    description = "subscription is not exist",
                    content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json")),
    })

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Subscription> update(@Valid @RequestBody SubscriptionUpdateDTO subscriptionUpdateDTO){
            return ResponseEntity.ok(service.update(subscriptionUpdateDTO));
    }

    @Operation(summary = "delete subscription",
            description = "delete subscription by id",
            parameters = @Parameter(name = "id",
                    description = "subscription id",
                    schema = @Schema(implementation = Long.class)),
            tags = {"delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "success operation",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "subscription is exist",
                    content = @Content( schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json")),
    })

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id ){
            service.deleteById(id);
    }
}
