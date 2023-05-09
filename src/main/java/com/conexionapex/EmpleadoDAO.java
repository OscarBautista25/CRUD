package com.conexionapex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    private Connection conexion;

    public EmpleadoDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    public List<Empleado> listarEmpleados() throws SQLException {
        List<Empleado> empleados = new ArrayList<>();
        
        String consulta = "SELECT * FROM empleados";
        
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(consulta)) {
            while (rs.next()) {
                Empleado empleado = new Empleado(consulta, consulta, consulta, 0);
                empleado.setId(0);(rs.getInt("ID"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));
                empleado.setSalario(rs.getDouble("salario"));
                empleados.add(empleado);
            }
        }
        
        return empleados;
    }
    
    public Empleado buscarEmpleado(int codigo) throws SQLException {
        Empleado empleado = null;
        
        String consulta = "SELECT * FROM empleados WHERE codigo = ?";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
            pstmt.setInt(1, codigo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    empleado = new Empleado(consulta, consulta, consulta, codigo);
                    empleado.setId(0);(rs.getInt("ID"));
                    empleado.setNombre(rs.getString("nombre"));
                    empleado.setApellido(rs.getString("apellido"));
                    empleado.setSalario(rs.getDouble("salario"));
                }
            }
        }
        
        return empleado;
    }
    
    public void insertarEmpleado(Empleado empleado) throws SQLException {
        String consulta = "INSERT INTO empleados (codigo, nombre, apellido, edad, salario) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
            pstmt.setInt(1, empleado.getCodigo());
            pstmt.setString(2, empleado.getNombre());
            pstmt.setString(3, empleado.getApellido());
            pstmt.setInt(4, empleado.getEdad());
            pstmt.setDouble(5, empleado.getSalario());
            pstmt.executeUpdate();
        }
    }
    
    public void actualizarEmpleado(Empleado empleado) throws SQLException {
        String consulta = "UPDATE empleados SET nombre = ?, apellido = ?, edad = ?, salario = ? WHERE codigo = ?";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, empleado.getApellido());
            pstmt.setDouble(4, empleado.getSalario());
            pstmt.setInt(5, empleado.getId());
            pstmt.executeUpdate();
        }
    }
    
    public void eliminarEmpleado(int codigo) throws SQLException {
        String consulta = "DELETE FROM empleados WHERE codigo = ?";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
            pstmt.setInt(1, codigo);
            pstmt.executeUpdate();
        }
    }

    public boolean eliminarEmpleado(int id) {
        try (PreparedStatement ps = conexion.prepareStatement("DELETE FROM empleados WHERE id = ?")) {
            ps.setInt(1, id);
            int resultado = ps.executeUpdate();
            return resultado > 0;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar empleado: " + ex.getMessage());
            return false;
        }
    }
}
    