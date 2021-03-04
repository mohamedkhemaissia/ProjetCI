package tn.esprit.spring.controller;

import org.springframework.stereotype.Controller;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;

import java.util.Date;
import java.util.List;


@Controller
public class IControllerEmployeImpl  {
	private final IEmployeService iemployeservice;
	private final IEntrepriseService ientrepriseservice;
	private final ITimesheetService itimesheetservice;

	public IControllerEmployeImpl(IEmployeService iemployeservice, IEntrepriseService ientrepriseservice, ITimesheetService itimesheetservice) {
		this.iemployeservice = iemployeservice;
		this.ientrepriseservice = ientrepriseservice;
		this.itimesheetservice = itimesheetservice;
	}

	public int ajouterEmploye(Employe employe)
	{
		iemployeservice.ajouterEmploye(employe);
		return employe.getId();
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		iemployeservice.mettreAjourEmailByEmployeId(email, employeId);

	}

	public void affecterEmployeADepartement(int employeId, int depId) {
		iemployeservice.affecterEmployeADepartement(employeId, depId);

	}



	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		iemployeservice.desaffecterEmployeDuDepartement(employeId, depId);
	}


	public int ajouterContrat(Contrat contrat) {
		iemployeservice.ajouterContrat(contrat);
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId)
	{
		iemployeservice.affecterContratAEmploye(contratId, employeId);
	}


	public String getEmployePrenomById(int employeId) {
		return iemployeservice.getEmployePrenomById(employeId);
	}


	public void deleteEmployeById(int employeId) {
		iemployeservice.deleteEmployeById(employeId);

	}
	public void deleteContratById(int contratId) {
		iemployeservice.deleteContratById(contratId);
	}


	public int getNombreEmployeJPQL() {

		return iemployeservice.getNombreEmployeJPQL();
	}


	public List<String> getAllEmployeNamesJPQL() {

		return iemployeservice.getAllEmployeNamesJPQL();
	}


	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return iemployeservice.getAllEmployeByEntreprise(entreprise);
	}


	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
	iemployeservice.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}


	public void deleteAllContratJPQL() {
		iemployeservice.deleteAllContratJPQL();

	}


	public float getSalaireByEmployeIdJPQL(int employeId) {
		// TODO Auto-generated method stub
		return iemployeservice.getSalaireByEmployeIdJPQL(employeId);
	}


	public Double getSalaireMoyenByDepartementId(int departementId) {
		// TODO Auto-generated method stub
		return iemployeservice.getSalaireMoyenByDepartementId(departementId);
	}


	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return iemployeservice.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}




	public List<Employe> getAllEmployes() {

		return iemployeservice.getAllEmployes();
	}


}
