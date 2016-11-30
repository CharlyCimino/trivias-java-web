/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author Charly
 */
public class ResultadoDePregunta 
{
    private String numeroPregunta;
    private String enunciado;
    private String idRespuestaUsuario;
    private String stringRespuestaUsuario;
    private String idRespuestaCorrecta;
    private String stringRespuestaCorrecta;
    private String icono;

    public ResultadoDePregunta(String numeroPregunta, String enunciado, String idCorrecta) 
    {
        this.numeroPregunta = numeroPregunta;
        this.enunciado = enunciado;
        this.idRespuestaUsuario = "";
        this.stringRespuestaUsuario = "";
        this.idRespuestaCorrecta = idCorrecta;
        this.stringRespuestaCorrecta = "";
    }
    
    

    public String getNumeroPregunta() {
        return numeroPregunta;
    }

    public void setNumeroPregunta(String numeroPregunta) {
        this.numeroPregunta = numeroPregunta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getIdRespuestaUsuario() {
        return idRespuestaUsuario;
    }

    public void setIdRespuestaUsuario(String idRespuestaUsuario) {
        this.idRespuestaUsuario = idRespuestaUsuario;
    }

    public String getStringRespuestaUsuario() {
        return stringRespuestaUsuario;
    }

    public void setStringRespuestaUsuario(String stringRespuestaUsuario) {
        this.stringRespuestaUsuario = stringRespuestaUsuario;
    }

    public String getIdRespuestaCorrecta() {
        return idRespuestaCorrecta;
    }

    public void setIdRespuestaCorrecta(String idRespuestaCorrecta) {
        this.idRespuestaCorrecta = idRespuestaCorrecta;
    }

    public String getStringRespuestaCorrecta() {
        return stringRespuestaCorrecta;
    }

    public void setStringRespuestaCorrecta(String stringRespuestaCorrecta) {
        this.stringRespuestaCorrecta = stringRespuestaCorrecta;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    
    
}
