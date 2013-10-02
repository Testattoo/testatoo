(function ($) {

    $(function () {



        $('#add-message').on('click', function () {
            setTimeout(function () {
                $('#messages').empty().append('<button type="button" id="msg" class="btn btn-large btn-primary">MESSAGE !</button>');
                setTimeout(function () {
                    $('#add-message').prop('disabled', false);
                }, 5000);
            }, 2000);
            $('#add-message').prop('disabled', true);
        });

    });

}(jQuery));
