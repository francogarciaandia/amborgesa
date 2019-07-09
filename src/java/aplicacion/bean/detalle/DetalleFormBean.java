/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import aplicacion.bean.detalle.DetalleBean;
import aplicacion.modelo.dominio.Detalle;
import aplicacion.modelo.dominio.Factura;
import aplicacion.modelo.dominio.ModoPago;
import aplicacion.modelo.dominio.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author TOSHIBA
 */
@ManagedBean
@RequestScoped
public class DetalleFormBean {
    @ManagedProperty(value = "#{detalleBean}")
    private DetalleBean detalleBean;
    private Factura unaFactura;
    private Producto unProducto;
    private Detalle unDetalle;
    private ModoPago modoPago;
    private List<Detalle> listadoDetalleCompra;
    private List<Detalle> listadodetallealternativo;

    /**
     * Creates a new instance of DetalleFormBean
     */
    public DetalleFormBean() {
        detalleBean =new DetalleBean();
        unaFactura=new Factura();
        unProducto=new Producto();
        unDetalle=new Detalle();
        modoPago=new ModoPago();
        listadoDetalleCompra=new ArrayList();
        listadodetallealternativo=new ArrayList();
    }
   
    public void agregarDetalle(){
        getUnDetalle().setFactura(unaFactura);
        getUnDetalle().setProductos(unProducto);
        Random r= new Random(System.currentTimeMillis());
         int codigo = (int) r.nextInt(200);
        getUnDetalle().setIddetalle((int) codigo);
         try {
            getDetalleBean().agregarDetalle(getUnDetalle());
             FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"Detalle agregado correctamente","muy bien");
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"hubo un inconveniente al intentar agregar","no se pudo agregar Detalle");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
    }
    public String reiniciarLista()
    {
        setListadodetallealternativo(new ArrayList());
        return "paginaproductos?faces-redirect=true";
    }
    public List<Detalle> obtenerDetalle()
    {
        
        return listadodetallealternativo;
    }
    public void agregardetalle(Producto r)
    {
        Detalle ggDetalle=new Detalle();
        ggDetalle.setProductos(r);
        getListadodetallealternativo().add(ggDetalle);
        
    }
    public void eliminarDetalle(){
         try {
             getDetalleBean().eliminarDetalle(unDetalle);
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"Detalle eliminado correctamente","Detalle");
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","no se pudo eliminar Detalle");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
    }
    public void modificarDetalle(){
         try {
            getDetalleBean().modificarDetalle(unDetalle);
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"Detalle modificado correctamente","Detalle");
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","no se pudo modificar Detalle");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
    }
//    public String confirmarPago(){
//        String res=null;
//        getModoPago().setInteres(5.0);
//        getUnaFactura().setModopago(getModoPago());
//        getUnaFactura().setFecha(new Date());
//        getUnaFactura().setUsuarios((Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioValidado"));
//        return res="pagoCompra";
//    }
//    public void confirmarCompra(Producto unProducto){
//        unDetalle=new Detalle();
//        unDetalle.setProductos(unProducto);
//        unDetalle.setCantidad(getCantidadProd());
//        unDetalle.setPrecioProdCant(getCantidadProd()*unProducto.getPrecio());
//        getListadoDetalleCompra().add(unDetalle);
//        
//    }
//public void cargarFactura()throws JRException, IOException{
//  for(Detalle det : getListadoDetalleCompra()){
//      det.setFactura(unaFactura);
//      getDetalleBean().agregarDetalle(det);
//      
//  }  
//  listarArrayUsuarioPdf();
//}
    /**
     * @return the detalleBean
     */
    public DetalleBean getDetalleBean() {
        return detalleBean;
    }

    /**
     * @param detalleBean the detalleBean to set
     */
    public void setDetalleBean(DetalleBean detalleBean) {
        this.detalleBean = detalleBean;
    }

    /**
     * @return the unaFactura
     */
    public Factura getUnaFactura() {
        return unaFactura;
    }

    /**
     * @param unaFactura the unaFactura to set
     */
    public void setUnaFactura(Factura unaFactura) {
        this.unaFactura = unaFactura;
    }

    /**
     * @return the unProducto
     */
    public Producto getUnProducto() {
        return unProducto;
    }

    /**
     * @param unProducto the unProducto to set
     */
    public void setUnProducto(Producto unProducto) {
        this.unProducto = unProducto;
    }

    /**
     * @return the unDetalle
     */
    public Detalle getUnDetalle() {
        return unDetalle;
    }

    /**
     * @param unDetalle the unDetalle to set
     */
    public void setUnDetalle(Detalle unDetalle) {
        this.unDetalle = unDetalle;
    }

    /**
     * @return the modoPago
     */
    public ModoPago getModoPago() {
        return modoPago;
    }

    /**
     * @param modoPago the modoPago to set
     */
    public void setModoPago(ModoPago modoPago) {
        this.modoPago = modoPago;
    }

    /**
     * @return the listadoDetalleCompra
     */
    public List<Detalle> getListadoDetalleCompra() {
        return listadoDetalleCompra;
    }

    /**
     * @param listadoDetalleCompra the listadoDetalleCompra to set
     */
    public void setListadoDetalleCompra(List<Detalle> listadoDetalleCompra) {
        this.listadoDetalleCompra = listadoDetalleCompra;
    }

    public List<Detalle> getListadodetallealternativo() {
        return listadodetallealternativo;
    }

    public void setListadodetallealternativo(List<Detalle> listadodetallealternativo) {
        this.listadodetallealternativo = listadodetallealternativo;
    }
    
}
