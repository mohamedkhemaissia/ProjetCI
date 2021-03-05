//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Employe implements Serializable {
	private static final long serialVersionUID = -1396669830860400871L;
	@Id
	@GeneratedValue(
			strategy = GenerationType.IDENTITY
	)
	private Integer id;
	private String prenom;
	private String nom;
	private String email;
	private boolean isActif;
	@Enumerated(EnumType.STRING)
	private Role role;
	@JsonIgnore
	@ManyToMany(
			mappedBy = "employes",
			fetch = FetchType.EAGER
	)
	private List<Departement> departements;
	@JsonIgnore
	@OneToOne(
			mappedBy = "employe"
	)
	private Contrat contrat;
	@JsonIgnore
	@OneToMany(
			mappedBy = "employe"
	)
	private List<Timesheet> timesheets;

	public Employe() {
	}

	public Employe(Integer id, String prenom, String nom, String email, boolean isActif, Role role) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.isActif = isActif;
		this.role = role;
	}

	public Employe(String nom, String prenom, String email, boolean isActif, Role role) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.isActif = isActif;
		this.role = role;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActif() {
		return this.isActif;
	}

	public void setActif(boolean isActif) {
		this.isActif = isActif;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Departement> getDepartements() {
		return this.departements;
	}

	public void setDepartements(List<Departement> departement) {
		this.departements = departement;
	}

	public Contrat getContrat() {
		return this.contrat;
	}

	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}

	public List<Timesheet> getTimesheets() {
		return this.timesheets;
	}

	public void setTimesheets(List<Timesheet> timesheets) {
		this.timesheets = timesheets;
	}
}
