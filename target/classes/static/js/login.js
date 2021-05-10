// modal form input animation
$(".txtb input").on("focus",function(){
  $(this).addClass("focus");
});
$(".txtb input").on("blur",function(){
  if($(this).val() == "")
    $(this).removeClass("focus");
});

//login
$('.login').click(function () {
  $('#modal-login').modal('show')
})
$('.forget-pass').click(function () {
  $('#modal-login').modal('hide')
  $('#modal-forgot').modal('show')
})

//sign-up
$('.sign-up').click(function () {
  $('#modal-login').modal('hide')
  $('#modal-signup').modal('show')
})
$('.back').click(function () {
  $('#modal-signup').modal('hide')
  $('#modal-login').modal('show')
})
