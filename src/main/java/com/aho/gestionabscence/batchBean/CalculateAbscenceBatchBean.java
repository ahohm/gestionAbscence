package com.aho.gestionabscence.batchBean;

import com.aho.gestionabscence.dao.EtudiantDao;
import com.aho.gestionabscence.dao.MatiereDao;
import com.aho.gestionabscence.model.Classe;
import com.aho.gestionabscence.model.Etudiant;
import com.aho.gestionabscence.model.Matiere;
import com.aho.gestionabscence.service.EtudiantService;
import com.aho.gestionabscence.service.MatiereService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalculateAbscenceBatchBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EtudiantDao etudiantDao;

    @Autowired
    private MatiereDao matiereService;

    @Autowired
    private EtudiantService etudiantService;


    @Autowired
    private MailConfig mailConfig;

    /*@Scheduled(cron = "0 17 * * FRI")*/
    @Scheduled(cron = "0/5 * * * * *")
    public void calculateAbscence(){
        logger.warn("nbr of student is {}",etudiantDao.findAll().size());

        List<Etudiant> etudiants = etudiantDao.findAll();

        for ( Etudiant etudiant: etudiants) {
            List<Classe> classes = null;
            classes.add(etudiant.getClasse());
            List<Matiere> matieres = matiereService.findByClassesEquals(classes);
            for (Matiere matiere:matieres) {

            }
            System.out.println("etudiant");
        }

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.mailConfig.getHost());
        mailSender.setPort(this.mailConfig.getPort());
        mailSender.setUsername(this.mailConfig.getUsername());
        mailSender.setPassword(this.mailConfig.getPassword());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("noReply@no.replay");
        mailMessage.setTo("ahmed.hmayer@gmail.com");
        mailMessage.setCc("Test");
        mailMessage.setText("spring boot test mail sender");

        mailSender.send(mailMessage);



    }

}
