/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NUWAA
 */
public class EmployeeModel {

    private Connection dbConnection;

    public EmployeeModel() {
        dbConnection = dbconnection.DBConnection.getConnection();
    }

    public String register(String name, String contactNo) {
        String query = "INSERT INTO employee(name, contact_no, status) VALUE(?,?,?)";
        try {
            PreparedStatement psm = dbConnection.prepareStatement(query);
            psm.setString(1, name);
            psm.setString(2, contactNo);
            psm.setInt(3, 1);
            psm.execute();
            return "Register Successful";
        } catch (Exception e) {
            e.printStackTrace();
            return "Register Error";
        }
    }

    public String update(String employeeNo, String name, String contactNo) {
        String query = "UPDATE employee SET  name = ?, contact_no = ? WHERE employee_no = ?";
        try {
            PreparedStatement psm = dbConnection.prepareStatement(query);
            psm.setString(1, name);
            psm.setString(2, contactNo);
            psm.setString(3, employeeNo);
            psm.execute();
            return "Update Successful";
        } catch (Exception e) {
            e.printStackTrace();
            return "Update Error";
        }
    }

    public String update(String employeeNo) {
        String query = "UPDATE employee SET  status = ? WHERE employee_no = ?";
        try {
            PreparedStatement psm = dbConnection.prepareStatement(query);
            psm.setInt(1, 0);
            psm.setString(2, employeeNo);
            psm.execute();
            return "Update Successful";
        } catch (Exception e) {
            e.printStackTrace();
            return "Update Error";
        }
    }

    public void loadEmployeeTable(String keyword, DefaultTableModel employeeTableModel) {
        String query = "SELECT * FROM employee WHERE (employee_no LIKE ? OR name LIKE ? OR contact_no LIKE ?) AND status = ?";

        try {
            PreparedStatement psm = dbConnection.prepareStatement(query);
            psm.setString(1, "%" + keyword + "%");
            psm.setString(2, "%" + keyword + "%");
            psm.setString(3, "%" + keyword + "%");
            psm.setInt(4, 1);
            ResultSet rs = psm.executeQuery();
            Object[] rowData = null;
            employeeTableModel.setRowCount(0);
            while (rs.next()) {
                int employeeNo = rs.getInt("employee_no");
                String name = rs.getString("name");
                String contactNo = rs.getString("contact_no");
                rowData = new Object[]{employeeNo, name, contactNo};
                employeeTableModel.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object[] getRowData(int employeeNo) {
        String query = "SELECT * FROM employee WHERE employee_no = ?";

        try {
            PreparedStatement psm = dbConnection.prepareStatement(query);
            psm.setInt(1, employeeNo);
            ResultSet rs = psm.executeQuery();
            Object[] rowData = null;
            while (rs.next()) {
                int employeeNoo = rs.getInt("employee_no");
                String name = rs.getString("name");
                String contactNo = rs.getString("contact_no");
                rowData = new Object[]{employeeNoo, name, contactNo};
                return rowData;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
