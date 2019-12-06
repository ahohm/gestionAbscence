package com.aho.gestionabscence.batchBean;

import com.aho.gestionabscence.dao.AbscenceDao;
import com.aho.gestionabscence.dao.EtudiantDao;
import com.aho.gestionabscence.dao.MatiereDao;
import com.aho.gestionabscence.model.Abscence;
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
    private MatiereDao matiereDao;

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private AbscenceDao abscenceDao;


    @Autowired
    private MailConfig mailConfig;




    /*@Scheduled(cron = "0 17 * * FRI")*/
    @Scheduled(cron = "0 * * * * *")
    public void calculateAbscence(){
        logger.info("Student => {}",etudiantDao.findAll().size());
        List<Etudiant> etudiants = etudiantDao.findAll();

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.mailConfig.getHost());
        mailSender.setPort(this.mailConfig.getPort());
        mailSender.setUsername(this.mailConfig.getUsername());
        mailSender.setPassword(this.mailConfig.getPassword());




        for ( Etudiant etudiant: etudiants) {
//            logger.error(etudiant.getNom()+" "+etudiant.getPrenom());

            List<Matiere> matieres = matiereDao.findAllByClasses(etudiant.getClasse());
//            logger.warn("matiers ::"+matieres.size()+"");

            for (Matiere matiere:matieres) {
//                logger.info("matier :: " + matiere.getLibel());
                List<Abscence> abscences = abscenceDao.findAllByMatiereAndEtudiant(matiere, etudiant);
            if (abscences.size()*1.5> matiere.getPermit()){
                logger.error("eliminated ::: NAME :: /*"+etudiant.getPrenom()+"*/ MATIERE ::"+matiere.getLibel()+", abscence ::"+abscences.size()*1.5);

                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setFrom("noReply@no.replay");
                mailMessage.setTo(etudiant.getEmail());
                mailMessage.setCc("Elimination");
                mailMessage.setText("mr."+etudiant.getNom()+" "+etudiant.getPrenom()+". \n " +
                        "you have been eliminated from the exam of "+matiere.getLibel()+", \n because " +
                        "you passed the permit number of abscence("+matiere.getPermit()+"), " +
                        "you've done "+abscences.size()*1.5+" hours of abscence. \n we are sorry for you");

                mailSender.send(mailMessage);
            }
            }

        }






    }

}
