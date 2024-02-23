import axios from "axios";

const API_URL = "http://localhost:8080/api/category/";

const obtenerCategorias = async () => {
    try {
        const response = await axios.get(API_URL);
        const categorias = response.data;
        return categorias;
    } catch (error) {
        console.error(error);
    }
}

export default {
    obtenerCategorias
};