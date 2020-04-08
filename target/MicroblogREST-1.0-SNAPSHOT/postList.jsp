<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <title>Post</title>
</head>

<body>

    <div class="d-flex justify-content-center align-items-center login-container">
        <div class="login-form text-center">
            <form action="CheckUser">
                <h1 class="mb-5 font-weight-light text-uppercase">Microblog</h1>
                <button type="submit" class="btn mt-5 rounded-pill btn-lg btn-custom btn-block text-uppercase">Create a
                    new
                    post</button>
            </form>
            <br>
            <p class="mt-3 font-weight-normal">or</p>
            <button type="submit" class="btn mt-5 rounded-pill btn-lg btn-custom btn-block text-uppercase"
                onclick="location.href = '#firstComment';">See posts</button>
        </div>

    </div>

    <a id="firstComment"></a>
    <div class="d-flex justify-content-center align-items-center login-container row-cols-3">
        <%@ page import="com.mycompany.microblog.entities.*" %> 
        <%@ page import="com.mycompany.microblog.DAO.*" %> 
        <%@ page import="java.util.List" %>
        
        <% List<Post> listaPost = (List<Post>) request.getAttribute("ListaPost"); %>
        <% for (int i = 0; i<listaPost.size(); i++) {%>
        <% Post post = listaPost.get(i); %>
        
        <div class="login-form text-center">
            <h1 class="mb-5 font-weight-light text-uppercase"><% out.print(post.getTitolo()); %></h1>
            <p class="mt-3 font-weight-normal"><% out.print(post.getUtente().getUsername()); %></p>
            <p class="mt-3 font-weight-normal"><% out.print(post.getDataOra().toString()); %></p>
            <p class="mt-3 font-weight-normal"><% out.print(post.getTesto()); %></p>
            <hr>
            <br>
            <h4 class="mb-5 font-weight-light text-uppercase">Comments</h4>
            <hr>
            <div class="comments">
                <% List<Commento> listaCommenti = (List<Commento>) CommentoJpaController.findCommentoByPost(post);%>    
                <%for (int c = 0; c<listaCommenti.size(); c++) {%>
                <% Commento commento = listaCommenti.get(c);%>
                
                <div class="comment">
                    <p class="mt-3 font-weight-normal"><% out.print(commento.getUtente().getUsername());%></p>
                    <p class="mt-3 font-weight-normal"><% out.print(commento.getDataOra().toString());%></p>
                    <p class="mt-3 font-weight-normal"><% out.print(commento.getTesto());%></p>
                    <br>
                    <hr>
                </div>
                  <%};%>
            </div>
            <form action="CheckUserComment">
                <% long postId = post.getId();%>
                <input type="hidden" name="postId" value="<%=postId%>">
                <button type="submit" class="btn mt-5 rounded-pill btn-lg btn-custom btn-block text-uppercase">Leave a
                comment</button>
            </form>
            
        </div>
        
        <%};%>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>

</html>