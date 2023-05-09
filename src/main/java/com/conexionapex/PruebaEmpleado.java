package com.conexionapex;

public class PruebaEmpleado {
    public static void main(String[] args) {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        // Insertar empleado
        Empleado empleado1 = new Empleado("Juan", "Perez", 30, "juan.perez@example.com");
        boolean insertado = empleadoDAO.insertarEmpleado(empleado1);
        System.out.println("Empleado insertado: " + insertado);

        // Obtener empleado
        Empleado empleado2 = empleadoDAO.obtenerEmpleadoPorId(1);
        System.out.println("Empleado obtenido: " + empleado2);

        // Actualizar empleado
        empleado2.setEdad(31);
        boolean actualizado = empleadoDAO.actualizarEmpleado(empleado2);
        System.out.println("Empleado actualizado: " + actualizado);

        // Eliminar empleado
        boolean eliminado = empleadoDAO.eliminarEmpleado(1);
        System.out.println("Empleado eliminado: " + eliminado);
    }
}
