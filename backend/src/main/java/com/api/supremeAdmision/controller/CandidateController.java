package com.api.supremeAdmision.controller;

import com.api.supremeAdmision.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("/enroll")
    public ResponseEntity<String> enrollCandidate(@RequestBody Map<String, Object> candidateData) {
        try {
            // Validar campos obligatorios
            if (candidateData.get("name") == null || 
                candidateData.get("paternalSurname") == null || 
                candidateData.get("maternalSurname") == null || 
                candidateData.get("identificationTypeId") == null ||
                candidateData.get("identification") == null ||
                candidateData.get("gender") == null ||
                candidateData.get("birthDate") == null ||
                candidateData.get("civilStatus") == null) {
                return ResponseEntity.badRequest().body("Error: Faltan campos obligatorios");
            }
            
            String name = (String) candidateData.get("name");
            String maternalSurname = (String) candidateData.get("maternalSurname");
            String paternalSurname = (String) candidateData.get("paternalSurname");
            Integer identificationTypeId = candidateData.get("identificationTypeId") != null ? 
                                          Integer.valueOf(candidateData.get("identificationTypeId").toString()) : 1;
            String identification = (String) candidateData.get("identification");
            String gender = (String) candidateData.get("gender");
            LocalDate birthDate = LocalDate.parse((String) candidateData.get("birthDate"));
            String civilStatus = (String) candidateData.get("civilStatus");
            Integer childrenCount = candidateData.get("childrenCount") != null ? 
                                   Integer.valueOf(candidateData.get("childrenCount").toString()) : 0;
            String provenanceCountry = (String) candidateData.get("provenanceCountry");
            LocalDate registrationDate = candidateData.get("registrationDate") != null ? 
                                        LocalDate.parse((String) candidateData.get("registrationDate")) : 
                                        LocalDate.now();
            Integer originDistrictId = candidateData.get("originDistrictId") != null ? 
                                      Integer.valueOf(candidateData.get("originDistrictId").toString()) : 1;
            String originInstitutionType = (String) candidateData.get("originInstitutionType");
            String dniAttachment = (String) candidateData.get("dniAttachment");
            String studyCertificateAttachment = (String) candidateData.get("studyCertificateAttachment");
            String compromiseStudyCertificateAttachment = (String) candidateData.get("compromiseStudyCertificateAttachment");
            String criminalRecordAttachment = (String) candidateData.get("criminalRecordAttachment");
            String veracityDeclarationAttachment = (String) candidateData.get("veracityDeclarationAttachment");
            Integer careerId = candidateData.get("careerId") != null ? 
                              Integer.valueOf(candidateData.get("careerId").toString()) : 1;

            String result = candidateRepository.enrollCandidate(
                    name, maternalSurname, paternalSurname, identificationTypeId,
                    identification, gender, birthDate, civilStatus, childrenCount,
                    provenanceCountry, registrationDate, originDistrictId,
                    originInstitutionType, dniAttachment, studyCertificateAttachment,
                    compromiseStudyCertificateAttachment, criminalRecordAttachment,
                    veracityDeclarationAttachment, careerId);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
