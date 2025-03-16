package com.api.supremeAdmision.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Document {
    private String uuid;
    private String base64Content;
    private LocalDateTime registrationDate;
}
