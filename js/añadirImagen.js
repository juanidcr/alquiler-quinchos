$("#cargar").click(function() {

    var image = new Image();

    var src = 'http://www.todopaisajes.com/Imagenes/paisaje-rural-del-campo.jpg'; //Esta es la variable que contiene la url de una imagen ejemplo, luego puedes poner la que quieras
    image.src = src;

    $('#image').append(image);
});