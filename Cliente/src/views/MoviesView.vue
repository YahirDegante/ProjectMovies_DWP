<template>
  <b-container class="bv-example-row">
    <b-row class="mt-4">
      <b-col>
        <movie-card />
      </b-col>
      <b-col>
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
    return {};
  },
  components: {
    MovieForm,
    MovieCard,
  },
};
</script>
<style>
</style>