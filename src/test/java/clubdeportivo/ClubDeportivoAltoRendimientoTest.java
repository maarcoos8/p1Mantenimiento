package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ClubDeportivoAltoRendimientoTest {
    public class PruebasClubDeportivoAltoRendimiento {
    private ClubDeportivoAltoRendimiento clubAltoRendimiento;
    private Grupo grupo1;
    private Grupo grupo2;

    @BeforeEach
    void setUp() throws ClubException {
        clubAltoRendimiento = new ClubDeportivoAltoRendimiento("Club Elite", 10, 15, 10.0);
        grupo1 = new Grupo("G1", "Futbol", 20, 10, 50.0);
        grupo2 = new Grupo("G2", "Baloncesto", 12, 6, 40.0);
    } 

    @Test
    @DisplayName("No se puede crear un club de alto rendimiento con valores negativos")
    void crear_club_con_valores_negativos() {
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Invalido", 10, -5, 10.0));
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Invalido", 10, 15, -5.0));
    }

    @Test
    @DisplayName("Se puede añadir una actividad dentro del número disponible de plazas")
    void anyadir_actividad_con_limite_plazas() throws ClubException {
        // Arrange
        String[] datos = {"G3", "Natacion", "20", "5", "30.0"};
        int plazasAntes = clubAltoRendimiento.plazasLibres("Natacion");
        
        // Act
        clubAltoRendimiento.anyadirActividad(datos);
        
        // Assert
        assertEquals(plazasAntes + 15, clubAltoRendimiento.plazasLibres("Natacion")); // El máximo permitido es 15
    }
    
    @Test
    @DisplayName("Los ingresos incluyen un incremento adicional")
    void ingresos_con_incremento() throws ClubException {
        // Arrange
        clubAltoRendimiento.anyadirActividad(grupo1);
        clubAltoRendimiento.anyadirActividad(grupo2);
        double ingresosBase = (grupo1.getMatriculados() * grupo1.getTarifa()) + (grupo2.getMatriculados() * grupo2.getTarifa());
        double ingresosEsperados = ingresosBase + (ingresosBase * 0.10);
        
        // Act
        double ingresosActuales = clubAltoRendimiento.ingresos();
        
        // Assert
        assertEquals(ingresosEsperados, ingresosActuales, 0.01);
    }
    
    @Test
    @DisplayName("No se puede añadir una actividad sin datos suficientes")
    void anyadir_actividad_datos_insuficientes() {
        // Arrange
        String[] datosIncompletos = {"G4", "Tenis"};
        
        // Act & Assert
        assertThrows(ClubException.class, () -> clubAltoRendimiento.anyadirActividad(datosIncompletos));
    }
    
    }
}
