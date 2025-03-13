package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class GrupoTest {
    Grupo grupo;

    @BeforeEach
    void setup() throws ClubException {
        grupo = new Grupo("123A", "Zumba", 20, 10, 50.0);
    }

    @Test
    @DisplayName("Creamos grupos con datos inválidos. Uno con plazas negativas, otro con matriculados negativos,otro con tarifa negativa y el último con más matriculados que número de plazas")
    void crear_grupos_datos_invalidos() {
        assertThrows(ClubException.class, () -> new Grupo("G3", "Natacion", -5, 0, 30.0));
        assertThrows(ClubException.class, () -> new Grupo("G3", "Natacion", 10, -1, 30.0));
        assertThrows(ClubException.class, () -> new Grupo("G3", "Natacion", 10, 5, -30.0));
        assertThrows(ClubException.class, () -> new Grupo("G3", "Natacion", 10, 15, 30.0));
    }
    
    @Test
    @DisplayName("Se ha actualizan de manera correcta el número de plazas")
    void actualizar_plazas_validas() throws ClubException {
        // Arrange
        int nuevasPlazas = 25;
        
        // Act
        grupo.actualizarPlazas(nuevasPlazas);
        
        // Assert
        assertEquals(nuevasPlazas, grupo.getPlazas());
    }
    
    @Test
    @DisplayName("Compueba que no se puedan asignar menos plazas de las ya matriculadas, ni que sea un valor negativo")
    void actualizar_plazas_invalido() {
        assertThrows(ClubException.class, () -> grupo.actualizarPlazas(5));
        assertThrows(ClubException.class, () -> grupo.actualizarPlazas(-2));
    }
    
    @Test
    @DisplayName ("Pueba que se pueda matricular correctamente un número válido de personas en grupo")
    void matricular_grupo_exitoso() throws ClubException {
        // Arrange
        int matriculadosAntes = grupo.getMatriculados();
        int matricular = 5;
        
        // Act
        grupo.matricular(matricular);
        
        // Assert
        assertEquals(matriculadosAntes + matricular, grupo.getMatriculados());
    }
    
    @Test
    @DisplayName("Comprueba que no se puedan matricular mas personas de las plazas disponibles, ni que sea un valor negativo")
    void matricular_grupo_sin_plazas_disponibles() {
        assertThrows(ClubException.class, () -> grupo.matricular(15));
        assertThrows(ClubException.class, () -> grupo.matricular(-15));
    }
    
    @Test
    @DisplayName("Comprueba que se imprime por pantalla correctamente")
    void grupo_toString() {
        // Arrange
        String expectedString = "(123A - Zumba - 50.0 euros - P:20 - M:10)";
        
        // Act
        String result = grupo.toString();
        
        // Assert
        assertEquals(expectedString, result);
    }
    /* 
    @Test
    void testGrupoEquals() throws ClubException {
        // Arrange
        Grupo grupoIgual = new Grupo("G1", "Futbol", 30, 15, 60.0);
        Grupo grupoDiferente = new Grupo("G2", "Baloncesto", 25, 5, 40.0);
        
        // Act & Assert
        assertTrue(grupo.equals(grupoIgual)); // Deben ser considerados iguales por código y actividad
        assertFalse(grupo.equals(grupoDiferente)); // No deben ser iguales
    }
    */
}
