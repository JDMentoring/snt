package bigpic.servlets;

import bigpic.bean.PhotoAlbum;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@WebServlet(name = "DisplayAlbumServlet", urlPatterns = {"/DisplayAlbumServlet"})
//https://docs.oracle.com/javaee/7/tutorial/servlets011.htm
@MultipartConfig()


public class DisplayAlbumServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        PhotoAlbum pa = PhotoAlbum.getPhotoAlbum(context);

       //TODO Read about getContentType
        if (req.getContentType() != null &&
                req.getContentType().startsWith("multipart/form-data")) {
            this.uploadPhoto(req, pa);
        }

        resp.setContentType("text/html; charset=UTF-8");

        try (PrintWriter writer = resp.getWriter()) {
            displayPage(pa, writer);
        }

    }


    /**
     * DATA ROAD MAP
     * FROM http request => PART => ByteArrayOutputStream TO => Photo Album
     */
    private void uploadPhoto(HttpServletRequest req, PhotoAlbum pa) throws ServletException, IOException {


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String fileName = null;

        //TODO WHAT IS PART?
        for (Part p : req.getParts()) {
            this.copyBytes(p.getInputStream(), baos);
            fileName = p.getSubmittedFileName();
        }

        if (!"".equals(fileName)) {
            String photoName = fileName.substring(0, fileName.lastIndexOf("."));
            pa.addPhoto(photoName, baos.toByteArray());
        }
    }

    private void displayPage(PhotoAlbum pa, PrintWriter writer) {

        writer.write("<!doctype html>");
        writer.write("<html lang=\"en\">");
        writer.write("<head>");
        writer.write(" <meta charset=\"utf-8\">");
        writer.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");
        writer.write("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css\"" +
                "          integrity=\"sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB\" crossorigin=\"anonymous\">");
        writer.write("</head>");
        writer.write("<body class=\"bg-secondary\">");

        this.displayAlbum(pa, "", writer);

        writer.write("<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\"\n" +
                "        integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\"\n" +
                "        crossorigin=\"anonymous\"></script>");
        writer.write("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\"\n" +
                "        integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\"\n" +
                "        crossorigin=\"anonymous\"></script>");
        writer.write("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js\"\n" +
                "        integrity=\"sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T\"\n" +
                "        crossorigin=\"anonymous\"></script>");
        writer.write("</body>");
        writer.write("</html>");
    }

    private void displayAlbum(PhotoAlbum pa, String label, PrintWriter writer) {
        writer.write("<main role=\"main\">\n" +
                "    <section class=\"bg-dark text-center\">\n" +
                "        <div class=\"container\">\n" +
                "            <h1 class=\"pt-5 pb-1 text-light\">YOUR PHOTO ALBUM</h1>\n" +
                "            <form action=\"DisplayAlbumServlet\" method=\"post\" enctype=\"multipart/form-data\">\n" +
                "                <div class=\"input-group pb-5\">\n" +
                "                    <div class=\"custom-file\">\n" +
                "                        <input type=\"file\" class=\"custom-file-input\" name=\"myFile\" accept=\"image/jpeg\"\n" +
                "                               id=\"inputGroupFile04\">\n" +
                "                        <label class=\"custom-file-label\" for=\"inputGroupFile04\">...</label>\n" +
                "                    </div>\n" +
                "                    <div class=\"input-group-append\">\n" +
                "                        <button class=\"btn btn-primary\" type=\"submit\">Upload</button>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </form>\n" +
                "        </div>\n" +
                "    </section>");

        writer.write("<div class=\"album py-5\">\n" +
                "        <div class=\"container\">\n" +
                "            <div class=\"row\">");


        for (int i = 0; i < pa.getPhotoQuantity(); i++) {
            writer.write(" <div class=\"col-md-4\">\n" +
                    "                    <div class=\"card mb-4 shadow\">\n" +
                    "                        <img class=\"card-img-top\" src='./DisplayPhotoServlet?photo="+i+"'>\n" +
                    "                        <div class=\"card-body p-3\">\n" +
                    "                            <div class=\"d-flex justify-content-center\">\n" +
                    "                                <div class=\"btn-group\">\n" +
                    "                                    <a href='./DisplayPhotoServlet?photo="+i+"' class=\"btn btn-outline-info\">Open</a>\n" +
                    "                                    <a href='RemovePhotoServlet?photo="+i+"' class=\"btn btn-outline-danger\">Delete</a>\n" +
                    "                                </div>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </div>");
        }


        writer.write("            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</main>");

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
