package com.trivalia.trivalia.services;

import com.trivalia.trivalia.services.interfaces.ContadorServiceInterface;
import org.springframework.stereotype.Service;
// Usa jakarta.annotation.PostConstruct si usas Spring Boot 3+
import jakarta.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
public class ContadorService implements ContadorServiceInterface {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> cuentaRegresivaHandle;
    private volatile int segundosRestantes = 21; // Volatile garantiza que todos los hilos vean la copia más reciente de la variable.

    public ContadorService() {}

    public void iniciarContador() {
        segundosRestantes = 21;
        Runnable tareaDeDecremento = this::ejecutarDecrementoYVerificacion;
        this.cuentaRegresivaHandle = scheduler.scheduleAtFixedRate(
                tareaDeDecremento,
                0, // Retraso inicial: 0
                1, // Periodo: 1 segundo
                TimeUnit.SECONDS
        );
        System.out.println("Contador iniciado");
    }

    private void ejecutarDecrementoYVerificacion() {
        // Reducir el tiempo
        if (segundosRestantes > 0) {
            segundosRestantes--;
        }
        // Verificar la condición para parar
        if (segundosRestantes <= 0) {
            detenerContador();
        }

        System.out.println("Contador >>" +  segundosRestantes);
    }

    public void detenerContador() {
        if (cuentaRegresivaHandle != null) {
            cuentaRegresivaHandle.cancel(false);
            System.out.println("Contador finalizado");
        }
    }

    public boolean verificarContadorMayorQueCero() {
        return segundosRestantes > 0;
    }
}