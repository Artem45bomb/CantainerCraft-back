package org.cantainercraft.micro.chats.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.ChatDTO;
import org.cantainercraft.micro.chats.service.MessageService;
import org.cantainercraft.micro.chats.webflux.UserWebClient;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.exception.NotValidateParamException;
import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.User_Emotion;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.cantainercraft.micro.chats.dto.MessageDTO;
import org.cantainercraft.micro.chats.dto.MessageSearchDTO;
import org.cantainercraft.project.entity.chats.Message;


import java.util.*;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService service;
    private final UserWebClient userWebClient;
    private static final String COLUM_ID = "id";

    @Operation(summary = "get all messages",
            description = "we get all message without input params",
            tags = {"get","all"})
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = Message.class))))


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/all")
    public ResponseEntity<List<Message>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "get message",
            description = "we get id, text, isPinned, type, date, userId",
            parameters = @Parameter(name = "id", description = "message id", schema = @Schema(implementation = UUID.class)),
            tags = "get")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Message.class))),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(responseCode = "404",
                    description = "No content",
                    content = @Content(mediaType = "application/json")
            )
    })

    @PostMapping("/uuid")
    public ResponseEntity<Message> findByUUID(@RequestBody UUID uuid) {
        Optional<Message> message = service.findByUuid(uuid);

        if(message.isEmpty()) {
            throw new NotResourceException("No content");
        }
        return ResponseEntity.ok(message.get());
    }

    @Operation(summary = "get message",
            description = "we get id, text, isPinned, type, date, userId",
            parameters = @Parameter(name = "id", description = "user id", schema = @Schema(implementation = Long.class)),
            tags = "get")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            array =@ArraySchema( schema = @Schema(implementation = Message.class)))),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "No content",
                    content = @Content(mediaType = "application/json"))
    })

    @PostMapping("/user")
    public ResponseEntity<List<Message>> findByUserId(@RequestBody Long id) {
       return ResponseEntity.ok(service.findByUserId(id));
    }

    @Operation(parameters = @Parameter(
            name = "message data",
            description = "message includes: uuid, text, type, date, isPinned, user id, chat, user emotions, contents",
            schema = @Schema(implementation = Message.class)),
            summary = "Add message",
            tags = {"add"})
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation =  Message.class)
                    )),
            @ApiResponse(responseCode = "400",
                    content = @Content(
                            mediaType = "application/json"),
                    description = "not valid param"
            ),
            @ApiResponse(responseCode = "404",
                    content = @Content(
                            mediaType = "application/json"),
                    description = "user is not exist"
            )
    })

    @PostMapping("/add")
    public ResponseEntity<Message> save(@Valid @RequestBody MessageDTO messageDTO) {

        if (!userWebClient.userExist(messageDTO.getUserId())) {
            throw new NotResourceException("user is not exist");
        }

        if(messageDTO.getDate() == null) messageDTO.setDate(new Date());

        Message result = service.save(messageDTO);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "delete message",
            description = "delete message",
            parameters = @Parameter(name = "id", description = "message id", schema = @Schema(implementation = UUID.class)),
            tags = {"delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "if the operation is successful, it will return to delete the message",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UUID.class)
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "not is exist",
                    content = @Content(
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "Missed param: id",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    })

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/delete")
    public void delete(@RequestBody UUID uuid){
            service.delete(uuid);
    }

    @Operation(
            summary = "update message",
            parameters = @Parameter(
                    name = "message data",
                    schema = @Schema(implementation = MessageDTO.class)
            ),
            tags = {"update"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MessageDTO.class)
                    )),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = "application/json"),
                    description = "not valid param"),
            @ApiResponse(responseCode = "404",
                    content = @Content(mediaType = "application/json"),
                    description = "message is not exist"),
            @ApiResponse(responseCode = "404",
                    description = "user is not exist",
                    content = @Content(mediaType = "application/json")),
    })
    @PutMapping("/update")
    public ResponseEntity<Message> update(@Valid @RequestBody MessageDTO messageDTO) {
            if(messageDTO.getUuid() == null) {
                throw new NotValidateParamException("Missed param: id");
            }
            if (!userWebClient.userExist(messageDTO.getUserId())) {
                throw new NotResourceException("user is not exist");
            }

            return ResponseEntity.ok(service.update(messageDTO));

    }

    @Operation(summary = "get message",
            description = "get messages by uuid userId, chatId, dateStart, dateEnd, value, pageNumber, pageSize, sortDirection, sortColumn ",
            parameters = @Parameter(name = "search data", schema = @Schema(implementation = MessageSearchDTO.class)),
            tags = {"get","search"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class))
                    )
            ),
            @ApiResponse(responseCode = "400",
            content = @Content(
                    mediaType = "application/json"),
            description = "not valid param"
            )
    })
    @PostMapping("/search")
    public ResponseEntity<List<Message>> findBySearch(@Valid @RequestBody MessageSearchDTO messageSearchDTO){

        UUID uuid = messageSearchDTO.getUuid() == null ? null :messageSearchDTO.getUuid();
        String valueMessage = messageSearchDTO.getValue() == null ? null :messageSearchDTO.getValue();
        Long userId = messageSearchDTO.getUserId() == null ? null : messageSearchDTO.getUserId();
        UUID chatId = messageSearchDTO.getChatId() == null ? null : messageSearchDTO.getChatId();

        Date dateStart = messageSearchDTO.getDateStart()  == null? null: messageSearchDTO.getDateStart();
        Date dateEnd = messageSearchDTO.getDateEnd() == null? null : messageSearchDTO.getDateEnd();

        if (messageSearchDTO.getDateEnd() != null) {

            Calendar calendarEnd = Calendar.getInstance();
            calendarEnd.setTime(messageSearchDTO.getDateEnd());
            calendarEnd.set(Calendar.HOUR_OF_DAY, 23);
            calendarEnd.set(Calendar.MINUTE, 59);
            calendarEnd.set(Calendar.SECOND, 59);
            calendarEnd.set(Calendar.MILLISECOND, 999);

            dateEnd = calendarEnd.getTime(); // записываем конечную дату с 23:59

        }
        if (messageSearchDTO.getDateStart() != null) {

            Calendar calendarStart = Calendar.getInstance();
            calendarStart.setTime(messageSearchDTO.getDateEnd());
            calendarStart.set(Calendar.HOUR_OF_DAY, 0);
            calendarStart.set(Calendar.MINUTE, 0);
            calendarStart.set(Calendar.SECOND, 0);
            calendarStart.set(Calendar.MILLISECOND, 0);

            dateStart = calendarStart.getTime(); // записываем конечную дату с 00:00

        }
        String sortDirection = messageSearchDTO.getSortDirection() == null ? "" : messageSearchDTO.getSortDirection();
        int pageNumber = messageSearchDTO.getPageNumber() == null ? 0 : messageSearchDTO.getPageNumber();
        int pageSize =  messageSearchDTO.getPageSize() == null ? 1 : messageSearchDTO.getPageSize();
        String sortColumn = messageSearchDTO.getSortColumn() == null? "id" :messageSearchDTO.getSortColumn();

        Sort.Direction direction = sortDirection.isEmpty() || sortDirection.equalsIgnoreCase("ASK") ?
                Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction,sortColumn,COLUM_ID);
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize,sort);

        return ResponseEntity.ok(service.findBySearch(dateStart,dateEnd,valueMessage,uuid,userId,chatId,pageRequest).getContent());

    }
}

