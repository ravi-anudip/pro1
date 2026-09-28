<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/jsp/common/header.jsp">
    <jsp:param name="title" value="${pageTitle}"/>
</jsp:include>

<div class="card shadow-sm mx-auto" style="max-width: 720px;">
    <div class="card-header bg-white"><h5 class="mb-0"><c:out value="${pageTitle}"/></h5></div>
    <div class="card-body">
        <form:form modelAttribute="employee" action="${ctx}/employees/save" method="post">
            <form:hidden path="id"/>
            <div class="row g-3">
                <div class="col-md-6">
                    <label class="form-label">First Name</label>
                    <form:input path="firstName" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                    <form:errors path="firstName" cssClass="invalid-feedback d-block"/>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Last Name</label>
                    <form:input path="lastName" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                    <form:errors path="lastName" cssClass="invalid-feedback d-block"/>
                </div>
                <div class="col-12">
                    <label class="form-label">Email</label>
                    <form:input path="email" type="email" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                    <form:errors path="email" cssClass="invalid-feedback d-block"/>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Department</label>
                    <form:select path="department" cssClass="form-select" cssErrorClass="form-select is-invalid">
                        <form:option value="" label="-- Select --"/>
                        <form:options items="${departments}"/>
                    </form:select>
                    <form:errors path="department" cssClass="invalid-feedback d-block"/>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Salary</label>
                    <form:input path="salary" type="number" step="0.01" cssClass="form-control"
                                cssErrorClass="form-control is-invalid"/>
                    <form:errors path="salary" cssClass="invalid-feedback d-block"/>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Joining Date</label>
                    <form:input path="joiningDate" type="date" cssClass="form-control"
                                cssErrorClass="form-control is-invalid"/>
                    <form:errors path="joiningDate" cssClass="invalid-feedback d-block"/>
                </div>
            </div>
            <div class="mt-4 d-flex gap-2">
                <button type="submit" class="btn btn-primary"><i class="bi bi-check-lg"></i> Save</button>
                <a href="${ctx}/employees" class="btn btn-outline-secondary">Cancel</a>
            </div>
        </form:form>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>