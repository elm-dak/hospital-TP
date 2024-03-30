package ma.dakouky.hospital.repositories;

import ma.dakouky.hospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByNom(String name);// 37:55

}
