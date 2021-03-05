package tn.esprit.spring.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	private final EntrepriseRepository enterpriseRepository;
	private final DepartementRepository deptRepository;

	public EntrepriseServiceImpl(DepartementRepository deptRepository, EntrepriseRepository enterpriseRepository) {
		this.deptRepository = deptRepository;
		this.enterpriseRepository = enterpriseRepository;
	}

	public Entreprise ajouterEntreprise(Entreprise entreprise) {
		enterpriseRepository.save(entreprise);
        return entreprise;
    }

	public int ajouterDepartement(Departement dep) {
		deptRepository.save(dep);
		return dep.getId();
	}

	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement
		//donc il faut rajouter l'entreprise a departement
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
		Entreprise entrepriseManagedEntity = enterpriseRepository.findById(entrepriseId).get();
		Departement depManagedEntity = deptRepository.findById(depId).get();

				depManagedEntity.setEntreprise(entrepriseManagedEntity);
		deptRepository.save(depManagedEntity);

	}

	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Entreprise entrepriseManagedEntity = enterpriseRepository.findById(entrepriseId).get();
		List<String> depNames = new ArrayList<>();
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}

		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		enterpriseRepository.delete(enterpriseRepository.findById(entrepriseId).get());
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		deptRepository.delete(deptRepository.findById(depId).get());
	}


	public Entreprise getentreprisebyid(int enterpriseId) {
		return enterpriseRepository.findById(enterpriseId).get();
	}

}
