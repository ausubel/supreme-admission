package com.api.supremeAdmision.repository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class CandidateRepository extends JdbcRepository {

    public String enrollCandidate(
            String name,
            String maternalSurname,
            String paternalSurname,
            Integer identificationTypeId,
            String identification,
            String gender,
            LocalDate birthDate,
            String civilStatus,
            Integer childrenCount,
            String provenanceCountry,
            LocalDate registrationDate,
            Integer originDistrictId,
            String originInstitutionType,
            String dniAttachment,
            String studyCertificateAttachment,
            String compromiseStudyCertificateAttachment,
            String criminalRecordAttachment,
            String veracityDeclarationAttachment,
            Integer careerId) {
        
        return jdbcTemplate.queryForObject(
                "CALL enroll_candidate(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                String.class,
                name,
                maternalSurname,
                paternalSurname,
                identificationTypeId,
                identification,
                gender,
                birthDate,
                civilStatus,
                childrenCount,
                provenanceCountry,
                registrationDate,
                originDistrictId,
                originInstitutionType,
                dniAttachment,
                studyCertificateAttachment,
                compromiseStudyCertificateAttachment,
                criminalRecordAttachment,
                veracityDeclarationAttachment,
                careerId);
    }
}
