<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">

        <c:url value="/process-login" var="loginUrl" />

        <form class="form-signin" role="form" action="${loginUrl}" method="post">
            <h2 class="form-signin-heading">Please sign in</h2>
            <input type="test" class="form-control" placeholder="Username" name="j_username" required="" autofocus="">
            <input type="password" class="form-control" placeholder="Password" name="j_password" required="">
            <div class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div>
            <button class="btn btn-primary btn-block" type="submit">Sign in</button>
        </form>

    </tiles:putAttribute>
</tiles:insertDefinition>










<%--
<div>
    <h2>Sign in to Helix</h2>

<!-- Путь к фильтру аутентификации -->

<form method="post" class="signin" action="${authUrl}">
    <fieldset>
        <table cellspacing="0">
            <tr>
                <th><label for="username_or_email">Username or Email</label></th>
                <td><input id="username_or_email"
                               name="j_username"
                           type="text" /> <!-- Поле ввода имени пользователя -->
                </td>
            </tr>
            <tr>
                <th><label for="password">Password</label></th>
                <td><input id="password"
                           name="j_password"
                           type="password" /> <!-- Поле ввода пароля -->
                    <small><a href="/account/resend_password">Forgot?</a></small>
                </td>
            </tr>
            <tr>
                <th></th>
                <td><input id="remember_me"name="_spring_security_remember_me"
                           type="checkbox"/> <!-- Флажок "запомнить меня" -->
                    <label for="remember_me"
                           class="inline">Remember me</label></td>
            </tr>
            <tr>
                <th></th>
                <td><input name="commit" type="submit" value="Sign In" /></td>
            </tr>
        </table>
    </fieldset>
</form>
</div>--%>
