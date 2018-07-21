<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="photo-tag" uri="personalTags" %>


<html>

<jsp:useBean id="photoAlbum" scope="session" class="bigpic.bean.PhotoAlbum"/>
<jsp:setProperty name="photoAlbum" property="session" value="<%=session%>"/>


<head>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Album Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
</head>

<body class="bg-secondary">

<main role="main">
    <section class="bg-dark text-center">
        <div class="container">
            <h1 class="pt-5 pb-1 text-light">YOUR PHOTO ALBUM</h1>
            <form action="/UploadPhotoServlet" method="post" enctype="multipart/form-data">
                <div class="input-group pb-5">
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" name="myFile" accept="image/jpeg"
                               id="inputGroupFile04">
                        <label class="custom-file-label" for="inputGroupFile04">...</label>
                    </div>
                    <div class="input-group-append">
                        <button class="btn btn-primary" type="submit">Upload</button>
                    </div>
                </div>
            </form>
        </div>
    </section>

    <div class="album py-5">
        <div class="container">
            <div class="row">

                <c:forEach var="item" begin="0" end="${photoAlbum.photoQuantity}">
                    <div class="col-md-4">
                        <div class="card mb-4 shadow">
                            <photo-tag:photo index='${item}' style="card-img-top"/>
                            <div class="card-body p-3">
                                <div class="d-flex justify-content-center">
                                    <div class="btn-group">
                                        <photo-tag:buttons index='${item}' openStyle="btn btn-outline-info"
                                                           deleteStyle="btn btn-outline-danger"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </div>
    </div>
</main>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
