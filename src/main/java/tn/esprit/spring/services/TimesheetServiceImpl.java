package tn.esprit.spring.services;

import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
@Service
public class TimesheetServiceImpl implements ITimesheetService {


	private final MissionRepository missionRepository;
	private final DepartementRepository deptRepoistory;
	private final TimesheetRepository timesheetRepository;
	private final EmployeRepository employeRepository;
	private static final  Logger log = LogManager.getLogger(EmployeServiceImpl.class);

	public TimesheetServiceImpl(MissionRepository missionRepository, DepartementRepository deptRepoistory, TimesheetRepository timesheetRepository, EmployeRepository employeRepository) {
		this.missionRepository = missionRepository;
		this.deptRepoistory = deptRepoistory;
		this.timesheetRepository = timesheetRepository;
		this.employeRepository = employeRepository;
	}

	public void ajouterMission(Mission mission) {
		missionRepository.save(mission);
	}

	public void affecterMissionADepartement(int missionId, int depId) {
		Mission mission = missionRepository.findById(missionId).get();
		Departement dep = deptRepoistory.findById(depId).get();
		mission.setDepartement(dep);
		missionRepository.save(mission);
		log.info("Mission affectee ");

	}

	public void ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
		TimesheetPK timesheetPK = new TimesheetPK();
		timesheetPK.setDateDebut(dateDebut);
		timesheetPK.setDateFin(dateFin);
		timesheetPK.setIdEmploye(employeId);
		timesheetPK.setIdMission(missionId);

		Timesheet timesheet = new Timesheet();
		timesheet.setTimesheetPK(timesheetPK);
		timesheet.setValide(false); //par defaut non valide
		timesheetRepository.save(timesheet);
		log.info("TimeSheet ajouté");

	}


	public void validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
		System.out.println("In valider Timesheet");
		Employe validateur = employeRepository.findById(validateurId).get();
		Mission mission = missionRepository.findById(missionId).get();
		//verifier s'il est un chef de departement (interet des enum)
		if(!validateur.getRole().equals(Role.CHEF_DEPARTEMENT)){
			System.out.println("l'employe doit etre chef de departement pour valider une feuille de temps !");
			return;
		}
		//verifier s'il est le chef de departement de la mission en question
		boolean chefDeLaMission = false;
		for(Departement dep : validateur.getDepartements()){
			if(dep.getId() == mission.getDepartement().getId()){
				chefDeLaMission = true;
				break;
			}
		}
		if(!chefDeLaMission){
			log.warn("l'employe doit etre chef de departement de la mission en question");
			return;
		}
//
		TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);
		Timesheet timesheet =timesheetRepository.findBytimesheetPK(timesheetPK);
		timesheet.setValide(true);

		//Comment Lire une date de la base de données
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("dateDebut : " + dateFormat.format(timesheet.getTimesheetPK().getDateDebut()));

	}


	public List<Mission> findAllMissionByEmployeJPQL(int employeId) {
		return timesheetRepository.findAllMissionByEmployeJPQL(employeId);
	}


	public List<Employe> getAllEmployeByMission(int missionId) {
		return timesheetRepository.getAllEmployeByMission(missionId);
	}

}
