/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ifpb.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Emanuel
 */
@WebServlet(name = "UploadImagem", urlPatterns = {"/upload-imagem"})
public class UploadImagem extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String caminho=getServletContext().getRealPath("/paginas/imagens");
        response.getWriter().print(caminho);
    } 

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if(isMultipart) {
            try {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List<FileItem> items = (List<FileItem>) upload.parseRequest(request);
                for(FileItem item : items) {
                    if(item.isFormField()) {
                        //se for um campo normal de form
                        response.getWriter().println("Form field");
                        response.getWriter().println("Name:"+item.getFieldName());
                        response.getWriter().println("Value:"+item.getString());
                    } else {
                        //caso seja um campo do tipo file
                        response.getWriter().println("NOT Form field");
                        response.getWriter().println("Name:"+item.getFieldName());
                        response.getWriter().println("FileName:"+item.getName());
                        response.getWriter().println("Size:"+item.getSize());
                        response.getWriter().println("ContentType:"+item.getContentType());
                    }
                    String caminho=getServletContext().getRealPath("/paginas/imagens");
                    File uploadedFile = new File(caminho+"\\"+new Date().getTime()+"_"+item.getName());
                    item.write(uploadedFile);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
 
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
