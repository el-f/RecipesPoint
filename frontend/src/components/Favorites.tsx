import { CircularProgress } from "@mui/material";
import { RecipeGrid } from "./RecipeGrid";
import { useAtomValue } from "jotai";
import { useFavorites } from "../api/use-favorites";
import { userAtom } from "../atoms/user-atom";

export default function Favorites() {
  const user = useAtomValue(userAtom);
  const favoritesManager = useFavorites(user.id);

  if (favoritesManager.isLoading) return <CircularProgress />;
  if (favoritesManager.isError) return "Something went wrong, please try again later.";

  return <RecipeGrid recipes={favoritesManager.favorites} favoritesManager={favoritesManager} />;
}