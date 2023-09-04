import { useQuery, useQueryClient } from "react-query";
import { RecipeQuery } from "../@types/recipe-query";
import api from "./api";
import { Recipe } from "../@types/recipe";
import { useCallback } from "react";
import _ from "lodash";
import { useAtom } from "jotai";
import { lastQueryAtom } from "../atoms/query-atom";

const useRecipes = (query: RecipeQuery) => {
  const queryClient = useQueryClient();

  const queryKey = ["recipes", query];

  const [lastQuery, setLastQuery] = useAtom(lastQueryAtom);

  const fetchRecipes = useCallback(async () => {
    console.log("fetching recipes...", query);
    const data = await api.getRecipes(query);
    const [lastQ, currentQ] = [
      _.omit(lastQuery, ["offset", "number"]),
      _.omit(query, ["offset", "number"]),
    ];

    const lastData =
      queryClient.getQueryData<Recipe[]>(["recipes", lastQuery]) || [];

    setLastQuery(query);

    if (_.isEqual(lastQ, currentQ)) {
      console.log("merging recipes", data, lastData);
      // merge (when requesting next page) but remove duplicates (when refetching because of cacheTime)
      return _.uniqBy([...lastData, ...data], "id");
    }

    console.log("overwriting recipes", data);
    return data;
  }, [query, lastQuery, queryClient, setLastQuery]);

  return useQuery(queryKey, fetchRecipes, {
    staleTime: 1000 * 60 * 5, // 5 minutes
    cacheTime: 1000 * 60 * 30, // 30 minutes
  });
};

export default useRecipes;
