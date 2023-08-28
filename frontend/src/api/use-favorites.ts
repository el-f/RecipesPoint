import { useQuery, useQueryClient, useMutation } from 'react-query';
import api from './api';

export const useFavorites = (userId: string) => {
  const queryClient = useQueryClient();

  const { data: favorites, isError, isLoading } = useQuery(
    ['favorites', userId],
    () => api.getFavorites(userId),
    {
      staleTime: 1000 * 60 * 5, // 5 minutes
      cacheTime: 1000 * 60 * 30, // 30 minutes
    }
  );

  const addFavoriteMutation = useMutation(
    (recipeId: string) => api.addFavorite(userId, recipeId),
    {
      onSuccess: () => {
        queryClient.invalidateQueries(['favorites', userId]);
      },
    }
  );

  const removeFavoriteMutation = useMutation(
    (recipeId: string) => api.removeFavorite(userId, recipeId),
    {
      onSuccess: () => {
        queryClient.invalidateQueries(['favorites', userId]);
      },
    }
  );

  return {
    favorites,
    isLoading,
    isError,
    addFavorite: addFavoriteMutation.mutate,
    removeFavorite: removeFavoriteMutation.mutate,
  };
};
