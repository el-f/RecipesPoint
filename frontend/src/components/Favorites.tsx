import { CircularProgress } from "@mui/material";
import { RecipeGrid } from "./RecipeGrid";
import { useAtomValue } from "jotai";
import { useFavorites } from "../api/use-favorites";
import { userAtom } from "../atoms/user-atom";

export default function Favorites() {
  const user = useAtomValue(userAtom);
  const { isLoading, isError, favorites } = useFavorites(user.id);

  if (isLoading) return <CircularProgress />;
  if (isError) return <div>Something went wrong, please try again later.</div>;

  return <RecipeGrid recipes={favorites} />;
}