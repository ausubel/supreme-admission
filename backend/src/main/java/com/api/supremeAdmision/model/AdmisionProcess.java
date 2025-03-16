package com.api.supremeAdmision.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AdmisionProcess {
    private Integer id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer status;
}
