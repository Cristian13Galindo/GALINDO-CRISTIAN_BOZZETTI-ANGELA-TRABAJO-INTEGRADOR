package com.backEnd.clinicaOdontologica.service;


import com.backEnd.clinicaOdontologica.Repository.TurnoRepository;
import com.backEnd.clinicaOdontologica.dto.TurnoDTO;
import com.backEnd.clinicaOdontologica.entity.Odontologo;
import com.backEnd.clinicaOdontologica.entity.Paciente;
import com.backEnd.clinicaOdontologica.entity.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;


    //METODOS
    public List<TurnoDTO> listarTodos(){
        List<Turno> turnosEncontrados= turnoRepository.findAll();
        List<TurnoDTO> respuesta= new ArrayList<>();
        for (Turno turno:turnosEncontrados) {
            respuesta.add(turnoATurnoDTO(turno));

        }
        return respuesta;
    }
    public Optional<TurnoDTO> buscarPorId(Long id){
        Optional<Turno> turnoEncontrado= turnoRepository.findById(id);
        if(turnoEncontrado.isPresent()){
            return Optional.of(turnoATurnoDTO(turnoEncontrado.get()));
        }else{
            return  Optional.empty();
        }
    }
    public void actualizarTurno(TurnoDTO turnoDTO){
        turnoRepository.save(turnoDTOaTurno(turnoDTO));
    }
    public TurnoDTO guardarTurno(Turno turno){
        Turno turnoGuardado= turnoRepository.save(turno);
        return turnoATurnoDTO(turnoGuardado);

    }
    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }
    private TurnoDTO turnoATurnoDTO(Turno turno){
        TurnoDTO dto= new TurnoDTO();
        dto.setId(turno.getId());
        dto.setPacienteId(turno.getPaciente().getId());
        dto.setOdontologoId(turno.getOdontologo().getId());
        dto.setFecha(turno.getFecha());
        return dto;
    }
    private Turno turnoDTOaTurno(TurnoDTO turnoDTO){
        Turno turno= new Turno();
        Odontologo odontologo= new Odontologo();
        Paciente paciente= new Paciente();
        odontologo.setId(turnoDTO.getOdontologoId());
        paciente.setId(turnoDTO.getPacienteId());
        turno.setFecha(turnoDTO.getFecha());
        turno.setId(turnoDTO.getId());
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);
        return  turno;
    }
}
