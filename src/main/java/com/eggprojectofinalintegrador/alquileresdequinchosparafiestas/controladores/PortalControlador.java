/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eggprojectofinalintegrador.alquileresdequinchosparafiestas.controladores;

import com.eggprojectofinalintegrador.alquileresdequinchosparafiestas.Servicios.UsuarioServicio;
import com.eggprojectofinalintegrador.alquileresdequinchosparafiestas.entidades.Usuario;
import com.eggprojectofinalintegrador.alquileresdequinchosparafiestas.excepciones.MiException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/galeriaAlquiler")
    public String inicioGaleria(ModelMap modelo){
        return "galeriaAlquiler.html";
    }

    @GetMapping("/carritoAlquiler")
    public String inicioApp(ModelMap modelo){
        return "carritoAlquiler.html";
    }

    @GetMapping("/registrarPropietario")
    public String registrar(){
        
        return "registroPropietario.html";
        
    }

    @PostMapping("/registroPropietario")
    public String registroPropietario(MultipartFile archivo, @RequestParam String apellido, @RequestParam String nombre, @RequestParam String dni, @RequestParam String email, @RequestParam String telefono, @RequestParam String descripcion, @RequestParam String password, String passwordR, ModelMap modelo){
        
        try {
            usuarioServicio.registrarPropietario(archivo, apellido, nombre, dni, email, telefono, descripcion, password, passwordR);
            modelo.put("exito","Usuario registrado correctamente");
            
            return "index.html";
            
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            
            modelo.put("apellido", apellido);
            modelo.put("nombre", nombre);
            modelo.put("dni", dni);
            modelo.put("email", email);
            modelo.put("telefono", telefono);
            modelo.put("descripcion", descripcion);
            
           
            return "registroPropietario.html";
            
        }
        
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo){
        
        if(error!=null){
            modelo.put("error", "Usuario o Contraseña invalidos!");
        }
        
        //return "login.html";
        return "iniciarSesion.html";
        
    }  

    @PreAuthorize("hasAnyRole('ROLE_CLIENTE','ROLE_ADMIN','ROLE_PROPIETARIO')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session, ModelMap modelo){
        
        Usuario logeado=(Usuario) session.getAttribute("usuariosession");
        
        if(logeado.getRol().toString().equals("ADMIN")){
            
            return "redirect:/admin/dashboard";
            
        }else if(logeado.getRol().toString().equals("PROPIETARIO")){

            return "redirect:/adminPropietario/dashboard";

        }else{
            return "galeriaAlquiler.html";
        }
        
    }     

}
