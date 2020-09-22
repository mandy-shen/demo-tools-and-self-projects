<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html>
	<head>
		<tiles:insertAttribute name="head" />
		<title>Mandy's DEMO</title>
	</head>
	<body>
		<div id="fh5co-page">
			<tiles:insertAttribute name="body.header" />
			<tiles:insertAttribute name="body" />
		</div>
		<tiles:insertAttribute name="body.footer" />
	</body>
	<tiles:insertAttribute name="script.art" />
	<tiles:insertAttribute name="script.dev" />
	<tiles:insertAttribute name="script" /> 
</html>
