
package com.br.ifpb.servlet;

import com.br.ifpb.businessObject.GerenciarFotos;
import com.br.ifpb.execoes.PersistenciaException;
import com.br.ifpb.valueObject.Usuario;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
 */
@WebServlet(name="PublicaFoto", urlPatterns={"/publica-foto"})
public class PublicaFoto extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

    } 

    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
                Usuario usuario = ((Usuario) request.getSession().getAttribute("usuario"));
        if (usuario == null) {
            response.sendRedirect("");
        } else {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List<FileItem> items = null;
                try {
                    items = (List<FileItem>) upload.parseRequest(request);
                } catch (FileUploadException ex) {
                    Logger.getLogger(UploadImagemPerfil.class.getName()).log(Level.SEVERE, null, ex);
                }
                FileItem item = items.get(0);
                if (item != null) {
                    String nome_arquivo = String.valueOf(new Date().getTime());
                    String caminho = getServletContext().getRealPath("/imagens") + "\\" + usuario.getId() + "\\";
                    File file = new File(caminho);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File uploadedFile = new File(caminho + nome_arquivo);
                    try {
                        item.write(uploadedFile);
                    } catch (Exception ex) {
                        Logger.getLogger(UploadImagemPerfil.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    GerenciarFotos gerenciarFotos=new GerenciarFotos();
                    try {
                        gerenciarFotos.publicarFoto("imagens" + "\\" + usuario.getId() + "\\" + nome_arquivo
                                , Timestamp.valueOf(LocalDateTime.now()), usuario);
                    } catch (PersistenciaException ex) {
                        Logger.getLogger(UploadImagemPerfil.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    response.sendRedirect("fotos");
                } else {

                }
            }

        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
