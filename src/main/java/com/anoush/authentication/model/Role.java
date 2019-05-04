package com.anoush.authentication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    private String id;

    private RoleName name;

    public Role(RoleName name) {
        this.name = name;
    }
}