/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import clases.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Charly
 */
@Named(value = "controladorBeans")
@SessionScoped
public class ControladorBeans implements Serializable {

    private static final long serialVersionUID = 1L;
    private Transacciones t = new Transacciones();
    private DecimalFormat decimales = new DecimalFormat("0.00");
    private int indice = 0; // INDICE DEL ARRAY DE PREGUNTAS
    private String imagenFondo; //NOMBRE DEL ARCHIVO DE IMAGEN DE FONDO
    
    /////////////////////////////////
    ///////////INDEX.XHTML///////////
    /////////////////////////////////
    
    private List<Trivias> listaDeTrivias;       // LISTA DE OBJETOS TRIVIA PARA LLENAR LA LISTA
    private String idTriviaSeleccionada;        // VALOR QUE TENDRA CADA OPCION
    private String nombreTriviaSeleccionada;    // LABEL QUE TENDRA CADA OPCION
    private Trivias triviaSeleccionada;         // LA TRIVIA QUE SELECCIONO EL USUARIO
    
    /////////////////////////////////
    /////////JUGANDO.XHTML///////////
    /////////////////////////////////
    
    private List<Preguntas> listaDePreguntas;   // LISTA DE OBJETOS PREGUNTA CORRESPONDIENTE A LA TRIVIA SELECCIONADA
    private String area;                        // LABEL PARA TEXTO "AREA: "
    private String tema;                        // LABEL PARA TEXTO "TEMA: "
    private String curso;                       // LABEL PARA TEXTO "CURSO: "
    private String nroPregunta = "1";           // LABEL PARA TEXTO "PREGUNTA Nº "
    private String enunciado;                   // LABEL DE LA PREGUNTA
    private String desactivarBotonAnterior;     // BANDERA PARA SABER SI DESACTIVA BOTON ANTERIOR (USUARIO ESTA EN LA PRIMERA PREGUNTA)
    private String desactivarBotonSiguiente;    // BANDERA PARA SABER SI DESACTIVA BOTON SIGUIENTE (USUARIO ESTA EN LA ULTIMA PREGUNTA)
    
    private List<Respuestas> listaDeRespuestas; // LISTA DE OBJETOS RESPUESTA POSIBLES CORRESPONDIENTES A LA PREGUNTA ACTUAL
    
    
    private String idRespuestaSeleccionada;     // 
    private String idRespuestaCorrecta;
    
    // CAMPOS DE PAGINA RESULTADO.HTML
    
    /////////////////////////////////
    /////////RESULTADO.XHTML/////////
    /////////////////////////////////
    
    private String puntajeObtenido;             // El puntaje que obtuvo el usuario en formato dos decimales
    
    // LISTA DE OBJETOS PARA CONTROL Y LA TABLA DE RESULTADOS
    private List<ResultadoDePregunta> listaDeResultados = new ArrayList<ResultadoDePregunta>();
    
    private String labelCantPreguntas;
    private String labelCantCorrectas;
    private String labelCantIncorrectas;
    private String labelCantSinContestar;
    private String estiloPuntaje;
    
    /////////////////////////////////
    ///////////HISTORIAL.XHTML///////////
    /////////////////////////////////
    
    private List<Puntajes> listaDePuntajes;
    
    /**
     * Creates a new instance of ControladorBeans
     */
    public ControladorBeans() 
    {
        // Pido las trivias a la BD para llenar la lista donde el usuario seleccionará
        listaDeTrivias = t.dameListaDeTrivias();
        imagenFondo = "trivia";
    }
    
    public String pasarAJugar()
    {
        // GUARDAR LA TRIVIA QUE SELECCIONO EL USUARIO
        triviaSeleccionada = listaDeTrivias.get(Integer.parseInt(idTriviaSeleccionada) - 1);
        nombreTriviaSeleccionada = triviaSeleccionada.getNombre();
        
        switch (triviaSeleccionada.getIdTrivia())
        {
            case 1:  imagenFondo = "sudamerica"; break;
            case 2:  imagenFondo = "futbol"; break;
            case 3:  imagenFondo = "sumas"; break;
        }
        
        // ADQUIRIR LAS PREGUNTAS CORRESPONDIENTES A LA TRIVIA
        listaDePreguntas = t.damePreguntas(idTriviaSeleccionada);
        
        // LLENAR LISTA DE RESULTADOS CON EL ENUNCIADO Y LA RESPUESTA CORRECTA DE CADA PREGUNTA DE LA TRIVIA
        for (int i = 0; i < listaDePreguntas.size(); i++) 
        {
            listaDeRespuestas = t.dameRespuestas(listaDePreguntas.get(i).getIdPregunta().toString());
            
            listaDeResultados.add(new ResultadoDePregunta( (i+1)+"" , 
                                                            listaDePreguntas.get(i).getEnunciado() , 
                                                            listaDeRespuestas.get(0).getIdRespuesta().toString()) );
        }
        
        // ADQUIRIR LAS RESPUESTAS CORRESPONDIENTES A LA PRIMER PREGUNTA
        listaDeRespuestas = t.dameRespuestas(listaDePreguntas.get(indice).getIdPregunta().toString());
        
        prepararPaginaJugando(0); // LE PASO PARAMETRO 0 PARA QUE EL INDICE NO SE MUEVA Y QUEDE EN 0 (PRIMERA PREGUNTA)
        return "jugando.xhtml";
    }
    
    public void prepararPaginaJugando(int sentido)
    {
        guardarSeleccionDeUsuario();
        
        System.out.println(listaDeResultados.get(indice).getStringRespuestaUsuario());
        
        // ACTUALIZO EL INDICE
        indice += sentido;
        
        // LLENO LOS DATOS DE LA TRIVIA Y COLOCO LA PREGUNTA
        area = listaDePreguntas.get(indice).getArea();
        tema = listaDePreguntas.get(indice).getTema();
        curso = listaDePreguntas.get(indice).getCurso();
        nroPregunta = (indice+1) + "";
        enunciado = listaDePreguntas.get(indice).getEnunciado();
        
        // RESETEAR BANDERAS DE BOTONES
        desactivarBotonAnterior = "False";
        desactivarBotonSiguiente = "False";
        
        // VERIFICO LOS EXTREMOS PARA DESACTIVAR BOTONES
        if (indice == 0) desactivarBotonAnterior = "True";
        if (indice == listaDePreguntas.size()-1) desactivarBotonSiguiente = "True";
        
        // SOLICITO A LA BD QUE ME DE LA LISTA DE RESPUESTAS PARA LA PREGUNTA ACTUAL
        listaDeRespuestas = t.dameRespuestas(listaDePreguntas.get(indice).getIdPregunta().toString());
         
        // GUARDO EL ID Y LA CONTESTACION CORRECTA (SIEMPRE ES LA PRIMER FILA QUE ME DEVUELVE LA BD)
        listaDeResultados.get(indice).setIdRespuestaCorrecta( listaDeRespuestas.get(0).getIdRespuesta()+"" );
        listaDeResultados.get(indice).setStringRespuestaCorrecta( listaDeRespuestas.get(0).getContestacion() );
        
        // MEZCLO Y COLOCO LAS RESPUESTAS
        Collections.shuffle(listaDeRespuestas);
        
        // TRAIGO LA RESPUESTA SELECCIONADA (SI ES QUE HUBO) Y LA SELECCIONO POR DEFECTO
        // POR SI EL USURIO REGRESA, DEBE VERSE LO QUE SELECCIONO, NO LA LISTA RESETEADA
        idRespuestaSeleccionada = listaDeResultados.get(indice).getIdRespuestaUsuario();
    }
    
    public String pasarAResultado()
    {
        guardarSeleccionDeUsuario();
        
        for (int i = 0; i < listaDeResultados.size(); i++) {
               System.out.println("Id de usuario: >" + listaDeResultados.get(i).getIdRespuestaUsuario() + "<");
        }
        
        double acertadas = 0.00;
        int incorrectas = 0;
        int sinContestar = 0;
        
        // CICLO PARA RECOPILAR INFORMACION SOBRE CADA PREGUNTA
        for (int i = 0; i < listaDePreguntas.size(); i++) 
        {
            System.out.println("Id de usuario: >" + listaDeResultados.get(i).getIdRespuestaUsuario() + "<");
            System.out.println("Id de correct: >" + listaDeResultados.get(i).getIdRespuestaCorrecta() + "<");
            // SI EL ID DE LA RESPUESTA CORRECTA COINCIDE CON EL ID DE LA RESPUESTA DEL USUARIO
            if (listaDeResultados.get(i).getIdRespuestaCorrecta().equals(listaDeResultados.get(i).getIdRespuestaUsuario())) 
            {
                acertadas++;
                listaDeResultados.get(i).setIcono("bg-success text-success glyphicon glyphicon-ok");
            }
            // SI EL ID DE LA RESPUESTA DEL USUARIO ESTA VACIO
            else if (listaDeResultados.get(i).getIdRespuestaUsuario().isEmpty())
            {
                sinContestar++;
                listaDeResultados.get(i).setIcono("bg-danger text-danger glyphicon glyphicon-remove");
                listaDeResultados.get(i).setStringRespuestaUsuario("SIN RESPONDER");
            }
            else
            {
                incorrectas++;
                listaDeResultados.get(i).setIcono("bg-danger text-danger glyphicon glyphicon-remove");
            }
        }
        
        // EL PUNTAJE SE CALCULA COMO LA CANTIDAD DE ACERTADAS SOBRE LA CANTIDAD TOTAL DE PREGUNTAS
        double puntajeDouble = acertadas * 10 / listaDePreguntas.size();
        
        // SI EL PUNTAJE ES MAYOR A 6, LO PINT ODE VERDE, SINO ROJO
        if (puntajeDouble >= 6.0) estiloPuntaje = "bg-success text-success";
        else estiloPuntaje = "bg-danger text-danger";
        
        // RELLENO LA PAGINA
        puntajeObtenido = decimales.format(puntajeDouble) + "";
        labelCantPreguntas = listaDePreguntas.size() + "";
        labelCantCorrectas = (int) acertadas + "";
        labelCantIncorrectas = incorrectas + "";
        labelCantSinContestar = sinContestar + "";
        
        // VOLCAR RESULTADO EN BD Y DEVOLVER LA PAGINA "RESULTADO.xhtml"
        t.guardarResultado(new Puntajes(0, Integer.parseInt(idTriviaSeleccionada), puntajeDouble, new Date()));
        return "resultado.xhtml";
    }
    
    private void guardarSeleccionDeUsuario()
    {
        if (idRespuestaSeleccionada != null) 
        {
            // GUARDO EL ID DE LA RESPUESTA SELECCIONADA ANTERIORMENTE
            listaDeResultados.get(indice).setIdRespuestaUsuario(idRespuestaSeleccionada);
            
            // A TRAVES DE ESE ID, BUSCO EL STRING DE LA RESPUESTA Y LO GUARDO PARA MOSTRARLO EN LA TABLA DE RESULTADOS
            for (int i = 0; i < listaDeRespuestas.size(); i++)
            {
                if (listaDeRespuestas.get(i).getIdRespuesta() == Integer.parseInt(idRespuestaSeleccionada))
                {
                    listaDeResultados.get(indice).setStringRespuestaUsuario( listaDeRespuestas.get(i).getContestacion() );
                } 
            }
        }
    }
    
    public String volverAIndex()
    {
       resetearTodo();
       return "index.xhtml";
    }
    
    public String mostrarHistorial()
    {
       listaDePuntajes = t.damePuntajes("0");
       return "historial.xhtml";
    }
   
    private void resetearTodo() 
    {
        indice = 0;
        listaDeTrivias = t.dameListaDeTrivias();
        idTriviaSeleccionada = null;
        nombreTriviaSeleccionada = null;
        triviaSeleccionada = null;
        listaDePreguntas = null;
        area = null;
        tema = null;
        curso = null;
        nroPregunta = "1";
        enunciado = null;
        desactivarBotonAnterior = null;
        desactivarBotonSiguiente = null;
        listaDeRespuestas = null;
        idRespuestaSeleccionada = null;
        idRespuestaCorrecta = null;
        puntajeObtenido = null;
        listaDeResultados = new ArrayList<ResultadoDePregunta>();
        labelCantPreguntas = null;
        labelCantCorrectas = null;
        labelCantIncorrectas = null;
        labelCantSinContestar = null;
        imagenFondo = "trivia";
        estiloPuntaje = null;
    }
    
    
    //////////////////////////////////////////////////////////////////////
    /////////////////////////GETTERS Y SETTERS////////////////////////////
    //////////////////////////////////////////////////////////////////////

    public List<Trivias> getListaDeTrivias() {
        return listaDeTrivias;
    }

    public void setListaDeTrivias(List<Trivias> listaDeTrivias) {
        this.listaDeTrivias = listaDeTrivias;
    }

    public String getIdTriviaSeleccionada() {
        return idTriviaSeleccionada;
    }

    public void setIdTriviaSeleccionada(String idTriviaSeleccionada) {
        this.idTriviaSeleccionada = idTriviaSeleccionada;
    }

    public Trivias getTriviaSeleccionada() {
        return triviaSeleccionada;
    }

    public void setTriviaSeleccionada(Trivias triviaSeleccionada) {
        this.triviaSeleccionada = triviaSeleccionada;
    }

    public String getNombreTriviaSeleccionada() {
        return nombreTriviaSeleccionada;
    }

    public void setNombreTriviaSeleccionada(String nombreTriviaSeleccionada) {
        this.nombreTriviaSeleccionada = nombreTriviaSeleccionada;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getNroPregunta() {
        return nroPregunta;
    }

    public void setNroPregunta(String nroPregunta) {
        this.nroPregunta = nroPregunta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getDesactivarBotonAnterior() {
        return desactivarBotonAnterior;
    }

    public void setDesactivarBotonAnterior(String desactivarBotonAnterior) {
        this.desactivarBotonAnterior = desactivarBotonAnterior;
    }

    public String getDesactivarBotonSiguiente() {
        return desactivarBotonSiguiente;
    }

    public void setDesactivarBotonSiguiente(String desactivarBotonSiguiente) {
        this.desactivarBotonSiguiente = desactivarBotonSiguiente;
    }

    public List<Respuestas> getListaDeRespuestas() {
        return listaDeRespuestas;
    }

    public void setListaDeRespuestas(List<Respuestas> listaDeRespuestas) {
        this.listaDeRespuestas = listaDeRespuestas;
    }

    public String getIdRespuestaSeleccionada() {
        return idRespuestaSeleccionada;
    }

    public void setIdRespuestaSeleccionada(String idRespuestaSeleccionada) {
        this.idRespuestaSeleccionada = idRespuestaSeleccionada;
    }

    public String getIdRespuestaCorrecta() {
        return idRespuestaCorrecta;
    }

    public void setIdRespuestaCorrecta(String idRespuestaCorrecta) {
        this.idRespuestaCorrecta = idRespuestaCorrecta;
    }

    public String getPuntajeObtenido() {
        return puntajeObtenido;
    }

    public void setPuntajeObtenido(String puntajeObtenido) {
        this.puntajeObtenido = puntajeObtenido;
    }

    public String getLabelCantCorrectas() {
        return labelCantCorrectas;
    }

    public void setLabelCantCorrectas(String labelCantCorrectas) {
        this.labelCantCorrectas = labelCantCorrectas;
    }

    public String getLabelCantIncorrectas() {
        return labelCantIncorrectas;
    }

    public void setLabelCantIncorrectas(String labelCantIncorrectas) {
        this.labelCantIncorrectas = labelCantIncorrectas;
    }

    public String getLabelCantSinContestar() {
        return labelCantSinContestar;
    }

    public void setLabelCantSinContestar(String labelCantSinContestar) {
        this.labelCantSinContestar = labelCantSinContestar;
    }

    public List<ResultadoDePregunta> getListaDeResultados() {
        return listaDeResultados;
    }

    public void setListaDeResultados(List<ResultadoDePregunta> listaDeResultados) {
        this.listaDeResultados = listaDeResultados;
    }

    public String getLabelCantPreguntas() {
        return labelCantPreguntas;
    }

    public void setLabelCantPreguntas(String labelCantPreguntas) {
        this.labelCantPreguntas = labelCantPreguntas;
    }

    public String getImagenFondo() {
        return imagenFondo;
    }

    public void setImagenFondo(String imagenFondo) {
        this.imagenFondo = imagenFondo;
    }

    public String getEstiloPuntaje() {
        return estiloPuntaje;
    }

    public void setEstiloPuntaje(String estiloPuntaje) {
        this.estiloPuntaje = estiloPuntaje;
    }

    public List<Puntajes> getListaDePuntajes() {
        return listaDePuntajes;
    }

    public void setListaDePuntajes(List<Puntajes> listaDePuntajes) {
        this.listaDePuntajes = listaDePuntajes;
    }

    
}
