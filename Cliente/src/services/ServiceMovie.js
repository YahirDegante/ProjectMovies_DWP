import axios from "axios";

const API_URL = "http://localhost:8081/api/movies/";

const registrarPelicula = async (movie) => {
    try {
        const response = await axios.post(API_URL, movie, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        console.log(response);
        return response.data;
    } catch (error) {
        console.error(error);
    }
};


const obtenerPeliculas = async (page, size) => {
    try {
        const response = await axios.post(API_URL + "page", {
            params: {
                page,
                size,
            },
        });
        console.log(response);
        return response.data;
    } catch (error) {
        console.error(error);
    }
};

export default {
    registrarPelicula,
    obtenerPeliculas,
};