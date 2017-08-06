$(document).ready(function() {
	
	/**
	 * ajax salvar matricula
	 */
	$("#formMatricula").submit(function(event) {
		
		event.preventDefault();
		var $form = $(this);
		
		$.ajax({
			  type: $form.attr("method"),
			  url: "matricula/salvarMatricula",
			  data: $form.serialize(),
			  success: $("#formMatricula").each (function(){
				  this.reset();
			  })
			  
		});
	});
	
});