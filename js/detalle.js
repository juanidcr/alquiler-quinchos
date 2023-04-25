const  inputQuantity = document.querySelector('.input-quantity')
const btnIncremento = document.querySelector('#incremento')
const btnDecremento = document.querySelector('#decremento')

let valueByDefault = parseInt(inputQuantity.value)

btnIncremento.addEventListener('click', () => {
    valueByDefault = valueByDefault +=1    
    inputQuantity.value = valueByDefault

})

btnDecremento.addEventListener('click', () => {
    if(valueByDefault === 1){
        return
    }
    valueByDefault = valueByDefault -=1    
    inputQuantity.value = valueByDefault
})

const toggleDescripcion = document.querySelector('titulo-descripcion')
const toggleAdditionalInformacion = document.querySelector('titulo-adicional-informacion')
const toggleResenia = document.querySelector('.titulo-resenia')


const contenedorDescripcion = document.querySelector('.texto-descripcion')
const contenedorAdicionalInformacion = document.querySelector('.texto-adicional-informacion')
const contenedorResenia = document.querySelector('.texto-resenia')

toggleDescripcion.addEventListener('click', () =>{
    contenedorDescripcion.classList.toggle('hidden');
});

toggleAdicionalInformacion.addEventListener('click', () =>{
    contenedorAdicionalInformacion.classList.toggle('hidden');
});

toggleResenia('click', () =>{
    contenedorResenia.classList.toggle.addEventListener('hidden');
});