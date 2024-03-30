package ma.dakouky.hospital;

import ma.dakouky.hospital.entities.*;
import ma.dakouky.hospital.repositories.ConsultationRepository;
import ma.dakouky.hospital.repositories.MedecinRepository;
import ma.dakouky.hospital.repositories.PatientRepository;
import ma.dakouky.hospital.repositories.RendezVousRepository;
import ma.dakouky.hospital.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
           IHospitalService HospitalService,
           PatientRepository patientRepository,
           RendezVousRepository rendezVousRepository
    , ConsultationRepository consultationRepository
    ,MedecinRepository medecinRepository){
        return args -> {
            Stream.of("Youness","Yassine","zakaria").forEach(name->{
                Patient patient = new Patient();
                patient.setNom(name);
                patient.setDateNaissance(new Date());
                patient.setMalade(false);
                HospitalService.savePatient(patient);
            });
            Stream.of("Must","Karim","yanis").forEach(name->{
                Medecin medecin = new Medecin();
                medecin.setNom(name);
                medecin.setEmail(name+"@gmail.com");
                medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");

                HospitalService.saveMedecin(medecin);
            });

            Patient patient = patientRepository.findById(1L).orElse(null);
            Patient patient1 = patientRepository.findByNom("Youness");

            Medecin medecin = medecinRepository.findByNom("Karim");

            RendezVous rendezVous = new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRDV.PENDING);
            rendezVous.setPatient(patient);
            rendezVous.setMedecin(medecin);

            RendezVous saveDRDV =  HospitalService.saveRDV( rendezVous);
            System.out.println(saveDRDV.getId());


            RendezVous rendezVous1=rendezVousRepository.findAll().get(0);
            Consultation consultation = new Consultation();
            consultation.setDateConsultation(new Date());
            consultation.setRendezVous(rendezVous1);
            consultation.setRapport("Rapport de la consultation ...");

            HospitalService.saveConsultation(consultation);

        };
}
}
