<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <h2>Enter Blog Details</h2>
    <form:form method="POST" modelAttribute="blog" enctype="multipart/form-data">
        <form:input type="hidden" path="id"/>

        <fieldset class="mb-3">
            <form:label path="title">Title</form:label>
            <form:input type="text" path="title"/>
            <form:errors path="title" cssClass="text-warning"/>
        </fieldset>

        <form:input type="hidden" path="username"/>

        <fieldset class="mb-3">
            <form:label path="content">Content</form:label>
            <form:input type="text" path="content" />
            <form:errors path="content" cssClass="text-warning"/>
        </fieldset>

        <fieldset class="mb-3">
            <form:label path="image">Image</form:label>
            <input type="file" name="imageFile" accept="image/*" />
            <form:errors path="image" cssClass="text-warning"/>
        </fieldset>

        <form:input type="hidden" path="dateOfCreation" />
        <form:input type="hidden" path="lastEdited"/>

        <input type="submit" class="btn btn-success"/>
    </form:form>
</div>

<%@include file="common/footer.jspf" %>
