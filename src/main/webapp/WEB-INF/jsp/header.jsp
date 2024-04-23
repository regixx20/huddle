<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<c:url var="css" value="/style.css" />

<html>
<head>
<meta charset="UTF-8">
<title>Club d'escalade</title>
    <style>


        body
        {
            margin:0;
            background-color: beige;

        }
        .menu
        {
            margin:0;
            padding:2px;
            background-color:#6f5499;
        }
        .menu .horizontal-menu .heading
        {
            color:white;
            font-size:40px;
             font-family:'Roboto', sans-serif;
        }
        .menu .horizontal-menu
        {
            list-style:none;
        }
        .menu .horizontal-menu .download
        {
            float:right;
            margin-right:15%;
            margin-top:1.5%;
        }
        .menu .horizontal-menu li
        {
            display:inline;
            margin:30px;
        }
        .menu .horizontal-menu li a
        {
            text-transform:uppercase;
            color:white;
            font-family:sans-serif;
            font-size:11px;
            text-decoration:none;
            font-weight:bold;
        }
        .menu .horizontal-menu li .login
        {
            padding-top:17px;
            clear:both;
            float:right;
            border:1px solid white;
            width:150px;
            height:25px;
            font-size:12px;
            color:white;
            background-color:transparent;
            border-radius:20px;
            margin:-5%;
            margin-right:10px;
            text-align:center;
        }
    </style>

</head>





