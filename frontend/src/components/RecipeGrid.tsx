import { Grid } from "@mui/material";
import { Recipe } from "../@types/recipes";
import RecipeCard from "./RecipeCard";

export type RecipeGridProps = {
  recipes: Recipe[];
};

export function RecipeGrid({ recipes }: RecipeGridProps) {
  return (
    <Grid container spacing={3}>
      {recipes.map((recipe: Recipe) => (
        <Grid key={recipe.id} item xs={12} sm={6} md={4} lg={3}>
          <RecipeCard recipe={recipe} />
        </Grid>
      ))}
    </Grid>
  );
}
