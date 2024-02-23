import axios from "axios";

const API_URL = "http://localhost:8080/api/movies/";

const registrarPelicula = async (movie) => {
  try {
    const response = await axios.post(API_URL, movie, {
      headers: {
        "Content-Type": "application/json",
      },
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
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

const editarPeliculas = async (movieId, movieData) => {
  try {
    const response = await axios.put(`${API_URL}${movieId}`, movieData, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    console.log(response);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

const eliminarPeliculas = async (id) => {
  try {
    const response = await axios.delete(API_URL + id);
    console.log(response);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

export default {
  registrarPelicula,
  obtenerPeliculas,
  eliminarPeliculas,
  editarPeliculas,
};
