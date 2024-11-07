/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author fredi
 */
@WebServlet(name = "MascotasXML", urlPatterns = {"/mascotasxml"})
public class MascotasXML extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Leer datos del formulario
        String nombre = request.getParameter("nombre");
        String edad = request.getParameter("edad");
        String tipo = request.getParameter("tipo");

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("mascota");
            document.appendChild(root);

            Element nombreElement = document.createElement("nombre");
            nombreElement.appendChild(document.createTextNode(nombre));
            root.appendChild(nombreElement);

            Element edadElement = document.createElement("edad");
            edadElement.appendChild(document.createTextNode(edad));
            root.appendChild(edadElement);

            Element tipoElement = document.createElement("tipo");
            tipoElement.appendChild(document.createTextNode(tipo));
            root.appendChild(tipoElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            File file = new File("C://XML/mascota.xml");
            file.getParentFile().mkdirs(); //Crear el directorio si no existe

            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            response.getWriter().println("Archivo XML creado con éxito en " + file.getAbsolutePath());

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
            response.getWriter().println("Ocurrió un error al crear el archivo XML." + e);
        }
    }
}
