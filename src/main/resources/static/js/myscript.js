function hide(id) {
    var o = document.getElementById(id).style;
    o.display = o.display ? '' : 'none'
}


function how(event) {
    if (event) {
        event.preventDefault();
        event.stopImmediatePropagation();
    }
//       document.getElementById("reg").style.display = "none"
//       componentHandler.upgradeAllRegistered();
}


$(window).on("load", function () {
    setTimeout(function () {
        var overlay = $("#overlay"),
            fab = $(".but"),
            cancel = $("#cancel"),
            subm = $("#subm");
        signup = $("#signup");

//fab click
        fab.on('click', openFAB);
        overlay.on('click', closeFAB);
        cancel.on('click', closeFAB);
        subm.on('click', submFAB);
        signup.on('click', closeFAB);

        function openFAB(event) {
            if (event) event.preventDefault();
            fab.addClass('active');
            overlay.addClass('dark-overlay');

        }

        function submFAB(event) {
            if (event) {
                event.preventDefault();
//            event.stopImmediatePropagation();
            }
            document.getElementById("f").submit();
//        fab.removeClass('active');
//        overlay.removeClass('dark-overlay');

        }

        function closeFAB(event) {
            if (event) {
                event.preventDefault();
                event.stopImmediatePropagation();
            }

            fab.removeClass('active');
            overlay.removeClass('dark-overlay');

        }
    }, 2000)
});