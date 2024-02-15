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
                    <div>
                        <button @click="seleccionarPelicula(movie)">Editar</button>
                    </div>
                    <button @click="eliminarPelicula(movie)">Eliminar</button>
                </div>
            </div>
        </div>
        <updateMovieModal v-if="selectedMovie" :selectedMovie="selectedMovie" @cerrar="cerrarModal" />
    </div>
</template>

<script>
import ServiceMovie from "../services/ServiceMovie";
import UpdateMovieModal from "../components/Modal/UpdateMovieModal.vue";

export default {
    data() {
        return {
            movies: [],
            selectedMovie: null
        };
    },
    components: {
        UpdateMovieModal
    },
    mounted() {
        this.obtenerPeliculas();
        this.obtenerPeliculaSeleccionada();
    },
    methods: {
        async obtenerPeliculas() {
            try {
                const data = await ServiceMovie.obtenerPeliculas();
                this.movies = data.content;
            } catch (error) {
                console.log(error);
            }
        },
        async eliminarPelicula(movie) {
            try {
                const confirmacion = confirm("¿Estás seguro de que deseas eliminar esta película?");
                if (confirmacion) {
                    await ServiceMovie.eliminarPeliculas(movie.id);
                    alert("Película eliminada exitosamente");
                    this.obtenerPeliculas();
                }
            } catch (error) {
                console.error("Error al eliminar la película:", error);
            }
        },
        seleccionarPelicula(movie) {
            this.selectedMovie = movie;
            localStorage.setItem("selectedMovie", JSON.stringify(movie));
        },
        cerrarModal() {
            this.selectedMovie = null;
            localStorage.removeItem("selectedMovie");
        },
        obtenerPeliculaSeleccionada() {
            const storedMovie = localStorage.getItem("selectedMovie");
            if (storedMovie) {
                this.selectedMovie = JSON.parse(storedMovie);
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
