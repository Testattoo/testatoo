$(function () {
  $.mockjax({
    url: '/api/security/login',
    type: 'POST',
    response: function (settings) {
      // set data to json object
      (settings.data.email === 'test@email.org' && settings.data.password === 'password666') ? this.status = 200 : this.status = 500;
    }
  });
});