import { CircularProgress } from "@mui/material";
import useRecipes from "../api/use-recipes";
import { RecipeGrid } from "./RecipeGrid";
import { useAtomValue } from "jotai";
import { queryAtom } from "../atoms/query-atom";
import { userAtom } from "../atoms/user-atom";
import { useFavorites } from "../api/use-favorites";

export default function Home() {
  const query = useAtomValue(queryAtom);
  const { data, isLoading, error } = useRecipes(query);

  const user = useAtomValue(userAtom);
  const favoritesManager = useFavorites(user.id);

  if (isLoading || favoritesManager.isLoading) return <CircularProgress />;
  if (error || favoritesManager.isError)
    return "Something went wrong, please try again later.";

  return <RecipeGrid recipes={data} favoritesManager={favoritesManager} />;
}
