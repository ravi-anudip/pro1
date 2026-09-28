<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/jsp/common/header.jsp">
    <jsp:param name="title" value="Employees"/>
</jsp:include>

<c:if test="${not empty success}">
    <div class="alert alert-success alert-dismissible fade show">
        <c:out value="${success}"/>
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
</c:if>

<div class="card shadow-sm">
    <div class="card-header bg-white d-flex flex-wrap justify-content-between align-items-center gap-2">
        <h5 class="mb-0">All Employees <span class="badge bg-secondary">${employees.size()}</span></h5>
        <form action="${ctx}/employees" method="get" class="d-flex gap-2">
            <input type="text" name="keyword" value="<c:out value='${keyword}'/>" class="form-control"
                   placeholder="Search name, email, department">
            <button class="btn btn-outline-primary" type="submit"><i class="bi bi-search"></i></button>
            <c:if test="${not empty keyword}">
                <a href="${ctx}/employees" class="btn btn-outline-secondary">Clear</a>
            </c:if>
        </form>
    </div>

    <div class="table-responsive">
        <table class="table table-hover align-middle mb-0">
            <thead class="table-light">
            <tr>
                <th>#</th><th>Name</th><th>Email</th><th>Department</th>
                <th class="text-end">Salary</th><th>Joined</th><th class="text-center">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="emp" items="${employees}">
                <tr>
                    <td>${emp.id}</td>
                    <td><a href="${ctx}/employees/${emp.id}" class="text-decoration-none fw-semibold">
                        <c:out value="${emp.fullName}"/></a></td>
                    <td><c:out value="${emp.email}"/></td>
                    <td><span class="badge text-bg-info"><c:out value="${emp.department}"/></span></td>
                    <td class="text-end"><fmt:formatNumber value="${emp.salary}" pattern="#,##0.00"/></td>
                    <td>${emp.joiningDate}</td>
                    <td class="text-center">
                        <a href="${ctx}/employees/${emp.id}/edit" class="btn btn-sm btn-outline-primary">
                            <i class="bi bi-pencil"></i></a>
                        <form action="${ctx}/employees/${emp.id}/delete" method="post" class="d-inline"
                              onsubmit="return confirm('Delete this employee?');">
                            <button type="submit" class="btn btn-sm btn-outline-danger">
                                <i class="bi bi-trash"></i></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty employees}">
                <tr><td colspan="7" class="text-center text-muted py-4">No employees found.</td></tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>


