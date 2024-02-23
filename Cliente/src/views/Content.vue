<template>
  <b-container class="mt-4">
    <b-card
      v-for="movie in movies"
      :key="movie.id"
      :title="movie.nombre"
      style="max-width: 20rem"
      class="mb-2"
    >
      <b-card-text>
        <p>Director: {{ movie.director }}</p>
        <p>Fecha de publicación: {{ formatDate(movie.fechaPublicacion) }}</p>
        <p>Duración: {{ movie.duracion }} minutos</p>
        <p>Categoría: {{ movie.category.nombreCategoria }}</p>
      </b-card-text>
      <b-button @click="seleccionarPelicula(movie)" variant="primary"
        >Editar</b-button
      >
      <b-button @click="eliminarPelicula(movie)" variant="danger"
        >Eliminar</b-button
      >
    </b-card>
    <updateMovieModal
      v-if="selectedMovie"
      :selectedMovie="selectedMovie"
      :categorias="categorias"
      @cerrar="cerrarModal"
    />
  </b-container>
</template>

<script>
import ServiceMovie from "../services/ServiceMovie";
import ServiceCategory from "../services/ServiceCategory";
import UpdateMovieModal from "../components/Modal/UpdateMovieModal.vue";

export default {
  data() {
    return {
      movies: [],
      selectedMovie: false,
      categorias: [{ text: "Seleccione una categoría", value: null }],
    };
  },
  components: {
    UpdateMovieModal,
  },
  mounted() {
    this.obtenerCategorias();
    this.obtenerPeliculas();
  },
  methods: {
    async obtenerCategorias() {
      try {
        const categoriasResponse = await ServiceCategory.obtenerCategorias();

        for (const categoria of categoriasResponse) {
          this.categorias.push({
            text: categoria.nombreCategoria,
            value: categoria.id,
          });
        }
      } catch (error) {
        console.log(error);
      }
    },
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
        const confirmacion = confirm(
          "¿Estás seguro de que deseas eliminar esta película?"
        );
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
    },
    cerrarModal() {
      this.selectedMovie = false;
      this.obtenerPeliculas();
    },
    formatDate(date) {
      const options = { year: "numeric", month: "long", day: "numeric" };
      return new Date(date).toLocaleDateString(undefined, options);
    },
  },
};
</script>
