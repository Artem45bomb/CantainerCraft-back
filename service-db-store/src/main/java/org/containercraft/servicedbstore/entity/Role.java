package org.containercraft.servicedbstore.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role_data",schema = "messenger_users")
public class Role implements Serializable {
    @Id
    @Column
    private Long id;

    @Column
    private String role;
}
