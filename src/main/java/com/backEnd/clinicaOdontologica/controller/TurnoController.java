package com.backEnd.clinicaOdontologica.controller;

import com.backEnd.clinicaOdontologica.dto.TurnoDTO;
import com.backEnd.clinicaOdontologica.entity.Odontologo;
import com.backEnd.clinicaOdontologica.entity.Paciente;
import com.backEnd.clinicaOdontologica.entity.Turno;
import com.backEnd.clinicaOdontologica.service.OdontologoService;
import com.backEnd.clinicaOdontologica.service.PacienteService;
import com.backEnd.clinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    //ATRIBUTOS
    private TurnoService turnoService;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;


    //@
    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarTodosLosTurnos(){

        return ResponseEntity.ok(turnoService.listarTodos());
    }
    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody Turno turno){
        Optional<Odontologo> odontologoABuscar= odontologoService.buscarPorId(turno.getOdontologo().getId());
        Optional<Paciente> pacienteABuscar= pacienteService.buscarPorId(turno.getPaciente().getId());

        if (odontologoABuscar.isPresent()&&pacienteABuscar.isPresent()){
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turno){
        Optional<TurnoDTO> turnoBuscado= turnoService.buscarPorId(turno.getId());
        if(turnoBuscado.isPresent()){
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("Turno actualizado con exito");
        }else {
            return ResponseEntity.badRequest().body("Error al actualizar Turno");

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id){
        Optional<TurnoDTO> turnoBuscado= turnoService.buscarPorId(id);
        if(turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("turno eliminado con éxito");

        }else {
            return ResponseEntity.badRequest().body("no se encontró el turno a Eliminar.");

        }
    }


}
