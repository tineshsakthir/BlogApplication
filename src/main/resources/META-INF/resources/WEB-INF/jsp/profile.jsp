<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <h1 class="text-center mt-4">hi ${name}</h1>
    <p class="text-center mt-4">You have totaly <span>${count}</span> blogs </p>
    <div class="text-center mt-4">
        <a href="add-blog" class="btn btn-primary">Add Blog</a>
    </div>
    <div class="text-center mt-4">
        <a href="edit-blogs" class="btn btn-dark">Edit My Blogs</a>
    </div>
</div>

<%@ include file="common/footer.jspf" %>
