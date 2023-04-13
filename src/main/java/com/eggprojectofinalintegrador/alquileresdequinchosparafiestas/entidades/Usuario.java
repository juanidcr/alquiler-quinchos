/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggprojectofinalintegrador.alquileresdequinchosparafiestas.entidades;

import com.eggprojectofinalintegrador.alquileresdequinchosparafiestas.enumeraciones.Rol;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Leo
 */
@Entity
public class Usuario {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
    private String Id;
    
    private String apellido;
    private String nombre;
    private String email;
    private String password;
    
    @Enumerated(EnumType.STRING)
    private Rol rol;    
    
    @OneToOne
    private Imagen imagen;
}
