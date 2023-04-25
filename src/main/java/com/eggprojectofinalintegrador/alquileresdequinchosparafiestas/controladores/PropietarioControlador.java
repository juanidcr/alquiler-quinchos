/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eggprojectofinalintegrador.alquileresdequinchosparafiestas.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Leo
 */
@Controller
@RequestMapping("/adminPropietario")
public class PropietarioControlador {
    @GetMapping("/dashboard")
    public String panelPropietario(){
        return "propietario.html";
    }
}
