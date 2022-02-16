package com.taller2dam.taller.log4j;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class HandlerLog4j {
    private static Logger log = Logger.getLogger(HandlerLog4j.class);

    @SuppressWarnings("rawtypes")
    public static void registrarInfo(Class clase, TipoLog tipo, String mensaje) {
        log = LogManager.getLogger(clase);

        switch (tipo) {
            case DEBUG:
                log.debug(mensaje);
                break;
            case ERROR:
                log.error(mensaje);
                break;
            case FATAL:
                log.fatal(mensaje);
                break;
            case INFO:
                log.info(mensaje);
                break;
            case WARNING:
                log.warn(mensaje);
        }
    }

    public enum TipoLog {
        DEBUG, ERROR, FATAL, INFO, WARNING
    }
}

