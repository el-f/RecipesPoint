import { useQuery, useQueryClient } from "react-query";
import { RecipeQuery } from "../@types/recipe-query";
import api from "./api";
import { Recipe } from "../@types/recipe";
import { useCallback, useMemo } from "react";
import _ from "lodash";

const useRecipes = (query: RecipeQuery) => {
  const queryClient = useQueryClient();

  const queryKey = useMemo(() => ["recipes", query], [query]); // useMemo to prevent triggering fetch render loop
  const lastQuery: RecipeQuery | undefined = queryClient.getQueryData(queryKey);

  const fetchRecipes = useCallback(async () => {
    console.log("fetching recipes", query);
    const data = await api.getRecipes(query);
    const [lastQ, currentQ] = [
      _.omit(lastQuery, ["offset", "number"]),
      _.omit(query, ["offset", "number"]),
    ];

    if (_.isEqual(lastQ, currentQ)) {
      // merge (when requesting next page) but remove duplicates (when refetching because of cacheTime)
      return _.uniqBy(
        [...(queryClient.getQueryData<Recipe[]>(queryKey) || []), ...data],
        "id"
      );
    }

    return data;
  }, [query, queryClient, queryKey, lastQuery]);

  return useQuery(queryKey, fetchRecipes, {
    staleTime: 1000 * 60 * 5, // 5 minutes
    cacheTime: 1000 * 60 * 30, // 30 minutes
  });
};

export default useRecipes;
