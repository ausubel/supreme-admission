package com.api.supremeAdmision.controller;

import com.api.supremeAdmision.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("/enroll")
    public ResponseEntity<String> enrollCandidate(@RequestBody Map<String, Object> candidateData) {
        try {
            String name = (String) candidateData.get("name");
            String maternalSurname = (String) candidateData.get("maternalSurname");
            String paternalSurname = (String) candidateData.get("paternalSurname");
            Integer identificationTypeId = Integer.valueOf(candidateData.get("identificationTypeId").toString());
            String identification = (String) candidateData.get("identification");
            String gender = (String) candidateData.get("gender");
            LocalDate birthDate = LocalDate.parse((String) candidateData.get("birthDate"));
            String civilStatus = (String) candidateData.get("civilStatus");
            Integer childrenCount = Integer.valueOf(candidateData.get("childrenCount").toString());
            String provenanceCountry = (String) candidateData.get("provenanceCountry");
            LocalDate registrationDate = LocalDate.parse((String) candidateData.get("registrationDate"));
            Integer originDistrictId = Integer.valueOf(candidateData.get("originDistrictId").toString());
            String originInstitutionType = (String) candidateData.get("originInstitutionType");
            String dniAttachment = (String) candidateData.get("dniAttachment");
            String studyCertificateAttachment = (String) candidateData.get("studyCertificateAttachment");
            String compromiseStudyCertificateAttachment = (String) candidateData.get("compromiseStudyCertificateAttachment");
            String criminalRecordAttachment = (String) candidateData.get("criminalRecordAttachment");
            String veracityDeclarationAttachment = (String) candidateData.get("veracityDeclarationAttachment");
            Integer careerId = Integer.valueOf(candidateData.get("careerId").toString());

            String result = candidateRepository.enrollCandidate(
                    name, maternalSurname, paternalSurname, identificationTypeId,
                    identification, gender, birthDate, civilStatus, childrenCount,
                    provenanceCountry, registrationDate, originDistrictId,
                    originInstitutionType, dniAttachment, studyCertificateAttachment,
                    compromiseStudyCertificateAttachment, criminalRecordAttachment,
                    veracityDeclarationAttachment, careerId);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
