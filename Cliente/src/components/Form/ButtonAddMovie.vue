<script setup>
import { ref } from 'vue';
import ModalFormMovies from './ModalFormMovies.vue';

const mostrarPantallaEmergente = ref(false);

function abrirPantallaEmergente() {
    mostrarPantallaEmergente.value = true;
}

function cerrarPantallaEmergente() {
    mostrarPantallaEmergente.value = false;
}
</script>

<template>
    <div>
        <ModalFormMovies v-if="mostrarPantallaEmergente" @cerrar="cerrarPantallaEmergente" />
        <button @click="abrirPantallaEmergente">Agregar película</button>
    </div>
</template>