package com.backEnd.clinicaOdontologica.service;

import com.backEnd.clinicaOdontologica.entity.Paciente;
import com.backEnd.clinicaOdontologica.entity.Domicilio;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;
    @Test
    @Order(1)
    public void guardarPacienteTest(){
        Paciente pacienteGuardado= new Paciente("Camilo","Galindo","11111", LocalDate.of(2023,9,13),new Domicilio("calle 1",20,"la que sea","la que sea"),"camilo992hotmail.es");
        pacienteService.guardarPaciente(pacienteGuardado);
        assertEquals(1L,pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void listaPacientesTest(){
        List<Paciente> pacientes = pacienteService.obtenerTodos();
        assertEquals(1,pacientes.size());
    }
    @Test
    @Order(3)
    public void actualizarPacienteTest(){
        Paciente pacienteActualizado = new Paciente(1L,"Camilo","Galindo","11111", LocalDate.of(2023,9,13),new Domicilio("calle 1",20,"la que sea","la que sea"),"camilo992@hotmail.es");
        pacienteService.actualizarPaciente(pacienteActualizado);
        Optional<Paciente> paciente= pacienteService.buscarPorId(1L);
        assertEquals("camilo",paciente.get().getNombre());

    }
    @Test
    @Order(4)
    public void eliminarPacienteTest(){
        Long id= 1L;
        pacienteService.eliminarPaciente(id);
        Optional<Paciente> pacienteEliminado= pacienteService.buscarPorId(id);
        assertFalse(pacienteEliminado.isPresent());

    }

}
