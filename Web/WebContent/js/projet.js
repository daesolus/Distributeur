$(document).ready(function () {
	
	$("#btnAffichage").click(function () {
		$("#changeInterface").submit();
	});
	
	$(".btnChoixRecette").click(function (){
		var idRecette = $('.hidId',this).val();
		$("#choixRecette").hide();
		$("#choixVerre").show();
		$("#hidCommande").val(idRecette);
	});
	$(".btnChoixQuantite").click(function (){
		var qte = $('.hidQte',this).val();
		$("#hidQuantite").val(qte);
		var parent = $(this).parent('.thumbnail');
		
		$("#interface #choixVerre .thumbnail").each(function () {
			if($(this).hasClass("thumbnail_click")){
				$(this).removeClass("thumbnail_click");
			}
		});
		parent.addClass('thumbnail_click');
	});
	
	$("#btnRetourCommande").click(function(){
		$("#choixRecette").show();
		$("#choixVerre").hide();
	});
	
	$("#btnAjoutBouteille").click(function () {
		var trDebut = "<tr class=\"nouvelleBouteille\" >";
		var tdType = "<td class=\"valBouteille\"><input name=\"type\" type=\"text\" class=\"form-control type\"></td>";
		var tdMarque = "<td class=\"valBouteille\"><input  name=\"marque\" type=\"text\" class=\"form-control marque\"></td>";
		var tdPompe = "<td class=\"valBouteille\">" +
				"<select class=\"pompe form-control\" name=\"pompe\"> " +
					"<option value=\"\">Choisir...</option>" +
					"<option value=\"0\">0</option>" +
					"<option value=\"1\">1</option>" +
					"<option value=\"2\">2</option>" +
					"<option value=\"3\">3</option>" +
					"<option value=\"4\">4</option>" +
				"</select></td>";
		var tdQte = "<td class=\"valBouteille\"><input name=\"qte\" type=\"text\" class=\"form-control qte\"></td>";
		var tdAnnuler = "<td><a  class=\"btn btnGlyphicon annulerBouteille\">" +
						"<span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span></a></td></tr>"
		
		$("#tableDistributeur").append(trDebut+tdType+tdMarque+tdPompe+tdQte+tdAnnuler);
		
		$(".tdAction .modifierBouteille").hide();
    });
	
	$(".enleverBouteille").click(function () {
		var id = $(this).parent().find(".hidId").val();
		$("#hidBouteille").val(id);
		$("#hidAction").val("supprimer");	
		$("#ajoutBouteille").submit();
	});
	
	$(".enleverRecette").click(function () {
		var id = $(this).parent().find(".hidIdRecette").val();
		$("#hidRecette").val(id);
		$("#hidAction").val("supprimer");	
		$("#listeRecette").submit();
	});
	
	$(".enleverBouteilleRecette").click(function () {
		var id = $(this).parent().find(".hidIdBouteilleRecette").val();
		$("#hidBouteilleRecette").val(id);
		$("#hidAction").val("supprimerBouteilleRecette");	
		$("#formModifierBouteille").submit();
	});
	
	
	$(".modifierBouteille").click(function () {
		
		var bouteille = new Object();
		var trElement = $(this).parents(':eq(1)');
		
		trElement.addClass('modifiedBouteille');
		
		var tdType = trElement.find(".tdType");
		var tdMarque = trElement.find(".tdMarque");
		var tdPompe = trElement.find(".tdPompe");
		var tdQte = trElement.find(".tdQte");
		
		bouteille.type = tdType.html().trim();
		bouteille.marque = tdMarque.html().trim();
		bouteille.pompe = tdPompe.html().trim();
		bouteille.qte = tdQte.html().trim();
		
		
		var tboxElement = "<input type=\"text\" value=\"";
		tdType.html(tboxElement + bouteille.type+"\" name=\"type\" class=\"form-control type  \">");
		tdMarque.html(tboxElement + bouteille.marque+"\" name=\"marque\" class=\"form-control marque \">");
		tdPompe.html(
			"<select class=\"form-control pompe\" name=\"pompe\">" +
				"<option value=\"\">Choisir...</option>" +
				"<option value=\"0\">0</option>" +
				"<option value=\"1\">1</option>" +
				"<option value=\"2\">2</option>" +
				"<option value=\"3\">3</option>" +
				"<option value=\"4\">4</option>" +
			"</select></td>"
		);
		tdQte.html(tboxElement + bouteille.qte+"\" name=\"qte\" class=\"form-control qte \">");
		
		$("#btnAjoutBouteille").hide();
		$("#btnAnnuler").show();
		$("#hidAction").val("modifier");
		$(".tdAction .modifierBouteille").hide();
		
	});
	
	$("#btnAnnuler").click(function () {
		location.reload();
	});
	
	
	$(document).on('click','.annulerBouteille',function () {	
		if($(this).closest('tr.nouvelleBouteille').length)
		{
			$(this).closest('tr').remove();
		}
		if($(".nouvelleBouteille").length == 0){
			$(".tdAction .modifierBouteille").show();
		}
	});
		
	$(document).on('click','#btnSaveBouteille',function () {
        var bouteilleArray = new Array();
        var lesBouteilles = new Object();
        
        var trSelec = "";
        if($("#hidAction").val() == "modifier"){
        	trSelec = "modifiedBouteille";
        } 
        else {
        	$("#hidAction").val("ajouter");
        	trSelec = "nouvelleBouteille";
        }
        
        $('#tableDistributeur tbody tr.'+trSelec).each(function() {
        	var bouteille = new Object();
        	
        	if($("#hidAction").val() == "modifier"){
        		bouteille.id = parseInt($('.tdAction .hidId',this).val());
        	}
        	
        	bouteille.marque = $('td.valBouteille .marque', this).val();
        	bouteille.type = $('td.valBouteille .type', this).val();
        	bouteille.pompe = parseInt($('td.valBouteille .pompe', this).val());
        	bouteille.qte = parseFloat($('td.valBouteille .qte', this).val());
        	bouteilleArray.push(bouteille);
        });
        lesBouteilles.nouvelleBouteille = (bouteilleArray);
        $("#hidBouteille").val(JSON.stringify(lesBouteilles));
        
        $("#ajoutBouteille").submit();
   });
	
	$(".recetteRow .recetteDetail").click(function(){
		var idBenevole = $(this.nextElementSibling).val();
		$.ajax({
            url: 'commande',
            type: "POST",
            data: {id: parseInt(idBenevole),action:"detail"},
            success:function(response){
            	$("#filler").addClass("fillerOn");
        		$("#filler").removeClass("fillerOff");
            	$('#sectionDetail').html(response);       	
            },
            error: function(xhr, status, error) {
    			console.log(xhr.responseText);
 			}
        })
	});	
	
	$(document).on('click','#closeDetail',function () {
	       $("#filler").removeClass("fillerOn");
	       $("#filler").addClass("fillerOff");
	       $('#detail').html("");
	});
	$('.chkCommande').change(function(){
		$("#formCommande").validate();
		 var tr= $(this).parent().parent();
		 var qte = $('.txtQte',tr)[0];
		  if($(this).is(':checked')){
			  $("#"+qte.id).rules("add", "required");
			  $("#"+qte.id).rules("add", "number");
			  $(tr).css("background-color","#64d193");
			  
		  } else {		
			  $("#"+qte.id).rules("remove", "required");
			  $("#"+qte.id).rules("remove", "number");
			  $(tr).css("background-color","#FFFFFF");
		  }
	});
	
	$('.chkBouteille').change(function(){
		$("#formAjoutRecette").validate();
		 var tr= $(this).parent().parent();
		 var proportion = $('.txtProportion',tr)[0];
		  if($(this).is(':checked')){
			  $("#"+proportion.id).rules("add", "required");
			  $("#"+proportion.id).rules("add", "number");
			  $(tr).css("background-color","#64d193");
			  
		  } else {		
			  $("#"+proportion.id).rules("remove", "required");
			  $("#"+proportion.id).rules("remove", "number");
			  $(tr).css("background-color","#FFFFFF");
		  }
	});
});
