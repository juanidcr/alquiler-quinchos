/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eggprojectofinalintegrador.alquileresdequinchosparafiestas.Servicios;

import com.eggprojectofinalintegrador.alquileresdequinchosparafiestas.entidades.Imagen;
import com.eggprojectofinalintegrador.alquileresdequinchosparafiestas.entidades.Usuario;
import com.eggprojectofinalintegrador.alquileresdequinchosparafiestas.enumeraciones.Rol;
import com.eggprojectofinalintegrador.alquileresdequinchosparafiestas.excepciones.MiException;
import com.eggprojectofinalintegrador.alquileresdequinchosparafiestas.repositorios.UsuarioRepositorio;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Leo
 */
@Service
public class UsuarioServicio implements UserDetailsService {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private ImagenServicio imagenServicio;

    @Transactional
    public void registrar(MultipartFile archivo, String apellido, String nombre, String dni, String email, String password, String passwordR) throws MiException{
        validar(apellido, nombre, email, password, passwordR);
        
        Usuario usuario=new Usuario();
        
        usuario.setApellido(apellido);
        usuario.setNombre(nombre);
        usuario.setDni(dni);
        usuario.setEmail(email);
        
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        
        usuario.setRol(Rol.PROPIETARIO);
        
        Imagen imagen=imagenServicio.guardar(archivo);
        
        usuario.setImagen(imagen);
        
        usuarioRepositorio.save(usuario);
    }

    private void validar(String apellido, String nombre, String email, String password, String passwordR) throws MiException{
        if(apellido.isEmpty() || apellido==null){
            throw new MiException("El nombre no puede ser nulo o estar vacio");
        }        
        if(nombre.isEmpty() || nombre==null){
            throw new MiException("El nombre no puede ser nulo o estar vacio");
        }
        if(email.isEmpty() || email==null){
            throw new MiException("El email no puede ser nulo o estar vacio");
        }
        if(password.isEmpty() || password==null || password.length()<=5){
            throw new MiException("La contraseña no puede ser nulo o estar vacio");
        }
        
        if(!password.equals(passwordR)){
            throw new MiException("Las contraseñas no son iguales");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
        Usuario usuario=usuarioRepositorio.buscarPorEmail(email);
        
        if(usuario!=null){
            
            List<GrantedAuthority> permisos=new ArrayList<>();
            
            GrantedAuthority p=new SimpleGrantedAuthority("ROLE_"+usuario.getRol().toString());
            
            permisos.add(p);
            
            ServletRequestAttributes attr=(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            
            HttpSession sesion=attr.getRequest().getSession(true);
            
            sesion.setAttribute("usuariosession", usuario);
            
            User user=new User(usuario.getEmail(), usuario.getPassword(), permisos);
            
            return user;            
            
        }else{
            return null;
        }
    }

    

}
