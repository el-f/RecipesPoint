import { Button, Grid } from "@mui/material";
import { Recipe } from "../@types/recipe";
import RecipeCard from "./RecipeCard";
import { useAtom } from "jotai";
import { selectedRecipeAtom } from "../atoms/selected-recipe-atom";
import RecipeDetails from "./RecipeDetails";
import { queryAtom } from "../atoms/query-atom";

export type RecipeGridProps = {
  recipes: Recipe[];
};

export function RecipeGrid({ recipes }: RecipeGridProps) {
  const [selectedRecipe, setSelectedRecipe] = useAtom(selectedRecipeAtom);
  const [query, setQuery] = useAtom(queryAtom);

  const loadMore = () => {
    setQuery({
      ...query,
      offset: query.offset + 7, // we just move the offset, the new data will be merged with the old one
      number: query.number, // we keep the same number of recipes per load (we progress in pages linearly with the offset)
    });
  };

  const isOnHome = window.location.pathname === "/home";

  if (recipes.length === 0) {
    return "No recipes found.";
  }

  return (
    <>
      {selectedRecipe && (
        <RecipeDetails
          recipe={selectedRecipe}
          handleClose={() => setSelectedRecipe(null)}
        />
      )}
      <Grid container spacing={3}>
        {recipes.map((recipe: Recipe) => (
          <Grid key={recipe.id} item xs={12} sm={6} md={4} lg={4}>
            <RecipeCard recipe={recipe} />
          </Grid>
        ))}
      </Grid>
      {isOnHome && (
        <Button
          variant="contained"
          style={{ backgroundColor: "#5E81AC", color: "white", marginTop: 50 }}
          onClick={loadMore}
        >
          Load More
        </Button>
      )}
    </>
  );
}
