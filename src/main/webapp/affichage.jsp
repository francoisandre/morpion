<%@page import="morpion.domaine.Partie"%>
<%@page import="morpion.servlet.Constantes"%>
<%!

 public String getMessageFinPartie(HttpSession session) {
	Partie partie = (Partie) session.getAttribute(Constantes.NOM_ATTRIBUT_PARTIE);
	if (partie == null) {
		return "";
	}
	else {
		if (partie.estEnCours()) {
			return "";
		} else {
			return partie.getMessageFinDePartie();
		}
	}
 }

 public String genereCellule(int x, int y, HttpSession session) {
	
	Partie partie = (Partie) session.getAttribute(Constantes.NOM_ATTRIBUT_PARTIE);
	StringBuffer sb = new StringBuffer();
	
	if (partie != null) {
		String aux = partie.getValeurCase(x, y);
		if (aux == null) {
			if (partie.estEnCours()) {
				sb.append("<i class='fa fa-plus-circle plus' title='Jouer ici' onclick=\"window.location.replace('./coup?x="+x+"&y="+y+"')\"></i>");
			}
			else {
				sb.append("&nbsp;");
			}
		}
		else {
			if (aux.equals(Constantes.PIECE_O)) {
				sb.append("<i class='fa fa-times ordinateur piece' ></i>");
			} else {
				sb.append("<i class='fa fa-circle-o humain piece' ></i>");
			}
		}
	}
	
	return sb.toString();
}

%>

<html>
<head>
<link href='https://fonts.googleapis.com/css?family=Poiret One'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href='./css/morpion.css'
	rel='stylesheet' type='text/css'>
<style>
.container {
	width: 100%;
	display: flex;
	justify-content: center;
}

.plus {
	font-size:40px;
	color: silver;
	cursor: pointer;
}

.piece {
	font-size:80px;
}

.humain {
	color: green;
}

.ordinateur {
	color: red;
}

.main {
	width: 300px;
	height: 300px;
	padding: 50px;
}

.grid {
	width: 100%;
	border-collapse: collapse
}

.grid td {
	width: 33.33%;
	border: 4px solid #222;
	text-align:center;
	height: 100px;
}

.grid td:FIRST-OF-TYPE {
	border-left-color: transparent;
	border-top-color: transparent;
	
}

.grid td:NTH-OF-TYPE(2) {
	border-top-color: transparent;
	
}

.grid td:NTH-OF-TYPE(3) {
	border-top-color: transparent;
	border-right-color: transparent;
}

.grid tr:NTH-OF-TYPE(3) td {
	border-bottom-color: transparent;
}




</style>
</head>
<body>
	<div class="title">Morpion</div>
</body>
	<div class="container">
	<div class="main">
		<table class="grid">
		<tr>
			<td><%= genereCellule(0,0,session) %></td>
			<td><%= genereCellule(0,1,session) %></td>
			<td><%= genereCellule(0,2,session) %></td>
		</tr>
		<tr>
			<td><%= genereCellule(1,0,session) %></td>
			<td><%= genereCellule(1,1,session) %></td>
			<td><%= genereCellule(1,2,session) %></td>
		</tr>
		<tr>
			<td><%= genereCellule(2,0,session) %></td>
			<td><%= genereCellule(2,1,session) %></td>
			<td><%= genereCellule(2,2,session) %></td>
		</tr>
		
		</table>
	</div>
	
	</div>
	<div class="title"><%= getMessageFinPartie(session) %></div>
	<div class="title"><a style='cursor: pointer' onclick="window.location.replace('./start?type=ordinateur')">Recommencer</a></div>
</html>