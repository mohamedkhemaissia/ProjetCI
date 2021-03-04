package tn.esprit.spring.controller;

import org.springframework.stereotype.Controller;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;

import java.util.Date;
import java.util.List;

@Controller
public class IControllerTimesheetImpl {

	private final IEmployeService iemployeservice;
	private final IEntrepriseService ientrepriseservice;
	private final ITimesheetService itimesheetservice;

	public IControllerTimesheetImpl(IEmployeService iemployeservice, IEntrepriseService ientrepriseservice, ITimesheetService itimesheetservice) {
		this.iemployeservice = iemployeservice;
		this.ientrepriseservice = ientrepriseservice;
		this.itimesheetservice = itimesheetservice;
	}

	public int ajouterMission(Mission mission) {
		itimesheetservice.ajouterMission(mission);
		return mission.getId();
	}

	public void affecterMissionADepartement(int missionId, int depId) {
		itimesheetservice.affecterMissionADepartement(missionId, depId);

	}
	public void ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
		itimesheetservice.ajouterTimesheet(missionId, employeId, dateDebut, dateFin);

	}


	public void validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
		itimesheetservice.validerTimesheet(missionId, employeId, dateDebut, dateFin, validateurId);

	}
	public List<Mission> findAllMissionByEmployeJPQL(int employeId) {

		return itimesheetservice.findAllMissionByEmployeJPQL(employeId);
	}


	public List<Employe> getAllEmployeByMission(int missionId) {

		return itimesheetservice.getAllEmployeByMission(missionId);
	}
}
