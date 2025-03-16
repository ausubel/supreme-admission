package com.api.supremeAdmision.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Candidate {
    private Integer id;
    private String name;
    private String maternalSurname;
    private String paternalSurname;
    private Integer identificationTypeId;
    private String identification;
    private String gender;
    private LocalDate birthDate;
    private String civilStatus;
    private Integer childrenCount;
    private String provenanceCountry;
    private LocalDate registrationDate;
    private Integer originDistrictId;
    private String originInstitutionType;
    private String dniAttachment;
    private String studyCertificateAttachment;
    private String compromiseStudyCertificateAttachment;
    private String criminalRecordAttachment;
    private String veracityDeclarationAttachment;
    private Integer careerId;
    private Integer admisionProcessId;
}
