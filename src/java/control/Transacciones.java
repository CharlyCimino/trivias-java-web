/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import clases.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Charly
 */
public class Transacciones 
{
    private Session sesion;
    private Transaction tx;
    
    //ESTABLECER CONEXION CON BD Y MANEJAR EXCEPCIONES

    private void iniciaOperacion() throws HibernateException
    {
        sesion = NewHibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }
    
    private void manejaExcepcion(HibernateException he) throws HibernateException
    {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }
    
    //CONSULTAS
    
    public List<Trivias> dameListaDeTrivias() throws HibernateException
    {
        List<Trivias> t = null;
        
        try 
        { 
            iniciaOperacion(); 
            t = sesion.createQuery("FROM Trivias").list(); 
        } 
        finally 
        { 
            sesion.close(); 
        }  
        
        return t;
    }
    
    public List<Preguntas> damePreguntas (String id) throws HibernateException 
    { 
        List<Preguntas> p = null;  

        try 
        { 
            iniciaOperacion(); 
            p = sesion.createQuery("FROM Preguntas p WHERE idTrivia = " + id).list(); 
        } 
        finally 
        { 
            sesion.close(); 
        }  

        return p; 
    }  
    
    public List<Respuestas> dameRespuestas (String id) throws HibernateException 
    { 
        List<Respuestas> r = null;  

        try 
        { 
            iniciaOperacion(); 
            r = sesion.createQuery("FROM Respuestas r WHERE idPregunta = " + id).list(); 
        } 
        finally 
        { 
            sesion.close(); 
        }  

        return r; 
    }  
    
    public void guardarResultado (Puntajes puntaje) throws HibernateException 
    {
        long id = 0;  

        try 
        { 
            iniciaOperacion(); 
            sesion.save(puntaje); 
            tx.commit(); 
        } 
        catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        }
    }
    
    public List<Puntajes> damePuntajes (String id) throws HibernateException 
    { 
        List<Puntajes> p = null;  

        try 
        { 
            iniciaOperacion(); 
            p = sesion.createQuery("FROM Puntajes p WHERE idUsuario = " + id).list(); 
        } 
        finally 
        { 
            sesion.close(); 
        }  

        return p; 
    }  
}
