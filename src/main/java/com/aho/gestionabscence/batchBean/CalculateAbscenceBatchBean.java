package com.aho.gestionabscence.batchBean;

import com.aho.gestionabscence.dao.AbscenceDao;
import com.aho.gestionabscence.dao.EtudiantDao;
import com.aho.gestionabscence.dao.MatiereDao;
import com.aho.gestionabscence.model.*;
import com.aho.gestionabscence.service.AbscenceService;
import com.aho.gestionabscence.service.EliminationService;
import com.aho.gestionabscence.service.EtudiantService;
import com.aho.gestionabscence.service.MatiereService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Component
public class CalculateAbscenceBatchBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());



    @Autowired
    private MatiereService matiereService;

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private AbscenceService abscenceService;

    @Autowired
    private MailConfig mailConfig;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private EliminationService eliminationService;


    /*  check if internet available*/
    public static boolean isInternetAvailable() throws IOException {
        return isHostAvailable("google.com") || isHostAvailable("amazon.com")
                || isHostAvailable("facebook.com")|| isHostAvailable("apple.com");
//            return false;
    }
    private static boolean isHostAvailable(String hostName) throws IOException {
        try(Socket socket = new Socket())
        {
            int port = 80;
            InetSocketAddress socketAddress = new InetSocketAddress(hostName, port);
            socket.connect(socketAddress, 3000);

            return true;
        }
        catch(UnknownHostException unknownHost)
        {
            return false;
        }
    }


    /*@Scheduled(cron = "0 17 * * FRI")*/
    @Scheduled(cron = "0/10 * * * * *")
    public void calculateAbscence() throws IOException {
        List<Etudiant> etudiants = etudiantService.findAll();

//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(this.mailConfig.getHost());
        javaMailSender.setPort(this.mailConfig.getPort());
        javaMailSender.setUsername(this.mailConfig.getUsername());
        javaMailSender.setPassword(this.mailConfig.getPassword());

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");

        /*we can set properties from here*/
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");

        javaMailSender.setJavaMailProperties(javaMailProperties);




        for ( Etudiant etudiant: etudiants) {
//            logger.error(etudiant.getNom()+" "+etudiant.getPrenom());

            List<Matiere> matieres = matiereService.findByClasse(etudiant.getClasse());
//            logger.warn("matiers ::"+matieres.size()+"");

/*** start 1st logic work well*/
//            for (Matiere matiere:matieres) {
//
////                logger.info("matier :: " + matiere.getLibel());
//                List<Abscence> abscences = abscenceService.findAllByMatiereAndEtudiant(matiere, etudiant);
//                if (abscences.size()*1.5> matiere.getPermit()){
//                    logger.debug("eliminated ::: NAME :: /*"+etudiant.getPrenom()+"*/ MATIERE ::"+matiere.getLibel()+", abscence ::"+abscences.size()*1.5);
//
//                    SimpleMailMessage mailMessage = new SimpleMailMessage();
//                    mailMessage.setFrom("noReply@no.replay");
//                    mailMessage.setSubject("Elimination");
//
//                    mailMessage.setCc(etudiant.getEmail());
//                    mailMessage.setText("mr."+etudiant.getNom()+" "+etudiant.getPrenom()+". \n " +
//                            "you have been eliminated from the exam of "+matiere.getLibel()+", \n because " +
//                            "you passed the permit number of abscence("+matiere.getPermit()+"), " +
//                            "you've done "+abscences.size()*1.5+" hours of abscence. \n we are sorry for you");
//
////                mailSender.send(mailMessage);
//                    if(isInternetAvailable()){
//                        javaMailSender.send(mailMessage);
////                    logger.info("ok");
//                    }
//                }
//            }
/*** End 1st logic work well*/


//            for (Matiere matiere: matieres) {
//                if (eliminationService.findEliminatedEtudiant(etudiant).indexOf(matiere)==-1){
//                    List<Matiere> e = eliminationService.findEliminatedEtudiant(etudiant);
//                    logger.error(etudiant.getPrenom()+"is eliminated"+matiere.getLibel()+" hey"+ e.size());
//                    for (int i=0 ; i<e.size();i++ ){
//                        System.out.println(e.get(i).getLibel());
//                    }
//
//                }else{
//                    logger.info(matiere.getLibel()+" checking ");
//                    List<Abscence> abscences = abscenceService.findAllByMatiereAndEtudiant(matiere, etudiant);
//                    if (abscences.size()*1.5> matiere.getPermit()){
//                        logger.debug("eliminated ::: NAME :: /*"+etudiant.getPrenom()+"*/ MATIERE ::"+matiere.getLibel()+", abscence ::"+abscences.size()*1.5);
//
//
//                        eliminationService.save(new Elimination(etudiant,matiere,true));
//
//
//                    SimpleMailMessage mailMessage = new SimpleMailMessage();
//                    mailMessage.setFrom("noReply@no.replay");
//                    mailMessage.setSubject("Elimination");
//
//                    mailMessage.setCc(etudiant.getEmail());
//                    mailMessage.setText("mr."+etudiant.getNom()+" "+etudiant.getPrenom()+". \n " +
//                            "you have been eliminated from the exam of "+matiere.getLibel()+", \n because " +
//                            "you passed the permit number of abscence("+matiere.getPermit()+"), " +
//                            "you've done "+abscences.size()*1.5+" hours of abscence. \n we are sorry for you");
//
////                mailSender.send(mailMessage);
//                    if(isInternetAvailable()){
//                        javaMailSender.send(mailMessage);
////                    logger.info("ok");
//                    }
//                }
//
//                }
//
//            }



        }
    }

}
