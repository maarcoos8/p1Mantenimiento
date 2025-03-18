package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ClubDeportivoAltoRendimientoTest {
    private ClubDeportivoAltoRendimiento clubAR;
    private Grupo grupo1;
    private Grupo grupo2;

    @BeforeEach
    void setUp() throws ClubException {
        clubAR = new ClubDeportivoAltoRendimiento("Club Elite",  15, 10.0);
        grupo1 = new Grupo("G1", "Futbol", 20, 10, 50.0);
        grupo2 = new Grupo("G2", "Baloncesto", 12, 6, 40.0);
    } 

    @Test
    @DisplayName("Constructor con valores válidos se crea correctamente")
    public void constructor_valores_validos() throws ClubException {
        // Arrange
        String nombre = "Marcos";
        int maximo = 6;
        double incremento = 25.0;

        // Act
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento(nombre, maximo, incremento);

        // Assert
        assertNotNull(club);
        assertEquals(0, club.ingresos());
    }

    @Test
    @DisplayName("Constructor con valores válidos y tamaño se crea correctamente")
    public void constructor_valores_validos_y_tam() throws ClubException {
        // Arrange
        String nombre = "Marcos";
        int tam = 8;
        int maximo = 6;
        double incremento = 25.0;

        // Act
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento(nombre, tam, maximo, incremento);

        // Assert
        assertNotNull(club);
        assertEquals(0, club.ingresos());
    }

    @Test
    @DisplayName("No se puede crear un club de alto rendimiento con valores negativos")
    void crear_club_con_valores_negativos() {
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Invalido", -5, 10.0));
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Invalido", 15, -5.0));
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Invalido", 10, -5, -10.0));
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Invalido", 10, -5, 10.0));
    }

    @Test
    @DisplayName("Se puede añadir una actividad cuando las plazas son mayor que el máximo y se cambia correctamente")
    void anyadir_actividad_con_limite_plazas() throws ClubException {
        // Arrange
        String[] datos = {"G3", "Natacion", "20", "12", "30.0"};
        
        // Act
        clubAR.anyadirActividad(datos);
        
        // Assert
        assertEquals(3 , clubAR.plazasLibres("Natacion")); // El máximo permitido es 15
    }

    @Test
    @DisplayName("Se puede añadir una actividad dentro del número disponible de plazas")
    void anyadir_actividada_con_limite_plazas() throws ClubException {
        // Arrange
        String[] datos = {"G3", "Natacion", "14", "12", "30.0"};
        
        // Act
        clubAR.anyadirActividad(datos);
        
        // Assert
        assertEquals(2 , clubAR.plazasLibres("Natacion"));
    }
    
    @Test
    @DisplayName("Los ingresos incluyen un incremento adicional")
    void ingresos_con_incremento() throws ClubException {
        // Arrange
        clubAR.anyadirActividad(grupo1);
        clubAR.anyadirActividad(grupo2);
        double ingresosBase = (grupo1.getMatriculados() * grupo1.getTarifa()) + (grupo2.getMatriculados() * grupo2.getTarifa());
        double ingresosEsperados = ingresosBase + (ingresosBase * 0.10);
        
        // Act
        double ingresosActuales = clubAR.ingresos();
        
        // Assert
        assertEquals(ingresosEsperados, ingresosActuales, 0.01);
    }
    
    @Test
    @DisplayName("No se puede añadir una actividad sin datos suficientes")
    void anyadir_actividad_datos_insuficientes() {
        // Arrange
        String[] datosIncompletos = {"G4", "Tenis"};
        
        // Act & Assert
        assertThrows(ClubException.class, () -> clubAR.anyadirActividad(datosIncompletos));
    }

    @Test
    @DisplayName("Error de formato de número incorrecto al añadir actividad")
    void anyadir_actividad_formato_incorrecto() {
        // Arrange
        String[] datosInvalidos = {"G5", "Boxeo", "v", "5", "30.0"}; // "v" no es un número válido
        
        // Act & Assert
        assertThrows(ClubException.class, () -> clubAR.anyadirActividad(datosInvalidos));
    }

}
