

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarritoTest {

    private CarritoService service;

    @BeforeEach
    void setUp() {
        service = new CarritoService();
    }

    @Test
    @DisplayName("Subtotal con varios productos (precio * cantidad)")
    void subtotal_variosProductos() {
        List<Producto> carrito = List.of(
            new Producto("teclado", 30, 2),  
            new Producto("raton",   10, 1)  
        );
        assertEquals(70.0, service.calcularSubtotal(carrito), 0.01);
    }

    @Test
    @DisplayName("Subtotal con un solo producto")
    void subtotal_unSoloProducto() {
        List<Producto> carrito = List.of(
            new Producto("monitor", 200, 1) 
        );
        assertEquals(200.0, service.calcularSubtotal(carrito), 0.01);
    }

    @Test
    @DisplayName("Subtotal con carrito vacío devuelve 0")
    void subtotal_carritoVacio() {
        assertEquals(0.0, service.calcularSubtotal(Collections.emptyList()), 0.01);
    }

    @Test
    @DisplayName("Descuento 0% no modifica el subtotal")
    void descuento_cero() {
        assertEquals(100.0, service.aplicarDescuento(100.0, 0), 0.01);
    }

    @Test
    @DisplayName("Descuento válido del 10%")
    void descuento_valido10() {
        assertEquals(90.0, service.aplicarDescuento(100.0, 10), 0.01);
    }

    @Test
    @DisplayName("Descuento del 100% resulta en 0€")
    void descuento_100() {
        assertEquals(0.0, service.aplicarDescuento(100.0, 100), 0.01);
    }

    @Test
    @DisplayName("Descuento negativo aumenta el subtotal (comportamiento esperado: lanzar excepción o rechazar)")
    void descuento_invalido_negativo() {
        double resultado = service.aplicarDescuento(100.0, -10);
        assertTrue(resultado <= 100.0, "Un descuento negativo no debería aumentar el precio");
    }

    @Test
    @DisplayName("Descuento mayor de 100 es inválido")
    void descuento_invalido_mayor100() {
        double resultado = service.aplicarDescuento(100.0, 150);
        // Un descuento mayor de 100 produciría precio negativo: también inválido
        assertTrue(resultado >= 0, "El precio tras descuento no puede ser negativo");
    }

    @Test
    @DisplayName("Envío: subtotal menor que 100 → 5€")
    void envio_subtotalMenorQue100() {
        assertEquals(5.0, service.calcularEnvio(50.0), 0.01);
    }

    @Test
    @DisplayName("Envío: subtotal igual a 100 → 0€ (envío gratis)")
    void envio_subtotalIgualA100() {
        assertEquals(0.0, service.calcularEnvio(100.0), 0.01);
    }

    @Test
    @DisplayName("Envío: subtotal mayor que 100 → 0€ (envío gratis)")
    void envio_subtotalMayorQue100() {
        assertEquals(0.0, service.calcularEnvio(150.0), 0.01);
    }


    @Test
    @DisplayName("Total sin descuento, carrito pequeño con envío")
    void total_sinDescuento_conEnvio() {
        List<Producto> carrito = List.of(
            new Producto("teclado", 30, 2),
            new Producto("raton",   10, 1)
        );
        assertEquals(75.0, service.calcularTotal(carrito, 0), 0.01);
    }

    @Test
    @DisplayName("Total con descuento del 10% y envío gratis")
    void total_conDescuento_envioGratis() {
        List<Producto> carrito = List.of(
            new Producto("monitor", 200, 1)
        );
        assertEquals(180.0, service.calcularTotal(carrito, 10), 0.01);
    }

    @Test
    @DisplayName("Total: pedido con envío gratis (subtotal >= 100 antes del descuento)")
    void total_envioGratis() {
        List<Producto> carrito = List.of(
            new Producto("auriculares", 50, 2)
        );
        assertEquals(100.0, service.calcularTotal(carrito, 0), 0.01);
    }
}
