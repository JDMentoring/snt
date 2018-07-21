package bigpic.servlets;

import bigpic.bean.PhotoAlbum;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


@WebServlet(name = "UploadPhotoServlet", urlPatterns = {"/UploadPhotoServlet"})
@MultipartConfig()
public class UploadPhotoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PhotoAlbum pa = PhotoAlbum.getPhotoAlbumS(session);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String fileName = null;

        for (Part p : req.getParts()) {
            this.copyBytes(p.getInputStream(), baos);
            fileName = p.getSubmittedFileName();
        }

        if (!"".equals(fileName)) {
            String photoName = fileName.substring(0, fileName.lastIndexOf("."));
            pa.addPhoto(photoName, baos.toByteArray());
        }

        RequestDispatcher rd = req.getRequestDispatcher("album/displayAlbum.jsp");
        rd.forward(req, resp);
    }

    private void copyBytes(InputStream inputStream, OutputStream os) throws ServletException, IOException {
        int i;
        while ((i = inputStream.read()) != -1) {
            os.write(i);
        }
        inputStream.close();
        os.close();
    }
}
