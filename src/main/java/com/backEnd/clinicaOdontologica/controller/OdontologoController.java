package com.backEnd.clinicaOdontologica.controller;

import com.backEnd.clinicaOdontologica.entity.Odontologo;
import com.backEnd.clinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {


    //ATRIBUTOS
    private OdontologoService odontologoService;


    //@
    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;

   }
   @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodosLosOdontologos(){
        return ResponseEntity.ok(odontologoService.listarTodos());
   }
   @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id){
       Optional<Odontologo> odontologoAEliminar= odontologoService.buscarPorId(id);
       if (odontologoAEliminar.isPresent()){
           odontologoService.eliminarOdontologo(id);
           return ResponseEntity.ok("Se elimino correctamente");
       }else{
           return ResponseEntity.badRequest().body("Error, no se encontró");

       }
   }
   @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
       Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(odontologo.getId());
       if (odontologoBuscado.isPresent()){
           odontologoService.actualizarOdontologo(odontologo);
           return ResponseEntity.ok("Se Actualizo correctamente");
       }else{
           return ResponseEntity.badRequest().body("Error, no se encontró");

       }
   }

}
