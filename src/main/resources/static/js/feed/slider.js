$(document).ready(function(){
    // $("#prevBtn").click(function(){
    //     alert("Hello jQuery1!");
    // })
    // $("#nextBtn").click(function(){
    //     alert("Hello jQuery2!");
    // })
    $('.slider_trap').slick({
        dots: true,
        infinite: true,
    });


    // const feedPopup = document.querySelector('.feed_popup');
    // const floatingMenu = document.querySelector('.floating_menu');

    // feedPopup.addEventListener('click', floatingMenu) => {}

    $('.feed_popup').on('click', function() {
        $(this).next('.floating_menu').toggle();
    });



});
