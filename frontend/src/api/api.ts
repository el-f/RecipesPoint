import axios from "axios";
import { RecipeQuery } from "../@types/recipe-query";

const BASE_URL = import.meta.env.VITE_API_URL;

console.log("BASE_URL", BASE_URL);

export const axiosClient = axios.create({
  baseURL: BASE_URL,
});

const api = {
  getFavorites: async (userId: string) => {
    const { data } = await axiosClient.get(`users/favorite-recipes/${userId}`);
    return data;
  },
  addFavorite: async (userId: string, recipeId: number) => {
    console.log("addFavorite", userId, recipeId);
    const { data } = await axiosClient.post(
      `users/favorite-recipes/${userId}/${recipeId}`
    );
    return data;
  },
  removeFavorite: async (userId: string, recipeId: number) => {
    console.log("removeFavorite", userId, recipeId);
    const { data } = await axiosClient.delete(
      `users/favorite-recipes/${userId}/${recipeId}`
    );
    return data;
  },
  getRecipes: async (query: RecipeQuery) => {
    const { data } = await axiosClient.post("/recipes/get", query);
    return data;
  },
};

export default api;
