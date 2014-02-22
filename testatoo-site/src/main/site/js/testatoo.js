jQuery(document).ready(function ($) {
  $.stellar();

  var links = $('.navbar-nav [data-slide]');

  links.on('click', function (e) {
    e.preventDefault();
    goToByScroll($(this).attr('data-slide'));
    links.find('a').removeClass('selected');
    $(this).find('a').addClass('selected');
  });

  function goToByScroll(dataslide) {
    $('html,body').animate({
      scrollTop: $('#' + dataslide).offset().top - 50
    }, 1000, 'easeInOutQuint');
  }

});

