/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eggprojectofinalintegrador.alquileresdequinchosparafiestas.controladores;

import com.eggprojectofinalintegrador.alquileresdequinchosparafiestas.Servicios.UsuarioServicio;
import com.eggprojectofinalintegrador.alquileresdequinchosparafiestas.excepciones.MiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Leo
 */
@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

//*********************************************

    @GetMapping("/")
    public String index(ModelMap modelo){
                        
        return "index.html";
        
    }

    @GetMapping("/registrar")
    public String registrar(){
        
        return "registroPropietario.html";
        
    }

    @PostMapping("/registroPropietario")
    public String registro(@RequestParam String apellido, @RequestParam String nombre, @RequestParam String dni, @RequestParam String email, @RequestParam String password, String passwordR, ModelMap modelo, MultipartFile archivo){
        
        try {
            usuarioServicio.registrar(archivo, apellido, nombre, dni, email, password, passwordR);
            modelo.put("exito","Usuario registrado correctamente");
            
            return "index.html";
            
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("apellido", apellido);
            modelo.put("nombre", nombre);
            modelo.put("dni", dni);
            modelo.put("email", email);
           
            return "registroPropietario.html";
            
        }
        
    }

    @GetMapping("/login")//@RequestParam(required = false) String error, ModelMap modelo
    public String login(@RequestParam(required = false) String error, ModelMap modelo){
        
        if(error!=null){
            modelo.put("error", "Usuario o Contraseña invalidos!");
        }
        
        return "login.html";
        
    }       

}
