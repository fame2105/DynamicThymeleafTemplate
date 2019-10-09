package com.learn.Controller;

import com.learn.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import javax.swing.*;

@RequestMapping("/api")
@RestController
public class MyController {

    private final SpringTemplateEngine templateEngine;
    private static final Logger log = LoggerFactory.getLogger(MyController.class);

    public MyController(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    // Will use StringTemplateResolver to resolve the template since template is not present in the classpath and thus needs to be resolved dynamically
    @GetMapping("/index")
    public String getData() {
        NoteTemplate note = new NoteTemplate();
        note.setNoteTemplateContent("<b>This note is for [(${patient.name})] and the practitioner writing this note is [(${practitioner.name})]</b> <br/>" +
                "<b> Note: <i>[(${patient.otherPatientDetails})] and practitioner \"[(${practitioner.speciality})]\" </i> </b>");
        note.setNoteTemplateContent("<head><title>[(${patient.name})]</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /><link rel=\"shortcut icon\" href=\"[(${baseUrl})]/favicon.ico\" />\n</head>\n<body>\n<p>[(${practitioner.name})] [(${patient.name})]</p><p>[(${patient.otherPatientDetails})]</p><p><a href=\"[(${baseUrl})]/activate?key=[(${patient.name})]\"> [(${url})] </a></p><p><span>[(${practitioner.speciality})], </span><br/><em>[(${practitioner.name})].</em></p></body></html>");

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

    // Will use ClassLoaderTemplateResolver to resolve the template in this case, since help2.html is present in the classpath
    @GetMapping("/try")
    public String displayStaticContent() {

        Context context = new Context();
        context.setVariable("message", "Fame");

        String processedTemplate = templateEngine.process("help", context);
        return processedTemplate;
    }

    // Will use ClassLoaderTemplateResolver to resolve the template in this case, since help2.html is present in the classpath
    @GetMapping("/try2")
    public String displayStaticContent2() {

        Context context = new Context();
        context.setVariable("message", "Fame");

        String processedTemplate = templateEngine.process("mail/help2", context);
        return processedTemplate;
    }
}
