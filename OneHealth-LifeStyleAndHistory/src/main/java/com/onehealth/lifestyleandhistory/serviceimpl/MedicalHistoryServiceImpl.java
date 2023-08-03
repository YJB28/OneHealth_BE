package com.onehealth.lifestyleandhistory.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onehealth.lifestyleandhistory.entity.MedicalHistory;
import com.onehealth.lifestyleandhistory.exception.RecordNotFoundException;
import com.onehealth.lifestyleandhistory.repository.MedicalHistoryRepository;
import com.onehealth.lifestyleandhistory.service.MedicalHistoryService;

import java.util.List;

@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

	@Autowired
	private MedicalHistoryRepository medicalHistoryRepository;

	@Override
	public List<MedicalHistory> getAllMedicalHistories() {
		return medicalHistoryRepository.findAll();
	}

	@Override
	public MedicalHistory getMedicalHistoryByRecordId(Long recordId) throws RecordNotFoundException {
		return medicalHistoryRepository.findById(recordId)
				.orElseThrow(() -> new RecordNotFoundException("Medical history not found with recordId: " + recordId));
	}

	@Override
	public MedicalHistory createMedicalHistory(MedicalHistory medicalHistory) {
		return medicalHistoryRepository.save(medicalHistory);
	}

	@Override
	public MedicalHistory updateMedicalHistory(Long recordId, MedicalHistory medicalHistory)
	        throws RecordNotFoundException {
	    MedicalHistory existingMedicalHistory = medicalHistoryRepository.findById(recordId)
	            .orElseThrow(() -> new RecordNotFoundException("Medical history not found with recordId: " + recordId));

	    // Update the properties based on your needs.
	    existingMedicalHistory.setAllergies(medicalHistory.getAllergies());
	    existingMedicalHistory.setCurrentMedication(medicalHistory.getCurrentMedication());
	    existingMedicalHistory.setPastMedication(medicalHistory.getPastMedication());
	    existingMedicalHistory.setChronicDiseases(medicalHistory.getChronicDiseases());
	    existingMedicalHistory.setInjuries(medicalHistory.getInjuries());
	    existingMedicalHistory.setSurgeries(medicalHistory.getSurgeries());

	    return medicalHistoryRepository.save(existingMedicalHistory);
	}


	@Override
	public void deleteMedicalHistoryByRecordId(Long recordId) throws RecordNotFoundException {
		
		if (!medicalHistoryRepository.existsById(recordId)) {
			throw new RecordNotFoundException("MedicalHistory not found with recordId: " + recordId);
		}
		medicalHistoryRepository.deleteById(recordId);
	}

	@Override
	public void deleteMedicalHistoryByPatientIdAndUserId(Long patientId, Long userId) throws RecordNotFoundException {
		List<MedicalHistory> medicalHistories = medicalHistoryRepository.findByPatientIdAndUserId(patientId, userId);
		
		if (medicalHistories.isEmpty()) {
			throw new RecordNotFoundException("MedicalHistory not found with recordId: " + patientId+" "+userId);
		}
		medicalHistoryRepository.deleteAll(medicalHistories);
	}
}
