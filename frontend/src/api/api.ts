import axios from 'axios';
import { RecipeQuery } from '../@types/recipe-query';

const BASE_URL = import.meta.env.VITE_API_URL;

export const axiosClient = axios.create({
  baseURL: BASE_URL,
});

const api = {
  getFavorites: async (userId: string) => {
    const { data } = await axios.get(`users/favorite-recipes/${userId}`);
    return data;
  },
  addFavorite: async (userId: string, recipeId: string) => {
    const { data } = await axios.post(`users/favorite-recipes/${userId}/${recipeId}`);
    return data;
  },
  removeFavorite: async (userId: string, recipeId: string) => {
    const { data } = await axios.delete(`users/favorite-recipes/${userId}/${recipeId}`);
    return data;
  },
  getRecipes: async (query: RecipeQuery) => {
    const { data } = await axios.post('/recipes/get', query);
    return data;
  }
};

export default api;
