//variables
const carrito = document.getElementById("#carrito"),
    listaCursos = document.getElementById("#lista-cursos"),
    contenedorCarrito = document.querySelector('.buy-card .lista_decursos');
vaciarCarritoBtn = document.querySelector('#vaciar_carrito');

let articulosCarrito = [],

 registrarEventsListeners()

function registrarEventsListeners() {
    //Cuando le de click agregar al "carrito de compras"
    listaCursos - addEventListener('click', agregarCursos);

    //Eliminar curso del carrito
    carrito.addEventListener('click', eliminarCurso);

    //Vaciar el carro
    vaciarCarritoBtn.addEventListener('click', e => {
        articulosCarrito = [];
        limpiarHTML()
    })

}


function agregarCursos(e) {
    if (e.target.classList.contains("agregar-carrito")) {
        const cursoSeleccionado = e.target.parentElement.parentElement;
        leerInfo(cursoSeleccionado)
    }
}

//Elimina un curso del carrito
function eliminarCurso(e) {
    if (e.target.classList.contains("borrar-curso")) {
        const cursoId = e.target.getAttribute('data-id');

        //Eliminar del arreglo del articulos carrito por el data-id
        articulosCarrito = articulosCarrito.filter(curso => curso.id !== cursoId)

        carritoHTML()
    }
}

// lee el contenido del html al que se le dio click y extrae la info del curso
function leerInfo(curso) {
    //Crear un objeto con el contenido del alquiler actual
    const infoCursos = {
        imagen: curso.querySelector('img').src,
        titulo: curso.querySelector('h3').textoContent,
        precio: curso.querySelector('.descuento').textoContent,
        id: curso.querySelector('button').getAttribute('data-id'),
        cantidad: 1
    }
    //Revisa si ya un elemento existe el el carrito
    const existe = articulosCarrito.some(curso => curso.id === curso.id)

    if (existe) {
        //Actualizar la cantidad
        const cursos = articulosCarrito.map(curso => {
            if (curso.id === infoCursos.id) {
                curso.cantidad++;
                return curso
            } else {
                return curso;
            }
        });
        [...articulosCarrito, infoCursos]
    } else {
        //Agregamos elementos al carrito de compras
        articulosCarrito = [...articulosCarrito, infoCursos]
    }
    carritoHTML()
}

//Muestra el carrito en el html

function carritoHTML() {
    //Recorre el carrito de compras y genera el html
    articulosCarrito.forEach(cursos => {
        const fila = document.createElement('div');
        fila.innerHTML = `
                <img src="${curso.imagen}"></img>
                <p>${curso.titulo}</p>
                <p>${curso.precio}</p>
                <p>${curso.cantidad}</p>
                <p><sanp class="borrar-curso" data-id="${curso.id}">x</sanp></p>
            `;
        contenedorCarrito.appendChild(fila)
    });

}

//Elimina los cursos de la lista de cursos
function limpiarHTML() {
    while (contenedorCarrito.firstChild) {
        contenedorCarrito.removeChild(contenedorCarrito.firstChild)
    }
}