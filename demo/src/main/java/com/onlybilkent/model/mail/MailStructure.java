package com.onlybilkent.model.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MailStructure {

    private String subject;
    private String message;
}
