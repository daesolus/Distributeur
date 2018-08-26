$(document).ready(function () {
	jQuery.validator.addClassRules("txtProportion", {
		number:true,
		somme:100,
		min: 0.1
	});
	jQuery.validator.addClassRules("txtQte", {
		number:true,
		min: 0.1
	});
	$.validator.addMethod(
	        "regex",
	        function(value, element, regexp) {
	            var re = new RegExp(regexp);
	            return this.optional(element) || re.test(value);
	        },
	        "Champ invalide"
		);
	$.validator.addMethod("notEqualTo",
			function(value, element, param) {
			    var notEqual = true;
			    value = $.trim(value);
			    $(param[0]).each(function() {
			    	if (value == $.trim($(this).html())) { notEqual = false; }
			    });	           
			    return this.optional(element) || notEqual;
			},
			"Pompe déjà utilisée"
	);
	$.validator.addMethod("somme",
		    function (value, element, params) {
				if(value != ""){
			        var sumOfVals = 0;
			        var parent = $(element).closest("#tableRecette");
			        $(parent).find('tbody .proportion .txtProportion').each(function () {
			        	if($(this).val()!= "")
			        		sumOfVals = sumOfVals + parseFloat($(this).val());
			        });
			        if (sumOfVals == params) return true;
			        return false;
				}
				return true;
		    },
		    $.validator.format("La proportion doit être de {0}%")
		);

	
	$("#ajoutBouteille").validate({ 
		lang: 'fr',
	    rules: { 
	    	"type":"required",
	    	"marque":"required",
	    	"qte":{
	    		required: true,
	    	    number: true,
	    	    min: 0
	    	},
	    	"pompe":{
	    		required: true,
	    		notEqualTo: ['#tableDistributeur tbody tr td.tdPompe']
	    	}
	    },
	});
		
	$("#formAjoutRecette").validate({ 
		lang: 'fr',
	    rules: { 
	    	"txtNomRecette":"required",
	    	"chkBouteille":"required",    	
	    },
	    messages:{
	    	"txtNomRecette":{
	    		required : "Veillez écrire un nom de recette",
	    	},
	    	"chkBouteille":{
	    		required : "Veillez choisir au moins une bouteille",
	    	}
	    },
	    errorElement : 'div',
 	    errorLabelContainer: '.errorTxt',
	    errorPlacement: function() {
	    	$('.errorTxt').show();
	    }
	   
	});
	
	$("#formCommande").validate({ 
		lang: 'fr',
	    rules: { 
	    	"selectRecette":"required"   	
	    },
	    messages:{
	    	"selectRecette":{
	    		required : "Veillez choisir au moins une recette",
	    	}
	    },
	    errorElement : 'div',
 	    errorLabelContainer: '.errorTxt',
	    errorPlacement: function() {
	    	$('.errorTxt').show();
	    }
	   
	});
	
	$("#formModifierBouteille").validate({ 
		lang: 'fr',
	    rules: { 
	    	"txtNom":"required"  ,
	    	"bouteillePercent":{
	    		number:true,
	    		required:true
	    	}
	    },
	    messages:{
	    	"selectRecette":{
	    		required : "Veillez choisir au moins une recette",
	    		somme:100,
	    		min: 0.1
	    	}
	    },
	    errorElement : 'div',
 	    errorLabelContainer: '.errorTxt',
	    errorPlacement: function() {
	    	$('.errorTxt').show();
	    }
	   
	});
});