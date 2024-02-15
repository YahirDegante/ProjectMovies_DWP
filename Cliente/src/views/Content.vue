<template>
    <div>
        <div class="row">
            <div v-for="movie in movies" :key="movie.id" class="card">
                <h2>{{ movie.nombre }}</h2>
                <p>Director: {{ movie.director }}</p>
                <p>Fecha de publicación: {{ formatDate(movie.fechaPublicacion) }}</p>
                <p>Duración: {{ movie.duracion }} minutos</p>
                <p>Categoría: {{ movie.category.nombreCategoria }}</p>
                <div class="card-buttons">
                    <button @click="editarPelicula(movie)">Editar</button>
                    <button @click="eliminarPelicula(movie)">Eliminar</button>
                </div>
            </div>
        </div>
    </div>
</template>  
  
<script>
import ServiceMovie from "../services/ServiceMovie";

export default {
    data() {
        return {
            movies: []
        };
    },
    mounted() {
        this.obtenerPeliculas();
    },
    methods: {
        async obtenerPeliculas() {
            try {
                const data = await ServiceMovie.obtenerPeliculas();
                console.log(data);
                this.movies = data.content;
            } catch (error) {
                console.log(error);
            }
        },
        formatDate(date) {
            const options = { year: "numeric", month: "long", day: "numeric" };
            return new Date(date).toLocaleDateString(undefined, options);
        }
    }
};
</script>
  
<style>
.row {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}

.card {
    width: 30%;
    border: 1px solid #ccc;
    padding: 10px;
    margin-bottom: 10px;
}
</style>