<template>
  <b-container>
    <b-row class="mt-4">
      <b-col>
        <movie-card />
      </b-col>
      <b-col v-show="showElement">
        <b-row> <movie-form /></b-row>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import MovieForm from "../components/AnimationView/MovieForm.vue";
import MovieCard from "../components/AnimationView/MovieCard.vue";

export default {
  data() {
    return {
      showElement: true,
      lastScrollPosition: 0,
    };
  },
  components: {
    MovieForm,
    MovieCard,
  },
  mounted() {
    window.addEventListener("scroll", this.onScroll);
  },
  beforeDestroy() {
    window.removeEventListener("scroll", this.onScroll);
  },

  methods: {
    onScroll() {

      // Obtiene la posición actual del scroll
      const currentScrollPosition = window.pageYOffset || document.documentElement.scrollTop;
      console.log(currentScrollPosition);

      // La función abs para tener valores absolutos y se delimita con un offset o bien llamado 
      // margen para que el valor de la posición sea después de desplazarce y no desde que uno se desplaza
      if (Math.abs(currentScrollPosition - this.lastScrollPosition) < 60) {
        return;
      }
      // aqui determinamos si la posición es mayor a la posición anterior. Entonces, si lo es, mostramos el elemento.
      this.showElement = currentScrollPosition < this.lastScrollPosition;
      //  
      this.lastScrollPosition = currentScrollPosition;
    },
  },
};
</script>
