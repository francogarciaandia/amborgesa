/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.bean.usuario;

import aplicacion.modelo.dominio.Cliente;
import aplicacion.modelo.dominio.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author TOSHIBA
 */
@ManagedBean
@RequestScoped
public class UsuarioFormBean implements Serializable{
    @ManagedProperty(value = "#{usuarioBean}")
    private UsuarioBean usuarioBean; 
    private Cliente unCliente;
    private Usuario unUsuario;
    private String nombre;
    private String pass;
    private String nombreUsuario;
    private List<Usuario> listUsuarios;
    private Usuario validadoUsuario;
    private boolean admin;
    /**
     * Creates a new instance of UsuarioFormBean
     */
    public UsuarioFormBean() {
        unCliente=new Cliente();
        unUsuario=new Usuario();
        listUsuarios=new ArrayList();
       
    }
    @PostConstruct
    public void init(){
         usuarioBean= new UsuarioBean();
       listUsuarios=usuarioBean.listaUsuario();
    }
        public void onRowEdit(RowEditEvent event)
  {   Usuario usuario= (Usuario) event.getObject();
         modificarUsuario(usuario);
  }
   public void onRowEditCancel(RowEditEvent event)
  {   FacesMessage facesmessage= new FacesMessage(FacesMessage.SEVERITY_INFO,"C cancelo la edicion", "Cambios no guardados");
            FacesContext.getCurrentInstance().addMessage(null, facesmessage);
  }      
    public void crearUsuario(){
           getUnUsuario().setEstado(true);
           getUnUsuario().setTipoUsuario("cliente");
           getUnUsuario().setCodigo((int) (Math.random()*1000000));
           getUnUsuario().setClientes(unCliente);
           try {
               usuarioBean.agregarUsuario(unUsuario);
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"usuario agreagado correctamente","Usuario" + unUsuario.getApellidos());
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
        
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","no se pudo crear usuario");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                       
           }
        }
    
       public String validarUsuario() 
    {String pagina=null;
        setUnUsuario(getUsuarioBean().validarUsuario(getNombre(), getPass()));
        if(getUnUsuario()!=null)
        {
            if(getUnUsuario().getTipoUsuario().equalsIgnoreCase("Admin"))
            {   
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuariovalidado", unUsuario);
                pagina="paginainicio?faces-redirect=true";
                admin=true;
            }
            else
            {
                 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuariovalidado", unUsuario);
               pagina="paginainicio?faces-redirect=true";
                admin=false;
            }
        }
        else
        {
            FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO, "Contraseña o nombre de usuario invalidos", "por favor vuelva a ingresarlas");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        return pagina;
    }
        public void modificarUsuario(Usuario ggusuario){
             try {
               usuarioBean.modificarUsuario(ggusuario);
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"usuario modificado correctamente", ggusuario.getNombreUsuario() );
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","no se pudo modificar usuario");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
            
        }
        public void eliminarUsuario(Usuario ty){
            try {
               usuarioBean.eliminarUsuario(ty);
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"usuario eliminado correctamente","Usuario: " + ty.getNombreUsuario());
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","no se pudo eliminado usuario");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
        }
        public void obtenerUsuario(){
             try {
               usuarioBean.obtenerUsuario(nombreUsuario);
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"usuario encontrado","Usuario" + unUsuario.getApellidos());
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error","no se pudo encontrar usuario");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
        }
        public void obtenerListado(){
             setListUsuarios(getUsuarioBean().listaUsuario());
        }

    /**
     * @return the usuarioBean
     */
    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    /**
     * @param usuarioBean the usuarioBean to set
     */
    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    /**
     * @return the unCliente
     */
    public Cliente getUnCliente() {
        return unCliente;
    }

    /**
     * @param unCliente the unCliente to set
     */
    public void setUnCliente(Cliente unCliente) {
        this.unCliente = unCliente;
    }

    /**
     * @return the unUsuario
     */
    public Usuario getUnUsuario() {
        return unUsuario;
    }

    /**
     * @param unUsuario the unUsuario to set
     */
    public void setUnUsuario(Usuario unUsuario) {
        this.unUsuario = unUsuario;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the listUsuarios
     */
    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    /**
     * @param listUsuarios the listUsuarios to set
     */
    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Usuario getValidadoUsuario() {
        Usuario segundoUsuario=(Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuariovalidado");
        validadoUsuario=segundoUsuario;
        return validadoUsuario;
    }

    public void setValidadoUsuario(Usuario validadoUsuario) {
        this.validadoUsuario = validadoUsuario;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    
}
