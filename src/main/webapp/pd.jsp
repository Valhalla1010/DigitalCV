<%@ page import="com.example.digitalcv.TOTP" %><%--
  Created by IntelliJ IDEA.
  User: davii
  Date: 4/29/2024
  Time: 12:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html>
<link href="CSS/prof.css" rel="stylesheet" type="text/css"/>

<head>
    <title>Skapa-CV</title>
</head>

<body>
<div class="header">
    <div class="logo"><img src="Images/cv.jpg" alt="logo"/>
        <h1 class="text">DigitalCV</h1>
    </div>
    <div class="dropdown">
        <button class="dropbtn">Meny</button>
        <div  class="dropdown-content">
            <a class="menu-item" href="create_acc.xhtml">Spara_CV</a>
            <a class="menu-item" href="profil1.xhtml">Back to the profile</a>
        </div>
    </div>
</div>
<form action="CV" method="get">
    <div class="profile">
    Foto       <br/> <input type="file">
    Förnamn    <br/> <input type="text" name="f"/><br/><br/>
    Efternamn  <br/> <input type="text" name="l"/><br/><br/>
    Adress     <br/> <input type="text" name="a"><br/><br/>
    Mobile     <br/>  <input type="text" name="m"><br/><br/>
    Email      <br/> <input type="email" name="to"><br/><br/>
    GitHub-Länk <br/><input type="text" name="g"><br/><br/>
    Profil      <br/><input type="text" name="p"><br/><br/>
    Personbrev  <br/><input type="text" name="per"><br/><br/>
    Utbildning  <br/><input type="text" name="u"><br/><br/>
    Arbetslivserfarenhet   <br/><input type="text" name="arb"><br/><br/>
    Programfärdighet       <br/><input type="text" name="prog"><br/><br/>
       <p>
        <br>
        <input type="submit" value="Skapa PDF"/>
        <br>
    </div>
</form>
</body>
</html>
