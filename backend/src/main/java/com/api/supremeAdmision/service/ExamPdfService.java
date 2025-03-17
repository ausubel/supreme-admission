package com.api.supremeAdmision.service;

import com.api.supremeAdmision.model.Exam;
import com.api.supremeAdmision.model.Question;
import com.api.supremeAdmision.model.QuestionExam;
import com.api.supremeAdmision.repository.DocumentRepository;
import com.api.supremeAdmision.repository.exam.ExamRepository;
import com.api.supremeAdmision.repository.question.QuestionRepository;
import com.api.supremeAdmision.repository.questionexam.QuestionExamRepository;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class ExamPdfService {

    private static final Logger logger = LoggerFactory.getLogger(ExamPdfService.class);

    private final ExamRepository examRepository;
    private final QuestionExamRepository questionExamRepository;
    private final QuestionRepository questionRepository;
    private final DocumentRepository documentRepository;

    @Autowired
    public ExamPdfService(ExamRepository examRepository, 
                         QuestionExamRepository questionExamRepository,
                         QuestionRepository questionRepository,
                         DocumentRepository documentRepository) {
        this.examRepository = examRepository;
        this.questionExamRepository = questionExamRepository;
        this.questionRepository = questionRepository;
        this.documentRepository = documentRepository;
    }

    /**
     * Genera un PDF para un examen específico y lo guarda en la base de datos
     * @param examId ID del examen para el cual generar el PDF
     * @return ID del documento generado
     */
    @Transactional
    public String generateExamPdf(int examId) {
        logger.info("Generando PDF para el examen con ID: {}", examId);
        
        try {
            // Obtener el examen
            Exam exam = examRepository.findById(examId);
            if (exam == null) {
                logger.error("No se encontró el examen con ID: {}", examId);
                throw new RuntimeException("Examen no encontrado");
            }
            
            // Obtener las preguntas del examen
            List<QuestionExam> questionExams = questionExamRepository.findByExamId(examId);
            
            // Cargar los detalles de cada pregunta
            for (QuestionExam qe : questionExams) {
                Question question = questionRepository.findById(qe.getQuestionId());
                qe.setQuestion(question);
            }
            
            // Ordenar las preguntas por número de orden
            questionExams.sort(Comparator.comparingInt(QuestionExam::getOrderNumber));
            
            // Generar el PDF
            byte[] pdfBytes = createPdf(exam, questionExams);
            
            // Guardar el PDF en la base de datos
            String documentId = UUID.randomUUID().toString();
            documentRepository.save(documentId, java.util.Base64.getEncoder().encodeToString(pdfBytes), new java.sql.Date(System.currentTimeMillis()));
            
            // Actualizar el examen con el ID del documento
            exam.setDocumentId(documentId);
            examRepository.update(exam);
            
            logger.info("PDF generado exitosamente para el examen ID: {}, documento ID: {}", examId, documentId);
            return documentId;
            
        } catch (Exception e) {
            logger.error("Error al generar PDF para el examen ID: {}", examId, e);
            throw new RuntimeException("Error al generar PDF: " + e.getMessage(), e);
        }
    }
    
    /**
     * Obtiene el contenido del PDF de un examen
     * @param examId ID del examen
     * @return Arreglo de bytes con el contenido del PDF
     */
    public byte[] getExamPdfContent(int examId) {
        logger.info("Obteniendo contenido del PDF para el examen ID: {}", examId);
        
        try {
            // Obtener el examen
            Exam exam = examRepository.findById(examId);
            if (exam == null) {
                logger.error("No se encontró el examen con ID: {}", examId);
                throw new RuntimeException("Examen no encontrado");
            }
            
            // Verificar si el examen ya tiene un documento asociado
            String documentId = exam.getDocumentId();
            if (documentId == null || documentId.isEmpty()) {
                // Si no tiene documento, generarlo
                documentId = generateExamPdf(examId);
            }
            
            // Obtener el contenido del documento
            String base64Content = documentRepository.findContentById(documentId);
            if (base64Content == null) {
                logger.error("No se encontró el documento con ID: {}", documentId);
                throw new RuntimeException("Documento no encontrado");
            }
            
            return java.util.Base64.getDecoder().decode(base64Content);
            
        } catch (Exception e) {
            logger.error("Error al obtener el contenido del PDF para el examen ID: {}", examId, e);
            throw new RuntimeException("Error al obtener el contenido del PDF: " + e.getMessage(), e);
        }
    }
    
    /**
     * Crea un documento PDF con las preguntas del examen
     * @param exam Examen para el cual crear el PDF
     * @param questionExams Lista de preguntas del examen
     * @return Arreglo de bytes con el contenido del PDF
     * @throws DocumentException Si hay un error al crear el documento
     * @throws IOException Si hay un error de E/S
     */
    private byte[] createPdf(Exam exam, List<QuestionExam> questionExams) throws DocumentException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, baos);
        
        document.open();
        
        // Configurar fuentes con soporte para caracteres especiales
        BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED);
        Font titleFont = new Font(baseFont, 16, Font.BOLD);
        Font normalFont = new Font(baseFont, 12);
        
        // Título del examen
        Paragraph title = new Paragraph("EXAMEN TIPO " + exam.getType() + " - ÁREA " + exam.getAreaId(), titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);
        
        // Instrucciones
        Paragraph instructions = new Paragraph("Instrucciones: Lea cuidadosamente cada pregunta y marque la alternativa correcta.", normalFont);
        document.add(instructions);
        document.add(Chunk.NEWLINE);
        
        // Preguntas
        for (QuestionExam qe : questionExams) {
            Question q = qe.getQuestion();
            if (q == null) continue;
            
            // Número y descripción de la pregunta
            Paragraph questionText = new Paragraph(qe.getOrderNumber() + ". " + q.getDescription(), normalFont);
            document.add(questionText);
            document.add(Chunk.NEWLINE);
            
            // Alternativas
            document.add(new Paragraph("a) " + q.getAlternativeA(), normalFont));
            document.add(new Paragraph("b) " + q.getAlternativeB(), normalFont));
            document.add(new Paragraph("c) " + q.getAlternativeC(), normalFont));
            document.add(new Paragraph("d) " + q.getAlternativeD(), normalFont));
            
            document.add(Chunk.NEWLINE);
        }
        
        document.close();
        return baos.toByteArray();
    }
}
