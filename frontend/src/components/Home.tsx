import { CircularProgress } from "@mui/material";
import useRecipes from "../api/use-recipes";
import { RecipeGrid } from "./RecipeGrid";
import { useAtomValue } from "jotai";
import { queryAtom } from "../atoms/query-atom";

export default function Home() {
  const query = useAtomValue(queryAtom);
  const { data, isLoading, error } = useRecipes(query);

  if (isLoading) return <CircularProgress />;
  if (error) return "Something went wrong, please try again later.";

  return <RecipeGrid recipes={data} />;
}
