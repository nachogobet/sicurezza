function processPurchase()
{
	var elements = document.getElementsByClassName("amount");
	var purchase = [];
	for(var x=0; x < elements.length; x++)
	{
		var ele = elements[x];
		if(ele.value != ""){
			purchase.push({id: ele.id, amount: ele.value}); 
		}
	}
	var empresa = document.getElementById("empresa").value;
	var nombre = document.getElementById("nombre").value;
	var email = document.getElementById("email").value;

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "processCatalog", true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.onreadystatechange = function() {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			var html = xhr.responseText;
			$("html").html(html);
		}
	};
	xhr.send(JSON.stringify({
		purchase: purchase,
		empresa: empresa,
		nombre: nombre,
		email: email
	}));
}