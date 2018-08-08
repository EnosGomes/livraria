
/* Ativa o componente tooltip do bootstrap */
$(function () {
  $('[data-toggle="tooltip"]').tooltip();
})


/* componente datepicker para exibição de um calendário no campo de data */
//$(function () {
//	$('.container-datepicker-js input').datepicker({
//	    format: "dd/mm/yyyy",
//	    language: "pt-BR",
//	    autoclose: true
//	  });
//});

/* Biblioteca Maskmoney */ 
//$(function() {
//    $('.maskmoney-js').maskMoney({
//    			allowNegative: false, 
//    		    thousands:'.', 
//    		    decimal:',', 
//    		    affixesStay: false
//   		});
//});

//consultar
//http://www.thymeleaf.org/doc/tutorials/2.1/usingthymeleaf.html#script-inlining-javascript-and-dart

$(document).ready( function() {
	
$('.excluir-livro-js').click(function() {
	 
	/*<![CDATA[*/
  var path = /*[[@{/}]]*/ 'remove';
 /*]]>*/
	
 var id = $(this).data('id');
 var valor = $('.tipo').data('id');
 var title;
 var mensagem;

	console.log("id :");
	console.log(id);
	console.log(valor);
	
	if(valor == 1){
		title = "Excluir Livro?";
		mensagem = "Confirma a exclusão do livro";
	}
	if(valor == 2){
		title = "Excluir Editora?";
		mensagem = "Confirma a exclusão da editora";
	}
	if(valor == 3){
		title = "Excluir Autor?";
		mensagem = "Confirma a exclusão do autor";
	}
	if(valor == 4){
		title = "Excluir Categoria?";
		mensagem = "Confirma a exclusão da categoria";
	}
	
	bootbox.confirm({
	    title: title,
	    message: mensagem,
	    buttons: {
	        cancel: {
	            label: '<i class="fa fa-times"></i> Cancelar'
	        },
	        confirm: {
	            label: '<i class="fa fa-check"></i> Confirmar'
	        }
	    },
	    callback: function (confirmado) {
	    	if (confirmado)
	           $.post(path, {'id':id}, function(resposta) {
	        	   location.reload();
	        	   console.log('o livro foi removido com sucesso');
	           })
	    }
	}); // bootbox.confirm()  
	
});

$('#excluir-livros').click(function() {
		/*<![CDATA[*/
	        var path = /*[[@{/}]]*/ 'removeLista';
	    /*]]>*/
	        
	        var id = $(this).attr('id');
	        var valor = $('.tipo').data('id');
	        var title;
	        var mensagem;
	        
	        if(valor == 1){
	    		title = "Excluir Livro?";
	    		mensagem = "Confirma a exclusão de todos os livros selecionados";
	    	}
	    	if(valor == 2){
	    		title = "Excluir Editora?";
	    		mensagem = "Confirma a exclusão de todas as editoras selecionadas";
	    	}
	    	if(valor == 3){
	    		title = "Excluir Autor?";
	    		mensagem = "Confirma a exclusão de todos os autores selecionados";
	    	}
	    	if(valor == 4){
	    		title = "Excluir Categoria?";
	    		mensagem = "Confirma a exclusão de todas as categorias selecionadas";
	    	}
	    	
	    	bootbox.confirm({
	    	    title: title,
	    	    message: mensagem,
	    	    buttons: {
	    	        cancel: {
	    	            label: '<i class="fa fa-times"></i> Cancelar'
	    	        },
	    	        confirm: {
	    	            label: '<i class="fa fa-check"></i> Confirmar'
	    	        }
	    	    },
	    	   
	    	    callback: function (confirmado) {
	    	    	if (confirmado)
	    	           $.ajax({
	    	        	   type: 'POST',
	    	        	   url: path,
	    	        	   data: JSON.stringify(listaLivroId),
	    	        	   contentType: 'application/json',
	    	        	   success: function(resposta) { 
	    	        		               console.log(resposta);
	    	        		               location.reload();
	    	        	   },
	    	               error: function(resposta) { 
	    	            	   			   console.log(resposta);
	    	            	   			   location.reload();
 	                   }
	    	         });
	    	    }
	    	}); // bootbox.confirm()
	});

 // código da lista de livros selecionadas no checkbox
	var listaLivroId = [];
	
	$('.checkboxlivro-js').click( function() {
		
		var id = $(this).attr('id');
		
		if ( this.checked ) {
			listaLivroId.push(id);
		
		} else {
			listaLivroId.splice(listaLivroId.indexOf(id), 1);
		}
		console.log(listaLivroId );
	});

 // código para selecionar todos os livros da página
	$('#todoscheckbox-js').click(function () {
		console.log('teste checkbox');
		listaLivroId = [];
		if (this.checked) {
			$('.checkboxlivro-js').prop('checked', true);
			$('.checkboxlivro-js').each(function() {
				 listaLivroId.push( $(this).attr('id') );
			});
	
		} else {
			$('.checkboxlivro-js').prop('checked', false);
	    }
		console.log(listaLivroId);
	}); 	
	
}); // função ready()

