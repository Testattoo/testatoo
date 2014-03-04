$(document).ready(function ($) {
  $.stellar();

  var links = $('[data-slide]');

  links.on('click', function (e) {
    e.preventDefault();
    goToByScroll($(this).attr('data-slide'));
    links.removeClass('selected');
    $(this).addClass('selected');
  });

  $('body').scrollspy({ target: '.navbar' });

  function goToByScroll(dataslide) {
    $('html').animate({
      scrollTop: $('#' + dataslide).offset().top - 50
    }, 1000, 'easeInOutQuint');
  }

});

