<template>
    <div class="modal-container">
        <div class="modal">
            <div class="modal-header">
                <p>Editar película</p>
                <span class="close" @click="cerrarModal">&times;</span>
            </div>
            <div class="modal-body">
                <form @submit.prevent="guardarCambiosPelicula">
                    <div class="form-group">
                        <label for="nombre">Nombre:</label>
                        <input type="text" id="nombre" v-model="pelicula.nombre">
                    </div>

                    <div class="form-group">
                        <label for="director">Director:</label>
                        <input type="text" id="director" v-model="pelicula.director">
                    </div>

                    <div class="form-group">
                        <label for="fecha">Fecha de publicación:</label>
                        <input type="date" id="fecha" v-model="pelicula.fechaPublicacion">
                    </div>

                    <div class="form-group">
                        <label for="duracion">Duración:</label>
                        <input type="number" id="duracion" v-model="pelicula.duracion">
                    </div>

                    <div class="form-group">
                        <label for="categoria">Categoría:</label>
                        <select id="categoria" v-model="pelicula.category.id">
                            <option v-for="categoria in categorias" :key="categoria.id" :value="categoria.id">{{
                                categoria.nombre }}</option>
                        </select>
                    </div>
                    <button type="submit">Guardar</button>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import ServiceMovie from '../../services/ServiceMovie';
import ServiceCategory from '../../services/ServiceCategory';

export default {
    data() {
        return {
            pelicula: {
                "id": null,
                "nombre": "",
                "director": "",
                "fechaPublicacion": "",
                "duracion": 0,
                "category": {
                    "id": null,
                    "nombreCategoria": ""
                }
            },
            categorias: []
        }
    },
    mounted() {
        this.obtenerCategorias();
        this.obtenerPeliculaSeleccionada();
    },
    methods: {
        async obtenerCategorias() {
            try {
                const categoriasResponse = await ServiceCategory.obtenerCategorias();
                const categorias = categoriasResponse.map(categoria => ({
                    id: categoria.id,
                    nombre: categoria.nombreCategoria
                }));
                this.categorias = categorias;
            } catch (error) {
                console.error("Error al obtener las categorías:", error);
            }
        },
        async guardarCambiosPelicula() {
            try {
                console.log("Datos de la película a enviar:", this.pelicula);
                await ServiceMovie.editarPeliculas(this.pelicula.id, this.pelicula);
                alert("Película actualizada con éxito");
                this.cerrarModal();
                window.location.reload();
            } catch (error) {
                console.error("Error al enviar los datos:", error);
            }
        },
        cerrarModal() {
            this.$emit('cerrar');
        },
        obtenerPeliculaSeleccionada() {
            const storedMovie = localStorage.getItem("selectedMovie");
            if (storedMovie) {
                const parsedMovie = JSON.parse(storedMovie);
                // Formatear la fecha en el formato "yyyy-MM-dd"
                parsedMovie.fechaPublicacion = new Date(parsedMovie.fechaPublicacion).toISOString().split('T')[0];
                this.pelicula = parsedMovie;
            }
        }
    }
}
</script>


<style scoped>
.modal-container {
    display: flex;
    justify-content: center;
    align-items: center;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
}

.modal {
    background-color: #fff;
    width: 60%;
    max-width: 500px;
    border-radius: 10px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
}

.modal-header {
    background-color: #007bff;
    padding: 10px;
    color: #fff;
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
    display: flex;
    justify-content: space-between;
}

.modal-body {
    padding: 20px;
}

.close {
    font-size: 24px;
    font-weight: bold;
    cursor: pointer;
    float: right;
    color: #fff;
}

.close:hover {
    color: #f00;
}

.form-group {
    margin-bottom: 20px;
}

label {
    display: block;
    margin-bottom: 5px;
    color: #333;
}

input[type="text"],
input[type="date"],
input[type="number"],
select {
    color: #333;
    width: calc(100% - 20px);
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

button {
    padding: 10px 20px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

button:hover {
    background-color: #0056b3;
}
</style>