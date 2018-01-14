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
        alert("");
    }