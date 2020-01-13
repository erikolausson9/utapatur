jQuery(function($) {

     if (location.pathname != "/profil") {
    $('.dropdown > a').click(function(){
            location.href = this.href;
        });
    }
    console.log(location.pathname);
});
