package org.cantainercraft.messenger.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageSearchDTO {
    private UUID uuid;
    private Long userId;
    private UUID chatId;

    private Date dateStart;
    private Date dateEnd;
    private String value;

    private Integer pageNumber;
    private Integer pageSize;
    private String sortDirection;
    private String sortColumn;

}
