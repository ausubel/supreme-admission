package com.api.supremeAdmision.service;

import com.api.supremeAdmision.model.ExamKey;
import com.api.supremeAdmision.repository.exam.ExamKeyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.List;

@Service
public class ExamKeyService {

    private static final Logger logger = LoggerFactory.getLogger(ExamKeyService.class);
    private final ExamKeyRepository examKeyRepository;

    @Autowired
    public ExamKeyService(ExamKeyRepository examKeyRepository) {
        this.examKeyRepository = examKeyRepository;
    }

    public List<ExamKey> getExamKeys() {
        return examKeyRepository.getExamKeys();
    }

    public String generateExamKeysCSV() {
        List<ExamKey> examKeys = getExamKeys();
        
        if (examKeys.isEmpty()) {
            logger.warn("No se encontraron claves de examen para generar el CSV");
            return "";
        }
        
        logger.info("Generando CSV para {} claves de examen", examKeys.size());
        
        StringWriter stringWriter = new StringWriter();
        
        // Encabezados CSV
        StringBuilder header = new StringBuilder("TEMA");
        for (int i = 1; i <= 100; i++) {
            header.append(",PREG_").append(String.format("%03d", i));
        }
        stringWriter.append(header.toString()).append("\n");
        
        // Datos
        for (ExamKey key : examKeys) {
            stringWriter.append(key.getTema()).append(",");
            appendValue(stringWriter, key.getPreg001());
            appendValue(stringWriter, key.getPreg002());
            appendValue(stringWriter, key.getPreg003());
            appendValue(stringWriter, key.getPreg004());
            appendValue(stringWriter, key.getPreg005());
            appendValue(stringWriter, key.getPreg006());
            appendValue(stringWriter, key.getPreg007());
            appendValue(stringWriter, key.getPreg008());
            appendValue(stringWriter, key.getPreg009());
            appendValue(stringWriter, key.getPreg010());
            appendValue(stringWriter, key.getPreg011());
            appendValue(stringWriter, key.getPreg012());
            appendValue(stringWriter, key.getPreg013());
            appendValue(stringWriter, key.getPreg014());
            appendValue(stringWriter, key.getPreg015());
            appendValue(stringWriter, key.getPreg016());
            appendValue(stringWriter, key.getPreg017());
            appendValue(stringWriter, key.getPreg018());
            appendValue(stringWriter, key.getPreg019());
            appendValue(stringWriter, key.getPreg020());
            appendValue(stringWriter, key.getPreg021());
            appendValue(stringWriter, key.getPreg022());
            appendValue(stringWriter, key.getPreg023());
            appendValue(stringWriter, key.getPreg024());
            appendValue(stringWriter, key.getPreg025());
            appendValue(stringWriter, key.getPreg026());
            appendValue(stringWriter, key.getPreg027());
            appendValue(stringWriter, key.getPreg028());
            appendValue(stringWriter, key.getPreg029());
            appendValue(stringWriter, key.getPreg030());
            appendValue(stringWriter, key.getPreg031());
            appendValue(stringWriter, key.getPreg032());
            appendValue(stringWriter, key.getPreg033());
            appendValue(stringWriter, key.getPreg034());
            appendValue(stringWriter, key.getPreg035());
            appendValue(stringWriter, key.getPreg036());
            appendValue(stringWriter, key.getPreg037());
            appendValue(stringWriter, key.getPreg038());
            appendValue(stringWriter, key.getPreg039());
            appendValue(stringWriter, key.getPreg040());
            appendValue(stringWriter, key.getPreg041());
            appendValue(stringWriter, key.getPreg042());
            appendValue(stringWriter, key.getPreg043());
            appendValue(stringWriter, key.getPreg044());
            appendValue(stringWriter, key.getPreg045());
            appendValue(stringWriter, key.getPreg046());
            appendValue(stringWriter, key.getPreg047());
            appendValue(stringWriter, key.getPreg048());
            appendValue(stringWriter, key.getPreg049());
            appendValue(stringWriter, key.getPreg050());
            appendValue(stringWriter, key.getPreg051());
            appendValue(stringWriter, key.getPreg052());
            appendValue(stringWriter, key.getPreg053());
            appendValue(stringWriter, key.getPreg054());
            appendValue(stringWriter, key.getPreg055());
            appendValue(stringWriter, key.getPreg056());
            appendValue(stringWriter, key.getPreg057());
            appendValue(stringWriter, key.getPreg058());
            appendValue(stringWriter, key.getPreg059());
            appendValue(stringWriter, key.getPreg060());
            appendValue(stringWriter, key.getPreg061());
            appendValue(stringWriter, key.getPreg062());
            appendValue(stringWriter, key.getPreg063());
            appendValue(stringWriter, key.getPreg064());
            appendValue(stringWriter, key.getPreg065());
            appendValue(stringWriter, key.getPreg066());
            appendValue(stringWriter, key.getPreg067());
            appendValue(stringWriter, key.getPreg068());
            appendValue(stringWriter, key.getPreg069());
            appendValue(stringWriter, key.getPreg070());
            appendValue(stringWriter, key.getPreg071());
            appendValue(stringWriter, key.getPreg072());
            appendValue(stringWriter, key.getPreg073());
            appendValue(stringWriter, key.getPreg074());
            appendValue(stringWriter, key.getPreg075());
            appendValue(stringWriter, key.getPreg076());
            appendValue(stringWriter, key.getPreg077());
            appendValue(stringWriter, key.getPreg078());
            appendValue(stringWriter, key.getPreg079());
            appendValue(stringWriter, key.getPreg080());
            appendValue(stringWriter, key.getPreg081());
            appendValue(stringWriter, key.getPreg082());
            appendValue(stringWriter, key.getPreg083());
            appendValue(stringWriter, key.getPreg084());
            appendValue(stringWriter, key.getPreg085());
            appendValue(stringWriter, key.getPreg086());
            appendValue(stringWriter, key.getPreg087());
            appendValue(stringWriter, key.getPreg088());
            appendValue(stringWriter, key.getPreg089());
            appendValue(stringWriter, key.getPreg090());
            appendValue(stringWriter, key.getPreg091());
            appendValue(stringWriter, key.getPreg092());
            appendValue(stringWriter, key.getPreg093());
            appendValue(stringWriter, key.getPreg094());
            appendValue(stringWriter, key.getPreg095());
            appendValue(stringWriter, key.getPreg096());
            appendValue(stringWriter, key.getPreg097());
            appendValue(stringWriter, key.getPreg098());
            appendValue(stringWriter, key.getPreg099());
            // La última pregunta no lleva coma al final
            if (key.getPreg100() == null) {
                stringWriter.append("NULL");
            } else {
                stringWriter.append(key.getPreg100());
            }
            stringWriter.append("\n");
        }
        
        return stringWriter.toString();
    }
    
    private void appendValue(StringWriter writer, String value) {
        if (value == null) {
            writer.append("NULL,");
        } else {
            writer.append(value).append(",");
        }
    }
}
