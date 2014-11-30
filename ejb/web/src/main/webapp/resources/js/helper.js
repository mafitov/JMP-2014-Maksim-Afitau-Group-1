$(document).ready(function() {
    var initValue = $('select option:selected').val();
    var initAmount = $("input[id*='amount']").val();
    $('select').change(function() {
        var newValue = $('select option:selected').val();

        $.get( "http://localhost:8080/banks-ejb/rest/currencies/from/" + initValue + "/to/" + newValue +"/amount/" + initAmount, function( data ) {
          $("input[id*='amount']").val( data );
          initValue = newValue;
          initAmount = data;
          console.log( "Load was performed." );
        });
    });
    
    /*$("#modal-create").easyModal();
    $('#add-account').click(function() {
    	$("#modal-create").trigger('openModal');
    });
    $("input[id*='create-account-btn']").click(function() {
    	$("#modal-create").trigger('closeModal');
    });*/
   
});