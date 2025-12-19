package com.trivalia.trivalia.services;

import com.trivalia.trivalia.services.interfaces.ContadorServiceInterface;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
public class ContadorService implements ContadorServiceInterface {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> cuentaRegresivaHandle;
    private volatile int segundosRestantes = 21;
    private volatile boolean contadorActivo = false;

    public ContadorService() {}

    public void iniciarContador() {
        if (contadorActivo) {
            return; // para no iniciar más de un contador
        }

        segundosRestantes = 21;
        contadorActivo = true;
        Runnable tareaDeDecremento = this::ejecutarDecrementoYVerificacion;
        this.cuentaRegresivaHandle = scheduler.scheduleAtFixedRate(
                tareaDeDecremento,
                0,
                1,
                TimeUnit.SECONDS
        );
        System.out.println("Contador iniciado");
    }

    private void ejecutarDecrementoYVerificacion() {
        if (!contadorActivo) {
            return;
        }
        // Reducir el tiempo
        if (segundosRestantes > 0) {
            segundosRestantes--;
        }

        System.out.println("Contador >> " + segundosRestantes);
        if (segundosRestantes <= 0) {
            contadorActivo = false;
            detenerContador();
        }
    }

    public void detenerContador() {
        contadorActivo = false;

        if (cuentaRegresivaHandle != null && !cuentaRegresivaHandle.isCancelled()) {
            cuentaRegresivaHandle.cancel(false);
            cuentaRegresivaHandle = null;
            System.out.println("Contador finalizado");
        }
    }

    public boolean verificarContadorMayorQueCero() {
        return segundosRestantes > 0 && contadorActivo;
    }

    // Spring llamará a este método para detener el hilo por completo y eliminarlo de memoria
    @PreDestroy
    public void shutdownContador() {
        detenerContador();
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}