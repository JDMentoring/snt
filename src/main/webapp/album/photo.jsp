<%@ page import="bigpic.bean.PhotoAlbum" %>
<%@ page import="java.io.OutputStream" %>
<%@ page contentType="image/jpeg" %>

<%
    OutputStream outputStream = response.getOutputStream();
    String indexString = request.getParameter("photo");
    int index = Integer.valueOf(indexString.trim());
    PhotoAlbum photoAlbum = PhotoAlbum.getPhotoAlbumS(session);
    byte[] photo = photoAlbum.getPhotoData(index);
    for (int i = 0; i < photo.length; i++) {
        outputStream.write(photo[i]);
    }
%>
