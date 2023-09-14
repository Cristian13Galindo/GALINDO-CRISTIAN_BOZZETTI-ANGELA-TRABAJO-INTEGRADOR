package com.backEnd.clinicaOdontologica.controller;

import com.backEnd.clinicaOdontologica.entity.Paciente;
import com.backEnd.clinicaOdontologica.exception.ResourceNotFoundException;
import com.backEnd.clinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    //
    @Autowired
    private PacienteService pacienteService = new PacienteService();


    //
    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(paciente.getId());
        if (pacienteBuscado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Paciente Acualizado: " + paciente.getNombre());
        } else {
            return ResponseEntity.badRequest().body("No se encontro para actualizar el paciente: " + paciente.getNombre() + " Con ID:" + paciente.getId());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
        if (pacienteBuscado.isPresent()) {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Paciente eliminado con exito");
        } else {
            throw new ResourceNotFoundException("No existe el id con el paciente asociado");
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodosPacientes(){
        return ResponseEntity.ok(pacienteService.obtenerTodos());
    }
}

