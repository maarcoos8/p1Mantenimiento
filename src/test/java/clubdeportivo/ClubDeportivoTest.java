    // Soraya Bennai Sadqi y Marcos Luque Montiel

package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ClubDeportivoTest {
    private ClubDeportivo club;
    private Grupo grupo1;
    private Grupo grupo2;

    @BeforeEach
    void setUp() throws ClubException {
        club = new ClubDeportivo("Club Soraya", 5);
        grupo1 = new Grupo("G1", "Futbol", 20, 10, 50.0);
        grupo2 = new Grupo("G2", "Baloncesto", 15, 5, 40.0);
    }

    @Test
    @DisplayName("Se puede crear un club deportivo con el constructor por defecto")
    void crear_club_constructor_por_defecto() throws ClubException {
        // Arrange & Act
        ClubDeportivo club = new ClubDeportivo("Club Marcos");
        
        // Assert
        assertNotNull(club);
    }


    @Test
    @DisplayName("Compruebaque se pueda agregar un grupo al club y que el número de plazas aumente correctamente")
    void agregar_grupo() throws ClubException {
        // Arrange
        int plazasAntes = club.plazasLibres("Futbol");
        
        // Act
        club.anyadirActividad(grupo1);
        
        // Assert
        assertEquals(plazasAntes + grupo1.plazasLibres(), club.plazasLibres("Futbol"));
    }
    
    @Test
    @DisplayName("No se puede crear un club deportivo con un valor máximo de grupo negativo")
    void crear_club_con_valor_negativo() {
        assertThrows(ClubException.class, () -> new ClubDeportivo("Club Invalido", -5)); 
    }

    @Test
    @DisplayName("Comprueba que no se pueda agregar un grupo nulo al club deportivo, ni un grupo a partir de un array con menos de 5 datos, ni con un valor numérico incorrecto")
    void agregar_grupo_nulo() {
        // Arrange
        Grupo nulo = null;
        String[] datos = {"G3", "Natacion", "10", "5"};
        String[] datos2 = {"G3", "Natacion", "10.7", "5", "30.0"};

        // Act & Assert
        assertThrows(ClubException.class, () -> club.anyadirActividad(nulo));
        assertThrows(ClubException.class, () -> club.anyadirActividad(datos));
        assertThrows(ClubException.class, () -> club.anyadirActividad(datos2));
    }
    
    @Test
    @DisplayName("Comprueba que las personas se puedan matricular en una actividad de manera exitosa y que el numero de las plazas disponibles disminuya")
    void matricular_exito() throws ClubException {
        // Arrange
        club.anyadirActividad(grupo1);
        int plazasAntes = club.plazasLibres("Futbol");
        int matricular = 5;
        
        // Act
        club.matricular("Futbol", matricular);
        
        // Assert
        assertEquals(plazasAntes - matricular, club.plazasLibres("Futbol"));
    }

    @Test
    @DisplayName ("Verifica que no se puedan matricular más personas de las plazas que hay disponibles")
    void matricular_sin_plazas() throws ClubException {
        // Arrange
        club.anyadirActividad(grupo1);
        int personasMatricular = 15;
        
        // Act & Assert
        assertThrows(ClubException.class, () -> club.matricular("Futbol", personasMatricular));
    }

    @Test
    @DisplayName ("Calcula los ingresos del club sumando la tarifa de todos los grupos matriuclados y que el resultado sea el esperado")
    void ingresos() throws ClubException {
        // Arrange
        club.anyadirActividad(grupo1);
        club.anyadirActividad(grupo2);
        double ingresosEsperados = (grupo1.getMatriculados() * grupo1.getTarifa()) + 
                                  (grupo2.getMatriculados() * grupo2.getTarifa());
        
        // Act
        double actualIngresos = club.ingresos();
        
        // Assert
        assertEquals(ingresosEsperados, actualIngresos);
    }

    @Test
    @DisplayName("Se puede añadir una actividad correctamente")
    void anyadir_actividad() throws ClubException {
        // Arrange
        String[] datos = {"G3", "Natacion", "25", "5", "30.0"};
        int plazasAntes = club.plazasLibres("Natacion");
        
        // Act
        club.anyadirActividad(datos);
        
        // Assert
        assertEquals(plazasAntes + 20, club.plazasLibres("Natacion"));
    }

    @Test
    @DisplayName("Se puede añadir un grupo ya existente y actualizar su numero de plazas")
    void anyadir_grupo_existente() throws ClubException {
        // Arrange
        club.anyadirActividad(grupo1);
        int plazasAntes = club.plazasLibres("Futbol");
        Grupo grupoDuplicado = new Grupo("G1", "Futbol", 25, 10, 50.0);
        
        // Act
        club.anyadirActividad(grupoDuplicado);
        
        // Assert
        assertEquals(plazasAntes + 5, club.plazasLibres("Futbol"));
    }

    @Test
    @DisplayName ("Suma el numero de plazas libres de todos los grupos de una actividad y comprueba que el resultado sea el esperado")
    void plazas_libres() throws ClubException {
        // Arrange
        club.anyadirActividad(grupo1);
        club.anyadirActividad(grupo2);
        int expectedPlazas = grupo1.plazasLibres() + grupo2.plazasLibres();
        
        // Act
        int actualPlazas = club.plazasLibres("Futbol") + club.plazasLibres("Baloncesto");
        
        // Assert
        assertEquals(expectedPlazas, actualPlazas);
    }

    @Test
    @DisplayName ("Comprueba que se imprima correctamente")
    void club_toString() throws ClubException {
        // Arrange
        club.anyadirActividad(grupo1);
        club.anyadirActividad(grupo2);
        String expectedString = "Club Soraya --> [ " + grupo1.toString() + ", " + grupo2.toString() + " ]";
        
        // Act
        String actualString = club.toString();
        
        // Assert
        assertEquals(expectedString, actualString);
    }

    
    @Test
    @DisplayName ("Comprobamos que no se pueda añadir más grupos de los que se pueden")
    void anyadir_grupo_maximo() throws ClubException {
        // Arrange
        Grupo grupo3 = new Grupo("G3", "Tenis", 20, 10, 50.0);
        Grupo grupo4 = new Grupo("G4", "Padel", 15, 5, 40.0);
        Grupo grupo5 = new Grupo("G5", "Yoga", 30, 26, 50.0);
        club.anyadirActividad(grupo1);
        club.anyadirActividad(grupo2);
        club.anyadirActividad(grupo3);
        club.anyadirActividad(grupo4);
        club.anyadirActividad(grupo5);
        Grupo grupo6 = new Grupo("G6", "Musculación", 24, 15, 50.0);
        
        // Act & Assert
        assertThrows(ClubException.class, () -> club.anyadirActividad(grupo6));
    }
    


    @Test
    @DisplayName("No se puede matricular un número negativo de personas")
    void matricular_num_negativo() {
        // Arrange
        int personasMatricular = -5;
        
        // Act & Assert
        assertThrows(ClubException.class, () -> club.matricular("Futbol", personasMatricular));
    }

    
    @Test
    @DisplayName("No se puede matricular en una actividad inexistente")
    void matricular_actividad_inexistente() {
        // Arrange
        int personasMatricular = 5;
        
        // Act & Assert
        assertThrows(ClubException.class, () -> club.matricular("Natacion", personasMatricular));
    }


    @Test
    @DisplayName("Comprueba que se pueda matricular en dos grupos diferentes si es necesario")
    public void matricula_en_grupos_diferentes() throws ClubException{
        // Arrange
        Grupo grupo3 = new Grupo("G3", "Futbol", 15, 10, 50.0);
        club.anyadirActividad(grupo1);
        club.anyadirActividad(grupo3);

        // Act
        club.matricular("Futbol", 12);

        // Assert
        assertEquals(3, club.plazasLibres("Futbol"));
    }
    
    @Test
    @DisplayName("Comprueba que matricular en una actividad cuyo nombre está vacío lanza una excepción")
    void matricular_actividad_vacia() {
        // Arrange
        int personasMatricular = 5;
        
        // Act & Assert
        assertThrows(ClubException.class, () -> club.matricular("", personasMatricular));
    }
}
