package com.seph_worker.worker.core.entity.Catalogos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@Entity
@Table(name = "cat_a_ctba")
public class CatACtba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cve_ct")
    private String cve_ct;

    @Column(name = "marca")
    private String marca;

    @Column(name = "llave")
    private String llave;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "nivel")
    private Integer nivel;

    @Column(name = "subnivel")
    private Integer subnivel;

    @Column(name = "n_tipo")
    private String n_tipo;

    @Column(name = "n_nivel")
    private String n_nivel;

    @Column(name = "n_subnivel")
    private String n_subnivel;

    @Column(name = "tipomod")
    private String tipomod;

    @Column(name = "nombrect")
    private String nombrect;

    @Column(name = "tv_princip")
    private String tv_princip;

    @Column(name = "domicilio")
    private String domicilio;

    @Column(name = "numext")
    private String numext;

    @Column(name = "tv_entreca")
    private String tv_entreca;

    @Column(name = "entrecalle")
    private String entrecalle;

    @Column(name = "tv_ycalle")
    private String tv_ycalle;

    @Column(name = "ycalle")
    private String ycalle;

    @Column(name = "tv_callepo")
    private String tv_callepo;

    @Column(name = "callepost")
    private String callepost;

    @Column(name = "asentamient")
    private String asentamient;

    @Column(name = "colonia")
    private String colonia;

    @Column(name = "nombrecol")
    private String nombrecol;

    @Column(name = "localidad")
    private String localidad;

    @Column(name = "nombreloc")
    private String nombreloc;

    @Column(name = "areametro")
    private String areametro;

    @Column(name = "zonaeconom")
    private String zonaeconom;

    @Column(name = "gradomargi")
    private String gradomargi;

    @Column(name = "habitantes")
    private String habitantes;

    @Column(name = "hombres")
    private String hombres;

    @Column(name = "mujeres")
    private String mujeres;

    @Column(name = "catpob")
    private String catpob;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "nombremun")
    private String nombremun;

    @Column(name = "codpost")
    private String codpost;

    @Column(name = "clavecart")
    private String clavecart;

    @Column(name = "ageb")
    private String ageb;

    @Column(name = "long_dec")
    private Double long_dec;

    @Column(name = "lati_dec")
    private Double lati_dec;

    @Column(name = "altitud")
    private String altitud;

    @Column(name = "lada")
    private String lada;

    @Column(name = "telefono1")
    private String telefono1;

    @Column(name = "telexten1")
    private String telexten1;

    @Column(name = "telefono2")
    private String telefono2;

    @Column(name = "telexten2")
    private String telexten2;

    @Column(name = "telefono3")
    private String telefono3;

    @Column(name = "telexten3")
    private String telexten3;

    @Column(name = "fax1")
    private String fax1;

    @Column(name = "faxexten1")
    private String faxexten1;

    @Column(name = "fax2")
    private String fax2;

    @Column(name = "faxexten2")
    private String faxexten2;

    @Column(name = "fax3")
    private String fax3;

    @Column(name = "faxexten3")
    private String faxexten3;

    @Column(name = "celular")
    private String celular;

    @Column(name = "celular2")
    private String celular2;

    @Column(name = "celular3")
    private String celular3;

    @Column(name = "correoele")
    private String correoele;

    @Column(name = "paginaweb")
    private String paginaweb;

    @Column(name = "turno_an")
    private String turno_an;

    @Column(name = "turno")
    private String turno;

    @Column(name = "tur_des")
    private String tur_des;

    @Column(name = "tc")
    private String tc;

    @Column(name = "zonaescola")
    private String zonaescola;

    @Column(name = "cct_zona")
    private String cct_zona;

    @Column(name = "supeeducfis")
    private String supeeducfis;

    @Column(name = "cct_edufis")
    private String cct_edufis;

    @Column(name = "serreg")
    private String serreg;

    @Column(name = "cct_serrreg")
    private String cct_serrreg;

    @Column(name = "almacen")
    private String almacen;

    @Column(name = "cct_almacen")
    private String cct_almacen;

    @Column(name = "depadmva")
    private String depadmva;

    @Column(name = "adm_des")
    private String adm_des;

    @Column(name = "adep_normtv")
    private String adep_normtv;

    @Column(name = "nor_des")
    private String nor_des;

    @Column(name = "servicio")
    private String servicio;

    @Column(name = "ser_des")
    private String ser_des;

    @Column(name = "sostenimie")
    private String sostenimie;

    @Column(name = "sos_des")
    private String sos_des;

    @Column(name = "inmueble")
    private String inmueble;

    @Column(name = "inm_resp")
    private String inm_resp;

    @Column(name = "plantel")
    private String plantel;

    @Column(name = "claveinsti")
    private String claveinsti;

    @Column(name = "nom_dir")
    private String nom_dir;

    @Column(name = "apellido1")
    private String apellido1;

    @Column(name = "apellido2")
    private String apellido2;

    @Column(name = "rep_pro")
    private String rep_pro;

    @Column(name = "cveasigna")
    private String cveasigna;

    @Column(name = "fecha_funda")
    private String fecha_funda;

    @Column(name = "fechaalta")
    private String fechaalta;

    @Column(name = "fechaclaus")
    private String fechaclaus;

    @Column(name = "fechareape")
    private String fechareape;

    @Column(name = "fechacambi")
    private String fechacambi;

    @Column(name = "fechaactual")
    private String fechaactual;

    @Column(name = "fechasol")
    private String fechasol;

    @Column(name = "areasol")
    private String areasol;

    @Column(name = "puestosol")
    private String puestosol;

    @Column(name = "nombresol")
    private String nombresol;

    @Column(name = "observacio")
    private String observacio;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "des_mot")
    private String des_mot;

    @Column(name = "folio")
    private String folio;

    @Column(name = "status")
    private String status;

    @Column(name = "sta_des")
    private String sta_des;

    @Column(name = "reg_ele")
    private String reg_ele;

    @Column(name = "n_reg_ele")
    private String n_reg_ele;

    @Column(name = "electicida")
    private String electicida;

    @Column(name = "agua_pota")
    private String agua_pota;

    @Column(name = "drenaje")
    private String drenaje;

    @Column(name = "infra_dis")
    private String infra_dis;

    @Column(name = "internet")
    private String internet;
}
