<template>
  <div class="mb-4">
    <b-card
      header="Arrastra el formulario para registrar"
      header-bg-variant="primary"
      header-text-variant="white"
      draggable
      @dragstart="startDrag($event, JSON.stringify(pelicula))"
    >
      <b-form v-if="show">
        <b-alert show variant="warning" v-if="errors.length">
          <b>Error:</b>
          <ul>
            <li v-for="error in errors" :key="error.index">{{ error }}</li>
          </ul>
        </b-alert>

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
          id="input-group-4"
          label="Duración:"
          label-for="input-4"
        >
          <b-input-group>
            <template #append>
              <b-input-group-text>minutos</b-input-group-text>
            </template>

            <b-form-input
              id="input-4"
              v-model="pelicula.duracion"
              placeholder="Duración en minutos"
              type="number"
            />
          </b-input-group>
        </b-form-group>

        <b-form-group id="input-group-5" label="Categoria:" label-for="input-5">
          <b-form-select
            id="input-5"
            v-model="pelicula.categoria"
            :options="categorias"
          />
        </b-form-group>
      </b-form>
    </b-card>

    <div @drop="onDrop($event, 2)" @dragover.prevent @dragenter.prevent>
      <b-card class="text-center text-light bg-primary mt-4">
        <div>Arrastra aquí</div>
      </b-card>
    </div>
  </div>
</template>

<script>
import ServiceMovie from "../../services/ServiceMovie";
import ServiceCategory from "../../services/ServiceCategory";

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
      categorias: [{ text: "Selecciona una categoria", value: null }],
      errors: [],
      show: true,
    };
  },
  mounted() {
    this.obtenerCategorias();
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
        console.error("Error al obtener las categorías:", error);
      }
    },
    startDrag(evt, item) {
      evt.dataTransfer.dropEffect = "move";
      evt.dataTransfer.effectAllowed = "move";
    },
    validField: function (field) {
      var re = /^[0-9]+$/;
      return re.test(field);
    },
    async onDrop(evt) {
      this.errors = [];

      if (!this.pelicula.nombre) {
        this.errors.push("El nombre de la pelicula es obligatorio.");
      }

      if (!this.pelicula.director) {
        this.errors.push("El director de la pelicula es obligatorio.");
      }

      if (!this.pelicula.fechaPublicacion) {
        this.errors.push("La fecha de estreno de la pelicula es obligatoria.");
      }

      if (!this.pelicula.duracion) {
        this.errors.push("La duración de la pelicula es obligatorio.");
      } else if (
        this.pelicula.duracion <= 0 ||
        !this.validField(this.pelicula.duracion)
      ) {
        this.errors.push("La duración de la pelicula es invalida.");
      }

      if (!this.pelicula.categoria) {
        this.errors.push("La categoría de la pelicula es obligatoria.");
      }

      if (this.errors.length > 0) {
        evt.preventDefault();
        return;
      }

      try {
        console.log(this.categorias);
        const selectedCategory = this.categorias.find(
          (categoria) => categoria.value === this.pelicula.categoria
        );
        console.log(selectedCategory);
        const movie = {
          nombre: this.pelicula.nombre,
          director: this.pelicula.director,
          fechaPublicacion: this.pelicula.fechaPublicacion,
          duracion: this.pelicula.duracion,
          category: {
            id: selectedCategory.value,
            nombreCategoria: selectedCategory.text,
          },
        };
        await ServiceMovie.registrarPelicula(movie);
        this.pelicula.nombre = "";
        this.pelicula.director = "";
        this.pelicula.fechaPublicacion = "";
        this.pelicula.duracion = 0;
        this.pelicula.categoria = null;
        // Trick to reset/clear native browser form validation state
        this.show = false;
        this.$nextTick(() => {
          this.show = true;
        });
      } catch (error) {
        console.error("Error al enviar los datos:", error);
      }
    },
  },
};
</script>
