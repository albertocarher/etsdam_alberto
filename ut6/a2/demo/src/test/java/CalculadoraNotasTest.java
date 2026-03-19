

import calculadora.CalculadoraNotas;

import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;


public class CalculadoraNotasTest {

    @Test
    void testMediaSimple(){
        assertEquals(7, CalculadoraNotas.calcularMedia(new int[]{6,7,8}));
    }

    @Test
    void testMediaListaVacia(){
        assertEquals(new IllegalArgumentException(), CalculadoraNotas.calcularMedia(new int[]{}));
    }

    @Test
    void testMediaDecimal() {
        assertEquals(6.5, CalculadoraNotas.calcularMedia(new int[]{6, 7}));
    }

    @Test
    void testNotaFueraDeRango() {
        assertEquals(-1.0, CalculadoraNotas.calcularMedia(new int[]{6, 11, 8}));
    }

    @Test
    void testListaVacia() {
        assertEquals(-1.0, CalculadoraNotas.calcularMedia(new int[]{}));
    }




}