<template>
  <b-modal v-model="showModal" title="Actualizar película" @hide="cerrarModal" hide-footer>
    <b-form @submit="actualizarPelicula">
      <b-form-group label="Título" label-for="title">
        <b-form-input id="title" v-model="movie.title" required />
      </b-form-group>

      <b-form-group label="Director" label-for="director">
        <b-form-input id="director" v-model="movie.director" required />
      </b-form-group>

      <b-form-group label="Año" label-for="year">
        <b-form-input id="year" v-model="movie.year" type="date" required />
      </b-form-group>

      <b-form-group label="Categoria:" label-for="category">
        <b-form-select
          id="category"
          v-model="movie.category"
          :options="categories"
          required
        />
      </b-form-group>
      <b-button class="mt-4" type="submit" variant="primary" @click="actualizarPelicula(movie)">Actualizar</b-button>
    </b-form>
    </b-modal>
</template>

<script>
import ServiceMovie from "../../services/ServiceMovie";

export default {
  props: {
    selectedMovie: {
      type: Object,
      required: true,
    },
    categorias: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      showModal: true,
      movie: {
        title: this.selectedMovie.nombre,
        director: this.selectedMovie.director,
        year: this.selectedMovie.fechaPublicacion,
        category: this.selectedMovie.category.nombreCategoria,
      },
      categories: this.categorias,
    };
  },
  methods: {
    cerrarModal() {
      this.$emit("cerrar");
    },
    async actualizarPelicula(movie) {
      try {
        await ServiceMovie.editarPeliculas(movie);
        this.cerrarModal();
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>
