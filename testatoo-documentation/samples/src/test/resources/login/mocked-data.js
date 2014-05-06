$(function () {
  $.mockjax({
    url: 'api/security/login',
    type: 'POST',
    dataType: 'json',
//  data: {"email":"test@email.org","password":"password666"},
    status: 200
  });
});