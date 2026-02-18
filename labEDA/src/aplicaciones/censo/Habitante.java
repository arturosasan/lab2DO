package aplicaciones.censo;

import java.util.Random;

/**
 * Habitante: representa una persona censada en un municipio
 * 
 * @author  Profesores EDA 
 * @version Septiembre 2023
 */

public class Habitante implements Comparable<Habitante> {

    private String dni, nombre, apellido1, apellido2;
    private int cp; /** cp: código postal */
    
    /** 100 apellidos más fecuentes, España, 2022, según INE */
    private static String[] apellidos = {"GARCIA", "RODRIGUEZ", "GONZALEZ", "FERNANDEZ", "LOPEZ",
        "MARTINEZ", "SANCHEZ", "PEREZ", "GOMEZ", "MARTIN", "JIMENEZ", "HERNANDEZ", "RUIZ", "DIAZ",
        "MORENO", "MUÑOZ", "ALVAREZ", "ROMERO", "GUTIERREZ", "ALONSO", "NAVARRO", "TORRES", "DOMINGUEZ",
        "RAMOS", "VAZQUEZ", "RAMIREZ", "GIL", "SERRANO", "MORALES", "MOLINA", "BLANCO", "SUAREZ",
        "CASTRO", "ORTEGA", "DELGADO", "ORTIZ", "MARIN", "RUBIO", "NUÑEZ", "MEDINA", "SANZ", "CASTILLO",
        "IGLESIAS", "CORTES", "GARRIDO", "SANTOS", "GUERRERO", "LOZANO", "CANO", "CRUZ", "MENDEZ",
        "FLORES", "PRIETO", "HERRERA", "PEÑA", "LEON", "MARQUEZ", "CABRERA", "GALLEGO", "CALVO", "VIDAL",
        "CAMPOS", "REYES", "VEGA", "FUENTES", "CARRASCO", "DIEZ", "AGUILAR", "CABALLERO", "NIETO", 
        "SANTANA", "VARGAS", "PASCUAL", "GIMENEZ", "HERRERO", "HIDALGO", "MONTERO", "LORENZO", "SANTIAGO",
        "BENITEZ", "DURAN", "IBAÑEZ", "ARIAS", "MORA", "FERRER", "CARMONA", "VICENTE", "ROJAS", "SOTO",
        "CRESPO", "ROMAN", "PASTOR", "VELASCO", "PARRA", "SAEZ", "MOYA", "BRAVO", "RIVERA", "GALLARDO",
        "SOLER"};
     
    /** 50 nombres masculinos más fecuentes, España, 2022, según INE */
    private static String[] nombresMasculinos = {"ANTONIO", "MANUEL", "JOSE", "FRANCISCO", "DAVID",
        "JUAN", "JAVIER", "DANIEL", "JOSE ANTONIO", "FRANCISCO JAVIER", "JOSE LUIS", "CARLOS", 
        "ALEJANDRO", "JESUS", "MIGUEL", "JOSE MANUEL", "MIGUEL ANGEL", "RAFAEL", "PABLO", "PEDRO",
        "ANGEL", "SERGIO", "FERNANDO", "JOSE MARIA", "JORGE", "LUIS", "ALBERTO", "ALVARO", 
        "JUAN CARLOS", "ADRIAN", "DIEGO", "JUAN JOSE", "RAUL", "IVAN", "RUBEN", "JUAN ANTONIO",
        "OSCAR", "ENRIQUE", "RAMON", "ANDRES", "JUAN MANUEL", "SANTIAGO", "VICENTE", "MARIO", 
        "VICTOR", "JOAQUIN", "EDUARDO", "ROBERTO", "MARCOS", "JAIME"};
      
    /** 50 nombres femeninos más fecuentes, España, 2022, según INE */
    private static String[] nombresFemeninos = {"MARIA CARMEN", "MARIA", "CARMEN", "ANA MARIA",
        "LAURA", "MARIA PILAR", "MARIA DOLORES", "ISABEL", "JOSEFA", "MARIA TERESA", "ANA", "MARTA",
        "CRISTINA", "MARIA ANGELES", "LUCIA", "MARIA ISABEL", "MARIA JOSE", "FRANCISCA", "ANTONIA",
        "DOLORES", "PAULA", "SARA", "ELENA", "MARIA LUISA", "RAQUEL", "ROSA MARIA", "MANUELA", 
        "MARIA JESUS", "PILAR", "CONCEPCION", "MERCEDES", "JULIA", "BEATRIZ", "ALBA", "SILVIA",
        "NURIA", "IRENE", "PATRICIA", "ROSARIO", "JUANA", "ROCIO", "ANDREA", "TERESA", "ENCARNACION",
        "MONTSERRAT", "MONICA", "ALICIA", "MARIA MAR", "SANDRA", "SONIA"};
    
    /** array para el cálculo de la letra del dni, segú url:
     *  https://www.interior.gob.es/opencms/ca/servicios-al-ciudadano/tramites-y-gestiones/dni/calculo-del-digito-de-control-del-nif-nie/  
     */
    private static char[] letraDni = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B',
        'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        
    /**
     * Crea un Habitante (con valores aleatorios)
     */
    public Habitante() {
        Random r = new Random();         
        /** dni en rango [10000000 .. 99999999], valor aleatorio */
        int n = r.nextInt(99999999) + 10000000; 
        /** cálculo de la letra del dni según se indica en esta url:
         *  https://www.interior.gob.es/opencms/ca/servicios-al-ciudadano/tramites-y-gestiones/dni/calculo-del-digito-de-control-del-nif-nie/  
         */
        dni = n + "" + letraDni[n % 23];         
        /** primer apellido, valor aleatorio */
        n = r.nextInt(apellidos.length);
        apellido1 = apellidos[n];        
        /** segundo apellido, valor aleatorio */
        n = r.nextInt(apellidos.length);
        apellido2 = apellidos[n];        
        /** nombre, valor aleatorio, probabilidad 50% mujer, 50% hombre */
        n = r.nextInt(100);
        if (n % 2 == 0) {
            n = r.nextInt(nombresFemeninos.length);
            nombre = nombresFemeninos[n];
        }
        else {
            n = r.nextInt(nombresMasculinos.length);
            nombre = nombresMasculinos[n];
        }
        /** codigo postal en rango [46000 .. 46999], valor aleatorio */
        cp = 46000 + r.nextInt(999); 
    }
    
    /**
     * Crea un Habitante (con valores pasados como argumentos)
     */
    public Habitante(String dni, String nom, String ap1, String ap2, int cp) {
        this.dni = dni;
        nombre = nom;
        apellido1 = ap1;
        apellido2 = ap2;
        this.cp = cp;
    }
    
    /**
     * Métodos consultores de atributos
     */
    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getApellido1() { return apellido1; }
    public String getApellido2() { return apellido2; }
    public int getCp() { return cp; }
    
    /**
     * Devuelve el String que representa un (this) Habitante en formato texto
     */
    public String toString() {
        return apellido1 + " " + apellido2 + ", " + nombre 
               + " (dni: " + dni + ". cp: " + cp + ")";
    }
    
    /**
     * Comprueba si un (this) Habitante es igual a otro, 
     * i.e. si ambos tienen el mismo valor 
     * (se comparan valores del atributo dni)
     * 
     * @param otro el otro Habitante.
     * @return true  si this y otro tienen el mismo dni 
     *         false en caso contrario
     */
    @Override
    public boolean equals(Object otro) {
        return (otro instanceof Habitante) && this.getDni().equals(((Habitante) otro).getDni());
    }
    
    /**
     * Compara un (this) Habitante con otro.
     * 
     * @param otro el Habitante a comparar con this.
     * @return -1 si this es menor que otro, 
     *         1  si this es mayor que otro
     *         0  si this y otro son iguales
     */
    public int compareTo(Habitante otro) {
        if (this.toString().compareTo(otro.toString()) < 0) {
            return -1;
        } else if (this.toString().compareTo(otro.toString()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

}
