/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author fredi
 */
@WebServlet(name = "VerMascotaServlet", urlPatterns = {"/vermascotaservlet"})
public class VerMascotaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            File file = new File("C://XML/mascota.xml");
            if (file.exists()) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(file);
                
                document.getDocumentElement().normalize();

                Element root = document.getDocumentElement();
                String nombre = root.getElementsByTagName("nombre").item(0).getTextContent();
                String edad = root.getElementsByTagName("edad").item(0).getTextContent();
                String tipo = root.getElementsByTagName("tipo").item(0).getTextContent();

                out.println("<h2>Datos de la mascota</h2>");
                out.println("<p>Nombre: " + nombre + "</p>");
                out.println("<p>Edad: " + edad + "</p>");
                out.println("<p>Tipo: " + tipo + "</p>");
            } else {
                out.println("<p>Archivo XML no encontrado.</p>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Ocurrió un error al leer el archivo XML." + e);
        }
    }
}
