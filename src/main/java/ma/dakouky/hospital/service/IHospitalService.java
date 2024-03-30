package ma.dakouky.hospital.service;

import ma.dakouky.hospital.entities.Consultation;
import ma.dakouky.hospital.entities.Medecin;
import ma.dakouky.hospital.entities.Patient;
import ma.dakouky.hospital.entities.RendezVous;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRDV(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}
