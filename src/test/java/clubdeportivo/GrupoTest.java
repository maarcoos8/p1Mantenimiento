package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class GrupoTest {
    Grupo grupo;

    @BeforeEach
    void setup() throws ClubException {
        grupo = new Grupo("123A", "Spinning", 20, 10, 50.0);
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
        String expectedString = "(123A - Spinning - 50.0 euros - P:20 - M:10)";
        
        // Act
        String result = grupo.toString();
        
        // Assert
        assertEquals(expectedString, result);
    }

    
    @Test
    @DisplayName("Equals devuelve true con dos grupos iguales")
    public void equals_DosGruposIguales_DevuelveTrue() throws ClubException {
        // Arrange
        Grupo grupoIgual = new Grupo("123A", "Spinning", 20, 10, 50.0);

        // Act
        boolean iguales = grupo.equals(grupoIgual);

        // Assert
        assertTrue(iguales);
    }

    @Test
    @DisplayName("equals devuelve false con un objeto que no es un Grupo")
    public void equals_ObjetoNoGrupo_DevuelveFalse() throws ClubException {
        // Arrange
        Object objeto = new Object();

        // Act
        boolean iguales = grupo.equals(objeto);

        // Assert
        assertFalse(iguales);
    }

    @Test
    @DisplayName("equals devuelve false entre dos grupos con códigos diferentes")
    public void equals_DosGruposConCodigosDiferentes_DevuelveFalse() throws ClubException {
        // Arrange
        Grupo grupo2 = new Grupo("123A", "Zumba", 15, 10, 25.45);

        // Act
        boolean iguales = grupo.equals(grupo2);

        // Assert
        assertFalse(iguales);
    }

    @Test
    @DisplayName("equals devuelve false entre dos grupos con actividades diferentes")
    public void equals_DosGruposConActividadesDiferentes_DevuelveFalse() throws ClubException {
        // Arrange
        Grupo grupo2 = new Grupo("123A", "Zumba", 15, 10, 25.45);

        // Act
        boolean iguales = grupo.equals(grupo2);

        // Assert
        assertFalse(iguales);
    }



    @Test
    @DisplayName("hashCode devuelve el código hash del grupo correctamente")
    public void hashCode_InvocarMetodo_DevuelveCodigoHashCorrectamente() throws ClubException {

        // Act
        int codigoHash = grupo.hashCode();
        int codigoHashEsperado = "123A".toUpperCase().hashCode() + "Spinning".toUpperCase().hashCode();

        // Assert
        assertEquals(codigoHashEsperado, codigoHash);
    }

    /*
     * PRUEBAS DE LOS GETTERS
     * grupo = new Grupo("123A", "Spinnig", 20, 10, 50.0);
     */

    @Test
    @DisplayName("getCodigo devuelve el código correctamente")
    public void getCodigo_prueba() throws ClubException {
        // Arrange
        String codigoEsperado = "123A";

        // Act
        String codigoObtenido = grupo.getCodigo();

        // Assert
        assertEquals(codigoEsperado, codigoObtenido);
    }

    @Test
    @DisplayName("getActividad devuelve la actividad correctamente")
    public void getActividad_prueba() throws ClubException {
        // Arrange
        String actividadEsperada = "Spinning";

        // Act
        String actividadObtenida = grupo.getActividad();

        // Assert
        assertEquals(actividadEsperada, actividadObtenida);
    }

    @Test
    @DisplayName("getPlazas devuelve el número de plazas correctamente")
    public void getPlazas_prueba() throws ClubException {
        // Arrange
        int plazasEsperadas = 20;

        // Act
        int plazasObtenidas = grupo.getPlazas();

        // Assert
        assertEquals(plazasEsperadas, plazasObtenidas);
    }

    @Test
    @DisplayName("getMatriculados devuelve el número de matriculados correctamente")
    public void getMatriculados_prueba() throws ClubException {
        // Arrange
        int matriculadosEsperados = 10;

        // Act
        int matriculadosObtenidos = grupo.getMatriculados();

        // Assert
        assertEquals(matriculadosEsperados, matriculadosObtenidos);
    }

    @Test
    @DisplayName("getTarifa devuelve la tarifa correctamente")
    public void getTarifa_prueba() throws ClubException {
        // Arrange
        double tarifaEsperada = 50.0;

        // Act
        double tarifaObtenida = grupo.getTarifa();

        // Assert
        assertEquals(tarifaEsperada, tarifaObtenida);
    }

}


