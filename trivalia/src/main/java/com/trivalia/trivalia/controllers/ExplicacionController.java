package com.trivalia.trivalia.controllers;

import com.trivalia.trivalia.model.ExplicacionDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/explicaciones")
public class ExplicacionController {

  @GetMapping("/obtener/{uid}/{idPregunta}")
  public ExplicacionDTO ObtenerExplicacion(@PathVariable String uid, @PathVariable Long idPregunta) {
      return new ExplicacionDTO();
  }
}
