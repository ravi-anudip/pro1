<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/jsp/common/header.jsp">
    <jsp:param name="title" value="Error"/>
</jsp:include>

<div class="alert alert-danger">
    <h5 class="alert-heading">Oops!</h5>
    <p class="mb-0"><c:out value="${message}"/></p>
</div>
<a href="${ctx}/employees" class="btn btn-primary">Back to list</a>

<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>