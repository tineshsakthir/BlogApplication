<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<main>
    <div class="container">
        <h1 class="text-center">The World's Cutting Edge Blogs</h1>
        <div class="row justify-content-center">
            <c:forEach items="${blogList}" var="blog">
                <div class="col-md-6 col-lg-4">
                    <div class="blog-post mb-4">
                        <div class="blog-header">
                            <h2>${blog.title}</h2>
                            <p class="created-at">Created At: ${blog.dateOfCreation.toLocalDate()}, ${blog.dateOfCreation.toLocalTime()}</p>
                            <p class="last-edited">Last Edited At: ${blog.lastEdited.toLocalDate()}, ${blog.lastEdited.toLocalTime()}</p>
                        </div>
                        <div class="blog-content">
                            <p>${blog.content}</p>
                            <img src="data:image/jpeg;base64,${blog.image}" alt="Blog Image" class="img-fluid mb-3">
                            <!-- Add more content here if needed -->
                        </div>
<%--                        <div class="blog-actions">--%>
<%--                            <a href="update-blog?id=${blog.id}" class="btn btn-warning">Update</a>--%>
<%--                            <a href="delete-blog?id=${blog.id}" class="btn btn-danger">Delete</a>--%>
<%--                        </div>--%>
                    </div>
                </div>
            </c:forEach>
        </div>
<%--        <div class="text-center mt-4">--%>
<%--            <a href="add-blog" class="btn btn-primary">Add Blog</a>--%>
<%--        </div>--%>
    </div>
</main>

<%@ include file="common/footer.jspf" %>
