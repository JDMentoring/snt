package bigpic.servlets;

import bigpic.bean.PhotoAlbum;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "DisplayPhotoServlet", urlPatterns = {"/DisplayPhotoServlet"})

public class DisplayPhotoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int index = Integer.parseInt(req.getParameter("photo"));
        resp.setContentType("image/jpg");

        try (OutputStream out = resp.getOutputStream()) {
            ServletContext context = req.getServletContext();
            PhotoAlbum pa = PhotoAlbum.getPhotoAlbum(context);
            byte[] bytes = pa.getPhotoData(index);

            for (int i = 0; i < bytes.length; i++) {
                out.write(bytes[i]);
            }
        }
    }
}
