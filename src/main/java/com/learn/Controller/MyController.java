package com.learn.Controller;

import com.learn.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@RequestMapping("/api")
@RestController
public class MyController {

    private final SpringTemplateEngine templateEngine;
    private final SpringTemplateEngine templateService;
    private static final Logger log = LoggerFactory.getLogger(MyController.class);

    public MyController(SpringTemplateEngine templateEngine, SpringTemplateEngine templateService) {
        this.templateEngine = templateEngine;
        this.templateService = templateService;
    }

    @GetMapping("/index")
    public String getData() {
        NoteTemplate note = new NoteTemplate();
        note.setNoteTemplateContent("<b>This note is for [(${patient.name})] and the practitioner writing this note is [(${practitioner.name})]</b> <br/>" +
                "<b> Note: <i>[(${patient.otherPatientDetails})] and practitioner [(${practitioner.speciality})] </i> </b>");

        Practitioner practitioner = new Practitioner();
        practitioner.setName("Dr. Dread");
        practitioner.setSpeciality("Starred in Teen Wolf");

        Patient patient = new Patient();
        patient.setName("Michael Corvus");
        patient.setOtherPatientDetails("Patient is a Vampire and Wolf hybrid");

        Context context = new Context();
        context.setVariable("patient", patient);
        context.setVariable("practitioner", practitioner);

        String processedTemplate = templateEngine.process(note.getNoteTemplateContent(), context);

        log.info(processedTemplate);
        return processedTemplate;
    }
}
