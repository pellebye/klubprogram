function jump() {
	var month = new Array();
	month[0] = "january";
	month[1] = "february";
	month[2] = "march";
	month[3] = "april";
	month[4] = "may";
	month[5] = "june";
	month[6] = "july";
	month[7] = "august";
	month[8] = "september";
	month[9] = "october";
	month[10] = "november";
	month[11] = "december";
	
	var date = new Date();
	var maaned = month[date.getMonth()];
	//alert("måned = " + maaned);
	//maaned="july";
	var year = date.getFullYear();
	var id = maaned + year;
	var elmnt = document.getElementById(id);
	elmnt.scrollIntoView();
}
