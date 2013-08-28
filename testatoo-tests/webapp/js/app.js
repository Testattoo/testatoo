(function ($) {

    $(function () {

        $('#add-message').on('click', function () {
            setTimeout(function () {
                $('#messages ol').append('<li>' + new Date() + ' - a message</li>');
                setTimeout(function () {
                    $('#add-message').prop('disabled', false);
                }, 1000);
            }, 1000);
            $('#add-message').prop('disabled', true);
        });

    });

}(jQuery));
