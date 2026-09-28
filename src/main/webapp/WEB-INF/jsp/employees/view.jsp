<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/jsp/common/header.jsp">
    <jsp:param name="title" value="Employee Details"/>
</jsp:include>

<div class="card shadow-sm mx-auto" style="max-width: 640px;">
    <div class="card-header bg-white"><h5 class="mb-0"><c:out value="${employee.fullName}"/></h5></div>
    <ul class="list-group list-group-flush">
        <li class="list-group-item"><strong>ID:</strong> ${employee.id}</li>
        <li class="list-group-item"><strong>Email:</strong> <c:out value="${employee.email}"/></li>
        <li class="list-group-item"><strong>Department:</strong> <c:out value="${employee.department}"/></li>
        <li class="list-group-item"><strong>Salary:</strong>
            <fmt:formatNumber value="${employee.salary}" pattern="#,##0.00"/></li>
        <li class="list-group-item"><strong>Joined:</strong> ${employee.joiningDate}</li>
    </ul>
    <div class="card-footer bg-white d-flex gap-2">
        <a href="${ctx}/employees/${employee.id}/edit" class="btn btn-primary">Edit</a>
        <a href="${ctx}/employees" class="btn btn-outline-secondary">Back</a>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>