<template>
  <b-container>
    <h2>Lista de películas</h2>
    <b-row v-if="isEmpty">
      <b-col v-for="movie in movies" :key="movie.id" sm="12" class="mb-3">
        <b-card :title="movie.nombre" :sub-title="movie.category.nombreCategoria"
          :img-src="'https://picsum.photos/900/250/?image=' + getRandomImageId()" 
          sub-title-text-variant="warning" text-variant="warning" overlay>
          <b-card-text>
            Dirigida por {{ movie.director }} · {{ movie.duracion }}min
          </b-card-text>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import ServiceMovie from '../../services/ServiceMovie';

export default {
  data() {
    return {
      movies: [],
      isEmpty: false
    }
  },
  mounted() {
    this.obtenerPeliculas();
  },
  methods: {
    getRandomImageId() {
      return Math.floor(Math.random() * 100) + 1;
    },
    async obtenerPeliculas() {
      try {
        const data = await ServiceMovie.obtenerPeliculas();
        this.movies = data.content;
        this.isEmpty = this.movies.length > 0; // Actualiza isEmpty basado en si hay películas o no
      } catch (error) {
        console.log(error);
      }
    }
  }
};
</script>

<style scoped>
@media (max-width: 575.98px) {
  .mb-3 {
    margin-bottom: 1rem;
  }
}
</style>
