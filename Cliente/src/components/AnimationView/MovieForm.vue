<template>
  <div class="mb-4">
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-form-group
        class="mb-3"
        id="input-group-1"
        label="Nombre de la pelicula:"
        label-for="input-1"
      >
        <b-form-input
          id="input-1"
          v-model="pelicula.nombre"
          placeholder="Nombre de la pelicula"
        />
      </b-form-group>

      <b-form-group
        class="mb-3"
        id="input-group-2"
        label="Director:"
        label-for="input-2"
      >
        <b-form-input
          id="input-2"
          v-model="pelicula.director"
          placeholder="Director"
        />
      </b-form-group>

      <b-form-group
        class="mb-3"
        id="input-group-3"
        label="Fecha de publicación:"
        label-for="input-3"
      >
        <b-form-input
          id="input-3"
          v-model="pelicula.fechaPublicacion"
          placeholder="Fecha de publicación"
          type="date"
        />
      </b-form-group>

      <b-form-group
        class="mb-3"
        id="input-group-3"
        label="Duración:"
        label-for="input-3"
      >
        <b-input-group>
          <template #append>
            <b-input-group-text>minutos</b-input-group-text>
          </template>

          <b-form-input
            id="input-4"
            v-model="pelicula.duracion"
            placeholder="000"
            type="number"
          />
        </b-input-group>
      </b-form-group>

      <b-button type="submit" variant="primary" >Submit</b-button>
    </b-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pelicula: {
        nombre: "",
        director: "",
        fechaPublicacion: "",
        duracion: "",
        categoria: null,
      },
      foods: [
        { text: "Select One", value: null },
        "Carrots",
        "Beans",
        "Tomatoes",
        "Corn",
      ],
      show: true,
    };
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      alert(JSON.stringify(this.pelicula));
    },
    onReset(event) {
      event.preventDefault();
      // Reset our form values
      this.pelicula.nombre = "";
      this.pelicula.director = "";
      this.pelicula.fechaPublicacion = null;
      this.pelicula.duracion = [];
      this.pelicula.categoria = [];
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
  },
};
</script>