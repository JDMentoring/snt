package servlets_big_pic;

import bigpic.PhotoAlbum;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemovePhotoServlet", urlPatterns = {"/RemovePhotoServlet"})

public class RemovePhotoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int index = Integer.parseInt(req.getParameter("photo"));
        PhotoAlbum pa = PhotoAlbum.getPhotoAlbum(req.getServletContext());
        pa.removePhoto(index);
        RequestDispatcher rd = req.getRequestDispatcher("DisplayAlbumServlet");
        rd.forward(req, resp);
    }
}
