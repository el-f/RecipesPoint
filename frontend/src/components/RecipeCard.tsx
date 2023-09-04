import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import CardActions from "@mui/material/CardActions";
import Avatar from "@mui/material/Avatar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import StarBorderIcon from "@mui/icons-material/StarBorder";
import { Recipe } from "../@types/recipe";
import StarIcon from "@mui/icons-material/Star";
import { Tooltip } from "@mui/material";
import { useAtom } from "jotai";
import { FavoritesManager } from "../api/use-favorites";
import { cardStyle } from "../styles/styles";
import VisibilityIcon from "@mui/icons-material/Visibility";
import { selectedRecipeAtom } from "../atoms/selected-recipe-atom";

export type RecipeCardProps = {
  recipe: Recipe;
  favoritesManager: FavoritesManager;
};

export default function RecipeCard({ recipe, favoritesManager }: RecipeCardProps) {
  function handleFavToggle() {
    if (favoritesManager.favoriteIdsSet.has(recipe.id)) {
      favoritesManager.removeFavorite(recipe.id);
    } else {
      favoritesManager.addFavorite(recipe.id);
    }
  }

  const [, setSelectedRecipe] = useAtom(selectedRecipeAtom);

  return (
    <Card sx={cardStyle}>
      <CardHeader
        avatar={
          <Avatar>
            <Tooltip title="View recipe">
              <IconButton
                aria-label="recipe"
                onClick={() => setSelectedRecipe(recipe)}
              >
                <VisibilityIcon />
              </IconButton>
            </Tooltip>
          </Avatar>
        }
        title={recipe.title}
      />
      <CardMedia
        component="img"
        height="194"
        image={recipe.image}
        alt={recipe.title}
      />
      <CardContent>
        <Typography
          sx={{
            overflow: "hidden",
            textOverflow: "ellipsis",
            display: "-webkit-box",
            WebkitLineClamp: "2",
            WebkitBoxOrient: "vertical",
          }}
        >
          {recipe.summary?.replace(/(<([^>]+)>)/gi, "")}
        </Typography>
      </CardContent>
      <CardActions disableSpacing>
        {favoritesManager.favoriteIdsSet.has(recipe.id) ? (
          <Tooltip title="Remove from favorites">
            <IconButton
              aria-label="remove from favorites"
              onClick={handleFavToggle}
            >
              <StarIcon />
            </IconButton>
          </Tooltip>
        ) : (
          <Tooltip title="Add to favorites">
            <IconButton aria-label="add to favorites" onClick={handleFavToggle}>
              <StarBorderIcon />
            </IconButton>
          </Tooltip>
        )}
      </CardActions>
    </Card>
  );
}
