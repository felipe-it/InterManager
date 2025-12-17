/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

/**
 *
 * @author felip
 */
public class Validador {
    
    public static boolean esEmailValido(String email) {
            
    String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
     
    return email != null && email.matches(regex);
    
} 
    
    public static boolean esClaveValida(String clave){
        
        if (clave == null || clave.length() < 8) {
            return false; // Debe tener al menos 8 caracteres
        }

        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneNumero = false;
        boolean tieneEspecial = false;

        for (char caracter : clave.toCharArray()) {
            if (Character.isUpperCase(caracter)) {
                tieneMayuscula = true;
            } else if (Character.isLowerCase(caracter)) {
                tieneMinuscula = true;
            } else if (Character.isDigit(caracter)) {
                tieneNumero = true;
            } else if (!Character.isLetterOrDigit(caracter)) {
                tieneEspecial = true;
            }
        }

        return tieneMayuscula && tieneMinuscula && tieneNumero && tieneEspecial;
    }

    
}
