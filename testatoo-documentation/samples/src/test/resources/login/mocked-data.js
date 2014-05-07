$(function () {
    $.mockjax({
        url: '/api/security/login',
        type: 'POST',
        response: function (settings) {
            // set data to json object
            var data = JSON.parse(settings.data);
            (data.email === 'test@email.org' && data.password === 'password666') ? this.status = 204 : this.status = 403;
        }
    });
});