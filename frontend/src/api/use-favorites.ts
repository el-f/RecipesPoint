import { useQuery, useQueryClient, useMutation } from "react-query";
import api from "./api";
import { Recipe } from "../@types/recipe";
import { useMemo } from "react";

export type FavoritesManager = ReturnType<typeof useFavorites>;

export const useFavorites = (userId: string) => {
  const queryClient = useQueryClient();

  const {
    data: favorites,
    isError,
    isLoading,
  } = useQuery(
    ["favorites"],
    () => {
      console.log("fetching favorites...");
      return api.getFavorites(userId);
    },
    {
      staleTime: 1000 * 60 * 5, // 5 minutes
      cacheTime: 1000 * 60 * 30, // 30 minutes
    }
  );

  const favoriteIdsSet = useMemo(() => { // memoize the built set (otherwise it will be built twice on each render instead of once)
    console.log("building favorites set");
    return new Set(favorites?.map((f: Recipe) => f.id) || []);
  }, [favorites]);

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
