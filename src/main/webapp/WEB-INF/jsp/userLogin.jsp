<%@include file="header.jsp"%>
<%@include file="adminPanel.jsp"%>
<div class="container myrow-container">
	<div class="panel panel-success">
		<div class="panel-heading">
			<h3 class="panel-title">
				<div align="left">
					<b>User Login Form</b>
				</div>
			</h3>
		</div>

		<div class="panel-body">
			<c:if test="${not empty error}">
				<span>${error}</span>
			</c:if>
			<c:if test="${not empty msg}">
				<span>${message}</span>
			</c:if>

			<form:form id="userLogin" cssClass="form-horizontal"
				action="<c:url value='j_spring_security_check' />" method="post">

				<div class="form-group">
					<div class="control-label col-xs-3">
						<form:label path="username">Email</form:label>
					</div>
					<div class="col-xs-6">
						<form:input cssClass="form-control" path="username" value=''
							autofocus="true" />
					</div>
				</div>

				<div class="form-group">
					<form:label path="password" cssClass="control-label col-xs-3">Password</form:label>
					<div class="col-xs-6">
						<form:input cssClass="form-control" path="password" value='' />
					</div>
				</div>

				<div class="form-group">
					<div class="row">
						<div class="col-xs-4"></div>
						<div class="col-xs-4">
							<input type="submit" id="saveUser" class="btn btn-primary"
								value="Login" onclick="return submitUserSignupForm();" />
						</div>
						<div class="col-xs-4"></div>
					</div>
				</div>
				<h4 class="text-center">
					<a href="createUser">Create an account</a>
				</h4>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form:form>
		</div>
	</div>
</div>

<%@include file="footer.jsp"%>