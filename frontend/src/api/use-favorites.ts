import { useQuery, useQueryClient, useMutation } from "react-query";
import api from "./api";
import { Recipe } from "../@types/recipe";

export const useFavorites = (userId: string) => {
  const queryClient = useQueryClient();

  const {
    data: favorites,
    isError,
    isLoading,
  } = useQuery(["favorites"], () => api.getFavorites(userId), {
    staleTime: 1000 * 60 * 5, // 5 minutes
    cacheTime: 1000 * 60 * 30, // 30 minutes
  });

  const favoriteIdsSet = new Set(favorites?.map((f: Recipe) => f.id) || []);

  const addFavoriteMutation = useMutation(
    (recipeId: number) => api.addFavorite(userId, recipeId),
    {
      onSuccess: () => {
        queryClient.invalidateQueries(["favorites"]);
      },
    }
  );

  const removeFavoriteMutation = useMutation(
    (recipeId: number) => api.removeFavorite(userId, recipeId),
    {
      onSuccess: () => {
        queryClient.invalidateQueries(["favorites"]);
      },
    }
  );

  return {
    favoriteIdsSet,
    favorites,
    isLoading,
    isError,
    addFavorite: addFavoriteMutation.mutate,
    removeFavorite: removeFavoriteMutation.mutate,
  };
};
