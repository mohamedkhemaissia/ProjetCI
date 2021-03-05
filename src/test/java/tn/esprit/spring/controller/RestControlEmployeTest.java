//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package tn.esprit.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.IEmployeService;

@ExtendWith({SpringExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
class RestControlEmployeTest {
    @MockBean
    private IEmployeService iemployeservice;
    @Autowired
    private MockMvc mockMvc;

    RestControlEmployeTest() {
    }

    @Test
    void ajouterEmploye() throws Exception {
        Employe submittedEmploye = new Employe("Khemaicia", "Med", "kh@med", true, Role.CHEF_DEPARTEMENT);
        Employe expectedEmploye = new Employe(0, "Khemaicia", "Med", "kh@med", true, Role.CHEF_DEPARTEMENT);
        Employe employe = ((IEmployeService)Mockito.doReturn(expectedEmploye).when(this.iemployeservice)).ajouterEmploye((Employe)Mockito.any());
        this.mockMvc.perform(MockMvcRequestBuilders.post("/ajouterEmployer", new Object[0]).contentType(MediaType.APPLICATION_JSON).content(Utils.toJsonString(submittedEmploye))).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(0))).andExpect(MockMvcResultMatchers.jsonPath("$.nom", Matchers.is("Khemaicia")));
    }

    @Test
    public void deleteEmployeById() throws Exception {
        Employe mocEmploye = new Employe("kallel", "khaled", "Khaled.kallel@ssiiconsulting.tn", true, Role.INGENIEUR);
        mocEmploye.setId(1);
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/deleteEmployeById/1", new Object[0])).andExpect(MockMvcResultMatchers.status().isOk());
    }

    static String asJsonString(Object o) {
        try {
            return (new ObjectMapper()).writeValueAsString(o);
        } catch (JsonProcessingException var2) {
            throw new RuntimeException(var2);
        }
    }
}
