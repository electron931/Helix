<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">



        <h1>Home page !</h1>
        <p>The time on the server is ${serverTime}.</p>




    </tiles:putAttribute>
</tiles:insertDefinition>